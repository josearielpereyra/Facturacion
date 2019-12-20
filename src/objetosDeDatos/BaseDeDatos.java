/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author josearielpereyra
 */
public class BaseDeDatos {
  private static String host = "192.168.64.2";
  private static String bd = "facturacion";
  private static String usuario = "ariel";
  private static String password = "";
  
  public static Connection conectar () throws SQLException {
    return DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + bd, usuario, password);
  }
  
}
