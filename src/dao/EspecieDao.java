package dao;

import db.MySQLConnection;
import model.Especie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspecieDao extends MySQLConnection {
    public boolean adicionar(Especie es) {
        String sql = "INSERT INTO es(esp_descripcion, esp_estado) VALUES (?, ?)";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, es.getEspDescripcion());
            ps.setString(2, es.getEspEstado());
            int n = ps.executeUpdate();
            if (n > 0) {
                System.out.println("[ INFO ] Insert ejecutado con éxito");
                return true;
            }
            this.desconectar();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] Problemas al ejecutar Insert: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean buscarId(Especie es) {
        String sql = "SELECT esp_id, esp_descripcion, esp_estado FROM es WHERE esp_id = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, es.getEspId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                es.setEspId(rs.getInt("esp_id"));
                es.setEspDescripcion(rs.getString("esp_descripcion"));
                es.setEspEstado(rs.getString("esp_estado"));
                this.desconectar();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminar(Especie es) {
        String sql = "DELETE FROM es WHERE esp_id = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(0, es.getEspId());
            int n = ps.executeUpdate();
            if (n > 0) {
                System.out.println("[ INFO ] Delete ejecutado con éxito");
                return true;
            }
            this.desconectar();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] Problemas al ejecutar Delete: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    //    public Boolean exist(Especie especie) {
//        String sql = "SELECT esp_id, esp_descripcion, esp_estado FROM especie WHERE esp_id = ?";
//        Connection conn = this.conectar();
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, especie.getEspId());
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                especie.setEspId(rs.getInt("esp_id"));
//                especie.setEspDescripcion(rs.getString("esp_descripcion"));
//                especie.setEspEstado(rs.getString("esp_estado"));
//                this.desconectar();
//                return true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
    // opt: 0=Todos; 1=Solo activos
    public List<Especie> getTodos(int optEstado) {
        List<Especie> list = new ArrayList<>();
        String sql = "SELECT esp_id, esp_descripcion, esp_estado FROM especie"
                + (optEstado == 1 ? " WHERE esp_estado = ?" : "");
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if (optEstado == 1)
                ps.setString(1, "Activo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Especie e = new Especie();
                e.setEspId(rs.getInt("esp_id"));
                e.setEspDescripcion(rs.getString("esp_descripcion"));
                e.setEspEstado(rs.getString("esp_estado"));
                list.add(e);
            }
            this.desconectar();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean modificar(Especie es) {
        String sql = "UPDATE es SET esp_descripcion = ?, esp_estado = ? WHERE esp_id = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, es.getEspDescripcion());
            ps.setString(2, es.getEspEstado());
            ps.setInt(3, es.getEspId());
            int n = ps.executeUpdate();
            if (n > 0) {
                System.out.println("[ INFO ] Update ejecutado con éxito");
                return true;
            }
            this.desconectar();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] Problemas al ejecutar Update: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


}
