package service.orcamento;

import entity.Orcamento;
import exception.OrcamentoNotFoundException;
import exception.OrcamentoNotSavedException;
import exception.UnsupportedServiceOperationException;

import java.sql.SQLException;
import java.util.List;

public interface OrcamentoService {
    Orcamento create(Orcamento orcamento) throws SQLException, UnsupportedServiceOperationException, OrcamentoNotSavedException;
    List<Orcamento> findAll();
    Orcamento update(Orcamento orcamento) throws SQLException, OrcamentoNotFoundException;
    void deleteById(Long id) throws SQLException, OrcamentoNotFoundException;
}
