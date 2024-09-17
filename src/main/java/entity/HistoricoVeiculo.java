package entity;

import java.time.LocalDate;
import java.util.Objects;

public class HistoricoVeiculo {

    private int idHistorico;
    private LocalDate dataAquisicao;
    private LocalDate dataVenda;
    private Integer idCliente;
    private Integer idVeiculo;

    public HistoricoVeiculo(int idHistorico, LocalDate dataAquisicao, LocalDate dataVenda, Integer idCliente, Integer idVeiculo) {
        this.idHistorico = idHistorico;
        this.dataAquisicao = dataAquisicao;
        this.dataVenda = dataVenda;
        this.idCliente = idCliente;
        this.idVeiculo = idVeiculo;
    }

    public int getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(int idHistorico) {
        this.idHistorico = idHistorico;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricoVeiculo that = (HistoricoVeiculo) o;
        return idHistorico == that.idHistorico && Objects.equals(dataAquisicao, that.dataAquisicao) && Objects.equals(dataVenda, that.dataVenda) && Objects.equals(idCliente, that.idCliente) && Objects.equals(idVeiculo, that.idVeiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHistorico, dataAquisicao, dataVenda, idCliente, idVeiculo);
    }

    @Override
    public String toString() {
        return "HistoricoVeiculo{" +
                "idHistorico=" + idHistorico +
                ", dataAquisicao=" + dataAquisicao +
                ", dataVenda=" + dataVenda +
                ", idCliente=" + idCliente +
                ", idVeiculo=" + idVeiculo +
                '}';
    }
}
