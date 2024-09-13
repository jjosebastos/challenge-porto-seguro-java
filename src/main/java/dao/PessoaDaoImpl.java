package dao;

import config.DatabaseConfig;
import entity.Pessoa;
import exception.PessoaDaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDaoImpl implements PessoaDao {
    private final DatabaseConfig db;
    public PessoaDaoImpl(DatabaseConfig db){
        this.db = db;
    }
    @Override
    public void create(Pessoa pessoa) throws SQLException, PessoaDaoException {

        String sql = "INSERT INTO T_CON_PESSOA (id_pessoa, nm_pessoa) VALUES (?, ?)";

        try {
            Connection connection = db.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, pessoa.getId());
            pstmt.setString(2, pessoa.getNome());
            pstmt.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e){
            throw new PessoaDaoException("Nenhuma pessoa foi inserida na tabela T_CON_PESSOA");
        }
    }

    @Override
    public List<Pessoa> readAll() throws SQLException, PessoaDaoException {

        String sql = "SELECT * FROM T_CON_PESSOA";

        try {
            Connection connection = db.getConnection();
            Statement stmt = connection.createStatement();
            List<Pessoa> result = new ArrayList<>();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                long id = rs.getLong("id_pessoa");
                String nome = rs.getString("nm_pessoa");
                result.add(new Pessoa(id, nome));
            }
            return (result);
        } catch (SQLException e){
            throw new PessoaDaoException("Query na tabela T_CON_PESSOA não realizada");
        }

    }

    @Override
    public void update(Pessoa pessoa) throws SQLException, PessoaDaoException {

        String sql = "UPDATE T_CON_PESSOA SET nm_pessoa=? WHERE id_pessoa=?";

        try {
            Connection connection = db.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, pessoa.getNome());
            pstmt.setLong(2, pessoa.getId());
            pstmt.executeUpdate();

            connection.commit();
            connection.close();

        } catch (SQLException e){
            throw new PessoaDaoException("Nenhum registro da tabela T_CON_PESSOA foi atualizado");
        }

    }

    @Override
    public void delete(int id) throws SQLException, PessoaDaoException {

        String sql = "DELETE FROM T_CON_PESSOA WHERE id_pessoa = ?";

        try {
            Connection connection = db.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();

            connection.commit();
            connection.close();

        } catch (SQLException e){
            throw new PessoaDaoException("Nenhum registro da tabela T_CON_PESSOA foi deletado");
        }
    }
}
