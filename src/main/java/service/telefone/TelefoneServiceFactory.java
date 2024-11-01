package service.telefone;

public class TelefoneServiceFactory {
    private TelefoneServiceFactory(){}
    public TelefoneService create(){
        return new TelefoneServiceImpl();
    }
}
