/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Irving
 */
public class prueba {
    
    private final modelo mimodelo = new modelo();
    public int j=0;
    
    public static void main(String[]args) {
        prueba a =new prueba();
       
    }
    
    Double totalbd, cantidadbd;
    public prueba(){
        String clavePapel="01-3-BLA-2-87070-IEXSA-IMPORTADO";
        String fecha="2015-02-12";
        int conkghoj=11;
        int conbobpaq= 0;
        this.consumir(clavePapel, conkghoj, conbobpaq, fecha);
        System.out.println("-----------");
        this.antipeps3(entradas);
        
    }
    
    public ResultSet buscarExistenciaPapelfecha(String clavePapel,String fecha) {
        String q = "call sumaxistencia_fecha2('"+clavePapel+"','"+fecha+"');";
        try {
                PreparedStatement pstm = mimodelo.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    
    public ResultSet primeraentrada(String clavePapel) {
        String q = "select id_detalleentrada as id, total_temporal as kghoj, cantidad_temoporal as bobpaq,costo from detalleentrada where clave_papel = '"+clavePapel+"' and total_temporal>0 limit 1";
        try {
                PreparedStatement pstm = mimodelo.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }  
    
    public boolean updateteporalde(int newkghoj,int newbobpaq,int id) {
        String q=" UPDATE  `dis_paper`.`detalleentrada` SET  `cantidad_temoporal` =  '"+newbobpaq+"',total_temporal='"+newkghoj+"' WHERE `id_detalleentrada` =  '"+id+"' ;";
        
        try{
            PreparedStatement pstm = mimodelo.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage() + "nuevexis");
            return false;
        }
    }
    String entradas="";
    int newkghoj;
    int newbobpaq;
    double costoconsumo=0;
    int newtemkghoj,newtembobpaq;
    public void Peps3(String clavePapel, int conkghoj, int conbobpaq) {
        try {
            ResultSet primeraentrada = primeraentrada(clavePapel);
            while(primeraentrada.next()){
                int identrada= primeraentrada.getInt("id");
                int temkghoj = primeraentrada.getInt("kghoj");
                int tembobpaq = primeraentrada.getInt("bobpaq");
                Double costoentrada = primeraentrada.getDouble("costo");
                newtemkghoj = temkghoj-conkghoj;
                newtembobpaq = tembobpaq-conbobpaq;
                if(newtemkghoj<0){
                    //falta consumo
                    mimodelo.updateteporalde(0, 0, identrada);
                    newtemkghoj=newtemkghoj*-1;
                    newtembobpaq=newtembobpaq*-1;
                    costoconsumo = costoconsumo +(costoentrada *temkghoj);
                    Peps3(clavePapel,newtemkghoj,newtembobpaq);
                    entradas+=identrada+","+temkghoj+","+tembobpaq+",/";
                }else{
                    costoconsumo = costoconsumo +(costoentrada *conkghoj);
                    entradas+=identrada+","+conkghoj+","+conbobpaq+",/";
                    mimodelo.updateteporalde(newtemkghoj, newtembobpaq, identrada);
                    return;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean sumarexistencia(String clave) {
        String q1="CALL sumaexistencia3('"+clave+"')";
         try{
            PreparedStatement pstm = mimodelo.getConexion().prepareStatement(q1);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public void consumir(String clavePapel,int conkghoj,int conbobpaq,String fecha) {
        try {
            ResultSet existenciaactual = this.buscarExistenciaPapelfecha(clavePapel, fecha);
            while(existenciaactual.next()){
                int exiskghoj = existenciaactual.getInt("kghoj");
                int exisbobpaq = existenciaactual.getInt("bobpaq");
                if(conkghoj>exiskghoj){
                    System.out.println("no hay suficientes kghoj");
                    return;
                }else{
                    if(conbobpaq>exisbobpaq){
                        System.out.println("no hay suficientes bobpaq");
                        return;
                    }else{
                        Peps3(clavePapel,conkghoj,conbobpaq);
                        this.sumarexistencia(clavePapel);
                        System.out.println(costoconsumo);
                        System.out.println(entradas);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void antipeps3(String entradas){
        String entrada[] = entradas.split("/");
        for(int i=0;i<entrada.length;i++){
            try {
                System.out.println(entrada[i]);
                String detalleentrada[]=entrada[i].split(",");
                int identrada= Integer.parseInt(detalleentrada[0]);
                int kghoj= Integer.parseInt(detalleentrada[1]);
                int bobpaq= Integer.parseInt(detalleentrada[2]);
                int temkghoj=0,tembobpaq=0;
                ResultSet buscaentrada = this.buscaentrada(identrada);
                while(buscaentrada.next()){
                    temkghoj=buscaentrada.getInt("kghoj");
                    tembobpaq=buscaentrada.getInt("bobpaq");
                }
                int newtemkghoj = temkghoj+kghoj;
                int newtembobpaq = tembobpaq+bobpaq;
                mimodelo.updateteporalde(newtemkghoj, newtembobpaq, identrada);
            } catch (SQLException ex) {
                Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public ResultSet buscaentrada(int identrada) {
        String q = "select id_detalleentrada as id, total_temporal as kghoj, cantidad_temoporal as bobpaq,costo from detalleentrada where id_detalleentrada='"+identrada+"' and total_temporal>0 limit 1";
        try {
                PreparedStatement pstm = mimodelo.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }  
}
