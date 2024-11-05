package br.com.inovadevs.service.tecnico;

public class TecnicoServiceFactory {
    private TecnicoServiceFactory(){

    }

    public static TecnicoService create(){
        return new TecnicoServiceImpl();
    }
}
