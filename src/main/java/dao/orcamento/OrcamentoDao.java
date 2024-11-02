package dao.orcamento;

import entity.Orcamento;
import exception.OrcamentoNotFoundException;
import exception.OrcamentoNotSavedException;
import exception.UnsupportedServiceOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrcamentoDao {
    Orcamento save(Orcamento orcamento, Connection connection) throws SQLException, UnsupportedServiceOperationException, OrcamentoNotSavedException;
    List<Orcamento> findAll();
    Orcamento update(Orcamento orcamento, Connection connection) throws SQLException, OrcamentoNotFoundException;
    void delete(long id, Connection connection) throws SQLException, OrcamentoNotFoundException;
}
