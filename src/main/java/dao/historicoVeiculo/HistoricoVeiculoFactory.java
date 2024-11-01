package dao.historicoVeiculo;

public class HistoricoVeiculoFactory {
    private HistoricoVeiculoFactory(){

    }

    public static HistoricoVeiculoDao create(){
        return new HistoricoVeiculoDaoImpl();
    }
}
