package br.com.inovadevs.service.telefone;

import br.com.inovadevs.config.DatabaseConnectionFactory;
import br.com.inovadevs.dao.telefone.TelefoneDao;
import br.com.inovadevs.dao.telefone.TelefoneDaoFactory;
import br.com.inovadevs.entity.Telefone;
import br.com.inovadevs.exception.TelefoneNotFoundException;
import br.com.inovadevs.exception.TelefoneNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TelefoneServiceImpl implements TelefoneService {

    private final TelefoneDao dao = TelefoneDaoFactory.create();

    @Override
    public Telefone create(Telefone telefone) throws UnsupportedServiceOperationException, SQLException, TelefoneNotSavedException {
        if(telefone.getIdTelefone() == null){
            Connection conn = DatabaseConnectionFactory.create().get();
            try {
                telefone = this.dao.save(telefone, conn);
                conn.commit();
                return telefone;
            } catch (SQLException | TelefoneNotSavedException e) {
                conn.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException();
        }
    }

    @Override
    public List<Telefone> readAll() {
        return this.dao.readAll();
    }

    @Override
    public Telefone update(Telefone telefone) throws TelefoneNotFoundException, SQLException {
        Connection conn = DatabaseConnectionFactory.create().get();
        telefone = this.dao.update(telefone, conn);
        conn.commit();
        return telefone;
    }

    @Override
    public void delete(Long id) throws TelefoneNotFoundException, SQLException {
        Connection conn = DatabaseConnectionFactory.create().get();
        this.dao.delete(id, conn);
        conn.commit();
    }
}
