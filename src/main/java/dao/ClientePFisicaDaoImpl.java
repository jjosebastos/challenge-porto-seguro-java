package dao;

import config.DatabaseConfig;
import entity.Pessoa;
import exception.PessoaFisicaDaoException;
import entity.ClientePFisica;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientePFisicaDaoImpl implements ClientePFisicaDao {

    private final DatabaseConfig db;

    public ClientePFisicaDaoImpl(DatabaseConfig db) {
        this.db = db;
    }

//    public void create(ClientePFisica pessoaFisica) throws PessoaFisicaDaoException {
//        String sqlPessoaFisica = "INSERT INTO T_CON_PESSOA_FISICA (vl_idade, nr_cpf, id_email) VALUES (?, ?, ?)";
//
//        try {
//            Connection connection = db.getConnection();
//            connection.setAutoCommit(false);
//
//            PreparedStatement stmtPessoaF = connection.prepareStatement(sqlPessoaFisica);
//            stmtPessoaF.setInt(1, pessoaFisica.getIdade());
//            stmtPessoaF.setString(2, pessoaFisica.getCpf());
//            stmtPessoaF.setString(3, pessoaFisica.getEmail());
//            stmtPessoaF.executeUpdate();
//            connection.commit();
//            connection.close();
//        } catch (SQLException e) {
//            throw new PessoaFisicaDaoException("Não foi feita a inserção");
//        }
//
//    }

//    @Override
   public void insertTransactioned(ClientePFisica pessoaFisica) throws PessoaFisicaDaoException, SQLException {
//        Connection connection = null;
//        PreparedStatement pstmtPessoa = null;
//        PreparedStatement pstmtPessoaFisica = null;
//
//        String sqlPessoa = "INSERT INTO T_CON_PESSOA (nm_pessoa) VALUES (?)";
//        String sqlPessoaFisica = "INSERT INTO T_CON_PESSOA_FISICA (vl_idade, nr_cpf, id_email) VALUES (?, ?, ?)";
//
//        try {
//            connection = db.getConnection();
//            connection.setAutoCommit(false);
//
//            pstmtPessoa = connection.prepareStatement(sqlPessoa);
//            pstmtPessoa.
//
//
//
//            ResultSet rsPessoa = pstmtPessoa.getGeneratedKeys();
//            int idPessoa = 0;
//
//            if (rsPessoa.next()){
//                idPessoa = rsPessoa.getInt(1);
//            } else {
//                throw new PessoaFisicaDaoException("Falha ao inserir em T_CON_PESSOA, nenhuma chave gerada");
//            }
//
//            pstmtPessoaFisica = connection.prepareStatement(sqlPessoaFisica);
//            pstmtPessoaFisica.setInt(1, pessoaFisica.getIdade());
//            pstmtPessoaFisica.setString(2, pessoaFisica.getCpf());
//            pstmtPessoaFisica.setString(3, pessoaFisica.getEmail());
//            pstmtPessoaFisica.setInt(4, idPessoa);  // Usar o id gerado para a chave estrangeira
//            pstmtPessoaFisica.executeUpdate();
//
//            connection.commit();
//
//        } catch (SQLException e) {
//            if (connection != null){
//                connection.rollback();
//            }
//            throw new PessoaFisicaDaoException("Erro ao inserir pessoa física e pessoa");
//
//
//        } finally {
//             if (pstmtPessoaFisica != null) pstmtPessoaFisica.close();
//             if (pstmtPessoa != null) pstmtPessoa.close();
//             if (connection != null) connection.setAutoCommit(true);
//        }
     }

    @Override
    public List<ClientePFisica> readAll() throws PessoaFisicaDaoException, SQLException {

        String sql = "SELECT P.ID_PESSOA, P.NM_PESSOA, PF.VL_IDADE, PF.NR_CPF, PF.ID_EMAIL FROM T_CON_PESSOA P\n" +
                "INNER JOIN T_CON_PESSOA_FISICA PF ON P.ID_PESSOA = PF.ID_PESSOA";
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = db.getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<ClientePFisica> resul = new ArrayList<>();

            while (rs.next()){
                int id_pessoa = rs.getInt("id_pessoa");
                String nome = rs.getString("nm_pessoa");
                int idade = rs.getInt("vl_idade");
                String cpf = rs.getString("nr_cpf");
                String email = rs.getString("id_email");

                ClientePFisica cliente = new ClientePFisica(idade, cpf, email);
                resul.add(cliente);
            }
            return resul;
        } catch (SQLException e) {
            throw new PessoaFisicaDaoException("Registros da tabela pessoa não encontrados");
        }
    }

    @Override
    public void update(ClientePFisica pessoaFisica) throws PessoaFisicaDaoException {
        String sql = "UPDATE pessoa_1tdspz SET vl_idade=?, nr_cpf, id_email WHERE id_pessoa=?";

        try {
            Connection connection = db.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, pessoaFisica.getIdade());
            pstmt.setString(2, pessoaFisica.getCpf());
            pstmt.setString(3, pessoaFisica.getEmail());
            pstmt.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw  new PessoaFisicaDaoException("Nenhum registro atualizado");
        }
    }

    @Override
    public void delete(int id) throws PessoaFisicaDaoException {
        String sql = "DELETE FROM T_CON_PESSOA_FISICA WHERE ID=?";

        try {
            Connection connection = db.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e){
            throw new PessoaFisicaDaoException("Exclusão não realizada");
        }

    }
}
