package dao.telefone;

import entity.Telefone;
import exception.TelefoneNotFoundException;
import exception.TelefoneNotSavedException;
import exception.UnsupportedServiceOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TelefoneDao {

    Telefone save(Telefone telefone, Connection connection) throws UnsupportedServiceOperationException, SQLException, TelefoneNotSavedException;
    List<Telefone> readAll()  ;
    Telefone update(Telefone telefone, Connection connection) throws TelefoneNotFoundException, SQLException;
    void delete(Long id, Connection connection) throws TelefoneNotFoundException, SQLException;
}
