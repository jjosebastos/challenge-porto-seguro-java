package dao;

import config.DatabaseConfig;
import entity.Veiculo;
import exception.VeiculoDaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDaoImpl implements VeiculoDao {
    private final DatabaseConfig db;

    public VeiculoDaoImpl(DatabaseConfig db) {
        this.db = db;
    }

    @Override
    public void create(Veiculo veiculo) throws VeiculoDaoException {
        String sql = "INSERT INTO T_CON_VEICULO (ID_VEICULO, NR_PLACA, DS_MARCA, NM_MODELO, NR_CHASSI, ID_CLIENTE) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);

            connection.setAutoCommit(false);
            pstmt.setInt(1, veiculo.getIdVeiculo());
            pstmt.setString(2, veiculo.getPlaca());
            pstmt.setString(3, veiculo.getMarca());
            pstmt.setString(4, veiculo.getModelo());
            pstmt.setString(5, veiculo.getChassi());
            pstmt.setInt(6, veiculo.getIdCliente());
            pstmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new VeiculoDaoException("Erro ao inserir dados na T_CON_VEICULO");
        }
    }

    @Override
    public List<Veiculo> readAll() throws VeiculoDaoException {
        List<Veiculo> result = new ArrayList<>();
        String sql = "SELECT * FROM T_CON_VEICULO";

        try {
            Connection connection = db.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int idVeiculo = rs.getInt("ID_VEICULO");
                String placa = rs.getString("NR_PLACA");
                String marca = rs.getString("DS_MARCA");
                String modelo = rs.getString("NM_MODELO");
                String chassi = rs.getString("NR_CHASSI");
                int idCliente = rs.getInt("ID_CLIENTE");

                result.add(new Veiculo(idVeiculo, placa, marca, modelo, chassi, idCliente));
            }
        } catch (SQLException e) {
            throw new VeiculoDaoException("Erro ao buscar dados na T_CON_VEICULO");
        }

        return result;
    }

    @Override
    public void update(Veiculo veiculo) throws VeiculoDaoException {
        String sql = "UPDATE T_CON_VEICULO SET NR_PLACA = ?, DS_MARCA = ?, NM_MODELO = ?, NR_CHASSI = ?, ID_CLIENTE = ? WHERE ID_VEICULO = ?";
        try {
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            pstmt.setString(1, veiculo.getPlaca());
            pstmt.setString(2, veiculo.getMarca());
            pstmt.setString(3, veiculo.getModelo());
            pstmt.setString(4, veiculo.getChassi());
            pstmt.setInt(5, veiculo.getIdCliente());
            pstmt.setInt(6, veiculo.getIdVeiculo());

            pstmt.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw new VeiculoDaoException("Erro ao atualizar dados na T_CON_VEICULO");
        }
    }

    @Override
    public void delete(int id) throws VeiculoDaoException {
        String sql = "DELETE FROM T_CON_VEICULO WHERE ID_VEICULO = ? CASCADE";
        try {
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            connection.setAutoCommit(false);

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw new VeiculoDaoException("Erro ao excluir dados da T_CON_VEICULO");
        }
    }
}
