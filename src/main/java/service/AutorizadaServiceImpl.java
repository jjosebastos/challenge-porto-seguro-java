package service;

import config.DatabaseConnectionFactory;
import dao.autorizada.AutorizadaDao;
import dao.autorizada.AutorizadaDaoFactory;
import entity.Autorizada;
import exception.AutorizadaNotFoundException;
import exception.AutorizadaNotSavedException;
import exception.UnsupportedServiceOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AutorizadaServiceImpl implements AutorizadaService {

    private final AutorizadaDao dao = AutorizadaDaoFactory.create();

    @Override
    public Autorizada create(Autorizada autorizada) throws UnsupportedServiceOperationException, SQLException, AutorizadaNotSavedException {
        if(autorizada.getIdAutorizada() == null){
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                autorizada = this.dao.save(autorizada, connection);
                connection.commit();
                return autorizada;

            } catch ( SQLException | AutorizadaNotSavedException e) {
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException();
        }
    }

    @Override
    public List<Autorizada> findAll() {
        return this.dao.readAll();
    }

    @Override
    public Autorizada update(Autorizada autorizada) throws AutorizadaNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        autorizada = this.dao.update(autorizada, connection);
        connection.commit();
        return autorizada;
    }

    @Override
    public void deleteById(Long id) throws AutorizadaNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id, connection);;
    }
}
