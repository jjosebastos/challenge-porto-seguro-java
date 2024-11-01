package dao.tecnico;

import entity.Tecnico;
import exception.TecnicoNotFoundException;
import exception.TecnicoNotSavedException;
import exception.UnsupportedServiceOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TecnicoDao {

    Tecnico save(Tecnico tecnico, Connection connection) throws UnsupportedServiceOperationException, SQLException, TecnicoNotSavedException;
    List<Tecnico> readAll();
    Tecnico update(Tecnico tecnico, Connection connection) throws TecnicoNotFoundException, SQLException;
    void deleteById(Long id, Connection connection) throws TecnicoNotFoundException, SQLException;
}
