package service.veiculo;

import entity.Veiculo;
import exception.UnsupportedServiceOperationException;
import exception.VeiculoNotFoundException;
import exception.VeiculoNotSavedException;

import java.sql.SQLException;
import java.util.List;

public interface VeiculoService {
    Veiculo create(Veiculo veiculo) throws SQLException, VeiculoNotSavedException, UnsupportedServiceOperationException;
    List<Veiculo> findAll();
    Veiculo update(Veiculo veiculo) throws SQLException, VeiculoNotFoundException;
    void deleteById(long id) throws SQLException, VeiculoNotFoundException;

}