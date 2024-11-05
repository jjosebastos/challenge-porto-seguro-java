package br.com.inovadevs.controller;

import br.com.inovadevs.dtos.VeiculoDto;
import br.com.inovadevs.entity.Veiculo;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;
import br.com.inovadevs.exception.VeiculoNotFoundException;
import br.com.inovadevs.exception.VeiculoNotSavedException;
import br.com.inovadevs.service.veiculo.VeiculoService;
import br.com.inovadevs.service.veiculo.VeiculoServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/veiculo")
public class VeiculoController {

    private final VeiculoService service = VeiculoServiceFactory.create();

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Response add(VeiculoDto input) throws UnsupportedServiceOperationException{
        if(input.getIdVeiculo() == null){
            try {
                Veiculo veiculo = this.service.create(new Veiculo(null, input.getPlaca(), input.getMarca(), input.getModelo(), input.getChassi(), input.getIdPessoa()));
                return Response.status(Response.Status.OK).entity(veiculo).build();
            } catch (SQLException | VeiculoNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "erro inesperado ao inserir veiculo"
                        )).build();
            }

        } else  {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of(
                    "mensagem","erro inesperado ao tentar inserir pessoa"
            )).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, VeiculoDto input){
        try {
            Veiculo updated = this.service.update(new Veiculo(null, input.getPlaca(), input.getMarca(), input.getModelo(), input.getChassi(), input.getIdPessoa()));
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (VeiculoNotFoundException v){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "erro inesperado ao atualizar veiculo")).build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response.status(Response.Status.OK)
                .entity(service.findAll()).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id){
        try {
            this.service.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (VeiculoNotFoundException v){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "erro inesperado ao deletar veiculo")).build();
        }
    }

}
