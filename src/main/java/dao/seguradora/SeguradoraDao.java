package dao.seguradora;

import entity.Seguradora;
import exception.SeguradoraNotFoundException;
import exception.SeguradoraNotSavedException;
import exception.UnsupportedServiceOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SeguradoraDao {
    Seguradora create(Seguradora seguradora, Connection connection) throws SeguradoraNotSavedException, SQLException, UnsupportedServiceOperationException;
    List<Seguradora> readAll();
    Seguradora update(Seguradora seguradora, Connection connection) throws SeguradoraNotFoundException, SQLException;
    void deleteById(Long id, Connection connection) throws SQLException, SeguradoraNotFoundException;
}
