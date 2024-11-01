package dao.veiculo;

import entity.Veiculo;
import exception.UnsupportedServiceOperationException;
import exception.VeiculoDaoException;
import exception.VeiculoNotFoundException;
import exception.VeiculoNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface VeiculoDao {
    Veiculo create(Veiculo veiculo, Connection connection) throws UnsupportedServiceOperationException, SQLException, VeiculoNotSavedException;
    List<Veiculo> readAll();
    Veiculo update(Veiculo veiculo, Connection connection) throws  SQLException, VeiculoNotFoundException;
    void deleteById(long id, Connection connection) throws  SQLException, VeiculoNotFoundException;

}

