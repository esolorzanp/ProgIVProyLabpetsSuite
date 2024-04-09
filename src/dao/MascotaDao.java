package dao;

import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException;
import db.MySQLConnection;
import model.Mascota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MascotaDao extends MySQLConnection {
    public boolean adicionar(Mascota m) {
        String sql = "INSERT INTO m\n" +
                "(exa_id,\n" +
                "exa_descripcion,\n" +
                "exa_valor,\n" +
                "exa_tipo,\n" +
                "exa_estado,\n" +
                "exa_usu_anu,\n" +
                "exa_fecha_anu)\n" +
                "VALUES\n" +
                "?,\n" +            // 0 exa_id
                "?,\n" +            // 1 exa_descripcion
                "?,\n" +            // 2 exa_valor
                "?,\n" +            // 3 exa_tipo
                "?,\n" +            // 4 exa_estado
                "?,\n" +            // 5 exa_usu_anu
                "?);\n";            // 6 exa_fecha_anu
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, m.getExaDescripcion());
            ps.setInt(2, m.getExaValor());
            ps.setString(3, m.getExaTipo());
            ps.setString(4, m.getExaEstado());
            ps.setString(5, m.getExaUsuAnu());
            ps.setTimestamp(6, m.getExaFechaAnu());
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

    public boolean buscarId(Mascota m) {
        String sql = "SELECT exa_id,\n" +
                "    exa_descripcion,\n" +
                "    exa_valor,\n" +
                "    exa_tipo,\n" +
                "    exa_estado,\n" +
                "    exa_usu_anu,\n" +
                "    exa_fecha_anu\n" +
                "FROM m\n" +
                "WHERE exa_estado = 'Activo'\n" +
                "AND exa_id = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, m.getExaId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                m.setExaId(rs.getInt("exa_id"));
                m.setExaValor(rs.getInt("exa_valor"));
                m.setExaTipo(rs.getString("exa_tipo"));
                m.setExaEstado(rs.getString("exa_estado"));
                m.setExaUsuAnu(rs.getString("exa_usu_anu"));
                m.setExaFechaAnu(rs.getTimestamp("exa_fecha_anu"));
                this.desconectar();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminar(Mascota m) {
        String sql = "DELETE FROM examen WHERE ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(0, m.getExaId());
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

    //    public Boolean exist(Examen examen) {
//        String sql = "SELECT esp_id, esp_descripcion, esp_estado FROM examen WHERE esp_id = ?";
//        Connection conn = this.conectar();
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, examen.getEspId());
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                examen.setEspId(rs.getInt("esp_id"));
//                examen.setEspDescripcion(rs.getString("esp_descripcion"));
//                examen.setEspEstado(rs.getString("esp_estado"));
//                this.desconectar();
//                return true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    // opt: 0=Todos; 1=Solo activos
    public List<Mascota> getTodos(int optEstado) {
        List<Mascota> list = new ArrayList<>();
        String sql = "SELECT mas_id,\n" +
                "    mas_nombre,\n" +
                "    mas_edad,\n" +
                "    mas_propietario,\n" +
                "    mas_sexo,\n" +
                "    raza_id,\n" +
                "    usu_crea,\n" +
                "    fecha_crea,\n" +
                "    usu_anula,\n" +
                "    fecha_anula,\n" +
                "    mas_estado\n" +
                "FROM mascota\n"
                + (optEstado == 1 ? " WHERE mas_estado = 'Activo';" : "");
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if (optEstado == 1)
                ps.setString(1, "Activo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mascota m = new Mascota();
                m.setMasId(rs.getInt("mas_id"));
                m.setMasNombre(rs.getString("mas_nombre"));
                m.setMasEdad(rs.getInt("mas_edad"));
                m.setMasPropietario(rs.getString("mas_propietario"));
                m.setMasSexo(rs.getString("mas_sexo"));
                m.setRazaId(rs.getInt("raza_id"));
                m.setUsuCrea(rs.getString("usu_crea"));
                m.setFechaCrea(rs.getDate("fecha_crea"));
                m.setUsuAnula(rs.getString("usu_anula"));
                m.setFechaAnula(rs.getDate("fecha_anula"));
                m.setMasEstado(rs.getString("mas_estado"));
                list.add(m);
            }
            this.desconectar();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean modificar(Mascota m) {
        String sql = "UPDATE labpets2.m\n" +
                "SET\n" +
                "exa_descripcion = ?,\n" +
                "exa_valor = ?,\n" +
                "exa_tipo = ?,\n" +
                "exa_estado = ?,\n" +
                "exa_usu_anu = ?,\n" +
                "exa_fecha_anu = ?\n" +
                "WHERE exa_id = ?;\n";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(0, m.getExaDescripcion());
            ps.setInt(1, m.getExaValor());
            ps.setString(2, m.getExaTipo());
            ps.setString(3, m.getExaEstado());
            ps.setString(4, m.getExaUsuAnu());
            ps.setTimestamp(5, m.getExaFechaAnu());
            ps.setInt(6, m.getExaId());
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
