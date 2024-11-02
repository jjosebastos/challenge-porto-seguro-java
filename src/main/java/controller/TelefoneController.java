package controller;

import dtos.TelefoneDto;
import entity.Telefone;
import exception.TelefoneNotFoundException;
import exception.TelefoneNotSavedException;
import exception.UnsupportedServiceOperationException;
import service.telefone.TelefoneService;
import service.telefone.TelefoneServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/restapi/telefone")
public class TelefoneController {

    private final TelefoneService telefoneService = TelefoneServiceFactory.create();

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(TelefoneDto input) throws UnsupportedServiceOperationException {
        if(input.getIdAutorizada() == null) {
            try {
                Telefone telefone =
                        this.telefoneService.create(new Telefone(null, input.getNumero(), input.getDdd(), input.getTipoTelefone(),
                                input.getIdPessoa(),input.getIdSeguradora(), input.getIdAutorizada()));
                        return Response.status(Response.Status.CREATED)
                                .entity(telefone)
                                .build();
            } catch (SQLException | TelefoneNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem",
                                "erro inesperado ao tentar inserir pessoa"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "esse método so permite a ciração de novos telefones"))
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, TelefoneDto input){
        try {
            Telefone updated = this.telefoneService.update(new Telefone(null, input.getNumero(), input.getDdd(), input.getTipoTelefone(),
                    input.getIdPessoa(),input.getIdSeguradora(), input.getIdAutorizada()));
            return Response.status(Response.Status.OK).entity(updated).build();

        } catch (TelefoneNotFoundException t) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "erro ineseperado ao atualizar telefone"))
                    .build();
        }
    }

    @GET
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response.status(Response.Status.OK)
                .entity(this.telefoneService.readAll())
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id){
        try {
            this.telefoneService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (TelefoneNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem",
                            "erro inesperado ao tentar excluir telefone")).build();
        }

    }


}
