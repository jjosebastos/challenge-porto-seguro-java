package dao;

import config.DatabaseConfig;
import entity.Endereco;
import exception.EnderecoDaoException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EnderecoDaoImplTest {

    private Connection connection;
    private EnderecoDao enderecoDao;

    @BeforeEach
    public void setUp() throws SQLException {
        // Configuração do banco de dados Oracle
        connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "rm557525", "121105");
        DatabaseConfig dbConfig = new DatabaseConfig() {
            @Override
            public Connection getConnection() throws SQLException {
                return connection;
            }
        };
        enderecoDao = new EnderecoDaoImpl(dbConfig);

        // Criação da tabela Endereco
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE ENDERECO (" +
                    "D_ENDERECO NUMBER(38) PRIMARY KEY, " +
                    "NM_RUA VARCHAR2(25) NOT NULL, " +
                    "NR_ENDERECO VARCHAR2(5), " +
                    "NM_BAIRRO VARCHAR2(20) NOT NULL, " +
                    "NM_CIDADE VARCHAR2(15) NOT NULL, " +
                    "SG_UNIDADE_FEDERATIVA CHAR(2) NOT NULL, " +
                    "DS_COMPLEMENTO VARCHAR2(40), " +
                    "ID_CLIENTE NUMBER(38), " +
                    "ID_SEGURADORA NUMBER(38), " +
                    "ID_AUTORIZADA NUMBER(38), " +
                    "CEP VARCHAR2(9))");
        }
    }

    @AfterEach
    public void tearDown() throws SQLException {
        // Limpeza do banco de dados
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE ENDERECO");
        }
        connection.close();
    }

    @Test
    public void testCreate() throws SQLException, EnderecoDaoException {
        Endereco endereco = new Endereco(1, "Rua A", "123", "Bairro B", "Cidade C", "SP", "Complemento", 1, 2, 3, "12345678");

        enderecoDao.create(endereco);

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM ENDERECO WHERE D_ENDERECO = 1")) {
            assertTrue(rs.next());
            assertEquals(1, rs.getInt("D_ENDERECO"));
            assertEquals("Rua A", rs.getString("NM_RUA"));
            assertEquals("123", rs.getString("NR_ENDERECO"));
            assertEquals("Bairro B", rs.getString("NM_BAIRRO"));
            assertEquals("Cidade C", rs.getString("NM_CIDADE"));
            assertEquals("SP", rs.getString("SG_UNIDADE_FEDERATIVA"));
            assertEquals("Complemento", rs.getString("DS_COMPLEMENTO"));
            assertEquals(1, rs.getInt("ID_CLIENTE"));
            assertEquals(2, rs.getInt("ID_SEGURADORA"));
            assertEquals(3, rs.getInt("ID_AUTORIZADA"));
            assertEquals("12345678", rs.getString("CEP"));
        }
    }

    @Test
    public void testReadAll() throws SQLException, EnderecoDaoException {
        // Inserindo dados de teste
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("INSERT INTO ENDERECO (D_ENDERECO, NM_RUA, NR_ENDERECO, NM_BAIRRO, NM_CIDADE, SG_UNIDADE_FEDERATIVA, DS_COMPLEMENTO, ");
