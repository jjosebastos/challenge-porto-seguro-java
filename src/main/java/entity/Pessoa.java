package entity;

public class Pessoa {
    private long id;
    private String nome;

    public Pessoa(){}

    public Pessoa(long id, String nome) {
        super();
        this.id = id;
        this.nome = nome;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
