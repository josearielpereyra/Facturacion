package facturacion;

/**
 * @author josearielpereyra
 */
import objetosDeDatos.Factura;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;

public class Facturacion implements Printable, ActionListener {

    int[] saltosDePagina;
    String[] lineasDeTexto;
    Factura factura;

    public Facturacion(Factura factura) {
        this.factura = factura;
    }

    private void inicializarLineasAImprimir() {
        if (lineasDeTexto == null) {
            lineasDeTexto = factura.obtenerFacturaParaPapelTamanioCarta().split("\n");
        }
    }

    @Override
    public int print(Graphics g, PageFormat pf, int indiceDePagina)
            throws PrinterException {

        Font fuente = new Font("Monaco", Font.PLAIN, 14);
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

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        g2d.setFont(fuente);

        int y = 0;
        int inicio = (indiceDePagina == 0) ? 0 : saltosDePagina[indiceDePagina - 1];
        int fin = (indiceDePagina == saltosDePagina.length)
                ? lineasDeTexto.length : saltosDePagina[indiceDePagina];
        for (int linea = inicio; linea < fin; linea++) {
            y += alturaDeLinea;
            if ((linea >= inicio && linea <= inicio + 2)) {
                fuente = new Font(fuente.getName(), Font.BOLD, 14);
            } else {
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
                ex.printStackTrace();
            }
        }
    }

    public void imprimir() {
        PrinterJob trabajoDeImpresion = PrinterJob.getPrinterJob();
        trabajoDeImpresion.setPrintable(this);
        boolean seDebeImprimir = trabajoDeImpresion.printDialog();
        if (seDebeImprimir) {
            try {
                trabajoDeImpresion.print();
            } catch (PrinterException ex) {
                /* El trabajo de impresion no se completó exitosamente */
                System.out.println("No se pudo imprimir");
                ex.printStackTrace();
            }
        }
    }

}
