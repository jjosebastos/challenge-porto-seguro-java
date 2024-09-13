import config.DatabaseConfig;
import dao.ClientePFisicaDao;
import dao.ClientePFisicaDaoImpl;
import dao.PessoaDaoImpl;
import entity.Pessoa;
import exception.PessoaDaoException;
import exception.PessoaFisicaDaoException;
import entity.ClientePFisica;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException,  PessoaDaoException {

        DatabaseConfig db = new DatabaseConfig("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
        "rm559221","jn100800");

        PessoaDaoImpl pessoaDao = new PessoaDaoImpl(db);

        Pessoa pessoa2 = new Pessoa(30L, "José Bezerra");
        pessoa2.setNome("José Bezerra Bastos Neto");
        pessoaDao.update(pessoa2);


        pessoaDao.delete(30);

        List<Pessoa> result = pessoaDao.readAll();
        for (Pessoa p: result){
            System.out.println(p.toString());
        }





    }
}
