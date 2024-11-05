package br.com.inovadevs.entity;

import java.util.Objects;

public class Veiculo {
    private Long idVeiculo;
    private String placa;
    private String marca;
    private String modelo;
    private String chassi;
    private Long idPessoa;

    public Veiculo(Long idVeiculo, String placa, String marca, String modelo, String chassi, Long idPessoa) {
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.chassi = chassi;
        this.idPessoa = idPessoa;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
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

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idCliente) {
        this.idPessoa = idPessoa;
    }

    public boolean isChassiValido(String chassi){
        return chassi != null && chassi.length() == 17;
    }


    public void setChassi(String chassi) {
        if (isChassiValido(chassi)){
            this.chassi = chassi;
        }
        throw new RuntimeException("Chassi inválido");
    }

    public String getChassi() {
        return chassi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(idVeiculo, veiculo.idVeiculo) && Objects.equals(placa, veiculo.placa) && Objects.equals(marca, veiculo.marca) && Objects.equals(modelo, veiculo.modelo) && Objects.equals(chassi, veiculo.chassi) && Objects.equals(idPessoa, veiculo.idPessoa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVeiculo, placa, marca, modelo, chassi, idPessoa);
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "idVeiculo=" + idVeiculo +
                ", placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", chassi='" + chassi + '\'' +
                ", idPessoa=" + idPessoa +
                '}';
    }
}
