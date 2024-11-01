package service.tecnico;

import entity.Tecnico;
import exception.TecnicoNotFoundException;
import exception.TecnicoNotSavedException;
import exception.UnsupportedServiceOperationException;

import java.sql.SQLException;
import java.util.List;

public interface TecnicoService {
    Tecnico create (Tecnico tecnico) throws TecnicoNotSavedException, UnsupportedServiceOperationException, SQLException;
    List<Tecnico> readAll();
    Tecnico update(Tecnico tecnico) throws TecnicoNotFoundException, SQLException;
    void deleteById(Long id) throws TecnicoNotFoundException, SQLException;
}
