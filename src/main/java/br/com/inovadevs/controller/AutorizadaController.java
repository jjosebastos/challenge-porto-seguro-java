package br.com.inovadevs.controller;

import br.com.inovadevs.dtos.AutorizadaDto;
import br.com.inovadevs.entity.Autorizada;
import br.com.inovadevs.exception.*;
import br.com.inovadevs.service.autorizada.AutorizadaService;
import br.com.inovadevs.service.autorizada.AutorizadaServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/autorizada")
public class AutorizadaController {

private final AutorizadaService autorizadaService = AutorizadaServiceFactory.create();

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add (AutorizadaDto input) throws UnsupportedServiceOperationException {
        if(input.getIdAutorizada() == null){
            try {
                Autorizada autorizada = this.autorizadaService.create(new Autorizada(null, input.getNome(), input.getCnpj()));
                return Response
                        .status(Response.Status.CREATED)
                        .entity(autorizada)
                        .build();
            } catch (SQLException | AutorizadaNotSavedException e ){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "erro inesperado ao tentar inserir pessoa"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of(
                            "mensagem",
                            "esse método só permite criação de novas pessoas"
                    )).build();
        }

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id")Long id, AutorizadaDto input){
        try {
            Autorizada updated = this.autorizadaService.update(new Autorizada(id, input.getNome(), input.getCnpj()));
            return Response.status(Response.Status.OK)
                    .entity(updated).build();

        } catch (AutorizadaNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "erro inesperado ao atualizar em autorizada")).build();
        }
    }


    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        return Response.status(Response.Status.OK)
                .entity(this.autorizadaService.findAll())
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id){
        try {
            this.autorizadaService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT)
                    .build();
        } catch (AutorizadaNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "erro inesperado ao excluir em T_CON_AUTORIZADA")).build();
        }

    }


}
