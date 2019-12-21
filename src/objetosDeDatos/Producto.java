package objetosDeDatos;

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
    
    

}
