package model;

import java.time.LocalDate;
import java.time.Period;

public class Cliente {
    private long idCliente;
    private String email;
    private String cpf;
    private String dataNascimento;

    public Cliente(long idCliente, String email, String cpf, String dataNascimento) {
        this.idCliente = idCliente;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public long getId() {
        return idCliente;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public int calculoIdade(String dataNascimento){
        LocalDate dataNasc = LocalDate.parse(dataNascimento);
        LocalDate dataAtual = LocalDate.now();

        Period periodo = Period.between(dataNasc, dataAtual);
        return periodo.getYears();
    }
}