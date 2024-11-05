package br.com.inovadevs.dtos;

public class PessoaJuridicaDto{

    private Long idPessoa;
    private String tipo;
    private String status;
    private Long idPessoaJuridica;
    private String razaoSocial;
    private String cnpj;
    private String nomeFantasia;

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdPessoaJuridica() {
        return idPessoaJuridica;
    }

    public void setIdPessoaJuridica(Long idPessoaJuridica) {
        this.idPessoaJuridica = idPessoaJuridica;
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

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
}
