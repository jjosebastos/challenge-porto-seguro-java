package br.com.inovadevs.dao.pagamento;

import br.com.inovadevs.entity.Pagamento;
import br.com.inovadevs.exception.PagamentoNotSavedException;
import br.com.inovadevs.exception.PagamentoNotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PagamentoDao {
    Pagamento save(Pagamento pagamento, Connection connection) throws SQLException, PagamentoNotSavedException;
    List<Pagamento> readAll();
    Pagamento update(Pagamento pagamento, Connection connection) throws PagamentoNotFoundException, SQLException;
    void deleteById(long id, Connection connection) throws PagamentoNotFoundException, SQLException;
}
