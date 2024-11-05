package br.com.inovadevs.service.pagamento;

import br.com.inovadevs.entity.Pagamento;
import br.com.inovadevs.exception.*;

import java.sql.SQLException;
import java.util.List;

public interface PagamentoService {
    Pagamento create(Pagamento pagamento) throws SQLException, PagamentoNotSavedException, UnsupportedServiceOperationException;
    List<Pagamento> findAll();
    Pagamento update(Pagamento pagamento) throws SQLException, PagamentoNotFoundException;
    void deleteById(long id) throws SQLException, PagamentoNotFoundException;

}
