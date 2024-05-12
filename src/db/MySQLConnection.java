package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private Connection connection;
    private String serverDb = "localhost";
    private String portDb = "3306";
    private String dataBaseDb = "labpets2";
    private String userDb = "root";
    private String passDb = "";
    private String urlDb = "jdbc:mysql://" + serverDb + ":" + portDb + '/' + dataBaseDb;


    public Connection conectar() {
        try {
            this.connection = DriverManager.getConnection(urlDb, userDb, passDb);
            System.out.println("[ INFO ] Conexión DB realizada");
        } catch (SQLException e) {
            System.out.println("[ ERROR ] Error al realizar la conexión DB");
            System.out.println("Mensaje: " + e.getMessage());
            e.printStackTrace();
        }
        return this.connection;
    }

    public boolean desconectar() {
        try {
            this.connection.close();
            System.out.println("[ INFO ] Cierre conexión DB realizada");
        } catch (SQLException e) {
            System.out.println("[ ERROR ] Proeblas al realizar el cierre de la conexión");
            System.out.println("Mensaje: " + e.getMessage());
            e.printStackTrace();
        }
        return true;
    }
}
