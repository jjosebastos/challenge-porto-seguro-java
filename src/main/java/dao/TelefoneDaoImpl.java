package dao;

import config.DatabaseConfig;
import entity.Telefone;
import exception.TelefoneDaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelefoneDaoImpl implements TelefoneDao {
    private final DatabaseConfig db;

    public TelefoneDaoImpl(DatabaseConfig db) {
        this.db = db;
    }

    @Override
    public void create(Telefone telefone) throws TelefoneDaoException {
        String sql = "INSERT INTO T_CON_TELEFONE (ID_TELEFONE, NR_DDD, NR_TELEFONE, TP_TELEFONE, ID_SEGURADORA, ID_AUTORIZADA, ID_CLIENTE) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            connection.setAutoCommit(false);

            connection.setAutoCommit(false);
            pstmt.setInt(1, telefone.getIdTelefone());
            pstmt.setString(2, telefone.getDdd());
            pstmt.setString(3, telefone.getNumero());
            pstmt.setString(4, telefone.getTipoTelefone());
            pstmt.setObject(5, telefone.getIdSeguradora(), Types.INTEGER);
            pstmt.setObject(6, telefone.getIdAutorizada(), Types.INTEGER);
            pstmt.setObject(7, telefone.getIdCliente(), Types.INTEGER);
            pstmt.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw new TelefoneDaoException("Erro ao inserir dados na T_CON_TELEFONE");
        }
    }

    @Override
    public List<Telefone> readAll() throws TelefoneDaoException {
        List<Telefone> result = new ArrayList<>();
        String sql = "SELECT * FROM T_CON_TELEFONE";

        try {
            Connection connection = db.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int idTelefone = rs.getInt("ID_TELEFONE");
                String ddd = rs.getString("NR_DDD");
                String numero = rs.getString("NR_TELEFONE");
                String tipoTelefone = rs.getString("TP_TELEFONE");
                int idSeguradora = rs.getInt("ID_SEGURADORA");
                int idAutorizada = rs.getInt("ID_AUTORIZADA");
                int idCliente = rs.getInt("ID_CLIENTE");

                result.add(new Telefone(idTelefone, numero, ddd, tipoTelefone, idSeguradora, idAutorizada, idCliente));
            }
        } catch (SQLException e) {
            throw new TelefoneDaoException("Erro ao buscar dados na T_CON_TELEFONE");
        }

        return result;
    }

    @Override
    public void update(Telefone telefone) throws TelefoneDaoException {
        String sql = "UPDATE T_CON_TELEFONE SET NR_DDD = ?, NR_TELEFONE = ?, TP_TELEFONE = ?, ID_SEGURADORA = ?, ID_AUTORIZADA = ?, ID_CLIENTE = ? WHERE ID_TELEFONE = ?";
        try {
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            connection.setAutoCommit(false);

            pstmt.setString(1, telefone.getDdd());
            pstmt.setString(2, telefone.getNumero());
            pstmt.setString(3, telefone.getTipoTelefone());
            pstmt.setObject(4, telefone.getIdSeguradora(), Types.INTEGER);
            pstmt.setObject(5, telefone.getIdAutorizada(), Types.INTEGER);
            pstmt.setObject(6, telefone.getIdCliente(), Types.INTEGER);
            pstmt.setInt(7, telefone.getIdTelefone());
            pstmt.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw new TelefoneDaoException("Erro ao atualizar dados na T_CON_TELEFONE");
        }
    }

    @Override
    public void delete(int id) throws TelefoneDaoException {
        String sql = "DELETE FROM T_CON_TELEFONE WHERE ID_TELEFONE = ?";
        try {
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            connection.setAutoCommit(false);


            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw new TelefoneDaoException("Erro ao excluir dados da T_CON_TELEFONE");
        }
    }
}
