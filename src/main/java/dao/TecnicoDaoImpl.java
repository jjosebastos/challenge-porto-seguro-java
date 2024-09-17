package dao;

import config.DatabaseConfig;
import entity.Tecnico;
import exception.TecnicoDaoException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TecnicoDaoImpl implements TecnicoDao {
    private final DatabaseConfig db;

    public TecnicoDaoImpl(DatabaseConfig db) {
        this.db = db;
    }

    @Override
    public void create(Tecnico tecnico) throws TecnicoDaoException {
        String sql = "INSERT INTO T_CON_TECNICO (ID_TECNICO, NM_TECNICO, DT_NASCIMENTO, NR_REGISTRO_MATRICULA, ID_AUTORIZADA) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);

            connection.setAutoCommit(false);
            pstmt.setInt(1, tecnico.getIdTecnico());
            pstmt.setString(2, tecnico.getNome());
            pstmt.setDate(3, Date.valueOf(tecnico.getDataNascimento()));
            pstmt.setString(4, tecnico.getMatricula());
            pstmt.setObject(5, tecnico.getIdAutorizada(), Types.INTEGER);
            pstmt.executeUpdate();
            connection.commit();
            connection.close();

        } catch (SQLException e) {
            throw new TecnicoDaoException("Erro ao inserir dados na T_CON_TECNICO");
        }
    }

    @Override
    public List<Tecnico> readAll() throws TecnicoDaoException {
        List<Tecnico> result = new ArrayList<>();
        String sql = "SELECT * FROM T_CON_TECNICO";

        try {
            Connection connection = db.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int idTecnico = rs.getInt("ID_TECNICO");
                String nome = rs.getString("NM_TECNICO");
                LocalDate dataNascimento = rs.getDate("DT_NASCIMENTO").toLocalDate();
                String matricula = rs.getString("NR_REGISTRO_MATRICULA");
                Integer idAutorizada = (Integer) rs.getObject("ID_AUTORIZADA");

                result.add(new Tecnico(idTecnico, nome, dataNascimento, matricula, idAutorizada));
            }
            return result;
        } catch (SQLException e) {
            throw new TecnicoDaoException("Erro ao buscar dados na T_CON_TECNICO");
        }


    }

    @Override
    public void update(Tecnico tecnico) throws TecnicoDaoException {
        String sql = "UPDATE T_CON_TECNICO SET NM_TECNICO = ?, DT_NASCIMENTO = ?, NR_REGISTRO_MATRICULA = ?, ID_AUTORIZADA = ? WHERE ID_TECNICO = ?";
        try {
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, tecnico.getNome());
            pstmt.setDate(2, Date.valueOf(tecnico.getDataNascimento()));
            pstmt.setString(3, tecnico.getMatricula());
            pstmt.setObject(4, tecnico.getIdAutorizada(), Types.INTEGER);
            pstmt.setInt(5, tecnico.getIdTecnico());
            pstmt.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw new TecnicoDaoException("Erro ao atualizar dados na T_CON_TECNICO");
        }
    }

    @Override
    public void delete(int id) throws TecnicoDaoException {
        String sql = "DELETE FROM T_CON_TECNICO WHERE ID_TECNICO = ?";
        try {
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw new TecnicoDaoException("Erro ao excluir dados da T_CON_TECNICO");
        }
    }
}
