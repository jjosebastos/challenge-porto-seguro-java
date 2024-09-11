package model;

public class Funcionario {
    private int idFuncionario;
    private String nome;
    private int rm;

    public Funcionario(int idFuncionario, String nome, int rm) {
        super();
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.rm = rm;
    }

    public int getId() {
        return idFuncionario;
    }

    public void setId(int id) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRm() {
        return rm;
    }

    public void setRm(int rm) {
        this.rm = rm;
    }


}
