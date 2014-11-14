/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import Vista.*;

/**
 *
 * @author Favorito
 */
class TableCellRenderedColor extends DefaultTableCellRenderer {
    private Component componente;
    private Vista.MovimientosPapel movimientos = new MovimientosPapel();

    public Component getTableCellRendererComponent (final JTable table, Object value, boolean selected, boolean focused, int row, int column){
        componente = super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        componente.setForeground(Color.black);
        if(column==1||column==2){
            componente.setBackground(new Color(255,2,0));
        }else if(column==3||column==4){
            componente.setBackground(new Color(255,50,0));
        }else if(column==5||column==6){
            componente.setBackground(new Color(30,255,0));
        }else{
            componente.setBackground(Color.white);
        }
        return componente;
    }
    
}
