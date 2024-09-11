package dao;

import model.ClientePFisica;

import java.sql.SQLException;
import java.util.List;

public interface ClientePFisicaDAO {

    //TODO: CREATE
    void create(ClientePFisica pessoaFisica) throws SQLException;

    //TODO: READ
    List<ClientePFisica> readAll() throws SQLException;

    //TODO: UPDATE
    void update(ClientePFisica pessoaFisica) throws SQLException;

    //TODO: DELETE
    void delete(int id) throws SQLException;
}
