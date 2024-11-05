package br.com.inovadevs.dao.veiculo;

import br.com.inovadevs.config.DatabaseConnectionFactory;
import br.com.inovadevs.entity.Veiculo;
import br.com.inovadevs.exception.VeiculoNotFoundException;
import br.com.inovadevs.exception.VeiculoNotSavedException;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class VeiculoDaoImpl implements VeiculoDao {

    private final Logger logger = Logger.getLogger(VeiculoDaoImpl.class.getName());
    @Override
    public Veiculo create(Veiculo veiculo, Connection connection) throws SQLException, VeiculoNotSavedException {
        final String sql =
                """
                BEGIN 
                    INSERT INTO (NR_PLACA,DS_MARCA,NM_MODELO,NR_CHASSI,ID_PESSOA)
                    VALUES (?,?,?,?,?) RETURNING ID INTO ?;
                END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, veiculo.getPlaca());
        call.setString(2, veiculo.getMarca());
        call.setString(3, veiculo.getModelo());
        call.setString(4, veiculo.getChassi());
        call.setLong(5, veiculo.getIdPessoa());
        call.registerOutParameter(6, OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(6);
        if (linhasAlteradas == 0 || id == 0){
            throw new VeiculoNotSavedException();
        }
        return veiculo;
    }

    @Override
    public List<Veiculo> readAll() {
        final List<Veiculo> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_CON_VEICULO";
        try (Connection connection = DatabaseConnectionFactory.create().get()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                Veiculo veiculo = new Veiculo(
                        resultSet.getLong("id_veiculo"),
                        resultSet.getString("nr_placa"),
                        resultSet.getString("ds_marca"),
                        resultSet.getString("nm_modelo"),
                        resultSet.getString("nr_chassi"),
                        resultSet.getLong("id_pessoa"));
                all.add(veiculo);
            }
        } catch (SQLException e){
            logger.warning("nenhum registro de veiculo encontrado: " + e.getMessage());
        }
        return all;
    }

    @Override
    public Veiculo update(Veiculo veiculo, Connection connection) throws SQLException, VeiculoNotFoundException {
        final String sql =
        """
        UPDATE T_CON_VEICULO 
            SET NR_PLACA = ?, DS_MARCA = ?, NM_MODELO = ?, NR_CHASSI = ?, ID_PESSOA = ?
        WHERE ID_VEICULO = ?;
        """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, veiculo.getPlaca());
        stmt.setString(2, veiculo.getMarca());
        stmt.setString(3, veiculo.getModelo());
        stmt.setString(4, veiculo.getChassi());
        stmt.setLong(5, veiculo.getIdPessoa());
        stmt.setLong(6, veiculo.getIdVeiculo());

        int linhasAlteradas = stmt.executeUpdate();

        if (linhasAlteradas == 0){
            throw  new VeiculoNotFoundException();
        }
        return veiculo;
    }

    @Override
    public void deleteById(long id, Connection connection) throws SQLException, VeiculoNotFoundException {
        final String sql = "DELETE FROM T_CON_VEICULO WHERE ID_VEICULO = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();
        if(linhasAlteradas == 0){
            throw new VeiculoNotFoundException();
        }
    }
}
