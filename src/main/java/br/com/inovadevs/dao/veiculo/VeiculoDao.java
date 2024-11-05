package br.com.inovadevs.dao.veiculo;

import br.com.inovadevs.entity.Veiculo;
import br.com.inovadevs.exception.VeiculoNotFoundException;
import br.com.inovadevs.exception.VeiculoNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface VeiculoDao {
    Veiculo create(Veiculo veiculo, Connection connection) throws  SQLException, VeiculoNotSavedException;
    List<Veiculo> readAll();
    Veiculo update(Veiculo veiculo, Connection connection) throws  SQLException, VeiculoNotFoundException;
    void deleteById(long id, Connection connection) throws  SQLException, VeiculoNotFoundException;

}

