package br.com.inovadevs.service.historico;

import br.com.inovadevs.config.DatabaseConnectionFactory;
import br.com.inovadevs.dao.historico.HistoricoVeiculoDao;
import br.com.inovadevs.dao.historico.HistoricoVeiculoFactory;
import br.com.inovadevs.entity.HistoricoVeiculo;
import br.com.inovadevs.exception.HistoricoVeiculoNotFoundException;
import br.com.inovadevs.exception.HistoricoVeiculoNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HistoricoVeiculoServiceImpl implements HistoricoVeiculoService {

    private final HistoricoVeiculoDao dao = HistoricoVeiculoFactory.create();


    @Override
    public HistoricoVeiculo create(HistoricoVeiculo historicoVeiculo) throws UnsupportedServiceOperationException, SQLException, HistoricoVeiculoNotSavedException {
        if(historicoVeiculo.getIdHistorico() == null){
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                historicoVeiculo = this.dao.create(historicoVeiculo, connection);
                connection.commit();
                return historicoVeiculo;

            } catch (SQLException | HistoricoVeiculoNotSavedException e){
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException();
        }

    }

    @Override
    public List<HistoricoVeiculo> findAll() {
        return this.dao.readAll();
    }

    @Override
    public HistoricoVeiculo update(HistoricoVeiculo historicoVeiculo) throws HistoricoVeiculoNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        historicoVeiculo = this.dao.update(historicoVeiculo, connection);
        connection.commit();
        return historicoVeiculo;
    }

    @Override
    public void deleteById(Long id) throws HistoricoVeiculoNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id, connection);
        connection.commit();
    }
}
