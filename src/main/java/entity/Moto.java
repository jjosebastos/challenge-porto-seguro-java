package entity;

public class Moto extends Veiculo {
    private double aroRoda;
    private int cilindradas;

    public Moto(int idVeiculo, String chassi, String marca, String modelo, String placa,
                int quilometragem, double aroRoda, int cilindradas) {
        super(idVeiculo, chassi, marca, modelo, placa, quilometragem);
        this.aroRoda = aroRoda;
        this.cilindradas = cilindradas;
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



}
