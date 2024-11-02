package dao.seguradora;


import config.DatabaseConnectionFactory;
import entity.Seguradora;
import exception.SeguradoraDaoException;
import exception.SeguradoraNotFoundException;
import exception.SeguradoraNotSavedException;
import exception.UnsupportedServiceOperationException;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SeguradoraDaoImpl implements SeguradoraDao {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public Seguradora create(Seguradora seguradora, Connection connection) throws SeguradoraNotSavedException, SQLException, UnsupportedServiceOperationException {
        final String sql = "BEGIN INSERT INTO T_CON_SEGURADORA(NM_SEGURADORA, NR_CNPJ, ID_VEICULO)"+
                " VALUES(?,?,?) RETURNING ID INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, seguradora.getNome());
        call.setString(2, seguradora.getCnpj());
        call.setLong(3, seguradora.getIdVeiculo());
        call.registerOutParameter(4, OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(4);

        if(linhasAlteradas == 0 || id == 0){
            throw new SeguradoraNotSavedException();
        }

        return seguradora;
    }

    @Override
    public List<Seguradora> readAll() {
        final List<Seguradora> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_CON_SEGURADORA WHERE ID_SEGURADORA = ?";
        try(Connection connection = DatabaseConnectionFactory.create().get()){

            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                Seguradora seguradora = new Seguradora(
                        resultSet.getLong("id_seguradora"),
                        resultSet.getString("nm_seguradora"),
                        resultSet.getString("nr_cnpj"),
                        resultSet.getLong("id_veiculo"));
                all.add(seguradora);
            }
        } catch (SQLException e){
            logger.info("Nenhum registro encontrado");
        }
        return all;
    }

    @Override
    public Seguradora update(Seguradora seguradora, Connection connection) throws SeguradoraNotFoundException, SQLException {
        final String sql = "UPDATE T_CON_SEGURADORA SET NM_SEGURADORA = ?, NR_CNPJ = ?, ID_VEICULO = ?" +
                " WHERE ID_SEGURADORA = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "nm_seguradora");
        stmt.setString(2, "nr_cnpj");
        stmt.setObject(3, "id_veiculo");
        stmt.setObject(4, "id_veiculo");

        int linhasAlteradas = stmt.executeUpdate();
        if(linhasAlteradas == 0){
            throw new SeguradoraNotFoundException();
        }
        return seguradora;
    }

    @Override
    public void deleteById(Long id, Connection connection) throws SQLException, SeguradoraNotFoundException {
        final String sql = "DELETE FROM T_CON_SEGURADORA WHERE ID_SEGURADORA = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);

        int linhasAlteradas = stmt.executeUpdate();
        if(linhasAlteradas == 0){
            throw new SeguradoraNotFoundException();
        }

    }
}
