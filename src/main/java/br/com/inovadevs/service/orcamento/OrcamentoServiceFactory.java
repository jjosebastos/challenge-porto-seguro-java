package br.com.inovadevs.service.orcamento;

public class OrcamentoServiceFactory {
    private OrcamentoServiceFactory (){}
    public static OrcamentoService create(){
        return new OrcamentoServiceImpl();
    }
}
