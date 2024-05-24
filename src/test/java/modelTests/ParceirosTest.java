package modelTests;

import model.Parceiros;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParceirosTest {
    private Parceiros parceirosSet;

    @BeforeEach
    public void setUp(){
        parceirosSet = new Parceiros(1L, "54.954.001/0001-94", 1);
        parceirosSet.getIdParceiro();
        parceirosSet.getTipoParceiro();
        parceirosSet.getCnpj();
        parceirosSet.setIdParceiro(1);
    }

    @Test
    void tipo_parceiro(){
        parceirosSet.setTipoParceiro(2);
        Assertions.assertEquals(2, parceirosSet.getTipoParceiro());
    }

    @Test
    void definir_um_cnpj_54954001000194(){
        parceirosSet.setCnpj("54954001000194");
        Assertions.assertEquals("54954001000194", parceirosSet.getCnpj());
    }
}
