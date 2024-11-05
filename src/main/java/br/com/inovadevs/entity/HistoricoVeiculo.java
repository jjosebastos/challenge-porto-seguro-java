package br.com.inovadevs.entity;

import java.util.Date;
import java.util.Objects;

public class HistoricoVeiculo {

    private Long idHistorico;
    private String tipoEvento;
    private Date dataEvento;
    private String descricao;
    private int kmOdometro;
    private Long idVeiculo;
    private Long idAutorizada;

    public HistoricoVeiculo(Long idHistorico, String tipoEvento, Date dataEvento, String descricao, int kmOdometro, Long idVeiculo, Long idAutorizada) {
        this.idHistorico = idHistorico;
        this.tipoEvento = tipoEvento;
        this.dataEvento = dataEvento;
        this.descricao = descricao;
        this.kmOdometro = kmOdometro;
        this.idVeiculo = idVeiculo;
        this.idAutorizada = idAutorizada;
    }

    public Long getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(Long idHistorico) {
        this.idHistorico = idHistorico;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getKmOdometro() {
        return kmOdometro;
    }

    public void setKmOdometro(int kmOdometro) {
        this.kmOdometro = kmOdometro;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Long getIdAutorizada() {
        return idAutorizada;
    }

    public void setIdAutorizada(Long idAutorizada) {
        this.idAutorizada = idAutorizada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricoVeiculo that = (HistoricoVeiculo) o;
        return kmOdometro == that.kmOdometro && Objects.equals(idHistorico, that.idHistorico) && Objects.equals(tipoEvento, that.tipoEvento) && Objects.equals(dataEvento, that.dataEvento) && Objects.equals(descricao, that.descricao) && Objects.equals(idVeiculo, that.idVeiculo) && Objects.equals(idAutorizada, that.idAutorizada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHistorico, tipoEvento, dataEvento, descricao, kmOdometro, idVeiculo, idAutorizada);
    }

    @Override
    public String toString() {
        return "HistoricoVeiculo{" +
                "idHistorico=" + idHistorico +
                ", tipoEvento='" + tipoEvento + '\'' +
                ", dataEvento=" + dataEvento +
                ", descricao='" + descricao + '\'' +
                ", kmOdometro=" + kmOdometro +
                ", idVeiculo=" + idVeiculo +
                ", idAutorizada=" + idAutorizada +
                '}';
    }
}

