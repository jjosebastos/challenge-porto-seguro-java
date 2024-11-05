package br.com.inovadevs.dao.pessoa;

public class PessoaDaoFactory {
    private PessoaDaoFactory(){}

    public static PessoaDao create(){
        return new PessoaDaoImpl();
    }
}
