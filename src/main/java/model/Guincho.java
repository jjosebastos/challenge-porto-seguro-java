package model;

public class Guincho extends Funcionario{

    private Veiculo veiculo;
    private double localizacao;
    private Boolean status;

    public Guincho(int idFuncionario, String nome, int rm, Veiculo veiculo, double localizacao, boolean status) {
        super(idFuncionario, nome, rm);
        this.veiculo = veiculo;
        this.localizacao = localizacao;
        this.status = status;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public double getLocalizacao() {
        return localizacao;
    }

    public Boolean getStatus() {
        return status;
    }

}
