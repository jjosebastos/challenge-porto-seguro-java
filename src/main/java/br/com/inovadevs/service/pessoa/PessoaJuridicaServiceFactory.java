package br.com.inovadevs.service.pessoa;

public class PessoaJuridicaServiceFactory {
    private PessoaJuridicaServiceFactory() {}
    public static PessoaJuridicaService create() {
        return new PessoaJuridicaServiceImpl();
    }
}
