package br.com.inovadevs.dao.pessoa.juridica;

import br.com.inovadevs.config.DatabaseConnectionFactory;
import br.com.inovadevs.entity.PessoaJuridica;
import exception.PessoaFisicaNotFoundException;
import exception.PessoaFisicaNotSavedException;
import exception.PessoaJuridicaNotFoundException;
import exception.PessoaJuridicaNotSavedException;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PessoaJuridicaDaoImpl implements PessoaJuridicaDao {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public PessoaJuridica save(PessoaJuridica pessoaJuridica, Connection connection) throws PessoaJuridicaNotSavedException, SQLException {
        final String sql =
                """
                BEGIN 
                     INSERT INTO T_CON_PESSOA_JURIDICA (NR_CNPJ, NM_RAZAO_SOCIAL, NM_FANTASIA, ID_PESSOA)
                     VALUES (?,?,?,?) RETURNING ID INTO?;
                END
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, pessoaJuridica.getCnpj());
        call.setString(2, pessoaJuridica.getRazaoSocial());
        call.setString(3, pessoaJuridica.getNomeFantasia());
        call.setLong(4, pessoaJuridica.getIdPessoa());
        call.registerOutParameter(5, OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(5);

        if (linhasAlteradas == 0 || id == 0) {
            throw new PessoaJuridicaNotSavedException();
        }

        return pessoaJuridica;
    }

    @Override
    public List<PessoaJuridica> readAll() {
        final List<PessoaJuridica> all = new ArrayList<>();
        final String sql = """
                SELECT p.id_pessoa, p.tp_pessoa, p.st_pessoa, 
                       pj.id_pessoa_juridica, pj.nr_cnpj, pj.nm_razao_social, 
                       pj.nm_fantasia, pj.id_pessoa
                FROM T_CON_PESSOA p
                INNER JOIN T_CON_PESSOA_JURIDICA pj
                ON p.id_pessoa = pj.id_pessoa_juridica
                """;
        try (Connection connection = DatabaseConnectionFactory.create().get()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                PessoaJuridica pessoaJuridica = new PessoaJuridica(
                        resultSet.getLong("id_pessoa"),
                        resultSet.getString("tp_pessoa"),
                        resultSet.getString("st_pessoa"),
                        resultSet.getLong("id_pessoa_juridica"),
                        resultSet.getString("nr_cnpj"),
                        resultSet.getString("nm_razao_social"),
                        resultSet.getString("nm_fantasia"));
                all.add(pessoaJuridica);
            }
        } catch (SQLException e) {
            logger.warning("nenhum registro encontrado em pessoa juridica: " + e.getMessage());
        }
        return all;
    }

    @Override
    public PessoaJuridica update(PessoaJuridica pessoaJuridica, Connection connection) throws PessoaJuridicaNotFoundException, SQLException {
        final String sql =
                """
                UPDATE T_CON_PESSOA_JURIDICA 
                    SET NR_CNPJ = ?, NM_RAZAO_SOCIAL = ?, NM_FANTASIA = ?, ID_PESSOA = ?
                WHERE ID_PESSOA_JURIDICA = ?
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, pessoaJuridica.getCnpj());
        stmt.setString(2, pessoaJuridica.getRazaoSocial());
        stmt.setString(3, pessoaJuridica.getNomeFantasia());
        stmt.setLong(4, pessoaJuridica.getIdPessoa());
        stmt.setLong(5, pessoaJuridica.getIdPessoaJuridica());

        int linhasAlteradas = stmt.executeUpdate();
        if (linhasAlteradas == 0){
            throw new PessoaJuridicaNotFoundException();
        }
        return pessoaJuridica;
    }

    @Override
    public void deleteById(long id, Connection connection) throws PessoaJuridicaNotFoundException, SQLException {
        final String sql = "DELETE FROM T_CON_PESSOA_JURIDICA WHERE ID_PESSOA_JURIDICA = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();
        if (linhasAlteradas == 0){
            throw new PessoaJuridicaNotFoundException();
        }
    }
}
