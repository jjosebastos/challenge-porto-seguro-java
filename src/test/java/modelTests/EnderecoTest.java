package modelTests;

import model.Endereco;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnderecoTest {
    private Endereco enderecoSet;
    @BeforeEach
    public void setUp(){
        enderecoSet = new Endereco(1L, "Rua das flores","Jd. das flores", "São Paulo", "SP", "08430-170");
        enderecoSet.getIdEndereco();
        enderecoSet.getRua();
        enderecoSet.getBairro();
        enderecoSet.getCidade();
        enderecoSet.getUf();
        enderecoSet.getCep();
    }
    @Test
    void consultar_id_endereco_1l(){
        Assertions.assertEquals(enderecoSet.getIdEndereco(), 1L);

    }

}
