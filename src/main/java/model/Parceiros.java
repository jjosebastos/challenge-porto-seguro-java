package model;

public class Parceiros {
    private long idParceiro;
    private String cnpj;
    private int tipoParceiro;

    public Parceiros(long idParceiro, String cnpj, int tipoParceiro) {
        this.idParceiro = idParceiro;
        this.cnpj = cnpj;
        this.tipoParceiro = tipoParceiro;
    }

    public long getIdParceiro() {
        return idParceiro;
    }

    public void setIdParceiro(long idParceiro) {
        this.idParceiro = idParceiro;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getTipoParceiro() {
        return tipoParceiro;
    }

    public void setTipoParceiro(int tipoParceiro) {
        this.tipoParceiro = tipoParceiro;
    }
}
