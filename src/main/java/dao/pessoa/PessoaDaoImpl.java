package dao;

import config.DatabaseConnectionFactory;
import entity.Pessoa;
import exception.ClienteDaoException;
import exception.PessoaDaoNotSavedException;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PessoaDaoImpl implements PessoaDao {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void save(Pessoa pessoa, Connection connection) throws PessoaDaoNotSavedException, SQLException {
        final String sql = "BEGIN INSERT INTO T_CON_PESSOA(tp_pessoa, st_pessoa) VALUES (?, ?) RETURNING ID INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, pessoa.getStatus());
        call.setString(2, pessoa.getTipoCliente());
        call.registerOutParameter(3, OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(2);
        if(linhasAlteradas == 0 || id == 0){
            throw new PessoaDaoNotSavedException();
        }
    }

    @Override
    public List<Pessoa> readAll()  {
        final List<Pessoa> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_CON_PESSOA";
        try(Connection conn = DatabaseConnectionFactory.create().get()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                Pessoa pessoa = new Pessoa(
                        resultSet.getLong("id"),
                        resultSet.getString("tp_pessoa"),
                        resultSet.getString("st_pessoa"));
                all.add(pessoa);
            }
        } catch (SQLException e) {
            logger.warning("não foi possível localizar nenhum registro de pessoa: "+e.getMessage());
        }
        return all;
    }

    @Override
    public void update(Pessoa pessoa) throws ClienteDaoException {
        String sql = "UPDATE T_CON_CLIENTE SET NM_CLIENTE_COMPLETO = ?, DT_NASCIMENTO = ?, NR_CPF = ?, DS_GENERO = ? WHERE ID_CLIENTE = ?";
        try (Connection conn = DatabaseConnectionFactory.create().get()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            pstmt.setLong(1, pessoa.getIdPessoa());
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
