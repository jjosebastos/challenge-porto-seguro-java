package dao.telefone;


import config.DatabaseConnectionFactory;

import entity.Telefone;
import exception.TelefoneNotFoundException;
import exception.TelefoneNotSavedException;
import exception.UnsupportedServiceOperationException;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TelefoneDaoImpl implements TelefoneDao {

    private final Logger logger = Logger.getLogger(TelefoneDaoImpl.class.getName());
    @Override
    public Telefone save(Telefone telefone, Connection connection) throws UnsupportedServiceOperationException, SQLException, TelefoneNotSavedException {
        final String sql = "BEGIN INSERT INTO T_CON_TELEFONE " +
                "(nr_ddd, nr_telefone, tp_telefone, id_pessoa, id_seguradora, id_autorizada)" +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING ID INTO?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, "nr_ddd");
        call.setString(2, "nr_telefone");
        call.setString(3, "tp_telefone");
        call.setObject(4, "id_cliente");
        call.setObject(5, "id_seguradora");
        call.setObject(6, "id_autorizada");
        call.registerOutParameter(7, OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        Long id = call.getLong(7);
        if (linhasAlteradas == 0 || id == null) {
            throw new TelefoneNotSavedException();
        }
        return telefone;
    }

    @Override
    public List<Telefone> readAll() {
        final String sql = "SELECT * FROM T_CON_TELEFONE";
        final List<Telefone> all = new ArrayList<>();
        try (Connection conn = DatabaseConnectionFactory.create().get()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Telefone telefone = new Telefone(
                        resultSet.getLong("id_telefone"),
                        resultSet.getString("nr_ddd"),
                        resultSet.getString("nr_telefone"),
                        resultSet.getString("tp_telefone"),
                        resultSet.getLong("id_cliente"),
                        resultSet.getLong("id_seguradora"),
                        resultSet.getLong("id_autorizada"));
                all.add(telefone);
            }
        } catch (SQLException e){
            logger.info("nenhum telefone inserido");
        }

        return all;
    }

    @Override
    public Telefone update(Telefone telefone, Connection connection) throws TelefoneNotFoundException, SQLException {
        final String sql = "UPDATE T_CON_TELEFONE " +
                "SET NR_DDD = ?, NR_TELEFONE = ?, TP_TELEFONE = ?, ID_CLIENTE = ?," +
                "ID_SEGURADORA = ?, ID_AUTORIZADA = ? WHERE ID_TELEFONE = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "nr_ddd");
        stmt.setString(2, "nr_telefone");
        stmt.setString(3, "tp_telefone");
        stmt.setObject(4, "id_cliente");
        stmt.setObject(5, "id_seguradora");
        stmt.setObject(6, "id_autorizada");
        stmt.setObject(7, "id_telefone");

        int linhasAlteradas = stmt.executeUpdate();

        if(linhasAlteradas == 0){
            throw new TelefoneNotFoundException();
        }
        return telefone;
    }

    @Override
    public void delete(Long id, Connection connection) throws TelefoneNotFoundException, SQLException{
        final String sql = "DELETE FROM T_CON_TELEFONE WHERE ID_TELEFONE = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setObject(1, id);
        int linhasAlteradas = stmt.executeUpdate();
        if(linhasAlteradas == 0){
            throw new TelefoneNotFoundException();
        }
    }
}
