package service;

public class TecnicoServiceFactory {
    private TecnicoServiceFactory(){

    }

    public static TecnicoService create(){
        return new TecnicoServiceImpl();
    }
}
