package dao;

import db.MySQLConnection;
import model.Perfil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerfilDao extends MySQLConnection {
    public boolean adicionar(Perfil p) {
        String sql = "INSERT INTO perfil (per_id, per_descripcion, per_estado) VALUES\n" +
                "(?, " +                     // 1 per_descripcion
                "?); ";                     // 2 per_estado
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getPerDescripcion());
            ps.setString(2, p.getPerEstado());
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

    public boolean buscarId(Perfil p) {
        String sql = "SELECT per_id, per_descripcion, per_estado FROM perfil WHERE per_estado = 'Activo' AND" +
                " per_id = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(0, p.getPerId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p.setPerId(rs.getInt("per_id"));
                p.setPerDescripcion(rs.getString("per_descripcion"));
                p.setPerEstado(rs.getString("per_estado"));
                this.desconectar();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminar(Perfil p) {
        String sql = "DELETE FROM perfil WHERE per_id ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getPerId());
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

    // opt: 0=Todos; 1=Solo activos
    public List<Perfil> getTodos(int optEstado) {
        List<Perfil> list = new ArrayList<>();
        String sql = "SELECT per_id, per_descripcion, per_estado FROM perfil "
                + (optEstado == 1 ? " where per_estado = 'Activo';" : "");
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Perfil p = new Perfil();
                p.setPerId(rs.getInt("per_id"));
                p.setPerDescripcion(rs.getString("per_descripcion"));
                p.setPerEstado(rs.getString("per_estado"));
                list.add(p);
            }
            this.desconectar();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean modificar(Perfil p) {
        String sql = "UPDATE perfil SET " +
                "per_descripcion = ?, " +
                "per_estado = ? " +
                "WHERE per_id = ?; ";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getPerDescripcion());
            ps.setString(2, p.getPerEstado());
            ps.setInt(3, p.getPerId());
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
