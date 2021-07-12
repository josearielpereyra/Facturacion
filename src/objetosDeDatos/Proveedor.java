package objetosDeDatos;

/**
 *
 * @author juanp
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Proveedor {

    String nombre;
    String apellidos;
    String direccion;
    String cedula;
    String rnc;

    public Proveedor( String nombre, String apellidos, String direccion, String cedula,  String rnc) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.cedula = cedula;
        this.rnc = rnc;
    }

    public Proveedor() {

    }

    public boolean guardar() {
        boolean seHaInsertado = false;
        try {
            Connection conexion = BaseDeDatos.conectar();
            String sql = "INSERT INTO tblproveedor(nombre,apellido,direccion,cedula,rnc) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement sentenciaDeInsercion = conexion.prepareStatement(sql);
            sentenciaDeInsercion.setString(1, nombre);
            sentenciaDeInsercion.setString(2, apellidos);
            sentenciaDeInsercion.setString(3, direccion);
            sentenciaDeInsercion.setString(4, cedula);
            sentenciaDeInsercion.setString(5, rnc);

            seHaInsertado = sentenciaDeInsercion.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar guardar los datos. Intente de nuevo");
            ex.printStackTrace();
        }

        return seHaInsertado;
    }


    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public String getapellidos() {
        return apellidos;
    }

    public void setapellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getdireccion() {
        return direccion;
    }

    public void setdireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getcedula() {
        return cedula;
    }

    public void setcedula(String telefono) {
        this.cedula = telefono;
    }

    public String rnc() {
        return rnc;
    }

    public void setrnc(String rfc) {
        this.rnc = rfc;
    }

}
