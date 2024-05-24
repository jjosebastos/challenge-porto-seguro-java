package model;

public class Manutencao {
    private long idManutencao;
    private Veiculo veiculo;
    private String descricao;
    private Endereco endereco;
    private Mecanico mecanico;

    public Manutencao(long idManutencao, Veiculo veiculo, Mecanico mecanico, String descricao, Endereco endereco) {
        this.idManutencao = idManutencao;
        this.veiculo = veiculo;
        this.mecanico = mecanico;
        this.descricao = descricao;
        this.endereco = endereco;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public long getId() {
        return idManutencao;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

}
