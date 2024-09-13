package dao;

import exception.PessoaFisicaDaoException;
import entity.ClientePFisica;

import java.sql.SQLException;
import java.util.List;

public interface ClientePFisicaDao {

    //TODO: CREATE
    //void create(ClientePFisica pessoaFisica) throws PessoaFisicaDaoException, SQLException;
    void insertTransactioned(ClientePFisica pessoaFisica) throws PessoaFisicaDaoException, SQLException;

    //TODO: READ
    List<ClientePFisica> readAll() throws PessoaFisicaDaoException, SQLException;

    //TODO: UPDATE
    void update(ClientePFisica pessoaFisica) throws PessoaFisicaDaoException, SQLException;

    //TODO: DELETE
    void delete(int id) throws PessoaFisicaDaoException, SQLException;
}
