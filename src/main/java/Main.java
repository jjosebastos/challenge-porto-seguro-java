import config.DatabaseConfig;
import dao.*;
import entity.*;
import exception.*;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, PessoaDaoException, EnderecoDaoException, SeguradoraDaoException {

        DatabaseConfig db = new DatabaseConfig("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557525", "121105");





    }
}
