package br.com.inovadevs.dtos;

import java.util.Date;

public class HistoricoVeiculoDto {
    private Long idHistorico;
    private String tipoEvento;
    private Date dataEvento;
    private String descricao;
    private int kmOdometro;
    private Long idVeiculo;
    private Long idAutorizada;

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
}
