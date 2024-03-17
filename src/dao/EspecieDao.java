package dao;

import db.ConnectionDB;
import model.Especie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspecieDao extends ConnectionDB {
    public List<Especie> getAll() {
        List<Especie> list = new ArrayList<>();
        String sql = "SELECT * FROM especie";
        Connection conn = this.connect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Especie e = new Especie();
                e.setEspId(rs.getInt("esp_id"));
                e.setEspDescripcion(rs.getString("esp_descripcion"));
                e.setEspEstado(rs.getString("esp_estado"));
                list.add(e);
                System.out.println(e);
            }
            this.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Especie getById(int intId) {
        String sql = "SELECT * FROM especie WHERE esp_id = ?";
        Connection conn = this.connect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, intId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Especie e = new Especie();
                e.setEspId(rs.getInt("esp_id"));
                e.setEspDescripcion(rs.getString("esp_descripcion"));
                e.setEspEstado(rs.getString("esp_estado"));
                System.out.println(e);
                return e;
            }
            this.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Especie getByEspecie(String strEspecie) {
        String sql = "SELECT * FROM especie WHERE esp_descripcion = ?";
        Connection conn = this.connect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, strEspecie);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Especie e = new Especie();
                e.setEspId(rs.getInt("esp_id"));
                e.setEspDescripcion(rs.getString("esp_descripcion"));
                e.setEspEstado(rs.getString("esp_estado"));
                System.out.println(e);
                this.close();
                return e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean exist(String strDescripcion) {
        String sql = "SELECT * FROM especie WHERE esp_descripcion = ?";
        Connection conn = this.connect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, strDescripcion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Especie e = new Especie();
                e.setEspId(rs.getInt("esp_id"));
                e.setEspDescripcion(rs.getString("esp_descripcion"));
                e.setEspEstado(rs.getString("esp_estado"));
                System.out.println(e);
                this.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insert(Especie especie) {
        String sql = "INSERT INTO especie(esp_descripcion, esp_estado) VALUES (?, ?)";
        Connection conn = this.connect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, especie.getEspDescripcion());
            ps.setString(2, especie.getEspEstado());
            int n = ps.executeUpdate();
            if (n > 0) {
                System.out.println("[ INFO ] Insert ejecutado con éxito");
                return true;
            }
            this.close();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] Problemas al ejecutar Insert: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Especie especie) {
        String sql = "UPDATE especie SET esp_descripcion = ?, esp_estado = ? WHERE esp_id = ?";
        Connection conn = this.connect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, especie.getEspDescripcion());
            ps.setString(2, especie.getEspEstado());
            ps.setInt(3, especie.getEspId());
            int n = ps.executeUpdate();
            if (n > 0) {
                System.out.println("[ INFO ] Update ejecutado con éxito");
                return true;
            }
            this.close();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] Problemas al ejecutar Update: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int intId) {
        String sql = "DELETE FROM especie WHERE esp_id = ?";
        Connection conn = this.connect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, intId);
            int n = ps.executeUpdate();
            if (n > 0) {
                System.out.println("[ INFO ] Delete ejecutado con éxito");
                return true;
            }
            this.close();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] Problemas al ejecutar Delete: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
