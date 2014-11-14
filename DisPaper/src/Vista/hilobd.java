/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import javax.swing.JOptionPane;
import Modelo.modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Irving
 */
public class hilobd extends Thread {
    String mensaje;
    modelo mimodelo = new modelo();
    
    public hilobd(String msg){

        super(msg);
        
    }

     

    public void run() {
        for(;;){
            try {
                    ResultSet conexionbd = mimodelo.bucarMaxEntrada();
                    conexionbd.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Conexion Perdida con la base de Datos");
                    System.exit(0);
                }

            }
        } 
    

     

    public void setMensaje(String msj){
        this.mensaje = msj;
    }   
}
