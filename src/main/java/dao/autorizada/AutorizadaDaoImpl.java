package dao;

import config.DatabaseConnectionFactory;
import entity.Autorizada;
import exception.AutorizadaDaoException;
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
        final String sql = "BEGIN INSERT INTO T_CON_AUTORIZADA (NM_AUTORIZADA, NR_CNPJ) VALUES (?, ?, ?) RETURNING ID_AUTORIZADA INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        connection.setAutoCommit(false);
        call.setString(1, autorizada.getNome());
        call.setString(2, autorizada.getCnpj());
        call.registerOutParameter(3, OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(3);
        if(linhasAlteradas == 0 || id == 0){
            throw new AutorizadaNotSavedException();
        }
        autorizada.setIdAutorizada(id);
        return pessoa;
  }

    @Override
    public List<Autorizada> readAll() {
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
            logger.warning("não foi possível localizar nenhum registro de pessoa: "+e.getMessage());
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
    public void deleteById(int id) throws AutorizadaDaoException {
        String sql = "DELETE FROM T_CON_AUTORIZADA WHERE ID_AUTORIZADA = ?";

        try (Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            connection.setAutoCommit(false);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new AutorizadaDaoException("Nenhum dado excluído da T_CON_AUTORIZADA");
        }
    }
}
