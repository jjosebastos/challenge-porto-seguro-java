package br.com.inovadevs.entity;

import java.util.Objects;

public class Telefone {

    private Long idTelefone;
    private String numero;
    private String ddd;
    private String tipoTelefone;
    private Long idPessoa;
    private Long idSeguradora;
    private Long idAutorizada;

    public Telefone(Long idTelefone, String numero, String ddd, String tipoTelefone, Long idPessoa, Long idSeguradora, Long idAutorizada) {
        this.idTelefone = idTelefone;
        this.numero = numero;
        this.ddd = ddd;
        this.tipoTelefone = tipoTelefone;
        this.idPessoa = idPessoa;
        this.idSeguradora = idSeguradora;
        this.idAutorizada = idAutorizada;
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


    public String getNumero() {
        return numero;
    }

    public boolean isNumeroTelefoneValido(String numero){
        return numero.length() == 8;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone = (Telefone) o;
        return Objects.equals(idTelefone, telefone.idTelefone) && Objects.equals(numero, telefone.numero) && Objects.equals(ddd, telefone.ddd) && Objects.equals(tipoTelefone, telefone.tipoTelefone) && Objects.equals(idPessoa, telefone.idPessoa) && Objects.equals(idSeguradora, telefone.idSeguradora) && Objects.equals(idAutorizada, telefone.idAutorizada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTelefone, numero, ddd, tipoTelefone, idPessoa, idSeguradora, idAutorizada);
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "idTelefone=" + idTelefone +
                ", numero='" + numero + '\'' +
                ", ddd='" + ddd + '\'' +
                ", tipoTelefone='" + tipoTelefone + '\'' +
                ", idPessoa=" + idPessoa +
                ", idSeguradora=" + idSeguradora +
                ", idAutorizada=" + idAutorizada +
                '}';
    }
}
