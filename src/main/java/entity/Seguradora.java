package entity;

import java.util.Objects;

public class Seguradora {
    private Long idSeguradora;
    private String nome;
    private String cnpj;
    private Long idVeiculo;

    public Seguradora(Long idSeguradora, String nome, String cnpj, Long idVeiculo) {
        this.idSeguradora = idSeguradora;
        this.nome = nome;
        this.cnpj = cnpj;
        this.idVeiculo = idVeiculo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdSeguradora() {
        return idSeguradora;
    }

    public void setIdSeguradora(Long idSeguradora) {
        this.idSeguradora = idSeguradora;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public boolean isCnpjValido(String cnpj) {
        if (cnpj.length() == 14) {
            return true;
        } else {
            throw new RuntimeException("CNPJ inválido.");
        }
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if (isCnpjValido(cnpj)) {
            this.cnpj = cnpj;
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seguradora that = (Seguradora) o;
        return Objects.equals(idSeguradora, that.idSeguradora) && Objects.equals(nome, that.nome) && Objects.equals(cnpj, that.cnpj) && Objects.equals(idVeiculo, that.idVeiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSeguradora, nome, cnpj, idVeiculo);
    }
}