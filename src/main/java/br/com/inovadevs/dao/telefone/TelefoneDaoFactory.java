package br.com.inovadevs.dao.telefone;

public class TelefoneDaoFactory {
    private TelefoneDaoFactory() {}
    public static TelefoneDao create() {
        return new TelefoneDaoImpl();
    }
}
