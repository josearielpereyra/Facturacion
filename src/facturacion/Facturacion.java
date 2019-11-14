package facturacion;
/**
 * @author josearielpereyra
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.print.*;
import java.util.*;

public class Facturacion implements Printable, ActionListener {

  int[] saltosDePagina;  // array of page break line positions.

  /* Synthesise some sample lines of text */
  String[] lineasDeTexto;

  private void inicializarLineasAImprimir() {
    if (lineasDeTexto == null) {
      Calendar fechaActual = Calendar.getInstance();
      Cliente clienteActual = new Cliente();

      
      new Producto("Queso Danes", "QD01", 360.00, 300.00, 200.00, 18.00);

      ArrayList<LineaDeFactura> lineas = new ArrayList<>();
      
      Random numerosAleatorios = new Random();
      String[] productos = {"Jabon Lavador", "Queso Danes", "Berenjena", "Arroz Pimco"};
      for (int i = 0; i < 100; i++) {
        int indiceProducto = numerosAleatorios.nextInt(productos.length);
        double costo = numerosAleatorios.nextDouble() * 1000 + 1;
        double precio = costo * 1.4;
        int existencia = numerosAleatorios.nextInt(100) + 1;
        int cantidad = numerosAleatorios.nextInt(100) + 1;//TODO validar que no se pueda vender mas que la existencia 
        Producto producto = new Producto(productos[indiceProducto], "JLV01", precio, costo, existencia, 18.00);
        lineas.add(new LineaDeFactura(producto, cantidad, cantidad * precio * 0.10));
      }
      
      Factura factura = new Factura(fechaActual, clienteActual, "contado", "", 1000.00, lineas);  
      lineasDeTexto = factura.obtenerFacturaParaPapelTamanioCarta().split("\n");
      /*
      Scanner entrada = new Scanner( factura.obtenerFacturaParaPapelTamanioCarta() );
      
      lineasDeTexto = new ArrayList<>();
      while(entrada.hasNext()) {
        lineasDeTexto.add(  entrada.nextLine() );
      }
      */
    }
  }

  @Override
  public int print(Graphics g, PageFormat pf, int indiceDePagina)
    throws PrinterException {

    Font fuente = new Font("Monospaced", Font.PLAIN, 12);
    FontMetrics metricasDeFuente = g.getFontMetrics(fuente);
    int alturaDeLinea = metricasDeFuente.getHeight();
    
    int lineasPorPagina = 0;

    if (saltosDePagina == null) {
      inicializarLineasAImprimir();
      lineasPorPagina = (int) (pf.getImageableHeight() / alturaDeLinea);
      System.out.println(lineasPorPagina);
      int numBreaks = (lineasDeTexto.length - 1) / lineasPorPagina;
      saltosDePagina = new int[numBreaks];
      for (int b = 0; b < numBreaks; b++) {
        saltosDePagina[b] = (b + 1) * lineasPorPagina;
      }
    }

    if (indiceDePagina > saltosDePagina.length) {
      return NO_SUCH_PAGE;
    }

    /* 
      User (0,0) is typically outside the imageable area, so we must
      translate by the X and Y values in the PageFormat to avoid clipping
    */
    Graphics2D g2d = (Graphics2D) g;
    g2d.translate(pf.getImageableX(), pf.getImageableY());
    g2d.setFont(fuente);

    /* 
      Since we are drawing text we 
      Draw each line that is on this page.
      Increment 'y' position by lineHeight for each line.
     */
    int y = 0;
    int inicio = (indiceDePagina == 0) ? 0 : saltosDePagina[indiceDePagina - 1];
    int fin = (indiceDePagina == saltosDePagina.length)
      ? lineasDeTexto.length : saltosDePagina[indiceDePagina];
    for (int linea = inicio; linea < fin; linea++) {
      y += alturaDeLinea;
      if((linea >= inicio && linea <= inicio + 2)) {
        fuente = new Font(fuente.getName(), Font.BOLD, 14);
      }
      else {
        fuente = new Font(fuente.getName(), Font.PLAIN, 12);
      }
      g.setFont(fuente);
      g.drawString("    " + lineasDeTexto[linea], 0, y);
    }

    /* tell the caller that this page is part of the printed document */
    return PAGE_EXISTS;
  }

  public void actionPerformed(ActionEvent e) {
    PrinterJob trabajoDeImpresion = PrinterJob.getPrinterJob();
    trabajoDeImpresion.setPrintable(this);
    boolean seDebeImprimir = trabajoDeImpresion.printDialog();
    if (seDebeImprimir) {
      try {
        trabajoDeImpresion.print();
      } catch (PrinterException ex) {
        /* El trabajo de impresion no se completó exitosamente */
        System.out.println("No se pudo imprimir");
      }
    }
  }

  public static void main(String args[]) {

    try {
      String cn = UIManager.getSystemLookAndFeelClassName();
      UIManager.setLookAndFeel(cn); // Usar el Look and Feel Nativo
    } catch (Exception ex) {
      System.out.println("No se pudo aplicar la apariencia.");
    }
    JFrame ventana = new JFrame("Ejemplo de impresión con paginacion.");
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JButton printButton = new JButton("Imprimir Paginas");
    printButton.addActionListener(new Facturacion());
    ventana.add(printButton, BorderLayout.CENTER);
    ventana.pack();
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);
  }
}