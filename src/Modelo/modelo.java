/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;



/**__ACEPTARSALIDAH
 *
 * @author Favorito
 */
public class modelo extends database {
    
    public modelo(){} //constructor de la clase.
    
    public boolean conexionbd() throws SQLException {
        
            return this.getConexion().isClosed();
        
            //JOptionPane.showMessageDialog(null, "Conexion a la Base de Datos Perdida");
        
    }
    
    public ResultSet buscarUser1(String nombre) throws java.sql.SQLException{       
        String q = "SELECT pswd,id_responsable,cargo_id_cargo,nombre,usuario,Activo,Sesion FROM responsable where usuario='"+nombre+"'" ;
        String pswd="";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                

                return null;
            }
    }
    
    
    public ResultSet buscarUser(String nombre) throws java.sql.SQLException{       
        String q = "SELECT pswd,id_responsable,cargo_id_cargo,nombre,usuario,Activo,_cntr FROM responsable where usuario='"+nombre+"' and usuario <>'root'" ;
        String pswd="";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarMarca(String parametro, boolean like) throws java.sql.SQLException{   
        String q = like==true?"SELECT id_marca as id, marca FROM `marca` where marca  like '%"+parametro+"%'":"SELECT id_marca as id, marca FROM `marca` where marca='"+parametro+"'";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarMarca() throws java.sql.SQLException{   
        String q = "SELECT id_marca as id, marca FROM `marca` ORDER BY MARCA";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarMaquinas(String parametro, boolean like) {   
        String q  = like==true? "SELECT id_maquina as id , nombre as descripcion  FROM maquinas where nombre like '%"+parametro+"%'" :"SELECT id_maquina as id , nombre FROM maquinas where nombre = '"+parametro+"'";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    
    public String buscarFolioMaxSalidaH() throws java.sql.SQLException{   
        Calendar Cal= Calendar.getInstance();
        int anio=Integer.parseInt(Cal.get(Cal.YEAR)+"");
        try {
            String id = "SELECT folio from salidah order by id_salida desc limit 1";
            PreparedStatement pstm = this.getConexion().prepareStatement(id);
            ResultSet res = pstm.executeQuery();
            if(!res.next()){
                return "SALHOJ"+anio+"-1";
            }
            res.beforeFirst();
            while(res.next()){
                String folio = res.getString("folio");
                if(folio==null||folio.isEmpty()){
                    folio= "SALHOJ"+anio+"-1";
                }else{
                    String aniofolio = folio.substring(6);
                    String aniof[]= aniofolio.split("-");
                    if(Integer.parseInt(aniof[0])<anio){
                        int fol = Integer.parseInt(folio.replace("SALHOJ"+aniof[0]+"-", ""));
                        int ano = Integer.parseInt(folio.substring(6, 10));
                        if(anio>ano) fol=1; else fol++;
                        folio= "SALHOJ"+anio+"-"+fol; 
                    }else 
                    if(Integer.parseInt(aniof[0])==anio){
                        int fol = Integer.parseInt(folio.replace("SALHOJ"+aniof[0]+"-", ""));
                        int ano = Integer.parseInt(folio.substring(6, 10));
                        if(anio>ano) fol=1; else fol++;
                        folio= "SALHOJ"+anio+"-"+fol; 
                    }   
                }               
                return folio;
            }
            }catch(SQLException e){
                if(e.getMessage().equals("Illegal operation on empty result set.")){
                    return "SALHOJ"+anio+"-1";
                }else{
                    System.err.println( e.getMessage());
                    return null;
                }
            }
        return null;
    }
    
    
   public String buscarFolioEntrada() throws java.sql.SQLException{
        Calendar Cal= Calendar.getInstance();
        int anio=Integer.parseInt(Cal.get(Cal.YEAR)+"");
        System.out.println(anio);
        try {
            String id = "SELECT folio from entrada order by id_entrada desc limit 1 ";
            PreparedStatement pstm = this.getConexion().prepareStatement(id);
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
                        int ano = Integer.parseInt(folio.substring(3, 7));
                        if(anio>ano) fol=1; else fol++;
                        folio= "ENT"+anio+"-"+fol; 
                    }else
                    if(Integer.parseInt(aniof[0])==anio){
                        int fol = Integer.parseInt(folio.replace("ENT"+aniof[0]+"-", ""));
                        int ano = Integer.parseInt(folio.substring(3, 7));
                        if(anio>ano) fol=1; else fol++;
                        folio= "ENT"+anio+"-"+fol; 
                    }
                }             
                return folio;
           }
        }catch(SQLException e){
            if(e.getMessage().equals("Illegal operation on empty result set.")){
                return "ENT"+anio+"-1";
            }else{
                return null;
            }
        }
        return null;
    }
    
    public String buscarFolioSalida() throws java.sql.SQLException{
        Calendar Cal= Calendar.getInstance();
        int anio=Integer.parseInt(Cal.get(Cal.YEAR)+"");
        try {
            String id = "SELECT folio from salida order by id_salida desc limit 1  ";
            PreparedStatement pstm = this.getConexion().prepareStatement(id);
            ResultSet res = pstm.executeQuery();
            if(!res.next()){
                return "SAL"+anio+"-1";
            }
                res.beforeFirst();
                while(res.next()){
                String folio = res.getString("folio");
                System.err.println(folio);
                if(folio==null||folio.isEmpty()){
                    folio= "SAL"+anio+"-1";
                }else{
                    String aniofolio = folio.substring(3);
                    String aniof[] = aniofolio.split("-");
                    if(Integer.parseInt(aniof[0])<anio){
                        int fol = Integer.parseInt(folio.replace("SAL"+aniof[0]+"-", ""));
                        System.err.println(fol);
                        int ano = Integer.parseInt(folio.substring(3, 7));
                        if(anio>ano) fol=1; else fol++;
                        folio= "SAL"+anio+"-"+fol;
                    }else
                     if(Integer.parseInt(aniof[0])==anio){
                        int fol = Integer.parseInt(folio.replace("SAL"+aniof[0]+"-", ""));
                        System.err.println(fol);
                        int ano = Integer.parseInt(folio.substring(3, 7));
                        if(anio>ano) fol=1; else fol++;
                        folio= "SAL"+anio+"-"+fol;
                    }                   
                     
                }               
                return folio;
            
               }
            }catch(SQLException e){
                if(e.getMessage().equals("Illegal operation on empty result set.")){
                    return "SAL"+anio+"-1";
                }else{
                    //return "ENT"+anio+"-1";
                    return null;
                }
            }
        return null;
    }
    
    public String buscarFolioMaxSalidaBobina() throws java.sql.SQLException{  
        System.out.println("AAAAAAAAAAAAAAMODELO");
        Calendar Cal= Calendar.getInstance();
        int anio=Integer.parseInt(Cal.get(Cal.YEAR)+"");
        try {
            String id = "SELECT folio from salidab order by id_salida desc limit 1;";
            PreparedStatement pstm = this.getConexion().prepareStatement(id);
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
    public String buscarFolioMaxTraspaso() throws java.sql.SQLException{   
        Calendar Cal= Calendar.getInstance();
        int anio=Integer.parseInt(Cal.get(Cal.YEAR)+"");
        try {
            String id = "SELECT folio from traspaso order by id_traspaso desc limit 1;";
            PreparedStatement pstm = this.getConexion().prepareStatement(id);
            ResultSet res = pstm.executeQuery();
            if(!res.next()){    
                return "TRAS"+anio+"-1";
            }
            res.beforeFirst();
            while(res.next()){
                String folio = res.getString("folio");
                if(folio==null){
                    return "TRAS"+anio+"-1";
                }else{
                    String aniofolio = folio.substring(4);
                    String aniof[]= aniofolio.split("-");
                    if(Integer.parseInt(aniof[0])<anio){
                        int fol = Integer.parseInt(folio.replace("TRAS"+aniof[0]+"-", ""));
                        int ano = Integer.parseInt(folio.substring(4, 8));
                        if(anio==ano) fol++; else fol=1;
                        folio = "TRAS"+anio+"-"+fol; 
                    }else
                    if(Integer.parseInt(aniof[0])==anio){
                        int fol = Integer.parseInt(folio.replace("TRAS"+aniof[0]+"-", ""));
                        int ano = Integer.parseInt(folio.substring(4, 8));
                        if(anio==ano) fol++; else fol=1;
                        folio = "TRAS"+anio+"-"+fol; 
                    }
                }
                return folio;
            }
            }catch(Exception e){
                if(e.getMessage().equals("Illegal operation on empty result set.")){
                    return "TRAS"+anio+"-1";
                }else{
                    System.err.println( e.getMessage());
                    
                    return null;
                }
            }
        return null;
    }
    
    public ResultSet buscarMaquina(int id) throws java.sql.SQLException{   
        String q = "SELECT nombre as descripcion FROM `maquinas` where id_maquina="+id+";";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarMaquinaBobina() throws java.sql.SQLException{   
        String q = "SELECT nombre FROM `maquinas` where tipo_maquina=2 order by id_maquina asc;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarClasePapel() throws java.sql.SQLException{       
        String q = "SELECT  clase FROM  `clase_papel` order by id_clase_papel asc ;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarNombrePapel() throws java.sql.SQLException{       
        String q = "SELECT NOMBRE fROM  `nombre_papel` order by nombre ;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarNombrePapelHoja(int index) throws java.sql.SQLException{       
        String q = "SELECT nombre FROM  `nombre_papel` WHERE id_clase_papel ="+index+" and id_tipo_papel<>2 ;";
        //select nombre from nombre_papel where id_clase_papel = 1 and id_tipo_papel <>0
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    
    public boolean nuevoTipoPapel(String TipoPapel){
        String q ="INSERT INTO clase_papel (Clase) values ('"+TipoPapel+"')";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                
            }
        return false;
    }
    public boolean nuevoNombrePapel(String nombre,int id_clase_papel, int tipo_papel){
        String q ="INSERT INTO clase_papel (nombre,id_clase_papel) values ('"+nombre+"',"+id_clase_papel+","+tipo_papel+")";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                
            }
        return false;
    }
    public boolean Traspaso(String folio,String claveo,String claved, String kg_hojas,String bob_pq, String fecha,String costo){
        
        //INSERT INTO `dis_paper`.`traspaso`  VALUES ('TRAS2014-1', '32132132', '3213132', '654', '654', '65454', '15.326');
        String q ="INSERT INTO traspaso "
                                + "VALUES (null,'"+folio+"','"+claveo+"','"+claved+"','"+kg_hojas+"','"+bob_pq+"','"+fecha+"','"+costo+"')";
        
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
            }
        return false;
    }
    public boolean nuevomarcapapel(String MARCA) {
        String q ="INSERT INTO marca (marca) values ('"+MARCA+"');";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
            }
        return false;
    }
    
    public boolean nuevopropiedadpapel(String PROPIEDAD) {
        String q ="INSERT INTO propietarios (nombre) values ('"+PROPIEDAD+"');";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
            }
        return false;
    }
    public ResultSet buscarColorPapel() {
        String q = "SELECT color FROM `color` order by color asc; ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                return null;
            }
    }
    public ResultSet buscarPropiedad(String parametro,boolean like) {
        String q = like ==true? "SELECT id_propietarios as id, nombre as descripcion  FROM `propietarios` where nombre like '%"+parametro+"%';": "SELECT id_propietarios as id, nombre FROM `propietarios` where nombre = '"+parametro+"';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                return null;
            }
    }
    public ResultSet buscarPropiedad(int id) {
        String q = "SELECT id_propietarios as id, nombre as descripcion  FROM `propietarios` where id_propietarios='"+id+"';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarPropiedad() {
        String q = "SELECT id_propietarios as id, nombre as descripcion  FROM `propietarios` order by descripcion;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarPropiedadnoIEXSA() {
        String q = "SELECT nombre FROM `propietarios` where nombre <> 'IEXSA';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarCliente(String parametro, boolean like) {
        String q = like==true? "SELECT id_clientes as id,nombre as descripcion  FROM `clientes` where nombre like '%"+parametro+"%'" : "SELECT id_clientes as id,nombre FROM `clientes` where nombre ='"+parametro+"'";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarCliente(int id) {
        String q = "SELECT id_clientes as id,nombre as descripcion  FROM `clientes` where id_clientes = '"+id+"'" ;
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public boolean newColor(String nuevocolor) {
        String q = "INSERT INTO color VALUES (null, '"+nuevocolor+"');";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
   
    public String altaPapel(String clave, int idmarca, int idnombre,int idcolor,int cara,int idTipoPapel,int clasePapel,String ancho, String alto, String gramaje){
     String q = "INSERT INTO `dis_paper`.`papel` (`id_papel`, `Clave`, `MARCA_id_marca`, `NOMBRE_PAPEL_id_nombre`, `COLOR_id_color`, `TIPO_PAPEL_id_tipo_papel`, `CLASE_PAPEL_id_clase_papel`, `caras`, `ancho`, `alto`, `grams`)"
             + "                          VALUES (NULL, '"+clave+"','"+idmarca+"', '"+idnombre+"','"+idcolor+"', '"+idTipoPapel+"', '"+clasePapel+"','"+cara+"', '"+ancho+"', '"+alto+"','"+gramaje+"');";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return "true";
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return e.getMessage();
        }   
    }  
    
    
    
    public int buscaridNombrePapel(String altanombre) {
        String q = "SELECT id_nombre from nombre_papel where nombre='"+altanombre+"';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                res.next();
                int id = res.getInt(1);
                return id;
            }catch(SQLException e){
                return 0;
            }
    }
    public int buscaridcolor(String foraltacolor) {
        String q = "SELECT id_color from color where color='"+foraltacolor+"';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                res.next();
                int id = res.getInt(1);
                return id;
            }catch(SQLException e){
                
                return 0;
            }
    }
    
    public ResultSet buscaTipoEntrada(String parametro,boolean like) {
        String q = like ==true? "SELECT id_tipo_en as id, tipo_entrada as descripcion FROM TIPO_ENTRADA where tipo_entrada like '%"+parametro+"%' ":"SELECT id_tipo_en as id, tipo_entrada FROM TIPO_ENTRADA where tipo_entrada = '"+parametro+"' ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscaTipoEntrada() {
        String q = "SELECT id_tipo_en as id, tipo_entrada as descripcion FROM TIPO_ENTRADA  ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscaTipoEntrada(int id) {
        String q = "SELECT id_tipo_en as id, tipo_entrada as descripcion FROM TIPO_ENTRADA where id_tipo_en='"+id+"' ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscaTipoSalida(String parametro, boolean like) {
        
        String q = like==true ?"SELECT id_tipo_sal as id, tipo_salida as descripcion from tipo_salida where tipo_salida like '%"+parametro+"%';":"SELECT id_tipo_sal as id, tipo_salida from tipo_salida where tipo_salida ='"+parametro+"';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscaTipoSalida() {
        String q = "SELECT id_tipo_sal as id, tipo_salida as descripcion from tipo_salida ;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscaTipoSalida(int id) {
        String q = "SELECT id_tipo_sal as id, tipo_salida as descripcion from tipo_salida where id_tipo_sal="+id+" ;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    
    
     public ResultSet buscaProveedor(String parametro,boolean like) {
        String q = like==true? "SELECT id_provedores as id,nombre as descripcion  from proveedores where nombre like '%"+parametro+"%'":"SELECT id_provedores as id,nombre from proveedores where nombre= '"+parametro+"'";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
     public ResultSet buscaProveedor(int id) {
        String q ="SELECT id_provedores as id,nombre as descripcion  from proveedores where id_provedores='"+id+"'";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
     public ResultSet buscaProveedor() {
        String q ="SELECT id_provedores as id,nombre as descripcion  from proveedores ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }

     public ResultSet buscaClavePapelExist(String parametro,int propietario) {
        String q = "select clavePapel from inventario where id_propietario="+propietario+" and clavePapel like '%"+parametro+"%' and cantidad >0 and presentacion >0";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
     public ResultSet buscaClavePapel(String parametro,int propietario) {
        String q = "select clavePapel from inventario where id_propietario="+propietario+";";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
     
     public ResultSet buscaClave(String parametro) {
        String q = "select clavePapel from inventario where clavePapel like '%"+parametro+"%' ;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
     
     public ResultSet buscaClavea(String parametro) {
        String q = "select clavePapel from inventario where clavePapel = '"+parametro+"' ;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
     
     
     public ResultSet buscaClaveHoja(String parametro,int propietario) {
        String q = "select clavePapel from inventario where id_propietario="+propietario+" and clavePapel like '02-%"+parametro+"%' and (cantidad>0 or presentacion>0) ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
     public ResultSet buscaClaveBobina(String parametro,int propietario) {
        String q = "select clavePapel from inventario where id_propietario="+propietario+" and clavePapel like '01-%"+parametro+"%' and (cantidad >0 or presentacion >0)";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
     public ResultSet buscaClaveBobina(String parametro,String propietario) {
        String q = "select clavePapel from inventario where clavePapel like '%"+propietario+"%' and clavePapel like '%01-%' and clavePapel like '%"+parametro+"%'";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
     
     public ResultSet buscaDocumentoEntrada(String eval) {
        String q = "SELECT * FROM documento_entrada where documento='"+eval+"'";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
     
     public boolean altaDocEntrada(String eval){
         String q1 = "INSERT INTO `dis_paper`.`documento_entrada` (`Documento`) VALUES ('"+eval+"');";
         try{
             PreparedStatement pstm1 = this.getConexion().prepareStatement(q1);
             pstm1.execute();
             pstm1.close();
             return true;
         }
         catch(SQLException e){
             return false;
         }
     }
     
     
     public boolean altaEntrada(String folioentrada,String t1, String t2, String t3,String ordenProduccion,String ordenCompra,String documentoEntrada,int propietario,int proveedor, int responsable,String fecha,int tipoentrada,String Observaciones,int cliente){
     String q = "INSERT INTO  `dis_paper`.`entrada` (`id_entrada`,`folio` ,`TURNO1` ,`TURNO2` ,`TURNO3` ,`orden_produccion` ,`orden_compra` ,`documento_entrada` ,`id_propietario` ,`id_proveedores` ,`id_responsable` ,`Fecha` ,`tipo_entrada`,`observaciones`,`cliente`)"
             //                           VALUES (NULL, 'SADFADF'  ,'1'          , '1'           , '1'      , '1'          , '1'              , '1'             , '1'        , '1'       , 'SD');
             + "                          VALUES (NULL, '"+folioentrada+"','"+t1+"','"+t2+"', '"+t3+"','"+ordenProduccion+"', '"+ordenCompra+"', '"+documentoEntrada+"', '"+propietario+"', '"+proveedor+"', '"+responsable+"', '"+fecha+"', '"+tipoentrada+"', '"+Observaciones+"', '"+cliente+"')";
     
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            
            return false;
        }   
    } 
     
     public boolean altaSalida(String foliosalida,String t1, String t2, String t3,String ordenProduccion,String ordenCompra,String documentoSalida,int propietario,int proveedor, int responsable,String fecha,int tiposalida,String Observaciones,int cliente){
     String q = "INSERT INTO  `dis_paper`.`salida` (`id_salida`,`folio` ,`TURNO1` ,`TURNO2` ,`TURNO3` ,`orden_produccion` ,`orden_compra` ,`documento_salida` ,`id_propietario` ,`id_proveedores` ,`id_responsable` ,`Fecha` ,`tipo_salida`,`observaciones`,`cliente`)"
             //                           VALUES (NULL, 'SADFADF'  ,'1'          , '1'           , '1'      , '1'          , '1'              , '1'             , '1'        , '1'       , 'SD');
             + "                          VALUES (NULL, '"+foliosalida+"','"+t1+"','"+t2+"', '"+t3+"','"+ordenProduccion+"', '"+ordenCompra+"', '"+documentoSalida+"', '"+propietario+"', '"+proveedor+"', '"+responsable+"', '"+fecha+"', '"+tiposalida+"', '"+Observaciones+"', '"+cliente+"')";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage()+ "ALTA SALIDA");
            return false;
        }   
    } 
     
     public boolean altaDevolucion(String t1, String t2, String t3,String ordenProduccion,String ordenCompra,String documentoEntrada,int propietario,int proveedor, int responsable,String fecha,int tipoentrada,String Observaciones){
     String q = "INSERT INTO  `dis_paper`.`Devolucion` (`id` ,`TURNO1` ,`TURNO2` ,`TURNO3` ,`orden_produccion` ,`orden_compra` ,`documento_entrada` ,`id_propietario` ,`id_proveedores` ,`id_responsable` ,`Fecha` ,`tipo_entrada`,`observaciones`)"
             //                           VALUES (NULL, 'SADFADF'  ,'1'          , '1'           , '1'      , '1'          , '1'              , '1'             , '1'        , '1'       , 'SD');
             + "                          VALUES (NULL, '"+t1+"','"+t2+"', '"+t3+"','"+ordenProduccion+"', '"+ordenCompra+"', '"+documentoEntrada+"', '"+propietario+"', '"+proveedor+"', '"+responsable+"', '"+fecha+"', '"+tipoentrada+"', '"+Observaciones+"')";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            return false;
        }   
    }
    

    public ResultSet bucarMaxEntrada() {
        String q = "SELECT MAX( id_entrada ) FROM entrada";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    
    public ResultSet bucarMaxSalida() {
        String q = "SELECT MAX( id_salida ) FROM salida";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    
    
    public ResultSet bucarMaxDevolucion() {
        String q = "SELECT MAX( id ) FROM devolucion";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet bucarMaxSalidah() {
        String q = "SELECT MAX( id_salida ) FROM salidah";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    
    public ResultSet bucarMaxSalidab() {
        String q = "SELECT MAX( id_salida ) FROM salidab";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }

    public boolean altaDetalleEntrada(int id_entrada, String clavePapel, String totalentrada,String cantidadentrada, String ubicacion,String costo, String totalcosto) {
        String q = " INSERT INTO  `dis_paper`.`detalleentrada`"
                + "     (`id_entrada` ,`Clave_Papel` ,`total_entrada` ,`cantidad_entrada`,  `ubicacion`,`Costo` ,`total_costo`,`total_temporal` ,`cantidad_temoporal`)"
             + "VALUES ('"+id_entrada+"','"+clavePapel+"','"+totalentrada+"','"+cantidadentrada+"','"+ubicacion+"','"+costo+"','"+totalcosto+"','"+totalentrada+"','"+cantidadentrada+"');";                         
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            return false;
        }
    }
    
    public boolean altaDetalleSalida(int id_salida, String clavePapel, String totalsalida,String cantidadsalida, String costo, String totalcosto,String identradas_) {
        String q = " INSERT INTO  `dis_paper`.`detallesalida`"
                + "     (`idsalida`  ,`ClavePapel` ,`total_salida` ,`cantidad_salida`,`Costo` ,`totalcosto`,`entradas`)"
             + "VALUES ('"+id_salida+"','"+clavePapel+"','"+totalsalida+"','"+cantidadsalida+"','"+costo+"','"+totalcosto+"','"+identradas_+"');";                         
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage()+" ALTA DETALLE SALIDA");
            return false;
        }
    }
    
    public boolean modifDetalleEntrada(int id_entrada, String clavePapel, String totalentrada,String cantidadentrada, String ubicacion,String costo, String totalcosto) {
       // String q = " INSERT INTO  `dis_paper`.`detalleentrada`"
       //         + "     (`id_entrada` ,`Clave_Papel` ,`total_entrada` ,`cantidad_entrada`,  `ubicacion`,`Costo` ,`total_costo`)"
       //      + "VALUES ('"+id_entrada+"','"+clavePapel+"','"+totalentrada+"','"+cantidadentrada+"','"+ubicacion+"','"+costo+"','"+totalcosto+"');"; 
        String q="UPDATE detalleentrada set Clave_Papel='"+clavePapel+"',"
                                        +"total_entrada='"+totalentrada+"',"
                                        +"cantidad_entrada='"+cantidadentrada+"',"
                                        +"ubicacion='"+ubicacion+"', "
                                        +"costo='"+costo+"', "
                +"total_temporal='"+totalentrada+"', "
                +"cantidad_temoporal='"+cantidadentrada+"', "
                                        +"total_costo='"+totalcosto+"' where id_detalleentrada='"+id_entrada+"';";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            System.err.println(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            
            return false;
        }
    }
    
    public boolean modifDetalleSalida(int id_salida, String clavePapel, String totalsalida,String cantidadsalida, String costo, String totalcosto,String identradas_) {
       // String q = " INSERT INTO  `dis_paper`.`detalleentrada`"
       //         + "     (`id_entrada` ,`Clave_Papel` ,`total_entrada` ,`cantidad_entrada`,  `ubicacion`,`Costo` ,`total_costo`)"
       //      + "VALUES ('"+id_entrada+"','"+clavePapel+"','"+totalentrada+"','"+cantidadentrada+"','"+ubicacion+"','"+costo+"','"+totalcosto+"');"; 
        String q="UPDATE detallesalida set ClavePapel='"+clavePapel+"',"
                                        +"total_salida='"+totalsalida+"',"
                                        +"cantidad_salida='"+cantidadsalida+"',"
                                        +"costo='"+costo+"', "
                                        +"totalcosto='"+totalcosto+"', entradas='"+identradas_+"' where iddetallesalida='"+id_salida+"';";
        System.out.println(q);
        
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            
            return false;
        }
    }

    public boolean altaDetalleSalidah(int id_salidah, String clave_papel, int kghoj, int bobpaq, String costo,String totalcosto,String identradas_,String epsah) {
        String q = "INSERT INTO  `dis_paper`.`detallesalidah` (`id_salida` ,`Clave_Papel` ,`total_hojas`,`cantidad` ,`costo`,`totalcosto`,`identradas`,estandar,contenido,resto)" 
             +                                       "VALUES ('"+id_salidah+"','"+clave_papel+"','"+kghoj+"','"+bobpaq+"','"+costo+"','"+totalcosto+"','"+identradas_+"','"+epsah+"',0,0);";                         
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage()+"altadeta");
            return false;
        }
    }
    public boolean modifDetalleSalidah(int id_salidah, String clave_papel, int kghoj,int bobpaq,String costo,String totalcosto,String entradas,String epsalh) {
        //String q = "INSERT INTO  `dis_paper`.`detallesalidah` (`id_salida` ,`Clave_Papel` ,`total_hojas` ,`contenido` ,`cantidad` ,`resto`)" 
        //     + "VALUES ('"+id_salidah+"','"+clave_papel+"','"+total_hojas+"','"+contenido+"','"+cantidad+"','"+resto+"');";                         
        String q="UPdATE detallesalidah SET clave_papel='"+clave_papel+"',"
                + " total_hojas='"+kghoj+"', "
                + " cantidad='"+bobpaq+"', "
                + " costo='"+costo+"',"
                + " totalcosto='"+totalcosto+"', identradas='"+entradas+"', estandar='"+epsalh+"' where id_salida='"+id_salidah+"';";
        
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage()+"altadeta");
            return false;
        }
    }
    public boolean altaDetalleSalidab(int id_salidab,String clave_papel,String totksini,String totbobini,String totkgssur,String totbobsur,String totkgsdev,String totbobdev,String totalsurtido,String desperdicio,String empaque,String capa,String cono,String totales,String totalpliegos,String despliegos,String empqplie,String capaplie,String conoplie,String totalesplie,String costo, String totalcosto,String totalkil,String identradas_) {
        String q = "INSERT INTO  `dis_paper`.`detallesalidab` (`id_salida` ,`Clave_Papel` ,`totkgsini` ,`totbobini` ,`totkgssur` ,`totbobsur` ,`totkgsdev` ,`totbobdev` ,`totalsurtido` ,`desperdicio` ,`empaque` ,`capa` ,`cono` ,`totales` ,`totalpliegos` ,`despliegos` ,`empqplie` ,`capaplie` ,`conoplie` ,`totalesplie`,`costo`,`totalcosto`,`estandar`,`entradas`)"
        +"VALUES ('"+id_salidab+"',  '"+clave_papel+"',  '"+totksini+"',  '"+totbobini+"',  '"+totkgssur+"',  '"+totbobsur+"',  '"+totkgsdev+"',  '"+totbobdev+"',  '"+totalsurtido+"',  '"+desperdicio+"',  '"+empaque+"',  '"+capa+"',  '"+cono+"',  '"+totales+"',  '"+totalpliegos+"',  '"+despliegos+"',  '"+empqplie+"',  '"+capaplie+"',  '"+conoplie+"',  '"+totalesplie+"',  '"+costo+"',  '"+totalcosto+"',  '"+totalkil+"',  '"+identradas_+"');";                   
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage()+"altadeta");
            return false;
        }
    }
    public boolean modifdetalleSalidab(String id_salidab,String clave_papel,String totksini,String totbobini,String totkgssur,String totbobsur,String totkgsdev,String totbobdev,String totalsurtido,String desperdicio,String empaque,String capa,String cono,String totales,String totalpliegos,String despliegos,String empqplie,String capaplie,String conoplie,String totalesplie,String costo,
            String totalcosto,String identradas_,String estandar) {
        
       // String q = "INSERT INTO  `dis_paper`.`detallesalidab` (`id_salida` ,`Clave_Papel` ,`totkgsini` ,`totbobini` ,`totkgssur` ,`totbobsur` ,`totkgsdev` ,`totbobdev` ,`totalsurtido` ,`desperdicio` ,`empaque` ,`capa` ,`cono` ,`totales` ,`totalpliegos` ,`despliegos` ,`empqplie` ,`capaplie` ,`conoplie` ,`totalesplie`)"
        //+"VALUES ('"+id_salidab+"',  '"+clave_papel+"',  '"+totksini+"',  '"+totbobini+"',  '"+totkgssur+"',  '"+totbobsur+"',  '"+totkgsdev+"',  '"+totbobdev+"',  '"+totalsurtido+"',  '"+desperdicio+"',  '"+empaque+"',  '"+capa+"',  '"+cono+"',  '"+totales+"',  '"+totalpliegos+"',  '"+despliegos+"',  '"+empqplie+"',  '"+capaplie+"',  '"+conoplie+"',  '"+totalesplie+"');";
         String q="UPDATE `dis_paper`.`detallesalidab` SET `clave_papel`='"+clave_papel+"',`totkgsini`='"+totksini+"', `totbobini`='"+totbobini+"', `totkgssur`='"+totkgssur+"', `totbobsur`='"+totbobsur+"', `totkgsdev`='"+totkgsdev+"', `totbobdev`='"+totbobdev+"', `totalsurtido`='"+totalsurtido+"', `desperdicio`='"+desperdicio+"', `empaque`='"+empaque+"', `capa`='"+capa+"', `cono`='"+cono+"', `totales`='"+totales+"', `totalpliegos`='"+totalpliegos+"', `despliegos`='"+despliegos+"', `empqplie`='"+empqplie+"', `capaplie`='"+capaplie+"', `conoplie`='"+conoplie+"', `totalesplie`='"+totalesplie+"', `costo`='"+costo+"', `totalcosto`='"+totalcosto+"', `entradas`='"+identradas_+"' , `estandar`='"+estandar+"'  WHERE `idd_salida`='"+id_salidab+"';";                      
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage()+"altadeta");
            return false;
        }
    }
    
    public boolean modifsalidabtiro(String merma,String fechaini,String hraini,String fechafin,String hrafin,String estandarprod,String totalespliegos,String controtativas,String tiempoReal,String folio){
        String q="UPDATE `dis_paper`.`salidab` SET `merma`='"+merma+"', "
        + "`fecha_inicial`='"+fechaini+"', `hora_inicial`='"+hraini+"', `fecha_final`='"+fechafin+"', `hora_final`='"+hrafin+"',"
                + " `tiempo_real`='"+tiempoReal+"', `total_pliego`='"+totalespliegos+"',estandar_produccion='"+estandarprod+"', `contador_rotativas`='"+controtativas+"' "
                + "WHERE `folio`='"+folio+"';";
        try{
            System.out.println(q);
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage()+"altadeta");
            return false;
        }
    }

    public ResultSet buscarExistenciaPapel(String clavePapel) {
        String q = "SELECT * FROM inventario where clavePapel='"+clavePapel+"';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    
    public ResultSet buscarExistenciaPapelfecha(String clavePapel,String fecha) {
        String q = "call sumaxistencia_fecha('"+clavePapel+"','"+fecha+"');";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }

    public boolean nuevaExistencia(String cantidad,String Clave,String presentacion) {
        String q=" UPDATE  `dis_paper`.`inventario` SET  `cantidad` =  '"+cantidad+"',presentacion='"+presentacion+"' WHERE `inventario`.`clavePapel` =  '"+Clave+"' ;";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage() + "nuevexis");
            return false;
        }
    }
    
    public boolean updateteporalde(int totaltemporal,int cantidadtemporal,String id) {
        String q=" UPDATE  `dis_paper`.`detalleentrada` SET  `cantidad_temoporal` =  '"+cantidadtemporal+"',total_temporal='"+totaltemporal+"' WHERE `id_entrada` =  '"+id+"' ;";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage() + "nuevexis");
            return false;
        }
    }

    public boolean nuevaexistencia(String clave,int propiedad) {
      String q ="INSERT INTO  `dis_paper`.`inventario` (`id_inventario` ,`clavePapel` ,`cantidad`,id_propietario,presentacion)VALUES (NULL ,  '"+clave+"',  '0',"+propiedad+",'0');";
    try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            
            return false;
        }
    }

    public boolean altaSalidah(String folio,String t1, String t2, String t3, String ordenProduccion, String estandar_produccion, int cliente, int propietario, int maquina, String fec, String titulo,int responsable,String Observaciones,int tiposal) {
        String q = "INSERT INTO  `dis_paper`.`salidah` (`id_salida`,`folio`,`TURNO1` ,`TURNO2` ,`TURNO3` ,`orden_produccion` ,`estandar_produccion` ,`id_cliente` ,`id_propietario` ,`id_maquina` ,`Fecha` ,`titulo`,`id_responsable`,`observaciones`,`tipo_salida`)"
             + "                          VALUES (NULL,'"+folio+"', '"+t1+"','"+t2+"', '"+t3+"','"+ordenProduccion+"', '"+estandar_produccion+"', '"+cliente+"', '"+propietario+"', '"+maquina+"', '"+fec+"', '"+titulo+"','"+responsable+"','"+Observaciones+"','"+tiposal+"')";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage()+"altasal");
            return false;
        }   
    }
    
    public boolean altaSalidab(String foliob,String t1,String t2,String t3,String op,String ep,String factor,String pliego,String cliente,String propietario,String  maquina,
                            String fecha,String titulo,String responsable,String observaciones,String totalkgs,String tiroxpliego,String pliegokgs,String pliegoajuste,String ajustekgs,int tiposal,
                            String merma,String fechaini,String hraini,String fechafin,String hrafin,String estandarprod,String totalpliegos,String controtativas,String tiempoReal) {
        
      String q=  "INSERT INTO  `dis_paper`.`salidab` (`id_salida` ,`folio` ,`TURNO1` ,`TURNO2` ,`TURNO3` ,`orden_produccion` ,`estandar_produccion` ,`factor` ,`pliego` ,`id_cliente` "
              + ",`id_propietario` ,`id_maquina` ,`Fecha` ,`titulo` ,`id_responsable` ,`observaciones` ,`totalkg` ,`tiroxpliego` ,`pliegokgs` ,`pliegoajuste` ,`ajustekgs`,`tipo_salida`"
              + ",`tiempo_real`,`total_pliego`,`contador_rotativas`,`merma`,`fecha_inicial`,`hora_inicial`,`fecha_final`,`hora_final`)"
              +"VALUES (NULL ,  '"+foliob+"',  '"+t1+"',  '"+t2+"',  '"+t3+"',  '"+op+"',  '"+ep+"',  '"+factor+"',  '"+pliego+"',  '"+cliente+"',  '"+propietario+"',  '"+maquina+"',"
              +"'"+fecha+"',  '"+titulo+"',  '"+responsable+"',  '"+observaciones+"',  '"+totalkgs+"',  '"+tiroxpliego+"',  '"+pliegokgs+"', '"+pliegokgs+"',  '"+ajustekgs+"','"+tiposal+"'"
              + ",'"+tiempoReal+"','"+totalpliegos+"','"+controtativas+"','"+merma+"','"+fechaini+"','"+hraini+"','"+fechafin+"','"+hrafin+"');";
      System.out.println(q);
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage()+"altasal");
            return false;
        }   
    }
    public boolean cambiocontrasena(String newpswd1,String newpswd2, int id_responsable) {
        String q ="UPDATE  `dis_paper`.`responsable` SET  `pswd` =  '"+newpswd1+"', `_cntr` = '"+newpswd2+"'  WHERE  `responsable`.`id_responsable` ="+id_responsable;
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            
            return false;
        }
    }

    public ResultSet buscarClavePapelcliente(int cliente) {
        String q = "select clavepapel,cantidad from inventario where id_propietario="+cliente+" and cantidad > 0";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                return null;
            }
    }

    public ResultSet buscarExistenciaPapelcliente( String clave) {
        String q = "select cantidad from inventario where  clavePapel like '%"+clave+"%'";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }

    public boolean agregarnuevaexistencia(String clave, int nuevaexistencia,int presentacion) {
        String q ="UPDATE  `dis_paper`.`inventario` SET  `cantidad` =  '"+nuevaexistencia+"', presentacion='"+presentacion+"' WHERE  `inventario`.`clavePapel` =  '"+clave+"'";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            
            return false;
        }
    }

    public ResultSet buscarDatosPapel(String clave) {
        String q = "select * from papel where  clave = '"+clave+"'";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    
    public String buscarnombrePapel(int id){
        String q = "select nombre from nombre_papel where  id_nombre= '"+id+"'";
        String result;
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                res.next();
                result= res.getString("nombre");
                return result;
            }catch(SQLException e){
                
                return null;
            }
    }

    public String buscarcolorPapel(int id) {
        String q = "select Color from color where id_color= '"+id+"'";
        String result;
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                res.next();
                result= res.getString("color");
                return result;
            }catch(SQLException e){
                return null;
            }
    }

    public ResultSet buscarPresentacionPapelCliente(String clavecl) {
        String q = "SELECT presentacion FROM inventario where clavePapel='"+clavecl+"';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }

     public boolean newaltausuario(String nombreus, String usuario, String contraseña, int nivel, int bloqueo) {
        String q = "INSERT INTO  `dis_paper`.`responsable` (`id_responsable` ,`CARGO_id_cargo` ,`nombre` ,`usuario` ,`pswd`,`Activo`,Sesion,_cntr)"
                                                              + "VALUES (NULL,'"+nivel+"','"+nombreus+"','"+usuario+"',MD5('"+contraseña+"'),'"+bloqueo+"','0','"+contraseña+"');";         
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            if(e.getMessage().equals("Duplicate entry '"+usuario+"' for key 'usuario'")){
                JOptionPane.showMessageDialog(null, "Ya existe el usuario " + usuario);
            }else{
                
            }
            return false;
        }
    }

    public boolean bloquearusuario(String bloquearuser) {
        String q ="UPDATE  `dis_paper`.`responsable` SET  `Activo` =  '0' WHERE  `responsable`.`usuario` ='"+bloquearuser+"'";
        //UPDATE  `dis_paper`.`responsable` SET  `Activo` =  '0' WHERE  `responsable`.`id_responsable` =6;
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            
            return false;
        }
    }

    public boolean modificaruser(String nombreus, String usuario, String contraseña, int nivel, int bloqueo,String modificaruser) {
        String q=" UPDATE  `dis_paper`.`responsable` SET  `CARGO_id_cargo` =  '"+nivel+"',`nombre` =  '"+nombreus+"',`usuario` = '"+usuario+"',`pswd` =  MD5('"+contraseña+"'),`Activo` =  '"+bloqueo+"',_cntr='"+contraseña+"' WHERE  `responsable`.`usuario` ='"+modificaruser+"';";                
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            
            return false;
        }
    }

    public ResultSet buscarop(String op) {
        String q = "SELECT * FROM ordenprod op where op.id_op='"+op+"';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    
    public ResultSet buscarop2(String op) {
        String q = "SELECT titulo,cliente FROM vw_infosalidab where id_op='"+op+"';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }

    public boolean altaop(String op, String merma, String fechaini, String hraini, String fechafin, String hrafin,String estandarprod,String tiemporeal, String totalpliego, String controtativas) {
        String q ="INSERT INTO  `dis_paper`.`ordenprod` (`id_op`,`merma` ,`fecha_inicial` ,`hora_inicial` ,`fecha_final` ,`hora_final` ,`estandar_produccion` ,`tiempo_real` ,`total_pliego` ,`contador_rotatvivas`,`clave_papel`,`invkgs`,`invbob`)"
                + "VALUES ('"+op+"',  '"+merma+"',  '"+fechaini+"',  '"+hraini+"',  '"+fechafin+"',  '"+hrafin+"',  '"+estandarprod+"',  '"+tiemporeal+"',  '"+totalpliego+"',  '"+controtativas+"','0','0','0');";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            //
            if(e.getMessage().equals("Duplicate entry '"+op+"' for key 'PRIMARY'")){
                String q2 ="update ordenprod set merma='"+merma+"',fecha_inicial='"+fechaini+"',hora_inicial='"+hraini+"',fecha_inicial='"+fechafin+"',hora_final='"+hrafin+"',estandar_produccion='"+estandarprod+"',tiempo_real='"+tiemporeal+"',total_pliego='"+totalpliego+"',contador_rotatvivas='"+controtativas+"' where id_op='"+op+"'";
                try{
                    PreparedStatement pstm = this.getConexion().prepareStatement(q2);
                    pstm.execute();
                    pstm.close();
                    return true;
                }catch(SQLException a){
                    return false;
                }
            }else
            return false;
        }
        
    }
    public boolean nuevatipoentrada(String tipoEntra) {
        String q = "INSERT INTO  `dis_paper`.`tipo_entrada` (`id_tipo_en` ,`Tipo_Entrada`)VALUES (NULL, '"+tipoEntra+"')";                 
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            if(e.getMessage().equals("Duplicate entry '"+tipoEntra+"' for key 'Tipo_Entrada'")){
                JOptionPane.showMessageDialog(null, "Ya Existe la Entrada: " +tipoEntra);
            }else{
                
            }
            return false;
        }   
    }  

    public boolean newaltaproveedor(String Nombre, String Direccion, String telefono, String RfC) {
         String q = "INSERT INTO `dis_paper`.`proveedores` (`id_provedores`, `Nombre`, `Direccion`, `Telefono`, `RFC`)"
                  + "VALUES (NULL,'"+Nombre+"','"+Direccion+"','"+telefono+"','"+RfC+"')";        
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){            
               if(e.getMessage().equals("Duplicate entry '"+Nombre+"' for key 'Nombre'")){                   
                JOptionPane.showMessageDialog(null, "Ya Existe Este Proveedor: " +Nombre);
            }else{
                
            }            
            return false;
        }
    }

    public boolean newaltacliente(String Nombre, String Direccion, String telefono, String RfC) {
        String q = "INSERT INTO `dis_paper`.`clientes` (`id_clientes`, `Nombre`, `Direccion`, `Telefono`, `RFC`)"
                  +"VALUES (NULL,'"+Nombre+"','"+Direccion+"','"+telefono+"','"+RfC+"')"; 
        //INSERT INTO `dis_paper`.`clientes` (`id_clientes`, `Nombre`, `Direccion`, `Telefono`, `RFC`) VALUES (NULL, 'ola', 'gh', 'gh2', '132');
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){            
               if(e.getMessage().equals("Duplicate entry '"+Nombre+"' for key 'Nombre'")){                   
                JOptionPane.showMessageDialog(null, "Ya Existe Este Cliente: " +Nombre);
            }else{
                
            }            
            return false;
        }
    }

    public boolean nuevamaquinah(String maquina) {
        String q = "INSERT INTO `dis_paper`.`maquinas` (`id_maquina`, `nombre`,TIPO_MAQUINA)"
                  +"VALUES (NULL,'"+maquina+"',0)"; 
        //INSERT INTO `dis_paper`.`maquinas` (`id_maquina`, `nombre`, `tipo_maquina`) VALUES (NULL, 'cosedora', '0');
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){            
               if(e.getMessage().equals("Duplicate entry '"+maquina+"' for key 'nombre'")){                   
                JOptionPane.showMessageDialog(null, "Ya Existe Esta Maquina: " +maquina);
            }else{
                
            }            
            return false;
        }
    }
    public boolean registraracceso(String user, String fech, String horaentrada) {
           String q = "INSERT INTO `dis_paper`.`reporusuario` (`id_ReporUsuario`, `NombreEmpleado`, `Fecha`, `HoraEntrada`, `HoraSalida`)VALUES (NULL,'"+user+"','"+fech+"','"+horaentrada+"',' ')"; 
                     //INSERT INTO `dis_paper`.`reporusuario` (`id_ReporUsuario`, `NombreEmpleado`, `Fecha`, `HoraEntrada`, `HoraSalida`)VALUES (NULL, 'Rodrigo', '2014-05-05', '10:51', '13:00');        
           try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){                                      
                
                       
            return false;
        }
    }
   public boolean horasalida(String horasalida, String user) {
       try{
           String q1 ="SELECT MAX(id_reporusuario) from reporusuario where nombreempleado='"+user+"';";
            PreparedStatement pstm = this.getConexion().prepareStatement(q1);
            ResultSet res = pstm.executeQuery();
            String id="";
            while(res.next()){
                id=res.getString(1);
            }
            String q=" UPDATE  `dis_paper`.`reporusuario` SET  `HoraSalida` =  '"+horasalida+"' WHERE `reporusuario`.`id_reporusuario` =  '"+id+"';";
            PreparedStatement pstm2 = this.getConexion().prepareStatement(q);
            pstm2.execute();
            pstm2.close();
            return true;
        }catch(SQLException e){
            
            return false;
        }
    }

    public boolean agregarinventarioinicial(String opsalB,String tmpclv, int devkgs, int devbob) {
        String q=" UPDATE  `dis_paper`.`ordenprod` SET  `invkgs` =  '"+devkgs+"',`invbob` =  '"+devbob+"',`clave_papel` =  '"+tmpclv+"' WHERE  `ordenprod`.`id_op` =  '"+opsalB+"'";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            
            return false;
        }
    }

    public ResultSet buscaOP(String parametro) {
        String q="SELECT id_op from ordenprod where id_op like '%"+parametro+"%'";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarReporteUserDate(String uusuario,String datee){  
        
        String q = "SELECT nombreempleado as Nombre_de_Empleado, CONCAT( fecha,' - ',horaentrada) AS Fecha_y_Hora_de_Ingreso, CONCAT( fecha,' - ',horasalida) AS Fecha_y_Hora_de_Salida FROM `reporusuario` WHERE nombreempleado='"+uusuario+"' and fecha ='"+datee+"'";
        //          SELECT nombreempleado, CONCAT( fecha,horaentrada) AS ingreso, CONCAT( fecha,horasalida) AS salida FROM `reporusuario` WHERE nombreempleado =  'jhafet' LIMIT 0 , 30        
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(Exception e){
                                
                return null;
                
            }
    }
    public ResultSet buscarReporteUser(String uusuario){  
        
        String q = "SELECT nombreempleado as Nombre_de_Empleado, CONCAT( fecha,' - ',horaentrada) AS Fecha_y_Hora_de_Ingreso, CONCAT( fecha,' - ',horasalida) AS Fecha_y_Hora_de_Salida FROM `reporusuario` WHERE nombreempleado='"+uusuario+"'";
        //          SELECT nombreempleado, CONCAT( fecha,horaentrada) AS ingreso, CONCAT( fecha,horasalida) AS salida FROM `reporusuario` WHERE nombreempleado =  'jhafet' LIMIT 0 , 30
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(Exception e){
                
                return null;
            }
    }
    public ResultSet buscartodoreporte(){  
        
        String q = "SELECT nombreempleado as Nombre_de_Empleado, CONCAT( fecha,' - ',horaentrada) AS Fecha_y_Hora_de_Ingreso, CONCAT( fecha,' - ',horasalida) AS Fecha_y_Hora_de_Salida FROM `reporusuario`;";
        //          SELECT nombreempleado, CONCAT( fecha,horaentrada) AS ingreso, CONCAT( fecha,horasalida) AS salida FROM `reporusuario` WHERE nombreempleado =  'jhafet' LIMIT 0 , 30
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(Exception e){
                
                return null;
            }
    }

    public boolean nuevaDevolucion(String devolucion) {
        String q = "INSERT INTO `dis_paper`.`tipo_devolucion` (`id_tipodevolucion`, `descripcion`) VALUES (NULL, '"+devolucion+"');";
        //INSERT INTO `dis_paper`.`maquinas` (`id_maquina`, `nombre`, `tipo_maquina`) VALUES (NULL, 'cosedora', '0');
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){            
               if(e.getMessage().equals("Duplicate entry '"+devolucion+"' for key 'descripcion'")){                   
                JOptionPane.showMessageDialog(null, "Ya Existe Este tipo de devolucion: " +devolucion);
            }else{
                
            }            
            return false;
        }
    }

    public boolean nuevaTipoSalida(String salida) {
        String q = "INSERT INTO `dis_paper`.`tipo_salida` (`id_tipo_sal`, `tipo_salida`) VALUES (NULL, '"+salida+"');";
        //INSERT INTO `dis_paper`.`maquinas` (`id_maquina`, `nombre`, `tipo_maquina`) VALUES (NULL, 'cosedora', '0');
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){            
               if(e.getMessage().equals("Duplicate entry '"+salida+"' for key 'tipo_salida'")){                   
                JOptionPane.showMessageDialog(null, "Ya Existe Este tipo de devolucion: " +salida);
            }else{
                
            }            
            return false;
        }
    }
    public ResultSet buscarDevolucion() {
        String q = "SELECT  `descripcion` FROM  `tipo_devolucion` ORDER BY  `tipo_devolucion`.`id_tipodevolucion` ASC ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(Exception e){
                
                return null;
            }
    }

    public boolean abrirsesion(String nombre) throws java.sql.SQLException{       
        String q = "update responsable set sesion=1 where usuario ='"+nombre+"';" ;
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                
                return true;
            }
    }
    public boolean cerrarsesion(String nombre) throws java.sql.SQLException{       
        String q = "update responsable set sesion=0 where usuario ='"+nombre+"';" ;
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                
                return true;
            }
    }

    public ResultSet Consulta(String consulta) {
        String q = consulta;
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(Exception e){
                
                return null;
            }
    }
    public ResultSet vw_buscaClaveHoja(String parametro) {
        String q = "select clave from vw_infopapelh;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet vw_buscaClaveTodo(String parametro) {
        String q = "select clave from papel;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet vw_buscaClaveTodoExist(String parametro) {
        String q = "select clavepapel from inventario where cantidad >0 and presentacion > 0 ;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet vw_buscaClaveBobina(String parametro) {
        String q = "select clave from vw_infopapelb;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet vw_buscaAnchoHoja(String parametro) {
        String q = "select distinct ancho from vw_infopapelh;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet vw_buscaAnchoTodo(String parametro) {
        String q = "select  distinct ancho from papel;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet vw_buscaAnchoBobina(String parametro) {
        String q = "select  distinct ancho from vw_infopapelb;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet vw_buscaAltoHoja(String parametro) {
        String q = "select distinct alto from vw_infopapelh;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }

    public ResultSet vw_buscaFolioHoja(String parametro) {
        String q = "select folio from vw_infosalidah;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }

    public ResultSet vw_buscaFolioBobina(String parametro) {
        String q = "select folio from vw_infosalidab;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }

    public ResultSet buscaPapelExist(String parametro, String propietario, String tipo) {
        String q = "select clave from vw_infopapel"+tipo+" where propiedad='"+propietario+"' and cantidad>0 and presentacion>0;";

        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }

    
    public void abrirReporte(String report,HashMap map){
        try{
            String in ="C:/IEXSA/Reportes/"+report;
            JasperReport archivo = JasperCompileManager.compileReport(in);
            JasperPrint jasperPrint= JasperFillManager.fillReport(archivo,map,this.getConexion());
            java.util.List<JRPrintPage> pages = jasperPrint.getPages();
            if(pages.isEmpty()){
                JOptionPane.showMessageDialog(null, "Sin paginas, el reporte no se ha creado...");
                return;
            }
            JasperViewer jviewer= new JasperViewer(jasperPrint,false);
            jviewer.setTitle("Reportes de DisPaper");
            jviewer.setVisible(true);
            Dimension screenSize = jviewer.getToolkit().getScreenSize();
            jviewer.setSize(screenSize.width-40, screenSize.height-40);
            jviewer.setIconImage(Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("imagenes/logo.png")));
            jviewer.setLocationRelativeTo(null);
            /*JasperExportManager.exportReportToPdfFile(jasperPrint, "C://IEXSA//"+report+".pdf");
            File f = new File("C://IEXSA//"+report+".pdf");
                        Desktop.getDesktop().open(f);*/
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    

    public ResultSet buscarEntrada(String buscarfolio) {
        String q = "SELECT * FROM entrada where folio = '"+buscarfolio+"'; ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarSalida(String buscarfolio) {
        String q = "SELECT * FROM salida where folio = '"+buscarfolio+"'; ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    
    public ResultSet buscarSalidaH(String buscarfolio) {
        String q = "SELECT * FROM salidah where folio = '"+buscarfolio+"'; ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }

    public ResultSet buscarSalidaB(String buscarfolio) {
        String q = "SELECT * FROM salidab where folio = '"+buscarfolio+"'; ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarDetalleEntrada(int idfolio) {
        String q = "SELECT * FROM detalleentrada where id_entrada = '"+idfolio+"'; ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    
    public ResultSet buscarDetalleSalida(int idfolio) {
        String q = "SELECT * FROM detallesalida where idsalida = '"+idfolio+"'; ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarDetalleSalidaH(int idfolio) {
        String q = "SELECT * FROM detallesalidah where id_salida = '"+idfolio+"'; ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarEntradaID(String id) {
        String q = "SELECT total_entrada,cantidad_entrada,total_temporal,cantidad_temoporal FROM detalleentrada where id_detalleentrada="+id+"; ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    //
    public ResultSet buscarDetalleSalidaB(int idfolio) {
        String q = "SELECT * FROM detallesalidab where id_salida = '"+idfolio+"'; ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }

    public ResultSet buscarDetalleEntrada(String clave) {
        String q = "SELECT clave_papel,total_entrada,cantidad_entrada,ubicacion, costo, total_costo FROM detalleentrada where clave_papel   = '"+clave+"'; ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }

    public boolean updatedetalleentrada(String id,String campo,String valor) {
        String q=" UPDATE  `dis_paper`.`detalleentrada` SET "+campo+" ='"+valor+"' WHERE id_entrada ='"+id+"';";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage() + " updatedetalle");
            return false;
        }
    }
    public boolean updatedetallesalidah(String id,String campo,String valor) {
        String q=" UPDATE  `dis_paper`.`detallesalidah` SET "+campo+" ='"+valor+"' WHERE id_salida ='"+id+"';";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage() + " updatedetalle");
            return false;
        }
    }
    public boolean updateClave(String id,String clave) {
            String q=" UPDATE  `dis_paper`.`detalleentrada` SET clave_papel ='"+clave+"' WHERE id_entrada ='"+id+"';";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage() + " updatedetalle");
            return false;
        }
    }
    public boolean updateClaveSalidaH(String id,String clave) {
            String q=" UPDATE  `dis_paper`.`detallesalidah` SET clave_papel ='"+clave+"' WHERE id_salida ='"+id+"';";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage() + " updatedetalle");
            return false;
        }
    }
    public boolean modifEntrada(String folioentrada,String t1, String t2, String t3,String ordenProduccion,String ordenCompra,String documentoEntrada,int propietario,int proveedor, int responsable,String fecha,int tipoentrada,String Observaciones,int cliente){
        String q1="UPDATE `dis_paper`.`entrada` SET `TURNO1`='"+t1+"', "
                + "`TURNO2`='"+t2+"', "
                + "`TURNO3`='"+t3+"', "
                + "`orden_produccion`='"+ordenProduccion+"', "
                + "`orden_compra`='"+ordenCompra+"', "
                + "`documento_entrada`='"+documentoEntrada+"', "
                + "`id_propietario`='"+propietario+"', "
                + "`id_proveedores`='"+proveedor+"', "
                + "`id_responsable`='"+responsable+"', "
                + "`fecha`='"+fecha+"', "
                + "`tipo_entrada`='"+tipoentrada+"', "
                + "`observaciones`='"+Observaciones+"', "
                + "`cliente`='"+cliente+"' "
                + "WHERE `folio`='"+folioentrada+"';";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q1);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            
            return false;
        }
    }
    
    public boolean modifSalida(String foliosalida,String t1, String t2, String t3,String ordenProduccion,String ordenCompra,String documentoSalida,int propietario,int proveedor, int responsable,String fecha,int tiposalida,String Observaciones,int cliente){
        String q1="UPDATE `dis_paper`.`salida` SET `TURNO1`='"+t1+"', "
                + "`TURNO2`='"+t2+"', "
                + "`TURNO3`='"+t3+"', "
                + "`orden_produccion`='"+ordenProduccion+"', "
                + "`orden_compra`='"+ordenCompra+"', "
                + "`documento_salida`='"+documentoSalida+"', "
                + "`id_propietario`='"+propietario+"', "
                + "`id_proveedores`='"+proveedor+"', "
                + "`id_responsable`='"+responsable+"', "
                + "`fecha`='"+fecha+"', "
                + "`tipo_salida`='"+tiposalida+"', "
                + "`observaciones`='"+Observaciones+"', "
                + "`cliente`='"+cliente+"' "
                + "WHERE `folio`='"+foliosalida+"';";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q1);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            
            return false;
        }
    }
    
    public boolean modifSalidaH(String foliosalida,String t1, String t2, String t3,String ordenProduccion,String estandarProduccion,int propietario,int maquina,String titulo, int responsable,String fecha,int tiposalida,String Observaciones,int cliente){
        String q1="UPDATE `dis_paper`.`salidah` SET `TURNO1`='"+t1+"', "
                + "`TURNO2`='"+t2+"', "
                + "`TURNO3`='"+t3+"', "
                + "`orden_produccion`='"+ordenProduccion+"', "
                + "`estandar_produccion`='"+estandarProduccion+"', "
                + "`id_cliente`='"+cliente+"', "
                + "`id_propietario`='"+propietario+"', "
                + "`id_maquina`='"+maquina+"', "
                + "`fecha`='"+fecha+"', "
                + "`titulo`='"+titulo+"', "
                + "`id_responsable`='"+responsable+"', "
                + "`observaciones`='"+Observaciones+"', "
                + "`tipo_salida`='"+tiposalida+"' "
                + "WHERE `folio`='"+foliosalida+"';";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q1);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            
            return false;
        }
    }
     public boolean modifSalidaB(String foliob,String t1,String t2,String t3,String op,String ep,String factor,String pliego,String cliente,String propietario,String  maquina,
                            String fecha,String titulo,String responsable,String observaciones,String totalkgs,String tiroxpliego,String pliegokgs,
                            String pliegoajuste,String ajustekgs,int tiposal){
        String q1="UPDATE `dis_paper`.`salidab` SET "
                + "`TURNO1`='"+t1+"', "
                + "`TURNO2`='"+t2+"', "
                + "`TURNO3`='"+t3+"', "
                + "`orden_produccion`='"+op+"', "
                + "`factor`='"+factor+"', "
                + "`pliego`='"+pliego+"', "
                + "`id_cliente`='"+cliente+"', "
                + "`id_propietario`='"+propietario+"', "
                + "`id_maquina`='"+maquina+"', "
                + "`fecha`='"+fecha+"', "
                + "`titulo`='"+titulo+"', "
                + "`id_responsable`='"+responsable+"', "
                + "`observaciones`='"+observaciones+"', "
                + "`totalkg`='"+totalkgs+"', "
                + "`tiroxpliego`='"+tiroxpliego+"', "
                + "`pliegokgs`='"+pliegokgs+"', "
                + "`pliegoajuste`='"+pliegoajuste+"', "
                + "`ajustekgs`='"+ajustekgs+"', "
                + "`tipo_salida`='"+tiposal+"' "
                + "WHERE `folio`='"+foliob+"';";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q1);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            
            return false;
        }
     }
     
     public ResultSet buscarPrimeraEntrada2(String clave,String fec) {
        String q = "select d.id_detalleentrada as id, clave_papel, total_temporal as total ,cantidad_temoporal as cantidad,min(fecha) as fecha_entrada from detalleentrada d, entrada e where clave_papel='"+clave+"' and e.id_entrada=d.id_entrada  and fecha<='"+fec+"' and(total_temporal>0 OR cantidad_temoporal>0) ;";

        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }

     public ResultSet buscarUltimaEntrada(String clave,String fec) {
        String q = "select id_detalleentrada,d.id_entrada,clave_papel,total_entrada,cantidad_entrada, total_temporal as total_temporal ,cantidad_temoporal as cantidad_temoporal,fecha as fecha_entrada\n" +
"from detalleentrada d, entrada e " +
"where e.id_entrada=d.id_entrada " +
"and clave_papel = '"+clave+"'" +
"and fecha <= '"+fec+"'" +
"and id_detalleentrada = (select max(id_detalleentrada) " +
"from detalleentrada d, entrada e " +
"where e.id_entrada=d.id_entrada " +
"and clave_papel = '"+clave+"' " +
"and fecha <= '"+fec+"')";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
     
    public ResultSet buscarPrimeraEntrada(String clave,String fec) {
        String q = "select d.id_detalleentrada as id, clave_papel, total_temporal as total ,cantidad_temoporal as cantidad,min(fecha) as fecha_entrada from detalleentrada d, entrada e where clave_papel='"+clave+"' and e.id_entrada=d.id_entrada and total_temporal>0  cantidad_temoporal>0 and fecha<='"+fec+"';";

        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }

    public boolean nuevacantidadtemporal(String id,Double cantidad, Double total) {
        String q1="UPDATE `dis_paper`.`detalleentrada` SET cantidad_temoporal='"+cantidad+"', total_temporal='"+total+"' where id_detalleentrada='"+id+"'";
         try{

            PreparedStatement pstm = this.getConexion().prepareStatement(q1);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            
            return false;
        }
    }
    public ResultSet buscarExistenciaPapelfecha2(String clavePapel,String fecha) {
        String q = "call sumaxistencia_fecha2('"+clavePapel+"','"+fecha+"');";
        System.out.println(q);
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet primeraentrada(String clavePapel) {
        String q = "select id_detalleentrada as id, total_temporal as kghoj, cantidad_temoporal as bobpaq,costo from detalleentrada where clave_papel = '"+clavePapel+"' and total_temporal>0 limit 1";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    } 
    public boolean updateteporalde(int newkghoj,int newbobpaq,int id) {
        String q=" UPDATE  `dis_paper`.`detalleentrada` SET  `cantidad_temoporal` =  '"+newbobpaq+"',total_temporal='"+newkghoj+"' WHERE `id_detalleentrada` =  '"+id+"' ;";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage() + "nuevexis");
            return false;
        }
    }
    public boolean sumarexistencia(String clave) {
        String q1="CALL sumaexistencia3('"+clave+"')";
         try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q1);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    public boolean costopromedio(String clave) {
        String q1="CALL preciopromedio('"+clave+"')";
         try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q1);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    public boolean SALIDAS() {
        String q1="CALL salidas()";
         try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q1);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public ResultSet buscarReporteDate(String datee) {
        String q = "SELECT nombreempleado as Nombre_de_Empleado, CONCAT( fecha,' - ',horaentrada) AS Fecha_y_Hora_de_Ingreso, CONCAT( fecha,' - ',horasalida) AS Fecha_y_Hora_de_Salida FROM `reporusuario` WHERE fecha='"+datee+"'";
        //          SELECT nombreempleado, CONCAT( fecha,horaentrada) AS ingreso, CONCAT( fecha,horasalida) AS salida FROM `reporusuario` WHERE nombreempleado =  'jhafet' LIMIT 0 , 30
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(Exception e){
                
                return null;
            }
    }
    
    public ResultSet ultimocosto(String clave) {
        String q = "select costo from detalleentrada where clave_papel='"+clave+"' order by id_detalleentrada desc limit 1";
        //          SELECT nombreempleado, CONCAT( fecha,horaentrada) AS ingreso, CONCAT( fecha,horasalida) AS salida FROM `reporusuario` WHERE nombreempleado =  'jhafet' LIMIT 0 , 30
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(Exception e){
                
                return null;
            }
    }
    
    public ResultSet costoprom(String clave) {
        String q = "select preciopromedio from inventario where clavepapel='"+clave+"'";
        //          SELECT nombreempleado, CONCAT( fecha,horaentrada) AS ingreso, CONCAT( fecha,horasalida) AS salida FROM `reporusuario` WHERE nombreempleado =  'jhafet' LIMIT 0 , 30
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(Exception e){
                
                return null;
            }
    }
    
    public ResultSet ops(String opini,String opfin) {
        String q = "select id_op from ordenprod where id_op>='"+opini+"' and id_op<='"+opfin+"'";
        //          SELECT nombreempleado, CONCAT( fecha,horaentrada) AS ingreso, CONCAT( fecha,horasalida) AS salida FROM `reporusuario` WHERE nombreempleado =  'jhafet' LIMIT 0 , 30
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(Exception e){
                
                return null;
            }
    }
    
   
    public void bp(String fecha){
        database.backupbd(fecha);
    }
    
    public boolean cerrarsesiones(int SesionCerrada) {
            String q=" UPDATE  `dis_paper`.`responsable` SET Sesion ='"+SesionCerrada+"';";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean bajaPapel(String clave) {
            String q="delete i,p from inventario as i inner join papel as p where p.clave = i.clavepapel and i.clavePapel='"+clave+"';";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean newNombre(String Nombre) {
        String q = "INSERT INTO `dis_paper`.`nombre_papel` (`nombre`, `id_clase_papel`, `id_tipo_papel`) VALUES ( '"+Nombre+"','0','0');";
        //          INSERT INTO `dis_paper`.`nombre_papel` (`nombre`, `id_clase_papel`, `id_tipo_papel`) VALUES (        'PROBANDO','1','0');
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    
    public boolean ops() {
        String q = "truncate consumototalop;";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    public boolean oop(String op) {
        String q = "call op('"+op+"');";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    public ResultSet buscatodoop() {
        String q = "SELECT id_op FROM ordenprod;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public boolean bajaProveedor(int id) {
            String q="delete from proveedores where id_provedores='"+id+"'";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    public boolean bajaPropietarios(int id) {
            String q="delete from propietarios where id_propietarios='"+id+"'";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    public boolean bajaClientes(int id) {
            String q="delete from clientes where id_clientes='"+id+"'";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    public boolean bajaMaquina(int id) {
            String q="delete from maquinas where id_maquina='"+id+"'";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    public boolean bajaTipoEntrada(int id) {
            String q="delete from tipo_entrada where id_tipo_en='"+id+"'";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    public boolean bajaTipoSalida(int id) {
            String q="delete from tipo_salida where id_tipo_sal='"+id+"'";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public ResultSet buscaridColor(String nombre){ 
        String q = "SELECT id_color as id FROM color where color='"+nombre+"';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscaridNombre(String nombre){ 
        String q = "SELECT id_nombre as id FROM nombre_papel where nombre='"+nombre+"';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscaridPropietario(String nombre){ 
        String q = "SELECT id_propietarios as id FROM propietarios where nombre='"+nombre+"';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscaridMarca(String nombre){ 
        String q = "SELECT id_marca as id FROM marca where marca='"+nombre+"';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarUbicacion(String nombre){ 
        String q = "select ubicacion from inventario where clavepapel='"+nombre+"'";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarOP(String nombre){ 
        String q = "select op from inventario where clavepapel='"+nombre+"'";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    
    public boolean updateop(String clave,String op) {
            String q=" UPDATE  inventario SET op='"+op+"' where clavepapel ='"+clave+"';";
        try{

            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    //SELECT DISTINCT CLAVE , ORDEN_PRODUCCION FROM VW_INFOENTRADA
    
   
    public boolean ubicacion(String clave,String ubicacion) {
            String q=" UPDATE  inventario SET ubicacion='"+ubicacion+"' where clavepapel ='"+clave+"';";
        try{

            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    public boolean tottemp(int tottemp,int canttem,String id) {
            String q=" UPDATE  detalleentrada SET total_temporal='"+tottemp+"', cantidad_temoporal='"+canttem+"' where id_detalleentrada ='"+id+"';";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    public boolean bajaNombre(int id) {
            String q="delete from nombre_papel where id_nombre='"+id+"'";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    public boolean bajaColor(int id) {
            String q="delete from color where id_color='"+id+"'";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    public boolean bajaMarca(int id) {
            String q="delete from marca where id_marca='"+id+"'";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public ResultSet buscafoliotrasp(String parametro) {
         String q = "select folio from traspaso;";          
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }

    public ResultSet buscarOrdenPro(String clave) {                
        String q = "SELECT orden_produccion FROM dis_paper.vw_infoentrada where clave = '"+clave+"';";
                  //SELECT orden_produccion FROM dis_paper.vw_infoentrada where clave = '01-3-BLA-2-87068-IEXSA-BIOPAPPEL';
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscafoliosg(String parametro) {
        String q = "SELECT folio FROM dis_paper.vw_infosalida where folio like'%"+parametro+"%' order by id_salida asc;";
                  //SELECT folio FROM dis_paper.vw_infosalida where folio like"%             %" order by id_salida asc;
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    
    public ResultSet buscaentrada(int identrada) {
        String q = "select id_detalleentrada as id, total_temporal as kghoj, cantidad_temoporal as bobpaq,costo,clave_papel from detalleentrada where id_detalleentrada='"+identrada+"' and total_temporal>0 limit 1";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }  
}

