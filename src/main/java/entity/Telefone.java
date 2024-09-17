package entity;

import java.util.Objects;

public class Telefone {

    private int idTelefone;
    private String numero;
    private String ddd;
    private String tipoTelefone;
    private Integer idSeguradora;
    private Integer idAutorizada;
    private Integer idCliente;

    public Telefone(int idTelefone, String numero, String ddd, String tipoTelefone, Integer idSeguradora, Integer idAutorizada, Integer idCliente) {
        this.idTelefone = idTelefone;
        setNumero(numero);
        this.ddd = ddd;
        this.tipoTelefone = tipoTelefone;
        this.idSeguradora = idSeguradora;
        this.idAutorizada = idAutorizada;
        this.idCliente = idCliente;
    }

    public int getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(int idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if(isNumeroTelefoneValido(numero)){
            this.numero = numero;
        }
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
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

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public boolean isNumeroTelefoneValido(String numero){
        if(numero.length() >= 8 && numero.length() <=9){
            return true;
        }
        throw new RuntimeException("Telefone inválido.");


    }


    @Override
    public String toString() {
        return "Telefone{" +
                "idTelefone=" + idTelefone +
                ", numero='" + numero + '\'' +
                ", ddd='" + ddd + '\'' +
                ", tipoTelefone='" + tipoTelefone + '\'' +
                ", idSeguradora=" + idSeguradora +
                ", idAutorizada=" + idAutorizada +
                ", idCliente=" + idCliente +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone = (Telefone) o;
        return idTelefone == telefone.idTelefone && Objects.equals(numero, telefone.numero) && Objects.equals(ddd, telefone.ddd) && Objects.equals(tipoTelefone, telefone.tipoTelefone) && Objects.equals(idSeguradora, telefone.idSeguradora) && Objects.equals(idAutorizada, telefone.idAutorizada) && Objects.equals(idCliente, telefone.idCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTelefone, numero, ddd, tipoTelefone, idSeguradora, idAutorizada, idCliente);
    }
}
