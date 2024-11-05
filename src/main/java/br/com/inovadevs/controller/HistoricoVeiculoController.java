package br.com.inovadevs.controller;

import br.com.inovadevs.dtos.HistoricoVeiculoDto;
import br.com.inovadevs.entity.HistoricoVeiculo;
import br.com.inovadevs.exception.HistoricoVeiculoNotFoundException;
import br.com.inovadevs.exception.HistoricoVeiculoNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;
import br.com.inovadevs.service.historico.HistoricoVeiculoService;
import br.com.inovadevs.service.historico.HistoricoVeiculoServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/historico")
public class HistoricoVeiculoController {

    private final HistoricoVeiculoService service = HistoricoVeiculoServiceFactory.create();

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(HistoricoVeiculoDto input) throws UnsupportedServiceOperationException{
        if(input.getIdVeiculo() == null){
            try {
                HistoricoVeiculo historicoVeiculo = this.service.create(new HistoricoVeiculo(null,
                        input.getTipoEvento(), input.getDataEvento(), input.getDescricao(), input.getKmOdometro(),
                        input.getIdVeiculo(), input.getIdAutorizada()));
                return Response.status(Response.Status.CREATED).entity(historicoVeiculo).build();
            } catch (SQLException | HistoricoVeiculoNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "")).build();
            }

        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem","esse método só permite a criação de novos historicos")).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HistoricoVeiculoDto input, @PathParam("id") Integer id){
        try {
            HistoricoVeiculo historicoVeiculo = this.service.update(new HistoricoVeiculo(null,
                    input.getTipoEvento(), input.getDataEvento(), input.getDescricao(), input.getKmOdometro(),
                    input.getIdVeiculo(), input.getIdAutorizada()));
            return Response.status(Response.Status.OK).entity(historicoVeiculo).build();
        } catch (HistoricoVeiculoNotFoundException h) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("map", "erro inesperado ao atualizar historico")).build();
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
    public Response delete(@PathParam("id") long id){
        try {
            this.service.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (HistoricoVeiculoNotFoundException h){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "erro inesperado ao excluir historico")).build();
        }
    }
}
