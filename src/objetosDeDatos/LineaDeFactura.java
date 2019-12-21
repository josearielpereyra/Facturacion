package objetosDeDatos;

/**
 * @author josearielpereyra
 */
public class LineaDeFactura {

    Producto producto;
    double cantidad;
    double descuento;
    double importe;

    public LineaDeFactura(Producto producto, double cantidad, double descuento) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.importe = producto.precio * cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getImporte() {
        return importe;
    }

}
