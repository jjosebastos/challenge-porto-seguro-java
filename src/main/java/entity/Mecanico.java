package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mecanico extends Funcionario{

    public Mecanico(int idFuncionario, String nome, int rm) {
        super(idFuncionario, nome, rm);
    }

    public boolean isDisponivel(Boolean isDisponivel){
        return isDisponivel;
    }

    public List<String> diagnostico(String [] pecas){
        List<String> listaPecas = new ArrayList<>();
        listaPecas.addAll(Arrays.asList(pecas));
        return listaPecas;
    }


}
