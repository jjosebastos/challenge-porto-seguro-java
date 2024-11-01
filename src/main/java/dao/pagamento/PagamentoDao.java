package dao.pagamento;

import entity.Pagamento;
import exception.PagamentoDaoNotSavedException;
import exception.PagamentoNotFoundException;
import exception.UnsupportedServiceOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PagamentoDao {
    Pagamento create(Pagamento pagamento, Connection connection) throws UnsupportedServiceOperationException, SQLException, PagamentoDaoNotSavedException;
    List<Pagamento> readAll();
    Pagamento update(Pagamento pagamento) throws PagamentoNotFoundException, SQLException;
    void delete(int id, Connection connection) throws PagamentoNotFoundException, SQLException;
}
