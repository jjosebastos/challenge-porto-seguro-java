package br.com.inovadevs.dao.endereco;

import br.com.inovadevs.config.DatabaseConnectionFactory;
import br.com.inovadevs.entity.Endereco;
import br.com.inovadevs.exception.EnderecoNotFoundException;
import br.com.inovadevs.exception.EnderecoNotSavedException;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EnderecoDaoImpl implements EnderecoDao {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public Endereco save(Endereco endereco, Connection connection) throws EnderecoNotSavedException, SQLException {
        final String sql =
        """
        BEGIN 
            INSERT INTO T_CON_ENDERECO(NM_RUA, NR_ENDERECO, NM_BAIRRO, NM_CIDADE, 
                                        SG_UF, NR_CEP, DS_COMPLEMENTO, ID_PESSOA, 
                                        ID_SEGURADORA, ID_AUTORIZADA) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING ID INTO ?; 
        END;
        """;
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, endereco.getRua());
        call.setString(2, endereco.getNumero());
        call.setString(3, endereco.getBairro());
        call.setString(4, endereco.getCidade());
        call.setString(5, endereco.getUf());
        call.setString(6, endereco.getCep());
        call.setString(7, endereco.getComplemento());
        call.setLong(8, endereco.getIdCliente());
        call.setLong(9, endereco.getIdSeguradora());
        call.setLong(10, endereco.getIdAutorizada());
        call.registerOutParameter(11, OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(11);
        if (linhasAlteradas == 0 || id == 0) {
            throw new EnderecoNotSavedException();
        }
        endereco.setIdEndereco(id);
        return endereco;
    }

    @Override
    public List<Endereco> readAll() {
        final List<Endereco> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_CON_ENDERECO";
        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Endereco endereco = new Endereco(
                        resultSet.getLong("ID_ENDERECO"),
                        resultSet.getString("NM_RUA"),
                        resultSet.getString("NR_ENDERECO"),
                        resultSet.getString("NM_BAIRRO"),
                        resultSet.getString("NM_CIDADE"),
                        resultSet.getString("SG_UF"),
                        resultSet.getString("NR_CEP"),
                        resultSet.getString("DS_COMPLEMENTO"),
                        resultSet.getLong("ID_PESSOA"),
                        resultSet.getLong("ID_SEGURADORA"),
                        resultSet.getLong("ID_AUTORIZADA")
                );
                all.add(endereco);
            }
        } catch (SQLException e) {
            logger.warning("nenhum registro encontrado de endereco: " + e.getMessage());
        }
        return all;
    }

    @Override
    public Endereco update(Endereco endereco, Connection connection) throws EnderecoNotFoundException, SQLException {
        final String sql =
                """
                UPDATE T_CON_ENDERECO 
                        SET NM_RUA = ?, NR_ENDERECO = ?, NM_BAIRRO = ?, NM_CIDADE = ?, 
                            SG_UF = ?, NR_CEP = ?, DS_COMPLEMENTO = ?, ID_PESSOA = ?, 
                            ID_SEGURADORA = ?, ID_AUTORIZADA = ? 
                WHERE ID_ENDERECO = ?;
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, endereco.getRua());
        stmt.setString(2, endereco.getNumero());
        stmt.setString(3, endereco.getBairro());
        stmt.setString(4, endereco.getCidade());
        stmt.setString(5, endereco.getUf());
        stmt.setString(6, endereco.getCep());
        stmt.setString(7, endereco.getComplemento());
        stmt.setLong(8, endereco.getIdCliente());
        stmt.setLong(9, endereco.getIdSeguradora());
        stmt.setLong(10, endereco.getIdAutorizada());
        stmt.setLong(11, endereco.getIdEndereco());

        int linhasAlteradas = stmt.executeUpdate();
        if (linhasAlteradas == 0) {
            throw new EnderecoNotFoundException();
        }

        return endereco;
    }

    @Override
    public void deleteById(long id, Connection connection) throws EnderecoNotFoundException, SQLException {
        final String sql = "DELETE FROM T_CON_ENDERECO WHERE ID_ENDERECO = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();
        if (linhasAlteradas == 0) {
            throw new EnderecoNotFoundException();
        }
    }
}
