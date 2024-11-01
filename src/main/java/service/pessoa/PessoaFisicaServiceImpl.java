package service.pessoa;

import entity.Pessoa;
import entity.PessoaFisica;
import exception.ClienteDaoException;
import exception.UnsupportedServiceOperationException;

import java.sql.SQLException;
import java.util.List;

public class PessoaFisicaServiceImpl implements PessoaFisicaService {
    @Override
    public Pessoa create(PessoaFisica pessoaFisica) throws UnsupportedServiceOperationException, SQLException, ClienteDaoException {
        return null;
    }

    @Override
    public List<PessoaFisica> findAll() {
        return List.of();
    }

    @Override
    public Pessoa update(PessoaFisica pessoaFisica) throws ClienteDaoException, SQLException {
        return null;
    }

    @Override
    public void deleteById(Long id) throws ClienteDaoException, SQLException {

    }
}
