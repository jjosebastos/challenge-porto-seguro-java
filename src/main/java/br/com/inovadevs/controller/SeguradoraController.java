package br.com.inovadevs.controller;

import br.com.inovadevs.dtos.SeguradoraDto;
import br.com.inovadevs.entity.Seguradora;
import br.com.inovadevs.exception.SeguradoraNotFoundException;
import br.com.inovadevs.exception.SeguradoraNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;
import br.com.inovadevs.service.seguradora.SeguradoraService;
import br.com.inovadevs.service.seguradora.SeguradoraServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/seguradora")
public class SeguradoraController {

    private final SeguradoraService service = SeguradoraServiceFactory.create();

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(SeguradoraDto input) throws UnsupportedServiceOperationException {
        if(input.getIdSeguradora() == null){
            try {
                Seguradora seguradora = this.service.create(new Seguradora(null, input.getNome(), input.getCnpj(), input.getIdVeiculo()));
                return Response.status(Response.Status.CREATED).entity(seguradora).build();
            } catch (SQLException | SeguradoraNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mesagem", "erro inesperado ao tentar inserir seguradora"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem",
                            "esse método só permite a criação de novas pessoas"))
                    .build();
        }

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(SeguradoraDto input, @PathParam("id") Long id) {
        try {
            Seguradora seguradora = this.service.update(new Seguradora(null, input.getNome(), input.getCnpj(), input.getIdVeiculo()));
            return Response.status(Response.Status.OK).entity(seguradora).build();
        } catch (SeguradoraNotFoundException s){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "")).build();
        }

    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.status(Response.Status.OK).entity(this.service.readAll()).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        try {
            this.service.delete(id);
            return Response.status(Response.Status.OK).build();
        } catch (SeguradoraNotFoundException s){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem","erro inesperado ao excluir seguradora")).build();
        }
    }
}
