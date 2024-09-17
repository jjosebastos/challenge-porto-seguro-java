package dao;

import config.DatabaseConfig;
import entity.Autorizada;
import exception.AutorizadaDaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorizadaDaoImpl implements AutorizadaDao {
    private final DatabaseConfig db;

    public AutorizadaDaoImpl(DatabaseConfig db) {
        this.db = db;
    }

    @Override
    public void create(Autorizada autorizada) throws AutorizadaDaoException {
        String sql = "INSERT INTO T_CON_AUTORIZADA (ID_AUTORIZADA, NM_AUTORIZADA, NR_CNPJ) VALUES (?, ?, ?)";
        try {
                Connection connection = db.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);

            connection.setAutoCommit(false);
            pstmt.setInt(1, autorizada.getIdAutorizada());
            pstmt.setString(2, autorizada.getNome());
            pstmt.setString(3, autorizada.getCnpj());
            pstmt.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw new AutorizadaDaoException("Nenhum dado inserido na T_CON_AUTORIZADA");
        }
    }

    @Override
    public List<Autorizada> readAll() throws AutorizadaDaoException {
        List<Autorizada> result = new ArrayList<>();
        String sql = "SELECT * FROM T_CON_AUTORIZADA";

        try (Connection connection = db.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int idAutorizada = rs.getInt("ID_AUTORIZADA");
                String nome = rs.getString("NM_AUTORIZADA");
                String cnpj = rs.getString("NR_CNPJ");

                result.add(new Autorizada(idAutorizada, nome, cnpj));
            }
        } catch (SQLException e) {
            throw new AutorizadaDaoException("Nenhum dado encontrado");
        }

        return result;
    }

    @Override
    public void update(Autorizada autorizada) throws AutorizadaDaoException {
        String sql = "UPDATE T_CON_AUTORIZADA SET NM_AUTORIZADA = ?, NR_CNPJ = ? WHERE ID_AUTORIZADA = ?";
        try {
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, autorizada.getNome());
            pstmt.setString(2, autorizada.getCnpj());
            pstmt.setInt(3, autorizada.getIdAutorizada());
            pstmt.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw new AutorizadaDaoException("Nenhum dado atualizado na T_AUTORIZADA");
        }
    }

    @Override
    public void delete(int id) throws AutorizadaDaoException {
        String sql = "DELETE FROM T_CON_AUTORIZADA WHERE ID_AUTORIZADA = ?";

        try {
            Connection connection = db.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw new AutorizadaDaoException("Nenhum dado excluído da T_CON_AUTORIZADA");
        }
    }
}
