package dao;

import model.ClientePFisica;

import java.sql.*;
import java.util.List;

public class ClientePFisicaDAOImpl implements ClientePFisicaDAO {

    private final Connection connection;

    public ClientePFisicaDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public void create(ClientePFisica pessoaFisica) throws SQLException {
        // SQL para inserir em T_CON_PESSOA (inserindo o nome e usando chave primária auto increment)
        String sqlPessoa = "INSERT INTO T_CON_PESSOA (nm_pessoa) VALUES(?)";

        // SQL para inserir em T_CON_PESSOA_FISICA (usando o ID gerado de T_CON_PESSOA)
        String sqlPessoaFisica = "INSERT INTO T_CON_PESSOA_FISICA (vl_idade, nr_cpf, id_email) VALUES (?, ?, ?)";

        // Insere o nome na tabela T_CON_PESSOA
        PreparedStatement stmtPessoa = connection.prepareStatement(sqlPessoa);
        stmtPessoa.setString(1, pessoaFisica.getNome());
        stmtPessoa.executeUpdate();

        PreparedStatement stmtPessoaF = connection.prepareStatement(sqlPessoaFisica);
        stmtPessoaF.setInt(1, pessoaFisica.getIdade());
        stmtPessoaF.setString(2, pessoaFisica.getCpf());
        stmtPessoaF.setString(3, pessoaFisica.getEmail());
        stmtPessoaF.executeUpdate();

    }

    @Override
    public List<ClientePFisica> readAll() throws SQLException {
        return List.of();
    }

    @Override
    public void update(ClientePFisica pessoaFisica) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
