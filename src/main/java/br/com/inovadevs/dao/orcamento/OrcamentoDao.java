package br.com.inovadevs.dao.orcamento;

import br.com.inovadevs.entity.Orcamento;
import br.com.inovadevs.exception.OrcamentoNotFoundException;
import br.com.inovadevs.exception.OrcamentoNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrcamentoDao {
    Orcamento save(Orcamento orcamento, Connection connection) throws SQLException, OrcamentoNotSavedException;
    List<Orcamento> findAll();
    Orcamento update(Orcamento orcamento, Connection connection) throws SQLException, OrcamentoNotFoundException;
    void delete(long id, Connection connection) throws SQLException, OrcamentoNotFoundException;
}
