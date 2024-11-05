package br.com.inovadevs.service.seguradora;

import br.com.inovadevs.entity.Seguradora;
import br.com.inovadevs.exception.SeguradoraNotFoundException;
import br.com.inovadevs.exception.SeguradoraNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;

import java.sql.SQLException;
import java.util.List;

public interface SeguradoraService {

    Seguradora create(Seguradora seguradora) throws UnsupportedServiceOperationException, SQLException, SeguradoraNotSavedException;
    List<Seguradora> readAll();
    Seguradora update(Seguradora seguradora) throws SQLException, SeguradoraNotFoundException;
    void delete(long id) throws SQLException, SeguradoraNotFoundException;
}
