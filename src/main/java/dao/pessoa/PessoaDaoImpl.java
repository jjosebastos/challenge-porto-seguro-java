package dao.pessoa;

import config.DatabaseConnectionFactory;
import entity.Pessoa;
import exception.PessoaNotSavedException;
import exception.PessoaNotFoundException;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PessoaDaoImpl implements PessoaDao {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public Pessoa save(Pessoa pessoa, Connection connection) throws PessoaNotSavedException, SQLException {
        final String sql = "BEGIN INSERT INTO T_CON_PESSOA(tp_pessoa, st_pessoa) VALUES (?, ?) RETURNING ID INTO ?; END;";
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
            logger.info("não foi possível localizar nenhum registro de pessoa: "+e.getMessage());
        }
        return all;
    }

    @Override
    public Pessoa update(Pessoa pessoa, Connection connection) throws PessoaNotFoundException, SQLException {
        final String sql = "UPDATE t_con_pessoa set tp_pessoa=?, st_pessoa=? where id_pessoa = ?";

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
        final String sql = "delete from T_CON_PESSOA where id_pessoa = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();
        if (linhasAlteradas == 0) {
            throw new PessoaNotFoundException();
        }

    }
}
