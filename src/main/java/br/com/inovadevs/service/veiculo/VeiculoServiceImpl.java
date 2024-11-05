package br.com.inovadevs.service.veiculo;

import br.com.inovadevs.config.DatabaseConnectionFactory;
import br.com.inovadevs.dao.veiculo.VeiculoDao;
import br.com.inovadevs.dao.veiculo.VeiculoDaoFactory;
import br.com.inovadevs.entity.Veiculo;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;
import br.com.inovadevs.exception.VeiculoNotFoundException;
import br.com.inovadevs.exception.VeiculoNotSavedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VeiculoServiceImpl implements VeiculoService {
    private final VeiculoDao dao = VeiculoDaoFactory.create();


    @Override
    public Veiculo create(Veiculo veiculo) throws SQLException, VeiculoNotSavedException, UnsupportedServiceOperationException {
        if(veiculo.getIdVeiculo() == null){
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                veiculo = this.dao.create(veiculo,connection);
                connection.commit();
                return veiculo;
            } catch (SQLException | VeiculoNotSavedException e){
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException();
        }
    }

    @Override
    public List<Veiculo> findAll() {
        return this.dao.readAll();
    }

    @Override
    public Veiculo update(Veiculo veiculo) throws SQLException, VeiculoNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        veiculo = this.dao.update(veiculo, connection);
        connection.commit();
        return veiculo;
    }

    @Override
    public void deleteById(long id) throws SQLException, VeiculoNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id, connection);
        connection.commit();
    }
}
