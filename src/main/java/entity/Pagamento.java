package entity;

import java.time.LocalDate;
import java.util.Objects;

public class Pagamento {
    private int idPagamento;
    private LocalDate dataPagamento;
    private double valor;
    private String status;
    private Integer idCliente;
    private Integer idSeguradora;
    private Integer idAutorizada;
    private String tipoPagamento;

    public Pagamento(int idPagamento, LocalDate dataPagamento, double valor, String status, Integer idCliente, Integer idSeguradora, Integer idAutorizada, String tipoPagamento) {
        this.idPagamento = idPagamento;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.status = status;
        this.idCliente = idCliente;
        this.idSeguradora = idSeguradora;
        this.idAutorizada = idAutorizada;
        this.tipoPagamento = tipoPagamento;
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
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

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdSeguradora() {
        return idSeguradora;
    }

    public void setIdSeguradora(Integer idSeguradora) {
        this.idSeguradora = idSeguradora;
    }

    public Integer getIdAutorizada() {
        return idAutorizada;
    }

    public void setIdAutorizada(Integer idAutorizada) {
        this.idAutorizada = idAutorizada;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "idPagamento=" + idPagamento +
                ", dataPagamento=" + dataPagamento +
                ", valor=" + valor +
                ", status='" + status + '\'' +
                ", idCliente=" + idCliente +
                ", idSeguradora=" + idSeguradora +
                ", idAutorizada=" + idAutorizada +
                ", tipoPagamento='" + tipoPagamento + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return idPagamento == pagamento.idPagamento && Double.compare(valor, pagamento.valor) == 0 && Objects.equals(dataPagamento, pagamento.dataPagamento) && Objects.equals(status, pagamento.status) && Objects.equals(idCliente, pagamento.idCliente) && Objects.equals(idSeguradora, pagamento.idSeguradora) && Objects.equals(idAutorizada, pagamento.idAutorizada) && Objects.equals(tipoPagamento, pagamento.tipoPagamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPagamento, dataPagamento, valor, status, idCliente, idSeguradora, idAutorizada, tipoPagamento);
    }
}