package dao.seguradora;

import dao.autorizada.AutorizadaDao;
import dao.autorizada.AutorizadaDaoImpl;

public class SeguradoraDaoFactory {
    private SeguradoraDaoFactory(){

    }

    public static SeguradoraDao create(){
        return new SeguradoraDaoImpl();
    }
}
