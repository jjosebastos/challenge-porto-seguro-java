package br.com.inovadevs.service.autorizada;

public class AutorizadaServiceFactory {
    private AutorizadaServiceFactory(){

    }

    public static AutorizadaService create(){
        return new AutorizadaServiceImpl();
    }
}
