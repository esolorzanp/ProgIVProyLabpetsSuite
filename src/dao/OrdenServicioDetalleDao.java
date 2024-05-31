package dao;

import db.MySQLConnection;
import model.OrdenServicioDetalle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdenServicioDetalleDao extends MySQLConnection {
    public boolean adicionar(OrdenServicioDetalle osd) {
        String sql = "INSERT INTO orden_servicio_detalle " +
                "(dos_os_id, " +
                "dos_exa_id, " +
                "dos_exa_valor, " +
                "dos_exa_cantidad, " +
                "dos_exa_observaciones) " +
                "VALUES " +
                "(?, " +             // 1 dos_os_id
                "?, " +             // 2 dos_exa_id
                "?, " +             // 3 dos_exa_valor
                "?, " +             // 4 dos_exa_cantidad
                "?); ";             // 5 dos_exa_observaciones
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, osd.getDosOsId());
            ps.setInt(2, osd.getDosExaId());
            ps.setInt(3, osd.getDosExaValor());
            ps.setString(4, osd.getDosExaCantidad());
            ps.setString(5, osd.getDosExaObservaciones());
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

    public boolean buscarId(OrdenServicioDetalle osd) {
        String sql = "SELECT dos_id, " +
                "    dos_os_id, " +
                "    dos_exa_id, " +
                "    dos_exa_valor, " +
                "    dos_exa_cantidad, " +
                "    dos_exa_observaciones " +
                "FROM orden_servicio_detalle " +
                "WHERE dos_os_id = ? ";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, osd.getDosId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                osd.setDosId(rs.getInt("dos_id"));
                osd.setDosOsId(rs.getInt("dos_os_id"));
                osd.setDosExaId(rs.getInt("dos_exa_id"));
                osd.setDosExaValor(rs.getInt("dos_exa_valor"));
                osd.setDosExaCantidad(rs.getString("dos_exa_cantidad"));
                osd.setDosExaObservaciones(rs.getString("dos_exa_observaciones"));
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

    public boolean eliminar(OrdenServicioDetalle osd) {
        String sql = "DELETE FROM orden_servicio_detalle WHERE dos_os_id ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, osd.getDosId());
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
        finally {
            this.desconectar();
        }
        return false;
    }

    // opt: 0=Todos; 1=Solo activos
    public List<OrdenServicioDetalle> getTodos(int optEstado) {
        List<OrdenServicioDetalle> list = new ArrayList<>();
        String sql = "SELECT dos_id, " +
                "    dos_os_id, " +
                "    dos_exa_id, " +
                "    dos_exa_valor, " +
                "    dos_exa_cantidad, " +
                "    dos_exa_observaciones " +
                "FROM orden_servicio_detalle " +
                "WHERE dos_os_id = ? ";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrdenServicioDetalle osd = new OrdenServicioDetalle();
                osd.setDosId(rs.getInt("dos_id"));
                osd.setDosOsId(rs.getInt("dos_os_id"));
                osd.setDosExaId(rs.getInt("dos_exa_id"));
                osd.setDosExaValor(rs.getInt("dos_exa_valor"));
                osd.setDosExaCantidad(rs.getString("dos_exa_cantidad"));
                osd.setDosExaObservaciones(rs.getString("dos_exa_observaciones"));
                list.add(osd);
            }
            this.desconectar();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.desconectar();
        }
        return null;
    }

    public boolean modificar(OrdenServicioDetalle osd) {
        String sql = "UPDATE orden_servicio_detalle " +
                "SET " +
                "dos_os_id = ?, " +
                "dos_exa_id = ?, " +
                "dos_exa_valor = ?, " +
                "dos_exa_cantidad = ?, " +
                "dos_exa_observaciones = ? " +
                "WHERE dos_id = ?; ";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, osd.getDosId());
            ps.setInt(2, osd.getDosOsId());
            ps.setInt(3, osd.getDosExaId());
            ps.setInt(4, osd.getDosExaValor());
            ps.setString(5, osd.getDosExaCantidad());
            ps.setString(6, osd.getDosExaObservaciones());
            int n = ps.executeUpdate();
            if (n > 0) {
                System.out.println("[ INFO ] Update ejecutado con éxito");
                this.desconectar();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("[ ERROR ] Problemas al ejecutar Update: " + e.getMessage());
            e.printStackTrace();
        }finally {
            this.desconectar();
        }
        return false;
    }

}
