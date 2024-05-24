package model;

public class Moto extends Veiculo implements Orcamento{
    private double aroRoda;
    private int cilindradas;
    private int quilometragem;

    public Moto(long idVeiculo, String chassi, String marca, String modelo, double aroRoda, int cilindradas, int quilometragem) {
        super(idVeiculo, chassi, marca, modelo);
        this.aroRoda = aroRoda;
        this.cilindradas = cilindradas;
        this.quilometragem = quilometragem;
    }

    public double getAroRoda() {
        return aroRoda;
    }

    public void setAroRoda(double aroRoda) {
        this.aroRoda = aroRoda;
    }

    public int getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(int cilindradas) {
        this.cilindradas = cilindradas;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    @Override
    public int revisao() {
        int revisoesRealizadas = 0;

        if (this.quilometragem % 10000 == 0) {
            revisoesRealizadas += 1;
        }
        if (this.quilometragem % 50000 == 0) {
            revisoesRealizadas += 2;
        }
        if (this.quilometragem % 30000 == 0) {
            revisoesRealizadas += 1;
        }
        return revisoesRealizadas;
    }
}
