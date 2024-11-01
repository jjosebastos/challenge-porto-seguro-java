package controller;

import dtos.TecnicoDto;
import entity.Tecnico;
import exception.TecnicoNotFoundException;
import exception.TecnicoNotSavedException;
import exception.UnsupportedServiceOperationException;
import service.tecnico.TecnicoService;
import service.tecnico.TecnicoServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/tecnico")
public class TecnicoController {

    private final TecnicoService tecnicoService = TecnicoServiceFactory.create();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Response add(TecnicoDto input) throws UnsupportedServiceOperationException {
        if (input.getIdTecnico() == null)  {
            try {
                Tecnico tecnico = this.tecnicoService.create(new Tecnico(null, input.getNome(), input.getDataNascimento(),
                        input.getMatricula(), input.getIdAutorizada()));
                return Response.status(Response.Status.OK).entity(tecnico).build();
            } catch (SQLException | TecnicoNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of(
                                "mensagem",
                                "erro inesperado ao tentar inserir tecnico")).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(Map.of(
                        "mensagem",
                        "esse método só permite criação de novas pessoas"
                )).build();

        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        return Response.status(Response.Status.OK)
                .entity(this.tecnicoService.readAll())
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        try {
            this.tecnicoService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (TecnicoNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of(
                            "mensagem",
                            "erro inesperado ao tentar deletar tecnico"
                    )).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, TecnicoDto input){
        try {
            Tecnico update = this.tecnicoService.update(new Tecnico(id, input.getNome(),
                    input.getDataNascimento(), input.getMatricula(), input.getIdAutorizada()));
            return Response.status(Response.Status.OK).entity(update).build();
        } catch (TecnicoNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of(
                            "mensagem",
                            "erro inesperado ao"
                    )).build();
        }
    }
}
