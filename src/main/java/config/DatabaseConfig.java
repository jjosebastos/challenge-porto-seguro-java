package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

final class DatabaseConfig {

    private DatabaseConfig() {
        throw new UnsupportedOperationException();
    }

    static String getUrl(){
        return "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    }

    static String getUser(){
        return "rm559221";
    }

    static String getPassword(){
        return "jn100800";
    }


}
