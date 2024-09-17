package dao;

import entity.Autorizada;
import exception.AutorizadaDaoException;

import java.sql.SQLException;
import java.util.List;

public interface AutorizadaDao {

    //TODO: CREATE
    void create(Autorizada autorizada) throws AutorizadaDaoException;

    //TODO: READ
    List<Autorizada> readAll() throws SQLException, AutorizadaDaoException;

    //TODO: UPDATE
    void update(Autorizada Autorizada) throws AutorizadaDaoException;

    //TODO: DELETE
    void delete(int id) throws AutorizadaDaoException;
}
