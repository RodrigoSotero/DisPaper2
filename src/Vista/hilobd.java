/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;
import Modelo.modelo;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 *
 * @author Irving
 */
public class hilobd extends Thread {
    modelo mimodelo = new modelo();
    public hilobd(){
        System.out.println("a");
    }
    @Override
    public void run() {
        for(;;){
        try {
            ResultSet a = mimodelo.bucarMaxSalida();
            if(a==null){ System.out.println("a");
            break;}
            else{System.out.println("b");}
        } catch (Exception ex) {
            Logger.getLogger(hilobd.class.getName()).log(Level.SEVERE, null, ex);
        }}
    } 
    public void setMensaje(String msj){
        
    }   
}
