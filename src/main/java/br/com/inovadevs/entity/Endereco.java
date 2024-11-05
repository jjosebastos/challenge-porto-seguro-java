package br.com.inovadevs.entity;

import java.util.Objects;

public class Endereco {

    private Long idEndereco;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String complemento;
    private Long idCliente;
    private Long idSeguradora;
    private Long idAutorizada;

    public Endereco(Long idEndereco, String rua, String numero, String bairro, String cidade, String uf, String cep, String complemento, Long idCliente, Long idSeguradora, Long idAutorizada) {
        this.idEndereco = idEndereco;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        setCep(cep);
        this.complemento = complemento;
        this.idCliente = idCliente;
        this.idSeguradora = idSeguradora;
        this.idAutorizada = idAutorizada;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        if(isCepValido(cep)){
            this.cep = cep;
        } else {
            throw new RuntimeException("CEP inválido");
        }
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
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

    public boolean isCepValido(String cep){
        return cep.length() == 9;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(idEndereco, endereco.idEndereco) && Objects.equals(rua, endereco.rua) && Objects.equals(numero, endereco.numero) && Objects.equals(bairro, endereco.bairro) && Objects.equals(cidade, endereco.cidade) && Objects.equals(uf, endereco.uf) && Objects.equals(cep, endereco.cep) && Objects.equals(complemento, endereco.complemento) && Objects.equals(idCliente, endereco.idCliente) && Objects.equals(idSeguradora, endereco.idSeguradora) && Objects.equals(idAutorizada, endereco.idAutorizada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEndereco, rua, numero, bairro, cidade, uf, cep, complemento, idCliente, idSeguradora, idAutorizada);
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "idEndereco=" + idEndereco +
                ", rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", cep='" + cep + '\'' +
                ", complemento='" + complemento + '\'' +
                ", idCliente=" + idCliente +
                ", idSeguradora=" + idSeguradora +
                ", idAutorizada=" + idAutorizada +
                '}';
    }
}
