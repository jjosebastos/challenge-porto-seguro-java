package entity;

import java.time.LocalDate;
import java.util.Objects;

public class Pagamento {
    private Long idPagamento;
    private String tipoPagamento;
    private LocalDate data;
    private double valor;
    private String status;
    private String descricao;
    private Long idPessoa;
    private Long idSeguradora;
    private Long idAutorizada;

    public Pagamento(Long idPagamento, String tipoPagamento, LocalDate data, double valor, String status, String descricao, Long idPessoa, Long idSeguradora, Long idAutorizada) {
        this.idPagamento = idPagamento;
        this.tipoPagamento = tipoPagamento;
        this.data = data;
        this.valor = valor;
        this.status = status;
        this.descricao = descricao;
        this.idPessoa = idPessoa;
        this.idSeguradora = idSeguradora;
        this.idAutorizada = idAutorizada;
    }

    public Long getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Long getIdSeguradora() {
        return idSeguradora;
    }

    public void setIdSeguradora(Long idSeguradora) {
        this.idSeguradora = idSeguradora;
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
        Pagamento pagamento = (Pagamento) o;
        return Double.compare(valor, pagamento.valor) == 0 && Objects.equals(idPagamento, pagamento.idPagamento) && Objects.equals(tipoPagamento, pagamento.tipoPagamento) && Objects.equals(data, pagamento.data) && Objects.equals(status, pagamento.status) && Objects.equals(descricao, pagamento.descricao) && Objects.equals(idPessoa, pagamento.idPessoa) && Objects.equals(idSeguradora, pagamento.idSeguradora) && Objects.equals(idAutorizada, pagamento.idAutorizada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPagamento, tipoPagamento, data, valor, status, descricao, idPessoa, idSeguradora, idAutorizada);
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "idPagamento=" + idPagamento +
                ", tipoPagamento='" + tipoPagamento + '\'' +
                ", data=" + data +
                ", valor=" + valor +
                ", status='" + status + '\'' +
                ", descricao='" + descricao + '\'' +
                ", idPessoa=" + idPessoa +
                ", idSeguradora=" + idSeguradora +
                ", idAutorizada=" + idAutorizada +
                '}';
    }
}