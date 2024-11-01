package service;

import entity.Autorizada;
import entity.HistoricoVeiculo;
import exception.*;

import java.sql.SQLException;
import java.util.List;

public interface HistoricoVeiculoService {
    HistoricoVeiculo create(HistoricoVeiculo historicoVeiculo) throws UnsupportedServiceOperationException, SQLException, HistoricoVeiculoNotSavedException;
    List<HistoricoVeiculo> findAll();
    HistoricoVeiculo update(HistoricoVeiculo historicoVeiculo) throws HistoricoVeiculoNotFoundException, SQLException;
    void deleteById(Long id) throws HistoricoVeiculoNotFoundException, SQLException;
}
