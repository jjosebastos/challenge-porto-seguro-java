package br.com.inovadevs.controller;

import br.com.inovadevs.dtos.PessoaFisicaDto;
import br.com.inovadevs.entity.PessoaFisica;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;
import br.com.inovadevs.service.pessoa.PessoaFisicaService;
import br.com.inovadevs.service.pessoa.PessoaFisicaServiceFactory;
import exception.ConjuntoPessoaNotSavedException;
import exception.PessoaFisicaNotFoundException;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/pessoa-fisica")
public class PessoaFisicaController {

    private final PessoaFisicaService service = PessoaFisicaServiceFactory.create();

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(PessoaFisicaDto input) throws UnsupportedServiceOperationException {
        if(input.getIdPessoaFisica() == null){
            try {
                PessoaFisica pessoaFisica = this.service.create(new PessoaFisica(null, input.getTipo(), input.getStatus(), null, input.getNome(), input.getDataNascimento(),
                        input.getCpf(), input.getGenero()));
                return Response.status(Response.Status.CREATED).entity(pessoaFisica).build();
            } catch (SQLException | ConjuntoPessoaNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem","erro inesperado ao inserir pessoa")).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, PessoaFisicaDto input) throws ConjuntoPessoaNotSavedException, UnsupportedServiceOperationException {
        try{
            PessoaFisica update = this.service.update(new PessoaFisica(null, input.getTipo(), input.getStatus(), null,
                    input.getNome(), input.getDataNascimento(), input.getCpf(), input.getGenero()));
            return Response.status(Response.Status.OK).entity(update).build();
        } catch (SQLException  e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "erro inesperado ao atualizar pessoa")).build();
        } catch ( PessoaFisicaNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
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
            this.service.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (PessoaFisicaNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
