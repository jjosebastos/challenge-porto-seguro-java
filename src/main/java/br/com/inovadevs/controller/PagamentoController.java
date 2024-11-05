package br.com.inovadevs.controller;

import br.com.inovadevs.dtos.PagamentoDto;
import br.com.inovadevs.entity.Pagamento;
import br.com.inovadevs.exception.PagamentoNotFoundException;
import br.com.inovadevs.exception.PagamentoNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;
import br.com.inovadevs.service.pagamento.PagamentoService;
import br.com.inovadevs.service.pagamento.PagamentoServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/pagamento")
public class PagamentoController {


    private final PagamentoService pagamentoService = PagamentoServiceFactory.create();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Response add(PagamentoDto input) throws UnsupportedServiceOperationException {
        if (input.getIdPagamento() == null) {
            try {
                Pagamento pagamento =
                        this.pagamentoService.create(new Pagamento(null, input.getTipoPagamento(),
                                input.getData(), input.getValor(), input.getStatus(), input.getDescricao()
                                , input.getIdPessoa(), input.getIdSeguradora(), input.getIdAutorizada()));

                return Response.status(Response.Status.OK)
                        .entity(pagamento)
                        .build();

            } catch (SQLException | PagamentoNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "erro inesperado"))
                        .build();
            }

        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of(
                            "mensagem",
                            "esse método só permite criação de novos pagamento"
                    )).build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response.status(Response.Status.OK)
                .entity(this.pagamentoService.findAll()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, PagamentoDto input) {
        try {
            Pagamento updated = this.pagamentoService.update(new Pagamento(null, input.getTipoPagamento(),
                    input.getData(), input.getValor(), input.getStatus(), input.getDescricao()
                    , input.getIdPessoa(), input.getIdSeguradora(), input.getIdAutorizada()));
            return Response.status(Response.Status.OK)
                    .entity(updated)
                    .build();

        } catch (PagamentoNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();

        } catch (SQLException s){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of(
                            "mensagem", "erro inesperado ao atualizar pagamento"
                    )).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes
    public Response delete(@PathParam("id") long id) {
        try {
            this.pagamentoService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (PagamentoNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem",
                            "erro inesperado ao deletar pagamentos")).build();
        }
    }
}