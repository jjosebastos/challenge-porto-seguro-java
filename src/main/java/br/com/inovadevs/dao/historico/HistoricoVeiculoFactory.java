package br.com.inovadevs.dao.historico;

public class HistoricoVeiculoFactory {
    private HistoricoVeiculoFactory(){

    }

    public static HistoricoVeiculoDao create(){
        return new HistoricoVeiculoDaoImpl();
    }
}
