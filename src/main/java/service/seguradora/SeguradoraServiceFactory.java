package service.seguradora;

public class SeguradoraServiceFactory {
    private SeguradoraServiceFactory(){}
    public static SeguradoraService create(){
        return new SeguradoraServiceImpl();
    }
}
