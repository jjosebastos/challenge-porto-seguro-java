package model;

public class Endereco {

    private long idEndereco;
    private String rua;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco(long idEndereco, String rua, String bairro, String cidade, String uf, String cep) {
        this.idEndereco = idEndereco;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }

    public long getIdEndereco() {
        return idEndereco;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public String getCep() {
        return cep;
    }

}
