package entity;

import java.util.Objects;

public class Veiculo {
    private int idVeiculo;
    private  String placa;
    private String marca;
    private String modelo;
    private String chassi;
    private Integer idCliente;


    public Veiculo(int idVeiculo, String placa, String marca, String modelo, String chassi, Integer idCliente) {
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.chassi = chassi;
        this.idCliente = idCliente;
    }

    public boolean isChassiValido(String chassi){
        return chassi != null && chassi.length() == 17;
    }


    public int getIdVeiculo() {
        return idVeiculo;
    }

    public int chassiLenght(String chassi){
        return chassi.length();
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        if (isChassiValido(chassi)){
            this.chassi = chassi;
        }
        throw new RuntimeException("Chassi inválido");
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return idVeiculo == veiculo.idVeiculo && Objects.equals(placa, veiculo.placa) && Objects.equals(marca, veiculo.marca) && Objects.equals(modelo, veiculo.modelo) && Objects.equals(chassi, veiculo.chassi) && Objects.equals(idCliente, veiculo.idCliente);
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "idVeiculo=" + idVeiculo +
                ", placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", chasse='" + chassi + '\'' +
                ", idCliente=" + idCliente +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVeiculo, placa, marca, modelo, chassi, idCliente);
    }
}
