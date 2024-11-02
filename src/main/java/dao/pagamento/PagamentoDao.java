package dao.pagamento;

import entity.Pagamento;
import exception.PagamentoNotSavedException;
import exception.PagamentoNotFoundException;
import exception.UnsupportedServiceOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PagamentoDao {
    Pagamento save(Pagamento pagamento, Connection connection) throws UnsupportedServiceOperationException, SQLException, PagamentoNotSavedException;
    List<Pagamento> readAll();
    Pagamento update(Pagamento pagamento, Connection connection) throws PagamentoNotFoundException, SQLException;
    void deleteById(long id, Connection connection) throws PagamentoNotFoundException, SQLException;
}
