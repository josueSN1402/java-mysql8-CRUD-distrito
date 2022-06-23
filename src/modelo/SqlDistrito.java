package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlDistrito extends connection {

    public boolean insertar(Distrito dis) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = establecerConexion();
        int res = 0;
        String sql = "INSERT INTO Distrito (nomDis, estDis) VALUES (?, ?)";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, dis.getNomDis());
            ps.setInt(2, dis.getEstDis());
            res = ps.executeUpdate();
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.err.println(ex);
            return false;
        } finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean modificar(Distrito dis) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = establecerConexion();
        int res = 0;
        String sql = "UPDATE Distrito SET nomDis=?, estDis=? WHERE idDis=?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, dis.getNomDis());
            ps.setInt(2, dis.getEstDis());
            ps.setInt(3, dis.getIdDis());
            res = ps.executeUpdate();
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.err.println(ex);
            return false;
        } finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean eliminar(Distrito dis) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = establecerConexion();
        int res = 0;
        String sql = "DELETE FROM Distrito WHERE idDis = ?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, dis.getIdDis());
            res = ps.executeUpdate();
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.err.println(ex);
            return false;
        } finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean buscar(Distrito dis) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = establecerConexion();
        String sql = "SELECT * FROM Distrito WHERE idDis = ?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, dis.getIdDis());
            rs = ps.executeQuery();
            if (rs.next()) {
                dis.setIdDis(rs.getInt("idDis"));
                dis.setNomDis(rs.getString("nomDis"));
                dis.setEstDis(rs.getInt("estDis"));
                return true;
            }
            return false;
        } catch (SQLException ex) {
            System.err.println(ex);
            return false;
        } finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
