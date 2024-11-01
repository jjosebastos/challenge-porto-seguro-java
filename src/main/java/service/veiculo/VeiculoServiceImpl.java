package service.veiculo;

import config.DatabaseConnectionFactory;
import dao.veiculo.VeiculoDao;
import dao.veiculo.VeiculoDaoFactory;
import entity.Veiculo;
import exception.UnsupportedServiceOperationException;
import exception.VeiculoNotFoundException;
import exception.VeiculoNotSavedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VeiculoServiceImpl implements VeiculoService {
    private final VeiculoDao dao = VeiculoDaoFactory.create();


    @Override
    public Veiculo create(Veiculo veiculo) throws SQLException, VeiculoNotSavedException, UnsupportedServiceOperationException {
        if(veiculo.getIdVeiculo() == 0){
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
