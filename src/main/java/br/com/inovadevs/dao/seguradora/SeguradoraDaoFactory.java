package br.com.inovadevs.dao.seguradora;

public class SeguradoraDaoFactory {
    private SeguradoraDaoFactory(){

    }

    public static SeguradoraDao create(){
        return new SeguradoraDaoImpl();
    }
}
