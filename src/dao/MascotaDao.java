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
        String sql = "INSERT INTO mascota " +
                "(mas_id, " +
                "mas_nombre, " +
                "mas_edad, " +
                "mas_propietario, " +
                "mas_sexo, " +
                "raza_id, " +
                "usu_crea, " +
                "fecha_crea, " +
                "usu_anula, " +
                "fecha_anula, " +
                "mas_estado) " +
                "VALUES " +
                "(?, " +           //  0 mas_id
                "?, " +            //  1 mas_nombre
                "?, " +            //  2 mas_edad
                "?, " +            //  3 mas_propietario
                "?, " +            //  4 mas_sexo
                "?, " +            //  5 raza_id
                "?, " +            //  6 usu_crea
                "?, " +            //  7 fecha_crea
                "?, " +            //  8 usu_anula
                "?, " +            //  9 fecha_anula
                "?)";               // 10 mas_estado
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, m.getMasNombre());
            ps.setInt(2, m.getMasEdad());
            ps.setString(3, m.getMasPropietario());
            ps.setString(4, m.getMasSexo());
            ps.setInt(5, m.getRazaId());
            ps.setString(6, m.getUsuCrea());
            ps.setDate(7, m.getFechaCrea());
            ps.setString(8, m.getUsuAnula());
            ps.setDate(9, m.getFechaAnula());
            ps.setString(10, m.getMasEstado());
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
        String sql = "SELECT mas_id," +
                "    mas_nombre, " +
                "    mas_edad, " +
                "    mas_propietario, " +
                "    mas_sexo, " +
                "    raza_id, " +
                "    usu_crea, " +
                "    fecha_crea, " +
                "    usu_anula, " +
                "    fecha_anula, " +
                "    mas_estado " +
                "FROM mascota " +
                "WHERE mas_id ) ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, m.getMasId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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
                this.desconectar();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminar(Mascota m) {
        String sql = "DELETE FROM mascota WHERE mas_id = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(0, m.getMasId());
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

    // opt: 0=Todos; 1=Solo activos
    public List<Mascota> getTodos(int optEstado) {
        List<Mascota> list = new ArrayList<>();
        String sql = "SELECT mas_id, " +
                "    mas_nombre, " +
                "    mas_edad, " +
                "    mas_propietario, " +
                "    mas_sexo, " +
                "    raza_id, " +
                "    usu_crea, " +
                "    fecha_crea, " +
                "    usu_anula, " +
                "    fecha_anula, " +
                "    mas_estado " +
                "FROM mascota "
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
        String sql = "UPDATE mascota " +
                "SET " +
                "mas_nombre = ?, " +
                "mas_edad = ?, " +
                "mas_propietario = ?, " +
                "mas_sexo = ?, " +
                "raza_id = ?, " +
                "usu_crea = ?, " +
                "fecha_crea = ?, " +
                "usu_anula = ?, " +
                "fecha_anula = ?, " +
                "mas_estado = ? " +
                "WHERE mas_id = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(0, m.getMasNombre());
            ps.setInt(1, m.getMasEdad());
            ps.setString(2, m.getMasPropietario());
            ps.setString(3, m.getMasSexo());
            ps.setInt(4, m.getRazaId());
            ps.setString(5, m.getUsuCrea());
            ps.setDate(6, m.getFechaCrea());
            ps.setString(7, m.getUsuAnula());
            ps.setDate(8, m.getFechaAnula());
            ps.setString(9, m.getMasEstado());
            ps.setInt(10, m.getMasId());
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
