package br.com.inovadevs.dao.tecnico;

import br.com.inovadevs.config.DatabaseConnectionFactory;
import br.com.inovadevs.entity.Tecnico;
import br.com.inovadevs.exception.TecnicoNotFoundException;
import br.com.inovadevs.exception.TecnicoNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TecnicoDaoImpl implements TecnicoDao {


    private final Logger logger = Logger.getLogger(TecnicoDaoImpl.class.getName());

    @Override
    public Tecnico save(Tecnico tecnico, Connection connection) throws SQLException, TecnicoNotSavedException {
        final String sql =
                """
                BEGIN 
                    INSERT INTO T_CON_TECNICO (NM_TECNICO, DT_NASCIMENTO, NR_MATRICULA, ID_AUTORIZADA) 
                    VALUES (?,?,?,?,?) RETURNING ID INTO ?;
                END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, tecnico.getNome() );
        call.setObject(2, tecnico.getDataNascimento());
        call.setString(3, tecnico.getMatricula());
        call.setLong(4, tecnico.getIdAutorizada());
        call.registerOutParameter(5, OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(5);
        if (linhasAlteradas == 0 || id == 0){
            throw  new TecnicoNotSavedException();
        }
        return tecnico;
    }

    @Override
    public List<Tecnico> readAll() {
            final List<Tecnico> all = new ArrayList<>();
            final String sql = "SELECT * FROM T_CON_TECNICO";
            try (Connection conn = DatabaseConnectionFactory.create().get()){
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()){
                    Tecnico tecnico = new Tecnico(
                            resultSet.getLong("id_tecnico"),
                            resultSet.getString("nm_tecnico"),
                            resultSet.getDate("dt_nascimento"),
                            resultSet.getString("nr_matricula"),
                            resultSet.getLong("id_autorizada"));
                    all.add(tecnico);

                }
            } catch (SQLException e) {
                logger.info("nenhum registro encontrado em tecnico: " + e.getMessage());
            }
            return all;
    }

    @Override
    public Tecnico update(Tecnico tecnico, Connection connection) throws TecnicoNotFoundException, SQLException {
        final String sql =
                """
                UPDATE T_CON_TECNICO 
                    SET NM_TENCICO = ?, DT_NASCIMENTO = ?, NR_MATRICULA = ?,
                    ID_AUTORIZADA = ? WHERE id_tecnico = ?
                WHERE ID_TECNICO = ?;
                """;
        PreparedStatement call = connection.prepareStatement(sql);
        call.setString(1, tecnico.getNome());
        call.setObject(2, tecnico.getDataNascimento());
        call.setString(3, tecnico.getMatricula());
        call.setLong(4, tecnico.getIdAutorizada());
        int linhasAlteradas = call.executeUpdate();

        if (linhasAlteradas == 0){
            throw  new TecnicoNotFoundException();
        }

        return tecnico;
    }

    @Override
    public void deleteById(Long id, Connection connection) throws TecnicoNotFoundException, SQLException {
        final String sql = "DELETE FROM T_CON_TECNICO WHERE id_tecnico = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();
        if (linhasAlteradas == 0){
            throw  new TecnicoNotFoundException();
        }

    }
}
