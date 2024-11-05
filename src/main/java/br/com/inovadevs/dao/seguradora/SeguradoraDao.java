package br.com.inovadevs.dao.seguradora;

import br.com.inovadevs.entity.Seguradora;
import br.com.inovadevs.exception.SeguradoraNotFoundException;
import br.com.inovadevs.exception.SeguradoraNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SeguradoraDao {
    Seguradora create(Seguradora seguradora, Connection connection) throws SeguradoraNotSavedException, SQLException;
    List<Seguradora> readAll();
    Seguradora update(Seguradora seguradora, Connection connection) throws SeguradoraNotFoundException, SQLException;
    void deleteById(Long id, Connection connection) throws SQLException, SeguradoraNotFoundException;
}
