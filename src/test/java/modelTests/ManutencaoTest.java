package modelTests;

import model.Endereco;
import model.Manutencao;
import model.Mecanico;
import model.Veiculo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ManutencaoTest {
    private Manutencao manutencaoSet;
    private Veiculo veiculoSet = new Veiculo(1,"QVL013PIQ09", "FIAT", "ARGO", "BRA1090", 23050);
    private Mecanico mecanicoSet = new Mecanico(1, "JOSE", 123049);
    private Endereco enderecoSet = new Endereco(1, "Rua das flores","Jd. das flores", "São Paulo", "SP", "08430-170");
    @BeforeEach
    public void setUp(){
        manutencaoSet = new Manutencao(1, veiculoSet, mecanicoSet, "Motor fundiu", enderecoSet );
        manutencaoSet.getId();
        manutencaoSet.getVeiculo();
        manutencaoSet.getMecanico();
        manutencaoSet.getDescricao();
        manutencaoSet.getEndereco();
    }

    @Test
    void verificacao_do_veiculo_e_mecanico(){
        Assertions.assertEquals(manutencaoSet.getVeiculo(), veiculoSet);
        Assertions.assertEquals(manutencaoSet.getMecanico(), mecanicoSet);
    }

}
