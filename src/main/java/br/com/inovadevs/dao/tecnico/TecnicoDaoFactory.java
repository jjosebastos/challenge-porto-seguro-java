package br.com.inovadevs.dao.tecnico;

public class TecnicoDaoFactory {
    private TecnicoDaoFactory() {}
    public static TecnicoDao create() {
        return new TecnicoDaoImpl();
    }
}
