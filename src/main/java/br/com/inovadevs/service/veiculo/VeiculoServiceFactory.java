package br.com.inovadevs.service.veiculo;

public class VeiculoServiceFactory {
    private VeiculoServiceFactory(){}
    public static VeiculoService create(){
        return new VeiculoServiceImpl();
    }
}
