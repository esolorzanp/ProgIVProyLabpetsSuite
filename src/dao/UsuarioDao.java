package dao;

import db.MySQLConnection;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao extends MySQLConnection {
    public boolean adicionar(Usuario us) {
        String sql = "INSERT INTO usuario " +
                "(usu_identificacion, " +
                "usu_login, " +
                "usu_pass, " +
                "usu_nombre, " +
                "usu_apellido, " +
                "usu_email, " +
                "usu_dir, " +
                "usu_tel, " +
                "usu_estado, " +
                "per_id, " +
                "img_firma) " +
                "VALUES" +
                "(?, " +         // 1  usu_identificacion,
                "?, " +          // 2  usu_login,
                "?, " +          // 3  usu_pass,
                "?, " +          // 4  usu_nombre,
                "?, " +          // 5  usu_apellido,
                "?, " +          // 6  usu_email,
                "?, " +          // 7  usu_dir,
                "?, " +          // 8  usu_tel,
                "?, " +          // 9  usu_estado,
                "?, " +          // 10 per_id,
                "?) ";           // 11 img_firma
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, us.getUsuIdentificacion());
            ps.setString(2, us.getUsuLogin());
            ps.setString(3, us.getUsuPass());
            ps.setString(4, us.getUsuNombre());
            ps.setString(5, us.getUsuApellido());
            ps.setString(6, us.getUsuEmail());
            ps.setString(7, us.getUsuDir());
            ps.setString(8, us.getUsuTel());
            ps.setString(9, us.getUsuEstado());
            ps.setInt(10, us.getPerId());
            ps.setString(11, us.getImgFirma());
            int n = ps.executeUpdate();
            if (n > 0) {
                System.out.println("[ INFO ] Insert ejecutado con éxito");
                this.desconectar();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("[ ERROR ] Problemas al ejecutar Insert: " + e.getMessage());
            e.printStackTrace();
        }
        this.desconectar();
        return false;
    }

    public boolean buscarId(Usuario us) {
        String sql = "SELECT usu_id, " +
                "usu_identificacion, " +
                "usu_login, " +
                "usu_pass, " +
                "usu_nombre, " +
                "usu_apellido, " +
                "usu_email, " +
                "usu_dir, " +
                "usu_tel, " +
                "usu_estado, " +
                "per_id, " +
                "img_firma " +
                "FROM usuario " +
                "WHERE usu_estado = 'Activo' " +
                "AND usu_id = ?;";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, us.getUsuId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                us.setUsuId(rs.getInt("usu_id"));
                us.setUsuIdentificacion(rs.getString("usu_identificacion"));
                us.setUsuLogin(rs.getString("usu_login"));
                us.setUsuPass(rs.getString("usu_pass"));
                us.setUsuNombre(rs.getString("usu_nombre"));
                us.setUsuApellido(rs.getString("usu_apellido"));
                us.setUsuEmail(rs.getString("usu_email"));
                us.setUsuDir(rs.getString("usu_dir"));
                us.setUsuTel(rs.getString("usu_tel"));
                us.setUsuEstado(rs.getString("usu_estado"));
                us.setPerId(rs.getInt("per_id"));
                us.setImgFirma(rs.getString("img_firma"));
                this.desconectar();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.desconectar();
        return false;
    }

    public boolean buscarLogin(Usuario us) {
        String sql = "SELECT usu_id, " +
                "usu_identificacion, " +
                "usu_login, " +
                "usu_pass, " +
                "usu_nombre, " +
                "usu_apellido, " +
                "usu_email, " +
                "usu_dir, " +
                "usu_tel, " +
                "usu_estado, " +
                "per_id, " +
                "img_firma " +
                "FROM usuario " +
                "WHERE usu_estado = 'Activo' " +
                "AND usu_login = ? ";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, us.getUsuLogin());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                us.setUsuId(rs.getInt("usu_id"));
                us.setUsuIdentificacion(rs.getString("usu_identificacion"));
                us.setUsuLogin(rs.getString("usu_login"));
                us.setUsuPass(rs.getString("usu_pass"));
                us.setUsuNombre(rs.getString("usu_nombre"));
                us.setUsuApellido(rs.getString("usu_apellido"));
                us.setUsuEmail(rs.getString("usu_email"));
                us.setUsuDir(rs.getString("usu_dir"));
                us.setUsuTel(rs.getString("usu_tel"));
                us.setUsuEstado(rs.getString("usu_estado"));
                us.setPerId(rs.getInt("per_id"));
                us.setImgFirma(rs.getString("img_firma"));
                this.desconectar();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.desconectar();
        return false;
    }

    public boolean eliminar(Usuario us) {
        String sql = "DELETE FROM Usuario WHERE usu_id = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, us.getUsuId());
            int n = ps.executeUpdate();
            if (n > 0) {
                System.out.println("[ INFO ] Delete ejecutado con éxito");
                this.desconectar();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("[ ERROR ] Problemas al ejecutar Delete: " + e.getMessage());
            e.printStackTrace();
        }
        this.desconectar();
        return false;
    }

    // opt: 0=Todos; 1=Solo activos
    public List<Usuario> getTodos(int optEstado) {
        List<Usuario> list = new ArrayList<>();
        String sql = "SELECT usu_id, " +
                "usu_identificacion, " +
                "usu_login, " +
                "usu_pass, " +
                "usu_nombre, " +
                "usu_apellido, " +
                "usu_email, " +
                "usu_dir, " +
                "usu_tel, " +
                "usu_estado, " +
                "per_id, " +
                "img_firma, " +
                "FROM usuario "
                + (optEstado == 1 ? " where usu_estado = 'Activo';" : "");
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario us = new Usuario();
                us.setUsuId(rs.getInt("usu_id"));
                us.setUsuIdentificacion(rs.getString("usu_identificacion"));
                us.setUsuLogin(rs.getString("usu_login"));
                us.setUsuPass(rs.getString("usu_pass"));
                us.setUsuNombre(rs.getString("usu_nombre"));
                us.setUsuApellido(rs.getString("usu_apellido"));
                us.setUsuEmail(rs.getString("usu_email"));
                us.setUsuDir(rs.getString("usu_dir"));
                us.setUsuTel(rs.getString("usu_tel"));
                us.setUsuEstado(rs.getString("usu_estado"));
                us.setPerId(rs.getInt("per_id"));
                us.setImgFirma(rs.getString("img_firma"));
                list.add(us);
            }
            this.desconectar();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.desconectar();
        return null;
    }

    public boolean modificar(Usuario us) {
        String sql = "UPDATE usuario " +
                "SET " +
                "usu_id = ?, " +
                "usu_identificacion = ?, " +
                "usu_login = ?, " +
                "usu_pass = ?, " +
                "usu_nombre = ?, " +
                "usu_apellido = ?, " +
                "usu_email = ?, " +
                "usu_dir = ?, " +
                "usu_tel = ?, " +
                "usu_estado = ?, " +
                "per_id = ?, " +
                "img_firma = ? " +
                "WHERE usu_id = ?; ";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, us.getUsuIdentificacion());
            ps.setString(2, us.getUsuLogin());
            ps.setString(3, us.getUsuPass());
            ps.setString(4, us.getUsuNombre());
            ps.setString(5, us.getUsuApellido());
            ps.setString(6, us.getUsuEmail());
            ps.setString(7, us.getUsuDir());
            ps.setString(8, us.getUsuTel());
            ps.setString(9, us.getUsuEstado());
            ps.setInt(10, us.getPerId());
            ps.setString(11, us.getImgFirma());
            ps.setInt(12, us.getUsuId());
            int n = ps.executeUpdate();
            if (n > 0) {
                System.out.println("[ INFO ] Update ejecutado con éxito");
                this.desconectar();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("[ ERROR ] Problemas al ejecutar Update: " + e.getMessage());
            e.printStackTrace();
        }
        this.desconectar();
        return false;
    }

}
