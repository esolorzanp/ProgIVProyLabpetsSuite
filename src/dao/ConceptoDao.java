package dao;

import db.MySQLConnection;
import model.Concepto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConceptoDao extends MySQLConnection {
    public boolean adicionar(Concepto c) {
        String sql = "INSERT INTO concepto " +
                "(con_descripcion, " +
                "con_estado) " +
                "VALUES " +
                "(?, " +
                "?) ";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getConDescripcion());
            ps.setString(2, c.getConEstado());
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

    public boolean buscarId(Concepto c) {
        String sql = "SELECT con_id, " +
                "con_descripcion, " +
                "con_estado " +
                "FROM concepto " +
                "WHERE con_estado = 'Activo' AND" +
                " con_id = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getConId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c.setConId(rs.getInt("con_id"));
                c.setConDescripcion(rs.getString("con_descripcion"));
                c.setConEstado(rs.getString("con_estado"));
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

    public boolean eliminar(Concepto c) {
        String sql = "DELETE FROM concepto WHERE con_id = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getConId());
            int n = ps.executeUpdate();
            if (n > 0) {
                System.out.println("[ INFO ] Delete ejecutado con éxito");
                this.desconectar();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("[ ERROR ] Problemas al ejecutar Delete: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return false;
    }

    // opt: 0=Todos; 1=Solo activos
    public List<Concepto> getTodos(int optEstado) {
        List<Concepto> list = new ArrayList<>();
        String sql = "SELECT con_id, " +
                "con_descripcion, " +
                "con_estado " +
                "FROM concepto " +
                (optEstado == 1 ? " where con_estado = 'Activo';" : "");
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Concepto c = new Concepto();
                c.setConId(rs.getInt("con_id"));
                c.setConDescripcion(rs.getString("con_descripcion"));
                c.setConEstado(rs.getString("con_estado"));
                list.add(c);
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

    public boolean modificar(Concepto p) {
        String sql = "UPDATE concepto " +
                "SET " +
                "con_descripcion = ?, " +
                "con_estado = ? " +
                "WHERE con_id = ? ";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getConDescripcion());
            ps.setString(2, p.getConEstado());
            ps.setInt(3, p.getConId());
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
