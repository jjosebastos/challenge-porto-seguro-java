package dao;

import config.DatabaseConfig;
import entity.Endereco;
import exception.EnderecoDaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDaoImpl implements EnderecoDao {
    private final DatabaseConfig db;
    public EnderecoDaoImpl (DatabaseConfig db){
        this.db = db;
    }
    @Override
    public void create(Endereco endereco) throws EnderecoDaoException {
        String sql = "INSERT INTO T_CON_ENDERECO (" +
                "ID_ENDERECO, NM_RUA, NR_ENDERECO, NM_BAIRRO, NM_CIDADE, SG_UNIDADE_FEDERATIVA, " +
                "DS_COMPLEMENTO, ID_CLIENTE, ID_SEGURADORA, ID_AUTORIZADA, CEP) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            pstmt.setInt(1, endereco.getIdEndereco());
            pstmt.setString(2, endereco.getRua());
            pstmt.setString(3, endereco.getNumeroCasa());
            pstmt.setString(4, endereco.getBairro());
            pstmt.setString(5, endereco.getCidade());
            pstmt.setString(6, endereco.getUf());
            pstmt.setString(7, endereco.getComplemento());
            if (endereco.getIdCliente() == null){
                pstmt.setNull(8, endereco.getIdCliente());
            } else {
                pstmt.setInt(8, endereco.getIdCliente());
            }
            if (endereco.getIdSeguradora() == null){
                pstmt.setNull(9, endereco.getIdSeguradora());
            } else {
                pstmt.setInt(9, endereco.getIdSeguradora());
            }
            if (endereco.getIdAutorizada() == null){
                pstmt.setNull(10, endereco.getIdAutorizada());
            } else {
                pstmt.setInt(10, endereco.getIdAutorizada());
            }
            pstmt.setString(11, endereco.getCep());
            pstmt.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e){
            throw new EnderecoDaoException("Nenhum dado inserido na T_CON_ENDERECO");
        }
    }

    @Override
    public List<Endereco> readAll() throws SQLException, EnderecoDaoException {
        List<Endereco> result = new ArrayList<>();
        String sql = "SELECT * FROM T_CON_ENDERECO";

        try {
            Connection connection = db.getConnection();
            Statement pstmt = connection.createStatement();
            ResultSet rs = pstmt.executeQuery(sql);

            while (rs.next()) {
                int idEndereco = rs.getInt("ID_ENDERECO");
                String nmRua = rs.getString("NM_RUA");
                String nrEndereco = rs.getString("NR_ENDERECO");
                String nmBairro = rs.getString("NM_BAIRRO");
                String nmCidade = rs.getString("NM_CIDADE");
                String sgUnidadeFederativa = rs.getString("SG_UNIDADE_FEDERATIVA");
                String dsComplemento = rs.getString("DS_COMPLEMENTO");
                int idCliente = rs.getInt("ID_CLIENTE");
                int idSeguradora = rs.getInt("ID_SEGURADORA");
                int idAutorizada = rs.getInt("ID_AUTORIZADA");
                String cep = rs.getString("CEP");

                // Adiciona o objeto Endereco à lista resultante
                result.add(new Endereco(idEndereco, nmRua, nrEndereco, nmBairro, nmCidade, sgUnidadeFederativa,
                        dsComplemento, idCliente, idSeguradora, idAutorizada, cep));
            }
        } catch (SQLException e){
            throw new EnderecoDaoException("Nenhum dado encontrado");
        }

        return result;
    }
    @Override
    public void update(Endereco endereco) throws EnderecoDaoException {
        String sql = "UPDATE T_ENDERECO SET " +
                "NM_RUA = ?, NR_ENDERECO = ?, NM_BAIRRO = ?, NM_CIDADE = ?, " +
                "SG_UNIDADE_FEDERATIVA = ?, DS_COMPLEMENTO = ?, ID_CLIENTE = ?, " +
                "ID_SEGURADORA = ?, ID_AUTORIZADA = ?, CEP = ? " +
                "WHERE ID_ENDERECO = ?";

        try {
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, endereco.getRua());
            pstmt.setString(2, endereco.getNumeroCasa());
            pstmt.setString(3, endereco.getBairro());
            pstmt.setString(4, endereco.getCidade());
            pstmt.setString(5, endereco.getUf());
            pstmt.setString(6, endereco.getComplemento());
            pstmt.setInt(7, endereco.getIdCliente());
            pstmt.setInt(8, endereco.getIdSeguradora());
            pstmt.setInt(9, endereco.getIdAutorizada());
            pstmt.setString(10, endereco.getCep());
            pstmt.setInt(11, endereco.getIdEndereco());
            pstmt.executeUpdate();
            connection.commit();
            connection.close();
            pstmt.executeUpdate();
        } catch (SQLException e){
            throw new EnderecoDaoException("Nenhum dado atualizado na T_CON_ENDERECO");
        }
    }

    @Override
    public void delete(int id) throws EnderecoDaoException {
        String sql = "DELETE FROM T_CON_ENDERECO WHERE ID = ?";
        try {
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
        } catch (SQLException e){
            throw new EnderecoDaoException("Nenhum dado Excluido da T_CON_ENDERECO");
        }
    }
}
