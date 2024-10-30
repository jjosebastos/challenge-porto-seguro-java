package dao;

import entity.Autorizada;
import exception.AutorizadaDaoException;
import exception.AutorizadaNotFoundException;
import exception.AutorizadaNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AutorizadaDao {

    Autorizada save (Autorizada autorizada, Connection connection) throws AutorizadaNotSavedException, SQLException;
    List<Autorizada> readAll() ;
    void update(Autorizada Autorizada) throws AutorizadaNotFoundException, SQLException;
    void deleteById(int id) throws AutorizadaNotFoundException, SQLException;
}
