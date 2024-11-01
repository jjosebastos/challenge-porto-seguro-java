package dao.seguradora;

import entity.Seguradora;
import exception.SeguradoraDaoException;
import exception.SeguradoraNotFoundException;
import exception.SeguradoraNotSavedException;
import exception.UnsupportedServiceOperationException;
import oracle.jdbc.internal.OracleStatement;

import javax.ws.rs.container.ConnectionCallback;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SeguradoraDao {
    Seguradora create(Seguradora seguradora, Connection connection) throws SeguradoraNotSavedException, SQLException, UnsupportedServiceOperationException;
    List<Seguradora> readAll();
    void update(Seguradora Seguradora, Connection connection) throws SeguradoraNotFoundException, SQLException;
    void deleteById(Long id, Connection connection) throws SQLException, SeguradoraNotFoundException;
}
