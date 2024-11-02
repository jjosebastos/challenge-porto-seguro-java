package service.historico;

public class HistoricoVeiculoServiceFactory {
    private HistoricoVeiculoServiceFactory(){}
    public static HistoricoVeiculoService create(){
        return new HistoricoVeiculoServiceImpl();
    }
}
