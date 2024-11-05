package br.com.inovadevs.controller;

import br.com.inovadevs.dtos.EnderecoDto;
import br.com.inovadevs.entity.Endereco;
import br.com.inovadevs.exception.EnderecoNotFoundException;
import br.com.inovadevs.exception.EnderecoNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;
import br.com.inovadevs.service.endereco.EnderecoService;
import br.com.inovadevs.service.endereco.EnderecoServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService = EnderecoServiceFactory.create();
    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(EnderecoDto input) throws UnsupportedServiceOperationException {
        if(input.getIdAutorizada() == null){
            try {
                Endereco endereco = this.enderecoService.create(new Endereco(null, input.getRua(), input.getNumero(), input.getBairro(), input.getCidade(),
                        input.getUf(), input.getCep(), input.getComplemento(),
                        input.getIdCliente(), input.getIdSeguradora(), input.getIdSeguradora()));
                return Response.status(Response.Status.OK).entity(endereco).build();
            } catch (SQLException | EnderecoNotSavedException e){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem","erro inesperado ao inserir endereco")).build();
            }

        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "esse método só criação de novos enderecos "))
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, EnderecoDto input){
        try  {
            Endereco updated = this.enderecoService.update(new Endereco(null, input.getRua(), input.getNumero(), input.getBairro(), input.getCidade(),
                    input.getUf(), input.getCep(), input.getComplemento(),
                    input.getIdCliente(), input.getIdSeguradora(), input.getIdSeguradora()));
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (EnderecoNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND)
                    .build();

        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "erro inesperado ao atualizar endereco")).build();
        }

    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response.status(Response.Status.OK)
                .entity(this.enderecoService.findAll())
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id){
        try {
            this.enderecoService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (EnderecoNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "erro inesperado ao tentar excluir Endereco")).build();
        }

    }
}
