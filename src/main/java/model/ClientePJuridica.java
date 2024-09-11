package model;

import java.util.Objects;

public class ClientePJuridica extends Pessoa{

    private  String razaoSocial;
    private String cnpj;
    private String inscricaoEstadual;

    public ClientePJuridica(String nome, String razaoSocial, String cnpj, String inscricaoEstadual) {
        super(nome);
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientePJuridica that = (ClientePJuridica) o;
        return Objects.equals(razaoSocial, that.razaoSocial) && Objects.equals(cnpj, that.cnpj) && Objects.equals(inscricaoEstadual, that.inscricaoEstadual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(razaoSocial, cnpj, inscricaoEstadual);
    }

    @Override
    public String toString() {
        return "ClientePJuridica{" +
                "razaoSocial='" + razaoSocial + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", inscricaoEstadual='" + inscricaoEstadual + '\'' +
                '}';
    }
}
