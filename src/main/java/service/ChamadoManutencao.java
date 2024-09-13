package service;


import entity.Endereco;
import entity.Manutencao;
import entity.Mecanico;
import entity.Veiculo;

import java.util.ArrayList;
import java.util.List;


public class ChamadoManutencao {
    List<Manutencao> listManutencao;

    public ChamadoManutencao(){
        this.listManutencao = new ArrayList<>();
    }


    public void adicionarNovaSolicitacao(int id, Veiculo veiculo, Mecanico mecanico, String descricao, Endereco endereco){
        listManutencao.add(new Manutencao(id, veiculo, mecanico, descricao, endereco));
    }

    public void exibirListaManutencoes(){
        System.out.println(listManutencao);
    }

    public void cancelarManutencao(Long id){
        List<Manutencao> manutencoesCancelar = new ArrayList<>();

        if (!listManutencao.isEmpty()){
            for (Manutencao m : listManutencao){
                if(m.getId() == id){
                    manutencoesCancelar.add(m);
                }
            }
        } else {
            throw new RuntimeException("Lista vazia");
        }
        listManutencao.remove(manutencoesCancelar);
    }

}