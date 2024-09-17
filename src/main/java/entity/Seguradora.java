package entity;

import java.util.Objects;

public class Seguradora {
    private int idSeguradora;
    private String nome;
    private String cnpj;
    private int idVeiculo;

    public Seguradora(int idSeguradora, String nome, String cnpj, int idVeiculo) {
        this.idSeguradora = idSeguradora;
        this.nome = nome;
        setCnpj(cnpj);
        this.idVeiculo = idVeiculo;
    }

    public boolean isCnpjValido(String cnpj){
        if(cnpj.length() == 14){
            return true;
        } else {
            throw new RuntimeException("CNPJ inválido.");
        }
    }

    public int getIdSeguradora() {
        return idSeguradora;
    }

    public void setIdSeguradora(int idSeguradora) {
        this.idSeguradora = idSeguradora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if(isCnpjValido(cnpj)){
            this.cnpj = cnpj;
        }
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }
    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    @Override
    public String toString() {
        return "Seguradora{" +
                "idSeguradora=" + idSeguradora +
                ", nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", idVeiculo=" + idVeiculo +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seguradora that = (Seguradora) o;
        return idSeguradora == that.idSeguradora && idVeiculo == that.idVeiculo && Objects.equals(nome, that.nome) && Objects.equals(cnpj, that.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSeguradora, nome, cnpj, idVeiculo);
    }
}
