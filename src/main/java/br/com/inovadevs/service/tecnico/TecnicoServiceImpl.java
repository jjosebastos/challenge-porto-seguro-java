package br.com.inovadevs.service.tecnico;

import br.com.inovadevs.config.DatabaseConnectionFactory;
import br.com.inovadevs.dao.tecnico.TecnicoDao;
import br.com.inovadevs.dao.tecnico.TecnicoDaoFactory;
import br.com.inovadevs.entity.Tecnico;
import br.com.inovadevs.exception.TecnicoNotFoundException;
import br.com.inovadevs.exception.TecnicoNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TecnicoServiceImpl implements TecnicoService {

    private TecnicoDao dao = TecnicoDaoFactory.create();

    @Override
    public Tecnico create(Tecnico tecnico) throws TecnicoNotSavedException, UnsupportedServiceOperationException, SQLException {
        if(tecnico.getIdTecnico() == null){
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                tecnico = this.dao.save(tecnico, connection);
                connection.commit();
                return tecnico;
            } catch (SQLException | TecnicoNotSavedException e) {
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException();
        }
    }

    @Override
    public List<Tecnico> readAll() {
        return this.dao.readAll();
    }

    @Override
    public Tecnico update(Tecnico tecnico) throws TecnicoNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        tecnico = this.dao.update(tecnico, connection);
        connection.commit();
        return tecnico;
    }

    @Override
    public void deleteById(Long id) throws TecnicoNotFoundException, SQLException {
        Connection conn = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id, conn);
    }
}
