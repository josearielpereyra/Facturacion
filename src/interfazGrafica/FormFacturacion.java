package interfazGrafica;

import facturacion.Facturacion;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import objetosDeDatos.BaseDeDatos;
import objetosDeDatos.Cliente;
import objetosDeDatos.Factura;
import objetosDeDatos.LineaDeFactura;
import objetosDeDatos.Producto;

/**
 *
 * @author codefutura
 */
public class FormFacturacion extends javax.swing.JDialog {

    Factura factura;
    Cliente clienteActual;
    Calendar fechaActual = Calendar.getInstance();
    ArrayList<LineaDeFactura> lineas;
    Producto producto;
    DefaultTableModel modelo;

    /**
     * Creates new form FormFacturacions
     *
     * @param parent
     * @param modal
     */
    public FormFacturacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        modelo = (DefaultTableModel) jTable1.getModel();
        formatoTablaDetalle();
        txtFecha.setText(String.valueOf(fechaActual.get(Calendar.DATE))
                + "/" + String.valueOf(fechaActual.get(Calendar.MONTH)) + "/" + String.valueOf(fechaActual.get(Calendar.YEAR)));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        txtImporteLinea = new javax.swing.JFormattedTextField();
        btnAgregaLinea = new javax.swing.JButton();
        btnSeleccionarProducto = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtComentario = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txtImpuesto = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        txtNumero = new javax.swing.JTextField();
        btnBuscarFactura = new javax.swing.JButton();
        txtNombreCliente = new javax.swing.JTextField();
        tFormaDePago = new javax.swing.JComboBox<>();
        btnBuscarCliente = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        txtFecha = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtMontoPagado = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("F A C T U R A C I O N");

        jTable1.setForeground(new java.awt.Color(0, 51, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descripción", "Cantidad", "Precio", "Importe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(22);
        jScrollPane1.setViewportView(jTable1);

        jLabel9.setText("Código");

        txtCodigo.setBackground(new java.awt.Color(255, 255, 204));
        txtCodigo.setForeground(new java.awt.Color(0, 51, 255));
        txtCodigo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        txtDescripcion.setEditable(false);
        txtDescripcion.setForeground(new java.awt.Color(0, 51, 255));
        txtDescripcion.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel10.setText("Descripción");

        txtCantidad.setBackground(new java.awt.Color(204, 255, 204));
        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
        });

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Cantidad");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Precio");

        txtPrecio.setEditable(false);
        txtPrecio.setForeground(new java.awt.Color(0, 51, 255));
        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Importe");

        txtImporteLinea.setEditable(false);
        txtImporteLinea.setBackground(new java.awt.Color(255, 255, 255));
        txtImporteLinea.setForeground(new java.awt.Color(0, 51, 204));
        txtImporteLinea.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtImporteLinea.setOpaque(false);

        btnAgregaLinea.setText("+");
        btnAgregaLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregaLineaActionPerformed(evt);
            }
        });

        btnSeleccionarProducto.setText("...");
        btnSeleccionarProducto.setToolTipText("");
        btnSeleccionarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSeleccionarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescripcion)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(txtCantidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtImporteLinea)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addComponent(btnAgregaLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregaLinea)
                        .addComponent(txtImporteLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Detalle", jPanel1);

        txtComentario.setColumns(20);
        txtComentario.setRows(5);
        jScrollPane2.setViewportView(txtComentario);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Comentario", jPanel2);

        jLabel1.setText("Número");

        jLabel2.setText("Cliente");

        jLabel3.setText("Forma de pago");
        jLabel3.setToolTipText("");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Fecha");

        txtImporte.setEditable(false);
        txtImporte.setForeground(new java.awt.Color(0, 51, 255));
        txtImporte.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        jLabel5.setText("SubTotal");

        txtDescuento.setEditable(false);
        txtDescuento.setForeground(new java.awt.Color(0, 51, 255));
        txtDescuento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        jLabel6.setText("Descuento");

        txtImpuesto.setEditable(false);
        txtImpuesto.setForeground(new java.awt.Color(0, 0, 255));
        txtImpuesto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        jLabel7.setText("Impuesto");

        txtTotal.setEditable(false);
        txtTotal.setForeground(new java.awt.Color(0, 51, 255));
        txtTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("T o t a l");

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/housekeeping.png"))); // NOI18N
        btnCancelar.setText("Limpiar / habilitar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });

        btnBuscarFactura.setText("Buscar");
        btnBuscarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarFacturaActionPerformed(evt);
            }
        });

        txtNombreCliente.setEditable(false);
        txtNombreCliente.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtNombreCliente.setForeground(new java.awt.Color(0, 51, 255));

        tFormaDePago.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        tFormaDePago.setForeground(new java.awt.Color(0, 51, 204));
        tFormaDePago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Crédito", "Tarjeta de crédito", "Cheque" }));

        btnBuscarCliente.setText("Localizar");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ledger.png"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        txtFecha.setEditable(false);
        txtFecha.setBackground(new java.awt.Color(255, 255, 255));
        txtFecha.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(0, 153, 51));
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFecha.setOpaque(false);

        jLabel14.setText("Monto pagado");

        txtMontoPagado.setForeground(new java.awt.Color(0, 51, 204));
        txtMontoPagado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtMontoPagado.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarFactura)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jTabbedPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombreCliente)
                                    .addComponent(tFormaDePago, 0, 236, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnBuscarCliente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel14)))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                    .addComponent(txtMontoPagado)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(txtImporte))
                                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarFactura)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscarCliente)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tFormaDePago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(txtMontoPagado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtImpuesto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtImporte, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtDescuento, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnImprimir)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (clienteActual == null) {
            JOptionPane.showMessageDialog(this, "Favor seleccionar un cliente");
            return;
        }

        if (tFormaDePago.getSelectedIndex() == 1) {
            if (validarDouble(txtTotal) > clienteActual.getLimiteDeCredito()) {
                JOptionPane.showMessageDialog(this, "Esta factura excede el límite de crédito para este cliente !");
                return;
            }
        }

        if (tFormaDePago.getSelectedIndex() == 0) {
            if (validarDouble(txtMontoPagado) == 0) {
                JOptionPane.showMessageDialog(this, "Favor indicar el pago");
                txtMontoPagado.requestFocus();
                return;
            }

            if ((validarDouble(txtMontoPagado) < validarDouble(txtTotal)) | (validarDouble(txtMontoPagado) > validarDouble(txtTotal))) {
                JOptionPane.showMessageDialog(this, "El monto pagado debe ser igual al total facturado");
                txtMontoPagado.requestFocus();
                return;
            }

        }

        factura = null;
        factura = new Factura(fechaActual, clienteActual, tFormaDePago.getSelectedItem().toString(), txtComentario.getText(), validarDouble(txtMontoPagado), lineas, tFormaDePago.getSelectedIndex());

        if (factura.guardarFactura() > 0) {
            JOptionPane.showMessageDialog(this, "Factura registrada exitosamente");
        }

        Facturacion fac = new Facturacion(factura);
        fac.imprimir();

        limpiarFormulario();

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAgregaLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregaLineaActionPerformed
        if (producto == null) {
            return;
        }

        if (producto.getExistencia() < validarDoubleTexto(txtCantidad)) {
            JOptionPane.showMessageDialog(this, "No hay existencia disponible para la cantidad");
            //considerando que es necesario recorrer toda la lista para ver cuanta unidades estan servidas de este producto.
            return;
        }

        if (lineas == null) {
            lineas = new ArrayList<>();
        }
        double cant = validarDouble(txtCantidad);
        double precio = producto.getPrecio();

        lineas.add(new LineaDeFactura(new Producto(producto.getDescripcion(), producto.getCodigo(),
                producto.getPrecio(), producto.getCosto(), producto.getExistencia(), producto.getImpuesto(), producto.getIdProducto()),
                cant, cant * precio * 0.10));

        agregarListaDetalle();

        txtImporte.setValue(obtenerSubtotal());
        txtImpuesto.setValue(obtenerTotalDeImpuestos());
        txtTotal.setValue(obtenerTotal());
        txtDescuento.setValue(obtenerDescuentos());

        limpiarCamposDetalle();
        producto = null;


    }//GEN-LAST:event_btnAgregaLineaActionPerformed

    private void btnSeleccionarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarProductoActionPerformed
        localizarProducto();
    }//GEN-LAST:event_btnSeleccionarProductoActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        if (clienteActual == null) {
            JOptionPane.showMessageDialog(this, "Favor seleccionar un cliente");
            return;
        }

        factura = null;
        factura = new Factura(fechaActual, clienteActual, tFormaDePago.getSelectedItem().toString(), txtComentario.getText(), validarDouble(txtMontoPagado), lineas, tFormaDePago.getSelectedIndex());

        if (!txtNumero.getText().trim().equals("")) {
            factura.setIdFactura(Long.parseLong(String.valueOf(txtNumero.getText().trim())));
        }

        Facturacion fac = new Facturacion(factura);
        fac.imprimir();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        buscarCliente();
        txtNombreCliente.setText(clienteActual.getNombre() + " " + clienteActual.getApellido());


    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed


    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        double cant = validarDoubleTexto(txtCantidad);
        txtImporteLinea.setValue(cant * producto.getPrecio());

    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        if (!txtCodigo.getText().trim().equals("")) {
            localizarProducto();

        }
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarFormulario();
        activarControles();
        txtNumero.setText("");

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFacturaActionPerformed
        limpiarFormulario();
        localizarFactura();
        desactivarControlesEnBusqueda();
    }//GEN-LAST:event_btnBuscarFacturaActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        limpiarFormulario();
        localizarFactura();

        desactivarControlesEnBusqueda();
    }//GEN-LAST:event_txtNumeroActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormFacturacion dialog = new FormFacturacion(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregaLinea;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarFactura;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnSeleccionarProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> tFormaDePago;
    private javax.swing.JFormattedTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextArea txtComentario;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JFormattedTextField txtDescuento;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JFormattedTextField txtImporte;
    private javax.swing.JFormattedTextField txtImporteLinea;
    private javax.swing.JFormattedTextField txtImpuesto;
    private javax.swing.JFormattedTextField txtMontoPagado;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JFormattedTextField txtPrecio;
    private javax.swing.JFormattedTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    private double validarDouble(JFormattedTextField f) {
        double valor = 0;
        try {
            valor = Double.parseDouble(String.valueOf(f.getValue()));
        } catch (NumberFormatException e) {
        }

        return valor;
    }

    private double validarDoubleTexto(JFormattedTextField f) {
        double valor = 0;
        try {
            valor = Double.parseDouble(String.valueOf(f.getText()));
        } catch (NumberFormatException e) {
        }

        return valor;
    }

    double obtenerSubtotal() {
        double subTotal = 0.0;
        for (LineaDeFactura linea : lineas) {
            subTotal += linea.getImporte();
        }
        return subTotal;
    }

    double obtenerDescuentos() {
        double descuento = 0.0;
        for (LineaDeFactura linea : lineas) {
            descuento += linea.getDescuento();
        }
        return descuento;
    }

    double obtenerTotalDeImpuestos() {
        double totalDeImpuestos = 0.0;
        for (LineaDeFactura linea : lineas) {
            totalDeImpuestos += (linea.getImporte() - linea.getDescuento()) * (linea.getProducto().getImpuesto() / 100);
        }
        return totalDeImpuestos;
    }

    double obtenerTotal() {
        return obtenerSubtotal() - obtenerDescuentos() + obtenerTotalDeImpuestos();
    }

    private void limpiarCamposDetalle() {
        txtDescripcion.setText("");
        txtCantidad.setValue(1);
        txtPrecio.setValue(0.00);
        txtImporteLinea.setValue(0.00);
        txtCodigo.setText("");
        txtCodigo.requestFocus();
    }

    private void borrarJTabla(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        int fichas = tabla.getRowCount();
        try {
            for (int i = 0; fichas > i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR al borrar tabla detalle");
        }
    }

    private void agregarListaDetalle() {
        borrarJTabla(jTable1);
        for (int i = 0; i < lineas.size(); i++) {
            LineaDeFactura linea = lineas.get(i);
            modelo.addRow(
                    new Object[]{
                        linea.getProducto().getCodigo(),
                        linea.getProducto().getDescripcion(),
                        linea.getCantidad(),
                        String.format("%,.2f", linea.getProducto().getPrecio()),
                        String.format("%,.2f", linea.getImporte())
                    });
        }
    }

    private void formatoTablaDetalle() {
        TableColumnModel columnModel = jTable1.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(8);
        columnModel.getColumn(1).setPreferredWidth(210);
        columnModel.getColumn(2).setPreferredWidth(12);
        columnModel.getColumn(3).setPreferredWidth(12);
        columnModel.getColumn(4).setPreferredWidth(12);
        jTable1.setColumnModel(columnModel);
        modelo = (DefaultTableModel) jTable1.getModel();
        //evita que muevan las columnas
        jTable1.getTableHeader().setReorderingAllowed(false);
    }

    private void buscarCliente() {
        try {
            JTable tablaClientes = new JTable();

            Connection conexion = BaseDeDatos.conectar();
            String sql = "SELECT cedula, nombre, apellido, direccion, telefono, limiteCredito,idcliente "
                    + "FROM tblcliente";

            Statement sentenciaDeConsulta = conexion.createStatement();
            ResultSet conjuntoDeResultados = sentenciaDeConsulta.executeQuery(sql);

            Vector<String> encabezados = new Vector<>();
            Vector< Vector<String>> datos = new Vector<>();

            ResultSetMetaData metaDatos = conjuntoDeResultados.getMetaData();
            int totalDeColumnas = metaDatos.getColumnCount();
            for (int i = 1; i <= totalDeColumnas; i++) {
                encabezados.add(metaDatos.getColumnName(i).toUpperCase().replace("_", " "));
            }

            while (conjuntoDeResultados.next()) {
                Vector<String> registroActual = new Vector<>();
                for (int i = 1; i <= totalDeColumnas; i++) {
                    registroActual.add(conjuntoDeResultados.getObject(i).toString());
                }
                datos.add(registroActual);
            }

            DefaultTableModel modeloTabla = new DefaultTableModel(datos, encabezados) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tablaClientes.setModel(modeloTabla);

            tablaClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    clienteActual = null;
                    clienteActual = new Cliente();
                    clienteActual.setCedula(tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 0).toString());
                    clienteActual.setNombre(tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 1).toString());
                    clienteActual.setApellido(tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 2).toString());
                    clienteActual.setDireccion(tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 3).toString());
                    clienteActual.setTelefono(tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 4).toString());
                    clienteActual.setLimiteDeCredito(Double.parseDouble(String.valueOf(tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 5))));
                    clienteActual.setIdCliente(Integer.parseInt(String.valueOf(tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 6))));
                }
            });

            JDialog ventanaDeBusqueda = new JDialog(this);
            ventanaDeBusqueda.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            ventanaDeBusqueda.add(new JScrollPane(tablaClientes));
            JButton botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ventanaDeBusqueda.dispose();
                }
            });
            ventanaDeBusqueda.add(botonAceptar, BorderLayout.SOUTH);

            ventanaDeBusqueda.pack();
            ventanaDeBusqueda.setLocationRelativeTo(this);
            ventanaDeBusqueda.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private void localizarProducto() {
        if (clienteActual == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente para continuar");
            return;
        }

        if (txtCodigo.getText().trim().length() > 0) {
            try {
                Connection conexion = BaseDeDatos.conectar();
                String sql = "SELECT codigo, descripcion, costo, existencia, precio, impuesto,idproducto "
                        + "FROM tblproducto "
                        + " WHERE codigo = ?";
                PreparedStatement sentenciaDeBusqueda = conexion.prepareStatement(sql);
                sentenciaDeBusqueda.setString(1, txtCodigo.getText().trim());
                ResultSet conjuntoDeResultados = sentenciaDeBusqueda.executeQuery();
                if (conjuntoDeResultados.next()) {
                    producto = new Producto(conjuntoDeResultados.getString("descripcion"), conjuntoDeResultados.getString("codigo"),
                            conjuntoDeResultados.getDouble("precio"), conjuntoDeResultados.getDouble("costo"),
                            conjuntoDeResultados.getDouble("existencia"), conjuntoDeResultados.getDouble("impuesto"), conjuntoDeResultados.getInt("idproducto"));

                    txtDescripcion.setText(producto.getDescripcion());
                    txtCantidad.setValue(1);
                    txtPrecio.setValue(producto.getPrecio());
                    txtImporteLinea.setValue(producto.getPrecio());

                    txtCantidad.requestFocus();
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error con la base de datos \n Error: " + ex.getErrorCode());
            }

        }

    }

    private void limpiarFormulario() {
        txtCantidad.setValue(0.00);
        txtImporte.setValue(0.00);
        txtImporteLinea.setValue(0.00);
        txtImpuesto.setValue(0.00);
        txtTotal.setValue(0.00);
        txtDescuento.setValue(0.00);
        txtNombreCliente.setText("");
        txtMontoPagado.setValue(0.00);
        txtPrecio.setValue(0.00);
        txtComentario.setText("");
        txtDescripcion.setText("");

        clienteActual = null;
        producto = null;
        lineas = null;
        factura = null;

        borrarJTabla(jTable1);

    }

    private void localizarFactura() {
        if (txtNumero.getText().trim().length() > 0) {
            try {
                Connection conexion = BaseDeDatos.conectar();
                String sql = "SELECT d.idventa, d.idproducto, d.cantidad,d.precio,d.subtotal,d.descuentos,f.fecha,"
                        + "f.montopagado,c.Nombre,c.Apellido,c.Cedula,c.LimiteCredito,c.telefono,c.Direccion,c.idcliente,"
                        + "p.descripcion,p.codigo,p.costo,p.existencia,p.impuesto "
                        + "FROM tbldetalleFactura d "
                        + " JOIN tblfactura f ON f.idfactura=d.idventa "
                        + " JOIN tblcliente c ON c.idcliente=f.idcliente "
                        + " JOIN tblproducto p ON p.idproducto=d.idproducto"
                        + " WHERE d.idventa = ?";
                PreparedStatement sentenciaDeBusqueda = conexion.prepareStatement(sql);
                sentenciaDeBusqueda.setString(1, txtNumero.getText().trim());
                ResultSet rs = sentenciaDeBusqueda.executeQuery();

                clienteActual = null;
               //Leer la primera del resultset 
                if (rs.first() == true) {
                    if (lineas == null) {
                        lineas = new ArrayList<>();
                    }
                    lineas.add(new LineaDeFactura(new Producto(rs.getString("descripcion"), rs.getString("codigo"),
                            rs.getDouble("precio"), rs.getDouble("costo"), rs.getDouble("existencia"),
                            rs.getDouble("impuesto"), rs.getInt("idproducto")),
                            rs.getDouble("cantidad"), rs.getDouble("descuentos")));

                    if (clienteActual == null) {
                        clienteActual = new Cliente(rs.getString("nombre"), rs.getString("apellido"),
                                rs.getString("direccion"), rs.getString("telefono"), rs.getString("cedula"), rs.getDouble("limitecredito"), rs.getInt("idcliente"));

                        txtNombreCliente.setText(clienteActual.getNombre() + " " + clienteActual.getApellido());
                        txtMontoPagado.setValue(rs.getDouble("montopagado"));
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "No se encontraron registros en esta búsqueda");
                    return;
                }

                while (rs.next()) {
                    lineas.add(new LineaDeFactura(new Producto(rs.getString("descripcion"), rs.getString("codigo"),
                            rs.getDouble("precio"), rs.getDouble("costo"), rs.getDouble("existencia"),
                            rs.getDouble("impuesto"), rs.getInt("idproducto")),
                            rs.getDouble("cantidad"), rs.getDouble("descuentos")));
                }

                agregarListaDetalle();

                txtImporte.setValue(obtenerSubtotal());
                txtImpuesto.setValue(obtenerTotalDeImpuestos());
                txtTotal.setValue(obtenerTotal());
                txtDescuento.setValue(obtenerDescuentos());

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error con la base de datos \n Error: " + ex.getErrorCode());
            }

        }

    }

    private void desactivarControlesEnBusqueda() {
        btnGuardar.setEnabled(false);
        txtCodigo.setEnabled(false);
        btnAgregaLinea.setEnabled(false);
        txtCantidad.setEnabled(false);
        btnSeleccionarProducto.setEnabled(false);
    }

    private void activarControles() {
        btnGuardar.setEnabled(true);
        txtCodigo.setEnabled(true);
        btnAgregaLinea.setEnabled(true);
        txtCantidad.setEnabled(true);
        btnSeleccionarProducto.setEnabled(true);
    }
}
