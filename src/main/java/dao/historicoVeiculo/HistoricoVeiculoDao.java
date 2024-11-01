package dao.historicoVeiculo;

import entity.HistoricoVeiculo;
import exception.HistoricoVeiculoDaoException;
import exception.HistoricoVeiculoNotFoundException;
import exception.HistoricoVeiculoNotSavedException;
import exception.UnsupportedServiceOperationException;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface HistoricoVeiculoDao {
    HistoricoVeiculo create(HistoricoVeiculo historicoVeiculo, Connection connection) throws HistoricoVeiculoNotSavedException, SQLException, UnsupportedServiceOperationException;
    List<HistoricoVeiculo> readAll();
    HistoricoVeiculo update(HistoricoVeiculo historicoVeiculo, Connection connection) throws HistoricoVeiculoNotFoundException,SQLException;
    void deleteById (Long id, Connection connection) throws HistoricoVeiculoNotFoundException, SQLException;
}
