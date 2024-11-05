package br.com.inovadevs.service.pessoa;

import br.com.inovadevs.entity.Pessoa;
import br.com.inovadevs.exception.PessoaNotFoundException;
import br.com.inovadevs.exception.PessoaNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;

import java.sql.SQLException;
import java.util.List;

public interface PessoaService {
    Pessoa create(Pessoa pessoa) throws UnsupportedServiceOperationException, SQLException, PessoaNotSavedException;
    List<Pessoa> findAll();
    Pessoa update(Pessoa pessoa) throws PessoaNotFoundException, SQLException;
    void deleteById(Long id) throws PessoaNotFoundException, SQLException;
}
