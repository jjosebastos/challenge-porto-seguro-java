package service.pagamento;

public class PagamentoServiceFactory {
    private PagamentoServiceFactory (){}
    public static PagamentoService create(){
        return new PagamentoServiceImpl();
    }
}
