package dao;

import db.MySQLConnection;
import model.FormaPago;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormaPagoDao extends MySQLConnection {
    public boolean adicionar(FormaPago fp) {
        String sql = "INSERT INTO forma_pago " +
                "(fp_descripcion, " +
                "fp_estado) " +
                "VALUES " +
                "(?, " +         // 2 fp_descripcion,
                "?); ";          // 3 fp_estado
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fp.getFpDescripcion());
            ps.setString(2, fp.getFpEstado());
            int n = ps.executeUpdate();
            if (n > 0) {
                System.out.println("[ INFO ] Insert ejecutado con éxito");
                return true;
            }
            this.desconectar();
        } catch (SQLException e) {
            System.out.println("[ ERROR ] Problemas al ejecutar Insert: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return false;
    }

    public boolean buscarId(FormaPago fp) {
        String sql = "SELECT fp_id, " +
                "    fp_descripcion, " +
                "    fp_estado " +
                "FROM forma_pago " +
                "WHERE fp_estado = 'Activo' " +
                "AND fp_id = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, fp.getFpId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                fp.setFpId(rs.getInt("fp_id"));
                fp.setFpDescripcion(rs.getString("fp_descripcion"));
                fp.setFpEstado(rs.getString("fp_estado"));
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

    public boolean buscarByDescripcion(FormaPago fp) {
        String sql = "SELECT fp_id, " +
                "    fp_descripcion, " +
                "    fp_estado " +
                "FROM forma_pago " +
                "WHERE exa_estado = 'Activo' " +
                "AND exa_descripcion = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fp.getFpDescripcion());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                fp.setFpId(rs.getInt("fp_id"));
                fp.setFpDescripcion(rs.getString("fp_descripcion"));
                fp.setFpEstado(rs.getString("fp_estado"));
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

    public boolean eliminar(FormaPago ex) {
        String sql = "DELETE FROM forma_pago WHERE fp_id = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ex.getFpId());
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
    public List<FormaPago> getTodos(int optEstado) {
        List<FormaPago> list = new ArrayList<>();
        String sql = "SELECT fp_id, " +
                "    fp_descripcion, " +
                "    fp_estado " +
                "FROM forma_pago " +
                (optEstado == 1 ? " where fp_estado = 'Activo';" : "");
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FormaPago fp = new FormaPago();
                fp.setFpId(rs.getInt("fp_id"));
                fp.setFpDescripcion(rs.getString("fp_descripcion"));
                fp.setFpEstado(rs.getString("fp_estado"));
                list.add(fp);
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

    public boolean modificar(FormaPago fp) {
        String sql = "UPDATE forma_pago " +
                "SET " +
                "fp_descripcion = ?, " +
                "fp_estado = ? " +
                "WHERE fp_id = ?; ";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fp.getFpDescripcion());
            ps.setString(2, fp.getFpEstado());
            ps.setInt(3, fp.getFpId());
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
