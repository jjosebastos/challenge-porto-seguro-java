package model;

import java.util.List;

public class Carro extends Veiculo implements Orcamento {
    private double aroRoda;
    private double quilometragem;
    private String placa;

    public Carro(long idVeiculo, String chassi, String marca, String modelo, double aroRoda, double quilometragem, String placa) {
        super(idVeiculo, chassi, marca, modelo);
        this.aroRoda = aroRoda;
        this.quilometragem = quilometragem;
        this.placa = placa;
    }

    public double getAroRoda() {
        return aroRoda;
    }

    public double getQuilometragem() {
        return quilometragem;
    }

    public String getPlaca() {
        return placa;
    }




    @Override
    public int revisao() {
        int revisoesRealizadas = 0;

        if (quilometragem % 10000 == 0) {
            revisoesRealizadas += 1;
        }
        if (quilometragem % 20000 == 0) {
            revisoesRealizadas += 2;
        }
        if (quilometragem % 80000 == 0) {
            revisoesRealizadas += 1;
        }
        return  revisoesRealizadas;
    }
}
