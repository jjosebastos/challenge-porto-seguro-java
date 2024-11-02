package service.orcamento;

import entity.Orcamento;
import exception.OrcamentoNotFoundException;
import exception.OrcamentoNotSavedException;
import exception.UnsupportedServiceOperationException;

import java.sql.SQLException;
import java.util.List;

public class OrcamentoServiceImpl implements OrcamentoService {
    @Override
    public Orcamento create(Orcamento orcamento) throws SQLException, UnsupportedServiceOperationException, OrcamentoNotSavedException {
        return null;
    }

    @Override
    public List<Orcamento> findAll() {
        return List.of();
    }

    @Override
    public Orcamento update(Orcamento orcamento) throws SQLException, OrcamentoNotFoundException {
        return null;
    }

    @Override
    public void deleteById(Long id) throws SQLException, OrcamentoNotFoundException {

    }
}
