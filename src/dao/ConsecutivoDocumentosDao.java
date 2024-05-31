package dao;

import db.MySQLConnection;
import model.ConsecutivoDocumento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsecutivoDocumentosDao extends MySQLConnection {
    public boolean adicionar(ConsecutivoDocumento cd) {
        String sql = "INSERT INTO consecutivo_documentos " +
                "(td_sigla, " +
                "nit_empresa, " +
                "ultimo_creado) " +
                "VALUES " +
                "(?, " +                // 1 td_sigla
                "?, " +                 // 2 nit_empresa
                "?); ";                 // 3 ultimo_creado
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cd.getTdSigla());
            ps.setString(2, cd.getNitEmpresa());
            ps.setString(3, cd.getUltimoCreado());
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
    public boolean buscarId(ConsecutivoDocumento cd) {
        String sql = "SELECT td_sigla, " +
                "nit_empresa, " +
                "ultimo_creado " +
                "FROM labpets2.consecutivo_documentos " +
                "WHERE td_sigla = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cd.getTdSigla());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cd.setTdSigla(rs.getString("td_sigla"));
                cd.setNitEmpresa(rs.getString("nit_empresa"));
                cd.setUltimoCreado(rs.getString("ultimo_creado"));
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
    public boolean modificar(ConsecutivoDocumento cd) {
        String sql = "UPDATE consecutivo_documentos " +
                "SET " +
                "nit_empresa = ?, " +
                "ultimo_creado = ? " +
                "WHERE td_sigla = ?; ";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cd.getNitEmpresa());
            ps.setString(2, cd.getUltimoCreado());
            ps.setString(3, cd.getTdSigla());
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
