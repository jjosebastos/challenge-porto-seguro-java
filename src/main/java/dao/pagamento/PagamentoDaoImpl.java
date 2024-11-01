package dao.pagamento;


import entity.Pagamento;
import exception.PagamentoDaoNotSavedException;
import exception.PagamentoNotFoundException;
import exception.UnsupportedServiceOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class PagamentoDaoImpl implements PagamentoDao {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public Pagamento create(Pagamento pagamento, Connection connection) throws UnsupportedServiceOperationException, SQLException, PagamentoDaoNotSavedException {
        final String sql = "BEGIN INSERT INTO T_CON_PAGAMENTO ("

        return null;
    }

    @Override
    public List<Pagamento> readAll() {
        return List.of();
    }

    @Override
    public Pagamento update(Pagamento pagamento) throws PagamentoNotFoundException, SQLException {
        return null;
    }

    @Override
    public void delete(int id, Connection connection) throws PagamentoNotFoundException, SQLException {

    }
}
