package infrastructure;

import java.sql.*;
import java.util.Map;

public class DataConnector {
    private String user;
    private String password;
    private String database;
    private Connection connection;

    public DataConnector() throws SQLException, ClassNotFoundException{
        this.setConnectionEnv();
        this.buildConnection();
    }

    private void buildConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        this.connection = DriverManager.getConnection(this.database, this.user, this.password);
    }

    public Connection getConnection() {
        return this.connection;
    }

    private void setConnectionEnv(){
        Map<String, String> environments = System.getenv();
        StringBuilder database = new StringBuilder();
        database.append("jdbc:mysql://")
                .append(environments.get("DATA_HOSTNAME")).append("/")
                .append(environments.get("DATA_DATABASE"));
        this.database = database.toString();
        this.user = environments.get("DATA_USER");
        this.password = environments.get("DATA_PASSWORD");
    }
}