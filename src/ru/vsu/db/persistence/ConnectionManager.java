package ru.vsu.db.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import ru.vsu.computer.di.annotation.Injectable;

@Injectable
public class ConnectionManager {

    private final Logger logger = Logger.getLogger(ConnectionManager.class.getCanonicalName());

    private DataSource dataSource;

    public ConnectionManager() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/catalog");
        } catch (NamingException e) {
            logger.info("Error while JNDI name lookup. [" + e.getMessage() + "]");
        }
    }

    public Connection getConnection() throws SQLException  {
        return dataSource.getConnection();
    }
}
