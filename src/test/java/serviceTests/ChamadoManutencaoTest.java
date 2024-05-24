package serviceTests;

import model.Endereco;
import model.Manutencao;
import model.Mecanico;
import model.Veiculo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ChamadoManutencao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChamadoManutencaoTest {
    private Veiculo veiculoSet;
    private Endereco enderecoSet;
    private Mecanico mecanicoSet;

    public ChamadoManutencao chamadoSet;

    @Test
    void adicao_de_nova_solicitacao(){
        chamadoSet.adicionarNovaSolicitacao(1l, veiculoSet, mecanicoSet, "Radiador furado", enderecoSet);
        List<Manutencao> listManutencao = new ArrayList<>((Collection) chamadoSet);
        for (Manutencao m : listManutencao){
            if (m.getVeiculo())
        }

    }
}
