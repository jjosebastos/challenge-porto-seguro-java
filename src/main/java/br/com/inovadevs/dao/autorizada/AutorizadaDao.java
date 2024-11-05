package br.com.inovadevs.dao.autorizada;

import br.com.inovadevs.entity.Autorizada;
import br.com.inovadevs.exception.AutorizadaNotFoundException;
import br.com.inovadevs.exception.AutorizadaNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AutorizadaDao {

    Autorizada save (Autorizada autorizada, Connection connection) throws AutorizadaNotSavedException, SQLException;
    List<Autorizada> readAll() ;
    Autorizada update(Autorizada Autorizada, Connection connection) throws AutorizadaNotFoundException, SQLException;
    void deleteById(long id, Connection connection) throws AutorizadaNotFoundException, SQLException;
}
