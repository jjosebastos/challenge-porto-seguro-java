package br.com.inovadevs.service.tecnico;

import br.com.inovadevs.entity.Tecnico;
import br.com.inovadevs.exception.TecnicoNotFoundException;
import br.com.inovadevs.exception.TecnicoNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;

import java.sql.SQLException;
import java.util.List;

public interface TecnicoService {
    Tecnico create (Tecnico tecnico) throws TecnicoNotSavedException, UnsupportedServiceOperationException, SQLException;
    List<Tecnico> readAll();
    Tecnico update(Tecnico tecnico) throws TecnicoNotFoundException, SQLException;
    void deleteById(Long id) throws TecnicoNotFoundException, SQLException;
}
