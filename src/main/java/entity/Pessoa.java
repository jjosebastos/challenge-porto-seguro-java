package entity;

import java.time.LocalDate;

public class Pessoa {
    private int idCliente;
    private String tipoCliente;
    private String status;

    public Pessoa(int idCliente, String tipoCliente, String status) {
        super();
        this.idCliente = idCliente;
        this.tipoCliente = tipoCliente;
        this.status = status;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "idCliente=" + idCliente +
                ", tipoCliente='" + tipoCliente + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
