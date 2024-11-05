package br.com.inovadevs.service.historico;

import br.com.inovadevs.entity.HistoricoVeiculo;
import br.com.inovadevs.exception.*;

import java.sql.SQLException;
import java.util.List;

public interface HistoricoVeiculoService {
    HistoricoVeiculo create(HistoricoVeiculo historicoVeiculo) throws UnsupportedServiceOperationException, SQLException, HistoricoVeiculoNotSavedException;
    List<HistoricoVeiculo> findAll();
    HistoricoVeiculo update(HistoricoVeiculo historicoVeiculo) throws HistoricoVeiculoNotFoundException, SQLException;
    void deleteById(Long id) throws HistoricoVeiculoNotFoundException, SQLException;
}
