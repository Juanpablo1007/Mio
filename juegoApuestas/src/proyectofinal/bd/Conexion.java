package proyectofinal.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
    private static final String user = "root";
    private static final String password = "admin";
    private static final String server = "localhost";
    private static final String port = "3306";
    private static final String nombreBD = "datos";
    
    private static final String JDBC_URL = "jdbc:mysql://" + server + ":" + port + "/" + nombreBD + "?zeroDateTimeBehavior=CONVERT_TO_NULL";
    
    private String driver = "com.mysql.cj.jdbc.Driver";
    
    public Conexion(){
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace(System.err);
            System.out.println("Error en la conexion");
        }
    }
    
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL, user, password);
    }
    
    public void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    
    public void close(PreparedStatement stmt) throws SQLException{
        stmt.close();
    }
    
    public void close(Connection conn) throws SQLException{
        conn.close();
    }
}
