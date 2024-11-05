package br.com.inovadevs.dao.orcamento;

import br.com.inovadevs.config.DatabaseConnectionFactory;
import br.com.inovadevs.entity.Orcamento;
import br.com.inovadevs.exception.OrcamentoNotFoundException;
import br.com.inovadevs.exception.OrcamentoNotSavedException;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OrcamentoDaoImpl implements OrcamentoDao {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public Orcamento save(Orcamento orcamento, Connection connection) throws OrcamentoNotSavedException, SQLException {
        final String sql =
            """
            BEGIN 
                INSERT INTO T_CON_ORCAMENTO(DT_HORA_CRIACAO, ST_APROVACAO, DS_DIAGNOSTICO_INICIAL, ID_VEICULO, ID_TECNICO)
                VALUES (?, ?, ?, ?, ?) RETURNING ID INTO ?; 
            END; 
            """;
        CallableStatement call = connection.prepareCall(sql);
        call.setObject(1, orcamento.getDataHoraCriacao());
        call.setString(2, orcamento.getStatus());
        call.setString(3, orcamento.getDiagnostico());
        call.setObject(4, orcamento.getIdVeiculo());
        call.setObject(5, orcamento.getIdTecnico());
        call.registerOutParameter(6, OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        long idOrcamento = call.getLong(6);
        if (linhasAlteradas == 0 || idOrcamento == 0) {
            throw new OrcamentoNotSavedException();
        }
        orcamento.setIdOrcamento(idOrcamento);
        return orcamento;
    }

    @Override
    public List<Orcamento> findAll() {
        final List<Orcamento> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_CON_ORCAMENTO";
        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Orcamento orcamento = new Orcamento(
                        resultSet.getLong("ID_ORCAMENTO"),
                        resultSet.getDate("DT_HORA_CRIACAO"),
                        resultSet.getString("ST_APROVACAO"),
                        resultSet.getString("DS_DIAGNOSTICO_INICIAL"),
                        resultSet.getLong("ID_VEICULO"),
                        resultSet.getLong("ID_TECNICO")
                );
                all.add(orcamento);
            }
        } catch (SQLException e) {
            logger.warning("nenhum registro encontrado em orcamento: " + e.getMessage());
        }
        return all;
    }

    @Override
    public Orcamento update(Orcamento orcamento, Connection connection) throws OrcamentoNotFoundException, SQLException {
        final String sql =
                """
                UPDATE T_CON_ORCAMENTO 
                    SET DT_HORA_CRIACAO = ?, ST_APROVACAO = ?, DS_DIAGNOSTICO_INICIAL = ?, ID_VEICULO = ?, ID_TECNICO = ? 
                WHERE ID_ORCAMENTO = ?;
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setObject(1, orcamento.getDataHoraCriacao());
        stmt.setString(2, orcamento.getStatus());
        stmt.setString(3, orcamento.getDiagnostico());
        stmt.setObject(4, orcamento.getIdVeiculo());
        stmt.setObject(5, orcamento.getIdTecnico());
        stmt.setLong(6, orcamento.getIdOrcamento());

        int linhasAlteradas = stmt.executeUpdate();
        if (linhasAlteradas == 0) {
            throw new OrcamentoNotFoundException();
        }
        return orcamento;
    }

    @Override
    public void delete(long id, Connection connection) throws OrcamentoNotFoundException, SQLException {
        final String sql = "DELETE FROM T_CON_ORCAMENTO WHERE ID_ORCAMENTO = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);

        int linhasAlteradas = stmt.executeUpdate();
        if (linhasAlteradas == 0) {
            throw new OrcamentoNotFoundException();
        }
    }
}
