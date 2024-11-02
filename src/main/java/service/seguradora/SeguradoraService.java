package service.seguradora;

import entity.Seguradora;
import exception.SeguradoraNotFoundException;
import exception.SeguradoraNotSavedException;
import exception.UnsupportedServiceOperationException;

import java.sql.SQLException;
import java.util.List;

public interface SeguradoraService {

    Seguradora create(Seguradora seguradora) throws UnsupportedServiceOperationException, SQLException, SeguradoraNotSavedException;
    List<Seguradora> readAll();
    Seguradora update(Seguradora seguradora) throws SQLException, SeguradoraNotFoundException;
    void delete(long id) throws SQLException, SeguradoraNotFoundException;
}
