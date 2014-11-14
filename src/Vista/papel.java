/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Irving
 */
public class papel extends javax.swing.JFrame {

    /**
     * Creates new form papel
     */
    public papel() {
        initComponents();        
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        Fondo fondo = new Fondo();
        Dimension size = this.getSize();
        fondo.setSize(size);
        add(fondo);
    }
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("imagenes/logo.png"));


        return retValue;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        __grpClasePapel = new javax.swing.ButtonGroup();
        __grpCaras = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        __etqFech = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        __Bobina = new javax.swing.JRadioButton();
        __Hoja = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        __NombrePapel = new javax.swing.JComboBox();
        __etqNewNombre = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        __ColorPapel = new javax.swing.JComboBox();
        __etqNewColor = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        __Cara1 = new javax.swing.JRadioButton();
        __Cara2 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        __Ancho = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        __etqAlto_ = new javax.swing.JLabel();
        __Alto_ = new javax.swing.JTextField();
        __etqcm_ = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        __Gramaje = new javax.swing.JTextField();
        __GrPts = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        __Propiedad = new javax.swing.JComboBox();
        __etqNewPropiedad = new javax.swing.JLabel();
        __etqnamemarca = new javax.swing.JLabel();
        __Marca = new javax.swing.JComboBox();
        __NewMarca = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        __etqClave = new javax.swing.JLabel();
        __SALIR = new javax.swing.JButton();
        __NUEVO = new javax.swing.JButton();
        __GUARDAR = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        __Archivo = new javax.swing.JMenu();
        __menuMovimientosPapel = new javax.swing.JMenuItem();
        __menuAnterior = new javax.swing.JMenuItem();
        __menuConsultas = new javax.swing.JMenuItem();
        __menuReportes = new javax.swing.JMenuItem();
        __MenuAbrirArchivo = new javax.swing.JMenuItem();
        __menuCerrarSesion = new javax.swing.JMenuItem();
        __menuSalir = new javax.swing.JMenuItem();
        __Edicion = new javax.swing.JMenu();
        __menuCambiarFecha = new javax.swing.JMenuItem();
        __menuNuevoUsuario = new javax.swing.JMenuItem();
        __menuCambiarContraseña = new javax.swing.JMenuItem();
        __menuReporte = new javax.swing.JMenuItem();
        __menucerrarsesiones = new javax.swing.JMenuItem();
        __menuBajaPapel = new javax.swing.JMenuItem();
        __menuAcerca = new javax.swing.JMenuItem();
        __menuBackup = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(580, 498));
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Papyrus", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("Alta de Papel");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 11, 159, 39);

        __etqFech.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        __etqFech.setForeground(new java.awt.Color(255, 255, 255));
        __etqFech.setText("DD/MM/AAAA");
        getContentPane().add(__etqFech);
        __etqFech.setBounds(430, 10, 110, 22);

        jLabel3.setFont(new java.awt.Font("Papyrus", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("´Clase de Papel");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 60, 120, 20);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        __grpClasePapel.add(__Bobina);
        __Bobina.setForeground(new java.awt.Color(255, 255, 255));
        __Bobina.setText("Bobina");
        __Bobina.setNextFocusableComponent(__Hoja);
        __Bobina.setOpaque(false);
        __Bobina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __BobinaActionPerformed(evt);
            }
        });

        __grpClasePapel.add(__Hoja);
        __Hoja.setForeground(new java.awt.Color(255, 255, 255));
        __Hoja.setText("Hoja");
        __Hoja.setOpaque(false);
        __Hoja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __HojaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(__Bobina)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(__Hoja)
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(__Bobina)
                    .addComponent(__Hoja))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(30, 90, 180, 40);

        jLabel10.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Nombre:");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(30, 160, 55, 30);

        __NombrePapel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona....." }));
        __NombrePapel.setNextFocusableComponent(__ColorPapel);
        __NombrePapel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __NombrePapelActionPerformed(evt);
            }
        });
        getContentPane().add(__NombrePapel);
        __NombrePapel.setBounds(90, 160, 125, 32);

        __etqNewNombre.setFont(new java.awt.Font("Papyrus", 0, 12)); // NOI18N
        __etqNewNombre.setForeground(new java.awt.Color(255, 255, 255));
        __etqNewNombre.setText("Nuevo Nombre...");
        getContentPane().add(__etqNewNombre);
        __etqNewNombre.setBounds(60, 200, 150, 20);

        jLabel6.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Color:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(40, 240, 43, 30);

        __ColorPapel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona....." }));
        __ColorPapel.setName(""); // NOI18N
        __ColorPapel.setNextFocusableComponent(__Cara1);
        __ColorPapel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __ColorPapelActionPerformed(evt);
            }
        });
        getContentPane().add(__ColorPapel);
        __ColorPapel.setBounds(90, 240, 130, 32);

        __etqNewColor.setFont(new java.awt.Font("Papyrus", 0, 12)); // NOI18N
        __etqNewColor.setForeground(new java.awt.Color(255, 255, 255));
        __etqNewColor.setText("Nuevo Color...");
        getContentPane().add(__etqNewColor);
        __etqNewColor.setBounds(70, 280, 130, 20);

        jLabel1.setFont(new java.awt.Font("Papyrus", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Caras del Papel");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(80, 320, 120, 20);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setOpaque(false);

        __grpCaras.add(__Cara1);
        __Cara1.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __Cara1.setForeground(new java.awt.Color(255, 255, 255));
        __Cara1.setText("1 Cara");
        __Cara1.setNextFocusableComponent(__Cara2);
        __Cara1.setOpaque(false);
        __Cara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __Cara1ActionPerformed(evt);
            }
        });

        __grpCaras.add(__Cara2);
        __Cara2.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __Cara2.setForeground(new java.awt.Color(255, 255, 255));
        __Cara2.setText("2 Caras");
        __Cara2.setNextFocusableComponent(__Alto_);
        __Cara2.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(__Cara2)
                    .addComponent(__Cara1))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(__Cara1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(__Cara2)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(80, 350, 120, 70);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setMaximumSize(new java.awt.Dimension(0, 0));
        jPanel3.setMinimumSize(new java.awt.Dimension(213, 191));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ancho:");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(30, 50, 47, 30);

        __Ancho.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        __Ancho.setNextFocusableComponent(__Gramaje);
        jPanel3.add(__Ancho);
        __Ancho.setBounds(80, 50, 70, 30);

        jLabel11.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("cm.");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(150, 50, 30, 30);

        __etqAlto_.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __etqAlto_.setForeground(new java.awt.Color(255, 255, 255));
        __etqAlto_.setText("Alto:");
        jPanel3.add(__etqAlto_);
        __etqAlto_.setBounds(40, 10, 34, 30);

        __Alto_.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        __Alto_.setNextFocusableComponent(__Ancho);
        jPanel3.add(__Alto_);
        __Alto_.setBounds(80, 10, 70, 30);

        __etqcm_.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __etqcm_.setForeground(new java.awt.Color(255, 255, 255));
        __etqcm_.setText("cm.");
        jPanel3.add(__etqcm_);
        __etqcm_.setBounds(150, 10, 30, 30);

        jLabel12.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Gramaje:");
        jPanel3.add(jLabel12);
        jLabel12.setBounds(20, 90, 56, 30);

        __Gramaje.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        __Gramaje.setNextFocusableComponent(__GrPts);
        jPanel3.add(__Gramaje);
        __Gramaje.setBounds(80, 90, 70, 30);

        __GrPts.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Grs.", "Pts." }));
        __GrPts.setNextFocusableComponent(__Propiedad);
        __GrPts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __GrPtsActionPerformed(evt);
            }
        });
        jPanel3.add(__GrPts);
        __GrPts.setBounds(160, 90, 60, 30);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(274, 44, 230, 130);

        jLabel15.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Propiedad:");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(260, 190, 68, 30);

        __Propiedad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona....." }));
        __Propiedad.setNextFocusableComponent(__Marca);
        __Propiedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __PropiedadActionPerformed(evt);
            }
        });
        getContentPane().add(__Propiedad);
        __Propiedad.setBounds(330, 190, 180, 32);

        __etqNewPropiedad.setFont(new java.awt.Font("Papyrus", 0, 12)); // NOI18N
        __etqNewPropiedad.setForeground(new java.awt.Color(255, 255, 255));
        __etqNewPropiedad.setText("Nuevo Propietario...");
        getContentPane().add(__etqNewPropiedad);
        __etqNewPropiedad.setBounds(340, 230, 160, 20);

        __etqnamemarca.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __etqnamemarca.setForeground(new java.awt.Color(255, 255, 255));
        __etqnamemarca.setText("Marca:");
        getContentPane().add(__etqnamemarca);
        __etqnamemarca.setBounds(280, 260, 43, 30);

        __Marca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona....." }));
        __Marca.setNextFocusableComponent(__GUARDAR);
        __Marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __MarcaActionPerformed(evt);
            }
        });
        getContentPane().add(__Marca);
        __Marca.setBounds(330, 260, 180, 32);

        __NewMarca.setFont(new java.awt.Font("Papyrus", 0, 12)); // NOI18N
        __NewMarca.setForeground(new java.awt.Color(255, 255, 255));
        __NewMarca.setText("Nueva Marca...");
        getContentPane().add(__NewMarca);
        __NewMarca.setBounds(340, 300, 150, 20);

        jLabel16.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Clave Papel:");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(350, 330, 78, 23);

        __etqClave.setBackground(new java.awt.Color(0, 0, 0));
        __etqClave.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(__etqClave);
        __etqClave.setBounds(243, 360, 290, 27);

        __SALIR.setText("Regresar");
        __SALIR.setNextFocusableComponent(__Bobina);
        getContentPane().add(__SALIR);
        __SALIR.setBounds(430, 400, 100, 30);

        __NUEVO.setText("Nuevo");
        __NUEVO.setNextFocusableComponent(__SALIR);
        getContentPane().add(__NUEVO);
        __NUEVO.setBounds(330, 400, 80, 30);

        __GUARDAR.setText("Guardar");
        __GUARDAR.setNextFocusableComponent(__NUEVO);
        getContentPane().add(__GUARDAR);
        __GUARDAR.setBounds(240, 400, 80, 30);

        __Archivo.setText("Archivo");

        __menuMovimientosPapel.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        __menuMovimientosPapel.setText("Movimientos de Papel");
        __Archivo.add(__menuMovimientosPapel);

        __menuAnterior.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        __menuAnterior.setText("Regresar al menú anterior");
        __menuAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __menuAnteriorActionPerformed(evt);
            }
        });
        __Archivo.add(__menuAnterior);

        __menuConsultas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        __menuConsultas.setText("Consultas");
        __Archivo.add(__menuConsultas);

        __menuReportes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        __menuReportes.setText("Generar un reporte...");
        __menuReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __menuReportesActionPerformed(evt);
            }
        });
        __Archivo.add(__menuReportes);

        __MenuAbrirArchivo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        __MenuAbrirArchivo.setText("Abrir Archivo...");
        __MenuAbrirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __MenuAbrirArchivoActionPerformed(evt);
            }
        });
        __Archivo.add(__MenuAbrirArchivo);

        __menuCerrarSesion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        __menuCerrarSesion.setText("Cerrar Sesion");
        __Archivo.add(__menuCerrarSesion);

        __menuSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        __menuSalir.setText("Salir del Sistema");
        __menuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __menuSalirActionPerformed(evt);
            }
        });
        __Archivo.add(__menuSalir);

        jMenuBar1.add(__Archivo);

        __Edicion.setText("Edición");

        __menuCambiarFecha.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        __menuCambiarFecha.setText("Cambiar Fecha del Sistema");
        __menuCambiarFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __menuCambiarFechaActionPerformed(evt);
            }
        });
        __Edicion.add(__menuCambiarFecha);

        __menuNuevoUsuario.setText("Nuevo Usuario...");
        __Edicion.add(__menuNuevoUsuario);

        __menuCambiarContraseña.setText("Cambiar mi Contraseña");
        __Edicion.add(__menuCambiarContraseña);

        __menuReporte.setText("Reporte de Usuarios");
        __Edicion.add(__menuReporte);

        __menucerrarsesiones.setText("Cerrar Todas las Sesiones");
        __Edicion.add(__menucerrarsesiones);

        __menuBajaPapel.setText("Baja de Papel");
        __Edicion.add(__menuBajaPapel);

        __menuAcerca.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, java.awt.event.InputEvent.CTRL_MASK));
        __menuAcerca.setText("Acerca de...");
        __Edicion.add(__menuAcerca);

        __menuBackup.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        __menuBackup.setText("Backup");
        __menuBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __menuBackupActionPerformed(evt);
            }
        });
        __Edicion.add(__menuBackup);

        jMenuBar1.add(__Edicion);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void __menuAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___menuAnteriorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___menuAnteriorActionPerformed

    private void __menuReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___menuReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___menuReportesActionPerformed

    private void __MenuAbrirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___MenuAbrirArchivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___MenuAbrirArchivoActionPerformed

    private void __menuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___menuSalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___menuSalirActionPerformed

    private void __menuCambiarFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___menuCambiarFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___menuCambiarFechaActionPerformed

    private void __BobinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___BobinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___BobinaActionPerformed

    private void __HojaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___HojaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___HojaActionPerformed

    private void __NombrePapelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___NombrePapelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___NombrePapelActionPerformed

    private void __ColorPapelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___ColorPapelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___ColorPapelActionPerformed

    private void __Cara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___Cara1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___Cara1ActionPerformed

    private void __GrPtsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___GrPtsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___GrPtsActionPerformed

    private void __PropiedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___PropiedadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___PropiedadActionPerformed

    private void __MarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___MarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___MarcaActionPerformed

    private void __menuBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___menuBackupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___menuBackupActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(papel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(papel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(papel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(papel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new papel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField __Alto_;
    public javax.swing.JTextField __Ancho;
    public javax.swing.JMenu __Archivo;
    public javax.swing.JRadioButton __Bobina;
    public javax.swing.JRadioButton __Cara1;
    public javax.swing.JRadioButton __Cara2;
    public javax.swing.JComboBox __ColorPapel;
    public javax.swing.JMenu __Edicion;
    public javax.swing.JButton __GUARDAR;
    public javax.swing.JComboBox __GrPts;
    public javax.swing.JTextField __Gramaje;
    public javax.swing.JRadioButton __Hoja;
    public javax.swing.JComboBox __Marca;
    public javax.swing.JMenuItem __MenuAbrirArchivo;
    public javax.swing.JButton __NUEVO;
    public javax.swing.JLabel __NewMarca;
    public javax.swing.JComboBox __NombrePapel;
    public javax.swing.JComboBox __Propiedad;
    public javax.swing.JButton __SALIR;
    public javax.swing.JLabel __etqAlto_;
    public javax.swing.JLabel __etqClave;
    public javax.swing.JLabel __etqFech;
    public javax.swing.JLabel __etqNewColor;
    public javax.swing.JLabel __etqNewNombre;
    public javax.swing.JLabel __etqNewPropiedad;
    public javax.swing.JLabel __etqcm_;
    public javax.swing.JLabel __etqnamemarca;
    public javax.swing.ButtonGroup __grpCaras;
    public javax.swing.ButtonGroup __grpClasePapel;
    public javax.swing.JMenuItem __menuAcerca;
    public javax.swing.JMenuItem __menuAnterior;
    public javax.swing.JMenuItem __menuBackup;
    public javax.swing.JMenuItem __menuBajaPapel;
    public javax.swing.JMenuItem __menuCambiarContraseña;
    public javax.swing.JMenuItem __menuCambiarFecha;
    public javax.swing.JMenuItem __menuCerrarSesion;
    public javax.swing.JMenuItem __menuConsultas;
    public javax.swing.JMenuItem __menuMovimientosPapel;
    public javax.swing.JMenuItem __menuNuevoUsuario;
    public javax.swing.JMenuItem __menuReporte;
    public javax.swing.JMenuItem __menuReportes;
    public javax.swing.JMenuItem __menuSalir;
    public javax.swing.JMenuItem __menucerrarsesiones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
