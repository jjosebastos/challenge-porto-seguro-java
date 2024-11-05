package br.com.inovadevs.dao.historico;

import br.com.inovadevs.entity.HistoricoVeiculo;
import br.com.inovadevs.exception.HistoricoVeiculoNotFoundException;
import br.com.inovadevs.exception.HistoricoVeiculoNotSavedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface HistoricoVeiculoDao {
    HistoricoVeiculo create(HistoricoVeiculo historicoVeiculo, Connection connection) throws HistoricoVeiculoNotSavedException, SQLException;
    List<HistoricoVeiculo> readAll();
    HistoricoVeiculo update(HistoricoVeiculo historicoVeiculo, Connection connection) throws HistoricoVeiculoNotFoundException,SQLException;
    void deleteById (Long id, Connection connection) throws HistoricoVeiculoNotFoundException, SQLException;
}
