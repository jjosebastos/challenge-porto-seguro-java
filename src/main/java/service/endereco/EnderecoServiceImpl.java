package service.endereco;

import config.DatabaseConnectionFactory;
import dao.endereco.EnderecoDao;
import dao.endereco.EnderecoDaoFactory;
import entity.Endereco;
import exception.EnderecoNotFoundException;
import exception.EnderecoNotSavedException;
import exception.UnsupportedServiceOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoDao dao = EnderecoDaoFactory.create();

    @Override
    public Endereco create(Endereco endereco) throws SQLException, EnderecoNotSavedException, UnsupportedServiceOperationException {
        if(endereco.getIdEndereco() == null){
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                endereco = this.dao.save(endereco, connection);
                connection.commit();
                return  endereco;
            } catch (SQLException | EnderecoNotSavedException e){
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException();
        }
    }

    @Override
    public List<Endereco> findAll() {
        return this.dao.readAll();
    }

    @Override
    public Endereco update(Endereco endereco) throws SQLException, EnderecoNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        endereco = this.dao.update(endereco, connection);
        connection.commit();
        return endereco;
    }

    @Override
    public void deleteById(long id) throws SQLException, EnderecoNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id, connection);
        connection.commit();
    }
}
