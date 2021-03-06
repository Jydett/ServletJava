package fr.polytech.jydet.td2.storage;

import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BasicConnectionPool {

    private final List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();

    private static int INITIAL_POOL_SIZE = 10;
    public static BasicConnectionPool INSTANCE;

    public static void create(int port, String host, String base, String user, String password) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(getConnectionString(port, host, base), user, password));
        }
        INSTANCE =  new BasicConnectionPool(pool);
    }

    private static String getConnectionString(int port, String host, String base) {
        return "jdbc:mysql://" + host + ":" + port + "/" + base + "?serverTimezone=UTC";
    }

    public Connection getConnection() {
        Connection connection = connectionPool
                .remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public void destroy() throws Exception {
        Exception ex = null;
        for (Connection connection : connectionPool) {
            try {
                connection.close();
            } catch (Exception e) {
                ex = e;
            }
        }
        for (Connection connection : usedConnections) {
            try {
                connection.close();
            } catch (Exception e) {
                ex = e;
            }
        }
        if (ex != null) {
            throw ex;
        }
    }

    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection(
            String url, String user, String password)
            throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }
}
