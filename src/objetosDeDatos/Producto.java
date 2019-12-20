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

  public Producto(String descripcion, String codigo, double precio, double costo, double existencia, double impuesto) {
    this.descripcion = descripcion;
    this.codigo = codigo;
    this.precio = precio;
    this.costo = costo;
    this.existencia = existencia;
    this.impuesto = impuesto;
  }
}
