package objetosDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * @author josearielpereyra
 */
public class Producto {

    String descripcion;
    String codigo;
    double precio;
    double costo;
    double existencia;
    double impuesto;
    int idProducto;

    public Producto(String descripcion, String codigo, double precio, double costo, double existencia, double impuesto,int idProducto) {
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.precio = precio;
        this.costo = costo;
        this.existencia = existencia;
        this.impuesto = impuesto;
        this.idProducto=idProducto;
    }

    public Producto() {
        
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getExistencia() {
        return existencia;
    }

    public void setExistencia(double existencia) {
        this.existencia = existencia;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    public boolean guardar() {
    boolean seHaInsertado = false;
    try {
      Connection conexion = BaseDeDatos.conectar();
      String sql = "INSERT INTO tblproducto (codigo, descripcion, costo, precio, existencia, impuesto) "
              + "VALUES(?, ?, ?, ?, ?, ?)";
     PreparedStatement sentenciaDeInsercion = conexion.prepareStatement(sql);
      sentenciaDeInsercion.setString(1, this.codigo);
      sentenciaDeInsercion.setString(2, this.descripcion);
      sentenciaDeInsercion.setDouble(3, this.costo);
      sentenciaDeInsercion.setDouble(4, this.precio);
      sentenciaDeInsercion.setDouble(5, this.existencia);
      sentenciaDeInsercion.setDouble(6, this.impuesto);
      
      seHaInsertado = sentenciaDeInsercion.executeUpdate() > 0;
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar guardar los datos. Intente de nuevo "+ex.getMessage());
    
    }
    
    return seHaInsertado; 
       
  }
}
