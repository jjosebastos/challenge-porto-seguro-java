package dao;

import config.DatabaseConfig;
import entity.Seguradora;
import exception.SeguradoraDaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeguradoraDaoImpl implements SeguradoraDao {
    private final DatabaseConfig db;

    public SeguradoraDaoImpl(DatabaseConfig db) {
        this.db = db;
    }

    @Override
    public void create(Seguradora seguradora) throws SeguradoraDaoException {
        String sql = "INSERT INTO T_CON_SEGURADORA (ID_SEGURADORA, NM_SEGURADORA, NR_CNPJ, ID_VEICULO) VALUES (?, ?, ?, ?)";
        try (Connection connection = db.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            pstmt.setInt(1, seguradora.getIdSeguradora());
            pstmt.setString(2, seguradora.getNome());
            pstmt.setString(3, seguradora.getCnpj());
            pstmt.setInt(4, seguradora.getIdVeiculo());
            pstmt.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw new SeguradoraDaoException("Nenhum dado inserido na T_CON_SEGURADORA");
        }
    }

    @Override
    public List<Seguradora> readAll() throws SeguradoraDaoException {
        List<Seguradora> result = new ArrayList<>();
        String sql = "SELECT * FROM T_CON_SEGURADORA";

        try {
            Connection connection = db.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int idSeguradora = rs.getInt("ID_SEGURADORA");
                String nome = rs.getString("NM_SEGURADORA");
                String cnpj = rs.getString("NR_CNPJ");
                int idVeiculo = rs.getInt("ID_VEICULO");

                result.add(new Seguradora(idSeguradora, nome, cnpj, idVeiculo));
            }
        } catch (SQLException e) {
            throw new SeguradoraDaoException("Nenhum dado encontrado na T_CON_SEGURADORA");
        }

        return result;
    }

    @Override
    public void update(Seguradora seguradora) throws SeguradoraDaoException {
        String sql = "UPDATE T_CON_SEGURADORA SET NM_SEGURADORA = ?, NR_CNPJ = ?, ID_VEICULO = ? WHERE ID_SEGURADORA = ?";
        try {
            Connection connection = db.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, seguradora.getNome());
            pstmt.setString(2, seguradora.getCnpj());
            pstmt.setInt(3, seguradora.getIdVeiculo());
            pstmt.setInt(4, seguradora.getIdSeguradora());
            pstmt.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw new SeguradoraDaoException("Nenhum dado atualizado na T_CON_SEGURADORA");
        }
    }

    @Override
    public void delete(int id) throws SeguradoraDaoException {
        String sql = "DELETE FROM T_CON_SEGURADORA WHERE ID_SEGURADORA = ?";
        try {
            Connection connection = db.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw new SeguradoraDaoException("Nenhum dado excluído da T_CON_SEGURADORA");
        }
    }
}
