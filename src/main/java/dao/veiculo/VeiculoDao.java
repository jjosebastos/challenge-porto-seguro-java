package dao.veiculo;

import entity.Veiculo;
import exception.VeiculoDaoException;

import java.util.List;

public interface VeiculoDao {

    //TODO: CREATE
    void create(Veiculo veiculo) throws VeiculoDaoException;

    //TODO: READ
    List<Veiculo> readAll() throws VeiculoDaoException;

    //TODO: UPDATE
    void update(Veiculo veiculo) throws  VeiculoDaoException;

    //TODO: DELETE
    void delete(int id) throws  VeiculoDaoException;

    class ClienteDao {
    }
}

