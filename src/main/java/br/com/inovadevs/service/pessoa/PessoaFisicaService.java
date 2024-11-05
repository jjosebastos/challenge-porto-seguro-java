package br.com.inovadevs.service.pessoa;

import br.com.inovadevs.entity.PessoaFisica;
import br.com.inovadevs.exception.PessoaNotSavedException;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;
import exception.ConjuntoPessoaNotSavedException;
import exception.PessoaFisicaNotFoundException;


import java.sql.SQLException;
import java.util.List;

public interface PessoaFisicaService {

    PessoaFisica create(PessoaFisica pessoaFisica) throws  SQLException, UnsupportedServiceOperationException, ConjuntoPessoaNotSavedException;
    List<PessoaFisica> readAll();
    PessoaFisica update(PessoaFisica pessoaFisica) throws PessoaFisicaNotFoundException, SQLException;
    void deleteById(long id) throws PessoaFisicaNotFoundException,  SQLException;
}
