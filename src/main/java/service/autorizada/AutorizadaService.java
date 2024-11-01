package service.autorizada;

import entity.Autorizada;
import exception.AutorizadaNotFoundException;
import exception.AutorizadaNotSavedException;
import exception.UnsupportedServiceOperationException;

import java.sql.SQLException;
import java.util.List;

public interface AutorizadaService {
    Autorizada create(Autorizada autorizada) throws UnsupportedServiceOperationException, SQLException, AutorizadaNotSavedException;
    List<Autorizada> findAll();
    Autorizada update(Autorizada autorizada) throws AutorizadaNotFoundException, SQLException;
    void deleteById(Long id) throws AutorizadaNotFoundException, SQLException;
}
