package modelTests;

import model.Guincho;
import model.Veiculo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GuinchoTest {

    private Guincho guinchoSet;
    private Veiculo veiculoSet = new Veiculo(1l, "E1029301", "MERCEDES", "CAMINHAO");
    @BeforeEach
    public void setUp(){
        guinchoSet = new Guincho(1l, "LUCIO", 30193, veiculoSet, 11309, true);
        guinchoSet.getId();
        guinchoSet.getNome();
        guinchoSet.getStatus();
        guinchoSet.getLocalizacao();
        guinchoSet.getVeiculo();
    }

    @Test
    void verificar_status_do_guincho(){
        Assertions.assertEquals(true, guinchoSet.getStatus());
    }
}
