package dao;

import entity.ClientePFisica;
import entity.Pessoa;
import exception.PessoaDaoException;
import exception.PessoaFisicaDaoException;

import java.sql.SQLException;
import java.util.List;

public interface PessoaDao {

    //TODO: CREATE
    void create(Pessoa pessoa) throws SQLException, PessoaDaoException;

    //TODO: READ
    List<Pessoa> readAll() throws SQLException, PessoaDaoException;

    //TODO: UPDATE
    void update(Pessoa pessoa) throws  SQLException, PessoaDaoException;

    //TODO: DELETE
    void delete(int id) throws SQLException, PessoaDaoException;

}
