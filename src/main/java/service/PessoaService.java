package service;

import entity.Pessoa;
import exception.ClienteDaoException;
import exception.PessoaNotFoundException;
import exception.PessoaNotSavedException;
import exception.UnsupportedServiceOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PessoaService {
    Pessoa create(Pessoa pessoa) throws UnsupportedServiceOperationException, SQLException, PessoaNotSavedException;
    List<Pessoa> findAll();
    Pessoa update(Pessoa pessoa) throws PessoaNotFoundException, SQLException;
    void deleteById(Long id) throws PessoaNotFoundException, SQLException;
}
