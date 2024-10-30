package service;

public class AutorizadaServiceFactory {
    private AutorizadaServiceFactory(){

    }

    public static AutorizadaService create(){
        return new AutorizadaServiceImpl();
    }
}
