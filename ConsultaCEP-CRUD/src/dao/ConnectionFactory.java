package dao;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private String conexao = "jdbc:sqlserver://localhost:1433;user=DESKTOP-NMEK6BP\\nicsi;databaseName=Livraria";
    private  String DRIVER ="com.microsoft.sqlserver.jdbc.SQLServerDriver" ;

    public Connection getConnection() throws SQLException{

        try {
            Class.forName(DRIVER );
            return DriverManager.getConnection(conexao);
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());

        }
    }
}
