package dtos;

public class SeguradoraDto {
    private Long idSeguradora;
    private String nome;
    private String cnpj;
    private Long idVeiculo;

    public Long getIdSeguradora() {
        return idSeguradora;
    }

    public void setIdSeguradora(Long idSeguradora) {
        this.idSeguradora = idSeguradora;
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
        this.cnpj = cnpj;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }
}
