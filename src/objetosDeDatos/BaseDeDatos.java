package objetosDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author josearielpereyra
 */
public class BaseDeDatos {

    private static String host = "localhost";
    private static String bd = "dbfacturas";
    private static String usuario = "root";
    private static String password = "1286";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + host + ":3308/" + bd, usuario, password);
    }

}
