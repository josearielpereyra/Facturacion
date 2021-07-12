
package objetosDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.Date;

/**
 *
 * @author Wilmer Morillo
 */
public class EntradaSalida {
    int tipo ;
    double cantidad;
    String concepto;
    int idproducto;

    public EntradaSalida(int tipo, double cantidad, String concepto, int idproducto) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.concepto = concepto;
        this.idproducto = idproducto;
    }
    
    public boolean guardar() {
    boolean seHaInsertado = false;
    try {
      Connection conexion = BaseDeDatos.conectar();
      String sql = "INSERT INTO tblregularizarinventario(idproducto, concepto, tipo,cantidad,fecha) "
              + "VALUES(?, ?, ?, ?,?)";
     PreparedStatement sentenciaDeInsercion = conexion.prepareStatement(sql);
      sentenciaDeInsercion.setInt(1,this.idproducto);
      sentenciaDeInsercion.setString(2, this.concepto);
      sentenciaDeInsercion.setInt(3, this.tipo);
      sentenciaDeInsercion.setDouble(4, this.cantidad);
      sentenciaDeInsercion.setDate(5,  new java.sql.Date(new Date().getTime()));
      
      seHaInsertado = sentenciaDeInsercion.executeUpdate() > 0;
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar guardar los datos. Intente de nuevo "+ex.getMessage());
    
    }
    
    return seHaInsertado; 
       
  }
    
    
}
