package dao;

import db.MySQLConnection;
import model.Raza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RazaDao extends MySQLConnection {
    public boolean adicionar(Raza rz) {
        String sql = "INSERT INTO raza " +
                "(raza_descripcion, " +
                "raza_estado, " +
                "esp_id) " +
                "VALUES " +
                "(?, " +        // 1 raza_descripcion,
                "?, " +         // 2 raza_estado,
                "?); ";         // 3 esp_id
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, rz.getRazaDescripcion());
            ps.setString(2, rz.getRazaEstado());
            ps.setInt(3, rz.getEspId());
            int n = ps.executeUpdate();
            if (n > 0) {
                System.out.println("[ INFO ] Insert ejecutado con éxito");
                this.desconectar();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("[ ERROR ] Problemas al ejecutar Insert: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return false;
    }

    public boolean buscarId(Raza rz) {
        String sql = "SELECT r.raza_id, " +
                "r.raza_descripcion, " +
                "r.raza_estado, " +
                "r.esp_id, " +
                "e.esp_descripcion, " +
                "e.esp_estado " +
                "FROM raza r " +
                "INNER JOIN especie e ON r.esp_id = e.esp_id " +
                "WHERE r.raza_estado = 'Activo' " +
                "AND r.raza_id = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, rz.getRazaId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rz.setRazaId(rs.getInt("raza_id"));
                rz.setRazaDescripcion(rs.getString("raza_descripcion"));
                rz.setRazaEstado(rs.getString("raza_estado"));
                rz.setEspId(rs.getInt("esp_id"));
                this.desconectar();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return false;
    }

    public boolean buscarByNombew(Raza rz) {
        String sql = "SELECT r.raza_id, " +
                "r.raza_descripcion, " +
                "r.raza_estado, " +
                "r.esp_id, " +
                "e.esp_descripcion, " +
                "e.esp_estado " +
                "FROM raza r " +
                "INNER JOIN especie e ON r.esp_id = e.esp_id " +
                "WHERE r.raza_estado = 'Activo' " +
                "AND r.raza_descripcion = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, rz.getRazaDescripcion());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rz.setRazaId(rs.getInt("raza_id"));
                rz.setRazaDescripcion(rs.getString("raza_descripcion"));
                rz.setRazaEstado(rs.getString("raza_estado"));
                rz.setEspId(rs.getInt("esp_id"));
                this.desconectar();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return false;
    }

    public boolean eliminar(Raza rz) {
        String sql = "DELETE FROM raza WHERE raza_id = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, rz.getRazaId());
            int n = ps.executeUpdate();
            if (n > 0) {
                System.out.println("[ INFO ] Delete ejecutado con éxito");
                return true;
            }
            this.desconectar();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] Problemas al ejecutar Delete: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return false;
    }

    // opt: 0=Todos; 1=Solo activos
    public List<Raza> getTodos(int optEstado) {
        List<Raza> list = new ArrayList<>();
        String sql = "SELECT r.raza_id, " +
                "r.raza_descripcion, " +
                "r.raza_estado, " +
                "r.esp_id, " +
                "e.esp_descripcion, " +
                "e.esp_estado " +
                "FROM raza r " +
                "INNER JOIN especie e ON r.esp_id = e.esp_id "
                + (optEstado == 1 ? " WHERE raza_estado = 'Activo';" : "");
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Raza rz = new Raza();
                rz.setRazaId(rs.getInt("raza_id"));
                rz.setRazaDescripcion(rs.getString("raza_descripcion"));
                rz.setRazaEstado(rs.getString("raza_estado"));
                rz.setEspId(rs.getInt("esp_id"));
                list.add(rz);
            }
            this.desconectar();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return null;
    }

    public boolean modificar(Raza rz) {
        String sql = "UPDATE raza " +
                "SET " +
                "raza_descripcion = ?, " +
                "raza_estado = ?, " +
                "esp_id = ? " +
                "WHERE raza_id = ?; ";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, rz.getRazaDescripcion());
            ps.setString(2, rz.getRazaEstado());
            ps.setInt(3, rz.getEspId());
            ps.setInt(4, rz.getRazaId());
            int n = ps.executeUpdate();
            if (n > 0) {
                System.out.println("[ INFO ] Update ejecutado con éxito");
                return true;
            }
            this.desconectar();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] Problemas al ejecutar Update: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return false;
    }

}
