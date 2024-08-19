package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose Torres
 */
public class ConexionConnection {

    private String usuario = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost:3306/prueba_jdbc";
    private Connection conexion;

    public ConexionConnection() {
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException ex) {

            System.out.println("ERROR al acceder a la base de datos");
            Logger.getLogger(ConexionConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexion cerrada");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
