package dao.autorizada;

import config.DatabaseConnectionFactory;
import entity.Autorizada;
import exception.AutorizadaNotFoundException;
import exception.AutorizadaNotSavedException;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AutorizadaDaoImpl implements AutorizadaDao {

    private final Logger logger = Logger.getLogger(this.getClass().getName());


    @Override
    public Autorizada save(Autorizada autorizada, Connection connection) throws AutorizadaNotSavedException, SQLException {
        final String sql = "BEGIN INSERT INTO T_CON_AUTORIZADA(NM_AUTORIZADA, NR_CNPJ) VALUES (?, ?) RETURNING ID INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, autorizada.getNome());
        call.setString(2, autorizada.getCnpj());
        call.registerOutParameter(3, OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(3);
        if(linhasAlteradas == 0 || id == 0){
            throw new AutorizadaNotSavedException();
        }
        return autorizada;
    }

    @Override
    public List<Autorizada> readAll() {
        final List<Autorizada> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_CON_AUTORIZADA";
        try (Connection conn = DatabaseConnectionFactory.create().get()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                Autorizada autorizada = new Autorizada(
                        resultSet.getLong("id_autorizada"),
                        resultSet.getString("nm_autorizada"),
                        resultSet.getString("nr_cnpj"));
                all.add(autorizada);

            }
        } catch (SQLException e) {
            logger.info("nenhum registro encontrado em t_con_autorizada");
        }
        return all;
    }

    @Override
    public Autorizada update(Autorizada autorizada, Connection connection) throws AutorizadaNotFoundException, SQLException {
        final String sql = "UPDATE T_CON_AUTORIZADA set nm_autorizada = ?, nr_cnpj = ? WHERE id_autorizada = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, autorizada.getNome());
        stmt.setString(2, autorizada.getCnpj());
        stmt.setLong(3, autorizada.getIdAutorizada());
        int linhasAlteradas = stmt.executeUpdate();

        if(linhasAlteradas == 0){
            throw new AutorizadaNotFoundException();
        }

        return autorizada;
    }

    @Override
    public void deleteById(long id, Connection connection) throws AutorizadaNotFoundException, SQLException {
        final String sql = "delete from t_con_autorizada where id_pessoa = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();
        if(linhasAlteradas == 0){
            throw new AutorizadaNotFoundException();
        }
    }
}
