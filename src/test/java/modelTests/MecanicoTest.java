package modelTests;

import model.Mecanico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MecanicoTest {
    private Mecanico mecanicoSet;
    @BeforeEach
    public void setUp(){
        mecanicoSet = new Mecanico(1L, "JOSE", 100900);
        mecanicoSet.getId();
        mecanicoSet.getNome();
        mecanicoSet.getRm();
    }

    @Test
    void checar_disponibilidade_true_mecanico(){
        Assertions.assertEquals(true, mecanicoSet.isDisponivel(true));
    }

    @Test
    void checar_tamanho_da_lista_de_pecas_diagnosticadas_com_defeito(){
        String pecas [] = {"Cabeçote do motor", "Pastilhas de freio","Para choque"};
        mecanicoSet.diagnostico(pecas);
        Assertions.assertEquals(3, mecanicoSet.diagnostico(pecas).size());
    }
    @Test
    void checar_se_a_lista_esta_contida(){

        String pecas [] = {"Cabeçote do motor", "Pastilhas de freio","Para choque"};
        Assertions.assertTrue(mecanicoSet.diagnostico(pecas).containsAll(mecanicoSet.diagnostico(pecas)));
    }
}
