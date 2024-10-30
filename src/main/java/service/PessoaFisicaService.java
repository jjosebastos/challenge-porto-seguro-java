package service;

import entity.Pessoa;
import entity.PessoaFisica;
import exception.ClienteDaoException;
import exception.UnsupportedServiceOperationException;

import java.sql.SQLException;
import java.util.List;

public interface PessoaFisicaService {
    Pessoa create(PessoaFisica pessoaFisica) throws UnsupportedServiceOperationException, SQLException, ClienteDaoException;
    List<PessoaFisica> findAll();
    Pessoa update(PessoaFisica pessoaFisica) throws ClienteDaoException, SQLException;
    void deleteById(Long id) throws ClienteDaoException, SQLException;
}
