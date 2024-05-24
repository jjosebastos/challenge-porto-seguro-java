package modelTests;

import model.Carro;
import model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarroTest {
    private Carro carroSet;
    private Cliente clienteSet;

    @BeforeEach
    void setUp(){
         carroSet = new Carro(1L, "30QLAJKLD", "FIAT", "ARGO", 27, 12500, "1FLQJW");
         carroSet.getPlaca();
         carroSet.getQuilometragem();
         carroSet.getAroRoda();
         carroSet.getChassi();
    }

    @Test
    void cadastro_orcamento_argo(){
        Assertions.assertEquals(4, carroSet.revisao(80000));
    }
}
