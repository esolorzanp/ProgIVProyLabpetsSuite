package dao;

import db.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MasterDao extends MySQLConnection {
    public int autoIncrement(String tipoCod) {
        String sql = "SELECT ultimo_creado " +
                "FROM consecutivo_documentos " +
                "WHERE td_sigla = ?";
        int consec = -1;
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tipoCod);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                consec = rs.getInt("ultimo_creado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return consec;
    }
}
