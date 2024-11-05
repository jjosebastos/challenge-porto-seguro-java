package br.com.inovadevs.service.telefone;

import br.com.inovadevs.entity.Telefone;
import br.com.inovadevs.exception.TelefoneNotFoundException;
import br.com.inovadevs.exception.TelefoneNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;

import java.sql.SQLException;
import java.util.List;

public interface TelefoneService {

    Telefone create(Telefone telefone) throws UnsupportedServiceOperationException, SQLException, TelefoneNotSavedException;
    List<Telefone> readAll();
    Telefone update(Telefone telefone) throws TelefoneNotFoundException, SQLException;
    void delete(Long id) throws TelefoneNotFoundException, SQLException;

}
