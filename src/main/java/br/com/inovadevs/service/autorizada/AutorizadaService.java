package br.com.inovadevs.service.autorizada;

import br.com.inovadevs.entity.Autorizada;
import br.com.inovadevs.exception.AutorizadaNotFoundException;
import br.com.inovadevs.exception.AutorizadaNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;

import java.sql.SQLException;
import java.util.List;

public interface AutorizadaService {
    Autorizada create(Autorizada autorizada) throws UnsupportedServiceOperationException, SQLException, AutorizadaNotSavedException;
    List<Autorizada> findAll();
    Autorizada update(Autorizada autorizada) throws AutorizadaNotFoundException, SQLException;
    void deleteById(Long id) throws AutorizadaNotFoundException, SQLException;
}
