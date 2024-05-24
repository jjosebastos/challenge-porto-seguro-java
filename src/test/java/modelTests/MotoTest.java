package modelTests;

import model.Moto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MotoTest {
    private Moto motoSet;

    @BeforeEach
    public void setUp(){
        motoSet = new Moto(1L, "1LQO0XW-2OQ", "KAWASAKI", "VULCAN", 13, 800, 15000);

        motoSet.getChassi();
        motoSet.getMarca();
        motoSet.getModelo();
        motoSet.getIdVeiculo();
        motoSet.getCilindradas();
        motoSet.getAroRoda();
        motoSet.getQuilometragem();
    }

    @Test
    void teste_set_aro_roda(){
        motoSet.setAroRoda(21);
        Assertions.assertEquals(21, motoSet.getAroRoda());
    }

    @Test
    void teste_set_quilometragem(){
        motoSet.setQuilometragem(100000);
        Assertions.assertEquals(3, motoSet.revisao());
    }

    @Test
    void teste_set_cilindradas(){
        motoSet.setCilindradas(1000);
        Assertions.assertEquals(1000, motoSet.getCilindradas());
    }
}
