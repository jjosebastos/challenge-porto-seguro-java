package br.com.inovadevs.dao.pessoa.fisica;

import br.com.inovadevs.config.DatabaseConnectionFactory;
import br.com.inovadevs.entity.PessoaFisica;
import exception.PessoaFisicaNotFoundException;
import exception.PessoaFisicaNotSavedException;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PessoaFisicaDaoImpl implements PessoaFisicaDao {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public PessoaFisica save(PessoaFisica pessoaFisica, Connection connection) throws  PessoaFisicaNotSavedException, SQLException {
        final String sql =
                """
                BEGIN 
                    INSERT INTO T_CON_PESSOA_FISICA (NM_PESSOA, DT_NASCIMENTO, NR_CPF, DS_GENERO, ID_PESSOA) 
                    VALUES (?,?,?,?,?) RETURNING ID INTO ?; 
                END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, pessoaFisica.getNome());
        call.setObject(2, pessoaFisica.getDataNascimento());
        call.setString(3, pessoaFisica.getCpf());
        call.setString(4, pessoaFisica.getGenero());
        call.setLong(5, pessoaFisica.getIdPessoa());
        call.registerOutParameter(6, OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(6);

        if( linhasAlteradas == 0 || id == 0 ) {
            throw new PessoaFisicaNotSavedException();
        }
        return pessoaFisica;
    }

    @Override
    public List<PessoaFisica> readAll() {
        final List<PessoaFisica> all = new ArrayList<>();
        final String sql =
            """
            SELECT pf.id_pessoa_fisica, pf.nm_pessoa, pf.dt_nascimento, pf.nr_cpf, pf.ds_genero,
                   p.id_pessoa, p.tp_pessoa, p.st_pessoa
            FROM T_CON_PESSOA_FISICA pf
            JOIN T_CON_PESSOA p ON pf.id_pessoa = p.id_pessoa
            """;
        try (Connection connection = DatabaseConnectionFactory.create().get()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                PessoaFisica pessoaFisica = new PessoaFisica(
                        resultSet.getLong("id_pessoa"),
                        resultSet.getString("tp_pessoa"),
                        resultSet.getString("st_pessoa"),
                        resultSet.getLong("id_pessoa_fisica"),
                        resultSet.getString("nm_pessoa"),
                        resultSet.getDate("dt_nascimento"),
                        resultSet.getString("nr_cpf"),
                        resultSet.getString("ds_genero")
                );
                all.add(pessoaFisica);
            }
        } catch (SQLException e) {
            logger.warning("nenhum registro encontrado de pessoa fisica: " + e.getMessage());
        }

        return all;
    }

    @Override
    public PessoaFisica update(PessoaFisica pessoaFisica, Connection connection) throws PessoaFisicaNotFoundException, SQLException {
        final String sql =
                """
                UPDATE T_CON_PESSOA_FISICA 
                    SET NM_PESSOA = ?, DT_NASCIMENTO = ?, NR_CPF = ?, DS_GENERO = ?, ID_PESSOA = ?  
                WHERE ID_PESSOA_FISICA = ?
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, pessoaFisica.getNome());
        stmt.setObject(2, pessoaFisica.getDataNascimento());
        stmt.setString(3, pessoaFisica.getCpf());
        stmt.setString(4, pessoaFisica.getGenero());
        stmt.setLong(5, pessoaFisica.getIdPessoa());
        stmt.setLong(6, pessoaFisica.getIdPessoaFisica());

        int linhasAlteradas = stmt.executeUpdate();
        if (linhasAlteradas == 0) {
            throw new PessoaFisicaNotFoundException();
        }
        return pessoaFisica;
    }

    @Override
    public void deleteById(Long id, Connection connection) throws PessoaFisicaNotFoundException, SQLException {
        final String sql = "DELETE FROM T_CON_PESSOA_FISICA WHERE ID_PESSOA = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();
        if (linhasAlteradas == 0) {
            throw new PessoaFisicaNotFoundException();
        }
    }
}
