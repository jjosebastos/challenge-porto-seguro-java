package dao;

import config.DatabaseConfig;
import entity.Cliente;
import exception.ClienteDaoException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoImpl implements ClienteDao {
    private final DatabaseConfig db;

    public ClienteDaoImpl(DatabaseConfig db) {
        this.db = db;
    }

    @Override
    public void create(Cliente cliente) throws ClienteDaoException {
        String sql = "INSERT INTO T_CON_CLIENTE (ID_CLIENTE, NM_CLIENTE_COMPLETO, DT_NASCIMENTO, NR_CPF, DS_GENERO) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);

            connection.setAutoCommit(false);

            pstmt.setInt(1, cliente.getIdCliente());
            pstmt.setString(2, cliente.getNome());
            pstmt.setDate(3, Date.valueOf(cliente.getDataNascimento()));
            pstmt.setString(4, cliente.getCpf());
            pstmt.setString(5, cliente.getDsGenero());

            pstmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new ClienteDaoException("Erro ao inserir cliente");
        }
    }

    @Override
    public List<Cliente> readAll() throws ClienteDaoException {
        List<Cliente> result = new ArrayList<>();
        String sql = "SELECT * FROM T_CON_CLIENTE";

        try (Connection connection = db.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int idCliente = rs.getInt("ID_CLIENTE");
                String nome = rs.getString("NM_CLIENTE_COMPLETO");
                LocalDate dataNascimento = rs.getDate("DT_NASCIMENTO").toLocalDate();
                String cpf = rs.getString("NR_CPF");
                String dsGenero = rs.getString("DS_GENERO");

                result.add(new Cliente(idCliente, nome, dataNascimento, cpf, dsGenero));
            }
        } catch (SQLException e) {
            throw new ClienteDaoException("Erro ao ler clientes");
        }

        return result;
    }

    @Override
    public void update(Cliente cliente) throws ClienteDaoException {
        String sql = "UPDATE T_CON_CLIENTE SET NM_CLIENTE_COMPLETO = ?, DT_NASCIMENTO = ?, NR_CPF = ?, DS_GENERO = ? WHERE ID_CLIENTE = ?";
        try {
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);

            connection.setAutoCommit(false);

            pstmt.setString(1, cliente.getNome());
            pstmt.setDate(2, Date.valueOf(cliente.getDataNascimento()));
            pstmt.setString(3, cliente.getCpf());
            pstmt.setString(4, cliente.getDsGenero());
            pstmt.setInt(5, cliente.getIdCliente());

            pstmt.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw new ClienteDaoException("Erro ao atualizar cliente");
        }
    }

    @Override
    public void delete(int id) throws ClienteDaoException {
        String sql = "DELETE FROM T_CON_CLIENTE WHERE ID_CLIENTE = ?";
        try {
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            connection.setAutoCommit(false);

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw new ClienteDaoException("Erro ao excluir cliente");
        }
    }
}
