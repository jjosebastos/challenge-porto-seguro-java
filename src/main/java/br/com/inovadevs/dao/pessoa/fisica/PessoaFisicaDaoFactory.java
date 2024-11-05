package br.com.inovadevs.dao.pessoa.fisica;

public class PessoaFisicaDaoFactory {
    private PessoaFisicaDaoFactory() {}
    public static PessoaFisicaDao create(){
        return new PessoaFisicaDaoImpl();
    }
}
