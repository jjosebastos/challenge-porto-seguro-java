package dao.endereco;

import config.DatabaseConfig;
import entity.Endereco;
import exception.EnderecoDaoException;
import exception.EnderecoNotFoundException;
import exception.EnderecoNotSavedException;
import exception.UnsupportedServiceOperationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EnderecoDaoImpl implements EnderecoDao {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public Endereco create(Endereco endereco, Connection connection) throws UnsupportedServiceOperationException, SQLException, EnderecoNotSavedException {

        final String sql = "BEGIN INSERT INTO T_CON_ENDERECO (NM_RUA,NR_ENDERECO,NM_BAIRRO, SG_UF, DS_COMPLEMENTO, ID_PESSOA, ID_SEGURADORA, ID_AUTORIZADA)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING ID INTO ?; END;";

        return endereco;
    }

    @Override
    public List<Endereco> readAll() {
        return List.of();
    }

    @Override
    public void update(Endereco endereco, Connection connection) throws SQLException, EnderecoNotFoundException {

    }

    @Override
    public void delete(int id, Connection connection) throws SQLException, EnderecoNotFoundException {

    }
}
