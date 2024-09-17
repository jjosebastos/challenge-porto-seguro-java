package entity;

import java.util.Objects;

public class Autorizada {
    private int idAutorizada;
    private String nome;
    private String cnpj;

    public Autorizada(int idAutorizada, String nome, String cnpj) {
        this.idAutorizada = idAutorizada;
        this.nome = nome;
        setCnpj(cnpj);
    }

    public int getIdAutorizada() {
        return idAutorizada;
    }

    public void setIdAutorizada(int idAutorizada) {
        this.idAutorizada = idAutorizada;
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

    public boolean isCnpjValido(String cnpj){
        if(cnpj.length() == 14){
            return true;
        } else {
            throw new RuntimeException("CNPJ inválido.");
        }
    }

    @Override
    public String toString() {
        return "Autorizada{" +
                "idAutorizada=" + idAutorizada +
                ", nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autorizada that = (Autorizada) o;
        return idAutorizada == that.idAutorizada && Objects.equals(nome, that.nome) && Objects.equals(cnpj, that.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAutorizada, nome, cnpj);
    }
}