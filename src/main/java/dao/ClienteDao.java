package dao;

import entity.Autorizada;
import entity.Cliente;
import exception.AutorizadaDaoException;
import exception.ClienteDaoException;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDao {

    //TODO: CREATE
    void create(Cliente cliente) throws ClienteDaoException;

    //TODO: READ
    List<Cliente> readAll() throws SQLException, ClienteDaoException;

    //TODO: UPDATE
    void update(Cliente cliente) throws ClienteDaoException;

    //TODO: DELETE
    void delete(int id) throws ClienteDaoException;
}
