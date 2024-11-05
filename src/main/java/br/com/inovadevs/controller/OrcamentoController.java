package br.com.inovadevs.controller;

import br.com.inovadevs.dtos.OrcamentoDto;
import br.com.inovadevs.entity.Orcamento;
import br.com.inovadevs.exception.OrcamentoNotFoundException;
import br.com.inovadevs.exception.OrcamentoNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;
import br.com.inovadevs.service.orcamento.OrcamentoService;
import br.com.inovadevs.service.orcamento.OrcamentoServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/orcamento")
public class OrcamentoController {

    private final OrcamentoService service = OrcamentoServiceFactory.create();

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(OrcamentoDto input) throws UnsupportedServiceOperationException {
        if(input.getIdOrcamento() == null){
            try {
                Orcamento orcamento = this.service.create(new Orcamento(null , input.getDataHoraCriacao(), input.getStatus(),
                        input.getDiagnostico(), input.getIdVeiculo(), input.getIdTecnico()));
                return Response.status(Response.Status.CREATED).entity(orcamento).build();
            } catch (SQLException | OrcamentoNotSavedException e){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem","erro inesperado ao inserir orcamento")).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem","esse método só permite a inserção de novos usuarios")).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update (@PathParam("id") Long id, OrcamentoDto input){
        try {
            Orcamento orcamento = this.service.update(new Orcamento(null , input.getDataHoraCriacao(), input.getStatus(),
                    input.getDiagnostico(), input.getIdVeiculo(), input.getIdTecnico()));
            return Response.status(Response.Status.OK).entity(orcamento).build();
        } catch (OrcamentoNotFoundException o){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "erro inesperado ao atualizar orcamento")).build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response.status(Response.Status.OK).entity(service.findAll()).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id){
        try {
            this.service.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (OrcamentoNotFoundException o){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "erro inesperado ao excluir orcamento")).build();
        }
    }
}
