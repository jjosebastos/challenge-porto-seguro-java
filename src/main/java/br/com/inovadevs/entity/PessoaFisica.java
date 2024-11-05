package br.com.inovadevs.entity;

import java.util.Date;
import java.util.Objects;

public class PessoaFisica extends Pessoa{
    private Long idPessoaFisica;
    private String nome;
    private Date dataNascimento;
    private String cpf;
    private String genero;

    public PessoaFisica(Long idPessoa, String tipoCliente, String status, Long idPessoaFisica, String nome, Date dataNascimento, String cpf, String genero) {
        super(idPessoa, tipoCliente, status);
        this.idPessoaFisica = idPessoaFisica;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.genero = genero;
    }



    public Long getIdPessoaFisica() {
        return idPessoaFisica;
    }

    public void setIdPessoaFisica(Long idPessoaFisica) {
        this.idPessoaFisica = idPessoaFisica;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PessoaFisica that = (PessoaFisica) o;
        return Objects.equals(idPessoaFisica, that.idPessoaFisica) && Objects.equals(nome, that.nome) && Objects.equals(dataNascimento, that.dataNascimento) && Objects.equals(cpf, that.cpf) && Objects.equals(genero, that.genero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPessoaFisica, nome, dataNascimento, cpf, genero);
    }

    @Override
    public String toString() {
        return "PessoaFisica{" +
                "idPessoaFisica=" + idPessoaFisica +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", cpf='" + cpf + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }
}