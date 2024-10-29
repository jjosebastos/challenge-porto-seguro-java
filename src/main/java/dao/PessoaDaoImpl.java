package dao;

import config.DatabaseConnectionFactory;
import entity.Pessoa;
import exception.ClienteDaoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PessoaDaoImpl implements PessoaDao {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void save(Pessoa pessoa) throws ClienteDaoException {
        String sql = "INSERT INTO T_CON_CLIENTE (ID_CLIENTE, NM_CLIENTE_COMPLETO, DT_NASCIMENTO, NR_CPF, DS_GENERO) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnectionFactory.create().get()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            pstmt.setInt(1, pessoa.getIdCliente());
            pstmt.setString(2,pessoa.getStatus());
            pstmt.setString(3, pessoa.getTipoCliente());
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new ClienteDaoException("Erro ao inserir cliente");
        }
    }

    @Override
    public List<Pessoa> readAll()  {
        final List<Pessoa> result = new ArrayList<>();
        final String sql = "SELECT * FROM T_CON_CLIENTE";

        try (Connection conn = DatabaseConnectionFactory.create().get()){
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                Pessoa pessoa = new Pessoa(
                        resultSet.getInt("ID_CLIENTE"),
                        resultSet.getString("TP_CLIENTE"),
                        resultSet.getString("ST_CLIENTE"));
                result.add(pessoa);
            }
        } catch (SQLException e) {
            logger.warning("Nenhuma pessoa encontrada!");
        }
        return result;
    }

    @Override
    public void update(Pessoa pessoa) throws ClienteDaoException {
        String sql = "UPDATE T_CON_CLIENTE SET NM_CLIENTE_COMPLETO = ?, DT_NASCIMENTO = ?, NR_CPF = ?, DS_GENERO = ? WHERE ID_CLIENTE = ?";
        try (Connection conn = DatabaseConnectionFactory.create().get()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            pstmt.setInt(1, pessoa.getIdCliente());
            pstmt.setString(2,pessoa.getStatus());
            pstmt.setString(3, pessoa.getTipoCliente());
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new ClienteDaoException("Erro ao atualizar cliente");
        }
    }

    @Override
    public void deleteById(int id) throws ClienteDaoException {
        String sql = "DELETE FROM T_CON_CLIENTE WHERE ID_CLIENTE = ?";
        try (Connection conn = DatabaseConnectionFactory.create().get()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new ClienteDaoException("Erro ao excluir cliente");
        }
    }
}
