package dao;

import db.MySQLConnection;
import model.OrdenServicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdenServicioDao extends MySQLConnection {
    public boolean adicionar(OrdenServicio os) {
        String sql = "INSERT INTO orden_servicio " +
                "(os_id, " +
                "os_fecha, " +
                "os_vet_id, " +
                "os_mas_id, " +
                "os_mas_propietario, " +
                "os_estado, " +
                "os_valor_total, " +
                "os_abono, " +
                "os_observaciones, " +
                "os_observacionesResultados, " +
                "os_usu_crea, " +
                "os_fecha_crea, " +
                "os_usu_anula, " +
                "os_fecha_anula, " +
                "os_razon_anula, " +
                "os_usuResultado_crea) " +
                "VALUES (" +
                "?, " +             // 1  os_fecha,
                "?, " +             // 2  os_vet_id,
                "?, " +             // 3  os_mas_id,
                "?, " +             // 4  os_mas_propietario,
                "?, " +             // 5  os_estado,
                "?, " +             // 6  os_valor_total,
                "?, " +             // 7  os_abono,
                "?, " +             // 8  os_observaciones,
                "?, " +             // 9  os_observacionesResultados,
                "?, " +             // 10 os_usu_crea,
                "?, " +             // 11 os_fecha_crea,
                "?, " +             // 12 os_usu_anula,
                "?, " +             // 13 os_fecha_anula,
                "?, " +             // 14 os_razon_anula,
                "? ";               // 15 os_usuResultado_crea
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, os.getOsFecha());
            ps.setInt(2, os.getOsVetId());
            ps.setInt(3, os.getOsMasId());
            ps.setString(4, os.getOsMasPropietario());
            ps.setString(5, os.getOsEstado());
            ps.setInt(6, os.getOsValorTotal());
            ps.setInt(7, os.getOsAbono());
            ps.setString(8, os.getOsObservaciones());
            ps.setString(9, os.getOsObservacionesResultados());
            ps.setString(10, os.getOsUsuCrea());
            ps.setDate(11, os.getOsFechaCrea());
            ps.setString(12, os.getOsUsuAnula());
            ps.setDate(13, os.getOsFechaAnula());
            ps.setString(14, os.getOsRazonAnula());
            ps.setString(15, os.getOsUsuResultadoCrea());
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

    public boolean buscarId(OrdenServicio os) {
        String sql = "SELECT os_id, " +
                "    os_fecha, " +
                "    os_vet_id, " +
                "    os_mas_id, " +
                "    os_mas_propietario, " +
                "    os_estado, " +
                "    os_valor_total, " +
                "    os_abono, " +
                "    os_observaciones, " +
                "    os_observacionesResultados, " +
                "    os_usu_crea, " +
                "    os_fecha_crea, " +
                "    os_usu_anula, " +
                "    os_fecha_anula, " +
                "    os_razon_anula, " +
                "    os_usuResultado_crea " +
                "FROM orden_servicio " +
                "WHERE os_estado = 'Activo' " +
                "AND os_id = ? ";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(0, os.getOsId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                os.setOsId(rs.getInt("os_id"));
                os.setOsFecha(rs.getDate("os_fecha"));
                os.setOsVetId(rs.getInt("os_vet_id"));
                os.setOsMasId(rs.getInt("os_mas_id"));
                os.setOsMasPropietario(rs.getString("os_mas_propietario"));
                os.setOsEstado(rs.getString("os_estado"));
                os.setOsValorTotal(rs.getInt("os_valor_total"));
                os.setOsAbono(rs.getInt("os_abono"));
                os.setOsObservaciones(rs.getString("os_observaciones"));
                os.setOsObservacionesResultados(rs.getString("os_observacionesResultados"));
                os.setOsUsuCrea(rs.getString("os_usu_crea"));
                os.setOsFechaCrea(rs.getDate("os_fecha_crea"));
                os.setOsUsuAnula(rs.getString("os_usu_anula"));
                os.setOsFechaAnula(rs.getDate("os_fecha_anula"));
                os.setOsRazonAnula(rs.getString("os_razon_anula"));
                os.setOsUsuResultadoCrea(rs.getString("os_usuResultado_crea"));
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

    public boolean eliminar(OrdenServicio os) {
        String sql = "DELETE FROM orden_servicio WHERE os_id ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, os.getOsId());
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
    public List<OrdenServicio> getTodos(int optEstado) {
        List<OrdenServicio> list = new ArrayList<>();
        String sql = "SELECT os_id, " +
                "    os_fecha, " +
                "    os_vet_id, " +
                "    os_mas_id, " +
                "    os_mas_propietario, " +
                "    os_estado, " +
                "    os_valor_total, " +
                "    os_abono, " +
                "    os_observaciones, " +
                "    os_observacionesResultados, " +
                "    os_usu_crea, " +
                "    os_fecha_crea, " +
                "    os_usu_anula, " +
                "    os_fecha_anula, " +
                "    os_razon_anula, " +
                "    os_usuResultado_crea " +
                "FROM orden_servicio "
                + (optEstado == 1 ? " where os_estado = 'Activo';" : "");
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrdenServicio os = new OrdenServicio();
                os.setOsId(rs.getInt("os_id"));
                os.setOsFecha(rs.getDate("os_fecha"));
                os.setOsVetId(rs.getInt("os_vet_id"));
                os.setOsMasId(rs.getInt("os_mas_id"));
                os.setOsMasPropietario(rs.getString("os_mas_propietario"));
                os.setOsEstado(rs.getString("os_estado"));
                os.setOsValorTotal(rs.getInt("os_valor_total"));
                os.setOsAbono(rs.getInt("os_abono"));
                os.setOsObservaciones(rs.getString("os_observaciones"));
                os.setOsObservacionesResultados(rs.getString("os_observacionesResultados"));
                os.setOsUsuCrea(rs.getString("os_usu_crea"));
                os.setOsFechaCrea(rs.getDate("os_fecha_crea"));
                os.setOsUsuAnula(rs.getString("os_usu_anula"));
                os.setOsFechaAnula(rs.getDate("os_fecha_anula"));
                os.setOsRazonAnula(rs.getString("os_razon_anula"));
                os.setOsUsuResultadoCrea(rs.getString("os_usuResultado_crea"));
                list.add(os);
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

    public boolean modificar(OrdenServicio os) {
        String sql = "UPDATE orden_servicio SET " +
                "os_fecha = ?, " +
                "os_vet_id = ?, " +
                "os_mas_id = ?, " +
                "os_mas_propietario = ?, " +
                "os_estado = ?, " +
                "os_valor_total = ?, " +
                "os_abono = ?, " +
                "os_observaciones = ?, " +
                "os_observacionesResultados = ?, " +
                "os_usu_crea = ?, " +
                "os_fecha_crea = ?, " +
                "os_usu_anula = ?, " +
                "os_fecha_anula = ?, " +
                "os_razon_anula = ?, " +
                "os_usuResultado_crea = ? " +
                "WHERE os_id = ?; ";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, os.getOsFecha());
            ps.setInt(2, os.getOsVetId());
            ps.setInt(3, os.getOsMasId());
            ps.setString(4, os.getOsMasPropietario());
            ps.setString(5, os.getOsEstado());
            ps.setInt(6, os.getOsValorTotal());
            ps.setInt(7, os.getOsAbono());
            ps.setString(8, os.getOsObservaciones());
            ps.setString(9, os.getOsObservacionesResultados());
            ps.setString(10, os.getOsUsuCrea());
            ps.setDate(11, os.getOsFechaCrea());
            ps.setString(12, os.getOsUsuAnula());
            ps.setDate(13, os.getOsFechaAnula());
            ps.setString(14, os.getOsRazonAnula());
            ps.setString(15, os.getOsUsuResultadoCrea());
            ps.setInt(16, os.getOsId());
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
