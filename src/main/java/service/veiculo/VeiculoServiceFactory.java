package service.veiculo;

import dao.veiculo.VeiculoDao;
import dao.veiculo.VeiculoDaoImpl;

public class VeiculoServiceFactory {
    private VeiculoServiceFactory(){}
    public static VeiculoService create(){
        return new VeiculoServiceImpl();
    }
}
