package br.com.inovadevs.controller;

import br.com.inovadevs.dtos.PessoaDto;
import br.com.inovadevs.entity.Pessoa;
import br.com.inovadevs.exception.PessoaNotFoundException;
import br.com.inovadevs.exception.PessoaNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;
import br.com.inovadevs.service.pessoa.PessoaService;
import br.com.inovadevs.service.pessoa.PessoaServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;


@Path("/rest/pessoa")
public class PessoaController {
    private final PessoaService pessoaService = PessoaServiceFactory.create();

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(PessoaDto input) throws UnsupportedServiceOperationException {
        if(input.getIdPessoa() == null) {
            try {

                Pessoa pessoa = this.pessoaService.create(new Pessoa(null, input.getTipoCliente(), input.getStatus()));
                return Response
                        .status(Response.Status.CREATED)
                        .entity(pessoa)
                        .build();

            } catch (SQLException | PessoaNotSavedException e){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "erro inesperado ao tentar inserir pessoa")).build();
            }
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(Map.of(
                        "mensagem",
                    "esse método só permite a criação de novas pessoas"))
                    .build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        return Response.status(Response.Status.OK)
                .entity(this.pessoaService.findAll())
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Long id){
        try {
            this.pessoaService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (PessoaNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "erro inesperado ao tentar deletar pessoa")).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, PessoaDto input){
        try {
            Pessoa update = this.pessoaService.update(new Pessoa(id, input.getTipoCliente(), input.getStatus()));
            return Response.status(Response.Status.OK)
                    .entity(update)
                    .build();
        } catch (PessoaNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "erro inesperado ao atualizar em pessoa")).build();
        }
    }
}
