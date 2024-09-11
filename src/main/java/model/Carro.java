package model;

import java.util.List;

public class Carro extends Veiculo {
    private int numeroPortas;
    private String cor;
    private String categoria;


    public Carro(int idVeiculo, String chassi, String marca, String modelo, String placa, int quilometragem, int numeroPortas, String cor, String categoria) {
        super(idVeiculo, chassi, marca, modelo, placa, quilometragem);
        this.numeroPortas = numeroPortas;
        this.cor = cor;
        this.categoria = categoria;
    }

    public int getNumeroPortas() {
        return numeroPortas;
    }

    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
