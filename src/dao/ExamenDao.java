package dao;

import db.MySQLConnection;
import model.Examen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamenDao extends MySQLConnection {
    public boolean adicionar(Examen ex) {
        String sql = "INSERT INTO ex\n" +
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
            ps.setString(1, ex.getExaDescripcion());
            ps.setInt(2, ex.getExaValor());
            ps.setString(3, ex.getExaTipo());
            ps.setString(4, ex.getExaEstado());
            ps.setString(5, ex.getExaUsuAnu());
            ps.setTimestamp(6, ex.getExaFechaAnu());
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

    public boolean buscarId(Examen ex) {
        String sql = "SELECT exa_id,\n" +
                "    exa_descripcion,\n" +
                "    exa_valor,\n" +
                "    exa_tipo,\n" +
                "    exa_estado,\n" +
                "    exa_usu_anu,\n" +
                "    exa_fecha_anu\n" +
                "FROM ex\n" +
                "WHERE exa_estado = 'Activo'\n" +
                "AND exa_id = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ex.getExaId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ex.setExaId(rs.getInt("exa_id"));
                ex.setExaValor(rs.getInt("exa_valor"));
                ex.setExaTipo(rs.getString("exa_tipo"));
                ex.setExaEstado(rs.getString("exa_estado"));
                ex.setExaUsuAnu(rs.getString("exa_usu_anu"));
                ex.setExaFechaAnu(rs.getTimestamp("exa_fecha_anu"));
                this.desconectar();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminar(Examen ex) {
        String sql = "DELETE FROM examen WHERE ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(0, ex.getExaId());
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
    public List<Examen> getTodos(int optEstado) {
        List<Examen> list = new ArrayList<>();
        String sql = "SELECT exa_id,\n" +
                "    exa_descripcion,\n" +
                "    exa_valor,\n" +
                "    exa_tipo,\n" +
                "    exa_estado,\n" +
                "    exa_usu_anu,\n" +
                "    exa_fecha_anu\n" +
                "FROM examen\n"
                + (optEstado == 1 ? " where exa_estado = 'Activo';" : "");
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if (optEstado == 1)
                ps.setString(1, "Activo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Examen e = new Examen();
                e.setExaId(rs.getInt("exa_id"));
                e.setExaValor(rs.getInt("exa_valor"));
                e.setExaTipo(rs.getString("exa_tipo"));
                e.setExaEstado(rs.getString("exa_estado"));
                e.setExaUsuAnu(rs.getString("exa_usu_anu"));
                e.setExaFechaAnu(rs.getTimestamp("exa_fecha_anu"));
                list.add(e);
            }
            this.desconectar();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean modificar(Examen ex) {
        String sql = "UPDATE labpets2.ex\n" +
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
            ps.setString(0, ex.getExaDescripcion());
            ps.setInt(1, ex.getExaValor());
            ps.setString(2, ex.getExaTipo());
            ps.setString(3, ex.getExaEstado());
            ps.setString(4, ex.getExaUsuAnu());
            ps.setTimestamp(5, ex.getExaFechaAnu());
            ps.setInt(6, ex.getExaId());
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
