package service;

import entity.PessoaFisica;

public class PessoaServiceFactory {
    private PessoaServiceFactory() {}

    public static PessoaService create(String tipo) {
        if(tipo.equalsIgnoreCase("pf")){
            return new PessoaFisica(int i);
        }
    }
}
