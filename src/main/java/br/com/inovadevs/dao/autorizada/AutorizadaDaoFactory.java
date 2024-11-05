package br.com.inovadevs.dao.autorizada;

public class AutorizadaDaoFactory {
    private AutorizadaDaoFactory(){

    }
    public static AutorizadaDao create(){
        return new AutorizadaDaoImpl();
    }
}
