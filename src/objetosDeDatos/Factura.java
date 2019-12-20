package objetosDeDatos;

import objetosDeDatos.LineaDeFactura;
import objetosDeDatos.Cliente;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author josearielpereyra
 */
public class Factura {

  Calendar fecha;
  Cliente cliente;
  String tipo;
  String comentario;
  double totalPagado;
  
  ArrayList<LineaDeFactura> lineas;
  private final int dia;
  private final int mes;
  private final int anio;
  private String[] meses = {"enero", "febrero", "marzo","abril", "mayo",
      "junio","julio","agosto", "septiembre", "octubre", "noviembre", "diciembre"};

  public Factura(
    Calendar fecha,
    Cliente cliente,
    String tipo,
    String comentario,
    double totalPagado,
    ArrayList<LineaDeFactura> lineas) {
    this.fecha = fecha;
    this.cliente = cliente;
    this.tipo = tipo;
    this.comentario = comentario;
    this.totalPagado = totalPagado;
    this.lineas = lineas;
    
    dia = fecha.get(Calendar.DATE);
    mes = fecha.get(Calendar.MONTH);
    anio = fecha.get(Calendar.YEAR);
  }
  
  double obtenerSubtotal(){
    double subTotal = 0.0;
    for (LineaDeFactura linea : lineas) {
      subTotal += linea.importe;
    }
    return subTotal;
  }
  
  double obtenerDescuentos() {
    double descuento = 0.0;
    for (LineaDeFactura linea : lineas) {
      descuento += linea.descuento;
    }
    return descuento;
  }
  
  double obtenerTotalDeImpuestos(){
    double totalDeImpuestos = 0.0;
    for (LineaDeFactura linea : lineas) {
      totalDeImpuestos += (linea.importe - linea.descuento) * ( linea.producto.impuesto/100 );
    }
    return totalDeImpuestos;
  }
  
  double obtenerTotal() {
    return obtenerSubtotal() - obtenerDescuentos() + obtenerTotalDeImpuestos();
  }
  
  String obtenerSeparador(int longitud)  {
    String separador = "";
    for (int i = 0; i < longitud + 7; i++) {
      if(i == 0 || i == 11 || i == 22 || i == 43 || i == 54 || i == 65 || i == 76) {
        separador += "+";
      }
      else {
        separador += "-";
      }
    }
    separador += "\n";
    
    return separador;
  }
  
  public String obtenerFacturaParaPapelTamanioCarta() {
    double lineasPorPagina = 31;
    int totalDePaginas = (int)Math.ceil(lineas.size() / lineasPorPagina );
    int paginaActual = 1;
    String factura = "";
    
    factura += obtenerEncabezado();
    for (int i = 0; i < lineas.size(); i++) {
      if(i > 0 && i%lineasPorPagina == 0){
        factura += obtenerSeparador(70);
        factura += String.format("Pagina %d de %d\n\n\n\n\n\n\n\n\n\n", paginaActual++, totalDePaginas);
        factura += obtenerEncabezado();
      }
      LineaDeFactura linea = lineas.get(i);
      
      String codigo = linea.producto.codigo;
      double cantidad = linea.cantidad;
      String descripcion = linea.producto.descripcion;
      double precio = linea.producto.precio;
      double importe = linea.importe;
      double descuento = linea.descuento;
      factura += String.format("|%-10s|%,10.2f|%-20s|%,10.2f|%,10.2f|%,10.2f|\n", 
        codigo, cantidad, descripcion, precio, importe, descuento);
    } 
    factura += obtenerSeparador(70);
    factura += String.format("Pagina %3d de %3d", totalDePaginas, totalDePaginas);
    factura += String.format("%49s%,10.2f\n", "Totales:", obtenerSubtotal());
    factura += String.format("%66s%,10.2f\n", "Descuento:", obtenerDescuentos());
    factura += String.format("%66s%,10.2f\n", "ITBIS:", obtenerTotalDeImpuestos());
    factura += String.format("%66s%,10.2f\n", "Total:", obtenerTotal());
    
    factura += "\n\n    ---------------------------        -----------------------------\n";
    factura += "            Vendido Por                      Recibido Por\n";
    
    factura += "\n               Gracias por utilizar nuestros servicios.\n";
    return factura;
  }

  private String obtenerEncabezado() {
    String encabezado = "";
    encabezado += String.format("%-50s%02d de %s de %04d\n", "TIENDAS GARCÍA", dia, meses[mes].substring(0, 3), anio);
    encabezado += "Av. Principal #43, Nagua\n";
    encabezado += "809-333-2727\n\n";
    
    encabezado += String.format("Cliente: %s %s\n", cliente.getNombre(), cliente.getApellido());
    encabezado += String.format("Direccion: %s\nTelefono: %s Cedula: %s\n", 
        cliente.getDireccion(), cliente.getTelefono(), cliente.getCedula());
    
    encabezado += obtenerSeparador(70);
    encabezado += String.format("|%-10s|%-10s|%-20s|%-10s|%-10s|%-10s|\n",
      "Codigo", "Cant.", "Descripcion", "Precio", "Importe", "Descuento");
    encabezado += obtenerSeparador(70);
    
    return encabezado;
  }
  
  String obtenerFacturaParaPOS() {
    String factura = "";
    factura += String.format("%-40s\n%02d de %s de %04d\n", "TIENDAS GARCÍA", dia, meses[mes].substring(0, 3), anio);
    factura += String.format("Av. Principal #43, Pimentel\n");
    factura += String.format("809-333-2727\n");
    factura += String.format("----------------------------------------\n");
    factura += String.format("%-20s%10s%10s\n", "DESCRIPCION", "ITBIS", "VALOR");
    factura += String.format("----------------------------------------\n");
    
    for (LineaDeFactura linea : lineas) {
      String codigo = linea.producto.codigo;
      double cantidad = linea.cantidad;
      String descripcion = linea.producto.descripcion;
      double precio = linea.producto.precio;
      double importe = linea.importe;
      double descuento = linea.descuento;
      double itbis = linea.importe * (linea.producto.impuesto/100);
      factura += String.format("%-40s\n%-12s%,8.2f%,10.2f%,10.2f\n", 
        descripcion, codigo, cantidad, itbis, importe);
    } 
    factura += String.format("\n%30s%,10.2f\n", "Totales:", obtenerSubtotal());
    factura += String.format("%30s%,10.2f\n", "Descuento:", obtenerDescuentos());
    factura += String.format("%30s%,10.2f\n", "ITBIS:", obtenerTotalDeImpuestos());
    factura += String.format("%30s%,10.2f\n", "Total:", obtenerTotal());
    
    factura += String.format("\n\n     ------------------------------\n");
    factura += String.format("                Vendido Por          \n");
    
    factura += String.format("\nGracias por utilizar nuestros servicios!\n");
    return factura;
  }
  
}
