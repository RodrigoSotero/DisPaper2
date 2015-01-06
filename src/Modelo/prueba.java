/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import Controlador.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
/**
 *
 * @author Irving
 */
public class prueba {
    
    private final modelo mimodelo = new modelo();
    
    public static void main(String[]args) throws SQLException{
       System.out.println(new prueba().buscarFolioMaxSalidaB());
    }
    public String buscarFolioMaxSalidaB() throws java.sql.SQLException{   
        Calendar Cal= Calendar.getInstance();
        int anio=Integer.parseInt(Cal.get(Cal.YEAR)+"");
        try {
            String id = "SELECT folio from salidab order by id_salida desc limit 1;";
            PreparedStatement pstm =mimodelo.getConexion().prepareStatement(id);
            ResultSet res = pstm.executeQuery();
            if(!res.next()){
                return "SALBOB"+anio+"-1";
            }
            res.beforeFirst();
            while(res.next()){
                String folio = res.getString("folio");
                if(folio==null){
                    return "SALBOB"+anio+"-1";
                }else{
                    String aniofolio = folio.substring(6);
                    String aniof[]= aniofolio.split("-");
                    if(Integer.parseInt(aniof[0])<anio){
                        int fol = Integer.parseInt(folio.replace("SALBOB"+aniof[0]+"-", ""));
                        int ano = Integer.parseInt(folio.substring(6, 10));
                        if(anio>ano) fol=1; else fol++;
                        folio= "SALBOB"+anio+"-"+fol; 
                    }else 
                    if(Integer.parseInt(aniof[0])==anio){
                        int fol = Integer.parseInt(folio.replace("SALBOB"+aniof[0]+"-", ""));
                        int ano = Integer.parseInt(folio.substring(6, 10));
                        if(anio>ano) fol=1; else fol++;
                        folio= "SALBOB"+anio+"-"+fol; 
                    }   
                }   
                return folio;
            }
            }catch(Exception e){
                if(e.getMessage().equals("Illegal operation on empty result set.")){
                    return "SALBOB"+anio+"-1";
                }else{
                    System.err.println( e.getMessage());
                    
                    return null;
                }
            }
        //return "SALBOB"+anio+"-1";
        return null;
    }
    public String buscarFolioEntrada() throws java.sql.SQLException{
        Calendar Cal= Calendar.getInstance();
        int anio=Integer.parseInt(Cal.get(Cal.YEAR)+"");
        System.out.println(anio);
        try {
            String id = "SELECT folio from entrada order by id_entrada desc limit 1 ";
            PreparedStatement pstm = mimodelo.getConexion().prepareStatement(id);
            ResultSet res = pstm.executeQuery();
            if(!res.next()){
                return "ENT"+anio+"-1";
            }
            res.beforeFirst();
            while(res.next()){
                String folio = res.getString("folio");
                if(folio==null||folio.isEmpty()){
                    folio= "ENT"+anio+"-1";
                }else{
                    String aniofolio = folio.substring(3);
                    String[] aniof = aniofolio.split("-");
                    if(Integer.parseInt(aniof[0])<anio){
                        int fol = Integer.parseInt(folio.replace("ENT"+aniof[0]+"-", ""));
                        System.err.println(fol);
                        int ano = Integer.parseInt(folio.substring(3, 7));
                        if(anio>ano) fol=1; else fol++;
                        folio= "ENT"+anio+"-"+fol; 
                    }
                }
                //System.err.println(folio);
                /*else{
                    int fol = Integer.parseInt(folio.replace("ENT"+anio+"-", ""));
                    System.err.println(fol);
                    int ano = Integer.parseInt(folio.substring(3, 7));
                    if(anio>ano) fol=1; else fol++;
                    folio= "ENT"+anio+"-"+fol; 
                }            */  
                return folio;
           }
        }catch(SQLException e){
            if(e.getMessage().equals("Illegal operation on empty result set.")){
                return "ENT"+anio+"-1";
            }else{
                
//return "ENT"+anio+"-1";
                return null;
            }
        }
        return null;
    }
    public void a(){
        for(;;){
            try {
                
                Connection conexion = mimodelo.getConexion();
                if(conexion!=null){
                    System.out.println(conexion+"");
                }
                else{
                    System.out.println("coexion perdida");
                    break;
                }
            } catch (Exception ex) {
                Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public int busquedaid(String nombre){
        String texto="";
        if(nombre.isEmpty()){
            return 0;
        }else{
            ResultSet id = null;
            switch (nombre){
                case "color":
                    texto = "TERRA";
                    id=mimodelo.buscaridColor(texto);
                    break;
                case "nombre":
                    texto = "CARTULINA SULFATADA";
                    id=mimodelo.buscaridNombre(texto);
                    break;
                case "propiedad":
                    texto = "TERRACOTA";
                    id=mimodelo.buscaridPropietario(texto);
                    break;
                case "marca":
                    texto = "GALERI";
                    id=mimodelo.buscaridMarca(texto);
                    break;    
            }
            try{
                while(id.next()){
                    return id.getInt("id");
                }
            }catch(Exception a){
                return 0;
            }
        }
        return 0;
    }
    
    public Object[][] restarecursiva(Object[][] array){
                
        return null;
    }
    
    public prueba(){
        mimodelo.bp("aaaaaaaa");
    }
    public String busquedaNombre(String tabla, int id){
        ResultSet p=null;
        switch(tabla){
            case "tipo_entrada":
                p= mimodelo.buscaTipoEntrada(id);
                break;
        }
        try {
            while(p.next()){
                return p.getString("descripcion");
            }
        } catch (SQLException ex) {
           return null;
        }
    return null;
    }
    
    public ResultSet buscaTipoEntrada(String parametro) {
        String q = "SELECT * FROM TIPO_ENTRADA where tipo_entrada like '%"+parametro+"%' ";
        try {
                PreparedStatement pstm = mimodelo.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
    
    public ResultSet TodosSalida() {
        String q = "select * from table_salidas";
        try {
                PreparedStatement pstm = mimodelo.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
    public ResultSet buscarExistenciaPapelfecha(String clavePapel,String fecha) {
        String q = "call sumaxistencia_fecha('"+clavePapel+"','"+fecha+"');";
        try {
                PreparedStatement pstm = mimodelo.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
    
    
    public int busquedaid(String parametro,String abuscar){
        if(parametro.isEmpty()){
            System.out.println("Debe Ingresar un Tipo de Entrada de Papel");
        }else{
            ResultSet p=null;
            switch (abuscar){
                case "tipoentrada":
                    p = mimodelo.buscaTipoEntrada(parametro,false);
                    break;
                case "propietario":
                    p = mimodelo.buscarPropiedad(parametro,false);
                    break; 
                case "proveedor":
                    p = mimodelo.buscaProveedor(parametro,false);
                    break;
                case "cliente":
                    p=mimodelo.buscarCliente(parametro,false);
                    break;
            }
            try{
                while(p.next()){
                    return p.getInt("id");
                }
            }catch(Exception a){
                return 0;
            }
        }
    return 0;
    }
    
    public boolean compara(String fecha){
        fecha = fecha +" 00:00:00";
        Calendar Cal= Calendar.getInstance();
        String anio=Cal.get(Cal.YEAR)+"";
        String mes=(Cal.get(Cal.MONTH)+1)<10?"0"+(Cal.get(Cal.MONTH)+1):(Cal.get(Cal.MONTH)+1)+"";
        String dia=Cal.get(Cal.DATE)<10?"0"+Cal.get(Cal.DATE):(Cal.get(Cal.DATE))+"";
        String fechaactual=anio+"-"+mes+"-"+dia+ " 00:00:00";
//        int max = Integer.parseInt(calcularHoras(fecha, fechaactual,2));
//        System.out.println(max);
//        if(max<8&&max>-1){
//               return true;                                     
//        }
        System.out.println(calcularHoras(fecha, fechaactual,2));
        return false;
    }

    public String calcularHoras(String fechaInicial, String fechaFinal, int parametro ) {
        java.util.GregorianCalendar jCal = new java.util.GregorianCalendar();
        java.util.GregorianCalendar jCal2 = new java.util.GregorianCalendar();
                //2014-07-20 00:00:00:00
            //jCal.set(year, month, date, hourOfDay, minute)
        System.out.println("00 "+Integer.parseInt(fechaInicial.substring(14,16)));
        
        jCal.set(Integer.parseInt(fechaInicial.substring(0,4)), 
                Integer.parseInt(fechaInicial.substring(5,7))-1, 
                Integer.parseInt(fechaInicial.substring(8,10)), 
                Integer.parseInt(fechaInicial.substring(11,13)),
                Integer.parseInt(fechaInicial.substring(14,16)), 
                Integer.parseInt(fechaInicial.substring(17,19)));
        jCal2.set(Integer.parseInt(fechaFinal.substring(0,4)), 
                Integer.parseInt(fechaFinal.substring(5,7))-1, 
                Integer.parseInt(fechaFinal.substring(8,10)), 
                Integer.parseInt(fechaFinal.substring(11,13)),
                Integer.parseInt(fechaFinal.substring(14,16)), 
                Integer.parseInt(fechaFinal.substring(17,19)));     
        long diferencia = jCal2.getTime().getTime()-jCal.getTime().getTime();
        
        double minutos = diferencia / (1000 * 60);
        long horas = (long) (minutos / 60);
        long minuto = (long) (minutos%60);
        long dias = horas/24;
        
        //Calcular meses...
        //Crear vector para almacenar los diferentes dias maximos segun correponda
        String[] mesesAnio = new String[12];
        mesesAnio[0] = "31";
        //validacion de los años bisiestos
        if (jCal.isLeapYear(jCal.YEAR)){mesesAnio[1] = "29";}else{mesesAnio[1] = "28";}
        mesesAnio[2] = "31";
        mesesAnio[3] = "30";
        mesesAnio[4] = "31";
        mesesAnio[5] = "30";
        mesesAnio[6] = "31";
        mesesAnio[7] = "31";
        mesesAnio[8] = "30";
        mesesAnio[9] = "31";
        mesesAnio[10] = "30";
        mesesAnio[11] = "31";
        int diasRestantes = (int) dias;
        //variable almacenará el total de meses que hay en esos dias
        int totalMeses = 0;
        int mesActual = jCal.MONTH;
        //Restar los dias de cada mes desde la fecha de ingreso hasta que ya no queden sufcientes dias para 
        // completar un mes.
        for (int i=0; i<=11; i++ ){
            //Validar año, si sumando 1 al mes actual supera el fin de año, 
            // setea la variable a principio de año 
            if ((mesActual+1)>=12){
                mesActual = i;
            }
            //Validar que el numero de dias resultantes de la resta de las 2 fechas, menos los dias
            //del mes correspondiente sea mayor a cero, de ser asi totalMeses aumenta,continuar hasta 
            //que ya nos se cumpla.
            if ((diasRestantes -Integer.parseInt(mesesAnio[mesActual]))>=0){
                totalMeses ++;
                diasRestantes = diasRestantes- Integer.parseInt(mesesAnio[mesActual]);
                mesActual ++;
            }else{
                break;
            }
        }
        //Resto de horas despues de sacar los dias
        horas = horas % 24;
        if(horas<0){
            //mensaje(3,"La hora final es incorecta");
            return null;
        }
        String salida ="";
        if (totalMeses > 0){
            if (totalMeses > 1)
                salida = salida+  String.valueOf(totalMeses)+" Meses,  ";
            else
                salida = salida+  String.valueOf(totalMeses)+" Mes, ";
        }
        if (diasRestantes > 0){
            if (diasRestantes > 1)
                salida = salida+  String.valueOf(diasRestantes)+" Dias, ";
            else
                salida = salida+  String.valueOf(diasRestantes)+" Dia, ";
        }               
         salida = salida + String.valueOf(horas)+":"+String.valueOf(minuto);
         if(parametro==1){
             return salida;
         }
         if(parametro ==2){
             System.out.println(dias);
             return dias+"";
         }
        return null;
         
    }

    public void PEPS(String cpsalh,double totalhojassalh,double cantsalh) {
        if(totalhojassalh==0&&cantsalh==0){
            System.err.println("bye");
            return;
        }
        try{
        ResultSet PE = mimodelo.buscarPrimeraEntrada(cpsalh,"2014-07-21");
                    String fecpe="";
                    Double total=0.0,cantidad=0.0;
                    String ID="";
                    while(PE.next()){
                        fecpe=PE.getString("fecha_entrada");
                        if(fecpe==null){
                            //mensaje(2,"no hay entradas de este papel");
                            System.err.println("no hay entradas");
                            return;
                        }else{
                            total=Double.parseDouble(PE.getString("total"));
                            cantidad=Double.parseDouble(PE.getString("cantidad"));
                            ID=PE.getString("id");
                        }
                    }
                    if(total==totalhojassalh){
                        if(cantidad==cantsalh){
                            System.err.println("iguanas ");
                            mimodelo.nuevacantidadtemporal(ID,cantidad-cantidad,total-total);
                        }
                    }
                    if(total>totalhojassalh){
                        if(cantidad>cantsalh){
                            System.err.println("mayores ");
                            if(totalhojassalh<0&&cantsalh<0){
                                totalhojassalh=totalhojassalh*-1;
                                cantsalh=cantsalh*-1;
                            }
                            mimodelo.nuevacantidadtemporal(ID,cantidad-cantsalh,total-totalhojassalh);
                        }
                    }
                    if(total<totalhojassalh){
                        if(cantidad<cantsalh){
                            System.err.println("menores ");
                            mimodelo.nuevacantidadtemporal(ID,cantidad-cantidad,total-total);
                            totalhojassalh=total-totalhojassalh;
                            cantsalh=cantidad-cantsalh;
                            PEPS(cpsalh,totalhojassalh,cantsalh);
                        }
                    }
        }catch(Exception e){
        }
    }
    /*public void PEPS2(String clave,double total_restar,double cantidad_restar){
        String fecpe="";
        Double total=0.0,cantidad=0.0;
        String ID="";
        try{
            ResultSet PE = mimodelo.buscarPrimeraEntrada2(clave,"2014-10-29");
            while(PE.next()){
                fecpe=PE.getString("fecha_entrada");
                if(fecpe==null){
                    //mensaje(2,"no hay entradas de este papel");
                    System.out.println("no hay entradas");
                    
                }else{
                    total=Double.parseDouble(PE.getString("total"));
                    cantidad=Double.parseDouble(PE.getString("cantidad"));
                    ID=PE.getString("id");
                }
            }
        }catch(Exception e){
        }
        if(cantidad_restar==0.0){
            if(total_restar==0.0){
                System.out.println("no hay que restar");
            }
            if(total_restar<total){
                mimodelo.nuevacantidadtemporal(ID,cantidad,total-total_restar);
            }
            if(total_restar>total){
                mimodelo.nuevacantidadtemporal(ID,cantidad,total-total);
                PEPS2(clave,total_restar-total,0.0);
            }
            if(total_restar==total){
                mimodelo.nuevacantidadtemporal(ID,cantidad,total_restar-total_restar);
                
            }
        }
        if(cantidad_restar<cantidad){
            if(total_restar==0.0){
                mimodelo.nuevacantidadtemporal(ID,cantidad-cantidad_restar,total);
                
            }
            if(total_restar<total){
                mimodelo.nuevacantidadtemporal(ID,cantidad-cantidad_restar,total-total_restar);
                
            }
            if(total_restar>total){
                mimodelo.nuevacantidadtemporal(ID,cantidad-cantidad_restar,total-total);
                PEPS2(clave,total_restar-total,0.0);
            }
            if(total_restar==total){
                mimodelo.nuevacantidadtemporal(ID,cantidad-cantidad_restar,total_restar-total_restar);
                
            }
        }
        if(cantidad_restar>cantidad){
            if(total_restar==0.0){
               mimodelo.nuevacantidadtemporal(ID,cantidad-cantidad,total);
               PEPS2(clave,0.0,cantidad_restar-cantidad);
            }
            if(total_restar<total){
               mimodelo.nuevacantidadtemporal(ID,cantidad-cantidad,total-total_restar);
               PEPS2(clave,0.0,cantidad_restar-cantidad);
            }
            if(total_restar>total){
                mimodelo.nuevacantidadtemporal(ID,cantidad-cantidad,total-total);
                PEPS2(clave,total_restar-total,cantidad_restar-cantidad);
            }
            if(total_restar==total){
                mimodelo.nuevacantidadtemporal(ID,cantidad-cantidad,total_restar-total_restar);
                PEPS2(clave,0.0,cantidad_restar-cantidad);
            }
        }
        if(cantidad_restar==cantidad){
            if(total_restar==0.0){
               mimodelo.nuevacantidadtemporal(ID,cantidad_restar-cantidad_restar,total);
               
            }
            if(total_restar<total){
               mimodelo.nuevacantidadtemporal(ID,cantidad_restar-cantidad_restar,total-total_restar);
               
            }
            if(total_restar>total){
                mimodelo.nuevacantidadtemporal(ID,cantidad_restar-cantidad_restar,total-total);
                PEPS2(clave,total_restar-total,0.0);
            }
            if(total_restar==total){
                mimodelo.nuevacantidadtemporal(ID,cantidad_restar-cantidad_restar,total_restar-total_restar);
                
            }
        }
        
    }*/
    
    public boolean PEPS2(String clave,double total_restar,double cantidad_restar){
        String fecpe="";
        Double total=0.0,cantidad=0.0;
        String ID="";
        try{
            ResultSet PE = mimodelo.buscarPrimeraEntrada2(clave,"2014-10-29");
            while(PE.next()){
                fecpe=PE.getString("fecha_entrada");
                if(fecpe==null){
                    //mensaje(2,"no hay entradas de este papel");
                    return false;
                }else{
                    total=Double.parseDouble(PE.getString("total"));
                    cantidad=Double.parseDouble(PE.getString("cantidad"));
                    ID=PE.getString("id");
                }
            }
        }catch(Exception e){
        }
        if(cantidad_restar==0.0){
            if(total_restar==0.0){
                return true;
            }
            if(total_restar<total){
                mimodelo.nuevacantidadtemporal(ID,cantidad,total-total_restar);
                return true;
            }
            if(total_restar>total){
                mimodelo.nuevacantidadtemporal(ID,cantidad,total-total);
                PEPS2(clave,total_restar-total,0.0);
            }
            if(total_restar==total){
                mimodelo.nuevacantidadtemporal(ID,cantidad,total_restar-total_restar);
                return true;
            }
        }
        if(cantidad_restar<cantidad){
            if(total_restar==0.0){
                mimodelo.nuevacantidadtemporal(ID,cantidad-cantidad_restar,total);
                return true;
            }
            if(total_restar<total){
                mimodelo.nuevacantidadtemporal(ID,cantidad-cantidad_restar,total-total_restar);
                return true;
            }
            if(total_restar>total){
                mimodelo.nuevacantidadtemporal(ID,cantidad-cantidad_restar,total-total);
                PEPS2(clave,total_restar-total,0.0);
            }
            if(total_restar==total){
                mimodelo.nuevacantidadtemporal(ID,cantidad-cantidad_restar,total_restar-total_restar);
                return true;
            }
        }
        if(cantidad_restar>cantidad){
            if(total_restar==0.0){
               mimodelo.nuevacantidadtemporal(ID,cantidad-cantidad,total);
               PEPS2(clave,0.0,cantidad_restar-cantidad);
            }
            if(total_restar<total){
               mimodelo.nuevacantidadtemporal(ID,cantidad-cantidad,total-total_restar);
               PEPS2(clave,0.0,cantidad_restar-cantidad);
            }
            if(total_restar>total){
                mimodelo.nuevacantidadtemporal(ID,cantidad-cantidad,total-total);
                PEPS2(clave,total_restar-total,cantidad_restar-cantidad);
            }
            if(total_restar==total){
                mimodelo.nuevacantidadtemporal(ID,cantidad-cantidad,total_restar-total_restar);
                PEPS2(clave,0.0,cantidad_restar-cantidad);
            }
        }
        if(cantidad_restar==cantidad){
            if(total_restar==0.0){
               mimodelo.nuevacantidadtemporal(ID,cantidad_restar-cantidad_restar,total);
               return true;
            }
            if(total_restar<total){
               mimodelo.nuevacantidadtemporal(ID,cantidad_restar-cantidad_restar,total-total_restar);
               return true;
            }
            if(total_restar>total){
                mimodelo.nuevacantidadtemporal(ID,cantidad_restar-cantidad_restar,total-total);
                PEPS2(clave,total_restar-total,0.0);
            }
            if(total_restar==total){
                mimodelo.nuevacantidadtemporal(ID,cantidad_restar-cantidad_restar,total_restar-total_restar);
                return true;
            }
        }
        return false;
    }
}
