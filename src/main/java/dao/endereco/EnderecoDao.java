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
    Endereco save(Endereco endereco, Connection connection) throws UnsupportedServiceOperationException, SQLException, EnderecoNotSavedException;
    List<Endereco> readAll();
    Endereco update(Endereco endereco, Connection connection) throws SQLException, EnderecoNotFoundException;
    void deleteById(long id, Connection connection) throws SQLException, EnderecoNotFoundException;
}
