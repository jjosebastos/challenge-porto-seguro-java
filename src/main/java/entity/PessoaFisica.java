package entity;

import java.util.Objects;

public class PessoaFisica extends Pessoa{

    private int idade;
    private String cpf;
    private String email;

    public PessoaFisica(int idCliente, String tipoCliente, String status, int idade, String email, String cpf) {
        super(idCliente, tipoCliente, status);
        this.email = email;
        setIdade(idade);
        this.cpf = cpf;

    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (isMaiorIdade(idade)){
            this.idade = idade;
        } else {
            throw new RuntimeException("Menor de idade.");
        }
    }

    public boolean isMaiorIdade(int idade){
        this.idade = idade;
        return  idade >= 18;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PessoaFisica that = (PessoaFisica) o;
        return idade == that.idade && Objects.equals(cpf, that.cpf) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idade, cpf, email);
    }

    @Override
    public String toString() {
        return "ClientePFisica{" +
                "idade=" + idade +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}