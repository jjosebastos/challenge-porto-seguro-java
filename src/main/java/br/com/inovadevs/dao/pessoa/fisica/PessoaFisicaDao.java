package br.com.inovadevs.dao.pessoa.fisica;
import br.com.inovadevs.entity.PessoaFisica;
import exception.PessoaFisicaNotFoundException;
import exception.PessoaFisicaNotSavedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PessoaFisicaDao {
    PessoaFisica save(PessoaFisica pessoaFisica, Connection connection) throws PessoaFisicaNotSavedException, SQLException;
    List<PessoaFisica> readAll();
    PessoaFisica update(PessoaFisica pessoaFisica, Connection connection) throws PessoaFisicaNotFoundException, SQLException;
    void deleteById(Long id, Connection connection) throws PessoaFisicaNotFoundException, SQLException;

}
