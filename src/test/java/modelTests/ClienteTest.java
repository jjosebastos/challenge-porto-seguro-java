package modelTests;

import model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {
    private Cliente clienteSet;
    @BeforeEach
    public void setUp(){
        clienteSet = new Cliente(1L, "exemplo@example.com","000.111.222-33", "2000-08-10");
    }
    @Test
    void calculo_de_idade_pessoa(){
        clienteSet.getId();
        clienteSet.getEmail();
        clienteSet.getCpf();
        clienteSet.getDataNascimento();
        Assertions.assertEquals(23, clienteSet.calculoIdade("2000-08-10"));
    }

}
