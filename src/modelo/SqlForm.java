package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SqlForm {

    public boolean conexion(JTable table) {
        connection cnBD = new connection();
        Connection cn = cnBD.establecerConexion();
        if (cn == null) {
            JOptionPane.showMessageDialog(null, "Error al conectar");
            return false;
        } else {
            mostrarDatos("", table);
            JOptionPane.showMessageDialog(null, "Conexión establecida");
            return true;
        }
    }

    public void mostrarDatos(String valor, JTable table) {
        connection cc = new connection();
        Connection cn = cc.establecerConexion();
        String[] titulos = {"Código", "Nombre", "Estado"};
        String[] registros = new String[3];

        String sql = "SELECT * FROM Distrito where idDis LIKE '%" + valor + "%'";
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("idDis");
                registros[1] = rs.getString("nomDis");
                registros[2] = rs.getString("estDis");

                modelo.addRow(registros);
            }
            rs.close();
            table.setModel(modelo);
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
