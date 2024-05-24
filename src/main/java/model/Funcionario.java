package model;

public class Funcionario {
    private long idFuncionario;
    private String nome;
    private int rm;

    public Funcionario(long idFuncionario, String nome, int rm) {
        super();
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.rm = rm;
    }


    public long getId() {
        return idFuncionario;
    }

    public void setId(long id) {
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
