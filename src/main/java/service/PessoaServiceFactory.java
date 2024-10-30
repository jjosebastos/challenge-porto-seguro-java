package service;

import entity.PessoaFisica;

public class PessoaServiceFactory {
    private PessoaServiceFactory() {}
    public static PessoaService create() {
        return new PessoaServiceImpl();
    }

}
