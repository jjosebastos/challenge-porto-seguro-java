package dao;

import config.DatabaseConfig;
import entity.Pagamento;
import exception.PagamentoDaoException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDaoImpl implements PagamentoDao {
    private final DatabaseConfig db;

    public PagamentoDaoImpl(DatabaseConfig db) {
        this.db = db;
    }

    @Override
    public void create(Pagamento pagamento) throws PagamentoDaoException {
        String sql = "INSERT INTO T_CON_PAGAMENTO (ID_PAGAMENTO, DT_PAGAMENTO, VL_PAGAMENTO, ST_PAGAMENTO, ID_CLIENTE, ID_SEGURADORA, ID_AUTORIZADA, TP_PAGAMENTO) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            connection.setAutoCommit(false);

            pstmt.setInt(1, pagamento.getIdPagamento());
            pstmt.setDate(2, Date.valueOf(pagamento.getDataPagamento()));
            pstmt.setDouble(3, pagamento.getValor());
            pstmt.setString(4, pagamento.getStatus());
            pstmt.setInt(5, pagamento.getIdCliente());

            // Note: setNull if the value is null
            if (pagamento.getIdSeguradora() != null) {
                pstmt.setInt(6, pagamento.getIdSeguradora());
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }

            if (pagamento.getIdAutorizada() != null) {
                pstmt.setInt(7, pagamento.getIdAutorizada());
            } else {
                pstmt.setNull(7, Types.INTEGER);
            }

            pstmt.setString(8, pagamento.getTipoPagamento());

            pstmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new PagamentoDaoException("Erro ao inserir dados na tabela T_CON_PAGAMENTO");
        }
    }

    @Override
    public List<Pagamento> readAll() throws PagamentoDaoException {
        List<Pagamento> result = new ArrayList<>();
        String sql = "SELECT * FROM T_CON_PAGAMENTO";

        try (Connection connection = db.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int idPagamento = rs.getInt("ID_PAGAMENTO");
                LocalDate dataPagamento = rs.getDate("DT_PAGAMENTO").toLocalDate();
                double valor = rs.getDouble("VL_PAGAMENTO");
                String status = rs.getString("ST_PAGAMENTO");
                int idCliente = rs.getInt("ID_CLIENTE");
                Integer idSeguradora = rs.getObject("ID_SEGURADORA", Integer.class);
                Integer idAutorizada = rs.getObject("ID_AUTORIZADA", Integer.class);
                String tipoPagamento = rs.getString("TP_PAGAMENTO");

                result.add(new Pagamento(idPagamento, dataPagamento, valor, status, idCliente, idSeguradora, idAutorizada, tipoPagamento));
            }
        } catch (SQLException e) {
            throw new PagamentoDaoException("Erro ao consultar dados na tabela T_CON_PAGAMENTO");
        }

        return result;
    }

    @Override
    public void update(Pagamento pagamento) throws PagamentoDaoException {
        String sql = "UPDATE T_CON_PAGAMENTO SET DT_PAGAMENTO = ?, VL_PAGAMENTO = ?, ST_PAGAMENTO = ?, ID_CLIENTE = ?, ID_SEGURADORA = ?, ID_AUTORIZADA = ?, TP_PAGAMENTO = ? " +
                "WHERE ID_PAGAMENTO = ?";
        try {
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            connection.setAutoCommit(false);

            pstmt.setDate(1, Date.valueOf(pagamento.getDataPagamento()));
            pstmt.setDouble(2, pagamento.getValor());
            pstmt.setString(3, pagamento.getStatus());

            if (pagamento.getIdCliente() != null) {
                pstmt.setInt(4, pagamento.getIdCliente());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }
            ;

            if (pagamento.getIdSeguradora() != null) {
                pstmt.setInt(5, pagamento.getIdSeguradora());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }

            if (pagamento.getIdAutorizada() != null) {
                pstmt.setInt(6, pagamento.getIdAutorizada());
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }

            pstmt.setString(7, pagamento.getTipoPagamento());
            pstmt.setInt(8, pagamento.getIdPagamento());

            pstmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new PagamentoDaoException("Erro ao atualizar dados na tabela T_CON_PAGAMENTO");
        }
    }

    @Override
    public void delete(int id) throws PagamentoDaoException {
        String sql = "DELETE FROM T_CON_PAGAMENTO WHERE ID_PAGAMENTO = ?";

        try (Connection connection = db.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            throw new PagamentoDaoException("Erro ao excluir dados na tabela T_CON_PAGAMENTO");
        }
    }
}
