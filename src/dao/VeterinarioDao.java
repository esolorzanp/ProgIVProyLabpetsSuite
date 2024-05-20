package dao;

import db.MySQLConnection;
import model.Veterinario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDao extends MySQLConnection {
    public boolean adicionar(Veterinario v) {
        String sql = "INSERT INTO veterinario " +
                "(vet_nombre, " +
                "vet_email, " +
                "vet_tel, " +
                "vet_veterinaria, " +
                "vet_direccion, " +
                "vet_estado, " +
                "usu_crea, " +
                "fecha_crea, " +
                "usu_anula, " +
                "fecha_anula) " +
                "VALUES " +
                "(?, " +        // 1 vet_nombre,
                "?, " +         // 2 vet_email,
                "?, " +         // 3 vet_tel,
                "?, " +         // 4 vet_veterinaria,
                "?, " +         // 5 vet_direccion,
                "?, " +         // 6 vet_estado,
                "?, " +         // 7 usu_crea,
                "?, " +         // 8 fecha_crea,
                "?, " +         // 9 usu_anula,
                "?); ";         // 10 fecha_anula
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, v.getVetNombre());
            ps.setString(2, v.getVetEmail());
            ps.setString(3, v.getVetTel());
            ps.setString(4, v.getVetVeterinaria());
            ps.setString(5, v.getVetDireccion());
            ps.setString(6, v.getVetEstado());
            ps.setString(7, v.getUsuCrea());
            ps.setDate(8, v.getFechaCrea());
            ps.setString(9, v.getUsuAnula());
            ps.setDate(10, v.getFechaAnula());
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

    public boolean buscarId(Veterinario v) {
        String sql = "SELECT vet_id, " +
                "vet_nombre, " +
                "vet_email, " +
                "vet_tel, " +
                "vet_veterinaria, " +
                "vet_direccion, " +
                "vet_estado, " +
                "usu_crea, " +
                "fecha_crea, " +
                "usu_anula, " +
                "fecha_anula " +
                "FROM veterinario " +
                "WHERE vet_estado = 'Activo' " +
                "AND vet_id = ? ";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, v.getVetId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                v.setVetId(rs.getInt("vet_id"));
                v.setVetNombre(rs.getString("vet_nombre"));
                v.setVetEmail(rs.getString("vet_email"));
                v.setVetTel(rs.getString("vet_tel"));
                v.setVetVeterinaria(rs.getString("vet_veterinaria"));
                v.setVetEstado(rs.getString("vet_estado"));
                v.setUsuCrea(rs.getString("usu_crea"));
                v.setFechaCrea(rs.getDate("fecha_crea"));
                v.setUsuAnula(rs.getString("usu_anula"));
                v.setFechaAnula(rs.getDate("fecha_anula"));
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

    public boolean eliminar(Veterinario v) {
        String sql = "DELETE FROM veterinario WHERE vet_id = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, v.getVetId());
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
    public List<Veterinario> getTodos(int optEstado) {
        List<Veterinario> list = new ArrayList<>();
        String sql = "SELECT vet_id, " +
                "vet_nombre, " +
                "vet_email, " +
                "vet_tel, " +
                "vet_veterinaria, " +
                "vet_direccion, " +
                "vet_estado, " +
                "usu_crea, " +
                "fecha_crea, " +
                "usu_anula, " +
                "fecha_anula " +
                "FROM veterinario " +
                (optEstado == 1 ? " WHERE vet_estado = 'Activo';" : "");
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Veterinario v = new Veterinario();
                v.setVetId(rs.getInt("vet_id"));
                v.setVetNombre(rs.getString("vet_nombre"));
                v.setVetEmail(rs.getString("vet_email"));
                v.setVetTel(rs.getString("vet_tel"));
                v.setVetVeterinaria(rs.getString("vet_veterinaria"));
                v.setVetDireccion(rs.getString("vet_direccion"));
                v.setVetEstado(rs.getString("vet_estado"));
                v.setUsuCrea(rs.getString("usu_crea"));
                v.setFechaCrea(rs.getDate("fecha_crea"));
                v.setUsuAnula(rs.getString("usu_anula"));
                v.setFechaAnula(rs.getDate("fecha_anula"));
                list.add(v);
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

    public boolean modificar(Veterinario v) {
        String sql = "UPDATE veterinario " +
                "SET " +
                "vet_nombre = ?, " +
                "vet_email = ?, " +
                "vet_tel = ?, " +
                "vet_veterinaria = ?, " +
                "vet_direccion = ?, " +
                "vet_estado = ?, " +
                "usu_crea = ?, " +
                "fecha_crea = ?, " +
                "usu_anula = ?, " +
                "fecha_anula = ? " +
                "WHERE vet_id = ? ";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, v.getVetNombre());
            ps.setString(2, v.getVetEmail());
            ps.setString(3, v.getVetTel());
            ps.setString(4, v.getVetVeterinaria());
            ps.setString(5, v.getVetDireccion());
            ps.setString(6, v.getVetEstado());
            ps.setString(7, v.getUsuCrea());
            ps.setDate(8, v.getFechaCrea());
            ps.setString(9, v.getUsuAnula());
            ps.setDate(10, v.getFechaAnula());
            ps.setInt(11, v.getVetId());
            int n = ps.executeUpdate();
            if (n > 0) {
                System.out.println("[ INFO ] Update ejecutado con éxito");
                this.desconectar();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("[ ERROR ] Problemas al ejecutar Update: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return false;
    }

}
