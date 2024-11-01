package entity;

import java.util.Objects;

public class Telefone {

    private Long idTelefone;
    private String numero;
    private String ddd;
    private String tipoTelefone;
    private Long idSeguradora;
    private Long idAutorizada;
    private Long idCliente;

    public Telefone(Long idTelefone, String numero, String ddd, String tipoTelefone, Long idSeguradora, Long idAutorizada, Long idCliente) {
        this.idTelefone = idTelefone;
        setNumero(numero);
        this.ddd = ddd;
        this.tipoTelefone = tipoTelefone;
        this.idSeguradora = idSeguradora;
        this.idAutorizada = idAutorizada;
        this.idCliente = idCliente;
    }

    public Long getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(Long idTelefone) {
        this.idTelefone = idTelefone;
    }

    public void setNumero(String numero) {
        if(isNumeroTelefoneValido(numero)){
            this.numero = numero;
        }
    }

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
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

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNumero() {
        return numero;
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
