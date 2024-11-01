package dao.pagamento;

public class PagamentoDaoFactory {
    private PagamentoDaoFactory() {}

    public static PagamentoDao create() {
        return new PagamentoDaoImpl();
    }

}
