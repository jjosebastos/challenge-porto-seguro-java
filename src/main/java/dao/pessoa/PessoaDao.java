package dao;

import entity.Pessoa;
import exception.ClienteDaoException;
import exception.PessoaDaoNotSavedException;
import exception.PessoaNotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PessoaDao {
    void save(Pessoa pessoa, Connection connection) throws PessoaDaoNotSavedException, SQLException;

    List<Pessoa> readAll();

    void update(Pessoa pessoa) throws PessoaNotFoundException, SQLException;

    void deleteById(int id) throws PessoaNotFoundException, SQLException;

}
