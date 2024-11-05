package br.com.inovadevs.dao.telefone;

import br.com.inovadevs.entity.Telefone;
import br.com.inovadevs.exception.TelefoneNotFoundException;
import br.com.inovadevs.exception.TelefoneNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TelefoneDao {

    Telefone save(Telefone telefone, Connection connection) throws SQLException, TelefoneNotSavedException;
    List<Telefone> readAll()  ;
    Telefone update(Telefone telefone, Connection connection) throws TelefoneNotFoundException, SQLException;
    void delete(Long id, Connection connection) throws TelefoneNotFoundException, SQLException;
}
