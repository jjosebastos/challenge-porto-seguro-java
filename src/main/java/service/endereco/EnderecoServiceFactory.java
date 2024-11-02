package service.endereco;

public class EnderecoServiceFactory {
    private EnderecoServiceFactory(){}
    public static EnderecoService create () {
        return new EnderecoServiceImpl();

    }
}
