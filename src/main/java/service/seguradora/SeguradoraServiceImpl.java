package service.seguradora;

import config.DatabaseConnectionFactory;
import dao.seguradora.SeguradoraDao;
import dao.seguradora.SeguradoraDaoFactory;
import dao.seguradora.SeguradoraDaoImpl;
import entity.Seguradora;
import exception.SeguradoraNotFoundException;
import exception.SeguradoraNotSavedException;
import exception.UnsupportedServiceOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SeguradoraServiceImpl implements SeguradoraService {

    private final SeguradoraDao dao = SeguradoraDaoFactory.create();
    @Override
    public Seguradora create(Seguradora seguradora) throws UnsupportedServiceOperationException, SQLException, SeguradoraNotSavedException {
        if(seguradora.getIdSeguradora() == null){
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                seguradora = this.dao.create(seguradora, connection);
                connection.commit();
                return seguradora;
            } catch (SQLException | SeguradoraNotSavedException e) {
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException();
        }

    }

    @Override
    public List<Seguradora> readAll() {
        return this.dao.readAll();
    }

    @Override
    public Seguradora update(Seguradora seguradora) throws SQLException, SeguradoraNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        seguradora = this.dao.update(seguradora, connection);
        connection.commit();
        return seguradora;
    }

    @Override
    public void delete(long id) throws SQLException, SeguradoraNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id, connection);
        connection.commit();
    }
}
