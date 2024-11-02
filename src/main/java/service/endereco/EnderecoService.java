package service.endereco;

import entity.Endereco;
import exception.*;

import java.sql.SQLException;
import java.util.List;

public interface EnderecoService {
    Endereco create(Endereco endereco) throws SQLException, EnderecoNotSavedException, UnsupportedServiceOperationException;
    List<Endereco> findAll();
    Endereco update(Endereco endereco) throws SQLException, EnderecoNotFoundException;
    void deleteById(long id) throws SQLException, EnderecoNotFoundException;
}
