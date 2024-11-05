package br.com.inovadevs.dao.endereco;

import br.com.inovadevs.entity.Endereco;
import br.com.inovadevs.exception.EnderecoNotFoundException;
import br.com.inovadevs.exception.EnderecoNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EnderecoDao {
    Endereco save(Endereco endereco, Connection connection) throws  SQLException, EnderecoNotSavedException;
    List<Endereco> readAll();
    Endereco update(Endereco endereco, Connection connection) throws SQLException, EnderecoNotFoundException;
    void deleteById(long id, Connection connection) throws SQLException, EnderecoNotFoundException;
}
