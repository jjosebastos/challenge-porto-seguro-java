package br.com.inovadevs.dao.historico;

import br.com.inovadevs.config.DatabaseConnectionFactory;
import br.com.inovadevs.entity.HistoricoVeiculo;
import br.com.inovadevs.exception.HistoricoVeiculoNotFoundException;
import br.com.inovadevs.exception.HistoricoVeiculoNotSavedException;

import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class HistoricoVeiculoDaoImpl implements HistoricoVeiculoDao {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public HistoricoVeiculo create(HistoricoVeiculo historicoVeiculo, Connection connection) throws HistoricoVeiculoNotSavedException, SQLException {
        final String sql =
                """
                BEGIN 
                    INSERT INTO T_CON_HISTORICO_VEICULO (tp_evento,dt_evento,ds_evento,km_odometro, id_veiculo, id_autorizada) 
                    VALUES(?,?,?,?,?,?,?) RETURNING ID INTO ?; 
                END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1,  historicoVeiculo.getTipoEvento());
        call.setObject(2, historicoVeiculo.getDataEvento());
        call.setString(3, historicoVeiculo.getDescricao());
        call.setInt(4, historicoVeiculo.getKmOdometro());
        call.setLong(5, historicoVeiculo.getIdVeiculo());
        call.setLong(6, historicoVeiculo.getIdAutorizada());
        call.registerOutParameter(7, OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(7);

        if (linhasAlteradas == 0 || id == 0) {
            throw new HistoricoVeiculoNotSavedException();
        }
        return historicoVeiculo;
    }

    @Override
    public List<HistoricoVeiculo> readAll() {
        final List<HistoricoVeiculo> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_CON_HISTORICO_VEICULO";
        try (Connection conn = DatabaseConnectionFactory.create().get()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                HistoricoVeiculo historicoVeiculo = new HistoricoVeiculo(
                        resultSet.getLong("id_historico_veiculo"),
                        resultSet.getString("tp_evento"),
                        resultSet.getDate("dt_evento"),
                        resultSet.getString("ds_evento"),
                        resultSet.getInt("km_odometro"),
                        resultSet.getLong("id_veiculo"),
                        resultSet.getLong("id_autorizada"));
                all.add(historicoVeiculo);
            }
        } catch (SQLException e){
            logger.warning("nenhum registro encontrado de telefone: " + e.getMessage());
        }

        return all;
    }

    @Override
    public HistoricoVeiculo update(HistoricoVeiculo historicoVeiculo, Connection connection) throws HistoricoVeiculoNotFoundException, SQLException {
        final String sql =
                """
                UPDATE T_CON_HISTORICO_VEICULO 
                    SET TP_EVENTO = ?, DT_EVENTO = ?, DS_EVENTO = ?, KM_ODOMETRO = ?, ID_VEICULO = ?, AUTORIZADA = ?
                WHERE ID_HISTORICO_VEICULO = ?
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1,  historicoVeiculo.getTipoEvento());
        stmt.setObject(2, historicoVeiculo.getDataEvento());
        stmt.setString(3, historicoVeiculo.getDescricao());
        stmt.setInt(4, historicoVeiculo.getKmOdometro());
        stmt.setLong(5, historicoVeiculo.getIdVeiculo());
        stmt.setLong(6, historicoVeiculo.getIdAutorizada());
        stmt.setLong(7, historicoVeiculo.getIdHistorico());

        int linhasAlteradas = stmt.executeUpdate();
        if (linhasAlteradas == 0) {
            throw new HistoricoVeiculoNotFoundException();
        }

        return historicoVeiculo;
    }

    @Override
    public void deleteById(Long id, Connection connection) throws HistoricoVeiculoNotFoundException, SQLException {
        final String sql = "DELETE FROM T_CON_HISTORICO_VEICULO WHERE ID_HISTORICO_VEICULO = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();
        if (linhasAlteradas == 0) {
            throw new HistoricoVeiculoNotFoundException();
        }
    }
}
