package entity;

import java.time.LocalDate;

public class Cliente {
    private int idCliente;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String dsGenero;

    public Cliente(int idCliente, String nome, LocalDate dataNascimento, String cpf, String dsGenero) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.dsGenero = dsGenero;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDsGenero() {
        return dsGenero;
    }

    public void setDsGenero(String dsGenero) {
        this.dsGenero = dsGenero;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", cpf='" + cpf + '\'' +
                ", dsGenero='" + dsGenero + '\'' +
                '}';
    }
}
