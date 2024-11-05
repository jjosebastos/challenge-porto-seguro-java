package br.com.inovadevs.service.veiculo;

import br.com.inovadevs.entity.Veiculo;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;
import br.com.inovadevs.exception.VeiculoNotFoundException;
import br.com.inovadevs.exception.VeiculoNotSavedException;

import java.sql.SQLException;
import java.util.List;

public interface VeiculoService {
    Veiculo create(Veiculo veiculo) throws SQLException, VeiculoNotSavedException, UnsupportedServiceOperationException;
    List<Veiculo> findAll();
    Veiculo update(Veiculo veiculo) throws SQLException, VeiculoNotFoundException;
    void deleteById(long id) throws SQLException, VeiculoNotFoundException;

}