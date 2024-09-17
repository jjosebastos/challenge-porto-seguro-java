package exception;

import dao.TelefoneDao;
import entity.Telefone;

import java.sql.SQLException;
import java.util.List;

public class TelefoneDaoImpl implements TelefoneDao {
    @Override
    public void create(Telefone telefone) throws TelefoneDaoException {

    }

    @Override
    public List<Telefone> readAll() throws SQLException, TelefoneDaoException {
        return List.of();
    }

    @Override
    public void update(Telefone telefone) throws TelefoneDaoException {

    }

    @Override
    public void delete(int id) throws TelefoneDaoException {

    }
}
