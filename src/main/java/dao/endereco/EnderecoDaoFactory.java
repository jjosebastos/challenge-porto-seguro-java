package dao.endereco;

public class EnderecoDaoFactory {
    private EnderecoDaoFactory() {}
    public static EnderecoDao create() {
        return new EnderecoDaoImpl();
    }
}
