package br.com.inovadevs.dao.pessoa.juridica;

public class PessoaJuridicaDaoFactory {
    private PessoaJuridicaDaoFactory() {}
    public static PessoaJuridicaDao create() {
        return new PessoaJuridicaDaoImpl();
    }
}
