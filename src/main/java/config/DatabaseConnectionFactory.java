package config;

import java.sql.Connection;
import java.sql.SQLException;

public final class DatabaseConnectionFactory {

    private DatabaseConnectionFactory() {
        throw new UnsupportedOperationException();
    }

    public static DatabaseConnection create() throws SQLException {
        return DatabaseConnectionImpl.getInstance();
    }
}
