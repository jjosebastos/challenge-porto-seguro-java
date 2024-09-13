package dao;

import entity.ClientePFisica;
import entity.Veiculo;
import exception.PessoaFisicaDaoException;

import java.sql.SQLException;
import java.util.List;

public interface VeiculoDao {

    //TODO: CREATE
    void create(Veiculo veiculo) throws SQLException;

    //TODO: READ
    List<Veiculo> readAll() throws SQLException;

    //TODO: UPDATE
    void update(Veiculo veiculo) throws  SQLException;

    //TODO: DELETE
    void delete(int id) throws  SQLException;
}

