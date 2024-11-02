package entity;

import dtos.OrcamentoDto;

import java.time.LocalDateTime;
import java.util.Objects;

public class Orcamento {

    private Long idOrcamento;
    private LocalDateTime dataHoraCriacao;
    private String status;
    private String diagnostico;
    private Long idVeiculo;
    private Long idTecnico;

    public Orcamento(Long idOrcamento, LocalDateTime dataHoraCriacao, String status, String diagnostico, Long idVeiculo, Long idTecnico) {
        this.idOrcamento = idOrcamento;
        this.dataHoraCriacao = dataHoraCriacao;
        this.status = status;
        this.diagnostico = diagnostico;
        this.idVeiculo = idVeiculo;
        this.idTecnico = idTecnico;
    }


    public Long getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(Long idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Long getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(Long idTecnico) {
        this.idTecnico = idTecnico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orcamento orcamento = (Orcamento) o;
        return Objects.equals(idOrcamento, orcamento.idOrcamento) && Objects.equals(dataHoraCriacao, orcamento.dataHoraCriacao) && Objects.equals(status, orcamento.status) && Objects.equals(diagnostico, orcamento.diagnostico) && Objects.equals(idVeiculo, orcamento.idVeiculo) && Objects.equals(idTecnico, orcamento.idTecnico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrcamento, dataHoraCriacao, status, diagnostico, idVeiculo, idTecnico);
    }

    @Override
    public String toString() {
        return "OrcamentoDto{" +
                "idOrcamento=" + idOrcamento +
                ", dataHoraCriacao=" + dataHoraCriacao +
                ", status='" + status + '\'' +
                ", diagnostico='" + diagnostico + '\'' +
                ", idVeiculo=" + idVeiculo +
                ", idTecnico=" + idTecnico +
                '}';
    }
}
