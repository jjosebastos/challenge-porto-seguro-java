package br.com.inovadevs.service.pessoa;

import br.com.inovadevs.config.DatabaseConnectionFactory;
import br.com.inovadevs.dao.pessoa.PessoaDao;
import br.com.inovadevs.dao.pessoa.PessoaDaoFactory;
import br.com.inovadevs.entity.Pessoa;
import br.com.inovadevs.exception.PessoaNotFoundException;
import br.com.inovadevs.exception.PessoaNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PessoaServiceImpl implements PessoaService {

    private final PessoaDao dao = PessoaDaoFactory.create();


    @Override
    public Pessoa create(Pessoa pessoa) throws UnsupportedServiceOperationException, SQLException, PessoaNotSavedException {
        if(pessoa.getIdPessoa() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                pessoa = this.dao.save(pessoa, connection);
                connection.commit();
                return pessoa;

            }catch (SQLException | PessoaNotSavedException e){
                connection.rollback();
                throw e;
            }

        } else {
            throw new PessoaNotSavedException();
        }
    }

    @Override
    public List<Pessoa> findAll() {
        return this.dao.readAll();
    }

    @Override
    public Pessoa update(Pessoa pessoa) throws PessoaNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        pessoa = this.dao.update(pessoa, connection);
        connection.commit();
        return pessoa;
    }

    @Override
    public void deleteById(Long id) throws PessoaNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id, connection);
    }
}
