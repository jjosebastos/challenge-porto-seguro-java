package controller;

import dao.veiculo.VeiculoDao;
import dao.veiculo.VeiculoDaoFactory;
import dtos.VeiculoDto;
import entity.Veiculo;
import exception.UnsupportedServiceOperationException;
import exception.VeiculoNotSavedException;
import service.veiculo.VeiculoService;
import service.veiculo.VeiculoServiceFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/restapi/veiculo")
public class VeiculoController {

    private final VeiculoService service = VeiculoServiceFactory.create();

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Response add(VeiculoDto input){
        if(input.getIdVeiculo() == null){
            try {
                Veiculo veiculo = this.service.create(new Veiculo(null, input.getPlaca(), input.getMarca(), input.getModelo(), input.getChassi(), input.getIdPessoa()));
                return Response.status(Response.Status.OK).entity(veiculo).build();
            } catch (SQLException | VeiculoNotSavedException e) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(Map.of("mensagem","não foi possível inserir imagem"
                        )).build();
            } catch (UnsupportedServiceOperationException e){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of(
                        "mensagem","erro inesperado ao tentar inserir pessoa"
                )).build();
            }

        }
    }

}
