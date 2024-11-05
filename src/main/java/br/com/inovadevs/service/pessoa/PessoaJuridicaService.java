package br.com.inovadevs.service.pessoa;

import br.com.inovadevs.entity.PessoaJuridica;
import br.com.inovadevs.exception.UnsupportedServiceOperationException;
import exception.ConjuntoPessoaNotSavedException;
import exception.PessoaJuridicaNotFoundException;
import exception.PessoaJuridicaNotSavedException;

import java.sql.SQLException;
import java.util.List;

public interface PessoaJuridicaService {
    PessoaJuridica create(PessoaJuridica pessoaJuridica) throws UnsupportedServiceOperationException, SQLException, ConjuntoPessoaNotSavedException;
    List<PessoaJuridica> readAll();
    PessoaJuridica update(PessoaJuridica pessoaJuridica) throws PessoaJuridicaNotFoundException, SQLException;
    void deleteById(long id) throws PessoaJuridicaNotFoundException, SQLException;
}
