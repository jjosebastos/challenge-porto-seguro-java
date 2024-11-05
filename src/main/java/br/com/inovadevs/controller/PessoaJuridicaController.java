package br.com.inovadevs.controller;

import br.com.inovadevs.dtos.PessoaJuridicaDto;
import br.com.inovadevs.entity.PessoaJuridica;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;
import br.com.inovadevs.service.pessoa.PessoaJuridicaService;
import br.com.inovadevs.service.pessoa.PessoaJuridicaServiceFactory;
import exception.ConjuntoPessoaNotSavedException;
import exception.PessoaJuridicaNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/pessoa-juridica")
public class PessoaJuridicaController {

    private final PessoaJuridicaService service = PessoaJuridicaServiceFactory.create();

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(PessoaJuridicaDto input) throws UnsupportedServiceOperationException {
        if(input.getIdPessoaJuridica() == null){
            try {
                PessoaJuridica pessoaJuridica = this.service.create(new PessoaJuridica(null, input.getTipo(), input.getStatus(), null, input.getCnpj(), input.getRazaoSocial(), input.getNomeFantasia()));
                return Response.status(Response.Status.CREATED).entity(pessoaJuridica).build();
            } catch (SQLException | ConjuntoPessoaNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem","erro inesperado ao inserir pessoa jurídica")).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, PessoaJuridicaDto input) throws ConjuntoPessoaNotSavedException, UnsupportedServiceOperationException {
        try{
            PessoaJuridica update = this.service.update(new PessoaJuridica(id, input.getTipo(), input.getStatus(), null, input.getCnpj(), input.getRazaoSocial(), input.getNomeFantasia()));
            return Response.status(Response.Status.OK).entity(update).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "erro inesperado ao atualizar pessoa jurídica")).build();
        } catch (PessoaJuridicaNotFoundException e){
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
        } catch (PessoaJuridicaNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
