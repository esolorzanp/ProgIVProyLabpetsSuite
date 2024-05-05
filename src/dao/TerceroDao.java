package dao;

import db.MySQLConnection;
import model.Tercero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TerceroDao extends MySQLConnection {
    public boolean adicionar(Tercero t) {
        String sql = "INSERT INTO terceros " +
                "(ti_descripcion, " +
                "identificacion, " +
                "representante_legal, " +
                "razon_social, " +
                "direccion, " +
                "tel1, " +
                "tel2, " +
                "observaciones, " +
                "usu_crea, " +
                "fecha_crea, " +
                "usu_anula, " +
                "fecha_anula, " +
                "razon_anula, " +
                "estado) " +
                "VALUES " +
                "(?, " +            // 1 ti_descripcion,
                "?, " +             // 2 identificacion,
                "?, " +             // 3 representante_legal,
                "?, " +             // 4 razon_social,
                "?, " +             // 5 direccion,
                "?, " +             // 6 tel1,
                "?, " +             // 7 tel2,
                "?, " +             // 8 observaciones,
                "?, " +             // 9 usu_crea,
                "?, " +             // 10 fecha_crea,
                "?, " +             // 11 usu_anula,
                "?, " +             // 12 fecha_anula,
                "?, " +             // 13 razon_anula,
                "?) ";              // 14 estado
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getTiDescripcion());
            ps.setString(2, t.getIdentificacion());
            ps.setString(3, t.getRepresentanteLegal());
            ps.setString(4, t.getRazonSocial());
            ps.setString(5, t.getDireccion());
            ps.setString(6, t.getTel1());
            ps.setString(7, t.getTel2());
            ps.setString(8, t.getObservaciones());
            ps.setString(9, t.getUsuCrea());
            ps.setTimestamp(10, t.getFechaCrea());
            ps.setString(11, t.getUsuAnula());
            ps.setTimestamp(12, t.getFechaAnula());
            ps.setString(13, t.getRazonAnula());
            ps.setString(14, t.getEstado());
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

    public boolean buscarId(Tercero t) {
        String sql = "SELECT id, " +
                "ti_descripcion, " +
                "identificacion, " +
                "representante_legal, " +
                "razon_social, " +
                "direccion, " +
                "tel1, " +
                "tel2, " +
                "observaciones, " +
                "usu_crea, " +
                "fecha_crea, " +
                "usu_anula, " +
                "fecha_anula, " +
                "razon_anula, " +
                "estado " +
                "FROM terceros " +
                "WHERE " +
//                "estado = 'Activo' " +
//                "AND " +
                "id = ? ";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, t.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                t.setId(rs.getInt("id"));
                t.setTiDescripcion(rs.getString("ti_descripcion"));
                t.setIdentificacion(rs.getString("identificacion"));
                t.setRepresentanteLegal(rs.getString("representante_legal"));
                t.setDireccion(rs.getString("direccion"));
                t.setTel1(rs.getString("tel1"));
                t.setTel2(rs.getString("tel2"));
                t.setObservaciones(rs.getString("observaciones"));
                t.setUsuCrea(rs.getString("usu_crea"));
                t.setFechaCrea(rs.getTimestamp("fecha_crea"));
                t.setUsuAnula(rs.getString("usu_anula"));
//                t.setFechaAnula(rs.getTimestamp("fecha_anula"));
                t.setRazonAnula(rs.getString("razon_anula"));
                t.setEstado(rs.getString("estado"));
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

    public boolean eliminar(Tercero t) {
        String sql = "DELETE FROM terceros WHERE id = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, t.getId());
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
    public List<Tercero> getTodos(int optEstado) {
        List<Tercero> list = new ArrayList<>();
        String sql = "SELECT id, " +
                "ti_descripcion, " +
                "identificacion, " +
                "representante_legal, " +
                "razon_social, " +
                "direccion, " +
                "tel1, " +
                "tel2, " +
                "observaciones, " +
                "usu_crea, " +
                "fecha_crea, " +
                "usu_anula, " +
                "fecha_anula, " +
                "razon_anula, " +
                "estado " +
                "FROM terceros ";
//                + (optEstado == 1 ? " WHERE estado = 'Activo'" : "");
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tercero t = new Tercero();
                t.setId(rs.getInt("id"));
                t.setTiDescripcion(rs.getString("ti_descripcion"));
                t.setIdentificacion(rs.getString("identificacion"));
                t.setRepresentanteLegal(rs.getString("representante_legal"));
                t.setDireccion(rs.getString("direccion"));
                t.setTel1(rs.getString("tel1"));
                t.setTel2(rs.getString("tel2"));
                t.setObservaciones(rs.getString("observaciones"));
                t.setUsuCrea(rs.getString("usu_crea"));
                t.setFechaCrea(rs.getTimestamp("fecha_crea"));
                t.setUsuAnula(rs.getString("usu_anula"));
//                t.setFechaAnula(rs.getTimestamp("fecha_anula"));
                t.setRazonAnula(rs.getString("razon_anula"));
                t.setEstado(rs.getString("estado"));
                list.add(t);
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

    public boolean modificar(Tercero t) {
        String sql = "UPDATE terceros " +
                "SET " +
                "ti_descripcion = ?, " +
                "identificacion = ?, " +
                "representante_legal = ?, " +
                "razon_social = ?, " +
                "direccion = ?, " +
                "tel1 = ?, " +
                "tel2 = ?, " +
                "observaciones = ?, " +
                "usu_crea = ?, " +
                "fecha_crea = ?, " +
                "usu_anula = ?, " +
                "fecha_anula = ?, " +
                "razon_anula = ?, " +
                "estado = ? " +
                "WHERE id = ?; ";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getTiDescripcion());
            ps.setString(2, t.getIdentificacion());
            ps.setString(3, t.getRepresentanteLegal());
            ps.setString(4, t.getRazonSocial());
            ps.setString(5, t.getDireccion());
            ps.setString(6, t.getTel1());
            ps.setString(7, t.getTel2());
            ps.setString(8, t.getObservaciones());
            ps.setString(9, t.getUsuCrea());
            ps.setTimestamp(10, t.getFechaCrea());
            ps.setString(11, t.getUsuAnula());
            ps.setTimestamp(12, t.getFechaAnula());
            ps.setString(13, t.getRazonAnula());
            ps.setString(14, t.getEstado());
            ps.setInt(15, t.getId());
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
