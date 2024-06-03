package dao;

import db.MySQLConnection;
import model.Examen;
import model.ExamenParametro;
import model.Parametro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParametroDao extends MySQLConnection {
    public boolean adicionar(Parametro p) {
        String sql = "INSERT INTO parametros " +
                "(para_descripcion, " +
                "para_unidad, " +
                "para_estado, " +
                "usu_crea, " +
                "fecha_crea, " +
                "para_usu_anu, " +
                "para_fecha_anu, " +
                "para_formula, " +
                "para_referencia1, " +
                "para_referencia2) " +
                "VALUES " +
                "(?, " +            //  1 para_descripcion
                "?, " +             //  2 para_unidad
                "?, " +             //  3 para_estado
                "?, " +             //  4 usu_crea
                "?, " +             //  5 fecha_crea
                "?, " +             //  6 para_usu_anu
                "?, " +             //  7 para_fecha_anu
                "?, " +             //  8 para_formula
                "?, " +             //  9 para_referencia1
                "?) ";              // 10 para_referencia2
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getParaDescripcion());
            ps.setString(2, p.getParaUnidad());
            ps.setString(3, p.getParaEstado());
            ps.setString(4, p.getUsuCrea());
            ps.setTimestamp(5, p.getFechaCrea());
            ps.setString(6, p.getParaUsuAnula());
            ps.setTimestamp(7, p.getParaFechaAnula());
            ps.setString(8, p.getParaFormula());
            ps.setString(9, p.getParaReferencia1());
            ps.setString(10, p.getParaReferencia2());
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

    public boolean buscarId(Parametro p) {
        String sql = "SELECT para_id, " +
                "para_descripcion, " +
                "para_unidad, " +
                "para_estado, " +
                "usu_crea, " +
                "fecha_crea, " +
                "para_usu_anu, " +
                "para_fecha_anu, " +
                "para_formula, " +
                "para_referencia1, " +
                "para_referencia2 " +
                "FROM parametros " +
                "WHERE para_id = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getParaId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p.setParaId(rs.getInt("para_id"));
                p.setParaDescripcion(rs.getString("para_descripcion"));
                p.setParaUnidad(rs.getString("para_unidad"));
                p.setParaEstado(rs.getString("para_estado"));
                p.setUsuCrea(rs.getString("usu_crea"));
                p.setFechaCrea(rs.getTimestamp("fecha_crea"));
                p.setParaUsuAnula(rs.getString("para_usu_anu"));
                p.setParaFechaAnula(rs.getTimestamp("para_fecha_anu"));
                p.setParaFormula(rs.getString("para_formula"));
                p.setParaReferencia1(rs.getString("para_referencia1"));
                p.setParaReferencia2(rs.getString("para_referencia2"));
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

    public boolean eliminar(Parametro m) {
        String sql = "DELETE FROM parametros WHERE para_id = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, m.getParaId());
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
    public List<Parametro> getTodos(int optEstado) {
        List<Parametro> list = new ArrayList<>();
        String sql = "SELECT para_id, " +
                "para_descripcion, " +
                "para_unidad, " +
                "para_estado, " +
                "usu_crea, " +
                "fecha_crea, " +
                "para_usu_anu, " +
                "para_fecha_anu, " +
                "para_formula, " +
                "para_referencia1, " +
                "para_referencia2 " +
                "FROM parametros "
                + (optEstado == 1 ? " WHERE para_estado = 'Activo';" : "");
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Parametro p = new Parametro();
                p.setParaId(rs.getInt("para_id"));
                p.setParaDescripcion(rs.getString("para_descripcion"));
                p.setParaUnidad(rs.getString("para_unidad"));
                p.setParaEstado(rs.getString("para_estado"));
                p.setUsuCrea(rs.getString("usu_crea"));
                p.setFechaCrea(rs.getTimestamp("fecha_crea"));
                p.setParaUsuAnula(rs.getString("para_usu_anu"));
                p.setParaFechaAnula(rs.getTimestamp("para_fecha_anu"));
                p.setParaFormula(rs.getString("para_formula"));
                p.setParaReferencia1(rs.getString("para_referencia1"));
                p.setParaReferencia2(rs.getString("para_referencia2"));
                list.add(p);
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

    public boolean modificar(Parametro p) {
        String sql = "UPDATE parametros " +
                "SET " +
                "para_descripcion = ?, " +
                "para_unidad = ?, " +
                "para_estado = ?, " +
                "usu_crea = ?, " +
                "fecha_crea = ?, " +
                "para_usu_anu = ?, " +
                "para_fecha_anu = ?, " +
                "para_formula = ?, " +
                "para_referencia1 = ?, " +
                "para_referencia2 = ? " +
                "WHERE para_id = ? ";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getParaDescripcion());
            ps.setString(2, p.getParaUnidad());
            ps.setString(3, p.getParaEstado());
            ps.setString(4, p.getUsuCrea());
            ps.setTimestamp(5, p.getFechaCrea());
            ps.setString(6, p.getParaUsuAnula());
            ps.setTimestamp(7, p.getParaFechaAnula());
            ps.setString(8, p.getParaFormula());
            ps.setString(9, p.getParaReferencia1());
            ps.setString(10, p.getParaReferencia2());
            ps.setInt(11, p.getParaId());
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
