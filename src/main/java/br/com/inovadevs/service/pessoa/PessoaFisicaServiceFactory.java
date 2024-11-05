package br.com.inovadevs.service.pessoa;

public class PessoaFisicaServiceFactory {
    private PessoaFisicaServiceFactory() {}
    public static PessoaFisicaService create() {
        return new PessoaFisicaServiceImpl();
    }
}
