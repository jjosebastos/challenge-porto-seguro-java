package br.com.inovadevs.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Reparo {
    private int idReparo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String descricao;
    private Double valorServico;
    private Integer idTecnico;


    public Reparo(int idReparo, LocalDate dataInicio, LocalDate dataFim, String descricao, Double valorServico, Integer idTecnico) {
        this.idReparo = idReparo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        if(isDataValida(dataInicio, dataFim)){
            throw new RuntimeException("Datas inválidas");
        }
        this.descricao = descricao;
        this.valorServico = valorServico;
        this.idTecnico = idTecnico;
    }

    public boolean isDataValida(LocalDate dataInicio, LocalDate dataFim){
        return dataInicio.isAfter(dataFim);
    }

    public int getIdReparo() {
        return idReparo;
    }

    public void setIdReparo(int idReparo) {
        this.idReparo = idReparo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorServico() {
        return valorServico;
    }

    public void setValorServico(Double valorServico) {
        this.valorServico = valorServico;
    }

    public Integer getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(Integer idTecnico) {
        this.idTecnico = idTecnico;
    }

    @Override
    public String toString() {
        return "Reparo{" +
                "idReparo=" + idReparo +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", descricao='" + descricao + '\'' +
                ", valorServico=" + valorServico +
                ", idTecnico=" + idTecnico +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reparo reparo = (Reparo) o;
        return idReparo == reparo.idReparo && Objects.equals(dataInicio, reparo.dataInicio) && Objects.equals(dataFim, reparo.dataFim) && Objects.equals(descricao, reparo.descricao) && Objects.equals(valorServico, reparo.valorServico) && Objects.equals(idTecnico, reparo.idTecnico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReparo, dataInicio, dataFim, descricao, valorServico, idTecnico);
    }
}
