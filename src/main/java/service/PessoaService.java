package service;

import entity.Pessoa;
import exception.ClienteDaoException;
import exception.UnsupportedServiceOperationException;

import java.sql.SQLException;
import java.util.List;

public interface PessoaService {
    Pessoa create(Pessoa pessoa) throws UnsupportedServiceOperationException, SQLException, ClienteDaoException;

    List<Pessoa> findAll();

    Pessoa update(Pessoa pessoa) throws ClienteDaoException, SQLException;

    void deleteById(Long id) throws ClienteDaoException, SQLException;
}
