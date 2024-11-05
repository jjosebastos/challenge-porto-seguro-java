package br.com.inovadevs.dao.pessoa.juridica;

import br.com.inovadevs.entity.PessoaJuridica;
import exception.PessoaFisicaNotFoundException;
import exception.PessoaFisicaNotSavedException;
import exception.PessoaJuridicaNotFoundException;
import exception.PessoaJuridicaNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PessoaJuridicaDao {
    PessoaJuridica save(PessoaJuridica pessoaJuridica, Connection connection) throws PessoaJuridicaNotSavedException, SQLException;
    List<PessoaJuridica> readAll();
    PessoaJuridica update(PessoaJuridica pessoaJuridica, Connection connection) throws PessoaJuridicaNotFoundException, SQLException;
    void deleteById(long id, Connection connection) throws PessoaJuridicaNotFoundException, SQLException;
}
