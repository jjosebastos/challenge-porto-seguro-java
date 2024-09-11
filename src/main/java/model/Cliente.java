package model;

import java.time.LocalDate;
import java.time.Period;

public class Cliente extends Pessoa{
    private int idCliente;
    private String email;
    private String cpf;

    public Cliente(String nome, String dataNascimento, int idCliente, String email, String cpf) {
        super(nome, dataNascimento);
        this.idCliente = idCliente;
        this.email = email;
        this.cpf = cpf;

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






}