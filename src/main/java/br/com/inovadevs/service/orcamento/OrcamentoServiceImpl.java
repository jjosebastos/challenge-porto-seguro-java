package br.com.inovadevs.service.orcamento;

import br.com.inovadevs.config.DatabaseConnectionFactory;
import br.com.inovadevs.dao.orcamento.OrcamentoDao;
import br.com.inovadevs.dao.orcamento.OrcamentoDaoFactory;
import br.com.inovadevs.entity.Orcamento;
import br.com.inovadevs.exception.OrcamentoNotFoundException;
import br.com.inovadevs.exception.OrcamentoNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrcamentoServiceImpl implements OrcamentoService {

    private final OrcamentoDao dao = OrcamentoDaoFactory.create();
    @Override
    public Orcamento create(Orcamento orcamento) throws SQLException, UnsupportedServiceOperationException, OrcamentoNotSavedException {
        if(orcamento.getIdOrcamento() == null){
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                orcamento = this.dao.save(orcamento, connection);
                connection.commit();
                return orcamento;
            } catch (SQLException | OrcamentoNotSavedException e){
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException();
        }

    }

    @Override
    public List<Orcamento> findAll() {
        return this.dao.findAll();
    }

    @Override
    public Orcamento update(Orcamento orcamento) throws SQLException, OrcamentoNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        orcamento = this.dao.update(orcamento, connection);
        connection.commit();
        return orcamento;
    }

    @Override
    public void deleteById(Long id) throws SQLException, OrcamentoNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.delete(id, connection);
        connection.commit();
    }
}
