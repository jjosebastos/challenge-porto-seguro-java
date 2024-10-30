package entity;

import java.util.InvalidPropertiesFormatException;
import java.util.Objects;

public class PessoaJuridica extends Pessoa{
    private  String razaoSocial;
    private String cnpj;
    private String nomeFantasia;

    public PessoaJuridica(Long idPessoa, String tipoCliente, String status, String cnpj, String razaoSocial, String nomeFantasia) {
        super(idPessoa, tipoCliente, status);
        this.razaoSocial = razaoSocial;
        setCnpj(cnpj);
        this.nomeFantasia = nomeFantasia;
    }

    public boolean isCnpjValido(String cnpj){
        return cnpj.length() == 18;
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
        if(isCnpjValido(cnpj)){
            this.cnpj = cnpj;
        } else {
            throw new RuntimeException();
        }

    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    @Override
    public String toString() {
        return "PessoaJuridica{" +
                "razaoSocial='" + razaoSocial + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PessoaJuridica that = (PessoaJuridica) o;
        return Objects.equals(razaoSocial, that.razaoSocial) && Objects.equals(cnpj, that.cnpj) && Objects.equals(nomeFantasia, that.nomeFantasia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(razaoSocial, cnpj, nomeFantasia);
    }
}
