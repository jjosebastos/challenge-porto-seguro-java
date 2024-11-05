package br.com.inovadevs.service.orcamento;

import br.com.inovadevs.entity.Orcamento;
import br.com.inovadevs.exception.OrcamentoNotFoundException;
import br.com.inovadevs.exception.OrcamentoNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;

import java.sql.SQLException;
import java.util.List;

public interface OrcamentoService {
    Orcamento create(Orcamento orcamento) throws SQLException, UnsupportedServiceOperationException, OrcamentoNotSavedException;
    List<Orcamento> findAll();
    Orcamento update(Orcamento orcamento) throws SQLException, OrcamentoNotFoundException;
    void deleteById(Long id) throws SQLException, OrcamentoNotFoundException;
}
