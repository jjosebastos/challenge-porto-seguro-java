package entity;

import java.util.Objects;

public class Endereco {

    private int idEndereco;
    private String rua;
    private String numeroCasa;
    private String bairro;
    private String cidade;
    private String uf;
    private String complemento;
    private Integer idCliente;
    private Integer idSeguradora;
    private Integer idAutorizada;
    private String cep;

    public Endereco(int idEndereco, String rua, String numeroCasa, String bairro, String cidade, String uf, String complemento, Integer idCliente, Integer idSeguradora, Integer idAutorizada, String cep) {
        this.idEndereco = idEndereco;
        this.rua = rua;
        this.numeroCasa = numeroCasa;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.complemento = complemento;
        this.idCliente = idCliente;
        this.idSeguradora = idSeguradora;
        this.idAutorizada = idAutorizada;
        setCep(cep);
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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

    public boolean isCepValido(String cep){
        return cep.length() == 9;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "idEndereco=" + idEndereco +
                ", rua='" + rua + '\'' +
                ", numeroCasa='" + numeroCasa + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", complemento='" + complemento + '\'' +
                ", idCliente=" + idCliente +
                ", idSeguradora=" + idSeguradora +
                ", idAutorizada=" + idAutorizada +
                ", cep='" + cep + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return idEndereco == endereco.idEndereco && Objects.equals(rua, endereco.rua) && Objects.equals(numeroCasa, endereco.numeroCasa) && Objects.equals(bairro, endereco.bairro) && Objects.equals(cidade, endereco.cidade) && Objects.equals(uf, endereco.uf) && Objects.equals(complemento, endereco.complemento) && Objects.equals(idCliente, endereco.idCliente) && Objects.equals(idSeguradora, endereco.idSeguradora) && Objects.equals(idAutorizada, endereco.idAutorizada) && Objects.equals(cep, endereco.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEndereco, rua, numeroCasa, bairro, cidade, uf, complemento, idCliente, idSeguradora, idAutorizada, cep);
    }
}
