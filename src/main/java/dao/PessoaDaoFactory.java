package dao;

public class PessoaDaoFactory {
    private PessoaDaoFactory(){}

    public static PessoaDao create(){
        return new PessoaDaoImpl();
    }
}
