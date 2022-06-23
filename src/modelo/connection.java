package modelo;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {

    Connection cn = null;
    String user = "root";
    String pass = "mysql2022";
    String bd = "bdprueba";
    String ip = "localhost";
    String puerto = "3306";
    String driver = "com.mysql.cj.jdbc.Driver";
    String sql = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

    public Connection establecerConexion() {
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(sql, user, pass);
//            JOptionPane.showMessageDialog(null, "Conectado");
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            System.err.println("No se puedo conectar \n" + e);
        }
        return cn;
    }
}
