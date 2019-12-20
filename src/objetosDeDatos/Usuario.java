
package objetosDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author codefutura
 */
public class Usuario {
    String nombre;
    String clave;
    String tipo;

    public Usuario(String nombre, String clave, String tipo) {
        this.nombre = nombre;
        this.clave = clave;
        this.tipo = tipo;
    }
    
 
    public static int verificarAcceso(String nombre, String clave){
        try {   
            Connection conexion = BaseDeDatos.conectar();
            String sql = "SELECT login(?,?) AS valido";
            PreparedStatement sentenciaDeBusqueda = conexion.prepareStatement(sql);
            sentenciaDeBusqueda.setString(1, nombre);
            sentenciaDeBusqueda.setString(2, clave);
            ResultSet resultado = sentenciaDeBusqueda.executeQuery();           
            if( resultado.next() ) {
              return resultado.getInt("valido");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     return 0;
    }

}
