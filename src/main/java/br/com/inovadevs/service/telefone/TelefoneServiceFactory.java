package br.com.inovadevs.service.telefone;

public class TelefoneServiceFactory {
    private TelefoneServiceFactory(){}
    public static TelefoneService create(){
        return new TelefoneServiceImpl();
    }
}
