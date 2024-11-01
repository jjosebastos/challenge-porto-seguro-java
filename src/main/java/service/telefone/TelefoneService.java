package service.telefone;

import entity.Telefone;
import exception.TelefoneNotFoundException;
import exception.TelefoneNotSavedException;
import exception.UnsupportedServiceOperationException;

import java.sql.SQLException;
import java.util.List;

public interface TelefoneService {

    Telefone create(Telefone telefone) throws UnsupportedServiceOperationException, SQLException, TelefoneNotSavedException;
    List<Telefone> readAll();
    Telefone update(Telefone telefone) throws TelefoneNotFoundException, SQLException;
    void delete(Long id) throws TelefoneNotFoundException, SQLException;

}
