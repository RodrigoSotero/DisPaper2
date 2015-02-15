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
 * @author Rodrigo
 */
public class Tras extends javax.swing.JFrame {

    /**
     * Creates new form Tras
     */
    public Tras() {
        initComponents();
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

        __ClasePapelTraspaso = new javax.swing.ButtonGroup();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        __BobinaTras = new javax.swing.JRadioButton();
        __HojaTras = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        __Destino = new javax.swing.JTextField();
        __Origen = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        __ClavePapelTras = new javax.swing.JTextField();
        __CantidadTotal = new javax.swing.JTextField();
        __etqCantidad = new javax.swing.JLabel();
        __etqCantidadPT = new javax.swing.JLabel();
        __CantidadPT = new javax.swing.JTextField();
        __CANCELARTRASPASO = new javax.swing.JButton();
        __ACEPTARTRASPASO = new javax.swing.JButton();
        __etqCantidadPT1 = new javax.swing.JLabel();
        __CostoTrasEntrada = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        __folioT = new javax.swing.JTextField();
        __etqCantidadPT2 = new javax.swing.JLabel();
        __CostoTras1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jLabel8.setFont(new java.awt.Font("Papyrus", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 0));
        jLabel8.setText("Traspasos de Papel");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Clase de Papel para Traspaso"));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setOpaque(false);

        __ClasePapelTraspaso.add(__BobinaTras);
        __BobinaTras.setForeground(new java.awt.Color(255, 255, 255));
        __BobinaTras.setText("Bobina");
        __BobinaTras.setNextFocusableComponent(__HojaTras);
        __BobinaTras.setOpaque(false);
        __BobinaTras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __BobinaTrasActionPerformed(evt);
            }
        });

        __ClasePapelTraspaso.add(__HojaTras);
        __HojaTras.setForeground(new java.awt.Color(255, 255, 255));
        __HojaTras.setText("Hoja");
        __HojaTras.setNextFocusableComponent(__Origen);
        __HojaTras.setOpaque(false);
        __HojaTras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __HojaTrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(__BobinaTras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(__HojaTras)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(__BobinaTras)
                    .addComponent(__HojaTras))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel21.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Origen:");

        jLabel33.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText(">>>>>>>");

        jLabel19.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Destino:");

        __Destino.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __Destino.setMaximumSize(new java.awt.Dimension(10, 30));
        __Destino.setMinimumSize(new java.awt.Dimension(10, 30));
        __Destino.setName("__Destino"); // NOI18N
        __Destino.setNextFocusableComponent(__ClavePapelTras);
        __Destino.setPreferredSize(new java.awt.Dimension(10, 30));
        __Destino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __DestinoActionPerformed(evt);
            }
        });

        __Origen.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __Origen.setMaximumSize(new java.awt.Dimension(10, 30));
        __Origen.setMinimumSize(new java.awt.Dimension(10, 30));
        __Origen.setName("__Origen"); // NOI18N
        __Origen.setNextFocusableComponent(__Destino);
        __Origen.setPreferredSize(new java.awt.Dimension(10, 30));
        __Origen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __OrigenActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Clave de Papel:");

        __ClavePapelTras.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __ClavePapelTras.setMaximumSize(new java.awt.Dimension(10, 30));
        __ClavePapelTras.setMinimumSize(new java.awt.Dimension(10, 30));
        __ClavePapelTras.setName(""); // NOI18N
        __ClavePapelTras.setNextFocusableComponent(__CantidadPT);
        __ClavePapelTras.setPreferredSize(new java.awt.Dimension(10, 30));
        __ClavePapelTras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __ClavePapelTrasActionPerformed(evt);
            }
        });

        __CantidadTotal.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __CantidadTotal.setMaximumSize(new java.awt.Dimension(10, 30));
        __CantidadTotal.setMinimumSize(new java.awt.Dimension(10, 30));
        __CantidadTotal.setName(""); // NOI18N
        __CantidadTotal.setNextFocusableComponent(__CostoTrasEntrada);
        __CantidadTotal.setPreferredSize(new java.awt.Dimension(10, 30));

        __etqCantidad.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __etqCantidad.setForeground(new java.awt.Color(255, 255, 255));
        __etqCantidad.setText("Cantidad de Paquetes/Tarimas:");

        __etqCantidadPT.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __etqCantidadPT.setForeground(new java.awt.Color(255, 255, 255));
        __etqCantidadPT.setText("Cantidad de Hojas:");

        __CantidadPT.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __CantidadPT.setMaximumSize(new java.awt.Dimension(10, 30));
        __CantidadPT.setMinimumSize(new java.awt.Dimension(10, 30));
        __CantidadPT.setName(""); // NOI18N
        __CantidadPT.setNextFocusableComponent(__CantidadTotal);
        __CantidadPT.setPreferredSize(new java.awt.Dimension(10, 30));

        __CANCELARTRASPASO.setText("Cancelar");
        __CANCELARTRASPASO.setNextFocusableComponent(__BobinaTras);
        __CANCELARTRASPASO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __CANCELARTRASPASOActionPerformed(evt);
            }
        });

        __ACEPTARTRASPASO.setText("Aceptar");
        __ACEPTARTRASPASO.setNextFocusableComponent(__CANCELARTRASPASO);
        __ACEPTARTRASPASO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __ACEPTARTRASPASOActionPerformed(evt);
            }
        });

        __etqCantidadPT1.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __etqCantidadPT1.setForeground(new java.awt.Color(255, 255, 255));
        __etqCantidadPT1.setText("Costo de Entrada:");

        __CostoTrasEntrada.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __CostoTrasEntrada.setMaximumSize(new java.awt.Dimension(10, 30));
        __CostoTrasEntrada.setMinimumSize(new java.awt.Dimension(10, 30));
        __CostoTrasEntrada.setName(""); // NOI18N
        __CostoTrasEntrada.setNextFocusableComponent(__ACEPTARTRASPASO);
        __CostoTrasEntrada.setPreferredSize(new java.awt.Dimension(10, 30));

        jLabel22.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Folio:");

        __folioT.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __folioT.setEnabled(false);
        __folioT.setMaximumSize(new java.awt.Dimension(10, 30));
        __folioT.setMinimumSize(new java.awt.Dimension(10, 30));
        __folioT.setName("__Origen"); // NOI18N
        __folioT.setNextFocusableComponent(__Destino);
        __folioT.setPreferredSize(new java.awt.Dimension(10, 30));
        __folioT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __folioTActionPerformed(evt);
            }
        });

        __etqCantidadPT2.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __etqCantidadPT2.setForeground(new java.awt.Color(255, 255, 255));
        __etqCantidadPT2.setText("Costo de Salida:");

        __CostoTras1.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __CostoTras1.setMaximumSize(new java.awt.Dimension(10, 30));
        __CostoTras1.setMinimumSize(new java.awt.Dimension(10, 30));
        __CostoTras1.setName(""); // NOI18N
        __CostoTras1.setNextFocusableComponent(__ACEPTARTRASPASO);
        __CostoTras1.setPreferredSize(new java.awt.Dimension(10, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(__CANCELARTRASPASO, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(230, 230, 230)
                .addComponent(__ACEPTARTRASPASO, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel8))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(156, 156, 156)
                            .addComponent(jLabel35))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(60, 60, 60)
                                    .addComponent(jLabel21)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(__Origen, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(__etqCantidad, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(__etqCantidadPT, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGap(27, 27, 27)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel33)
                                    .addGap(4, 4, 4)
                                    .addComponent(jLabel19)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(__Destino, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(__ClavePapelTras, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(__CantidadTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(__CantidadPT, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(__folioT, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(123, 123, 123)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(__etqCantidadPT1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(__CostoTrasEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(__etqCantidadPT2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(__CostoTras1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(__folioT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(__Origen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(__Destino, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(__ClavePapelTras, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(__CantidadPT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(__etqCantidadPT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(__CantidadTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(__etqCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(__CostoTras1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(__etqCantidadPT2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(__CostoTrasEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(__etqCantidadPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(__CANCELARTRASPASO, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(__ACEPTARTRASPASO, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void __BobinaTrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___BobinaTrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___BobinaTrasActionPerformed

    private void __HojaTrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___HojaTrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___HojaTrasActionPerformed

    private void __DestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___DestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___DestinoActionPerformed

    private void __OrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___OrigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___OrigenActionPerformed

    private void __ClavePapelTrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___ClavePapelTrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___ClavePapelTrasActionPerformed

    private void __CANCELARTRASPASOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___CANCELARTRASPASOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___CANCELARTRASPASOActionPerformed

    private void __folioTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___folioTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___folioTActionPerformed

    private void __ACEPTARTRASPASOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___ACEPTARTRASPASOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___ACEPTARTRASPASOActionPerformed

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
            java.util.logging.Logger.getLogger(Tras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton __ACEPTARTRASPASO;
    public javax.swing.JRadioButton __BobinaTras;
    public javax.swing.JButton __CANCELARTRASPASO;
    public javax.swing.JTextField __CantidadPT;
    public javax.swing.JTextField __CantidadTotal;
    public javax.swing.ButtonGroup __ClasePapelTraspaso;
    public javax.swing.JTextField __ClavePapelTras;
    public javax.swing.JTextField __CostoTras1;
    public javax.swing.JTextField __CostoTrasEntrada;
    public javax.swing.JTextField __Destino;
    public javax.swing.JRadioButton __HojaTras;
    public javax.swing.JTextField __Origen;
    public javax.swing.JLabel __etqCantidad;
    public javax.swing.JLabel __etqCantidadPT;
    public javax.swing.JLabel __etqCantidadPT1;
    public javax.swing.JLabel __etqCantidadPT2;
    public javax.swing.JTextField __folioT;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel21;
    public javax.swing.JLabel jLabel22;
    public javax.swing.JLabel jLabel33;
    public javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
