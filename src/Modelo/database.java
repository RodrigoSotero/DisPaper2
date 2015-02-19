/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Favorito
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 * 
 * @author Oldayr
 */
public class database {

  /* DATOS PARA LA CONEXION */
  /** base de datos por defecto es test*/
  static String db = "dis_paper2";
  /** usuario */
  static String user = "root";
  /** contraseña de MySql*/
  static String password = "root1234";
  /** Cadena de conexion */
  static String direccion;
  /** variable para trabajar con la conexion a la base de datos */
 static Connection conn = null;
    static String url=null;
  
    static String cadena;
    
    
  public static void copiarFicheros(File f1, File f2){
                try {
                        InputStream in = new FileInputStream(f1);
                        if(!f2.exists()){
                            OutputStream out = new FileOutputStream(f2);
                            byte[] buf = new byte[1024];
                            int len;

                            while ((len = in.read(buf)) > 0) {
                                    out.write(buf, 0, len);
                            }

                            in.close();
                            out.close();
                            System.out.println("Copiando fichero " + f1.toString());
                        }
                } catch (IOException ioe){
                        ioe.printStackTrace();
                }
        }
        

        public static void copiarDirectorios(File d1, File d2){
            // Comprobamos que es un directorio
                if (d1.isDirectory()){
                        //Comprobamos si existe el directorio destino, si no lo creamos
                        if (!d2.exists()){                              
                                d2.mkdir();
                                System.out.println("Creando directorio " + d2.toString());
                        }
                        // Sacamos todos los ficheros del directorio
                        String[] ficheros = d1.list();
                        for (int x=0;x<ficheros.length;x++) {
                                // Por cada fichero volvemos a llamar recursivamente a la copa de directorios
                                copiarDirectorios(new File(d1,ficheros[x]),new File(d2,ficheros[x]));                           
                        }  
                } else {
                        copiarFicheros(d1,d2);
                }
                
        }
  

   /** Constructor de clase */
   public database(){
      //this.direccion = JOptionPane.showInputDialog(null,"Escribe la direccion ip del servidor");
       File folder = new File("C:\\IEXSA\\");
       if (!folder.exists()) { 
           folder.mkdir();
       }
      String location = database.class.getProtectionDomain().getCodeSource().getLocation().toString();
      location = location.replace("file:/", "");
      location=location+"Reportes";
      File d1 = new File(location);
      if (!d1.exists()) { 
            d1.mkdir();
      }
      File d2 = new File("c:\\IEXSA\\Reportes");
      if (!d2.exists()) { 
            d2.mkdir();
      }
      copiarDirectorios(d1,d2);
      String location2 = database.class.getProtectionDomain().getCodeSource().getLocation().toString();
      location2 = location2.replace("file:/", "");
      location2=location2+"config";
      File d12 = new File(location2);
      if (!d12.exists()) { 
            d12.mkdir();
      }
      File d22 = new File("c:\\IEXSA\\config");
      if (!d22.exists()) { 
            d22.mkdir();
      }
      copiarDirectorios(d12,d22);
      FileReader fr = null;
        PrintWriter pw = null;
        
        try{
            File folder2 = new File("C:\\IEXSA\\config");
            if (!folder2.exists()) { 
                folder2.mkdir();
            } 
            String sFichero = "c:\\IEXSA\\config\\conf.txt";
            File fichero = new File(sFichero);
            if(!fichero.exists()){
                PrintStream archivo = new PrintStream("C:\\IEXSA\\config\\conf.txt");
            }
            fr = new FileReader(fichero);
            BufferedReader entrada = new BufferedReader(fr);
            cadena = entrada.readLine();
            while(cadena==null || cadena.length()<=1){
                cadena=JOptionPane.showInputDialog(null, "Ingresa la direccion de la base se datos");
                pw = new PrintWriter(fichero);
                pw.println(cadena);
                pw.flush();
            }
            
            url = "jdbc:mysql://"+cadena+"/"+this.db;
              //obtenemos el driver de para mysql
            
              Class.forName("com.mysql.jdbc.Driver");
              //obtenemos la conexión
              conn = DriverManager.getConnection( this.url, this.user , this.password );
              //conn.is
              
              if (conn!=null){
                  System.out.println("exacto p");
              }else{
                  JOptionPane.showMessageDialog(null,"Conexion no estaablecida con la base de datos.");
              }
              cadena=cadena.replace(":3306", "");
              
              //--result-file=D:\\COMP.ccm
//C:\Program Files\MySQL\MySQL Server 5.6\bin\mysqldump.exe -uroot -proot1234 -h 192.168.0.69 --routines dis_paper --result-file=C:\\iexsa\\COMP.ccm              
//C:\Program Files\MySQL\MySQL Server 5.6\bin\mysqldump.exe -uroot -proot1234 -h 192.168.0.69 --routines dis_paper>C:\IEXSA\dump.sql
              //String executeCmd = "mysqldump -uroot -proot1234 -h  --routines dis_paper ";
        
        
              
              
           }catch(Exception e){
              System.err.println( e.getMessage() );
           }
        
   }
   
   public static void backupbd(String fecha){
   Process runtimeProcess;
        try {
            String sFichero = "C:\\iexsa\\backups\\dump"+fecha+".sql";
            File fichero = new File(sFichero);
            if(fichero.exists()){
                return;
            }
            String executeCmd="mysqldump -u"+user+" -p"+password+" -h "+cadena+" --routines dis_paper2 --result-file=C:\\iexsa\\backups\\dump"+fecha+".sql";
            System.out.println(executeCmd);
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            runtimeProcess.getErrorStream();
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("Backup created successfully");
            } else {
                System.out.println(processComplete);
                System.out.println("Could not create the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
   }

   public Connection getConexion()
   {
    return this.conn;
   }

    
}
