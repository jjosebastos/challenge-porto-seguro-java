package dao;

import entity.Tecnico;
import exception.TecnicoDaoException;

import java.sql.SQLException;
import java.util.List;

public interface TecnicoDao {

    //TODO: CREATE
    void create(Tecnico tecnico) throws TecnicoDaoException;

    //TODO: READ
    List<Tecnico> readAll() throws SQLException, TecnicoDaoException;

    //TODO: UPDATE
    void update(Tecnico tecnico) throws TecnicoDaoException;

    //TODO: DELETE
    void delete(int id) throws TecnicoDaoException;
}
