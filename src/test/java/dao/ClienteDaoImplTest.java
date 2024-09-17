package dao;

import config.DatabaseConfig;
import entity.Cliente;
import exception.ClienteDaoException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteDaoImplTest {

    private Connection connection;
    private ClienteDao clienteDao;

    @BeforeEach
    public void setUp() throws SQLException {
        // Configuração do banco de dados em memória H2
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
        DatabaseConfig dbConfig = new DatabaseConfig() {
            @Override
            public Connection getConnection() throws SQLException {
                return connection;
            }
        };
        clienteDao = new ClienteDaoImpl(dbConfig);

        // Criação da tabela Cliente
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE CLIENTE (" +
                    "ID_CLIENTE NUMBER(38,0) PRIMARY KEY, " +
                    "NM_CLIENTE_COMPLETO VARCHAR2(50 BYTE), " +
                    "DT_NASCIMENTO DATE, " +
                    "NR_CPF VARCHAR2(11 BYTE), " +
                    "DS_GENERO VARCHAR2(15 BYTE))");
        }
    }

    @AfterEach
    public void tearDown() throws SQLException {
        // Limpeza do banco de dados
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE CLIENTE");
        }
        connection.close();
    }

    @Test
    public void testCreate() throws SQLException, ClienteDaoException {
        Cliente cliente = new Cliente(1, "João da Silva", LocalDate.of(1985, 5, 15), "12345678901", "Masculino");

        clienteDao.create(cliente);

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM CLIENTE WHERE ID_CLIENTE = 1")) {
            assertTrue(rs.next());
            assertEquals(1, rs.getInt("ID_CLIENTE"));
            assertEquals("João da Silva", rs.getString("NM_CLIENTE_COMPLETO"));
            assertEquals(Date.valueOf(LocalDate.of(1985, 5, 15)), rs.getDate("DT_NASCIMENTO"));
            assertEquals("12345678901", rs.getString("NR_CPF"));
            assertEquals("Masculino", rs.getString("DS_GENERO"));
        }
    }

    @Test
    public void testReadAll() throws SQLException, ClienteDaoException {
        // Inserindo dados de teste
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("INSERT INTO CLIENTE (ID_CLIENTE, NM_CLIENTE_COMPLETO, DT_NASCIMENTO, NR_CPF, DS_GENERO) " +
                    "VALUES (1, 'João da Silva', '1985-05-15', '12345678901', 'Masculino')");
            stmt.execute("INSERT INTO CLIENTE (ID_CLIENTE, NM_CLIENTE_COMPLETO, DT_NASCIMENTO, NR_CPF, DS_GENERO) " +
                    "VALUES (2, 'Maria Oliveira', '1990-12-01', '09876543210', 'Feminino')");
        }

        List<Cliente> clientes = clienteDao.readAll();

        assertEquals(2, clientes.size());

        Cliente cliente1 = clientes.get(0);
        assertEquals(1, cliente1.getIdCliente());
        assertEquals("João da Silva", cliente1.getNome());
        assertEquals(LocalDate.of(1985, 5, 15), cliente1.getDataNascimento());
        assertEquals("12345678901", cliente1.getCpf());
        assertEquals("Masculino", cliente1.getDsGenero());

        Cliente cliente2 = clientes.get(1);
        assertEquals(2, cliente2.getIdCliente());
        assertEquals("Maria Oliveira", cliente2.getNome());
        assertEquals(LocalDate.of(1990, 12, 1), cliente2.getDataNascimento());
        assertEquals("09876543210", cliente2.getCpf());
        assertEquals("Feminino", cliente2.getDsGenero());
    }

    @Test
    public void testUpdate() throws SQLException, ClienteDaoException {
        // Inserindo dados de teste
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("INSERT INTO CLIENTE (ID_CLIENTE, NM_CLIENTE_COMPLETO, DT_NASCIMENTO, NR_CPF, DS_GENERO) " +
                    "VALUES (1, 'João da Silva', '1985-05-15', '12345678901', 'Masculino')");
        }

        Cliente cliente = new Cliente(1, "João da Silva Atualizado", LocalDate.of(1985, 5, 15), "12345678901", "Masculino");
        clienteDao.update(cliente);

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM CLIENTE WHERE ID_CLIENTE = 1")) {
            assertTrue(rs.next());
            assertEquals("João da Silva Atualizado", rs.getString("NM_CLIENTE_COMPLETO"));
        }
    }

    @Test
    public void testDelete() throws SQLException, ClienteDaoException {
        // Inserindo dados de teste
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("INSERT INTO CLIENTE (ID_CLIENTE, NM_CLIENTE_COMPLETO, DT_NASCIMENTO, NR_CPF, DS_GENERO) " +
                    "VALUES (1, 'João da Silva', '1985-05-15', '12345678901', 'Masculino')");
        }

        clienteDao.delete(1);

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM CLIENTE WHERE ID_CLIENTE = 1")) {
            assertFalse(rs.next());
        }
    }
}
