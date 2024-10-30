package dao.endereco;

import entity.Endereco;
import exception.EnderecoDaoException;
import exception.EnderecoNotFoundException;
import exception.EnderecoNotSavedException;
import exception.UnsupportedServiceOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EnderecoDao {

    //TODO: CREATE
    Endereco create(Endereco endereco, Connection connection) throws UnsupportedServiceOperationException, SQLException, EnderecoNotSavedException;

    //TODO: READ
    List<Endereco> readAll();

    //TODO: UPDATE
    void update(Endereco endereco, Connection connection) throws SQLException, EnderecoNotFoundException;

    //TODO: DELETE
    void delete(int id, Connection connection) throws SQLException, EnderecoNotFoundException;
}
