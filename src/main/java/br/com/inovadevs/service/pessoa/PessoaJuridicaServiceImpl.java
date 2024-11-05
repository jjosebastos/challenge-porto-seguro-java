package br.com.inovadevs.service.pessoa;

import br.com.inovadevs.config.DatabaseConnectionFactory;
import br.com.inovadevs.dao.pessoa.PessoaDao;
import br.com.inovadevs.dao.pessoa.PessoaDaoFactory;
import br.com.inovadevs.dao.pessoa.juridica.PessoaJuridicaDao;
import br.com.inovadevs.dao.pessoa.juridica.PessoaJuridicaDaoFactory;
import br.com.inovadevs.entity.Pessoa;
import br.com.inovadevs.entity.PessoaJuridica;
import br.com.inovadevs.exception.PessoaNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;
import exception.ConjuntoPessoaNotSavedException;
import exception.PessoaJuridicaNotFoundException;
import exception.PessoaJuridicaNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PessoaJuridicaServiceImpl implements PessoaJuridicaService {
    private final PessoaDao pessoaDao = PessoaDaoFactory.create();
    private final PessoaJuridicaDao pessoaJuridicaDao = PessoaJuridicaDaoFactory.create();

    @Override
    public PessoaJuridica create(PessoaJuridica pessoaJuridica) throws UnsupportedServiceOperationException, SQLException, ConjuntoPessoaNotSavedException {
        if (pessoaJuridica.getIdPessoa() != null) {
            throw new UnsupportedOperationException("ID já existe para pessoa física.");
        }

        try (Connection connection = DatabaseConnectionFactory.create().get()) {
            connection.setAutoCommit(false);

            Pessoa pessoa = new Pessoa(null, pessoaJuridica.getTipoCliente(), pessoaJuridica.getStatus());
            pessoa = this.pessoaDao.save(pessoa, connection);
            pessoaJuridica.setIdPessoa(pessoa.getIdPessoa());

            this.pessoaJuridicaDao.save(pessoaJuridica, connection);

            connection.commit();
            return pessoaJuridica;

        } catch (SQLException | PessoaNotSavedException | PessoaJuridicaNotSavedException e) {
            throw new ConjuntoPessoaNotSavedException();
        }
    }

    @Override
    public List<PessoaJuridica> readAll() {
        return this.pessoaJuridicaDao.readAll();
    }

    @Override
    public PessoaJuridica update(PessoaJuridica pessoaJuridica) throws PessoaJuridicaNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        pessoaJuridica = this.pessoaJuridicaDao.update(pessoaJuridica, connection);
        return pessoaJuridica;
    }

    @Override
    public void deleteById(long id) throws PessoaJuridicaNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.pessoaJuridicaDao.deleteById(id, connection);
    }
}
