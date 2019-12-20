package objetosDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author josearielpereyra
 */
public class Cliente {
  String nombre;
  String apellido;
  String direccion;
  String telefono;
  String cedula;
  double limiteDeCredito;

  public Cliente(String nombre, String apellido, String direccion, String telefono, String cedula, double limiteDeCredito) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.direccion = direccion;
    this.telefono = telefono;
    this.cedula = cedula;
    this.limiteDeCredito = limiteDeCredito;
  }
  public Cliente(){
    
  }
  
  public boolean guardar() {
    boolean seHaInsertado = false;
    try {
      Connection conexion = BaseDeDatos.conectar();
      String sql = "INSERT INTO clientes VALUES (NULL, ?, ?, ?, ?, ?, ?)";
      PreparedStatement sentenciaDeInsercion = conexion.prepareStatement(sql);
      sentenciaDeInsercion.setString(1, nombre);
      sentenciaDeInsercion.setString(2, apellido);
      sentenciaDeInsercion.setString(3, direccion);
      sentenciaDeInsercion.setString(4, telefono);
      sentenciaDeInsercion.setString(5, cedula);
      sentenciaDeInsercion.setDouble(6, limiteDeCredito);
      
      seHaInsertado = sentenciaDeInsercion.executeUpdate() > 0;
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar guardar los datos. Intente de nuevo");
      ex.printStackTrace();
    }
    
    return seHaInsertado; 
  }

  public boolean modificar() {
    boolean seHaModificado = false;
    try {
      Connection conexion = BaseDeDatos.conectar();
      String sql = "UPDATE clientes SET nombre=?, apellido=?, direccion=?,"
        + "telefono=?, limite_de_credito=? "
        + "WHERE cedula=?";
      PreparedStatement sentenciaDeModificacion = conexion.prepareStatement(sql);
      sentenciaDeModificacion.setString(1, nombre);
      sentenciaDeModificacion.setString(2, apellido);
      sentenciaDeModificacion.setString(3, direccion);
      sentenciaDeModificacion.setString(4, telefono);
      sentenciaDeModificacion.setDouble(5, limiteDeCredito);
      sentenciaDeModificacion.setString(6, cedula);
      
      seHaModificado = sentenciaDeModificacion.executeUpdate() > 0;
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar modificar los datos. Intente de nuevo");
      ex.printStackTrace();
    }
    
    return seHaModificado; 
  }
  
}
