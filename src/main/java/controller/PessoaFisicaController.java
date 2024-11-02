package controller;

import dtos.PessoaFisicaDto;
import service.pessoa.PessoaFisicaService;
import service.pessoa.PessoaServiceFactory;
import service.seguradora.SeguradoraServiceFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/restapi/seguradora")
public class PessoaFisicaController {

    private final PessoaFisicaService service = PessoaServiceFactory.create();

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(PessoaFisicaDto input){
        if(input.getIdPessoaFisica() == null){
            try {
                PessoaFisicaDto pessoaFisicaDto = new PessoaFisicaDto();
            }
        }
    }
}
