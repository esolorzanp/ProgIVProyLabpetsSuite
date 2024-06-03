package dao;

import db.MySQLConnection;
import model.OrdenesServicioResultado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdenesServicioResultadoDao extends MySQLConnection {
    public boolean adicionar(OrdenesServicioResultado osr) {
        String sql = "INSERT INTO orden_servicio_resultados " +
                "(os_id, " +
                "dos_exa_id, " +
                "para_id, " +
                "osr_resultado, " +
                "usu_crea, " +
                "fecha_crea) " +
                "VALUES " +
                "(?, " +        // 1 os_id,
                "?, " +         // 2 dos_exa_id,
                "?, " +         // 3 para_id,
                "?, " +         // 4 osr_resultado,
                "?, " +         // 5 usu_crea,
                "?); ";         // 6 fecha_crea
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, osr.getOsId());
            ps.setInt(2, osr.getDosExaId());
            ps.setInt(3, osr.getParaId());
            ps.setString(4, osr.getOsrResultado());
            ps.setString(5, osr.getUsuCrea());
            ps.setTimestamp(6, osr.getFechaCrea());
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
}
