package br.com.inovadevs.dao.veiculo;

public class VeiculoDaoFactory {
    private VeiculoDaoFactory(){}

    public static VeiculoDao create(){
        return new VeiculoDaoImpl();
    }
}
