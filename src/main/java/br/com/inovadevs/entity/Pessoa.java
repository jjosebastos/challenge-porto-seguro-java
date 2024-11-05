package br.com.inovadevs.entity;

public class Pessoa {
    private Long idPessoa;
    private String tipoCliente;
    private String status;

    public Pessoa(Long idPessoa, String tipoCliente, String status) {
        super();
        this.idPessoa = idPessoa;
        this.tipoCliente = tipoCliente;
        this.status = status;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "idCliente=" + idPessoa +
                ", tipoCliente='" + tipoCliente + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
