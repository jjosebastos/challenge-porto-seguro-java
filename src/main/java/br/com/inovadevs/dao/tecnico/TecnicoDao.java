package br.com.inovadevs.dao.tecnico;

import br.com.inovadevs.entity.Tecnico;
import br.com.inovadevs.exception.TecnicoNotFoundException;
import br.com.inovadevs.exception.TecnicoNotSavedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TecnicoDao {

    Tecnico save(Tecnico tecnico, Connection connection) throws SQLException, TecnicoNotSavedException;
    List<Tecnico> readAll();
    Tecnico update(Tecnico tecnico, Connection connection) throws TecnicoNotFoundException, SQLException;
    void deleteById(Long id, Connection connection) throws TecnicoNotFoundException, SQLException;
}
