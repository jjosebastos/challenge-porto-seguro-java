import config.DatabaseConfig;
import dao.ClientePFisicaDAO;
import dao.ClientePFisicaDAOImpl;
import model.ClientePFisica;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseConfig db = new DatabaseConfig("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
        "rm559221","jn100800");

        ClientePFisica cliPF = new ClientePFisica("Jose Bezerra", 23,"jose@mail.com", "400.000.999-00");

        ClientePFisicaDAO clientePFDao = new ClientePFisicaDAOImpl(db.getConnection());

        clientePFDao.create(cliPF);

    }
}
