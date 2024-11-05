package br.com.inovadevs.service.pessoa;

import br.com.inovadevs.config.DatabaseConnectionFactory;
import br.com.inovadevs.dao.pessoa.PessoaDao;
import br.com.inovadevs.dao.pessoa.PessoaDaoFactory;
import br.com.inovadevs.dao.pessoa.fisica.PessoaFisicaDao;
import br.com.inovadevs.dao.pessoa.fisica.PessoaFisicaDaoFactory;
import br.com.inovadevs.entity.Pessoa;
import br.com.inovadevs.entity.PessoaFisica;
import br.com.inovadevs.exception.PessoaNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;
import exception.ConjuntoPessoaNotSavedException;
import exception.PessoaFisicaNotFoundException;
import exception.PessoaFisicaNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;

public class PessoaFisicaServiceImpl implements PessoaFisicaService {

    private PessoaDao pessoaDao = PessoaDaoFactory.create();
    private PessoaFisicaDao pessoaFisicaDao =  PessoaFisicaDaoFactory.create();


    @Override
    public PessoaFisica create(PessoaFisica pessoaFisica)
            throws SQLException,  UnsupportedServiceOperationException, ConjuntoPessoaNotSavedException {

        if (pessoaFisica.getIdPessoa() != null) {
            throw new UnsupportedOperationException("ID já existe para pessoa física.");
        }

        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            conn.setAutoCommit(false);

            Pessoa pessoa = new Pessoa(null, pessoaFisica.getTipoCliente(), pessoaFisica.getStatus());
            pessoa = this.pessoaDao.save(pessoa, conn);
            pessoaFisica.setIdPessoa(pessoa.getIdPessoa());

            this.pessoaFisicaDao.save(pessoaFisica, conn);

            conn.commit();
            return pessoaFisica;

        } catch (SQLException | PessoaNotSavedException | PessoaFisicaNotSavedException e) {
            throw new ConjuntoPessoaNotSavedException();
        }

    }
    @Override
    public List<PessoaFisica> readAll() {
        return this.pessoaFisicaDao.readAll();
    }

    @Override
    public PessoaFisica update(PessoaFisica pessoaFisica) throws PessoaFisicaNotFoundException, SQLException {
        Connection conn = DatabaseConnectionFactory.create().get();
        pessoaFisica = this.pessoaFisicaDao.update(pessoaFisica,conn);
        conn.commit();
        return pessoaFisica;
    }

    @Override
    public void deleteById(long id) throws PessoaFisicaNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.pessoaFisicaDao.deleteById(id,connection);
        connection.commit();
    }
}
