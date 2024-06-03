package dao;

import db.MySQLConnection;
import model.Examen;
import model.ExamenParametro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamenParametroDao extends MySQLConnection {
    public List<ExamenParametro> getTodos(Examen ex) {
        List<ExamenParametro> list = new ArrayList<>();
        String sql = "SELECT ep_id, " +
                "    ep_exa_id, " +
                "    ep_para_id, " +
                "    ep_consecutivo " +
                "FROM examen_parametros " +
                "WHERE ep_exa_id = ?";
        Connection conn = this.conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,ex.getExaId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ExamenParametro ep = new ExamenParametro();
                ep.setEpId(rs.getInt("ep_id"));
                ep.setEpExaId(rs.getInt("ep_exa_id"));
                ep.setEpParaId(rs.getInt("ep_para_id"));
                ep.setEpConsecutivo(rs.getInt("ep_consecutivo"));
                list.add(ep);
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
}
