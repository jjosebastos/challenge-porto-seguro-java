package model;

public class Veiculo {
    private long idVeiculo;
    private String modelo;
    private String marca;
    private String chassi;


    public Veiculo(long idVeiculo, String chassi, String marca, String modelo) {
        this.idVeiculo = idVeiculo;
        this.chassi = chassi;
        this.marca = marca;
        this.modelo = modelo;
    }

    public long getIdVeiculo() {
        return idVeiculo;
    }

    public void setId(long idVeiculo) {
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
