package dao.pagamento;

import config.DatabaseConnectionFactory;
import entity.Pagamento;
import exception.PagamentoNotFoundException;
import exception.PagamentoNotSavedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PagamentoDaoImpl implements PagamentoDao {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public Pagamento save(Pagamento pagamento, Connection connection) throws PagamentoNotSavedException, SQLException {
        final String sql = "BEGIN INSERT INTO T_CON_PAGAMENTO(TP_PAGAMENTO, DT_PAGAMENTO, VL_PAGAMENTO, ST_PAGAMENTO, DS_PAGAMENTO, ID_PESSOA, ID_SEGURADORA, ID_AUTORIZADA) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING ID_PAGAMENTO INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, pagamento.getTipoPagamento());
        call.setDate(2, Date.valueOf(pagamento.getData()));
        call.setDouble(3, pagamento.getValor());
        call.setString(4, pagamento.getStatus());
        call.setString(5, pagamento.getDescricao());
        call.setObject(6, pagamento.getIdPessoa());
        call.setObject(7, pagamento.getIdSeguradora());
        call.setObject(8, pagamento.getIdAutorizada());
        call.registerOutParameter(9, Types.NUMERIC);

        int linhasAlteradas = call.executeUpdate();
        long idPagamento = call.getLong(9);
        if (linhasAlteradas == 0 || idPagamento == 0) {
            throw new PagamentoNotSavedException();
        }
        pagamento.setIdPagamento(idPagamento);
        return pagamento;
    }

    public List<Pagamento> readAll() {
        final List<Pagamento> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_CON_PAGAMENTO";
        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Pagamento pagamento = new Pagamento(
                        resultSet.getLong("ID_PAGAMENTO"),
                        resultSet.getString("TP_PAGAMENTO"),
                        resultSet.getDate("DT_PAGAMENTO").toLocalDate(),
                        resultSet.getDouble("VL_PAGAMENTO"),
                        resultSet.getString("ST_PAGAMENTO"),
                        resultSet.getString("DS_PAGAMENTO"),
                        resultSet.getObject("ID_PESSOA", Long.class),
                        resultSet.getObject("ID_SEGURADORA", Long.class),
                        resultSet.getObject("ID_AUTORIZADA", Long.class)
                );
                all.add(pagamento);
            }
        } catch (SQLException e) {
            logger.info("Nenhum registro encontrado em T_CON_PAGAMENTO.");
        }
        return all;
    }

    public Pagamento update(Pagamento pagamento, Connection connection) throws PagamentoNotFoundException, SQLException {
        final String sql = "UPDATE T_CON_PAGAMENTO SET TP_PAGAMENTO = ?, DT_PAGAMENTO = ?, VL_PAGAMENTO = ?, ST_PAGAMENTO = ?, DS_PAGAMENTO = ?, " +
                "ID_PESSOA = ?, ID_SEGURADORA = ?, ID_AUTORIZADA = ? WHERE ID_PAGAMENTO = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, pagamento.getTipoPagamento());
        stmt.setDate(2, Date.valueOf(pagamento.getData()));
        stmt.setDouble(3, pagamento.getValor());
        stmt.setString(4, pagamento.getStatus());
        stmt.setString(5, pagamento.getDescricao());
        stmt.setObject(6, pagamento.getIdPessoa());
        stmt.setObject(7, pagamento.getIdSeguradora());
        stmt.setObject(8, pagamento.getIdAutorizada());
        stmt.setLong(9, pagamento.getIdPagamento());

        int linhasAlteradas = stmt.executeUpdate();
        if (linhasAlteradas == 0) {
            throw new PagamentoNotFoundException();
        }
        return pagamento;
    }

    public void deleteById(long idPagamento, Connection connection) throws PagamentoNotFoundException, SQLException {
        final String sql = "DELETE FROM T_CON_PAGAMENTO WHERE ID_PAGAMENTO = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, idPagamento);
        int linhasAlteradas = stmt.executeUpdate();
        if (linhasAlteradas == 0) {
            throw new PagamentoNotFoundException();
        }
    }
}
