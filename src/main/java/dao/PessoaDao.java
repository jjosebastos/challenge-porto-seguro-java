package dao;

import entity.Pessoa;
import exception.ClienteDaoException;

import java.sql.SQLException;
import java.util.List;

public interface PessoaDao {
    void save(Pessoa pessoa) throws ClienteDaoException;

    List<Pessoa> readAll();

    void update(Pessoa pessoa) throws ClienteDaoException;

    void deleteById(int id) throws ClienteDaoException;

}
