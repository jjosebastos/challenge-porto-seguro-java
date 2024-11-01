package service.pessoa;

public class PessoaServiceFactory {
    private PessoaServiceFactory() {}
    public static PessoaService create() {
        return new PessoaServiceImpl();
    }

}