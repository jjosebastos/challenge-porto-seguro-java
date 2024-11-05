package br.com.inovadevs.dao.pessoa;

import br.com.inovadevs.config.DatabaseConnectionFactory;
import br.com.inovadevs.entity.Pessoa;
import br.com.inovadevs.exception.PessoaNotSavedException;
import br.com.inovadevs.exception.PessoaNotFoundException;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PessoaDaoImpl implements PessoaDao {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public Pessoa save(Pessoa pessoa, Connection connection) throws PessoaNotSavedException, SQLException {
        final String sql = "BEGIN INSERT INTO T_CON_PESSOA(TP_PESSOA, ST_PESSOA) VALUES (?, ?) RETURNING ID INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, pessoa.getStatus());
        call.setString(2, pessoa.getTipoCliente());
        call.registerOutParameter(3, OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(3);
        if(linhasAlteradas == 0 || id == 0){
            throw new PessoaNotSavedException();
        }
        return pessoa;
    }

    @Override
    public List<Pessoa> readAll()  {
        final List<Pessoa> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_CON_PESSOA";
        try(Connection conn = DatabaseConnectionFactory.create().get()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                Pessoa pessoa = new Pessoa(
                        resultSet.getLong("id_pessoa"),
                        resultSet.getString("tp_pessoa"),
                        resultSet.getString("st_pessoa"));
                all.add(pessoa);
            }
        } catch (SQLException e) {
            logger.warning("nenhum nenhum registro encontrado de pessoa: " + e.getMessage());
        }
        return all;
    }

    @Override
    public Pessoa update(Pessoa pessoa, Connection connection) throws PessoaNotFoundException, SQLException {
        final String sql = "UPDATE T_CON_PESSOA SET TP_PESSOA = ?, ST_PESSOA = ? WHERE ID_PESSOA = ?;";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1,pessoa.getStatus());
        stmt.setString(2, pessoa.getTipoCliente());
        stmt.setLong(3, pessoa.getIdPessoa());
        int linhasAlteradas = stmt.executeUpdate();

        if(linhasAlteradas == 0 ) {
            throw new PessoaNotFoundException();
        }
        return pessoa;

    }

    @Override
    public void deleteById(Long id, Connection connection) throws PessoaNotFoundException, SQLException {
        final String sql = "DELETE FROM T_CON_PESSOA WHERE ID_PESSOA = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();
        if (linhasAlteradas == 0) {
            throw new PessoaNotFoundException();
        }

    }
}
