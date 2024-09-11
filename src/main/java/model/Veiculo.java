package model;

public class Veiculo {
    private int idVeiculo;
    private String modelo;
    private String marca;
    private String chassi;
    private String placa;
    private int quilometragem;



    public Veiculo(int idVeiculo, String chassi, String marca, String modelo, String placa, int quilometragem) {
        this.idVeiculo = idVeiculo;
        this.chassi = chassi;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.quilometragem = quilometragem;
    }

    public long getIdVeiculo() {
        return idVeiculo;
    }

    public void setId(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

}
