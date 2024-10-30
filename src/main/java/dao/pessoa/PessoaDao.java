package dao.pessoa;

import entity.Pessoa;
import exception.PessoaNotSavedException;
import exception.PessoaNotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PessoaDao {
    Pessoa save(Pessoa pessoa, Connection connection) throws PessoaNotSavedException, SQLException;
    List<Pessoa> readAll();
    Pessoa update(Pessoa pessoa, Connection connection) throws PessoaNotFoundException, SQLException;
    void deleteById(Long id, Connection connection) throws PessoaNotFoundException, SQLException;

}
