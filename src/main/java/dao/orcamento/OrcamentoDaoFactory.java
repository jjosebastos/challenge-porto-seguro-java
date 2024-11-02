package dao.orcamento;

public class OrcamentoDaoFactory {
    private OrcamentoDaoFactory() {}
    public static OrcamentoDao create() {
        return new OrcamentoDaoImpl();
    }
}
