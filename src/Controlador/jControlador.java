 /**
 *
 * @author Favorito && y0op0o    DISPAPER
 */
package Controlador;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import com.mxrck.autocompleter.*;
import com.toedter.calendar.JDateChooser;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.util.HashMap;
////////////////////////////////////////////////////////////////////////////////////////////
import Modelo.*;
import Vista.*;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

public class jControlador implements ActionListener{
    HiloProgreso hilo;
    private final  Login login = new Login();
    private final  modelo mimodelo = new modelo();
    private final  MenuMaster menumaster = new MenuMaster();
    Vista.papel ap = new papel();
    private final  Vista.MovimientosPapel movimientos = new MovimientosPapel();
    private final  Vista.Inventarios reportes = new Inventarios();
    private final  Vista.ConsultaPapel consultas = new ConsultaPapel();
    private final  Vista.Fecha fecha = new Fecha();    
    private final  Vista.NewUser newusuario = new NewUser();
    private final  Vista.Password cambiocontraseña = new Password();
    private static  Vista.Splash splash = new Splash();   
    private final  Vista.Tras traspaso = new Tras();
    Vista.Tiro tiro = new Tiro();
    private final  Vista.NewProveedor provedor = new NewProveedor();
    private final  Vista.Emergente emergente = new Emergente();
    private final  Vista.ReporteUser reporteuser = new ReporteUser();
    private final  Vista.Consulta verconsulta =  new Consulta();
    private final  Vista.Correo correo = new Correo();
    private final  Vista.bajaPapel bajaP = new bajaPapel();
    private final  Vista.ReFinanzas Finanzas = new ReFinanzas();
    private final  Vista.Ubicacion ubi = new Ubicacion();
    private final  Vista.ConsumoTotal Consumo = new ConsumoTotal();
    private final  Vista.ReTraspaso retras = new ReTraspaso();
    JPopupMenu popup = new JPopupMenu(); 
    HashMap map = new HashMap();
    String pswd;
    String fec,mensaje,user="",contra,ordenProduccion,ordenCompra,documentoEntrada,t1="",t2="",t3="",Obs="",modificaruser,folioentrada,foliosalida;
    String buscarfolio;
    boolean campo;
    int cliente =0,a=1,id_responsable,cargo,confir,tiposalida, propietario,proveedor, tipoentrada,tipotraspaso=1,tipoalta=0,clienteprovedor=0,modificarentrada=0,modificarsalida=0,modificarsh,modificarentradab,se,act;
    Double tmp=0.0,tmppliegos=0.0,tmpkg=0.0,tmpbob=0.0;
    int  tiposal=0; 
    String tmpclv="",horaentrada,fech,horasalida,ent,sal;
            BigDecimal costo,totalcosto;
    int pedirfecha=0;
    int invinikgs, invinibob,surkgs,surbob,devkgs,devbob, exiskgs=0,exisbob=0,newkgs,newbob,id_salida = 0;
    String foliob,titulosalb,opsalB, epsalb,factor,pliego,totalkil,tiroxpliego,pliegosdeajuste,pliegokgs,ajustekgs, maqsalbob,eval;
    int maqsalb, clientesalb, propsalb;
    Double dsppliego=0.0,tmpconopliego=0.0, capapliego=0.0, empqpliegos=0.0,dsppliegos=0.0,newtotal,newcantidad,restartotal,totalbd=null,restarcantidad,cantidadbd=null;
    Double conokg=0.0,capakg=0.0,empqkg=0.0,dspkg=0.0,totalkg=0.0, totalpliego=0.0;
    int abrirpantalla=0;
    String datosini [][];//array temporal :D
    String datosfin [][];//array nuevo :P
    int filas,columnas;
    String nombrecolumnas[];
    String identradas[]=new String [1000];
    String idsalidash[]=new String [1000];
    String idsalidas[]=new String [1000];
    String idsalidasb[] = new String[1000];
    int modificarsalidah=0,modificarsalidab=0;
    private String idsalb ,idd_salida;
    TextAutoCompleter Com_cliente,Com_clienteEntr ,Com_propietario,Com_propietarioEntr,bajapapel,Com_Origen,Com_Destino ;
    private TextAutoCompleter Com_clienteB;
    private TextAutoCompleter Com_propietarioB;
    private String ventana;
    hilobd h1;
    public jControlador( JFrame padre ){
        //this.frmprincipal = (frmPrincipal) padre;
        this.splash = (Splash) padre;
        
    }
    public boolean conexionBD(){
        try {
            mimodelo.bucarMaxSalidab();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    private void ponerfecha() {
        menumaster.__etqFechaMenuMaster.setText(fec);
        ap.__etqFech.setText(fec);
        movimientos.__etqFechaSalida.setText(fec);
        movimientos.__etqFechaEnt.setText(fec);
        movimientos.__etqFechaSalB.setText(fec);
        movimientos.__etqFechaSalH.setText(fec);
        reportes.__etqFecha.setText(fec);
        consultas.__etqFechaConMov.setText(fec);
    }
    public void mostrarTraspaso() {
        traspaso.__Origen.setVisible(true);
        traspaso.jLabel21.setVisible(true);
        traspaso.jLabel19.setVisible(true);
        traspaso.__Destino.setVisible(true);     
        traspaso.jLabel35.setVisible(true);
        traspaso.jLabel33.setVisible(true);
        traspaso.__ClavePapelTras.setVisible(true);
        traspaso.__CantidadTotal.setVisible(true);
        traspaso.__CantidadPT.setVisible(true);
        traspaso.__etqCantidad.setVisible(true);
        traspaso.__etqCantidadPT.setVisible(true);        
        traspaso.__CANCELARTRASPASO.setVisible(true);
        traspaso.__ACEPTARTRASPASO.setVisible(true);
        traspaso.__Origen.requestFocus();
    }
    public void ocultarTraspaso() {
        if(traspaso.__BobinaTras.isSelected()== true ||traspaso.__HojaTras.isSelected() == true ){
             traspaso.__ClasePapelTraspaso.clearSelection();
        }
        traspaso.__CantidadPT.setText("");
        traspaso.__ClavePapelTras.setText("");
        traspaso.__CantidadTotal.setText("");
        traspaso.__Origen.setText("");
        traspaso.__Destino.setText("");
        traspaso.__CostoTras.setText("");
        traspaso.jLabel21.setVisible(true);
        traspaso.jLabel19.setVisible(true);
        traspaso.jLabel35.setVisible(true);
        traspaso.jLabel33.setVisible(true);
        traspaso.__ClavePapelTras.setVisible(true);
        traspaso.__CantidadTotal.setVisible(true);
        traspaso.__CantidadPT.setVisible(true);
        traspaso.__etqCantidad.setVisible(true);
        traspaso.__etqCantidadPT.setVisible(true); 
    }
    private void maximosalidah() {
        try {
            String folio = mimodelo.buscarFolioMaxSalidaH();
            movimientos.__FolioSalidaHoja.setText(folio);
        } catch (SQLException ex) {
            mensaje(3,ex.getMessage());
        }
    }
    private void maximoentrada() {
        try {
            String folio = mimodelo.buscarFolioEntrada();
            movimientos.__FolioEntrada.setText(folio);
        } catch (SQLException ex) {
            mensaje(3,ex.getMessage());
        }
    }
    private void maximosalida() {
        try {
            String folio = mimodelo.buscarFolioSalida();
            movimientos.__FolioSalida.setText(folio);
        } catch (SQLException ex) {
            mensaje(3,ex.getMessage());
        }
    }
    private void maximosalidab() {
        try {
            String folio = mimodelo.buscarFolioMaxSalidaB();
            movimientos.__FolioSalidaBobina.setText(folio);
        } catch (SQLException ex) {
            mensaje(3,ex.getMessage());
        }
    }
    public boolean compara(String fecha){
        fecha = fecha +" 00:00:00";
        Calendar Cal= Calendar.getInstance();
        String anio=Cal.get(Cal.YEAR)+"";
        String mes=(Cal.get(Cal.MONTH)+1)<10?"0"+(Cal.get(Cal.MONTH)+1):(Cal.get(Cal.MONTH)+1)+"";
        String dia=Cal.get(Cal.DATE)<10?"0"+Cal.get(Cal.DATE):(Cal.get(Cal.DATE))+"";
        String fechaactual=anio+"-"+mes+"-"+dia+ " 00:00:00";
        int max = Integer.parseInt(calcularHoras(fecha, fechaactual,2));
        if(max<16&&max>-1){
               return true;                                     
        }
        return false;
    }
    public String calcularHoras(String fechaInicial, String fechaFinal, int parametro ) {
        java.util.GregorianCalendar jCal = new java.util.GregorianCalendar();
        java.util.GregorianCalendar jCal2 = new java.util.GregorianCalendar();
                //2014-07-20 00:00:00:00
            //jCal.set(year, month, date, hourOfDay, minute)
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
            mensaje(3,"La Hora Final es Incorrecta");
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
             return dias+"";
         }
        return null;
         
    }
    public void borrarFormularioTiro() {
        tiro.__ContadorRotativas.setText("");
        tiro.__EstandarProduccionXHora.setText("");
        tiro.__FechaFinal.setDate(null);
        tiro.__FechaInicial.setDate(null);
        tiro.__TiempoRealProduccion.setText("");
        tiro.__TotalPliegos.setText("");
        tiro.op="";
        tiro.__HoraFinal.setValue(null);
        tiro.__HoraInicial.setValue(null);
        tiro.__MermaImpresion.setText("");
    }
    public boolean aceptarfecha() {
        fec = this.aceptarFecha(fecha.date,0);
                if(fec==null){
                    //mensaje="Ingresa la fecha a capturar";
                    fecha.date.getDateEditor().getUiComponent().requestFocus();
                    return false;
                }
                confir = this.mensajeConfirmacion("La fecha es: "+fec+"\n¿Esta correcta?", "FECHA");
                if (confir==JOptionPane.OK_OPTION){
                    if(this.compara(fec)==true){
                        return true;
                    }else{
                        mensaje(3,"No Puedes Capturar la Fecha: "+fec);
                        return false;
                    }
                }
        return false;
    }
    public void SalirSistema(){
        try {
            //meter un update 
            if(cargo==1){
                mimodelo.bp(fech);
                this.enviaarchivo("C:\\iexsa\\backups\\dump"+fech+".sql","dispaper.iexsa@gmail.com" ,"Backup de la base de datos");
                File fichero = new File("C:\\iexsa\\backups\\dump"+fech+".sql");
                fichero.delete();
            }
             Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, false);
              Calendar Cal= Calendar.getInstance();                                                  
                               String hora=Cal.get(Cal.HOUR_OF_DAY)<10 ? "0"+Cal.get(Cal.HOUR_OF_DAY) : ""+Cal.get(Cal.HOUR_OF_DAY);
                               String minute=Cal.get(Cal.MINUTE)<10 ? "0"+Cal.get(Cal.MINUTE) : ""+Cal.get(Cal.MINUTE);
                               horasalida = hora+":"+minute;                
            boolean registrasalida=mimodelo.horasalida(horasalida,user);
            if(!user.equals("ROOT")){
                mimodelo.cerrarsesion(user);
            }
            System.exit(0);
        } catch (SQLException ex) {
            mensaje(3,ex.getMessage());
        }
    }
    public void buscarreporteusuario(int accion){
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            this.reporteuser.__tablaReporteUsuario.setModel(modelo);
            ResultSet buscarReporteUser =null;
            if(accion ==1){
                buscarReporteUser = mimodelo. buscartodoreporte();
            }
            if(accion==2){
                String nombre = this.reporteuser.__ReporteNombreUsuario.getText();
                String datee = this.aceptarFecha(this.reporteuser.reportedate,1);

                if(datee==null){
                    buscarReporteUser = mimodelo.buscarReporteUser(nombre);
                }
                if(nombre.isEmpty()){
                    buscarReporteUser = mimodelo.buscarReporteDate(datee);
                }
                if(datee!=null &&  !nombre.isEmpty()){
                    buscarReporteUser = mimodelo.buscarReporteUserDate(nombre,datee);
                }
                
            }
            ResultSetMetaData md = buscarReporteUser.getMetaData();
            String datos []={"Nombre Empleado","Fecha & Hora Entrada","Fecha & Hora Salida"};
            int col = md.getColumnCount();
           for(int i=0;i<col;i++){
                modelo.addColumn(datos[i]);
            } 
            
            while(buscarReporteUser.next()){
                Object [] fila = new Object[col];
                for(int i=0;i<col;i++){
                    fila[i]=buscarReporteUser.getObject(i+1);
                }
                modelo.addRow(fila);
            }
            this.reporteuser.__tablaReporteUsuario.setEnabled(false);
        } catch (SQLException ex) {
            mensaje(3,ex.getMessage());
        }
    }
    public void fechaini() {
        if (aceptarfecha()==true){
            if(pedirfecha==0){
                    switch(cargo){
                        case 1:
                            menumaster.__etqUsuarioMenuMaster.setText(fecha.__etqUser.getText());
                            menumaster.__ALTA_PAPEL.setEnabled(true);
                            menumaster.setLocationRelativeTo(null);
                            menumaster.show();
                            break;
                        case 2:
                            menumaster.__etqUsuarioMenuMaster.setText(fecha.__etqUser.getText());
                            menumaster.__ALTA_PAPEL.setEnabled(false);
                            menumaster.setLocationRelativeTo(null);
                            menumaster.show();
                            break;
                        case 3:
                            menumaster.__etqUsuarioMenuMaster.setText(fecha.__etqUser.getText());
                            menumaster.__ALTA_PAPEL.setEnabled(false);
                            menumaster.__MOVIMIENTOS.setEnabled(false);
                            menumaster.setLocationRelativeTo(null);
                            menumaster.show();
                            break;
                    }
                }
                ponerfecha();
                fecha.date.setDate(null);
                login.dispose();
                login.setEnabled(true);
                movimientos.setEnabled(true);
                ap.setEnabled(true);                   
                reportes.setEnabled(true);                    
                consultas.setEnabled(true);
                fecha.dispose();
            }
    }
    public void setEnabledConsultas() {
        this.consultas.__clave.setEnabled(true);
        this.consultas.__chkClavePapel.setEnabled(true);
        this.consultas.__cmbNombrePapel.setEnabled(true);
        this.consultas.__chkNombrePapel.setEnabled(true);
        this.consultas.__chkAncho.setEnabled(true);
        this.consultas.__ancho.setEnabled(true);
        this.consultas.__chkPropietario.setEnabled(true);
        this.consultas.__cmbPropi.setEnabled(true);
        this.consultas.__chkKiloHoja.setEnabled(true);
        this.consultas.__cmbKiloHoja.setEnabled(true);
        this.consultas.__kilohojas.setEnabled(true);
        this.consultas.__chkBobinaPaq.setEnabled(true);
        this.consultas.__cmbBobinaPaq.setEnabled(true);
        this.consultas.__bobinapaq.setEnabled(true);
        this.consultas.__chkGrsPts.setEnabled(true);
        this.consultas.__grspts.setEnabled(true);
        this.consultas.__chkColor.setEnabled(true);
        this.consultas.__cmbColor.setEnabled(true);
        if(consultas.__Bobina.isSelected()){
            this.consultas.__chkMarca.setEnabled(true);
            this.consultas.__cmbMarca.setEnabled(true);
            this.consultas.__chkAlto.setEnabled(false);
            this.consultas.__alto.setEnabled(false);
            this.consultas.__alto.setText("");
        }
        if(consultas.__Hoja.isSelected()){
            this.consultas.__chkMarca.setEnabled(false);
            this.consultas.__cmbMarca.setEnabled(false);
            this.consultas.__cmbMarca.setSelectedIndex(0);
            this.consultas.__chkAlto.setEnabled(true);
            this.consultas.__alto.setEnabled(true);
            
        }
        if(consultas.__optNinguno.isSelected()){
            this.consultas.__chkFechaIni.setEnabled(true);
            this.consultas.__dateIni.setDate(null);
            this.consultas.__dateIni.setEnabled(true);
            this.consultas.__chkFechaFin.setEnabled(true);
            this.consultas.__datefin.setDate(null);
            this.consultas.__datefin.setEnabled(true);
            this.consultas.__chkFolio.setEnabled(false);
            this.consultas.__folio.setEnabled(false);
            this.consultas.__foliohasta.setEnabled(false);
            this.consultas.__foliohasta.setText("");
            this.consultas.__folio.setText("");
            this.consultas.__OrdenP.setEnabled(false);
            this.consultas.__chkOrdenP.setEnabled(false);
            this.consultas.__OrdenP.setText("");
            this.consultas.__OrdenC.setEnabled(false);
            this.consultas.__chkOrdenC.setEnabled(false);
            this.consultas.__OrdenC.setText("");
            this.consultas.__chkDocumento.setEnabled(false);
            this.consultas.__documento.setEnabled(false);
            this.consultas.__documento.setText("");
            this.consultas.__chkProveedor.setEnabled(false);
            this.consultas.__cmbProveedor.setEnabled(false);
            this.consultas.__cmbProveedor.setSelectedIndex(0);
            this.consultas.__cmbTipoEntrada.setEnabled(false);
            this.consultas.__chkTipoEntrada.setEnabled(false);
            this.consultas.__cmbTipoEntrada.setSelectedIndex(0);
            this.consultas.__cmbTipoSalida.setEnabled(false);
            this.consultas.__chkTipoSalida.setEnabled(false);
            this.consultas.__cmbTipoSalida.setSelectedIndex(0);
            this.consultas.__chkTurno1.setEnabled(false);
            this.consultas.__chkTurno1.setSelected(false);
            this.consultas.__chkTurno2.setEnabled(false);
            this.consultas.__chkTurno2.setSelected(false);
            this.consultas.__chkTurno3.setEnabled(false);
            this.consultas.__chkTurno3.setSelected(false);
            this.consultas.__chkNombrePapel.setSelected(true);
            this.consultas.__chkAncho.setSelected(true);
            this.consultas.__chkAlto.setSelected(true);
            this.consultas.__chkGrsPts.setSelected(true);
            this.consultas.__chkColor.setSelected(true);
            this.consultas.__chkMarca.setSelected(true);
            this.consultas.__chkPropietario.setSelected(true);
        }
        
        if(consultas.__optEntrada.isSelected()|| consultas.__optSalida.isSelected()){
            this.consultas.__chkFechaIni.setEnabled(true);
            this.consultas.__dateIni.setEnabled(true);
            this.consultas.__chkFechaFin.setEnabled(true);
            this.consultas.__datefin.setEnabled(true);
            this.consultas.__chkFolio.setEnabled(true);
            this.consultas.__folio.setEnabled(true);
            this.consultas.__foliohasta.setEnabled(true);
            this.consultas.__chkDocumento.setEnabled(true);
            this.consultas.__documento.setEnabled(true);
            this.consultas.__OrdenP.setEnabled(true);
            this.consultas.__chkOrdenP.setEnabled(true);
            this.consultas.__OrdenC.setEnabled(true);
            this.consultas.__chkOrdenC.setEnabled(true);
            this.consultas.__chkProveedor.setEnabled(true);
            this.consultas.__cmbProveedor.setEnabled(true);
            this.consultas.__chkNombrePapel.setSelected(false);
            this.consultas.__chkAncho.setSelected(false);
            this.consultas.__chkAlto.setSelected(false);
            this.consultas.__chkGrsPts.setSelected(false);
            this.consultas.__chkColor.setSelected(false);
            this.consultas.__chkMarca.setSelected(false);
            this.consultas.__chkPropietario.setSelected(false);
            
            if(consultas.__optEntrada.isSelected()){
                this.consultas.__cmbTipoEntrada.setEnabled(true);
                this.consultas.__chkTipoEntrada.setEnabled(true);
                this.consultas.__cmbTipoSalida.setEnabled(false);
                this.consultas.__chkTipoSalida.setEnabled(false);
                this.consultas.__chkDocumento.setEnabled(true);
                this.consultas.__chkFolio.setEnabled(true); 
                this.consultas.__cmbTipoSalida.setSelectedIndex(0);                               
                this.consultas.__folio.setEnabled(true);
                this.consultas.__folio.setText("");
                this.consultas.__foliohasta.setEnabled(true);
                this.consultas.__foliohasta.setText("");                
                this.consultas.__documento.setEnabled(true);
                this.consultas.__documento.setText("");
            }
            if(consultas.__optSalida.isSelected()){
                this.consultas.__cmbTipoEntrada.setEnabled(false);
                this.consultas.__cmbTipoEntrada.setSelectedIndex(0);
                this.consultas.__chkTipoEntrada.setEnabled(false);
                this.consultas.__cmbTipoSalida.setEnabled(true);
                this.consultas.__chkTipoSalida.setEnabled(true); 
                this.consultas.__chkDocumento.setEnabled(true);                
                this.consultas.__chkFolio.setEnabled(true); 
                this.consultas.__folio.setEnabled(true);
                this.consultas.__folio.setText("");                
                this.consultas.__foliohasta.setEnabled(true);
                this.consultas.__foliohasta.setText("");
                this.consultas.__documento.setEnabled(true);
                this.consultas.__documento.setText("");
                this.consultas.__OrdenC.setEnabled(false);
                this.consultas.__chkOrdenC.setEnabled(false);
                this.consultas.__OrdenC.setText("");
            }
            this.consultas.__chkTurno1.setEnabled(true);
            this.consultas.__chkTurno2.setEnabled(true);
            this.consultas.__chkTurno3.setEnabled(true);
        }

    }
    int totbdtem=0,cantbdtem=0,newtottem,newcantem,totbd=0,cantbd,j;
    public boolean antipeps2(String[] entradas, int totalsumar, int cantidadsumar) {
        j--;
        try{
            ResultSet detent = mimodelo.buscarEntradaID(entradas[j]);
            while(detent.next()){
                totbd=detent.getInt(1);
                cantbd=detent.getInt(2);
                totbdtem = detent.getInt(3);
                cantbdtem = detent.getInt(4);
            }
            newcantem=cantbdtem+cantidadsumar;
            newtottem=totbdtem+totalsumar;
            if(newcantem==0){
                //no se suma cantidad
                if(newtottem==0){
                    return true;
                }
                if(newtottem>totbd){
                    mimodelo.tottemp(totbd, cantbdtem, entradas[j]);
                    antipeps2(entradas,totalsumar-totbdtem,0);
                }
                if(newtottem<totbd){
                    mimodelo.tottemp(newtottem, 0, entradas[j]);
                    return true;
                }
                if(newtottem==totbd){
                    mimodelo.tottemp(newtottem, 0, entradas[j]);
                    return true;
                }
            }
            if(newcantem>cantbd){
                if(newtottem==0){
                    mimodelo.tottemp(0, cantbd , entradas[j]);
                    antipeps2(entradas,0,cantidadsumar-cantbdtem);
                }
                if(newtottem>totbd){
                    mimodelo.tottemp(totbd, cantbd , entradas[j]);
                    antipeps2(entradas,totalsumar-totbd,cantidadsumar-cantbdtem);
                }
                if(newtottem<totbd){
                    mimodelo.tottemp(newtottem, newcantem , entradas[j]);
                    antipeps2(entradas,0,cantidadsumar-cantbdtem);
                }
                if(newtottem==totbd){
                    mimodelo.tottemp(newtottem, newcantem , entradas[j]);
                    antipeps2(entradas,0,cantidadsumar-cantbdtem);
                }
            }
            if(newcantem<cantbd){
                if(newtottem==0){
                    mimodelo.tottemp(totbdtem, newcantem, entradas[j]);
                    return true;
                }
                if(newtottem>totbd){
                    mimodelo.tottemp(totbd, newcantem, entradas[j]);
                    antipeps2(entradas,totalsumar-totbdtem,0);
                }
                if(newtottem<totbd){
                    //sumar canitdad y total al temporal
                     mimodelo.tottemp(newtottem, newcantem , entradas[j]);
                     return true;
                }
                if(newtottem==totbd){
                    mimodelo.tottemp(newtottem, newcantem , entradas[j]);
                     return true;
                }
            }
            if(newcantem==cantbd){
                if(newtottem==0){
                    mimodelo.tottemp(totbd ,newcantem , entradas[j]);
                    return true;
                }
                if(newtottem>totbd){
                    mimodelo.tottemp(newtottem ,cantbdtem, entradas[j]);
                    antipeps2(entradas,totalsumar-totbdtem,0);
                    return true;
                }
                if(newtottem<totbd){
                    mimodelo.tottemp(newtottem, cantbdtem, entradas[j]);
                    return true;
                }
                if(newtottem==totbd){
                    mimodelo.tottemp(newtottem, newcantem, entradas[j]);
                    return true;
                }
            }
        }
       catch(Exception e) {
       }
        return false;
    }
    public enum Accion{
        __INICIA_SESION, //-> Ejecuta Login
        __SALIR, //-> Sale del sistema
        __BLOQ,
        __ACEPTAR_FECHA,
        __CANCELAR_FECHA,
        __MENU_MASTER_ALTA_PAPEL,
        __MENU_MASTER_MOVIMIENTOS,
        __MENU_MASTER_REPORTES,
        __MENU_MASTER_CONSULTAS,
        __MENU_MASTER_CANCELAR,
        __ALTAS_REGRESAR,
        __OPCION_BOBINA,
        __OPCION_HOJA,
        __GUARDAR_PAPEL,
        __NUEVO_PAPEL,
        //baja Papel
        __ACEPTAR_BAJA,
        __CANCELAR_BAJA,
        //nuevo color
        __NEWCOLOR_GUARDAR,
        __NEWCOLOR_CANCELAR,
        //ALTA DE PAPEL
        __ALTAPAPEL_GUARDAR,
        __ALTAPAPEL_NUEVO,
        __ALTAPAPEL_SALIR,
        
       __ACEPTAR_MOVIMIENTOS_ENTRADA,
       __ACEPTAR_MOVIMIENTOS_SALIDA,
       __ACEPTAR_MOVIMIENTOS_SALIDAH,
       __ACEPTAR_MOVIMIENTOS_SALIDAB,          
       __MODIFICACIONENTRADA,
       __MODIFICACIONSALIDA,
       __MODIFICACIONSALHOJ,
       __MODIFICACIONSALBOB,
       
       //MENUS DE LOS FORMULARIOS
        __MENU_ANTERIOR,
        __MENU_ALTA,
        __MENU_MOV_PAPEL,        
        __MENU_CONSULTAS,
        __MENU_REPORTES,
        __MENU_CERRAR_SESION,
        __MENU_SALIR,
        __MENU_FECHA,
        __MENU_NEWUSER,
        __MENU_CONTRASEÑA,
        __MENU_REPORTE_USUARIOS,
        __MENU_ACERCADE,
        __MENU_BACKUP,
        __MENU_UBICACION,
        __MENU_DATOS,
        __MENU_MOV_TRASPASO,
        __MENU_ABRIR_ARCHIVO,
        __MENU_CERRAR_SESIONES,
        __MENU_BAJA_PAPEL,
        
        //-formpassword
        __GUARDARPASSWORD,
        __CANCELARPASSWORD,
        
        //traspasos
        __ACEPTAR_TRASPASO,
        __CANCELAR_TRASPASO,
        __BOBINA_TRASPASO,
        __HOJA_TRASPASO,
        //BOTONES FORMULARIO NUEVO USUARIO
        __GUARDAR_NEW_USER,
        __CANCELAR_NEW_USER,
        __NUEVO_USER_NEW,
         __MODIFICAR_USER,
        __BLOQUEAR_USER,
        
        //BOTONES TIRO
        __CANCELARTIRO,
        __ACEPTARTIRO,
        
        //BOTONES FACTOR
        __FACTORACEPTAR,
        
        __ACEPTAR_PROVEEDOR,
        __CANCELAR_PROVEEDOR,
        
        //BOTONES EMERGENTE
        __ACEPTAR_EMERGENTE,
        __CANCELAR_EMERGENTE,
        
        //BOTONES DE CONSULTA
        __ACEPTAR_CONSULTA,
        __SALIR_CONSULTA,
        __CONSULTAS,
        
        //VER CONSULTA
        __REGRESAR,
        __EXCEL,
        __IMPRIMIR,
        __CORREO,
        //REPORTE DE USUARIOS
        __ACEPTAR_REPORTE_USUARIO,
        __REGRESAR_REPORTE_USUARIO,
        __NUEVABUSQUEDA_REPORTE_USUARIO,
        //CORREO
        __BUSCARARCHIVO,
        __ABRIREXCEL,
        __ACEPTARCORREO,
        __SALIRCORREO,                
        //Botones de reportes
        __RBOBINA,
        __RHOJA,
        __ENTRADASH,
        __ENTRADASB,                
        __ENTRADASS,
        __SALIDASH,                
        __SALIDASB,  
        __SALIR_REPORTE,
        __ORDENPRODUCCION,
        __ORDENPRODUCCIONH,
        __Traspaso,
        __SALIDAG,
        __RFINANZA,
        __CONSUMOOP,
        //Vista Finanzas
        __ACEPTAR_FINANZAS,
        __CANCELAR_FINANZAS,
        __ACEPTAR_CONSUMO,
        __CANCELAR_CONSUMO,
        __ACEPTAR_UBICACION,
        __CANCELAR_UBICACION,
        //vista ReTraspaso
        __ACEPTAR_TRASPASORE,  
        __CANCELAR_TRASPASORE
        
    }
    public void iniciar(){
        //formulario login
        
        Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
        hilo = new HiloProgreso(this.splash.progreso);
        hilo.start();
        hilo = null;
        this.splash.setLocationRelativeTo(null);
        this.splash.setVisible(true);
        this.splash.progreso.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                if (splash.progreso.getValue()==100){
                    splash.dispose();
                    login.setLocationRelativeTo(null);
                    login.setVisible(true);
                    login.setDefaultCloseOperation(0);
                }
            }
        });
        this.login.__etqBloqMayus.setVisible(true);
        this.login.__INICIA_SESION.setActionCommand("__INICIA_SESION");
        this.login.__INICIA_SESION.addActionListener(this);
        this.login.__INICIA_SESION.setMnemonic('A');
        this.login.__INICIA_SESION.addKeyListener(new java.awt.event.KeyAdapter() {
                     public void keyPressed(java.awt.event.KeyEvent evt){
                         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                            iniciasesion();
                        } 
                     }
                });
        this.login.__SALIR_SISTEMA.setMnemonic('C');
        this.login.__SALIR_SISTEMA.setActionCommand("__SALIR");
        this.login.__SALIR_SISTEMA.addActionListener(this);
        this.login.__Usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        login.__etqBloqMayus.setVisible(true);
                        a=0;
                    }
                    else {
                        login.__etqBloqMayus.setVisible(false);
                        a=1;
                    }
                }
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    login.__Pswd.requestFocus();
                }
            }
        });
        this.login.__Pswd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        login.__etqBloqMayus.setVisible(true);
                        a=0;
                    }
                    else {
                        login.__etqBloqMayus.setVisible(false);
                        a=1;
                    }
                }
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    iniciasesion();
                } 
            }
        });
        this.login.__Pswd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                String contra = login.__Pswd.getText().toString();
                login.__Pswd.setToolTipText("<html><body bgcolor=\"red\"><font color=\"white\">"+contra+"</font></body></html>");
            }
        });
        //formulario fecha
        this.fecha.__ACEPTAR.setActionCommand("__ACEPTAR_FECHA");
        this.fecha.__ACEPTAR.addActionListener(this);
        fecha.date.getDateEditor().getUiComponent().addKeyListener(new java.awt.event.KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    fechaini();
                } 
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        
        });       
        this.fecha.__CANCELAR.setActionCommand("__CANCELAR_FECHA");
        this.fecha.__CANCELAR.addActionListener(this);
        //formulario menumaster
        this.menumaster.__ALTA_PAPEL.setActionCommand("__MENU_MASTER_ALTA_PAPEL");
        this.menumaster.__ALTA_PAPEL.setMnemonic('N');
        this.menumaster.__ALTA_PAPEL.addActionListener(this);
        this.menumaster.__MOVIMIENTOS.setActionCommand("__MENU_MASTER_MOVIMIENTOS");
        this.menumaster.__MOVIMIENTOS.setMnemonic('M');
        this.menumaster.__MOVIMIENTOS.addActionListener(this);
        this.menumaster.__REPORTES.setActionCommand("__MENU_MASTER_REPORTES");
        this.menumaster.__REPORTES.setMnemonic('R');
        this.menumaster.__REPORTES.addActionListener(this);
        this.menumaster.__CONSULTAS.setActionCommand("__MENU_MASTER_CONSULTAS");
        this.menumaster.__CONSULTAS.setMnemonic('C');
        this.menumaster.__CONSULTAS.addActionListener(this);
        this.menumaster.__CANCELAR.setActionCommand("__MENU_MASTER_CANCELAR");
        this.menumaster.__CANCELAR.setMnemonic('A');
        this.menumaster.__CANCELAR.addActionListener(this);
        //formulario altapapel
        this.ap.__Archivo.setMnemonic('A');
        this.ap.__Edicion.setMnemonic('E');
        this.ap.setDefaultCloseOperation(0);
        this.ap.__SALIR.setActionCommand("__ALTAS_REGRESAR");
        this.ap.__SALIR.setMnemonic('R');
        this.ap.__SALIR.addActionListener(this);
        this.ap.__Bobina.setActionCommand("__OPCION_BOBINA");
        this.ap.__Bobina.addActionListener(this);
        this.ap.__Hoja.setActionCommand("__OPCION_HOJA");
        this.ap.__Hoja.addActionListener(this);
        this.ap.__GUARDAR.setActionCommand("__ALTAPAPEL_GUARDAR");
        this.ap.__GUARDAR.setMnemonic('G');
        this.ap.__GUARDAR.addActionListener(this);
        this.ap.__etqEliNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                String altanombre=ap.__NombrePapel.getSelectedItem().toString();
                if(ap.__NombrePapel.getSelectedIndex()==0){
                    mensaje(3,"Debe Seleccionar un Nombre de Papel");
                }else{
                    confir = mensajeConfirmacion("¿Deseas eliminar el nombre "+altanombre+"?","Confirma"); 
                    if(confir!=JOptionPane.OK_OPTION){
                        return;
                    }else{
                    int idnombrepapel = busquedaid("nombre");
                    mimodelo.bajaNombre(idnombrepapel);
                    mensaje(1,"Nombre "+ altanombre +" eliminado correctamente");
                    ap.__NombrePapel.removeItem(altanombre);
                    }
                }
                
            }    
            public void mouseEntered(java.awt.event.MouseEvent evt){
               
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                
            }
        });
        this.ap.__etqEliColor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                String foraltacolor = ap.__ColorPapel.getSelectedItem()+"";
                if( foraltacolor.equals("Selecciona...")){
                    mensaje(3,"Debe Seleccionar un Color de Papel");
                }else{
                    confir = mensajeConfirmacion("¿Deseas eliminar el color "+foraltacolor+"?","Confirma"); 
                    if(confir!=JOptionPane.OK_OPTION){
                        return;
                    }else{
                    int idcolor = busquedaid("color");
                    mimodelo.bajaColor(idcolor);
                    ap.__ColorPapel.removeItem(foraltacolor);
                mensaje(1,"Color "+ foraltacolor +" eliminado correctamente");
                    }
                }              
                
            }    
            public void mouseEntered(java.awt.event.MouseEvent evt){
               
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                
            }
        });
        this.ap.__etqEliPropiedad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                int altapropiedad = Integer.parseInt(ap.__Propiedad.getSelectedIndex()+"");
                Object propiedad=ap.__Propiedad.getSelectedItem();
                if( altapropiedad == 0){
                    mensaje(3,"Debe Seleccionar un Propiedad de Papel");
                    return;
                }else{
                    confir = mensajeConfirmacion("¿Deseas eliminar la propiedad "+propiedad+"?","Confirma"); 
                    if(confir!=JOptionPane.OK_OPTION){
                        return;
                    }else{
                    propiedad = ap.__Propiedad.getSelectedItem()+"";
                    altapropiedad = busquedaid("propiedad");
                    mimodelo.bajaPropietarios(altapropiedad);
                    ap.__Propiedad.removeItem(propiedad);
                    mensaje(1,"Propiedad "+ propiedad+" eliminada correctamente");}
                }
                
            }    
            public void mouseEntered(java.awt.event.MouseEvent evt){
               
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                
            }
        });
        this.ap.__etqEliMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                String altamarca = ap.__Marca.getSelectedItem()+"";
                
                    if( altamarca.equals("Selecciona...")){
                        mensaje(3,"Debe Seleccionar una Marca de Papel");
                    }else{
                        confir = mensajeConfirmacion("¿Deseas eliminar la marca "+altamarca+"?","Confirma"); 
                        if(confir!=JOptionPane.OK_OPTION){
                            return;
                        }else{
                        int idmarca = busquedaid("marca");
                        mimodelo.bajaMarca(idmarca);
                        ap.__Marca.removeItem(altamarca);
                        mensaje(1,"Marca "+ altamarca+" eliminada correctamente");}
                    }
                
            }    
            public void mouseEntered(java.awt.event.MouseEvent evt){
               
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                
            }
        });        
        this.ap.__etqNewColor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                nuevoColor(ap.__ColorPapel);
            }    
            public void mouseEntered(java.awt.event.MouseEvent evt){
                ap.__etqNewColor.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                ap.__etqNewColor.setFont(new java.awt.Font("Papyrus", 0, 12));
            }

            
        });
        this.ap.__etqNewNombre.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                nuevoNombre(ap.__NombrePapel);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                ap.__etqNewNombre.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                ap.__etqNewNombre.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });
        this.ap.__NewMarca.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
               nuevaMarca();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                ap.__NewMarca.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                ap.__NewMarca.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });
        
        this.ap.__etqNewPropiedad.addMouseListener(new java.awt.event.MouseAdapter(){            
            public void mouseClicked(java.awt.event.MouseEvent evt){
               nuevoPropietario();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                ap.__etqNewPropiedad.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                ap.__etqNewPropiedad.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });
        this.ap.__NombrePapel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
               try{
                   if( ap.__NombrePapel.getSelectedItem().toString().equals("Sulfatada") || ap.__NombrePapel.getSelectedItem().toString().equals("Polypap")||                           ap.__NombrePapel.getSelectedItem().toString().equals("Cartulina Bristol")||                           ap.__NombrePapel.getSelectedItem().toString().equals("Adhesivos Couche")){
                       ap.__GrPts.setSelectedIndex(1);
                   }else{
                       ap.__GrPts.setSelectedIndex(0);
                   }
               }catch(Exception e){
                   
               }
            }
        });        
        this.ap.__Alto_.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){}
        });
        this.ap.__Ancho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){}
        });
        this.ap.__Gramaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
                
            }
        });
        //--menus--/    
        this.ap.__menucerrarsesiones.setActionCommand("__MENU_CERRAR_SESIONES");
        this.ap.__menucerrarsesiones.addActionListener(this);
        this.ap.__menuMovimientosPapel.setActionCommand("__MENU_MOV_PAPEL");
        this.ap.__menuMovimientosPapel.addActionListener(this);
        this.ap.__menuMovimientosPapel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,InputEvent.CTRL_MASK));
        this.ap.__menuAnterior.setActionCommand("__MENU_ANTERIOR");
        this.ap.__menuAnterior.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
        this.ap.__menuAnterior.addActionListener(this);
        this.ap.__menuConsultas.setActionCommand("__MENU_CONSULTAS");
        this.ap.__menuConsultas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));
        this.ap.__menuConsultas.addActionListener(this);
        this.ap.__menuReportes.setActionCommand("__MENU_REPORTES");
        this.ap.__menuReportes.addActionListener(this);
        this.ap.__MenuAbrirArchivo.setActionCommand("__MENU_ABRIR_ARCHIVO");
        this.ap.__MenuAbrirArchivo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));
        this.ap.__MenuAbrirArchivo.addActionListener(this);
        this.ap.__menuCerrarSesion.setActionCommand("__MENU_CERRAR_SESION");
        this.ap.__menuCerrarSesion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.CTRL_MASK));
        this.ap.__menuCerrarSesion.addActionListener(this);
        this.ap.__menuSalir.setActionCommand("__MENU_SALIR");
        this.ap.__menuSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.SHIFT_MASK |InputEvent.CTRL_MASK));
        this.ap.__menuSalir.addActionListener(this);
        this.ap.__menuCambiarFecha.setActionCommand("__MENU_FECHA");
        this.ap.__menuCambiarFecha.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
        this.ap.__menuCambiarFecha.addActionListener(this);
        this.ap.__menuNuevoUsuario.setActionCommand("__MENU_NEWUSER");
        this.ap.__menuNuevoUsuario.addActionListener(this);
        this.ap.__menuCambiarContraseña.setActionCommand("__MENU_CONTRASEÑA");
        this.ap.__menuCambiarContraseña.addActionListener(this);
        this.ap.__menuReporte.setActionCommand("__MENU_REPORTE_USUARIOS");
        this.ap.__menuReporte.addActionListener(this);
        this.ap.__menuBajaPapel.setActionCommand("__MENU_BAJA_PAPEL");
        this.ap.__menuBajaPapel.addActionListener(this);
        this.ap.__menuAcerca.setActionCommand("__MENU_ACERCADE");
        this.ap.__menuAcerca.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10,InputEvent.CTRL_MASK));
        this.ap.__menuAcerca.addActionListener(this);  
        this.ap.__menuBackup.setActionCommand("__MENU_BACKUP");
        this.ap.__menuBackup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_MASK));
        this.ap.__menuBackup.addActionListener(this);
         //--MENU MOVIMIENTO
        this.movimientos.__menucerrarsesiones.setActionCommand("__MENU_CERRAR_SESIONES");
        this.movimientos.__menucerrarsesiones.addActionListener(this);
        this.movimientos.__menuRegresar.setActionCommand("__MENU_ANTERIOR");
        this.movimientos.__menuRegresar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
        this.movimientos.__menuRegresar.addActionListener(this);
        this.movimientos.__menuAltaPapel.setActionCommand("__MENU_ALTA");
        this.movimientos.__menuAltaPapel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
        this.movimientos.__menuAltaPapel.addActionListener(this);
        this.movimientos.__menuconsulta.setActionCommand("__MENU_CONSULTAS");
        this.movimientos.__menuconsulta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));
        this.movimientos.__menuconsulta.addActionListener(this);
        this.movimientos.__menuGeneraReporte.setActionCommand("__MENU_REPORTES");
        this.movimientos.__menuGeneraReporte.addActionListener(this);
        this.movimientos.__MenuAbrirArchivo.setActionCommand("__MENU_ABRIR_ARCHIVO");
        this.movimientos.__MenuAbrirArchivo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));
        this.movimientos.__MenuAbrirArchivo.addActionListener(this);
        this.movimientos.__CerrarSesion.setActionCommand("__MENU_CERRAR_SESION");
        this.movimientos.__CerrarSesion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.CTRL_MASK));
        this.movimientos.__CerrarSesion.addActionListener(this);
        this.movimientos.__menuSalirSistema.setActionCommand("__MENU_SALIR");
        this.movimientos.__menuSalirSistema.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.SHIFT_MASK |InputEvent.CTRL_MASK));
        this.movimientos.__menuSalirSistema.addActionListener(this);
        this.movimientos.__menuEdiCambiarFecha.setActionCommand("__MENU_FECHA");
        this.movimientos.__menuEdiCambiarFecha.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
        this.movimientos.__menuEdiCambiarFecha.addActionListener(this);
        this.movimientos.__menuEdiNewUsuario.setActionCommand("__MENU_NEWUSER");
        this.movimientos.__menuEdiNewUsuario.addActionListener(this);
        this.movimientos.__menuEdiCambiarContraseña.setActionCommand("__MENU_CONTRASEÑA");
        this.movimientos.__menuEdiCambiarContraseña.addActionListener(this);
        this.movimientos.__menuEdiReporteUsuario.setActionCommand("__MENU_REPORTE_USUARIOS");
        this.movimientos.__menuEdiReporteUsuario.addActionListener(this);
        this.movimientos.__menuEdiAcerca.setActionCommand("__MENU_ACERCADE");
        this.movimientos.__menuEdiAcerca.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10,InputEvent.CTRL_MASK));
        this.movimientos.__menuEdiAcerca.addActionListener(this);
        this.movimientos.__menuBackup.setActionCommand("__MENU_BACKUP");
        this.movimientos.__menuBackup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_MASK));
        this.movimientos.__menuBackup.addActionListener(this);
        this.movimientos.__menuUbicacion.setActionCommand("__MENU_UBICACION");
        this.movimientos.__menuUbicacion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,InputEvent.CTRL_MASK));
        this.movimientos.__menuUbicacion.addActionListener(this);
        this.movimientos.__menutraspaso.setActionCommand("__MENU_MOV_TRASPASO");
        this.movimientos.__menutraspaso.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,InputEvent.CTRL_MASK));
        this.movimientos.__menutraspaso.addActionListener(this);
        JButton btnEliRegistroB = new JButton ("Elimina");
        btnEliRegistroB.setActionCommand("__ELIMINA_REGISTRO");
        btnEliRegistroB.addActionListener(this);
        //--MENU CONSULTA   
        this.consultas.__menucerrarsesiones.setActionCommand("__MENU_CERRAR_SESIONES");
        this.consultas.__menucerrarsesiones.addActionListener(this);
        this.consultas.__Archivo.setMnemonic('R');
        this.consultas.__Edicion.setMnemonic('E');
        this.consultas.__menuMovimientosPapel.setActionCommand("__MENU_MOV_PAPEL");
        this.consultas.__menuMovimientosPapel.addActionListener(this);
        this.consultas.__menuRegresar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,InputEvent.CTRL_MASK));
        this.consultas.__menuRegresar.setActionCommand("__MENU_ANTERIOR");
        this.consultas.__menuRegresar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
        this.consultas.__menuRegresar.addActionListener(this);
        this.consultas.__menuAltaPapel.setActionCommand("__MENU_ALTA");
        this.consultas.__menuAltaPapel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
        this.consultas.__menuAltaPapel.addActionListener(this);     
        this.consultas.__menuGeneraReporte.setActionCommand("__MENU_REPORTES");
        this.consultas.__menuGeneraReporte.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.CTRL_MASK));
        this.consultas.__menuGeneraReporte.addActionListener(this);  
        this.consultas.__MenuAbrirArchivo3.setActionCommand("__MENU_ABRIR_ARCHIVO");
        this.consultas.__MenuAbrirArchivo3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));
        this.consultas.__MenuAbrirArchivo3.addActionListener(this);
        this.consultas.__CerrarSesion.setActionCommand("__MENU_CERRAR_SESION");
        this.consultas.__CerrarSesion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.CTRL_MASK));
        this.consultas.__CerrarSesion.addActionListener(this);
        this.consultas.__menuSalirSistema.setActionCommand("__MENU_SALIR");
        this.consultas.__menuSalirSistema.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.SHIFT_MASK |InputEvent.CTRL_MASK));
        this.consultas.__menuSalirSistema.addActionListener(this);
        this.consultas.__menuEdiCambiarFecha.setActionCommand("__MENU_FECHA");
        this.consultas.__menuEdiCambiarFecha.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
        this.consultas.__menuEdiCambiarFecha.addActionListener(this);
        this.consultas.__menuEdiNewUsuario.setActionCommand("__MENU_NEWUSER");
        this.consultas.__menuEdiNewUsuario.addActionListener(this);
        this.consultas.__menuEdiCambiarContraseña.setActionCommand("__MENU_CONTRASEÑA");
        this.consultas.__menuEdiCambiarContraseña.addActionListener(this);
        this.consultas.__menuEdiReporteUsuario.setActionCommand("__MENU_REPORTE_USUARIOS");
        this.consultas.__menuEdiReporteUsuario.addActionListener(this);
        this.consultas.__menuEdiAcerca.setActionCommand("__MENU_ACERCADE");
        this.consultas.__menuEdiAcerca.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10,InputEvent.CTRL_MASK));
        this.consultas.__menuEdiAcerca.addActionListener(this);
        this.consultas.__menuBackup.setActionCommand("__MENU_BACKUP");
        this.consultas.__menuBackup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_MASK));
        this.consultas.__menuBackup.addActionListener(this);
        this.consultas.__Bobina.setActionCommand("__CONSULTAS");
        this.consultas.__Bobina.addActionListener(this);
        this.consultas.__Hoja.setActionCommand("__CONSULTAS");
        this.consultas.__Hoja.addActionListener(this);
        this.consultas.__optEntrada.setActionCommand("__CONSULTAS");
        this.consultas.__optEntrada.addActionListener(this);
        this.consultas.__optSalida.setActionCommand("__CONSULTAS");
        this.consultas.__optSalida.addActionListener(this);
        this.consultas.__PapelNingun.setActionCommand("__CONSULTAS");
        this.consultas.__PapelNingun.addActionListener(this);
        this.consultas.__optNinguno.setActionCommand("__CONSULTAS");
        this.consultas.__optNinguno.addActionListener(this);
        this.consultas.__ACEPTARCONSULTA.setActionCommand("__ACEPTAR_CONSULTA");
        this.consultas.__ACEPTARCONSULTA.setMnemonic('A');
        this.consultas.__ACEPTARCONSULTA.addActionListener(this);
        this.consultas.__SALIRCONSULTA.setActionCommand("__SALIR_CONSULTA");
        this.consultas.__SALIRCONSULTA.setMnemonic('C');
        this.consultas.__SALIRCONSULTA.addActionListener(this);
        final TextAutoCompleter claveConsulta = new TextAutoCompleter( consultas.__clave );
        claveConsulta.setMode(0); // infijo
        final TextAutoCompleter ancho = new TextAutoCompleter( consultas.__ancho );
        ancho.setMode(0); // infijo
        final TextAutoCompleter alto = new TextAutoCompleter( consultas.__alto );
        alto.setMode(0); // infijo
        final TextAutoCompleter folio = new TextAutoCompleter( consultas.__folio );
        folio.setMode(0); // infijo
        final TextAutoCompleter foliohasta = new TextAutoCompleter( consultas.__foliohasta );
        foliohasta.setMode(0); // infijo
        this.consultas.__clave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumGuion(evt);                
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
                try {
                    String parametro = consultas.__clave.getText();
                    if(parametro!=null){
                        ResultSet buscaClavePapel=null;
                        if(consultas.__Bobina.isSelected()){
                            buscaClavePapel = mimodelo.vw_buscaClaveBobina(parametro);
                        }
                        if(consultas.__Hoja.isSelected()){
                            buscaClavePapel = mimodelo.vw_buscaClaveHoja(parametro);
                        }
                        claveConsulta.removeAll();
                        while(buscaClavePapel.next()){
                            claveConsulta.addItem(buscaClavePapel.getString(1));
                        }
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
            }
        });
        this.consultas.__ancho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
                try {
                    String parametro = consultas.__ancho.getText();
                    ResultSet buscaAncho=null;
                    if(consultas.__Bobina.isSelected()){
                        buscaAncho = mimodelo.vw_buscaAnchoBobina(parametro);
                    }
                    if(consultas.__Hoja.isSelected()){
                        buscaAncho = mimodelo.vw_buscaAnchoHoja(parametro);
                    }
                    ancho.removeAll();
                    while(buscaAncho.next()){
                        ancho.addItem(buscaAncho.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
            }
        });
        this.consultas.__alto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
                try {
                    String parametro = consultas.__alto.getText();
                    ResultSet buscaAlto=null;
                    if(consultas.__Hoja.isSelected()){
                        buscaAlto = mimodelo.vw_buscaAltoHoja(parametro);
                    }
                    alto.removeAll();
                    while(buscaAlto.next()){
                        alto.addItem(buscaAlto.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
            }
        });
        this.consultas.__grspts.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
        });
        this.consultas.__kilohojas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
        });
        this.consultas.__bobinapaq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
        });
        this.consultas.__folio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumGuion(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
                try {
                    String parametro = consultas.__folio.getText();
                    ResultSet buscaFolio=null;
                    if(consultas.__Hoja.isSelected()){
                        buscaFolio = mimodelo.vw_buscaFolioHoja(parametro);
                    } else
                    if(consultas.__Bobina.isSelected()){
                        buscaFolio = mimodelo.vw_buscaFolioBobina(parametro);
                    } else{
                        mensaje(3,"Selecciona tipo papel");
                        return;
                    }
                    folio.removeAll();
                    while(buscaFolio.next()){
                        folio.addItem(buscaFolio.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
            }
        });        
        this.consultas.__foliohasta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumGuion(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
                try {
                    String parametro = consultas.__foliohasta.getText();
                    ResultSet buscaFolio=null;
                    if(consultas.__Hoja.isSelected()){
                        buscaFolio = mimodelo.vw_buscaFolioHoja(parametro);
                    }
                    if(consultas.__Bobina.isSelected()){
                        buscaFolio = mimodelo.vw_buscaFolioBobina(parametro);
                    }
                    foliohasta.removeAll();
                    while(buscaFolio.next()){
                        foliohasta.addItem(buscaFolio.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
            }
        });
        this.consultas.__documento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            }
        });
        this.consultas.__OrdenP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumGuion(evt);
            }
        });
        this.consultas.__OrdenC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumGuion(evt);
            }
        });
        //etiquetas con los check
        this.consultas.__chkClavePapel.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkClavePapel.isSelected()== true){
                        consultas.__chkClavePapel.setSelected(false);
                    }else
                        consultas.__chkClavePapel.setSelected(true);
                }
            }
        });
        this.consultas.__chkNombrePapel.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkNombrePapel.isSelected()== true){
                        consultas.__chkNombrePapel.setSelected(false);
                    }else
                        consultas.__chkNombrePapel.setSelected(true);
                }
            }
        });
        this.consultas.__chkAncho.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkAncho.isSelected()== true){
                        consultas.__chkAncho.setSelected(false);
                    }else
                        consultas.__chkAncho.setSelected(true);
                }
            }
        });
        this.consultas.__chkAlto.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkAlto.isSelected()== true){
                        consultas.__chkAlto.setSelected(false);
                    }else
                        consultas.__chkAlto.setSelected(true);
                }
            }
        });
        this.consultas.__chkGrsPts.addKeyListener(new java.awt.event.KeyAdapter(){
                public void keyPressed(java.awt.event.KeyEvent evt){
                    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                        if(consultas.__chkGrsPts.isSelected()== true){
                            consultas.__chkGrsPts.setSelected(false);
                        }else
                            consultas.__chkGrsPts.setSelected(true);
                        }
                }
        });
        this.consultas.__chkColor.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkColor.isSelected()== true){
                        consultas.__chkColor.setSelected(false);
                    }else
                        consultas.__chkColor.setSelected(true);
                }
            }
        });
        this.consultas.__chkMarca.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkMarca.isSelected()== true){
                        consultas.__chkMarca.setSelected(false);
                    }else
                        consultas.__chkMarca.setSelected(true);
                }
            }
        });
        this.consultas.__chkFechaIni.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkFechaIni.isSelected()== true){
                        consultas.__chkFechaIni.setSelected(false);
                    }else
                        consultas.__chkFechaIni.setSelected(true);
                }
            }
        });
        this.consultas.__chkPropietario.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkPropietario.isSelected()== true){
                        consultas.__chkPropietario.setSelected(false);
                    }else
                        consultas.__chkPropietario.setSelected(true);
                }
            }
        });
        this.consultas.__chkKiloHoja.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkKiloHoja.isSelected()== true){
                        consultas.__chkKiloHoja.setSelected(false);
                    }else
                        consultas.__chkKiloHoja.setSelected(true);
                }
            }
        });
        this.consultas.__chkBobinaPaq.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkBobinaPaq.isSelected()== true){
                        consultas.__chkBobinaPaq.setSelected(false);
                    }else
                        consultas.__chkBobinaPaq.setSelected(true);
                }
            }
        });
        this.consultas.__chkFolio.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkFolio.isSelected()== true){
                        consultas.__chkFolio.setSelected(false);
                    }else
                        consultas.__chkFolio.setSelected(true);
                }
            }
        });
        this.consultas.__chkDocumento.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkDocumento.isSelected()== true){
                        consultas.__chkDocumento.setSelected(false);
                    }else
                        consultas.__chkDocumento.setSelected(true);
                }
            }
        });
        this.consultas.__chkOrdenP.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkOrdenP.isSelected()== true){
                        consultas.__chkOrdenP.setSelected(false);
                    }else
                        consultas.__chkOrdenP.setSelected(true);
                }
            }
        });
        this.consultas.__chkOrdenC.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkOrdenC.isSelected()== true){
                        consultas.__chkOrdenC.setSelected(false);
                    }else
                        consultas.__chkOrdenC.setSelected(true);
                }
            }
        });
        this.consultas.__chkFechaFin.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkFechaFin.isSelected()== true){
                        consultas.__chkFechaFin.setSelected(false);
                    }else
                        consultas.__chkFechaFin.setSelected(true);
                }
            }
        });
        this.consultas.__chkProveedor.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkProveedor.isSelected()== true){
                        consultas.__chkProveedor.setSelected(false);
                    }else
                        consultas.__chkProveedor.setSelected(true);
                }
            }
        });
        this.consultas.__chkTipoEntrada.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkTipoEntrada.isSelected()== true){
                        consultas.__chkTipoEntrada.setSelected(false);
                    }else
                        consultas.__chkTipoEntrada.setSelected(true);
                }
            }
        });
        this.consultas.__chkTipoSalida.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkTipoSalida.isSelected()== true){
                        consultas.__chkTipoSalida.setSelected(false);
                    }else
                        consultas.__chkTipoSalida.setSelected(true);
                }
            }
        });
        this.consultas.__chkTurno1.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkTurno1.isSelected()== true){
                        consultas.__chkTurno1.setSelected(false);
                    }else
                        consultas.__chkTurno1.setSelected(true);
                }
            }
        });
        this.consultas.__chkTurno2.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkTurno2.isSelected()== true){
                        consultas.__chkTurno2.setSelected(false);
                    }else
                        consultas.__chkTurno2.setSelected(true);
                }
            }
        });
        this.consultas.__chkTurno3.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(consultas.__chkTurno3.isSelected()== true){
                        consultas.__chkTurno3.setSelected(false);
                    }else
                        consultas.__chkTurno3.setSelected(true);
                }
            }
        });
        //--MENU INVENTARIO
        this.reportes.__menucerrarsesiones.setActionCommand("__MENU_CERRAR_SESIONES");
        this.reportes.__menucerrarsesiones.addActionListener(this);
        this.reportes.__Archivo.setMnemonic('R');
        this.reportes.__Edicion.setMnemonic('E');
        this.reportes.__menuMovimientosPapel.setActionCommand("__MENU_MOV_PAPEL");          
        this.reportes.__menuMovimientosPapel.addActionListener(this);
        this.reportes.__menuRegresar.setActionCommand("__MENU_ANTERIOR");          
        this.reportes.__menuRegresar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
        this.reportes.__menuRegresar.addActionListener(this);
        this.reportes.__menuAltaPapel.setActionCommand("__MENU_ALTA");          
        this.reportes.__menuAltaPapel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
        this.reportes.__menuAltaPapel.addActionListener(this);
        this.reportes.__menuconsulta.setActionCommand("__MENU_CONSULTAS");          
        this.reportes.__menuconsulta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));
        this.reportes.__menuconsulta.addActionListener(this);
        this.reportes.__MenuAbrirArchivo.setActionCommand("__MENU_ABRIR_ARCHIVO");
        this.reportes.__MenuAbrirArchivo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));
        this.reportes.__MenuAbrirArchivo.addActionListener(this);
        this.reportes.__CerrarSesion.setActionCommand("__MENU_CERRAR_SESION");          
        this.reportes.__CerrarSesion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.CTRL_MASK));
        this.reportes.__CerrarSesion.addActionListener(this);
        this.reportes.__menuSalirSistema.setActionCommand("__MENU_SALIR");          
        this.reportes.__menuSalirSistema.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.SHIFT_MASK |InputEvent.CTRL_MASK));
        this.reportes.__menuSalirSistema.addActionListener(this);
        this.reportes.__menuEdiCambiarFecha.setActionCommand("__MENU_FECHA");          
        this.reportes.__menuEdiCambiarFecha.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
        this.reportes.__menuEdiCambiarFecha.addActionListener(this);
        this.reportes.__menuEdiNewUsuario.setActionCommand("__MENU_NEWUSER");          
        this.reportes.__menuEdiNewUsuario.addActionListener(this);
        this.reportes.__menuEdiCambiarContraseña.setActionCommand("__MENU_CONTRASEÑA");          
        this.reportes.__menuEdiCambiarContraseña.addActionListener(this);
        this.reportes.__menuEdiReporteUsuario.setActionCommand("__MENU_REPORTE_USUARIOS");          
        this.reportes.__menuEdiReporteUsuario.addActionListener(this);
        this.reportes.__menuBackup.setActionCommand("__MENU_BACKUP");
        this.reportes.__menuBackup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_MASK));
        this.reportes.__menuBackup.addActionListener(this);
        this.reportes.__menuDatos.setActionCommand("__MENU_DATOS");
        this.reportes.__menuDatos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,InputEvent.CTRL_MASK));
        this.reportes.__menuDatos.addActionListener(this);       
        this.reportes.__menuEdiAcerca.setActionCommand("__MENU_ACERCADE");          
        this.reportes.__menuEdiAcerca.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10,InputEvent.CTRL_MASK));
        this.reportes.__menuEdiAcerca.addActionListener(this);          
        this.reportes.__RBobina.setActionCommand("__RBOBINA");
        this.reportes.__RBobina.setMnemonic('B');
        this.reportes.__RBobina.addActionListener(this);
        this.reportes.__RHojas.setActionCommand("__RHOJA");
        this.reportes.__RHojas.setMnemonic('H');
        this.reportes.__RHojas.addActionListener(this);
        this.reportes.__REntradaH.setActionCommand("__ENTRADASH");
        this.reportes.__REntradaH.setMnemonic('O');
        this.reportes.__REntradaH.addActionListener(this);
        this.reportes.__REntradaB.setActionCommand("__ENTRADASB");
        this.reportes.__REntradaB.setMnemonic('I');
        this.reportes.__REntradaB.addActionListener(this);
        this.reportes.__RSalidaH.setActionCommand("__SALIDASH");
        this.reportes.__RSalidaH.setMnemonic('S');
        this.reportes.__RSalidaH.addActionListener(this);
        this.reportes.__RSalidaB.setActionCommand("__SALIDASB");
        this.reportes.__RSalidaB.setMnemonic('A');
        this.reportes.__RSalidaB.addActionListener(this);  
        this.reportes.__SALIR.setActionCommand("__SALIR_REPORTE");
        this.reportes.__SALIR.setMnemonic('R');
        this.reportes.__SALIR.addActionListener(this);
        this.reportes.__OP.setActionCommand("__ORDENPRODUCCION");
        this.reportes.__OP.setMnemonic('P');
        this.reportes.__OP.addActionListener(this);
        this.reportes.__OPHojas.setActionCommand("__ORDENPRODUCCIONH");
        this.reportes.__OPHojas.setMnemonic('P');
        this.reportes.__OPHojas.addActionListener(this);
        this.reportes.__TraspasoBH.setActionCommand("__Traspaso");
        this.reportes.__TraspasoBH.setMnemonic('T');
        this.reportes.__TraspasoBH.addActionListener(this);
        this.reportes.__RSalidaG.setActionCommand("__SALIDAG");
        this.reportes.__RSalidaG.setMnemonic('D');
        this.reportes.__RSalidaG.addActionListener(this);
        this.reportes.__RFinanzas.setActionCommand("__RFINANZA");
        this.reportes.__RFinanzas.setMnemonic('R');
        this.reportes.__RFinanzas.addActionListener(this);
        this.reportes.__CONSUMOTOTALOP.setActionCommand("__CONSUMOOP");
        this.reportes.__CONSUMOTOTALOP.setMnemonic('C');
        this.reportes.__CONSUMOTOTALOP.addActionListener(this);        
        //ReFinanzas
        this.Finanzas.__ACEPTAR.setActionCommand("__ACEPTAR_FINANZAS");
        this.Finanzas.__ACEPTAR.setMnemonic('A');
        this.Finanzas.__ACEPTAR.addActionListener(this);
        this.Finanzas.__SALIR.setActionCommand("__CANCELAR_FINANZAS");
        this.Finanzas.__SALIR.setMnemonic('C');
        this.Finanzas.__SALIR.addActionListener(this);
        final TextAutoCompleter clavePapel = new TextAutoCompleter(Finanzas.__clave );
        clavePapel.setMode(0);//infijo
        this.Finanzas.__clave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNum(evt);               
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){                        
                        a=0;
                    }else{
                        a=1;
                    }                   
                } 
               
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = Finanzas.__clave.getText();
                    ResultSet buscarClave = mimodelo.vw_buscaClaveTodo(parametro);
                    clavePapel.removeAll();
                    while(buscarClave.next()){
                        clavePapel.addItem(buscarClave.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
                    
        }); 
        // REPORTE TRASPASO         
        this.retras.__ACEPTAR.setActionCommand("__ACEPTAR_TRASPASORE");
        this.retras.__ACEPTAR.setMnemonic('A');
        this.retras.__ACEPTAR.addActionListener(this);
        this.retras.__SALIR.setActionCommand("__CANCELAR_TRASPASORE");
        this.retras.__SALIR.setMnemonic('C');
        this.retras.__SALIR.addActionListener(this);        
        final TextAutoCompleter foliotras = new TextAutoCompleter(retras.__foliotras );
        foliotras.setMode(0);//infijo
        this.retras.__foliotras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNum(evt);               
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){                        
                        a=0;
                    }else{
                        a=1;
                    }                   
                } 
               
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = retras.__foliotras.getText();
                    ResultSet buscarfoliotras = mimodelo.buscafoliotrasp(parametro);
                    foliotras.removeAll();
                    while(buscarfoliotras.next()){
                        foliotras.addItem(buscarfoliotras.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
                    
        });         
        final TextAutoCompleter foliotrasi = new TextAutoCompleter(retras.__foliotrashasta);
        foliotrasi.setMode(0);//infijo
        this.retras.__foliotrashasta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNum(evt);               
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){                        
                        a=0;
                    }else{
                        a=1;
                    }                   
                } 
               
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = retras.__foliotrashasta.getText();
                    ResultSet buscarfoliotras = mimodelo.buscafoliotrasp(parametro);
                    foliotrasi.removeAll();
                    while(buscarfoliotras.next()){
                        foliotrasi.addItem(buscarfoliotras.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
                    
        }); 
        //ReporteConsumototal
        this.Consumo.__ACEPTAR.setActionCommand("__ACEPTAR_CONSUMO");
        this.Consumo.__ACEPTAR.setMnemonic('A');
        this.Consumo.__ACEPTAR.addActionListener(this);
        this.Consumo.__SALIR.setActionCommand("__CANCELAR_CONSUMO");
        this.Consumo.__SALIR.setMnemonic('C');
        this.Consumo.__SALIR.addActionListener(this); 
        final TextAutoCompleter opdesde = new TextAutoCompleter(Consumo.OPDesde );
        opdesde.setMode(0); // infijo
        this.Consumo.OPDesde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        a=0;
                    }else{
                        a=1;
                    }                   
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
             try {
                    String parametro = Consumo.OPDesde.getText();
                    ResultSet buscaOP = mimodelo.buscaOP(parametro);
                    opdesde.removeAll();
                    while(buscaOP.next()){
                        opdesde.addItem(buscaOP.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }      
        });
        final TextAutoCompleter ophasta = new TextAutoCompleter(Consumo.OPHasta );
        ophasta.setMode(0); // infijo
        this.Consumo.OPDesde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        a=0;
                    }else{
                        a=1;
                    }                   
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
             try {
                    String parametro = Consumo.OPHasta.getText();
                    ResultSet buscaOP = mimodelo.buscaOP(parametro);
                    ophasta.removeAll();
                    while(buscaOP.next()){
                        ophasta.addItem(buscaOP.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }      
        });
        // Cambiar Ubicacion
        this.ubi.__ACEPTAR.setActionCommand("__ACEPTAR_UBICACION");
        this.ubi.__ACEPTAR.setMnemonic('A');
        this.ubi.__ACEPTAR.addActionListener(this);
        this.ubi.__SALIR.setActionCommand("__CANCELAR_UBICACION");
        this.ubi.__SALIR.setMnemonic('C');
        this.ubi.__SALIR.addActionListener(this);
        final TextAutoCompleter clavePapelubi = new TextAutoCompleter(ubi.__clave );
        clavePapelubi.setMode(0);//infijo
        this.ubi.__clave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumGuion(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){                        
                        a=0;
                    }else{
                        a=1;
                    }                   
                } 
               
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = ubi.__clave.getText();
                    ResultSet buscarClave = mimodelo.vw_buscaClaveTodoExist(parametro);
                    clavePapelubi.removeAll();
                    while(buscarClave.next()){
                        clavePapelubi.addItem(buscarClave.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
                    
        });  
        this.ubi.__Ubica.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            }
        });
        this.ubi.__Ubica.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                String clave = ubi.__clave.getText().toString();
                Object ubica=null;
                if(!clave.isEmpty()){
                    try {
                        ResultSet ubic = mimodelo.buscarUbicacion(clave);
                        while (ubic.next()){
                            ubica = ubic.getObject("ubicacion");
                        }
                        if(ubica!=null){
                            ubi.__Ubica.setText(ubica.toString());
                        }else{
                            ubi.__Ubica.setText("");
                        }
                    } catch (SQLException ex) {
                        
                    }
                }else{
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                
            }
        });
        //Baja de Papel
        this.bajaP.__GUARDAR.setActionCommand("__ACEPTAR_BAJA");
        this.bajaP.__GUARDAR.setMnemonic('A');
        this.bajaP.__GUARDAR.addActionListener(this);
        this.bajaP.__SALIR.setActionCommand("__CANCELAR_BAJA");
        this.bajaP.__SALIR.setMnemonic('C');
        this.bajaP.__SALIR.addActionListener(this);
        bajapapel = new TextAutoCompleter( bajaP.__ClavePapelBaja );
                bajapapel.setMode(0); // infijo
                try {
                    String parametro = bajaP.__ClavePapelBaja.getText();
                    if(parametro!=null){
                        ResultSet buscaClavePapel=null;
                        buscaClavePapel = mimodelo.buscaClave(parametro);
                        bajapapel.removeAll();
                        while(buscaClavePapel.next()){
                            bajapapel.addItem(buscaClavePapel.getString(1));
                        }
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }       
        //movimientos Papel
        this.movimientos.__ACEPTARENTRADA.setActionCommand("__ACEPTAR_MOVIMIENTOS_ENTRADA");
        this.movimientos.__ACEPTARENTRADA.setMnemonic('A');
        this.movimientos.__ACEPTARENTRADA.addActionListener(this);
        this.movimientos.__MODIFICACIONENTRADA.setActionCommand("__MODIFICACIONENTRADA");
        this.movimientos.__MODIFICACIONENTRADA.setMnemonic('M');
        this.movimientos.__MODIFICACIONENTRADA.addActionListener(this);
        this.movimientos.__ACEPTARSALIDA.setActionCommand("__ACEPTAR_MOVIMIENTOS_SALIDA");
        this.movimientos.__ACEPTARSALIDA.setMnemonic('A');
        this.movimientos.__ACEPTARSALIDA.addActionListener(this);
        this.movimientos.__MODIFICACIONSALIDA.setActionCommand("__MODIFICACIONSALIDA");
        this.movimientos.__MODIFICACIONSALIDA.setMnemonic('M');
        this.movimientos.__MODIFICACIONSALIDA.addActionListener(this);
        this.movimientos.__MODIFICACIONH.setActionCommand("__MODIFICACIONSALHOJ");
        this.movimientos.__MODIFICACIONH.setMnemonic('M');
        this.movimientos.__MODIFICACIONH.addActionListener(this);
        this.movimientos.__MODIFICACIONB.setActionCommand("__MODIFICACIONSALBOB");
        this.movimientos.__MODIFICACIONB.setMnemonic('M');
        this.movimientos.__MODIFICACIONB.addActionListener(this);
        this.movimientos.__ACEPTARSALIDAH.setActionCommand("__ACEPTAR_MOVIMIENTOS_SALIDAH");
        this.movimientos.__ACEPTARSALIDAH.setMnemonic('A');
        this.movimientos.__ACEPTARSALIDAH.addActionListener(this);
        this.movimientos.__ACEPTARSALIDAB.setActionCommand("__ACEPTAR_MOVIMIENTOS_SALIDAB");
        
        this.movimientos.__ACEPTARSALIDAB.setMnemonic('A');
        this.movimientos.__Archivo.setMnemonic('R');        
        this.movimientos.__Edicion.setMnemonic('E');
        this.movimientos.__ACEPTARSALIDAB.addActionListener(this);
        this.movimientos.__OrdenCompraEntr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                String texto = movimientos.__OrdenCompraEntr.getText().toString();
                String[] partido = texto.split("-");
                if(partido.length>2){
                    return;
                }
                for(int i=0;i<partido.length;i++){
                    partido[i]=partido[i].replace(" ", "");
                }
            }
        });
        //
        final TextAutoCompleter tipoEntrada = new TextAutoCompleter(movimientos.__TipoEntrada );
        tipoEntrada.setMode(0);//infijo
        this.movimientos.__TipoEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
                
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){                        
                        a=0;
                    }else{
                        a=1;
                    }                   
                } 
               
            }
            
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = movimientos.__TipoEntrada.getText();
                    ResultSet buscarTipoEntrada = mimodelo.buscaTipoEntrada(parametro,true);
                    tipoEntrada.removeAll();
                   
                    while(buscarTipoEntrada.next()){
                        tipoEntrada.addItem(buscarTipoEntrada.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
                    
        });         
        //Revisar el autcocomplementar
        final TextAutoCompleter tipoSalida = new TextAutoCompleter(movimientos.__TipoSalida );
        tipoSalida.setMode(0);//infijo
        this.movimientos.__TipoSalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
                
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        a=0;
                    }else{
                        a=1;
                    }                   
                } 
                
            }
            
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = movimientos.__TipoSalida.getText();
                    ResultSet buscarTipoSalida = mimodelo.buscaTipoSalida(parametro, true);
                    tipoSalida.removeAll();
                    while(buscarTipoSalida.next()){
                        tipoSalida.addItem(buscarTipoSalida.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
                    
        });
        //
        final TextAutoCompleter tipoSalidaH = new TextAutoCompleter(movimientos.__TipodeSalidaH );
        tipoSalidaH.setMode(0);//infijo
        this.movimientos.__TipodeSalidaH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){}
            
            public void keyReleased(java.awt.event.KeyEvent evt){
                try {
                    String parametro = movimientos.__TipodeSalidaH.getText();
                    ResultSet buscarTipoSalida = mimodelo.buscaTipoSalida(parametro,true);
                    tipoSalidaH.removeAll();
                    while(buscarTipoSalida.next()){
                        tipoSalidaH.addItem(buscarTipoSalida.getString(2));
                    }
                }catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
                
             }
                    
        }); 
        final TextAutoCompleter tipoSalidaB = new TextAutoCompleter(movimientos.__TipodeSalidaB );
        tipoSalidaB.setMode(0);//infijo
        this.movimientos.__TipodeSalidaB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
            }
            
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = movimientos.__TipodeSalidaB.getText();
                    ResultSet buscarTipoSalida = mimodelo.buscaTipoSalida(parametro,true);
                    tipoSalidaB.removeAll();
                    while(buscarTipoSalida.next()){
                        tipoSalidaB.addItem(buscarTipoSalida.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
                
             }
                    
        }); 
        final TextAutoCompleter maq = new TextAutoCompleter(movimientos.__MaquinaSalidaH );
        maq.setMode(0);//infijo
        this.movimientos.__MaquinaSalidaH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){}
            
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = movimientos.__MaquinaSalidaH.getText();
                    ResultSet buscarMarca = mimodelo.buscarMaquinas(parametro,true);
                    maq.removeAll();
                    while(buscarMarca.next()){
                        maq.addItem(buscarMarca.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
                
             }
                    
        }); 
        final TextAutoCompleter maqB = new TextAutoCompleter(movimientos.__MaquinaSalidaB );
        maqB.setMode(0);//infijo
        this.movimientos.__MaquinaSalidaB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){}
            
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = movimientos.__MaquinaSalidaB.getText();
                    ResultSet buscarMarca = mimodelo.buscarMaquinas(parametro,true);
                    maqB.removeAll();
                    while(buscarMarca.next()){
                        maqB.addItem(buscarMarca.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
                
             }
                    
        }); 
        Com_propietarioEntr = new TextAutoCompleter(movimientos.__PropietarioEntr );
        Com_propietarioEntr.setMode(0);//infijo
        this.movimientos.__PropietarioEntr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                
            }
            
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = movimientos.__PropietarioEntr.getText();
                    //ResultSet buscarTipoEntrada = mimodelo.buscaTipoEntrada(parametro);
                    ResultSet buscarPropiedad = mimodelo.buscarPropiedad(parametro,true);
                    Com_propietarioEntr.removeAll();
                    while(buscarPropiedad.next()){
                        Com_propietarioEntr.addItem(buscarPropiedad.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
                    
        }); 
        //Revisare
        final TextAutoCompleter Com_propietarioSalida = new TextAutoCompleter(movimientos.__PropietarioSalida );
        Com_propietarioSalida.setMode(0);//infijo
        this.movimientos.__PropietarioSalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        a=0;
                    }else{
                        a=1;
                    }                   
                } 
                
            }
            
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = movimientos.__PropietarioSalida.getText();
                    //ResultSet buscarTipoEntrada = mimodelo.buscaTipoEntrada(parametro);
                    ResultSet buscarPropiedad = mimodelo.buscarPropiedad(parametro,true);
                   Com_propietarioSalida.removeAll();
                    while(buscarPropiedad.next()){
                        Com_propietarioSalida.addItem(buscarPropiedad.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
                    
        });
        //
        Com_propietario = new TextAutoCompleter(movimientos.__PropietarioSalidaH );
        Com_propietario.setMode(0);//infijo
        this.movimientos.__PropietarioSalidaH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){}
            
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = movimientos.__PropietarioSalidaH.getText();
                    //ResultSet buscarTipoEntrada = mimodelo.buscaTipoEntrada(parametro);
                    ResultSet buscarPropiedad = mimodelo.buscarPropiedad(parametro,true);
                    Com_propietario.removeAll();
                    while(buscarPropiedad.next()){
                        Com_propietario.addItem(buscarPropiedad.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
                    
        });
        Com_propietarioB = new TextAutoCompleter(movimientos.__PropietarioSalidaB );
        Com_propietarioB.setMode(0);//infijo
        this.movimientos.__PropietarioSalidaB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){}
            
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = movimientos.__PropietarioSalidaB.getText();
                    //ResultSet buscarTipoEntrada = mimodelo.buscaTipoEntrada(parametro);
                    ResultSet buscarPropiedad = mimodelo.buscarPropiedad(parametro,true);
                    Com_propietarioB.removeAll();
                    while(buscarPropiedad.next()){
                        Com_propietarioB.addItem(buscarPropiedad.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
                    
        }); 
       
        final TextAutoCompleter Com_proveedor = new TextAutoCompleter(movimientos.__ProvEntr );
        Com_proveedor.setMode(0);//infijo
        this.movimientos.__ProvEntr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
            
            }
            
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = movimientos.__ProvEntr.getText();
                    ResultSet buscarProveedores = mimodelo.buscaProveedor(parametro,true);
                    //ResultSet buscarPropiedad = mimodelo.buscarPropiedad(parametro);
                    Com_proveedor.removeAll();
                    while(buscarProveedores.next()){
                        Com_proveedor.addItem(buscarProveedores.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
                    
        }); 
        //revisar
        final TextAutoCompleter Com_provedor = new TextAutoCompleter(movimientos.__ProvSalida );
        Com_provedor.setMode(0);//infijo
        this.movimientos.__ProvSalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        a=0;
                    }else{
                        a=1;
                    }                   
                } 
                
            }
            
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = movimientos.__ProvSalida.getText();
                    ResultSet buscarProveedores = mimodelo.buscaProveedor(parametro,true);
                    //ResultSet buscarPropiedad = mimodelo.buscarPropiedad(parametro);
                    Com_provedor.removeAll();
                    while(buscarProveedores.next()){
                        Com_provedor.addItem(buscarProveedores.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
                    
        });
        //
        Com_clienteEntr = new TextAutoCompleter(movimientos.__ClientEntr );
        Com_clienteEntr.setMode(0);//infijo
        this.movimientos.__ClientEntr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){}
            
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    
                    String parametro = movimientos.__ClientEntr.getText();
                    //ResultSet buscarProveedores = mimodelo.buscaProveedor(parametro);
                    ResultSet buscarClientes = mimodelo.buscarCliente(parametro,true);
                    //ResultSet buscarPropiedad = mimodelo.buscarPropiedad(parametro);
                    Com_clienteEntr.removeAll();
                    while(buscarClientes.next()){
                        Com_clienteEntr.addItem(buscarClientes.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
                    
        }); 
        //Revisar
        final TextAutoCompleter Com_clienteSalida = new TextAutoCompleter(movimientos.__ClientSalida);
        Com_clienteSalida.setMode(0);//infijo
        this.movimientos.__ClientSalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){}
            
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    
                    String parametro = movimientos.__ClientSalida.getText();
                    //ResultSet buscarProveedores = mimodelo.buscaProveedor(parametro);
                    ResultSet buscarClientes = mimodelo.buscarCliente(parametro,true);
                    //ResultSet buscarPropiedad = mimodelo.buscarPropiedad(parametro);
                    Com_clienteSalida.removeAll();
                    while(buscarClientes.next()){
                        Com_clienteSalida.addItem(buscarClientes.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
                    
        });
        //
        Com_cliente = new TextAutoCompleter(movimientos.__ClienteSalidaH );
        Com_cliente.setMode(0);//infijo
        this.movimientos.__ClienteSalidaH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){}
            
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = movimientos.__ClienteSalidaH.getText();
                    //ResultSet buscarProveedores = mimodelo.buscaProveedor(parametro);
                    ResultSet buscarClientes = mimodelo.buscarCliente(parametro,true);
                    //ResultSet buscarPropiedad = mimodelo.buscarPropiedad(parametro);
                    Com_cliente.removeAll();
                    while(buscarClientes.next()){
                        Com_cliente.addItem(buscarClientes.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
                    
        });
        Com_clienteB = new TextAutoCompleter(movimientos.__ClienteSalidaB );
        Com_clienteB.setMode(0);//infijo
        this.movimientos.__ClienteSalidaB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){}
            
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = movimientos.__ClienteSalidaB.getText();
                    //ResultSet buscarProveedores = mimodelo.buscaProveedor(parametro);
                    ResultSet buscarClientes = mimodelo.buscarCliente(parametro,true);
                    //ResultSet buscarPropiedad = mimodelo.buscarPropiedad(parametro);
                    Com_clienteB.removeAll();
                    while(buscarClientes.next()){
                        Com_clienteB.addItem(buscarClientes.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
                    
        });
        
        this.movimientos.__OrdenProduccionEntr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        a=0;
                    }else{
                        a=1;
                    }                   
                } 
            }
            
            public void keyReleased(java.awt.event.KeyEvent evt){
            }
                    
        });    
        this.movimientos.__OrdenProduccionSalida.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        a=0;
                    }else{
                        a=1;
                    }                   
                } 
            }
        });
        this.movimientos.__OrdenCompraEntr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){}
            public void keyReleased(java.awt.event.KeyEvent evt){}
                    
        });
        this.movimientos.__OrdenCompraSalida.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        a=0;
                    }else{
                        a=1;
                    }                   
                } 
            }
        });
        
        
        final TextAutoCompleter opSalH = new TextAutoCompleter( movimientos.__OPSalidaHoja );
        opSalH.setMode(0); // infijo
        this.movimientos.__OPSalidaHoja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                
            }
            
            public void keyReleased(java.awt.event.KeyEvent evt){
             try {
                    String parametro = movimientos.__OPSalidaHoja.getText();
                    ResultSet buscaOP = mimodelo.buscaOP(parametro);
                    opSalH.removeAll();
                    while(buscaOP.next()){
                        opSalH.addItem(buscaOP.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
                    
        });
        this.movimientos.__documentoEntr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            } 
            public void keyPressed(java.awt.event.KeyEvent evt){}
           
            
        });
        this.movimientos.__documentoEntr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                docentrada();
            }
        });
        this.movimientos.__documentoSalida.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        a=0;
                    }else{
                        a=1;
                    }                   
                } 
            }
        });
        this.movimientos.__totalentrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){}
        });
        this.movimientos.__cantidadentrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
            
            }
        });
        final JPopupMenu pop = new JPopupMenu();
        //JMenuItem findMenuItem = new JMenuItem("Eliminar Registro.");
        JButton btnalgo = new JButton("Eliminar");
        pop.add(btnalgo);
        
        movimientos.__tablaSalidaBobinaInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON3 && evt.MOUSE_CLICKED == MouseEvent.MOUSE_CLICKED) {
                    movimientos.__tablaSalidaBobinaInventario.setComponentPopupMenu(pop);
                }
            }
        });
        
       
        
        //etiquetas del panel de nuevas entradas
        this.movimientos.__etqNewEliPropietario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String propietario = movimientos.__PropietarioEntr.getText().toString();
                if(propietario.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar al propietario "+propietario+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__PropietarioEntr);
                    if(id>0){
                        boolean tru =mimodelo.bajaPropietarios(id);
                        if(tru) {mensaje(1,"Baja de propietario "+ propietario +" correcta.");
                                         movimientos.__PropietarioEntr.setText("");}
                        else mensaje(3,"Baja de propietario "+ propietario +" incorrecta.");
                    }else mensaje(2,"Verifica el propietario");
                }
            }
        });
        this.movimientos.__etqNewEliPropietario1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String propietario = movimientos.__PropietarioSalida.getText().toString();
                if(propietario.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar al propietario "+propietario+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__PropietarioSalida);
                    if(id>0){
                        boolean tru =mimodelo.bajaPropietarios(id);
                        if(tru) {mensaje(1,"Baja de propietario "+ propietario +" correcta.");
                                         movimientos.__PropietarioSalida.setText("");}
                        else mensaje(3,"Baja de propietario "+ propietario +" incorrecta.");
                    }else mensaje(2,"Verifica el propietario");
                }
            }
        });
        this.movimientos.__etqNewEliPropietario2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String propietario = movimientos.__PropietarioSalidaH.getText().toString();
                if(propietario.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar al propietario "+propietario+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__PropietarioSalidaH);
                    if(id>0){
                        boolean tru =mimodelo.bajaPropietarios(id);
                        if(tru) {mensaje(1,"Baja de propietario "+ propietario +" correcta.");
                                         movimientos.__PropietarioSalidaH.setText("");}
                        else mensaje(3,"Baja de propietario "+ propietario +" incorrecta.");
                    }else mensaje(2,"Verifica el propietario");
                }
            }
        });
        this.movimientos.__etqNewEliPropietario3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String propietario = movimientos.__PropietarioSalidaB.getText().toString();
                if(propietario.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar al propietario "+propietario+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__PropietarioSalidaB);
                    if(id>0){
                        boolean tru =mimodelo.bajaPropietarios(id);
                        if(tru) {mensaje(1,"Baja de propietario "+ propietario +" correcta.");
                                         movimientos.__PropietarioSalidaB.setText("");}
                        else mensaje(3,"Baja de propietario "+ propietario +" incorrecta.");
                    }else mensaje(2,"Verifica el propietario");
                }
            }
        });
        this.movimientos.__etqNewEliCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String cliente = movimientos.__ClientEntr.getText().toString();
                if(cliente.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar al cliente "+cliente+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__ClientEntr);
                    if(id>0){
                        boolean tru =mimodelo.bajaClientes(id);
                        if(tru) {mensaje(1,"Baja de cliente "+ cliente +" correcta.");
                                         movimientos.__ClientEntr.setText("");}
                        else mensaje(3,"Baja de cliente "+ cliente +" incorrecta.");
                    }else mensaje(2,"Verifica el cliente");
                }
            }
        });
        this.movimientos.__etqNewEliCliente1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String cliente = movimientos.__ClientSalida.getText().toString();
                if(cliente.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar al cliente "+cliente+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__ClientSalida);
                    if(id>0){
                        boolean tru =mimodelo.bajaClientes(id);
                        if(tru) {mensaje(1,"Baja de cliente "+ cliente +" correcta.");
                                         movimientos.__ClientSalida.setText("");}
                        else mensaje(3,"Baja de cliente "+ cliente +" incorrecta.");
                    }else mensaje(2,"Verifica el cliente");
                }
            }
        });
        this.movimientos.__etqNewEliCliente2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String cliente = movimientos.__ClienteSalidaH.getText().toString();
                if(cliente.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar al cliente "+cliente+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__ClienteSalidaH);
                    if(id>0){
                        boolean tru =mimodelo.bajaClientes(id);
                        if(tru) {mensaje(1,"Baja de cliente "+ cliente +" correcta.");
                                         movimientos.__ClienteSalidaH.setText("");}
                        else mensaje(3,"Baja de cliente "+ cliente +" incorrecta.");
                    }else mensaje(2,"Verifica el cliente");
                }
            }
        });
        this.movimientos.__etqNewEliCliente3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String cliente = movimientos.__ClienteSalidaB.getText().toString();
                if(cliente.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar al cliente "+cliente+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__ClienteSalidaB);
                    if(id>0){
                        boolean tru =mimodelo.bajaClientes(id);
                        if(tru) {mensaje(1,"Baja de cliente "+ cliente +" correcta.");
                                         movimientos.__ClienteSalidaB.setText("");}
                        else mensaje(3,"Baja de cliente "+ cliente +" incorrecta.");
                    }else mensaje(2,"Verifica el cliente");
                }
            }
        });
        this.movimientos.__etqNewEliMaquina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String maquina = movimientos.__MaquinaSalidaH.getText().toString();
                if(maquina.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar la maquina "+maquina+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__MaquinaSalidaH);
                    if(id>0){
                        boolean tru =mimodelo.bajaMaquina(id);
                        if(tru) {mensaje(1,"Baja de maquina "+ maquina +" correcta.");
                                         movimientos.__MaquinaSalidaH.setText("");}
                        else mensaje(3,"Baja de maquina "+ maquina +" incorrecta.");
                    }else mensaje(2,"Verifica la maquina");
                }
            }
        });
        this.movimientos.__etqNewEliMaquina1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String maquina = movimientos.__MaquinaSalidaB.getText().toString();
                if(maquina.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar la maquina "+maquina+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__MaquinaSalidaB);
                    if(id>0){
                        boolean tru =mimodelo.bajaMaquina(id);
                        if(tru) {mensaje(1,"Baja de maquina "+ maquina +" correcta.");
                                         movimientos.__MaquinaSalidaB.setText("");}
                        else mensaje(3,"Baja de maquina "+ maquina +" incorrecta.");
                    }else mensaje(2,"Verifica la maquina");
                }
            }
        });
        this.movimientos.__etqNewEliProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String prove = movimientos.__ProvEntr.getText().toString();
                if(prove.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar el proveedor "+prove+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__ProvEntr);
                    if(id>0){
                        boolean tru =mimodelo.bajaProveedor(id);
                        if(tru) {mensaje(1,"Baja de proveedor"+ prove +" correcta.");
                                         movimientos.__ProvEntr.setText("");}
                        else mensaje(3,"Baja de proveedor "+ prove +" incorrecta.");
                    }else mensaje(2,"Verifica el proveedor");
                }
            }
        });
        this.movimientos.__etqNewEliProveedor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String prove = movimientos.__ProvSalida.getText().toString();
                if(prove.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar el proveedor "+prove+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__ProvSalida);
                    if(id>0){
                        boolean tru =mimodelo.bajaProveedor(id);
                        if(tru) {mensaje(1,"Baja de proveedor"+ prove +" correcta.");
                                         movimientos.__ProvSalida.setText("");}
                        else mensaje(3,"Baja de proveedor "+ prove +" incorrecta.");
                    }else mensaje(2,"Verifica el proveedor");
                }
            }
        });
        this.movimientos.__etqNewEliTipoEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String ent = movimientos.__TipoEntrada.getText().toString();
                if(ent.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar el tipo de entrada "+ent+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__TipoEntrada);
                    if(id>0){
                        boolean tru =mimodelo.bajaTipoEntrada(id);
                        if(tru) {mensaje(1,"Baja de tipo entrada "+ ent +" correcta.");
                                         movimientos.__TipoEntrada.setText("");}
                        else mensaje(3,"Baja de tipo entrada "+ ent +" incorrecta.");
                    }else mensaje(2,"Verifica el tipo de entrada");
                }
            }
        });
        this.movimientos.__etqNewEliTipoSalida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String sal = movimientos.__TipoSalida.getText().toString();
                if(sal.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar el tipo de salida "+sal+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__TipoSalida);
                    if(id>0){
                        boolean tru =mimodelo.bajaTipoSalida(id);
                        if(tru) {mensaje(1,"Baja de tipo salida "+ sal +" correcta.");
                                         movimientos.__TipoSalida.setText("");}
                        else mensaje(3,"Baja de tipo salidaa "+ sal +" incorrecta.");
                    }else mensaje(2,"Verifica el tipo de salida");
                }
            }
        });
        this.movimientos.__etqNewEliTipoSalidaH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String sal = movimientos.__TipodeSalidaH.getText().toString();
                if(sal.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar el tipo de salida "+sal+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__TipodeSalidaH);
                    if(id>0){
                        boolean tru =mimodelo.bajaTipoSalida(id);
                        if(tru) {mensaje(1,"Baja de tipo salida "+ sal +" correcta.");
                                         movimientos.__TipodeSalidaH.setText("");}
                        else mensaje(3,"Baja de tipo salidaa "+ sal +" incorrecta.");
                    }else mensaje(2,"Verifica el tipo de salida");
                }
            }
        });
        this.movimientos.__etqNewEliTipoSalidaB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String sal = movimientos.__TipodeSalidaB.getText().toString();
                if(sal.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar el tipo de salida "+sal+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__TipodeSalidaB);
                    if(id>0){
                        boolean tru =mimodelo.bajaTipoSalida(id);
                        if(tru) {mensaje(1,"Baja de tipo salida "+ sal +" correcta.");
                                         movimientos.__TipodeSalidaB.setText("");}
                        else mensaje(3,"Baja de tipo salidaa "+ sal +" incorrecta.");
                    }else mensaje(2,"Verifica el tipo de salida");
                }
            }
        });
        this.movimientos.__etqNewTipoEntr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewTipoEntr.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewTipoEntr.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nuevaTipoEntrada();
            }
        });
        this.movimientos.__etqNewPropietarioEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewPropietarioEntrada.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewPropietarioEntrada.setFont(new java.awt.Font("Papyrus", 0, 12));
            }   
            public void mouseClicked(java.awt.event.MouseEvent evt){
                nuevoPropietario();
            }
        });
        this.movimientos.__etqNewProveedorEntr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clienteprovedor=1;
                provedor.ProveCli.setText("Nuevo Proveedor");
                provedor.setLocationRelativeTo(null);
                provedor.setVisible(true);
                
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewProveedorEntr.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewProveedorEntr.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        }); 
        this.movimientos.__etqLimpiarTablaEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                limpiarTabla(movimientos.__tablaEntrada);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqLimpiarTablaEntrada.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqLimpiarTablaEntrada.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });
        this.movimientos.__etqLimpiarTablaSalH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                limpiarTabla(movimientos.__tablaSalidaHoja);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqLimpiarTablaSalH.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqLimpiarTablaSalH.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });
        this.movimientos.__etqLimpiarTablaSalB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                limpiarTabla(movimientos.__tablaSalidaBobinaInventario);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqLimpiarTablaSalB.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqLimpiarTablaSalB.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });
        this.movimientos.__etqNewClienteE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clienteprovedor=0;
                provedor.ProveCli.setText("Nuevo Cliente");
                provedor.setVisible(true);
                provedor.setLocationRelativeTo(null);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewClienteE.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewClienteE.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });
        
        //etiquetas del panel de salidas generales
        this.movimientos.__etqNewClienteSalida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clienteprovedor=0;
                provedor.ProveCli.setText("Nuevo Cliente");
                provedor.setVisible(true);
                provedor.setLocationRelativeTo(null);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewClienteSalida.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewClienteSalida.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });
        this.movimientos.__etqNewProveedorSalida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clienteprovedor=1;
                provedor.ProveCli.setText("Nuevo Proveedor");
                provedor.setLocationRelativeTo(null);
                provedor.setVisible(true);
                
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewProveedorSalida.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewProveedorSalida.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        }); 
        this.movimientos.__etqNewPropietarioSalida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewPropietarioSalida.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewPropietarioSalida.setFont(new java.awt.Font("Papyrus", 0, 12));
            }   
            public void mouseClicked(java.awt.event.MouseEvent evt){
                nuevoPropietario();
            }
        });
        this.movimientos.__etqNewTipoSalida.addMouseListener(new java.awt.event.MouseAdapter() {
            
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewTipoSalida.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewTipoSalida.setFont(new java.awt.Font("Papyrus", 0, 12));
            }   
            public void mouseClicked(java.awt.event.MouseEvent evt){
                nuevaTipoDeSalida();
            }
        });
        
        this.movimientos.__etqLimpiarTablaSalida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                limpiarTabla(movimientos.__tablaSalida);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqLimpiarTablaSalida.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqLimpiarTablaSalida.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });
        //etiquetas del panel de salidas de hojas
        this.movimientos.__etqNewClienteSalH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clienteprovedor=0;
                provedor.ProveCli.setText("Nuevo Cliente");
                provedor.setVisible(true);
                provedor.setLocationRelativeTo(null);
                movimientos.setEnabled(false);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewClienteSalH.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewClienteSalH.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });  
        this.movimientos.__etqNewTipodeSalidaH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nuevaTipoDeSalida();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewTipodeSalidaH.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewTipodeSalidaH.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });
        this.movimientos.__etqNewPropietarioH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nuevoPropietario();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewPropietarioH.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewPropietarioH.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });  
        this.movimientos.__etqNewMaquinaH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nuevaMaquina();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewMaquinaH.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewMaquinaH.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });  
        //etiquetas del panel de salidas de bobinas
        this.movimientos.__etqNewMaquinaB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nuevaMaquina();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewMaquinaB.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewMaquinaB.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });  
        this.movimientos.__etqNewClienteSalB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clienteprovedor=0;
                provedor.ProveCli.setText("Nuevo Cliente");
                provedor.setVisible(true);
                provedor.setLocationRelativeTo(null);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewClienteSalB.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewClienteSalB.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });  
        this.movimientos.__etqNewPropietarioB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nuevoPropietario();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewPropietarioB.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewPropietarioB.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });
        this.movimientos.__etqNewTipodeSalidaB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nuevaTipoDeSalida();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewTipodeSalidaB.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewTipodeSalidaB.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });                
        //fin de las etiquetas de todos los paneles de movimientos        
        final TextAutoCompleter __clavePapelA = new TextAutoCompleter( movimientos.__clavePapel );
        __clavePapelA.setMode(0); // infijo
        final TextAutoCompleter __clavePapelD = new TextAutoCompleter( movimientos.__clavePapelsalida );
        __clavePapelD.setMode(0); // infijo
        final TextAutoCompleter __clavePapelH = new TextAutoCompleter( movimientos.__clavepapelhoja );
        __clavePapelH.setMode(0); // infijo
        final TextAutoCompleter __clavePapelB = new TextAutoCompleter( movimientos.__clavepapelbobina );
        __clavePapelB.setMode(0); // infijo
        final TextAutoCompleter __clavePapelT = new TextAutoCompleter( traspaso.__ClavePapelTras);
        __clavePapelT.setMode(0); // infijo           
        
        this.movimientos.__clavePapel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                        try {
                            String parametro = movimientos.__clavePapel.getText();
                            ResultSet costo = mimodelo.ultimocosto(parametro);
                            int fila =movimientos.__tablaEntrada.getSelectedRow();
                            while(costo.next()){
                                movimientos.__tablaEntrada.setValueAt(Double.parseDouble(costo.getString("costo")+""), fila, 4);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            }
            
            public void keyReleased(java.awt.event.KeyEvent evt){
                try {
                    String parametro = movimientos.__clavePapel.getText();
                    int propietario = busquedaid(movimientos.__PropietarioEntr);
                    if(propietario==0){
                        mensaje(3,"Verifica el Propietario");
                        movimientos.__PropietarioEntr.requestFocus();
                        return;
                    }
                    ResultSet buscaClavePapel = mimodelo.buscaClavePapel(parametro,propietario);
                    __clavePapelA.removeAll();
                    while(buscaClavePapel.next()){
                        __clavePapelA.addItem(buscaClavePapel.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
            }
            public void keyTiped(java.awt.event.KeyEvent evt){
            }
        });        
        this.movimientos.__cantidaddepaque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    int fila = movimientos.__tablaSalidaHoja.getSelectedRow();
                    int totalhojas = Integer.parseInt(movimientos.__tablaSalidaHoja.getValueAt(fila, 1).toString());
                    int contenido = Integer.parseInt(movimientos.__tablaSalidaHoja.getValueAt(fila, 2).toString());
                    int cantidad = Integer.parseInt(movimientos.__tablaSalidaHoja.getValueAt(fila, 3).toString());
                    int concant=contenido*cantidad;
                    if(concant>totalhojas){
                        mensaje(3,"no te alcanza el total de hojas en la salida #"+(fila+1));
                    }else{
                        int resto= totalhojas-(contenido*cantidad);
                         movimientos.__tablaSalidaHoja.setValueAt(resto, fila, 4);
                    }
                    
                }
            }
        });        
        this.movimientos.__costosalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    
                }
            }
        });
        this.movimientos.__costoentrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
            if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                try{
                    int i = movimientos.__tablaEntrada.getSelectedRow();
                    String cantidad=movimientos.__tablaEntrada.getValueAt(i, 1).toString();
                    Double cant= Double.parseDouble(cantidad);
                    String costo=movimientos.__tablaEntrada.getValueAt(i, 4).toString();
                    Double cos = Double.parseDouble(costo);
                    Double tot = cant * cos;
                    BigDecimal big = new BigDecimal(tot+"");
                    big = big.setScale(2, RoundingMode.HALF_UP);
                    movimientos.__tablaEntrada.setValueAt(big, i, 5);
                    }catch(Exception e){
                        mensaje(3,e.getMessage());
                    }
                }
            }
            
        });
        this.movimientos.__TituloSalidaHoja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            } 
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER||evt.getKeyCode()==KeyEvent.VK_TAB){
                    movimientos.__OPSalidaHoja.requestFocus();
                     
                }
            }
        });
        this.movimientos.__TituloSalidaBobina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                   if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        a=0;
                    }else{
                        a=1;
                    }                   
                }
                if(evt.getKeyCode()==KeyEvent.VK_ENTER||evt.getKeyCode()==KeyEvent.VK_TAB){
                    movimientos.__FactorSalidaBobina.requestFocus();
                     
                }
            }
        });
        this.movimientos.__FactorSalidaBobina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                   if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        a=0;
                    }else{
                        a=1;
                    }                   
                }
                if(evt.getKeyCode()==KeyEvent.VK_ENTER||evt.getKeyCode()==KeyEvent.VK_TAB){
                    movimientos.__PliegoSalidaBobina.requestFocus();
                     
                }
            }
        });
        this.movimientos.__PliegoSalidaBobina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                   if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        a=0;
                    }else{
                        a=1;
                    }                   
                }
                if(evt.getKeyCode()==KeyEvent.VK_ENTER||evt.getKeyCode()==KeyEvent.VK_TAB){
                    movimientos.__ClienteSalidaB.requestFocus();
                     
                }
            }
        });
        this.movimientos.__TotalKGSalidaBobina1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                   if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        a=0;
                    }else{
                        a=1;
                    }                   
                }
                if(evt.getKeyCode()==KeyEvent.VK_ENTER||evt.getKeyCode()==KeyEvent.VK_TAB){
                    movimientos.__TiroXPliegoSalidaBobina1.requestFocus();
                     
                }
            }
        });
        this.movimientos.__TiroXPliegoSalidaBobina1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                   if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        a=0;
                    }else{
                        a=1;
                    }                   
                }
                if(evt.getKeyCode()==KeyEvent.VK_ENTER||evt.getKeyCode()==KeyEvent.VK_TAB){
                    movimientos.__PliegodeAjusteSalidaBobina1.requestFocus();
                     
                }
            }
        });
        this.movimientos.__PliegodeAjusteSalidaBobina1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                   if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        a=0;
                    }else{
                        a=1;
                    }                   
                }
                if(evt.getKeyCode()==KeyEvent.VK_ENTER||evt.getKeyCode()==KeyEvent.VK_TAB){
                    movimientos.__tablaSalidaBobinaInventario.requestFocus();
                     
                }
            }
        });
        this.movimientos.__OPSalidaHoja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            } 
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER||evt.getKeyCode()==KeyEvent.VK_TAB){
                    movimientos.__EstandarProduccionSalidaHoja.requestFocus();
                }
            }
        });
        this.movimientos.__EstandarProduccionSalidaHoja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            } 
            public void keyPressed(java.awt.event.KeyEvent evt){
                 if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        a=0;
                    }else{
                        a=1;
                    }                   
                }
                if(evt.getKeyCode()==KeyEvent.VK_ENTER||evt.getKeyCode()==KeyEvent.VK_TAB){
                   movimientos.__ClienteSalidaH.requestFocus();
                }
            }
        });
        this.movimientos.__clavepapelbobina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    try {
                        String parametro = movimientos.__clavepapelbobina.getText();
                        ResultSet costo = mimodelo.costoprom(parametro);
                        int fila =movimientos.__tablaSalidaBobinaInventario.getSelectedRow();
                        while(costo.next()){
                            movimientos.__tablaSalidaBobinaInventario.setValueAt(Double.parseDouble(costo.getString("preciopromedio")+""), fila, 7);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
                try {
                    int propietario = busquedaid(movimientos.__PropietarioSalidaB);
                    if(propietario==0){
                        mensaje(2,"Selecciona el propietario del papel");
                        movimientos.__PropietarioSalidaB.requestFocus();
                        return;
                    }
                    String parametro = movimientos.__clavepapelbobina.getText();
                    ResultSet buscaClaveBobina = mimodelo.buscaClaveBobina(parametro,propietario);
                    __clavePapelB.removeAll();
                    while(buscaClaveBobina.next()){
                        __clavePapelB.addItem(buscaClaveBobina.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3, ex.getMessage());
                }
            }
            
        });
        this.movimientos.__clavepapelhoja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    try {
                        String parametro = movimientos.__clavepapelhoja.getText();
                        ResultSet costo = mimodelo.costoprom(parametro);
                        int fila =movimientos.__tablaSalidaHoja.getSelectedRow();
                        while(costo.next()){
                            movimientos.__tablaSalidaHoja.setValueAt(Double.parseDouble(costo.getString("preciopromedio")+""), fila, 3);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
                try {
                    int propietario = busquedaid(movimientos.__PropietarioSalidaH);
                    if(propietario==0){
                        mensaje(2,"Selecciona el propietario del papel");
                        movimientos.__PropietarioSalidaH.requestFocus();
                        return;
                    }
                    String parametro = movimientos.__clavepapelhoja.getText();
                    ResultSet buscaClaveHoja = mimodelo.buscaClaveHoja(parametro,propietario);
                    __clavePapelH.removeAll();
                    while(buscaClaveHoja.next()){
                        __clavePapelH.addItem(buscaClaveHoja.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3, ex.getMessage());
                }
            }
        });        
        this.movimientos.__tablaSalida.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyTyped(java.awt.event.KeyEvent evt) {
                
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                int columna = movimientos.__tablaSalida.getSelectedColumn();
                int fila = movimientos.__tablaSalida.getSelectedRow();
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    try{
                        if(columna==3||columna==1||columna==2||columna==4){
                            Double tot=Double.parseDouble(movimientos.__tablaSalida.getValueAt(fila, 1)+"");
                            Double cos=Double.parseDouble(movimientos.__tablaSalida.getValueAt(fila, 3)+"");
                            Double totcos= tot*cos;
                            movimientos.__tablaSalida.setValueAt(totcos, fila, 4);
                        }
                    }catch(Exception e){
                        mensaje(3,"completa los campos");
                    }
                }
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                
            }
        });        
        this.movimientos.__tablaEntrada.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyTyped(java.awt.event.KeyEvent evt) {
                
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                int columna = movimientos.__tablaEntrada.getSelectedColumn();
                int fila = movimientos.__tablaEntrada.getSelectedRow();
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    try{
                        if(columna==4||columna==1||columna==2||columna==3||columna==5){
                            Double tot=Double.parseDouble(movimientos.__tablaEntrada.getValueAt(fila, 1)+"");
                            Double cos=Double.parseDouble(movimientos.__tablaEntrada.getValueAt(fila, 4)+"");
                            Double totcos= tot*cos;
                            movimientos.__tablaEntrada.setValueAt(totcos, fila, 5);
                        }
                    }catch(Exception e){
                        mensaje(2,"completa los campos");
                    }
                }
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                
            }
        });        
        this.movimientos.__tablaSalidaHoja.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyTyped(java.awt.event.KeyEvent evt) {
                
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                int columna = movimientos.__tablaSalidaHoja.getSelectedColumn();
                int fila = movimientos.__tablaSalidaHoja.getSelectedRow();
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    try{
                        if(columna==3||columna==1||columna==2||columna==4){
                            Double tot=Double.parseDouble(movimientos.__tablaSalidaHoja.getValueAt(fila, 1)+"");
                            Double cos=Double.parseDouble(movimientos.__tablaSalidaHoja.getValueAt(fila, 3)+"");
                            Double totcos= tot*cos;
                            movimientos.__tablaSalidaHoja.setValueAt(totcos, fila, 4);
                        }
                    }catch(Exception e){
                        mensaje(3,"completa los campos");
                    }
                }
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                
            }
        });
        this.movimientos.__tablaSalidaBobinaInventario.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyTyped(java.awt.event.KeyEvent evt) {
                
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                int columna = movimientos.__tablaSalidaBobinaInventario.getSelectedColumn();
                int fila = movimientos.__tablaSalidaBobinaInventario.getSelectedRow();
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    try{
                        if(columna==3||columna==1||columna==2||columna==4||columna==5||columna==6||columna==7||columna==8){
                            Double tot=Double.parseDouble(movimientos.__tablaSalidaBobinaInventario.getValueAt(fila, 1)+"");
                            tot+=Double.parseDouble(movimientos.__tablaSalidaBobinaInventario.getValueAt(fila, 3)+"");
                            tot-=Double.parseDouble(movimientos.__tablaSalidaBobinaInventario.getValueAt(fila, 5)+"");
                            Double cos=Double.parseDouble(movimientos.__tablaSalidaBobinaInventario.getValueAt(fila, 7)+"");
                            Double totcos= tot*cos;
                            
                            //1+3-5
                            
                            movimientos.__tablaSalidaBobinaInventario.setValueAt(totcos, fila, 8);
                            /*for(int i=0;i<6;i++){
                                Object valor = movimientos.__tablaSalidaBobinaInventario.getValueAt(fila, i);
                                if(valor==null){
                                    mensaje(2,"Completa los campos...");
                                }
                            }*/
                        }
                    }catch(Exception e){
                        mensaje(2,"Completa los campos...");
                    }
                }
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                
            }
        });        
        this.movimientos.__clavePapelsalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    try {
                        String parametro = movimientos.__clavePapelsalida.getText();
                        ResultSet costo = mimodelo.costoprom(parametro);
                        int fila =movimientos.__tablaSalida.getSelectedRow();
                        while(costo.next()){
                            movimientos.__tablaSalida.setValueAt(Double.parseDouble(costo.getString("preciopromedio")+""), fila, 3);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
                try {
                    int propietario = busquedaid(movimientos.__PropietarioSalida);
                    if(propietario==0){
                        mensaje(2,"Selecciona el propietario del papel");
                        movimientos.__PropietarioSalida.requestFocus();
                        return;
                    }
                    String parametro = movimientos.__clavePapelsalida.getText();
                    ResultSet buscaClaveHoja = mimodelo.buscaClavePapelExist(parametro, propietario);
                    __clavePapelD.removeAll();
                    while(buscaClaveHoja.next()){
                        __clavePapelD.addItem(buscaClaveHoja.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3, ex.getMessage());
                }
            }
        });
        
        this.movimientos.__OPSalidaBobina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    String op = movimientos.__OPSalidaBobina.getText();
                    if(op.equals("    -    ")){
                        mensaje(3,"Captura la orden de producción");
                        return;
                    }else
                    if(op.isEmpty()){
                        mensaje(3,"Captura la orden de producción");
                        return;
                    }else{
                        confir = mensajeConfirmacion("¿Es correcta la Orden de Produccion "+op+"?","Confirma");  
                        if(confir==JOptionPane.YES_OPTION){
                            try {                        
                                ResultSet Op = mimodelo.buscarop(op);
                                if(Op.next()){
                                    Op.beforeFirst();
                                    tiro.op = op;                                    
                                    movimientos.setEnabled(false);
                                    tiro.setVisible(true);
                                    tiro.setLocationRelativeTo(null);
                                    while(Op.next()){
                                        tiro.__MermaImpresion.setText(Op.getString("merma"));
                                        //String fechaini=Op.getString("fecha_inicial");
                                        Date fechini=null;
                                        Date fechfin=null;
                                        try {
                                            fechini = new SimpleDateFormat("yyyy-MM-d").parse(Op.getString("fecha_inicial"));
                                            fechfin = new SimpleDateFormat("yyyy-MM-d").parse(Op.getString("fecha_final"));
                                        } catch (ParseException ex) {
                                            Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                                        } 
                                        tiro.__FechaInicial.setDate(fechini);
                                        tiro.__FechaFinal.setDate(fechfin);
                                        tiro.__HoraInicial.setText(Op.getString("hora_inicial"));
                                        tiro.__HoraFinal.setText(Op.getString("hora_final"));
                                        tiro.__EstandarProduccionXHora.setText(Op.getString("estandar_produccion"));
                                        tiro.__TiempoRealProduccion.setText(Op.getString("tiempo_real"));
                                        tiro.__TotalPliegos.setText(Op.getString("total_pliego"));
                                        tiro.__ContadorRotativas.setText(Op.getString("contador_rotatvivas"));
                                        limpiarTabla(movimientos.__tablaSalidaBobinaInventario);
                                        movimientos.__tablaSalidaBobinaInventario.setValueAt(Op.getString("clave_papel"), 0, 0);
                                        movimientos.__tablaSalidaBobinaInventario.setValueAt(Op.getString("invkgs"), 0, 1);
                                        movimientos.__tablaSalidaBobinaInventario.setValueAt(Op.getString("invbob"), 0, 2);
                                    }
                                    ResultSet Op2 = mimodelo.buscarop2(op);
                                    if(Op2.next()){
                                        Op2.beforeFirst();
                                        while(Op2.next()){
                                            movimientos.__TituloSalidaBobina.setText(Op2.getString("titulo"));
                                            movimientos.__ClienteSalidaB.setText(Op2.getString("cliente"));
                                        }
                                        movimientos.__FactorSalidaBobina.requestFocus();
                                    }else{
                                        movimientos.__TituloSalidaBobina.requestFocus();
                                    }
                                    tiro.guardar=1;
                                }else{
                                    mensaje(2,"La orden de produccion "+op+" no existe, Se capturara la nueva orden de produccion");
                                    tiro.op = op;
                                    tiro.guardar=0;
                                    movimientos.setEnabled(false);
                                    tiro.setVisible(true);
                                    tiro.setLocationRelativeTo(null);                                   
                                    limpiarTabla(movimientos.__tablaSalidaBobinaInventario);
                                }
                            } catch (SQLException ex) {
                                mensaje(3,ex.getMessage());
                            }
                            if(movimientos.__TituloSalidaBobina.getText().isEmpty()){
                                movimientos.__TituloSalidaBobina.requestFocus();
                                movimientos.setEnabled(true);
                            }else{
                                movimientos.__FactorSalidaBobina.requestFocus();
                                movimientos.setEnabled(true);
                            } 
                        }
                    } 
                } 
           }
            public void keyReleased(java.awt.event.KeyEvent evt){
                
            }
        });
        //this.movimientos.__tablaSalidaBobinaInventario.setDefaultRenderer(Object.class, new TableCellRenderedColor());
                                
        movimientos.__TiroXPliegoSalidaBobina1.addKeyListener(new java.awt.event.KeyAdapter() {
             public void keyTyped(java.awt.event.KeyEvent evt) {
                
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    totalkgpliego();
                } 
                
           }
            public void keyReleased(java.awt.event.KeyEvent evt){
                totalkgpliego();
            }
        });
        movimientos.__PliegodeAjusteSalidaBobina1.addKeyListener(new java.awt.event.KeyAdapter() {
             public void keyTyped(java.awt.event.KeyEvent evt) {
                
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    pliegosajuste();
                } 
                
           }
            public void keyReleased(java.awt.event.KeyEvent evt){
                pliegosajuste();
            }
        }); 
        /*this.movimientos.__pnlEntrada.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
                   regresar();
                }
            }
        });
        this.movimientos.__pnlSalidasG.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
                   regresar();
                }
            }
        });
        this.movimientos.__pnlSalidaHoja.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
                   regresar();
                }
            }
        });
        this.movimientos.__pnlSalidaBobina.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
                   regresar();
                }
            }
        });*/
        //que los check se seleccionen con enter
        this.movimientos.__chkTurno1Entr.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(movimientos.__chkTurno1Entr.isSelected()== true){
                        movimientos.__chkTurno1Entr.setSelected(false);
                    }else
                        movimientos.__chkTurno1Entr.setSelected(true);
                }
            }
        });
        this.movimientos.__chkTurno2Entr.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(movimientos.__chkTurno2Entr.isSelected()== true){
                        movimientos.__chkTurno2Entr.setSelected(false);
                    }else
                        movimientos.__chkTurno2Entr.setSelected(true);
                }
            }
        });
        this.movimientos.__chkTurno3Entr.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(movimientos.__chkTurno3Entr.isSelected()== true){
                        movimientos.__chkTurno3Entr.setSelected(false);
                    }else
                        movimientos.__chkTurno3Entr.setSelected(true);
                }
            }
        });
        this.movimientos.__chkTurno1SalHoja.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(movimientos.__chkTurno1SalHoja.isSelected()== true){
                        movimientos.__chkTurno1SalHoja.setSelected(false);
                    }else
                        movimientos.__chkTurno1SalHoja.setSelected(true);
                }
            }
        });
        this.movimientos.__chkTurno2SalHoja.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(movimientos.__chkTurno2SalHoja.isSelected()== true){
                        movimientos.__chkTurno2SalHoja.setSelected(false);
                    }else
                        movimientos.__chkTurno2SalHoja.setSelected(true);
                }
            }
        });
        this.movimientos.__chkTurno3SalHoja.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(movimientos.__chkTurno3SalHoja.isSelected()== true){
                        movimientos.__chkTurno3SalHoja.setSelected(false);
                    }else
                        movimientos.__chkTurno3SalHoja.setSelected(true);
                }
            }
        });
        this.movimientos.__chkTurno1SalBobina.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(movimientos.__chkTurno1SalBobina.isSelected()== true){
                        movimientos.__chkTurno1SalBobina.setSelected(false);
                    }else
                        movimientos.__chkTurno1SalBobina.setSelected(true);
                }
            }
        });
        this.movimientos.__chkTurno2SalBobina.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(movimientos.__chkTurno2SalBobina.isSelected()== true){
                        movimientos.__chkTurno2SalBobina.setSelected(false);
                    }else
                        movimientos.__chkTurno2SalBobina.setSelected(true);
                }
            }
        });
        //movimientos papel salidas generales
        this.movimientos.__chkTurno3SalBobina.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(movimientos.__chkTurno3SalBobina.isSelected()== true){
                        movimientos.__chkTurno3SalBobina.setSelected(false);
                    }else
                        movimientos.__chkTurno3SalBobina.setSelected(true);
                }
            }
        });
        this.movimientos.__chkTurno1Salida.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(movimientos.__chkTurno1Salida.isSelected()== true){
                        movimientos.__chkTurno1Salida.setSelected(false);
                    }else
                        movimientos.__chkTurno1Salida.setSelected(true);
                }
            }
        });
        this.movimientos.__chkTurno2Salida.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(movimientos.__chkTurno2Salida.isSelected()== true){
                        movimientos.__chkTurno2Salida.setSelected(false);
                    }else
                        movimientos.__chkTurno2Salida.setSelected(true);
                }
            }
        });
        this.movimientos.__chkTurno3Salida.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(movimientos.__chkTurno3Salida.isSelected()== true){
                        movimientos.__chkTurno3Salida.setSelected(false);
                    }else
                        movimientos.__chkTurno3Salida.setSelected(true);
                }
            }
        });
        this.movimientos.__TipoSalida.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(movimientos.__chkTurno3SalBobina.isSelected()== true){
                        movimientos.__chkTurno3SalBobina.setSelected(false);
                    }else
                        movimientos.__chkTurno3SalBobina.setSelected(true);
                }
            }
        });
        this.movimientos.__FolioSalida.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    if(movimientos.__chkTurno3SalBobina.isSelected()== true){
                        movimientos.__chkTurno3SalBobina.setSelected(false);
                    }else
                        movimientos.__chkTurno3SalBobina.setSelected(true);
                }
            }
        });
        //tiro
        this.tiro.__ACEPTARTIRO.setActionCommand("__ACEPTARTIRO");
        this.tiro.__ACEPTARTIRO.addActionListener(this);
        this.tiro.__CANCELARTIRO.setActionCommand("__CANCELARTIRO");
        this.tiro.__CANCELARTIRO.addActionListener(this);   
        tiro.__HoraFinal.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt){
                    
                }
                public void focusLost(java.awt.event.FocusEvent evt){
                    String fechaini = aceptarFecha(tiro.__FechaInicial,0);
                    if(fechaini==null){
                        mensaje="Ingresa la fecha a capturar";
                        tiro.__FechaInicial.requestFocus();
                        return;
                    }
                    String fechaInicial = fechaini;
                    String hraini = tiro.__HoraInicial.getText();
                    if(hraini.isEmpty()){
                        mensaje(3,"No Dejes Campos Vacios");
                        tiro.__HoraInicial.requestFocus();
                        return;
                    }else{
                        int hor= Integer.parseInt(hraini.substring(0,2));
                        int min= Integer.parseInt(hraini.substring(3,5));
                        if(hor>23||hor<0||min<0||min>59){
                            mensaje(3,"La hora inicial es incorrecta");
                            tiro.__HoraInicial.requestFocus();
                            return;
                        }
                    }
                    fechaInicial=fechaInicial+" "+hraini+":00";
                    String fechafin = aceptarFecha(tiro.__FechaFinal,0);
                    if(fechafin==null){
                        mensaje="Ingresa la fecha a capturar";
                        tiro.__FechaInicial.requestFocus();
                        return;
                    }
                    String fechaFinal = fechafin;
                    Date fechainii = tiro.__FechaInicial.getCalendar().getTime();
                    Date fechafinn=  tiro.__FechaFinal.getCalendar().getTime();
                    if(fechafinn.before(fechainii)){
                        mensaje(3,"La fecha inicial es mayor a la fecha final");
                        return;
                    }
                    String hrafin = tiro.__HoraFinal.getText();
                    if(hrafin.isEmpty()){
                        mensaje(3,"No Dejes Campos Vacios");
                        tiro.__HoraFinal.requestFocus();
                        return;
                    }else{
                        int hor= Integer.parseInt(hrafin.substring(0,2));
                        int min= Integer.parseInt(hrafin.substring(3,5));
                        if(hor>23||hor<0||min<0||min>59){
                            mensaje(3,"La hora final es incorrecta");
                            tiro.__HoraFinal.requestFocus();
                            return;
                        }
                    }
                    fechaFinal=fechaFinal+" "+hrafin+":00";
                    String tiempoReal = calcularHoras(fechaInicial, fechaFinal,1);
                    tiro.__TiempoRealProduccion.setText(tiempoReal);
                    }
        });
        //Cambio de Contraseña
        this.cambiocontraseña.__GUARDAR.setActionCommand("__GUARDARPASSWORD");
        this.cambiocontraseña.__GUARDAR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        cambiocontraseña.__etqBloqMayus.setVisible(true);
                        a=0;
                    }else{
                        cambiocontraseña.__etqBloqMayus.setVisible(false);
                        a=1;
                    }                   
                } 
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    cambiopswd();
                } 
            }            
        });
        this.cambiocontraseña.__GUARDAR.setMnemonic('G');
        this.cambiocontraseña.__GUARDAR.addActionListener(this);
        this.cambiocontraseña.__CANCELAR.setActionCommand("__CANCELARPASSWORD");
        this.cambiocontraseña.__CANCELAR.setMnemonic('C');
        this.cambiocontraseña.__CANCELAR.addActionListener(this);
        this.cambiocontraseña.__Pswd.addKeyListener(new java.awt.event.KeyAdapter() {//Evaluacion del las Caja de texto __Pswd del formulario Password
            public void keyTyped(java.awt.event.KeyEvent evt){//y evento de la etiqueta bloq mayus del mismo formulario Pasword
                KeyTipedLetrasNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        cambiocontraseña.__etqBloqMayus.setVisible(true);
                        a=0;
                    }else{
                        cambiocontraseña.__etqBloqMayus.setVisible(false);
                        a=1;
                    }                   
                } 
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    cambiocontraseña.__NewPswd.requestFocus(true);
                } 
            }            
        });
        this.cambiocontraseña.__Pswd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                String contra = cambiocontraseña.__Pswd.getText().toString();
                cambiocontraseña.__Pswd.setToolTipText("<html><body bgcolor=\"red\"><font color=\"white\">"+contra+"</font></body></html>");
            }
        });
        this.cambiocontraseña.__NewPswd.addKeyListener(new java.awt.event.KeyAdapter() {//Evaluacion del las Caja de texto __Pswd del formulario Password
            public void keyTyped(java.awt.event.KeyEvent evt){//y evento de la etiqueta bloq mayus del mismo formulario Pasword
                KeyTipedLetrasNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        cambiocontraseña.__etqBloqMayus.setVisible(true);
                        a=0;
                    }else{
                        cambiocontraseña.__etqBloqMayus.setVisible(false);
                        a=1;
                    }                   
                } 
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    cambiocontraseña.__NewPswd1.requestFocus(true);
                } 
            }            
        });
        this.cambiocontraseña.__NewPswd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                String contra = cambiocontraseña.__NewPswd.getText().toString();
                cambiocontraseña.__NewPswd.setToolTipText("<html><body bgcolor=\"red\"><font color=\"white\">"+contra+"</font></body></html>");
            }
        });
        this.cambiocontraseña.__NewPswd1.addKeyListener(new java.awt.event.KeyAdapter() {//Evaluacion del las Caja de texto __Pswd del formulario Password
            public void keyTyped(java.awt.event.KeyEvent evt){//y evento de la etiqueta bloq mayus del mismo formulario Pasword
                KeyTipedLetrasNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        cambiocontraseña.__etqBloqMayus.setVisible(true);
                        a=0;
                    }else{
                        cambiocontraseña.__etqBloqMayus.setVisible(false);
                        a=1;
                    }                   
                } 
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    cambiocontraseña.__GUARDAR.requestFocus(true);
                } 
            }            
        });
        this.cambiocontraseña.__NewPswd1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                String contra = cambiocontraseña.__NewPswd1.getText().toString();
                cambiocontraseña.__NewPswd1.setToolTipText("<html><body bgcolor=\"red\"><font color=\"white\">"+contra+"</font></body></html>");
            }
        });
        //Finaliza Cambio de Contrasela               
          //--NuevoUsuario
        this.newusuario.__ACEPTAR.setActionCommand("__GUARDAR_NEW_USER"); 
        this.newusuario.__ACEPTAR.setMnemonic('A');
        this.newusuario.__ACEPTAR.addActionListener(this);
        this.newusuario.__ACEPTAR.addKeyListener(new java.awt.event.KeyAdapter() {            
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();                
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altamodificacion();
                } 
            }
        });
        this.newusuario.__MODIFICARUSER.setActionCommand("__MODIFICAR_USER");
        this.newusuario.__MODIFICARUSER.setMnemonic('M');
        this.newusuario.__MODIFICARUSER.addActionListener(this);        
        this.newusuario.__BLOQUEARUSUARIO.setActionCommand("__BLOQUEAR_USER");
        this.newusuario.__BLOQUEARUSUARIO.setMnemonic('B');
        this.newusuario.__BLOQUEARUSUARIO.addActionListener(this);
        this.newusuario.__CANCELAR.setActionCommand("__CANCELAR_NEW_USER");
        this.newusuario.__CANCELAR.setMnemonic('C');
        this.newusuario.__CANCELAR.addKeyListener(new java.awt.event.KeyAdapter() {           
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();                
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    cancelarnewusuario();
                } 
            }
        });
        this.newusuario.__CANCELAR.addActionListener(this);
        this.newusuario.__NEWUSER.setActionCommand("__NUEVO_USER_NEW");        
        this.newusuario.__NEWUSER.setMnemonic('N');
        this.newusuario.__NEWUSER.addKeyListener(new java.awt.event.KeyAdapter() {            
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();                
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    borrarFormularioNewUser();
                } 
            }
        });
        this.newusuario.__NEWUSER.addActionListener(this);    
        this.newusuario.__nombreUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                 //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        newusuario.__etqBloqMayus.setVisible(true);
                        a=0;
                    }
                    else {
                        newusuario.__etqBloqMayus.setVisible(false);
                        a=1;
                    }
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altamodificacion();
                } 
            }
        }); 
        this.newusuario.__User.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        newusuario.__etqBloqMayus.setVisible(true);
                        a=0;
                    }
                    else {
                        newusuario.__etqBloqMayus.setVisible(false);
                        a=1;
                    }
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altamodificacion();
                } 
            }
        }); 
        this.newusuario.__NewPswd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        newusuario.__etqBloqMayus.setVisible(true);
                        a=0;
                    }
                    else {
                        newusuario.__etqBloqMayus.setVisible(false);
                        a=1;
                    }
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altamodificacion();
                } 
            }
        }); 
        this.newusuario.__NewPswd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                String contra = newusuario.__NewPswd.getText().toString();
                newusuario.__NewPswd.setToolTipText("<html><body bgcolor=\"red\"><font color=\"white\">"+contra+"</font></body></html>");
            }
        });
        this.newusuario.__NewPswd2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        newusuario.__etqBloqMayus.setVisible(true);
                        a=0;
                    }
                    else {
                        newusuario.__etqBloqMayus.setVisible(false);
                        a=1;
                    }
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altamodificacion();
                } 
            }
        });
        this.newusuario.__NewPswd2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                String contra = newusuario.__NewPswd2.getText().toString();
                newusuario.__NewPswd2.setToolTipText("<html><body bgcolor=\"red\"><font color=\"white\">"+contra+"</font></body></html>");
            }
        });
        this.newusuario.__optActivo.addKeyListener(new java.awt.event.KeyAdapter() {           
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();                
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altamodificacion();
                } 
            }
        });
        this.newusuario.__optInactivo.addKeyListener(new java.awt.event.KeyAdapter() {           
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();                
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altamodificacion();
                } 
            }
        });
        this.newusuario.__optMaster.addKeyListener(new java.awt.event.KeyAdapter() {           
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();                
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altamodificacion();
                } 
            }
        });
        this.newusuario.__optJunior.addKeyListener(new java.awt.event.KeyAdapter() {           
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();                
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altamodificacion();
                } 
            }
        });
        this.newusuario.__optKid.addKeyListener(new java.awt.event.KeyAdapter() {           
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();                
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altamodificacion();
                } 
            }
        });
        //nuevo usuarios finaliza
        //NUEVO PROVEEDOR
        this.provedor.__ACEPTARPROVEEDOR.setActionCommand("__ACEPTAR_PROVEEDOR");
        this.provedor.__ACEPTARPROVEEDOR.setMnemonic('A');
        this.provedor.__ACEPTARPROVEEDOR.addActionListener(this);
        this.provedor.__CANCELARPROVEEDOR.setActionCommand("__CANCELAR_PROVEEDOR");
        this.provedor.__CANCELARPROVEEDOR.setMnemonic('C');
        this.provedor.__CANCELARPROVEEDOR.addActionListener(this);
        this.provedor.__NombreProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
              }
            public void keyPressed(java.awt.event.KeyEvent evt){
                   if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altaproveedor();
                } 
            }
            });
        this.provedor.__DireccionProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
              }
             public void keyPressed(java.awt.event.KeyEvent evt){
                   if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altaproveedor();
                } 
            }
            });
        this.provedor.__TelefonoNProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);                
              }
             public void keyPressed(java.awt.event.KeyEvent evt){
                   if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altaproveedor();
                } 
            }
            });
        this.provedor.__RFC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNum(evt);
              }
             public void keyPressed(java.awt.event.KeyEvent evt){
                   if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altaproveedor();
                } 
            }
            });
        //-- TRASPASOS
        this.traspaso.__ACEPTARTRASPASO.setActionCommand("__ACEPTAR_TRASPASO");
        this.traspaso.__ACEPTARTRASPASO.setMnemonic('A');
        this.traspaso.__ACEPTARTRASPASO.addActionListener(this);
        this.traspaso.__CANCELARTRASPASO.setActionCommand("__CANCELAR_TRASPASO");
        this.traspaso.__CANCELARTRASPASO.setMnemonic('C');
        this.traspaso.__CANCELARTRASPASO.addActionListener(this);
        this.traspaso.__CantidadTotal.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyTyped(java.awt.event.KeyEvent evt) {
              KeyTipedNum(evt);
            } 
        });
        this.traspaso.__CostoTras.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyTyped(java.awt.event.KeyEvent evt) {
              KeyTipedNum(evt);
            } 
        });
        this.traspaso.__CantidadPT.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyTyped(java.awt.event.KeyEvent evt) {
              KeyTipedNum(evt);
            } 
        });
        this.traspaso.__ClavePapelTras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                /*if(Integer.parseInt(evt.getSource()+"")==KeyEvent.VK_TAB){
                   evt.consume();
                }*/
                 KeyTipedLetrasNumCar(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
                try {
                    String parametro = traspaso.__ClavePapelTras.getText();
                    String propietario = traspaso.__Origen.getText();
                    if(traspaso.__Origen.getText().equals("")){
                        mensaje(2,"Selecciona el propietario del papel");
                        traspaso.__Origen.requestFocus();
                        return;
                    }
                    ResultSet buscaClavePapel=null;
                    if(traspaso.__BobinaTras.isSelected()){
                        buscaClavePapel = mimodelo.buscaPapelExist(parametro,propietario,"b");
                    }
                    if(traspaso.__HojaTras.isSelected()){
                        buscaClavePapel = mimodelo.buscaPapelExist(parametro,propietario,"h");
                    }
                    __clavePapelT.removeAll();
                    while(buscaClavePapel.next()){
                         __clavePapelT.addItem(buscaClavePapel.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    String clavecl = traspaso.__ClavePapelTras.getText().toString();
                    try{
                        ResultSet costotrass = mimodelo.ultimocosto(clavecl);
                        while(costotrass.next()){
                            //movimientos.__tablaSalida.setValueAt(Double.parseDouble(costo.getString("costo")+""), fila, 3);
                            traspaso.__CostoTras.setText(costotrass.getString("costo"));
                        }
                    }catch(Exception e){
                    }
                } 
            }
        });
        this.traspaso.__BobinaTras.setActionCommand("__BOBINA_TRASPASO");
        this.traspaso.__BobinaTras.addActionListener(this);
        this.traspaso.__HojaTras.setActionCommand("__HOJA_TRASPASO");
        this.traspaso.__HojaTras.addActionListener(this); 
        //las nuevas cajas de texto
        Com_Origen = new TextAutoCompleter(traspaso.__Origen);
        Com_Origen.setMode(0);//infijo
        this.traspaso.__Origen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){}
            
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    
                    String parametro = traspaso.__Origen.getText();
                    //ResultSet buscarProveedores = mimodelo.buscaProveedor(parametro);
                    //ResultSet buscarClientes = mimodelo.buscarCliente(parametro,true);
                    ResultSet buscarPropiedad = mimodelo.buscarPropiedad(parametro, true);
                    Com_Origen.removeAll();
                    while(buscarPropiedad.next()){
                        Com_Origen.addItem(buscarPropiedad.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
                    
        });
        Com_Destino = new TextAutoCompleter(traspaso.__Destino);
        Com_Destino.setMode(0);//infijo
        this.traspaso.__Destino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){}
            
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    
                    String parametro = traspaso.__Destino.getText();
                    //ResultSet buscarProveedores = mimodelo.buscaProveedor(parametro);
                    //ResultSet buscarClientes = mimodelo.buscarCliente(parametro,true);
                    ResultSet buscarPropiedad = mimodelo.buscarPropiedad(parametro,true);
                    Com_Destino.removeAll();
                    while(buscarPropiedad.next()){
                        Com_Destino.addItem(buscarPropiedad.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
                    
        }); 
        //EMERGENTE 
        this.emergente.__ACEPTARFINAL.setActionCommand("__ACEPTAR_EMERGENTE");
        this.emergente.__ACEPTARFINAL.setMnemonic('A');
        this.emergente.__ACEPTARFINAL.addActionListener(this);
        this.emergente.__CANCELARFINAL.setActionCommand("__CANCELAR_EMERGENTE");
        this.emergente.__CANCELARFINAL.setMnemonic('C');
        this.emergente.__CANCELARFINAL.addActionListener(this);        
        this.emergente.__DesperdicioKG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if(emergente.__DesperdicioKG.getText().isEmpty()){
                    emergente.__DesperdicioPliegos.setText("");
                    return;
                }else{
                    dspkg=Double.parseDouble(emergente.__DesperdicioKG.getText());
                    dsppliegos = dspkg/Double.parseDouble(movimientos.__FactorSalidaBobina.getText());
                    emergente.__DesperdicioPliegos.setText(dsppliegos+"");
                    totalemergente();
                }
            }
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    emergente.__TotalSurtidoKG.requestFocus();
                            emergente.__TotalSurtidoPliegos.requestFocus();
                    emergente.__DesperdicioPliegos.requestFocus();
                    emergente.__EmpaqueKG.requestFocus();
                    dsppliegos = Double.parseDouble(emergente.__DesperdicioPliegos.getText());
                } 
            }
        });        
        this.emergente.__EmpaqueKG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if(emergente.__EmpaqueKG.getText().isEmpty()){
                    emergente.__EmpaquePliegos.setText("");
                    return;
                }else{
                    empqkg=Double.parseDouble(emergente.__EmpaqueKG.getText());
                    empqpliegos = empqkg/Double.parseDouble(movimientos.__FactorSalidaBobina.getText());
                    emergente.__EmpaquePliegos.setText(empqpliegos+"");
                    totalemergente();
                }
            }
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    emergente.__EmpaquePliegos.requestFocus();
                    emergente.__CapaKG.requestFocus();
                } 
            }
        });        
        this.emergente.__CapaKG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if(emergente.__CapaKG.getText().isEmpty()){
                    emergente.__CapaPliegos.setText("");
                    return;
                }else{
                    capakg = Double.parseDouble(emergente.__CapaKG.getText());
                    capapliego = capakg/Double.parseDouble(movimientos.__FactorSalidaBobina.getText());
                    emergente.__CapaPliegos.setText(capapliego+"");
                    totalemergente();
                }
            }
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    emergente.__CapaPliegos.requestFocus();
                    emergente.__ConoKG.requestFocus();
                } 
            }
        });
        this.emergente.__ConoKG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedNum(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if(emergente.__ConoKG.getText().isEmpty()){
                    emergente.__ConoPliegos.setText("");
                    return;
                }else{
                    conokg=Double.parseDouble(emergente.__ConoKG.getText());
                    tmpconopliego = conokg/Double.parseDouble(movimientos.__FactorSalidaBobina.getText());
                    emergente.__ConoPliegos.setText(tmpconopliego+"");
                    totalemergente();
                }
            }
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    emergente.__ConoPliegos.requestFocus();
                    emergente.__TotalesKG.requestFocus();
                    emergente.__TotalesPliegos.requestFocus();
                    emergente.__ACEPTARFINAL.requestFocus();
                } 
            }

            
        });
        //REPORTE USUARIOS
        this.reporteuser.__ACEPTAR.setActionCommand("__ACEPTAR_REPORTE_USUARIO");
        this.reporteuser.__ACEPTAR.setMnemonic('A');
        this.reporteuser.__ACEPTAR.addActionListener(this);
        this.reporteuser.__REGRESAR.setActionCommand("__REGRESAR_REPORTE_USUARIO");
        this.reporteuser.__REGRESAR.setMnemonic('R');
        this.reporteuser.__NUEVABUSQUEDA.setActionCommand("__NUEVABUSQUEDA_REPORTE_USUARIO");
        this.reporteuser.__NUEVABUSQUEDA.setMnemonic('N');
        this.reporteuser.__NUEVABUSQUEDA.addActionListener(this);
        this.reporteuser.__REGRESAR.addActionListener(this);
        this.reporteuser.__ReporteNombreUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
             public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                        buscarreporteusuario(2);
                    //eSTO ES UNA MODIFICACION 
                } 
            }
            
        });
        this.reporteuser.reportedate.getDateEditor().getUiComponent().addKeyListener(new java.awt.event.KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {                
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){                     
                        buscarreporteusuario(2);                    
                } 
            }
            @Override
            public void keyReleased(KeyEvent e) {                
            }        
        });              
        //FIN REPORTE USUARIOS
       this.verconsulta.__SALIR.setActionCommand("__REGRESAR");
       this.verconsulta.__SALIR.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
                    limpiarTabla(verconsulta.__tConsulta);
                    verconsulta.setVisible(false);
                }                    
            }
        });             
       this.verconsulta.__SALIR.addActionListener(this); 
       this.verconsulta.__EXCEL.setActionCommand("__EXCEL");
       this.verconsulta.__EXCEL.addActionListener(this);
       this.verconsulta.__CORREO.setActionCommand("__CORREO");
       this.verconsulta.__CORREO.addActionListener(this);
       
       
       //INICIA CORREO
       this.correo.__ABRIREXCEL.setActionCommand("__ABRIREXCEL");
       this.correo.__ABRIREXCEL.addActionListener(this);
       this.correo.__BUSCARARCHIVO.setActionCommand("__BUSCARARCHIVO");
       this.correo.__BUSCARARCHIVO.addActionListener(this);
       this.correo.__ACEPTARCORREO.setActionCommand("__ACEPTARCORREO");
       this.correo.__ACEPTARCORREO.addActionListener(this);
       this.correo.__SALIRCORREO.setActionCommand("__SALIRCORREO");
       this.correo.__SALIRCORREO.addActionListener(this);
       
       //INICIA REPORTES
       
       
    }
    public void totalemergente() {
        try{
            totalkg = totalkkgssss -(conokg+capakg+empqkg+dspkg);
            totalpliego=tmppliegos-(dsppliego+tmpconopliego+capapliego+empqpliegos+dsppliegos);
            emergente.__TotalesKG.setText(totalkg+"");
            emergente.__TotalesPliegos.setText(totalpliego+"");
        }catch(Exception e){
            
        }
    }    
    public void actionPerformed(ActionEvent e) {
        switch(Accion.valueOf(e.getActionCommand())){
            case __SALIR:
                confir = mensajeConfirmacion("¿Desea Salir?","Salida");
                if (confir==JOptionPane.OK_OPTION){
                    this.SalirSistema();
                }
                break;
            case __INICIA_SESION:
                iniciasesion();
                break;
            case __ACEPTAR_FECHA:
                fechaini();
                break;
            case __CANCELAR_FECHA:
                confir = this.mensajeConfirmacion("¿Estas seguro que deseas salir?","ALERTA");
                if (confir==JOptionPane.OK_OPTION){
                    
                    login.setEnabled(true);
                    ap.setEnabled(true);                   
                    movimientos.setEnabled(true);                   
                    reportes.setEnabled(true);                    
                    consultas.setEnabled(true);
                    fecha.setVisible(false);
                     Calendar Cal= Calendar.getInstance();                                                  
                               String hora=Cal.get(Cal.HOUR_OF_DAY)<10 ? "0"+Cal.get(Cal.HOUR_OF_DAY) : ""+Cal.get(Cal.HOUR_OF_DAY);
                               String minute=Cal.get(Cal.MINUTE)<10 ? "0"+Cal.get(Cal.MINUTE) : ""+Cal.get(Cal.MINUTE);
                               horasalida = hora+":"+minute;                
                    boolean registrasalida=mimodelo.horasalida(horasalida,user);
                    if(!user.equals("ROOT")){
                                try {
                                    mimodelo.cerrarsesion(user);
                                } catch (SQLException ex) {
                                    Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                                }
                    }
                            login.__Usuario.requestFocus();
                        }
                break;
            case __MENU_MASTER_ALTA_PAPEL:
                menumaster.dispose();
                ap.setVisible(true);
                ponerfecha();
                ap.setName("Alta de Papel");
                ap.__Bobina.requestFocus();
                ap.__etqAlto_.setVisible(false);
                ap.__etqcm_.setVisible(false);
                ap.__Alto_.setVisible(false);
                addItems("ap");
                break;
            case __MENU_MASTER_MOVIMIENTOS:
                menumaster.dispose();
                movimientos.setVisible(true);
                movimientos.setLocationRelativeTo(null);
                movimientos.setDefaultCloseOperation(0);
                
                ponerfecha();
                addItems("movimientos");
                maximoentrada();
                maximosalida();
                maximosalidah();
                maximosalidab();
                break;
            case __MENU_MASTER_REPORTES:
                menumaster.dispose();
                reportes.setVisible(true);
                reportes.setLocationRelativeTo(null);
                break;
            case __MENU_MASTER_CONSULTAS:
                menumaster.dispose();
                this.addItems("consultas");
                consultas.setVisible(true);
                consultas.setLocationRelativeTo(null);
                break;
            case __MENU_MASTER_CANCELAR:
                confir = this.mensajeConfirmacion("¿Desea Salir?","Salida");
                if (confir==JOptionPane.OK_OPTION){
                    menumaster.dispose();
                    Calendar Cal= Calendar.getInstance();                                                  
                    String hora=Cal.get(Cal.HOUR_OF_DAY)<10 ? "0"+Cal.get(Cal.HOUR_OF_DAY) : ""+Cal.get(Cal.HOUR_OF_DAY);
                    String minute=Cal.get(Cal.MINUTE)<10 ? "0"+Cal.get(Cal.MINUTE) : ""+Cal.get(Cal.MINUTE);
                    horasalida = hora+":"+minute;                
                    boolean registrasalida=mimodelo.horasalida(horasalida,user);
                    if(!user.equals("ROOT")){
                        try {
                            mimodelo.cerrarsesion(user);
                        } catch (SQLException ex) {
                            Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    login.show();
                }
                break;
            case __OPCION_BOBINA:
                this.ap.__Alto_.setVisible(false);
                this.ap.__etqAlto_.setVisible(false);
                this.ap.__etqcm_.setVisible(false);
                this.ap.__Marca.setVisible(true);
                this.ap.__NewMarca.setVisible(true);
                this.ap.__etqnamemarca.setVisible(true);
                break;
            case __OPCION_HOJA:
                this.ap.__Alto_.setVisible(true);
                this.ap.__etqAlto_.setVisible(true);
                this.ap.__etqcm_.setVisible(true);
                this.ap.__Marca.setVisible(false);
                this.ap.__NewMarca.setVisible(false);
                this.ap.__etqnamemarca.setVisible(false);
                break;
            
            case __ALTAS_REGRESAR:
                confir = mensajeConfirmacion("¿Realmente Deseas Regresar al Menú Principal?","Salida");
                                 if (confir == JOptionPane.OK_OPTION){
                                    this.ap.dispose();
                                    this.menumaster.setVisible(true);
                                    this.borrarFormularioAltaPapel();
                                 }
                break;
            case __ALTAPAPEL_GUARDAR:
                String clave = null;
                int tipopapel,idnombrepapel,idcolor,altacara;
                if(this.ap.__Bobina.isSelected()){
                    tipopapel = 1;
                }else if(this.ap.__Hoja.isSelected()){
                   tipopapel = 2; 
                }else{
                    mensaje(3,"Debe Seleccionar un Tipo de Papel");
                    break;
                }
                int altaclase = 0;
                String altanombre = this.ap.__NombrePapel.getSelectedItem()+"";
                if (altanombre.equals("Selecciona...")){
                    mensaje(3,"Debe Seleccionar el Nombre del Papel");
                    break;
                }else{
                    idnombrepapel=mimodelo.buscaridNombrePapel(altanombre);
                }
                idnombrepapel = this.busquedaid("nombre");
                if(altanombre.equals("Sulfatada")||altanombre.equals("Polypap")||altanombre.equals("Cartulina Bristol")||altanombre.equals("Adhesivos Couche")){
                    this.ap.__GrPts.setSelectedItem(2);
                }
                String foraltacolor = this.ap.__ColorPapel.getSelectedItem()+"";
                if( foraltacolor.equals("Selecciona...")){
                    mensaje(3,"Debe Seleccionar un Color de Papel");
                    break;  
                }else{
                    idcolor = mimodelo.buscaridcolor(foraltacolor);
                }              
                idcolor = this.busquedaid("color");
                if(this.ap.__Cara1.isSelected()){
                    altacara = 1;
                }else if(this.ap.__Cara2.isSelected()){
                    altacara =2;
                }else{
                    mensaje(3,"Debe Seleccionar Cuantas Caras Tiene el Papel");
                    break;
                }
                String coolor = foraltacolor.substring(0, 3).toUpperCase();
                String ancho = this.ap.__Ancho.getText();
                if(ancho.isEmpty()){
                    mensaje(3,"Debe Especificar el Ancho de Papel");
                    break;
                }
                String alto = null;
                if(ap.__Hoja.isSelected()){
                alto = this.ap.__Alto_.getText();
                    if(alto.isEmpty()){
                        mensaje(3,"Debe Especificar el Alto de Papel");
                        break;
                    }
                }
                String gramaje = this.ap.__Gramaje.getText();
                if(gramaje.isEmpty()){
                    mensaje(3,"Debe Especificar el Gramaje de Papel");
                    break;
                }
                if(Integer.parseInt(gramaje)<100 ){
                    gramaje="0"+gramaje;
                }
                int altapropiedad = Integer.parseInt(this.ap.__Propiedad.getSelectedIndex()+"");
                String propiedad ;
                
                if( altapropiedad == 0){
                    mensaje(3,"Debe Seleccionar un Propiedad de Papel");
                    break;  
                }else{
                    propiedad = this.ap.__Propiedad.getSelectedItem()+"";

                }
                altapropiedad = this.busquedaid("propiedad");
                if(ap.__GrPts.getSelectedIndex()==1){
                    gramaje = gramaje + "PTS";
                }
                String altamarca="";
                if(ap.__Bobina.isSelected()){
                altamarca = this.ap.__Marca.getSelectedItem()+"";
                    if( altamarca.equals("Selecciona...")){
                        mensaje(3,"Debe Seleccionar una Marca de Papel");
                        break;  
                    }
                }
                int idmarca = this.busquedaid("marca");
                if(ap.__Bobina.isSelected()){
                    clave = "0"+tipopapel+"-"+idnombrepapel+"-"+coolor+"-"+altacara+"-"+ancho+gramaje+"-"+propiedad+"-"+altamarca;
                    ap.__etqClave.setText(clave);
                    alto="0";
                }
                if(ap.__Hoja.isSelected()){
                    clave = "0"+tipopapel+"-"+idnombrepapel+"-"+coolor+"-"+altacara+"-"+alto+ancho+""+gramaje+"-"+propiedad;
                    ap.__etqClave.setText(clave);
                }
                confir = this.mensajeConfirmacion("La clave a guardar es: " + clave +" ¿Esta correcta?", "Confirmación");
                if(confir==JOptionPane.OK_OPTION){
                   String altapApel=mimodelo.altaPapel(clave, idmarca, idnombrepapel, idcolor, altacara, tipopapel, altaclase, ancho, alto, gramaje+"");
                   if(altapApel.equals("Duplicate entry '"+clave+"' for key 'Clave'")){
                       mensaje(3,"Error Ya Se Capturo Este Papel");
                       break;
                   }
                   if(altapApel.equals("true")){
                       boolean existencia = mimodelo.nuevaexistencia(clave,altapropiedad);
                        if(existencia==true){
                            mensaje(1,"Alta Correcta de Papel");
                        }else{
                            mensaje(3,"Ocurrio un Error al Dar de Alta el Nuevo Papel");
                        }
                        this.borrarFormularioAltaPapel();
                        this.addItems("ap"); 
                   }
                }
                if(this.abrirpantalla==0){
                    
                }else{
                    abrirpantalla=0;
                    ap.dispose();
                }                    
                break;
            case __ALTAPAPEL_NUEVO:
                confir = mensajeConfirmacion("¿Realmente Deseas Borrar el Formulario?","Alerta");
                if (confir == JOptionPane.OK_OPTION){
                   borrarFormularioAltaPapel();
                   this.addItems("altapapel");
                }
                break;
            case __ACEPTAR_BAJA:
                String claveBorrar = bajaP.__ClavePapelBaja.getText();
                if(claveBorrar.isEmpty()||claveBorrar.equals(null)){
                    mensaje(2,"Ingresa la Clave de Papel a Eliminar");
                }else{
                    try {
                        ResultSet buscaClavePapela = mimodelo.buscaClavea(claveBorrar);
                        if(buscaClavePapela.next()){
                            confir=this.mensajeConfirmacion("Realmente Deseas Borrar el Papel "+claveBorrar, "Borrar Papel");
                            if(confir == JOptionPane.OK_OPTION){
                                boolean borrado = mimodelo.bajaPapel(claveBorrar);
                                if(borrado){
                                    mensaje(1,"Eliminacion Correcta");
                                    bajaP.__ClavePapelBaja.setText("");
                                    break;
                                }
                            }
                        }else{
                            mensaje(2,"El papel no Existe, Verifica la Clave");
                            break;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case __CANCELAR_BAJA:
                if(bajaP.__ClavePapelBaja.getText().isEmpty()){
                    ap.setEnabled(true);
                    movimientos.setEnabled(true);
                    reportes.setEnabled(true);
                    consultas.setEnabled(true);
                    bajaP.setVisible(false);
                }else{
                    confir=this.mensajeConfirmacion("¿Realmente Deceas Regresar?", "Regresar");
                    if(confir==JOptionPane.OK_OPTION){
                        bajaP.__ClavePapelBaja.setText("");
                        ap.setEnabled(true);
                        movimientos.setEnabled(true);
                        reportes.setEnabled(true);
                        consultas.setEnabled(true);
                        bajaP.setVisible(false);
                    }
                }
                break;          
                //--MENUS DE TOODOS LOS FORMULARIOS
            case __MENU_ANTERIOR:                
                    regresar();
                break;
            case __MENU_ALTA:
                switch(cargo){
                    case 1:
                        confir = mensajeConfirmacion("¿Realmente Deseas ir a Alta de Papel?","Salida");
                            if (confir == JOptionPane.OK_OPTION){
                            this.ap.setVisible(true);
                            this.ap.setLocationRelativeTo(null);
                            movimientos.dispose();
                            consultas.dispose();
                            reportes.dispose();
                            reporteuser.dispose();
                            verconsulta.dispose();
                            this.addItems("ap");
                            }
                    break;                        
                    case 2:
                        mensaje(2,"No Hay Acceso a esta Información");
                    break;
                    case 3:
                        mensaje(2,"No Hay Acceso a esta Información");
                        break;
                }
                break;
            case __MENU_CONSULTAS:
                switch(cargo){
                    case 1:
                        confir = mensajeConfirmacion("¿Realmente Deseas ir a Consultas?","Salida");
                        if (confir == JOptionPane.OK_OPTION){
                            this.addItems("consultas");
                            consultas.setVisible(true);
                            consultas.setLocationRelativeTo(null);
                            this.ap.dispose();
                            reportes.dispose();
                            movimientos.dispose();
                            reporteuser.dispose();
                            verconsulta.dispose();
                        }                    
                        break;
                    case 2:
                         confir = mensajeConfirmacion("¿Realmente Deseas ir a Consultas?","Salida");
                        if (confir == JOptionPane.OK_OPTION){
                            this.addItems("consultas");
                            consultas.setVisible(true);
                            consultas.setLocationRelativeTo(null);
                            this.ap.dispose();
                            reportes.dispose();
                            movimientos.dispose();
                            reporteuser.dispose();
                            verconsulta.dispose();
                        }
                        break;
                    case 3:
                         confir = mensajeConfirmacion("¿Realmente Deseas ir a Consultas?","Salida");
                        if (confir == JOptionPane.OK_OPTION){
                            this.addItems("consultas");
                            consultas.setVisible(true);
                            consultas.setLocationRelativeTo(null);
                            this.ap.dispose();
                            reportes.dispose();
                            movimientos.dispose();
                            reporteuser.dispose();
                            verconsulta.dispose();
                        }
                        break;               
                }
                break;
                case __MENU_REPORTES:
                switch(cargo){
                    case 1:
                        confir = mensajeConfirmacion("¿Realmente Deseas ir a Generar un Reporte?","Salida");
                            if (confir == JOptionPane.OK_OPTION){
                            reportes.setVisible(true);
                            reportes.setLocationRelativeTo(null);
                            movimientos.dispose();
                            this.ap.dispose();
                            consultas.dispose();
                            reporteuser.dispose();
                            verconsulta.dispose();
                            }
                        break;
                    case 2:
                         confir = mensajeConfirmacion("¿Realmente Deseas ir a Generar un Reporte?","Salida");
                            if (confir == JOptionPane.OK_OPTION){
                            reportes.setVisible(true);
                            reportes.setLocationRelativeTo(null);
                            movimientos.dispose();
                            this.ap.dispose();
                            consultas.dispose();
                            reporteuser.dispose();
                            verconsulta.dispose();
                            }
                        break;
                     case 3:
                        confir = mensajeConfirmacion("¿Realmente Deseas ir a Generar un Reporte?","Salida");
                            if (confir == JOptionPane.OK_OPTION){
                            reportes.setVisible(true);
                            reportes.setLocationRelativeTo(null);
                            movimientos.dispose();
                            this.ap.dispose();
                            consultas.dispose();
                            reporteuser.dispose();
                            verconsulta.dispose();
                            }
                        break; 
                }                                
                break;
                case __MENU_ABRIR_ARCHIVO:
                    try {
                    File archivo = this.archivo(2);
                    if(archivo==null){
                        break;
                    }
                    String fil = archivo.getAbsolutePath();
                    if(!fil.isEmpty()){
                        File f = new File(fil);
                        Desktop.getDesktop().open(f);
                    }else{
                        mensaje(3,"Debes Seleccionar el Archivo para Abrir");
                    }
                } catch (IOException ex) {
                    mensaje(3,ex.getMessage());
                }
                break;
                case __MENU_CERRAR_SESION:
                    confir = mensajeConfirmacion("¿Realmente Desea Cerrar la Sesión?","Salida");
                    if (confir == JOptionPane.OK_OPTION){
                        try {
                            login.setVisible(true);
                            login.setLocationRelativeTo(null);
                            this.ap.dispose();
                            movimientos.dispose();
                            consultas.dispose();
                            reportes.dispose();
                            reporteuser.dispose();
                            mimodelo.cerrarsesion(user);
                            borrarFormularioNewUser();
                            borrarFormularioAltaPapel();
                            borrarFormularioMovimientosPapel();
                            borrarFormularioProveedor();
                            borrarFormularioConsultas();
                            borrarFormularioEmergente();
                        } catch (SQLException ex) {
                            mensaje(3,ex.getMessage());
                        }
                    }
                break;
                case __MENU_SALIR:
                        confir = mensajeConfirmacion("¿Realmente Desea Salir del Sistema?","Salida");
                            if (confir == JOptionPane.OK_OPTION){
                            SalirSistema();
                            }
                break;
                case __MENU_MOV_PAPEL:
                    if(cargo!=3){
                      confir = mensajeConfirmacion("¿Realmente Deseas ir a Movimientos?","Salida");
                        if (confir == JOptionPane.OK_OPTION){
                            movimientos.setVisible(true);
                            movimientos.setLocationRelativeTo(null);
                            consultas.dispose();                        
                            this.ap.dispose();
                            reportes.dispose();
                            ponerfecha();
                            movimientos.__TipoEntrada.requestFocus();
                            addItems("movimientos");
                            maximoentrada();
                            maximosalida();
                            maximosalidah();
                            maximosalidab();
                        }
                    }else{
                        mensaje(2,"No Hay Acceso a esta Información");
                        break;
                    }
                break;
                case __MENU_NEWUSER:                   
                    Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);  
                    newusuario.__etqBloqMayus.setVisible(true);
                        if(cargo!=3 && cargo!=2){
                            ap.setEnabled(false);
                            movimientos.setEnabled(false);
                            reportes.setEnabled(false);
                            consultas.setEnabled(false);
                            newusuario.setVisible(true);
                            newusuario.setLocationRelativeTo(null);
                         }else{
                            mensaje(2,"No Hay Acceso a esta Información");
                        }                        
                break;
                case __MENU_CONTRASEÑA:
                    if(menumaster.__etqUsuarioMenuMaster.getText().equals("ROOT")){
                        mensaje(2,"No Puedes Cambiar la Contraseña a Root pero Gracias por Intentarlo");
                    }else{
                    Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    ap.setEnabled(false);
                    movimientos.setEnabled(false);
                    reportes.setEnabled(false);
                    consultas.setEnabled(false);
                    cambiocontraseña.__etqBloqMayus.setVisible(true);
                    cambiocontraseña.show();
                    cambiocontraseña.setLocationRelativeTo(null);
                    }
                break;
                case __MENU_BAJA_PAPEL:
                    switch(cargo){
                        case 1:  
                                ap.setEnabled(false);
                                movimientos.setEnabled(false);
                                reportes.setEnabled(false);
                                consultas.setEnabled(false);
                                bajaP.setVisible(true);
                                bajaP.setLocationRelativeTo(null);
                            break;
                        case 2:
                            mensaje(2,"No Hay Acceso a esta Información");
                            break;
                        case 3:
                            mensaje(2,"No Hay Acceso a esta Información");
                            break;
                    } 
                break;   
                case __MENU_ACERCADE:
                    mensaje(1,"Dispaper \nBy: =F@vo0R!to0= && yo0po0");
                break;
                case __MENU_BACKUP:
                    //respaldo bd
                    if(cargo==1){
                        mimodelo.bp(fech+"desdemenu");
                        this.enviaarchivo("C:\\iexsa\\backups\\dump"+fech+"desdemenu.sql","dispaper.iexsa@gmail.com" ,"Backup de la base de datos");
                        File fichero = new File("C:\\iexsa\\backups\\dump"+fech+"desdemenu.sql");
                        fichero.delete();
                        mensaje(1,"Backup de la Base de Datos Correcta");
                    }else{
                        mensaje(2,"No Hay Acceso a esta Información");
                    }
                break;
                case __MENU_UBICACION:
                    if(cargo==1){                        
                        movimientos.setEnabled(false);
                        ubi.setVisible(true);                        
                    }
                    break;
                case __MENU_FECHA:
                    pedirfecha=1;
                    ap.setEnabled(false);
                    movimientos.setEnabled(false);
                    reportes.setEnabled(false);
                    consultas.setEnabled(false);
                    fecha.setVisible(true);
                    fecha.setLocationRelativeTo(null);
                    
                break; 
                case __MENU_DATOS:
                    this.mimodelo.abrirReporte("Datos.jrxml",new HashMap());
                    break;
                case __MENU_REPORTE_USUARIOS:
                    switch(cargo){
                        case 1:                                                       
                                reporteuser.setVisible(true);
                                reporteuser.setLocationRelativeTo(null);
                                this.buscarreporteusuario(1);
                                ap.setEnabled(false);
                                movimientos.setEnabled(false);
                                reportes.setEnabled(false);
                                consultas.setEnabled(false);
                            break;
                        case 2:
                            mensaje(2,"No Hay Acceso a esta Información");
                            break;
                        case 3:
                            mensaje(2,"No Hay Acceso a esta Información");
                            break;
                    }                                                    
                break;
                case __MENU_CERRAR_SESIONES:
                    switch(cargo){
                        case 1: 
                            int SesionCerrada=0;                            
                            confir= mensajeConfirmacion("Realmente Desea Cerrar Todas las Sesiones ","Cerrar Sesiones");
                            if(confir==JOptionPane.OK_OPTION){
                                boolean SC=mimodelo.cerrarsesiones(SesionCerrada);
                                    if(SC==true){
                                        mensaje(1,"Sesiones Cerradas con Exito");
                                        tipoalta=0;
                                    }else{
                                        mensaje(3,"Ocurrio un Error al Cerrar las Sesiones");
                                    }
                            }
                            break;
                        case 2:
                            mensaje(2,"No Hay Acceso a esta Información");
                            break;
                        case 3:
                            mensaje(2,"No Hay Acceso a esta Información");
                            break;
                    }
                    break;
                case __MENU_MOV_TRASPASO:
                    ap.setEnabled(false);
                    movimientos.setEnabled(false);
                    reportes.setEnabled(false);
                    consultas.setEnabled(false);
                    traspaso.setVisible(true);
                    traspaso.setLocationRelativeTo(null);
                    traspaso.__BobinaTras.requestFocus(true);
        try {
            String foliotraspaso= mimodelo.buscarFolioMaxTraspaso();
            traspaso.__folioT.setText(foliotraspaso);
        } catch (SQLException ex) {
            Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
                break;          
                    //--FIN DE LOS MENUS DE LOS FORMULARIOS     
                case __ACEPTAR_MOVIMIENTOS_SALIDA:
                    modificarsalida();
                    
                    break;
                case __MODIFICACIONSALIDA:
                    this.modificarsalida=1;
                buscarfolio = JOptionPane.showInputDialog("Folio","Ingresa el Folio de la Salida a Modificar");
                if(buscarfolio==null || buscarfolio.length()<3){
                    mensaje(2,"Intenta otra vez");
                    break;
                }
                try {
                   ResultSet salida = mimodelo.buscarSalida(buscarfolio); 
                   int idfolio=0;
                   if(salida.next()){
                       movimientos.__FolioSalida.setText(buscarfolio);
                       salida.beforeFirst();
                       while(salida.next()){
                           movimientos.__TipoSalida.setText(this.busquedaNombre("tipo_salida", salida.getInt("tipo_salida")));
                           movimientos.__PropietarioSalida.setText(this.busquedaNombre("id_propietario", salida.getInt("id_propietario")));
                           movimientos.__ClientSalida.setText(this.busquedaNombre("cliente", salida.getInt("cliente")));
                           movimientos.__ProvSalida.setText(this.busquedaNombre("id_proveedores", salida.getInt("id_proveedores")));
                           movimientos.__OrdenProduccionSalida.setText(salida.getString("orden_produccion"));
                           movimientos.__OrdenCompraSalida.setText(salida.getString("orden_compra"));
                           movimientos.__documentoSalida.setText(salida.getString("documento_salida"));
                           if(salida.getString("TURNO1").equals("t1")){
                               movimientos.__chkTurno1Salida.setSelected(true);
                           }
                           if(salida.getString("TURNO2").equals("t2")){
                               movimientos.__chkTurno2Salida.setSelected(true);
                           }
                           if(salida.getString("TURNO3").equals("t3")){
                               movimientos.__chkTurno3Salida.setSelected(true);
                           }
                           idfolio= salida.getInt("id_salida");
                       }
                       this.movimientos.__MenuMovimiento.setEnabled(false);
                       this.movimientos.__MODIFICACIONSALIDA.setEnabled(false);
                       this.movimientos.__Archivo.setEnabled(false);
                       this.movimientos.__Edicion.setEnabled(false);
                        this.movimientos.JPanel.setEnabledAt(0, false);
                        this.movimientos.JPanel.setEnabledAt(1, true);
                        this.movimientos.JPanel.setEnabledAt(2, false);
                        this.movimientos.JPanel.setEnabledAt(3, false);
                   }else{
                       mensaje(2,"La Entrada no Existe");
                       break;
                   }
                   ResultSet detallesalida = mimodelo.buscarDetalleSalida(idfolio);
                   if(detallesalida.next()){
                       detallesalida.beforeFirst();
                       int a=0;
                       while(detallesalida.next()){
                           
                           
                            idsalidas[a]=detallesalida.getString("iddetallesalida");
                            movimientos.__tablaSalida.setValueAt(detallesalida.getString("clavepapel"), a, 0);
                            movimientos.__tablaSalida.setValueAt(detallesalida.getInt("total_salida"), a, 1);
                            int totalsumar=detallesalida.getInt("total_salida");
                            movimientos.__tablaSalida.setValueAt(detallesalida.getInt("cantidad_salida"), a, 2);
                            int cantidadsumar=detallesalida.getInt("cantidad_salida");
                            ResultSet bep= mimodelo.buscarExistenciaPapel(detallesalida.getString("clavepapel"));
                            while(bep.next()){
                                totalbd=Double.parseDouble(bep.getString("presentacion"));
                                cantidadbd=Double.parseDouble(bep.getString("cantidad"));
                            }

                            String entradas[] = detallesalida.getString("entradas").split(",");
                            j = entradas.length;
                            antipeps2(entradas,totalsumar,cantidadsumar);
                            movimientos.__tablaSalida.setValueAt(Double.parseDouble(detallesalida.getString("costo")), a, 3);
                            movimientos.__tablaSalida.setValueAt(Double.parseDouble(detallesalida.getString("totalcosto")), a, 4);
                               mimodelo.sumarexistencia(detallesalida.getString("clavepapel"));
                           
                           ResultSetMetaData metaData = detallesalida.getMetaData();
                           int numcol = metaData.getColumnCount();
                           nombrecolumnas = new String[numcol];
                           for(int i=2;i<numcol;i++){
                               this.nombrecolumnas[i]=metaData.getColumnLabel(i+1);
                           }
                           a++;
                       }
                   }
                   movimientos.__TipoSalida.requestFocus();
                }catch(Exception ex){
                    //mensaje(3,ex.getMessage());
                    ex.printStackTrace();
                }
                    break;
            case __ACEPTAR_MOVIMIENTOS_ENTRADA:
                    modificarentrada();
                    
                break;
            case __MODIFICACIONENTRADA:
                this.modificarentrada=1;
                
                buscarfolio = JOptionPane.showInputDialog("Folio","Ingresa el Folio de la Entrada a Modificar");
                if(buscarfolio==null || buscarfolio.length()<3){
                    mensaje(2,"Intenta otra vez");
                    break;
                }
                try {
                   ResultSet entrada = mimodelo.buscarEntrada(buscarfolio); 
                   int idfolio=0;
                   if(entrada.next()){
                       movimientos.__FolioEntrada.setText(buscarfolio);
                       entrada.beforeFirst();
                       while(entrada.next()){
                           movimientos.__TipoEntrada.setText(this.busquedaNombre("tipo_entrada", entrada.getInt("tipo_entrada")));
                           movimientos.__PropietarioEntr.setText(this.busquedaNombre("id_propietario", entrada.getInt("id_propietario")));
                           movimientos.__ClientEntr.setText(this.busquedaNombre("cliente", entrada.getInt("cliente")));
                           movimientos.__ProvEntr.setText(this.busquedaNombre("id_proveedores", entrada.getInt("id_proveedores")));
                           movimientos.__OrdenProduccionEntr.setText(entrada.getString("orden_produccion"));
                           movimientos.__OrdenCompraEntr.setText(entrada.getString("orden_compra"));
                           movimientos.__documentoEntr.setText(entrada.getString("documento_entrada"));
                           if(entrada.getString("TURNO1").equals("t1")){
                               movimientos.__chkTurno1Entr.setSelected(true);
                           }
                           if(entrada.getString("TURNO2").equals("t2")){
                               movimientos.__chkTurno2Entr.setSelected(true);
                           }
                           if(entrada.getString("TURNO3").equals("t3")){
                               movimientos.__chkTurno3Entr.setSelected(true);
                           }
                           idfolio= entrada.getInt("id_entrada");
                       }
                       this.movimientos.__MenuMovimiento.setEnabled(false);
                       this.movimientos.__MODIFICACIONENTRADA.setEnabled(false);
                       this.movimientos.__Archivo.setEnabled(false);
                       this.movimientos.__Edicion.setEnabled(false);
                        this.movimientos.JPanel.setEnabledAt(1, false);
                        this.movimientos.JPanel.setEnabledAt(2, false);
                        this.movimientos.JPanel.setEnabledAt(3, false);
                   }else{
                       mensaje(2,"La Entrada no Existe");
                       break;
                   }
                   ResultSet detalleentrada = mimodelo.buscarDetalleEntrada(idfolio);
                   if(detalleentrada.next()){
                       detalleentrada.beforeFirst();
                       int d =0;
                       while(detalleentrada.next()){
                               identradas[d]=detalleentrada.getString("id_detalleentrada");
                               System.out.println(identradas[d]+" --- esta es la entrada");
                               movimientos.__tablaEntrada.setValueAt(detalleentrada.getString("clave_papel"), d, 0);
                               movimientos.__tablaEntrada.setValueAt(detalleentrada.getInt("total_entrada"), d, 1);
                               restarcantidad=Double.parseDouble(detalleentrada.getString("total_entrada"));
                               movimientos.__tablaEntrada.setValueAt(detalleentrada.getInt("cantidad_entrada"), d, 2);
                               restartotal=Double.parseDouble(detalleentrada.getString("cantidad_entrada"));
                               ResultSet bep= mimodelo.buscarExistenciaPapel(detalleentrada.getString("clave_papel"));
                               int totaltemporal,cantidadtemporal;
                               while(bep.next()){
                                   totalbd=Double.parseDouble(bep.getString("presentacion"));
                                   cantidadbd=Double.parseDouble(bep.getString("cantidad"));
                                   
                               }
                               newcantidad=cantidadbd-restarcantidad;
                               newtotal=totalbd-restartotal;
//                               mimodelo.nuevaExistencia(newcantidad+"", detalleentrada.getString("clave_papel"),newtotal+"");
                               movimientos.__tablaEntrada.setValueAt(detalleentrada.getString("ubicacion"), d, 3);
                               movimientos.__tablaEntrada.setValueAt(Double.parseDouble(detalleentrada.getString("costo")), d, 4);
                               movimientos.__tablaEntrada.setValueAt(Double.parseDouble(detalleentrada.getString("total_costo")), d, 5);
                               totaltemporal=detalleentrada.getInt("total_temporal");
                               cantidadtemporal=detalleentrada.getInt("cantidad_temoporal");
                               int a =totaltemporal-totaltemporal;
                               int b =cantidadtemporal-cantidadtemporal;
                               mimodelo.updateteporalde(a, b, identradas[d]);
                               mimodelo.sumarexistencia(detalleentrada.getString("clave_papel"));
                           ResultSetMetaData metaData = detalleentrada.getMetaData();
                           int numcol = metaData.getColumnCount();
                           nombrecolumnas = new String[numcol];
                           for(int i=2;i<numcol;i++){
                               this.nombrecolumnas[i]=metaData.getColumnLabel(i+1);
                           }
                           d++;
                       }
                   }
                }catch(Exception ex){
                    mensaje(3,ex.getMessage());
                } 
                break;
            case __MODIFICACIONSALHOJ:
                this.modificarsalidah=1;
                buscarfolio = JOptionPane.showInputDialog("Folio","Ingresa el Folio de la Salida de Hoja a Modificar");
                if(buscarfolio==null || buscarfolio.length()<3){
                    mensaje(2,"Intenta otra vez");
                    break;
                }
                try {
                   ResultSet RS = mimodelo.buscarSalidaH(buscarfolio); 
                   int idfolio=0;
                   movimientos.__FolioSalidaHoja.setText(buscarfolio);
                   if(RS.next()){
                       RS.beforeFirst();
                       while(RS.next()){
                           ResultSet tiposalidaaa = mimodelo.buscaTipoSalida(RS.getInt("tipo_salida"));
                           while(tiposalidaaa.next()){
                               movimientos.__TipodeSalidaH.setText(tiposalidaaa.getString("descripcion"));
                           }
                           ResultSet clienteee= mimodelo.buscarCliente(RS.getInt("id_cliente"));
                           while(clienteee.next()){
                               movimientos.__ClienteSalidaH.setText(clienteee.getString("descripcion"));
                           }
                           ResultSet propietariooo= mimodelo.buscarPropiedad(RS.getInt("id_propietario"));
                           while(propietariooo.next()){
                               movimientos.__PropietarioSalidaH.setText(propietariooo.getString("descripcion"));
                           }
                           ResultSet maquinaaa= mimodelo.buscarMaquina(RS.getInt("id_maquina"));
                           while(maquinaaa.next()){
                               movimientos.__MaquinaSalidaH.setText(maquinaaa.getString("descripcion"));
                           }
                           movimientos.__TituloSalidaHoja.setText(RS.getString("titulo"));
                           movimientos.__EstandarProduccionSalidaHoja.setText(RS.getString("estandar_produccion"));
                           movimientos.__OPSalidaHoja.setText(RS.getString("orden_produccion"));
                           if(RS.getString("TURNO1").equals("t1")){
                               movimientos.__chkTurno1SalHoja.setSelected(true);
                           }
                           if(RS.getString("TURNO2").equals("t2")){
                               movimientos.__chkTurno2SalHoja.setSelected(true);
                           }
                           if(RS.getString("TURNO3").equals("t3")){
                               movimientos.__chkTurno3SalHoja.setSelected(true);
                           }
                           idfolio= RS.getInt("id_salida");
                       } 
                       this.movimientos.__MenuMovimiento.setEnabled(false);
                    this.movimientos.__Archivo.setEnabled(false);
                    this.movimientos.__Edicion.setEnabled(false);
                    this.movimientos.__MODIFICACIONH.setEnabled(false);
                    this.movimientos.JPanel.setEnabledAt(0, false);
                    this.movimientos.JPanel.setEnabledAt(1, false);
                    this.movimientos.JPanel.setEnabledAt(2, true);
                    this.movimientos.JPanel.setEnabledAt(3, false);
                   }else{
                       mensaje(2,"La Salida no Existe");
                       break;
                   }
                   ResultSet detallesalidah = mimodelo.buscarDetalleSalidaH(idfolio);
                   if(detallesalidah.next()){
                       detallesalidah.beforeFirst();
                       int a=0;
                       while(detallesalidah.next()){
                           movimientos.__tablaSalidaHoja.setValueAt(detallesalidah.getString("clave_papel"), a, 0);
                           idsalidash[a]=detallesalidah.getString("id_salida");
                           movimientos.__tablaSalidaHoja.setValueAt(detallesalidah.getInt("total_hojas"), a, 1);
                           int totalsumar=detallesalidah.getInt("total_hojas");
                           movimientos.__tablaSalidaHoja.setValueAt(detallesalidah.getInt("cantidad"), a, 2);
                           int cantidadsumar=detallesalidah.getInt("cantidad");
                           movimientos.__tablaSalidaHoja.setValueAt(Double.parseDouble(detallesalidah.getString("costo")),a,3);
                           movimientos.__tablaSalidaHoja.setValueAt(Double.parseDouble(detallesalidah.getString("totalcosto")),a,4);
                           ResultSet bep = mimodelo.buscarExistenciaPapel(detallesalidah.getString("clave_papel"));
                           while(bep.next()){
                               totalbd=Double.parseDouble(bep.getString("presentacion"));
                               cantidadbd=Double.parseDouble(bep.getString("cantidad"));
                           }
                           String entradas[] = detallesalidah.getString("identradas").split(",");
                           j = entradas.length;
                           antipeps2(entradas,totalsumar,cantidadsumar);
                           mimodelo.sumarexistencia(detallesalidah.getString("clave_papel"));
                           ResultSetMetaData metaData = detallesalidah.getMetaData();
                           int numcol = metaData.getColumnCount();
                           nombrecolumnas = new String[numcol];
                           for(int i=2;i<numcol;i++){
                               this.nombrecolumnas[i]=metaData.getColumnLabel(i+1);
                           }
                           a++;
                       }
                   }
                }catch(Exception ex){
                    mensaje(3,ex.getMessage());
                } 
                break;
                //DESDE AQUI EMPEZE HOY 11 04 2014
            case __ACEPTAR_MOVIMIENTOS_SALIDAH:
                modificacionsalh();
                 
                break;
                //AQUI FINALICE HOY 11 04 2014 IRVING
            case __MODIFICACIONSALBOB:
                this.borrarFormularioMovimientosPapel();
                this.modificarsalidab=1;
                buscarfolio = JOptionPane.showInputDialog("Folio","Ingresa el Folio de la Salida de bobina a Modificar");
                if(buscarfolio==null || buscarfolio.length()<3){
                    mensaje(2,"Intenta otra vez");
                    break;
                }
                try {
                   ResultSet RS = mimodelo.buscarSalidaB(buscarfolio); 
                   int idfolio=0;
                   if(RS.next()){
                       RS.beforeFirst();
                       while(RS.next()){
                           movimientos.__FolioSalidaBobina.setText(buscarfolio);
                           ResultSet tiposalidaaa = mimodelo.buscaTipoSalida(RS.getInt("tipo_salida"));
                           while(tiposalidaaa.next()){
                               movimientos.__TipodeSalidaB.setText(tiposalidaaa.getString("descripcion"));
                           }
                           ResultSet clienteee= mimodelo.buscarCliente(RS.getInt("id_cliente"));
                           while(clienteee.next()){
                               movimientos.__ClienteSalidaB.setText(clienteee.getString("descripcion"));
                           }
                           ResultSet propietariooo= mimodelo.buscarPropiedad(RS.getInt("id_propietario"));
                           while(propietariooo.next()){
                               movimientos.__PropietarioSalidaB.setText(propietariooo.getString("descripcion"));
                           }
                           ResultSet maquinaaa= mimodelo.buscarMaquina(RS.getInt("id_maquina"));
                           while(maquinaaa.next()){
                               movimientos.__MaquinaSalidaB.setText(maquinaaa.getString("descripcion"));
                           }
                           movimientos.__TituloSalidaBobina.setText(RS.getString("titulo"));
                           movimientos.__OPSalidaBobina.setText(RS.getString("orden_produccion"));
                           movimientos.__FactorSalidaBobina.setText(RS.getString("factor"));
                           movimientos.__PliegoSalidaBobina.setText(RS.getString("pliego"));
                           
                           movimientos.__TotalKGSalidaBobina1.setText(RS.getString("totalkg"));
                           movimientos.__TiroXPliegoSalidaBobina1.setText(RS.getString("tiroxpliego"));
                           movimientos.__KGPliegoSalidaBobina1.setText(RS.getString("pliegokgs"));
                           movimientos.__PliegodeAjusteSalidaBobina1.setText(RS.getString("plIegoajuste"));
                           movimientos.__KGAjusteSalidaBobina1.setText(RS.getString("ajustekgs"));
                           if(RS.getString("TURNO1").equals("t1")){
                               movimientos.__chkTurno1SalBobina.setSelected(true);
                           }
                           if(RS.getString("TURNO2").equals("t2")){
                               movimientos.__chkTurno2SalBobina.setSelected(true);
                           }
                           if(RS.getString("TURNO3").equals("t3")){
                               movimientos.__chkTurno3SalBobina.setSelected(true);
                           }
                           idfolio= RS.getInt("id_salida");
                       } 
                       this.movimientos.__MenuMovimiento.setEnabled(false);
                       this.movimientos.__Archivo.setEnabled(false);
                       this.movimientos.__Edicion.setEnabled(false);
                       this.movimientos.__MODIFICACIONB.setEnabled(false);
                       this.movimientos.JPanel.setEnabledAt(1, false);
                       this.movimientos.JPanel.setEnabledAt(0, false); 
                    }else{
                        mensaje(2,"La Salida No Existe");
                    }
                    ResultSet detallesalidab = mimodelo.buscarDetalleSalidaB(idfolio);
                    if(detallesalidab.next()){
                        detallesalidab.beforeFirst();
                        int s=0;
                         while(detallesalidab.next()){
                               idsalidasb[s]=detallesalidab.getString("idd_salida");
                               String cclave= detallesalidab.getString("clave_papel");
                               movimientos.__tablaSalidaBobinaInventario.setValueAt(detallesalidab.getString("clave_papel"), s, 0);
                               int a =detallesalidab.getInt("totkgsini");
                               movimientos.__tablaSalidaBobinaInventario.setValueAt(detallesalidab.getInt("totkgsini"), s, 1);
                               int a1 =detallesalidab.getInt("totbobini");
                               movimientos.__tablaSalidaBobinaInventario.setValueAt(detallesalidab.getInt("totbobini"), s, 2);
                               int b= detallesalidab.getInt("totkgssur");
                               movimientos.__tablaSalidaBobinaInventario.setValueAt(detallesalidab.getInt("totkgssur"), s, 3);
                               int b1=detallesalidab.getInt("totbobsur");
                               movimientos.__tablaSalidaBobinaInventario.setValueAt(detallesalidab.getInt("totbobsur"), s, 4);
                               int c=detallesalidab.getInt("totkgsdev");
                               movimientos.__tablaSalidaBobinaInventario.setValueAt(detallesalidab.getInt("totkgsdev"), s, 5);
                               int c1=detallesalidab.getInt("totbobdev");
                               movimientos.__tablaSalidaBobinaInventario.setValueAt(detallesalidab.getInt("totbobdev"), s, 6);
                               int rk = a+b-c;
                               int rb = a1+b1-c1;
                               
                               String entradas[] = detallesalidab.getString("entradas").split(",");
                               j = entradas.length;
                               antipeps2(entradas,rk,rb);
                               mimodelo.sumarexistencia(detallesalidab.getString("clave_papel"));
                               movimientos.__tablaSalidaBobinaInventario.setValueAt(Double.parseDouble(detallesalidab.getString("costo")), s, 7);
                               movimientos.__tablaSalidaBobinaInventario.setValueAt(Double.parseDouble(detallesalidab.getString("totalcosto")), s, 8);
                               
                           
                           emergente.__CapaKG.setText(detallesalidab.getString("capa"));
                           emergente.__ConoKG.setText(detallesalidab.getString("cono"));
                           emergente.__DesperdicioKG.setText(detallesalidab.getString("desperdicio"));
                           emergente.__DesperdicioPliegos.setText(detallesalidab.getString("despliegos"));
                           emergente.__EmpaqueKG.setText(detallesalidab.getString("empaque"));
                           ResultSetMetaData metaData = detallesalidab.getMetaData();
                           int numcol = metaData.getColumnCount();
                           nombrecolumnas = new String[numcol];
                           for(int i=2;i<numcol;i++){
                               this.nombrecolumnas[i]=metaData.getColumnLabel(i+1);
                           }
                           s++;
                       }
                   }
                   filas=movimientos.__tablaSalidaBobinaInventario.getRowCount();
                   columnas=movimientos.__tablaSalidaBobinaInventario.getColumnCount();
                }catch(Exception ex){
                    mensaje(3,ex.getMessage());
                    //Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case __ACEPTAR_MOVIMIENTOS_SALIDAB:
                modificacionsalb();
                break;
                //AQUI FINALICE HOY 11 04 2014 IRVING
            case __GUARDARPASSWORD:
                cambiopswd();
                break;
            case __CANCELARPASSWORD:
                ap.setEnabled(true);
                movimientos.setEnabled(true);
                reportes.setEnabled(true);
                consultas.setEnabled(true);
                cambiocontraseña.__Pswd.setText("");
                cambiocontraseña.__NewPswd1.setText("");
                cambiocontraseña.__NewPswd.setText("");
                cambiocontraseña.setVisible(false);
                break;
           case __GUARDAR_NEW_USER:
                altamodificacion();
                break;
            case __CANCELAR_NEW_USER:
                cancelarnewusuario();
                break;
            case __NUEVO_USER_NEW:
                borrarFormularioNewUser();
                break;
            case __MODIFICAR_USER://ingresar un nombre de usuario para modificar algunos de sus valores  
                modificaruser = JOptionPane.showInputDialog(null,"Escribe el Nombre de Usuario a Modificar","Modificar Usuario",JOptionPane.PLAIN_MESSAGE);
                modificaruser = modificaruser.toUpperCase();
                    if(modificaruser==null){
                        tipoalta=1;
                        break;
                    }
                    if(menumaster.__etqUsuarioMenuMaster.getText().equals(modificaruser)){
                        mensaje(2,"No Puedes Modificar la Sesion Activa");
                    }else{
                        try {                                               
                            ResultSet buscarUser = mimodelo.buscarUser(modificaruser);  
                                if(buscarUser.next()){
                                 buscarUser.beforeFirst();
                                    while (buscarUser.next()){
                                        newusuario.__nombreUser.setText(buscarUser.getString(4));
                                        newusuario.__User.setText(buscarUser.getString(5));
                                        newusuario.__NewPswd.setText(buscarUser.getString("_cntr"));
                                        newusuario.__NewPswd2.setText(buscarUser.getString("_cntr"));
                                            if(buscarUser.getInt(3)==1){
                                                newusuario.__optMaster.setSelected(true);
                                            }               
                                            if(buscarUser.getInt(3)==2){
                                                newusuario.__optJunior.setSelected(true);
                                            }
                                            if(buscarUser.getInt(3)==3){
                                                newusuario.__optKid.setSelected(true);
                                            }
                                            if(buscarUser.getInt(6)==1){
                                                newusuario.__optActivo.setSelected(true);
                                            }
                                            if(buscarUser.getInt(6)==0){
                                                newusuario.__optInactivo.setSelected(true);
                                            }
                                            tipoalta=1;
                                    } 
                                }else{
                                    mensaje(2,"No Existe El Usuario");
                                    tipoalta=0;
                                    break;
                                }   
                            }catch (SQLException ex) {
                                mensaje(3,ex.getMessage());
                            }
                    }
            break;
            case __BLOQUEAR_USER:
                String bloquearuser = JOptionPane.showInputDialog(null,"Escribe el Nombre de Usuario a Bloquear","Bloquear Usuario",JOptionPane.PLAIN_MESSAGE);
                bloquearuser = bloquearuser.toUpperCase();
                if(bloquearuser==null){
                    break;
                }  
                if(menumaster.__etqUsuarioMenuMaster.getText().equals(bloquearuser)){
                    mensaje(2,"No Puedes Bloquear la Sesion Activa");
                }else{
                    try {
                        ResultSet bu = mimodelo.buscarUser(bloquearuser);
                            if(!bu.next()){
                                mensaje(2,"El usuario no existe");
                            }else{
                                confir= mensajeConfirmacion("Realmente Desea Bloquear el Usuario "+bloquearuser,"Modificación");
                                if(confir==JOptionPane.OK_OPTION){
                                    boolean busuario=mimodelo.bloquearusuario(bloquearuser);
                                    if(busuario==true){
                                        mensaje(1,"Usuario Bloqueado");
                                    }
                                }
                            }      
                    }catch (SQLException ex) {
                        mensaje(3,ex.getMessage());
                    }          
                }                      
                break;
                //finalisa el formulario de nuevo usuario y comienza el reporte de usuarios
            case __ACEPTAR_REPORTE_USUARIO:
                buscarreporteusuario(2);
                buscarreporteusuario(1);
                break;
            case __REGRESAR_REPORTE_USUARIO:
                String dt=this.aceptarFecha(reporteuser.reportedate, 1);
                if(reporteuser.__ReporteNombreUsuario.getText().isEmpty() && dt==null ){
                    this.reporteuser.__ReporteNombreUsuario.setText("");
                    this.reporteuser.reportedate.setDate(null);
                    limpiarTabla(reporteuser.__tablaReporteUsuario);
                    ap.setEnabled(true);
                    movimientos.setEnabled(true);
                    reportes.setEnabled(true);
                    consultas.setEnabled(true);
                    reporteuser.setVisible(false);
                }else{
                confir = mensajeConfirmacion("¿Realmente Deseas Salir Y Borrar?","Salida");
                                if (confir == JOptionPane.OK_OPTION){ 
                                    this.reporteuser.__ReporteNombreUsuario.setText("");
                                    this.reporteuser.reportedate.setDate(null);
                                    limpiarTabla(reporteuser.__tablaReporteUsuario);
                                    ap.setEnabled(true);
                                    movimientos.setEnabled(true);
                                    reportes.setEnabled(true);
                                    consultas.setEnabled(true);
                                    reporteuser.setVisible(false);
                                }
                }
                break;
            case __NUEVABUSQUEDA_REPORTE_USUARIO:
                confir = mensajeConfirmacion("¿Realmente Deseas Realizar una Nueva Busqueda?","Salida");
                                if (confir == JOptionPane.OK_OPTION){ 
                                    this.reporteuser.__ReporteNombreUsuario.setText("");
                                    this.reporteuser.reportedate.setDate(null);
                                    limpiarTabla(reporteuser.__tablaReporteUsuario);
                                }
                break;
            case __BOBINA_TRASPASO:    
                traspaso.__etqCantidad.setText("Cantidad de Bobinas");
                traspaso.__etqCantidadPT.setText("Cantidad de Kilos");  

                mostrarTraspaso();
                break;
            case __HOJA_TRASPASO:
                traspaso.__etqCantidad.setText("Cantidad de Hojas");
                traspaso.__etqCantidadPT.setText("Cantidad Por Paquete ó Tarima");
                mostrarTraspaso();
                break;      
            case __CANCELAR_TRASPASO:
                if(traspaso.__ClasePapelTraspaso.isSelected(null)&& 
                   traspaso.__Origen.getText().equals("") && traspaso.__Destino.getText().equals("")&&
                   traspaso.__ClavePapelTras.getText().equals("") &&traspaso.__ClavePapelTras.getText().equals("")&&
                   traspaso.__CantidadTotal.getText().equals("") && traspaso.__CantidadPT.getText().equals("") && traspaso.__CostoTras.getText().equals("")){
                    ap.setEnabled(true);
                    movimientos.setEnabled(true);
                    reportes.setEnabled(true);
                    consultas.setEnabled(true);
                    reportes.setEnabled(true);
                    consultas.setEnabled(true);
                    traspaso.setVisible(false);
                }else{
                    confir= mensajeConfirmacion("Deseas Cancelar el Traspaso","Cancelar");
                    if(confir==JOptionPane.OK_OPTION){
                        addItems("traspaso");
                        ocultarTraspaso();
                        ap.setEnabled(true);
                        movimientos.setEnabled(true);
                        reportes.setEnabled(true);
                        consultas.setEnabled(true);
                        traspaso.setVisible(false);
                    }
                }
                break;
            case __ACEPTAR_TRASPASO:
                switch(tipotraspaso){
                    case 1:
                        if(!traspaso.__Origen.getText().isEmpty() && !traspaso.__Destino.getText().isEmpty()){
                            int cliente =this.busquedaid(traspaso.__Origen);
                            int cliente1 = this.busquedaid(traspaso.__Destino);
                            String clavecl=this.traspaso.__ClavePapelTras.getText();
                            int cantcl=0,cantcl1=0, nuevaexistenciacl1=0,nuevaexistenciacl=0,nuevapresentacioncl=0,nuevapresentacioncl1=0,presentacioncl=0,presentacioncl1=0;;
                            int agregarexistencia = Integer.parseInt(traspaso.__CantidadPT.getText()); //kghojas
                            int presentacion = Integer.parseInt(traspaso.__CantidadTotal.getText()); //paqbob
                            Object object=null;
                            if(cliente==cliente1){
                                mensaje(2,"No se puede efectuar un traspaso al mismo propietario");
                                break;
                            }else{
                                try {
                                    ResultSet existenciapapelcl = mimodelo.buscarExistenciaPapelcliente(clavecl);
                                    while(existenciapapelcl.next()){
                                        cantcl=Integer.parseInt(existenciapapelcl.getString(1).replace(".00", ""));
                                    }
                                    if(cantcl<agregarexistencia){
                                        mensaje(3,"no tienes suficiente existencia del papel: "+clavecl+", Tienes: "+cantcl);
                                        traspaso.__CantidadPT.selectAll();
                                        traspaso.__CantidadPT.requestFocus();
                                        break;
                                    }
                                    ResultSet presentacionpapelcl= mimodelo.buscarPresentacionPapelCliente(clavecl);
                                     while(presentacionpapelcl.next()){
                                        presentacioncl=Integer.parseInt(presentacionpapelcl.getString(1).replace(".00", ""));
                                    }
                                    if(presentacioncl<presentacion){
                                        mensaje(3,"no tienes suficiente presentacion del papel: "+clavecl+", Tienes: "+presentacioncl);
                                        traspaso.__CantidadTotal.selectAll();
                                        traspaso.__CantidadTotal.requestFocus();
                                        break;
                                    }
                                    String clavePapcl1=clavecl.replaceAll(this.traspaso.__Origen.getText()+"", this.traspaso.__Destino.getText()+"");
                                    ResultSet existenciapapelcl1 = mimodelo.buscarExistenciaPapelcliente(clavePapcl1);
                                    while(existenciapapelcl1.next()){
                                        object = existenciapapelcl1.getObject(1);
                                        cantcl1=Integer.parseInt(existenciapapelcl1.getString(1).replace(".00", ""));
                                    }
                                    if(object==null){
                                        confir = this.mensajeConfirmacion("No se encontro el papel: "+clavePapcl1+" deseas darlo de alta?", "Deseas dar de alta un papel?");
                                        if (confir==JOptionPane.OK_OPTION){
                                            addItems("ap");
                                            ap.setEnabled(true);
                                            this.abrirpantalla=1;
                                            this.ap.setVisible(true);
                                            this.ap.setLocationRelativeTo(null);
                                            ResultSet datosPapel = mimodelo.buscarDatosPapel(clavecl);
                                            while(datosPapel.next()){
                                                if(datosPapel.getString("Clave").substring(0, 2).equals("01")){
                                                    this.ap.__Bobina.setSelected(true);
                                                }else{
                                                    this.ap.__Hoja.setSelected(true);
                                                }
                                                String buscarnombrePapel = mimodelo.buscarnombrePapel(datosPapel.getInt("NOMBRE_PAPEL_id_nombre"));
                                                this.ap.__NombrePapel.setSelectedItem(buscarnombrePapel);
                                                String buscarColorPapel = mimodelo.buscarcolorPapel(datosPapel.getInt("COLOR_id_color"));
                                                this.ap.__ColorPapel.setSelectedItem(buscarColorPapel);
                                                if(datosPapel.getInt("caras")==1){
                                                    this.ap.__Cara1.setSelected(true);
                                                }else{
                                                   this.ap.__Cara2.setSelected(true);
                                                }
                                                this.ap.__Alto_.setText(datosPapel.getInt("alto")+"");
                                                this.ap.__Ancho.setText(datosPapel.getString("ancho"));
                                                this.ap.__Gramaje.setText(datosPapel.getInt("grams")+"");
                                                this.ap.__Propiedad.setSelectedItem(this.traspaso.__Destino.getText()+"");
                                                this.ap.__Marca.setSelectedIndex(datosPapel.getInt("MARCA_id_marca"));
                                            }
                                        }
                                        break;
                                    }
                                    String foliotras=traspaso.__folioT.getText().toString();
                                    double costotras=Double.parseDouble(traspaso.__CostoTras.getText().toString());
                                    System.out.println(costotras);
                                    mimodelo.Traspaso(foliotras, clavecl, clavePapcl1, agregarexistencia+"", presentacion+"", fec, costotras+"");
                                    String folioentrada = mimodelo.buscarFolioEntrada();
                                    String foliosalida = mimodelo.buscarFolioSalida();
                                    ResultSet buscarMaxSalida = mimodelo.bucarMaxSalida();
                                    int id_salida=0;
                                    while(buscarMaxSalida.next()){
                                        id_salida = buscarMaxSalida.getInt(1);
                                    }
                                    buscarMaxSalida.close();
                                    id_salida++;
                                    int propietario = obtenerpropietario(clavecl);
                                    mimodelo.altaSalida(foliosalida,"-", "-", "-", "N/A", "N/A","-", cliente, 0, id_responsable, fec, 12,"TRASPASO CON LA CLAVE DE TRASPASO "+foliotras,0);
                                    identradas_="";
                                    this.PEPS2(clavecl,agregarexistencia,presentacion);
                                    mimodelo.altaDetalleSalida(id_salida,clavecl,agregarexistencia+"",presentacion+"",costotras+"",(costotras*agregarexistencia)+"",identradas_);
                                    System.out.println(costotras);
                                     mimodelo.altaEntrada(folioentrada,"-", "-", "-", "N/A", "N/A","-", cliente1, 0, id_responsable, fec, 12,"TRASPASO CON LA CLAVE DE TRASPASO "+foliotras,0);
                                     mimodelo.sumarexistencia(clavecl);

                                    ResultSet ubic = mimodelo.buscarUbicacion(clavecl);
                                    String ubicacion="";
                                    while(ubic.next()){
                                        ubicacion = ubic.getString("ubicacion");
                                    }
                                    ResultSet buscarMaxEntrada = mimodelo.bucarMaxEntrada();
                                    int id_entrada=0;
                                    while(buscarMaxEntrada.next()){
                                        id_entrada = buscarMaxEntrada.getInt(1);
                                    }
                                    buscarMaxEntrada.close();
                                    //id_entrada++;
                                    mimodelo.altaDetalleEntrada(id_entrada, clavePapcl1, agregarexistencia+"", presentacion+"",ubicacion,costotras+"", (costotras*agregarexistencia)+"");
                                    mimodelo.sumarexistencia(clavePapcl1);
                                    mimodelo.costopromedio(clavePapcl1);
                                    mimodelo.ubicacion(clavePapcl1, ubicacion);
                                    mensaje(1,"traspaso correcto");
                                    addItems("traspaso");
                                    ap.setEnabled(true);
                                    movimientos.setEnabled(true);
                                    reportes.setEnabled(true);
                                    consultas.setEnabled(true);
                                     maximoentrada();
                                    maximosalida();
                                    maximosalidah();
                                    maximosalidab();
                                    ocultarTraspaso();
                                    this.traspaso.setVisible(false);
                                } catch (SQLException ex) {
                                    mensaje(3,ex.getMessage());
                                    break;
                                }
                            }
                            break;
                        }else{
                            mensaje(2,"selecciona los propietarios entre los \n que se Realizara el Traspaso");
                            break;
                        }
                    }
                break;
            case __ACEPTARTIRO:
                String op = this.tiro.op;
                String merma = this.tiro.__MermaImpresion.getText();
                if(merma.isEmpty()){
                    mensaje(3,"No Dejes Campos Vacios");
                    tiro.__MermaImpresion.requestFocus();
                    break;
                }
                String fechaini = aceptarFecha(tiro.__FechaInicial,0);
                String hraini = tiro.__HoraInicial.getText();
                String fechafin = aceptarFecha(tiro.__FechaFinal,0);
                String hrafin = tiro.__HoraFinal.getText();
                String estandarprod = this.tiro.__EstandarProduccionXHora.getText();
                if(estandarprod.isEmpty()){
                    mensaje(3,"No Dejes Campos Vacios");
                    tiro.__EstandarProduccionXHora.requestFocus();
                    break;
                }
                String totalpliegos = this.tiro.__TotalPliegos.getText();
                if(totalpliegos.isEmpty()){
                    mensaje(3,"No Dejes Campos Vacios");
                    tiro.__TotalPliegos.requestFocus();
                    break;
                }
                String controtativas= this.tiro.__ContadorRotativas.getText();
                if(controtativas.isEmpty()){
                    mensaje(3,"No Dejes Campos Vacios");
                    tiro.__ContadorRotativas.requestFocus();
                    break;
                }
                String tiempoReal = tiro.__TiempoRealProduccion.getText();
                if(tiempoReal==null){
                    break;
                }else{
                    boolean altaop = mimodelo.altaop(op, merma, fechaini, hraini, fechafin, hrafin, estandarprod, tiempoReal, totalpliegos, controtativas);
                    if(altaop==true){
                        mensaje(1,"Datos Correctos de OP");
                        this.borrarFormularioTiro();
                        tiro.dispose();
                        movimientos.__FactorSalidaBobina.requestFocus();
                    }else{
                        mensaje(3,"Error");
                    }
                    movimientos.__FactorSalidaBobina.requestFocus();
                }
                this.movimientos.__TituloSalidaBobina.requestFocus();
                break;
            
            case __CANCELARTIRO:
                this.tiro.dispose();
                movimientos.__FactorSalidaBobina.requestFocus();
                break;
            case  __ACEPTAR_PROVEEDOR:
                altaproveedor();                             
                break;
            case __CANCELAR_PROVEEDOR: 
                if(provedor.__NombreProveedor.getText().isEmpty()&&provedor.__DireccionProveedor.getText().isEmpty()&&provedor.__RFC.getText().isEmpty()&&provedor.__TelefonoNProveedor.getText().isEmpty() ){
                    borrarFormularioProveedor();
                        movimientos.setEnabled(true);
                        provedor.dispose();
                }else
                confir= mensajeConfirmacion("Realmente Desea Salir","Cancelar");
                    if(confir==JOptionPane.OK_OPTION){
                        borrarFormularioProveedor();
                        movimientos.setEnabled(true);
                        provedor.dispose();
                    }
                break;
            case __ACEPTAR_EMERGENTE:  
                switch(modificarsalidab){
                    case 0:
                        Obs = JOptionPane.showInputDialog(null,"Observaciones de la salida de Bobina");       
                        confir = this.mensajeConfirmacion("Estas Seguro de Realizar la Salida", "Salida");
                        if(confir==JOptionPane.OK_OPTION){
                            String fechasalidab = fec.replaceAll("-", "");
                            boolean altasalidab = mimodelo.altaSalidab(foliob,t1,t2,t3,opsalB, epsalb, factor, pliego, clientesalb+"", propsalb+"", maqsalb+"", fechasalidab, titulosalb, id_responsable+"", Obs, totalkil, tiroxpliego, pliegokgs, pliegosdeajuste, ajustekgs,tiposal);
                            try{
                                for(int i=0;i<movimientos.__tablaSalidaBobinaInventario.getRowCount();i++){ 
                                    tmpkg=0.0;
                                    tmpbob=0.0;
                                    for(int j=0;j<movimientos.__tablaSalidaBobinaInventario.getColumnCount();j++){
                                        tmpclv= movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 0).toString();
                                        invinikgs = Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 1).toString());
                                        invinibob = Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 2).toString());
                                        surkgs=Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 3).toString());
                                        surbob=Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 4).toString());
                                        devkgs=Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 5).toString());
                                        devbob=Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 6).toString());
                                        costo = new BigDecimal(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 7)+"");
                                        totalcosto= new BigDecimal(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 8).toString()+"");
                                        tmpkg = invinikgs + surkgs - devkgs+0.0;
                                        tmpbob =  invinibob + surbob -devbob+0.0;
                                    }
                                    ResultSet bucarMaxSalidab = mimodelo.bucarMaxSalidab();
                                    int idsalidas=0;
                                    if(bucarMaxSalidab.next()){
                                         bucarMaxSalidab.beforeFirst();
                                        while(bucarMaxSalidab.next()){
                                            idsalidas = bucarMaxSalidab.getInt(1);
                                        }
                                    }else{
                                        idsalidas=1;    
                                    }
                                    identradas_="";
                                    PEPS2(tmpclv,tmpkg,tmpbob);
                                    boolean altadetallesalidab = mimodelo.altaDetalleSalidab(idsalidas, movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 0).toString(), invinikgs+"", invinibob+"", surkgs+"",surbob+"",devkgs+"",devbob+"", tmpkg+"", dspkg+"", empqkg+"", capakg+"", conokg+"",totalkg+"", tmppliegos+"", dsppliegos+"", empqpliegos+"", capapliego+"",tmpconopliego+"" ,totalpliego+"",costo+"",totalcosto+"",totalkil,identradas_);
                                    //para reporte en 0
                                    totalkil="0";
                                    totalpliego=0.0;
                                    dsppliegos=0.0;
                                    totalkg=0.0;
                                    dspkg=0.0;
                                    empqkg=0.0;
                                    capakg=0.0;
                                    conokg=0.0;
                                    if(altasalidab==true&&altadetallesalidab==true){
                                        boolean agregarinventarioinicial = mimodelo.agregarinventarioinicial(opsalB,tmpclv,devkgs,devbob);
                                        if(agregarinventarioinicial==true){
                                            mimodelo.sumarexistencia(tmpclv);
                                            mensaje(1,"Salida de bobina correcta");
                                        }
                                    }
                                }
                            } catch (Exception ex) {
                                
                            }
                        }
                        this.borrarFormularioMovimientosPapel();
                        this.borrarFormularioEmergente();
                        emergente.dispose();
                        this.movimientos.__pnlSalidaBobina.requestFocus();
                        break;
                    case 1:
                        Obs = JOptionPane.showInputDialog(null,"Observaciones de la salida de Bobina");    
                        confir = this.mensajeConfirmacion("Estas Seguro de Modificar la Salida", "Salida");
                        if(confir==JOptionPane.OK_OPTION){
                            try{
                                String fechasalidab = fec.replaceAll("-", "");
                                boolean modifsalidab = mimodelo.modifSalidaB(foliob,t1,t2,t3,opsalB, epsalb, factor, pliego, clientesalb+"", propsalb+"", maqsalb+"", fechasalidab, titulosalb, id_responsable+"", Obs, totalkil, tiroxpliego, pliegokgs, pliegosdeajuste, ajustekgs,tiposal);
                                try{
                                for(int i=0;i<movimientos.__tablaSalidaBobinaInventario.getRowCount();i++){ 
                                    tmpkg=0.0;
                                    tmpbob=0.0;
                                    for(int j=0;j<movimientos.__tablaSalidaBobinaInventario.getColumnCount();j++){
                                        tmpclv= movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 0).toString();
                                        invinikgs = Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 1).toString());
                                        invinibob = Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 2).toString());
                                        surkgs=Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 3).toString());
                                        surbob=Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 4).toString());
                                        devkgs=Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 5).toString());
                                        devbob=Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 6).toString());
                                        costo = new BigDecimal(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 7)+"");
                                        totalcosto= new BigDecimal(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 8).toString()+"");
                                        tmpkg = invinikgs + surkgs - devkgs+0.0;
                                        tmpbob =  invinibob + surbob -devbob+0.0;
                                    }
                                    ResultSet bucarMaxSalidab = mimodelo.bucarMaxSalidab();
                                    int idsalidas=0;
                                    if(bucarMaxSalidab.next()){
                                         bucarMaxSalidab.beforeFirst();
                                        while(bucarMaxSalidab.next()){
                                            idsalidas = bucarMaxSalidab.getInt(1);
                                        }
                                    }else{
                                        idsalidas=1;    
                                    }
                                    identradas_="";
                                    PEPS2(tmpclv,tmpkg,tmpbob);
                                    System.out.println("clave" + tmpclv);
                                    boolean modifdetallesalidab = mimodelo.modifdetalleSalidab(idsalidasb[i], tmpclv, invinikgs+"", invinibob+"", surkgs+"",surbob+"",devkgs+"",devbob+"", tmpkg+"", dspkg+"", empqkg+"", capakg+"", conokg+"",totalkg+"", tmppliegos+"", dsppliegos+"", empqpliegos+"", capapliego+"",tmpconopliego+"" ,totalpliego+"",costo+"",totalcosto+"",identradas_);
                                    //para reporte en 0
                                    totalkil="0";
                                    totalpliego=0.0;
                                    dsppliegos=0.0;
                                    totalkg=0.0;
                                    dspkg=0.0;
                                    empqkg=0.0;
                                    capakg=0.0;
                                    conokg=0.0;
                                    if(modifsalidab==true&&modifdetallesalidab==true){
                                        boolean agregarinventarioinicial = mimodelo.agregarinventarioinicial(opsalB,tmpclv,devkgs,devbob);
                                        if(agregarinventarioinicial==true){
                                            mimodelo.sumarexistencia(tmpclv);
                                            mensaje(1,"Modificacion de Salida de bobina correcta");
                                        }
                                    }
                                }
                            } catch (Exception ex) {}
                            this.borrarFormularioMovimientosPapel();
                            this.borrarFormularioEmergente();
                            this.movimientos.__MenuMovimiento.setEnabled(true);
                            this.movimientos.__Archivo.setEnabled(true);
                            this.movimientos.__Edicion.setEnabled(true);
                            this.movimientos.__MODIFICACIONB.setEnabled(true);
                            this.movimientos.JPanel.setEnabledAt(1, true);
                            this.movimientos.JPanel.setEnabledAt(0, true);
                            emergente.dispose();
                            this.movimientos.__pnlSalidaBobina.requestFocus();    
                            }catch(Exception evt){
                            }
                        }
                        
                        break;
                }
                
                break;
            case __CANCELAR_EMERGENTE:
                 confir=this.mensajeConfirmacion("Estas Seguro de Cancelar","Confirmación");
                        if (confir==JOptionPane.OK_OPTION){
                            emergente.dispose();
                        }
                break;           
            case __CONSULTAS:
                setEnabledConsultas();
                break;
            case __ACEPTAR_CONSULTA:
                String select="Select ",campos="",from=" from ",tabla="",where=" where",parametros="",query="";
                mimodelo.SALIDAS();
                if(consultas.__chkClavePapel.isSelected()==true){
                    campos=" clave,";
                }
                if(consultas.__chkNombrePapel.isSelected()==true){
                    campos+=" nombrepapel,";
                }
                if(consultas.__chkAncho.isSelected()==true){
                    campos+=" ancho,";
                }
                if(consultas.__chkAlto.isSelected()==true){
                    campos+=" alto,";
                }
                if(consultas.__chkGrsPts.isSelected()==true){
                    campos+=" grams,";
                }
                if(consultas.__chkColor.isSelected()==true){
                    campos+=" color,";
                }
                if(consultas.__chkMarca.isSelected()==true){
                    campos+=" marca,";
                }
                if(consultas.__chkPropietario.isSelected()==true){
                    campos+=" propiedad,";
                }
                if(consultas.__chkKiloHoja.isSelected()==true){
                    campos+=" presentacion,";
                }
                if(consultas.__chkKiloHoja.isSelected()==true){
                    campos+=" cantidad,";
                }
                if(consultas.__chkOrdenP.isSelected()==true){
                    campos+=" orden_produccion,";
                }
                if(consultas.__chkOrdenC.isSelected()==true){
                    campos+=" orden_compra,";
                }
                if(consultas.__chkFolio.isSelected()==true){
                    campos+=" Folio,";
                }
                if(consultas.__chkDocumento.isSelected()==true){
                    campos+=" documento,";
                }
                if(consultas.__chkProveedor.isSelected()==true){
                    campos+=" proveedor,";
                }
                if(consultas.__chkTipoEntrada.isSelected()==true){
                    campos+=" tipo_entrada,";
                }
                if(consultas.__chkTipoSalida.isSelected()==true){
                    campos+=" tipo_salida,";
                }
                if(consultas.__chkFechaIni.isSelected()==true || consultas.__chkFechaFin.isSelected()==true){
                    campos+=" fecha,";
                }
                
                //PARAMETROS
                String claves=this.consultas.__clave.getText();
                if (!claves.isEmpty()){
                    parametros=" clave like '%"+claves+"%' and";
                }
                String NomPap=this.consultas.__cmbNombrePapel.getSelectedItem()+"";
                if (!NomPap.isEmpty()){
                    parametros+=" nombre='"+NomPap+"' and";
                }
                String Ancho=this.consultas.__ancho.getText();
                if (!Ancho.isEmpty()){
                    parametros+=" ancho='"+Ancho+"' and";
                }
                String Alto=this.consultas.__alto.getText();
                if (!Alto.isEmpty()){
                    parametros+=" alto='"+Alto+"' and";
                }
                String Grama=this.consultas.__grspts.getText();
                if (!Grama.isEmpty()){
                    parametros+=" grams='"+Grama+"' and";
                }
                String Color=this.consultas.__cmbColor.getSelectedItem()+"";
                if (!Color.isEmpty()){
                    parametros+=" color='"+Color+"' and";
                }
                String Marca=this.consultas.__cmbMarca.getSelectedItem()+"";
                if (!Marca.isEmpty()){
                    parametros+=" marca='"+Marca+"' and";
                }
                String Propie=this.consultas.__cmbPropi.getSelectedItem()+"";
                if (!Propie.isEmpty()){
                    parametros+=" propiedad='"+Propie+"' and";
                }
                
                String KiloHoja=this.consultas.__kilohojas.getText();
                String kiho=this.consultas.__cmbKiloHoja.getSelectedItem()+"";
                if (!KiloHoja.isEmpty()){
                    if(Integer.parseInt(KiloHoja)==0 && ((kiho.equals("<"))||(kiho.equals("=")))){
                        mensaje(3,"No puedes hacer una consulta menor o igual a 0 en la existencia");
                        break;
                    }else if(Integer.parseInt(KiloHoja)>=0){
                        parametros+=" cantidad"+kiho+"'"+KiloHoja+"' and";
                    }
                }
                String BobinaPaq=this.consultas.__bobinapaq.getText();
                String BobiPa=this.consultas.__cmbBobinaPaq.getSelectedItem()+"";
                if (!BobinaPaq.isEmpty()){
                    if(Integer.parseInt(BobinaPaq)==0 && ((BobiPa.equals("<"))||(BobiPa.equals("=")))){
                        mensaje(3,"No puedes hacer una consulta menor o igual a 0 en la existencia");
                        break;
                    }else if(Integer.parseInt(BobinaPaq)>=0){
                        parametros+=" presentacion"+BobiPa+"'"+BobinaPaq+"' and";
                    }
                }
                String OrdenP=this.consultas.__OrdenP.getText();
                if (!OrdenP.isEmpty()){
                    parametros+=" orden_produccion like '%"+OrdenP+"%' and";
                }
                String OrdenC=this.consultas.__OrdenC.getText();
                if (!OrdenC.isEmpty()){
                    parametros+=" orden_compra like '%"+OrdenC+"%' and";
                }
                String Folio=this.consultas.__folio.getText();
                if (!Folio.isEmpty()){
                    parametros+=" Folio='"+Folio+"' and";
                }
                String Foliohasta=this.consultas.__foliohasta.getText();
                if (!Foliohasta.isEmpty()){
                    parametros = parametros.replace(" Folio='"+Folio+"' and", "");
                    parametros+=" Folio>='"+Folio+"' and Folio<='"+Foliohasta+"' and";
                }
                String Documento=this.consultas.__documento.getText();
                if (!Documento.isEmpty()){
                    parametros+=" Documento like '%"+Documento+"%' and";
                }
                String Provedor=this.consultas.__cmbProveedor.getSelectedItem()+"";
                if (!Provedor.isEmpty()){
                    parametros+=" proveedor='"+Provedor+"' and";
                }
                String TipoEntrada=this.consultas.__cmbTipoEntrada.getSelectedItem()+"";
                if (!TipoEntrada.isEmpty()){
                    parametros+=" tipo_entrada='"+TipoEntrada+"' and";
                }
                String TipoSalida=this.consultas.__cmbTipoSalida.getSelectedItem()+"";
                if (!TipoSalida.isEmpty()){
                    parametros+=" tipo_salida='"+TipoSalida+"' and";
                }
                if(consultas.__chkTurno1.isSelected()){
                     campos+=" turno,";
                     parametros+=" turno like '%t1%' and";
                }else if(consultas.__chkTurno2.isSelected()){
                       campos+=" turno,";
                       parametros+=" turno like '%t2%' and";
                }else if(consultas.__chkTurno3.isSelected()){
                       campos+=" turno,";
                       parametros+=" turno like '%t3%' and";
                }else{
                    campos+=" turno,";
                }
                String fechaInici = this.aceptarFecha(consultas.__dateIni,1);
                if(consultas.__dateIni.isEnabled()==true){
                    if(fechaInici!=null){
                        parametros +=" fecha >= '"+fechaInici+"' and" ;
                        
                    }
                }
                String fechaSalida = this.aceptarFecha(consultas.__datefin,1);
                if(consultas.__datefin.isEnabled()==true){ 
                    if(fechaSalida!=null){ 
                             parametros +=" fecha <= '"+fechaSalida+"' and ";
                     }
                }
                ResultSet Consulta=null;
                if(consultas.__PapelNingun.isSelected()){
                    if(consultas.__optNinguno.isSelected()){
                        mensaje(2,"Cambia los parametros de busqueda");
                        break;
                    }
                    if(consultas.__optEntrada.isSelected()){
                        tabla="vw_infoentrada";
                        campos=campos.replace("tipo_salida,", "");
                        campos=campos.replace("presentacion, cantidad,", "cantidad, presentacion,");
                        
                        campos+="costo,total,observaciones,";
                    }
                    if(consultas.__optSalidaInterna.isSelected()){
                        tabla="vw_infosalida";
                        campos = campos.replace("nombrepapel,", "");
                        campos = campos.replace("presentacion,", "total_salida,");
                        campos = campos.replace("cantidad,", "cantidad_salida,");
                        campos = campos.replace("tipo_entrada,", "");
                        campos = campos.replace("ancho, alto, grams, color, marca, propiedad,", "");
                        campos +="costo,totalcosto,observaciones,";                        
                    }
                    if(consultas.__optSalida.isSelected()){
                        mensaje(2,"Cambia la clase de Papel");
                        break;
                    }
                }
                if(consultas.__Bobina.isSelected()){
                    if(consultas.__optNinguno.isSelected()){
                        tabla="vw_infopapelb"; 
                        campos=campos.replace("nombrepapel,", "nombre");
                        campos=campos.replace("alto,", "");
                        campos=campos.replace("orden_produccion,", "");
                        campos=campos.replace("orden_compra,", "");
                        campos=campos.replace("Folio, documento, proveedor, tipo_entrada, tipo_salida, fecha, turno", "");
                        campos= campos.replace(" clave, nombre ancho,  grams, color, marca, propiedad, presentacion, cantidad,   ,", "clave, cantidad, presentacion, nombre, ancho,grams, color, marca, propiedad,");
                        campos+="preciopromedio,total,";
                        campos=campos.replace("clave, cantidad, presentacion, nombre, ancho,grams, color, marca, propiedad,preciopromedio,total,",
                                               "clave,nombre,color,ancho,grams,marca,propiedad,cantidad,presentacion,preciopromedio as costounitario,total,");
                    }
                    if(consultas.__optEntrada.isSelected()){
                        tabla="vw_infoentradabobina";
                        campos=campos.replace("tipo_salida,", "");
                        campos=campos.replace("presentacion, cantidad,", "cantidad, presentacion,");
                        campos+="costo,total,observaciones,";
                        campos = "fecha,turno,Folio,tipo_entrada,documento,proveedor,orden_compra,orden_produccion,clave,cantidad,presentacion,costo,total,observaciones,";
                    }
                    if(consultas.__optSalidaInterna.isSelected()){
                        tabla="vw_infosalida";
                        campos = campos.replace("nombrepapel,", "");
                        campos = campos.replace("presentacion,", "total_salida,");
                        campos = campos.replace("cantidad,", "cantidad_salida,");
                        campos = campos.replace("tipo_entrada,", "");
                        campos = campos.replace("ancho, alto, grams, color, marca, propiedad,", "");
                        campos +="costo,totalcosto,observaciones,";  
                        parametros +=" clave like '01-%'    ";
                    }
                    if(consultas.__optSalida.isSelected()){
                        tabla="vw_infosalidab";
                        campos = campos.replace("orden_compra,", "");  
                        campos = campos.replace("documento,", "");
                        campos = campos.replace("proveedor, tipo_entrada,","");
                        campos = campos.replace("presentacion, cantidad,","cantidad, presentacion,");
                        campos+="pliego,maquina,costo, total,observaciones,";
                        if(!Documento.isEmpty()){
                            mensaje(2,"No existen documentos en la salida de la bobina");
                            return;
                        }
                        if (!Provedor.isEmpty()){
                            mensaje(2,"No existen proveedores en la salida de la bobina");
                            return;
                        }
                        //          
                        campos="fecha,turno,Folio,tipo_salida,orden_produccion,pliego,maquina,clave,cantidad,presentacion,costo, total,observaciones,";
                    }   
                }
                if(consultas.__Hoja.isSelected()){
                    if(consultas.__optNinguno.isSelected()){
                        tabla="vw_infopapelh"; 
                        campos=campos.replace("nombrepapel,", "nombre");
                        campos=campos.replace("orden_produccion,", "");
                        campos=campos.replace("orden_compra,", "");
                        campos=campos.replace("Folio, documento, proveedor, tipo_entrada, tipo_salida, fecha, turno", "");
                        
                        campos= campos.replace(" clave, nombre ancho, alto, grams, color, marca, propiedad, presentacion, cantidad,   ,", "clave, cantidad, presentacion, nombre, ancho,alto,grams, color, marca, propiedad,");
                        campos = campos.replace("marca,", "");
                        campos+="preciopromedio,total,";
                        campos = campos.replace("clave, cantidad, presentacion, nombre, ancho,alto,grams, color,  propiedad,preciopromedio,total",
                                //"clave,nombre,color,ancho,grams,marca,propiedad,cantidad,presentacion,preciopromedio as costounitario,total,"
                                                 "clave,nombre,color,ancho,alto,grams,propiedad,cantidad,presentacion,preciopromedio as costounitario,total");
                    }
                    if(consultas.__optEntrada.isSelected()){
                        tabla="vw_infoentradahoja";
                        campos=campos.replace("tipo_salida,", "");
                        campos=campos.replace("presentacion, cantidad,", "cantidad, presentacion,");
                        campos+="costo,total,observaciones,";
                    }
                    if(consultas.__optSalidaInterna.isSelected()){
                        tabla="vw_infosalida";
                        campos = campos.replace("nombrepapel,", "");
                        campos = campos.replace("presentacion,", "total_salida,");
                        campos = campos.replace("cantidad,", "cantidad_salida,");
                        campos = campos.replace("tipo_entrada,", "");
                        campos = campos.replace("ancho, alto, grams, color, marca, propiedad,", "");
                        campos +="costo,totalcosto,observaciones,";  
                        parametros +=" clave like '02-%'    ";
                    }
                    if(consultas.__optSalida.isSelected()){
                        tabla="vw_infosalidah";
                        campos = campos.replace("orden_compra,", "");  
                        campos = campos.replace("documento,", "");
                        campos = campos.replace("proveedor, tipo_entrada,","");
                        campos = campos.replace("presentacion, cantidad,","cantidad, presentacion,");
                        campos+="costo, total,";
                    }   
                }
                int length = campos.length();
                    campos= campos.substring(0, (length-1));
                    
                    int parametroslength = parametros.length();
                    if(parametroslength ==0){
                        query=select+campos+from+tabla;
                    }else{
                        parametros = parametros.substring(0, (parametroslength-4));
                        query=select+campos+from+tabla+where+parametros;
                    }
                Consulta = mimodelo.Consulta(query);
                try {
                    if(Consulta.next()){
                        Consulta.beforeFirst();
                        ResultSetMetaData metaData = Consulta.getMetaData();
                        int numcolumnas = metaData.getColumnCount();
                        String datosT[] =  new String[numcolumnas];
                        String datosC[] =  new String[numcolumnas];
                        for(int i=0;i<numcolumnas;i++){
                            datosT[i]=metaData.getColumnLabel(i+1).toUpperCase();
                        }
                        DefaultTableModel modelo = new DefaultTableModel();
                        verconsulta.__tConsulta.setModel(modelo);
                        for(int i=0;i<numcolumnas;i++){
                            modelo.addColumn(datosT[i]);
                        }
                        TableColumnModel columnModel = verconsulta.__tConsulta.getColumnModel();
                        TableColumn columnaTabla = columnModel.getColumn(0);
                        String nombreColumna = columnaTabla.getIdentifier().toString();
                        if (nombreColumna.equals("CLAVE")||nombreColumna.equals("CLAVEPAPEL")){
                            columnaTabla.setPreferredWidth(300);
                        }
                        while(Consulta.next()){
                            for(int i=0;i<numcolumnas;i++){
                                datosC[i]=Consulta.getString(i+1);
                            }
                            modelo.addRow(datosC);
                        }
                        int total_filas = verconsulta.__tConsulta.getRowCount();
                        verconsulta.__etqTotal.setText("Total de Registros: "+total_filas);
                        verconsulta.show();
                    }else{
                        mensaje(2,"La consulta a la base de datos no devolvio informacion");
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                    
                }
                break;
            case __SALIR_CONSULTA:
                confir=this.mensajeConfirmacion("Estas Seguro de Salir","Confirmación");
                        if (confir==JOptionPane.OK_OPTION){
                            consultas.dispose();
                            menumaster.setVisible(true);
                            borrarFormularioConsultas();
                        }
                break;
            case __REGRESAR:
                this.limpiarTabla(verconsulta.__tConsulta);
                verconsulta.setVisible(false);
                break;
            case __EXCEL:
                try{
                    File archivo = this.archivo(1);
                    if(archivo==null){
                        return;
                    }
                    boolean arch = this.imprimirExcel(archivo,verconsulta.__tConsulta);
                    if(arch==true){
                        confir = this.mensajeConfirmacion("Archivo "+archivo+".xls creado correctamente, deseas hacer otra operacion", "Adicional");
                        if(confir==JOptionPane.OK_OPTION){
                            correo.setVisible(true);
                            correo.setLocationRelativeTo(null);
                            correo.__archivo.setText(archivo.getAbsolutePath().toLowerCase()+".xls");
                        }
                    }
                }catch(Exception a){
                    mensaje(3,a.getMessage());
                }
                break;
            case __CORREO:
                correo.setVisible(true);
                correo.setLocationRelativeTo(null);
                break;
            case __BUSCARARCHIVO:
                File archivo = this.archivo(2);
                if(archivo==null){
                    break;
                }
                correo.__archivo.setText(archivo.getAbsolutePath().toLowerCase());
                break;
            case __ABRIREXCEL:
                try {
                    String fil = correo.__archivo.getText();
                    if(!fil.isEmpty()){
                        File f = new File(fil);
                        Desktop.getDesktop().open(f);
                    }else{
                        mensaje(3,"Debes Seleccionar el archivo para abrir");
                    }
                } catch (IOException ex) {
                    mensaje(3,ex.getMessage());
                }
                break;
            case __ACEPTARCORREO:
                String urlarchivo = this.correo.__archivo.getText();
                if(urlarchivo.isEmpty()){
                    mensaje(3,"Selecciona el archivo a enviar");
                    break;
                }
                String destinatario = correo.__destinatario.getText();
                if(destinatario.isEmpty()){
                    mensaje(3,"Escribe el destinatario");
                    correo.__destinatario.requestFocus();
                    break;
                }
                String msg = correo.__msg.getText();
                if(msg.isEmpty()){
                    confir=this.mensajeConfirmacion("El correo se enviara sin mensaje, deseas continuar?", "Continuar");
                    if(confir!=JOptionPane.OK_OPTION){
                        correo.__msg.requestFocus();
                        break;
                    }else{
                        msg=" ";
                    }
                }
                confir=this.mensajeConfirmacion("Estas seguro de enviar el correo", "Continuar");
                if(confir==JOptionPane.OK_OPTION){
                    if(this.enviaarchivo(urlarchivo, destinatario, msg)){
                        mensaje(1,"Archivo enviado correctamente");
                        correo.__archivo.setText("");
                        correo.__destinatario.setText("");
                        correo.__msg.setText("");
                    }
                }
                break;
            case __SALIRCORREO:
                correo.__archivo.setText("");
                correo.__destinatario.setText("");
                correo.__msg.setText("");
                correo.dispose();
                verconsulta.requestFocus();
                break;
                //inicia reportes
            case __RBOBINA:
                //this.mimodelo.abrirReporte("bobina.jrxml");
                this.mimodelo.abrirReporte("bobina.jrxml",new HashMap());
                break;
            case __RHOJA:
                  this.mimodelo.abrirReporte("Hojas1.jrxml",new HashMap());
               break;
            case __ENTRADASH:
                String ENH = JOptionPane.showInputDialog(null,"Ingresa la Fecha Inicial(aaaa-mm-dd)");
                map.put("Ingresa", ENH);
                String ENHF = JOptionPane.showInputDialog(null,"Ingresa la Fecha Final (aaaa-mm-dd)");
                map.put("final", ENHF);
                if(!ENH.equals("") && !ENHF.equals("")){                                       
                    mensaje(1,"Generando Entradas Entre las Fechas "+ENH+" - "+ENHF );
                    this.mimodelo.abrirReporte("EntradaHIgual.jrxml",map);                    
                }else if(!ENH.equals("") && ENHF.equals("")){
                    mensaje(1,"Generando Entradas Mayores o Iguales a la Fecha "+ENH);
                    this.mimodelo.abrirReporte("EntradaHMayor.jrxml",map);                    
                }else if(ENH.equals("") && !ENHF.equals("")){
                    mensaje(1,"Generando Entradas Menores o Iguales a la Fecha "+ENHF);
                    this.mimodelo.abrirReporte("EntradaHMenor.jrxml",map);                    
                }else{
                    mensaje(2,"No hay Parametros de Busqueda, No se Creara el Reporte");  
                }
                
                break;
            case __ENTRADASB:
                String ENB = JOptionPane.showInputDialog(null,"Ingresa la Fecha Inicial(aaaa-mm-dd)");
                map.put("ingresa", ENB);
                String ENBF = JOptionPane.showInputDialog(null,"Ingresa la Fecha Final(aaaa-mm-dd)");
                map.put("final", ENBF);
                if(!ENB.equals("") && !ENBF.equals("")){
                    mensaje(1,"Generando Entradas Entre las Fechas "+ENB+" - "+ENBF );
                    this.mimodelo.abrirReporte("EntradaBIgual.jrxml",map);                    
                }else if(!ENB.equals("") && ENBF.equals("")){
                    mensaje(1,"Generando Entradas Mayores o Iguales a la Fecha "+ENB);
                    this.mimodelo.abrirReporte("EntradaBMayor.jrxml",map);                    
                }else if(ENB.equals("") && !ENBF.equals("")){
                    mensaje(1,"Generando Entradas Menores o Iguales a la Fecha "+ENBF);
                    this.mimodelo.abrirReporte("EntradaBMenor.jrxml",map);                    
                }else{
                    mensaje(2,"No hay Parametros de Busqueda, No se Creara el Reporte");  
                }
                
                break;            
            case __SALIDASH:
                String SLH = JOptionPane.showInputDialog(null,"Ingresa la Fecha Inicial(aaaa-mm-dd)");
                map.put("ingresa", SLH);
                String SLHF = JOptionPane.showInputDialog(null,"Ingresa la Fecha Final(aaaa-mm-dd)");
                map.put("final", SLHF);
                if(!SLH.equals("") && !SLHF.equals("")){
                    mensaje(1,"Generando Salidas entre las Fechas "+SLH+" - "+SLHF );
                    this.mimodelo.abrirReporte("SalidasH.jrxml",map);                    
                }else if(!SLH.equals("") && SLHF.equals("")){
                    mensaje(1,"Generando Salidas Mayores o Iguales a la Fecha "+SLH);
                    this.mimodelo.abrirReporte("SalidasHMayor.jrxml",map);                    
                }else if(SLH.equals("") && !SLHF.equals("")){
                    mensaje(1,"Generando Salidas Menores o Iguales a la Fecha "+SLHF);
                    this.mimodelo.abrirReporte("SalidasHMenor.jrxml",map);                    
                }else{
                    mensaje(2,"No hay Parametros de Busqueda, No se Creara el Reporte");  
                }
                
                break;
            case __SALIDASB:
                String SLB = JOptionPane.showInputDialog(null,"Ingresa la Fecha (aaaa-mm-dd)");
                map.put("ingresa", SLB);
                String SLBF = JOptionPane.showInputDialog(null,"Ingresa la Fecha Final(aaaa-mm-dd)");
                map.put("final", SLBF);
                if(!SLB.equals("") && !SLBF.equals("")){
                    mensaje(1,"Generando Salidas entre las Fechas "+SLB+" - "+SLBF );
                    this.mimodelo.abrirReporte("SalidaB.jrxml",map);                    
                }else if(!SLB.equals("") && SLBF.equals("")){
                    mensaje(1,"Generando Salidas Mayores o Iguales a la Fecha "+SLB);
                    this.mimodelo.abrirReporte("SalidaBMayor.jrxml",map);                    
                }else if(SLB.equals("") && !SLBF.equals("")){
                    mensaje(1,"Generando Salidas Menores o Iguales a la Fecha "+SLBF);
                    this.mimodelo.abrirReporte("SalidaBMenor.jrxml",map);                    
                }else{
                    mensaje(2,"No hay Parametros de Busqueda, No se Creara el Reporte");  
                }               
                break;
            case __SALIR_REPORTE:
                confir=this.mensajeConfirmacion("Estas Seguro de Salir","Confirmación");
                if (confir==JOptionPane.OK_OPTION){
                    reportes.dispose();
                    menumaster.setVisible(true);                            
                }
                break;
            case __ORDENPRODUCCION:
                String orden = JOptionPane.showInputDialog(null, "Ingresa la Orden de Producción");
                map.put("Ingresa_Orden_de_Produccion", orden);
                String pliego = JOptionPane.showInputDialog(null, "Ingresa el Pliego");
                map.put("Ingresa_el_Pliego", pliego);
                if(!orden.equals("") && !pliego.equals("")){
                    mensaje(1,"Generando Reporte por Orden de Produccion "+orden+" Y Pliego "+pliego );
                    this.mimodelo.abrirReporte("BobinasOPP.jrxml",map);
                }else if(!orden.equals("") && pliego.equals("")){
                    mensaje(1,"Generando Reporte por Orden de Produccion "+orden);
                    this.mimodelo.abrirReporte("BobinasOP.jrxml",map);
                }else{
                    mensaje(2,"No hay Parametros de Busqueda, No se Creara el Reporte");  
                }
                break;
            case __ORDENPRODUCCIONH:
                String ordenH = JOptionPane.showInputDialog(null, "Ingresa la Orden de Producción");
                map.put("Ingresa_Orden_de_Produccion", ordenH);                
                if(!ordenH.equals("")){
                    mensaje(1,"Generando Reporte por Orden de Produccion "+ordenH);
                    this.mimodelo.abrirReporte("HojaOP.jrxml",map);                
                }else{
                    mensaje(2,"No hay Parametros de Busqueda, No se Creara el Reporte");  
                }
                break;
            case __Traspaso:
                this.retras.setVisible(true);                
                this.reportes.setEnabled(false);
                break;
            case __SALIDAG:
                String SG = JOptionPane.showInputDialog(null, "Ingresa la Fecha Inicial (aaaa-mm-dd)");
                map.put("inicial",SG);
                String SGF = JOptionPane.showInputDialog(null, "Ingresa la Fecha Final (aaaa-mm-dd)");
                map.put("final", SGF);
                if(!SG.equals("") && !SGF.equals("")){
                    mensaje(1,"Generando Salidas entre las Fechas "+SG+" - "+SGF );
                    this.mimodelo.abrirReporte("SalidaG.jrxml",map);
                }else if(!SG.equals("") && SGF.equals("")){
                    mensaje(1,"Generando Salidas Mayores o Iguales a la Fecha "+SG);
                    this.mimodelo.abrirReporte("SalidaGMayor.jrxml",map);
                }else if(SG.equals("") && !SGF.equals("")){
                    mensaje(1,"Generando Salidas Menores o Iguales a la Fecha "+SGF);
                    this.mimodelo.abrirReporte("SalidaGMenor.jrxml",map);
                }else{
                    mensaje(2,"No hay Parametros de Busqueda, No se Creara el Reporte");  
                }             
                break;
            case __RFINANZA:
                this.Finanzas.setVisible(true);                
                this.reportes.setEnabled(false);
                break;
            case __ACEPTAR_FINANZAS:
                String ClaveFinanzas=this.Finanzas.__clave.getText();
                Date fechainii=null,fechafinn=null;
                map.put("clave",ClaveFinanzas);
                String RFI,RFF;
                if(aceptarFecha(this.Finanzas.__dateIni,1)==null){
                    RFI="";
                }else{
                    RFI= this.aceptarFecha(this.Finanzas.__dateIni,1).toString();
                    fechainii = Finanzas.__dateIni.getCalendar().getTime();
                    map.put("inicial",RFI);
                }
                if(aceptarFecha(this.Finanzas.__datefin,1)==null){
                    RFF="";
                }else{
                    RFF = this.aceptarFecha(this.Finanzas.__datefin,1).toString();
                    map.put("final",RFF);
                    fechafinn=  Finanzas.__datefin.getCalendar().getTime();
                }
                if(fechafinn!=null&&fechainii!=null&&fechafinn.before(fechainii)){
                    mensaje(3,"La Fecha Inicial No Puede se Mayor a Fecha Final");
                    return;
                }
                if(ClaveFinanzas.isEmpty()){
                    if(RFI.equals("")){
                        if(RFF.equals("")){
                            //tres vacipos
                            mensaje(2,"No hay Parametros de Busqueda, No se Creara el Reporte");  
                        }else{
                            //lo unico que tiene es fecha final
                            mensaje(1,"Busqueda por: Fecha Final "+RFF);
                            this.mimodelo.abrirReporte("ReporteFMenor.jrxml",map);
                        }
                    }else{
                        if(RFF.equals("")){
                            //ñlpo unicpo que tiene es fecha inciañl
                            mensaje(1,"Busqueda por: Fecha Inicial "+RFI);
                            this.mimodelo.abrirReporte("ReporteFMayor.jrxml",map);
                        }else{
                            //lo que tiene es fecha final e inicial
                            mensaje(1,"Busqueda por: Fecha Inicial "+RFI+", Fecha Final "+RFF);
                            this.mimodelo.abrirReporte("ReporteFIgual.jrxml",map);
                        }
                    }
                }else{
                    if(RFI.equals("")){
                        if(RFF.equals("")){
                            //tiene clave 
                            mensaje(1,"Busqueda por: Clave "+ClaveFinanzas);
                            this.mimodelo.abrirReporte("ReporteFClave.jrxml",map);
                        }else{
                            //tiene clave fecha final
                            mensaje(1,"Busqueda por: Clave "+ClaveFinanzas+", Fecha Final "+RFF);
                            this.mimodelo.abrirReporte("ReporteFClavefin.jrxml",map);
                        }
                    }else{
                        if(RFF.equals("")){
                            //tiene clave inicial
                            mensaje(1,"Busqueda por: Clave "+ClaveFinanzas+", Fecha Inicial "+RFI);
                            this.mimodelo.abrirReporte("ReporteFClaveIni.jrxml",map);
                        }else{
                            //tiene clave fecha final y fecha inicial
                            mensaje(1,"Busqueda por: Clave "+ClaveFinanzas+", Fecha Inicial "+RFI+", Fecha Final "+RFF);
                            this.mimodelo.abrirReporte("ReporteFClaveIniFin.jrxml",map);
                        }
                    }
                }
                break;
            case __CANCELAR_FINANZAS:
                Finanzas.__clave.setText("");
                this.Finanzas.__dateIni.setDate(null);
                this.Finanzas.__datefin.setDate(null);                
                reportes.setEnabled(true);
                reportes.setVisible(true);
                Finanzas.setVisible(false);                
                break;
            case __ACEPTAR_TRASPASORE:
                String ClaveOrigen=this.retras.__foliotras.getText();
                String ClaveDestino=this.retras.__foliotrashasta.getText();
                Date fechainicial=null,fechafinal=null;
                map.put("clave",ClaveOrigen);
                map.put("clave destino",ClaveDestino);
                String trasFI,trasFF;
                if(aceptarFecha(this.retras.__dateIni,1)==null){
                     trasFI="";
                }else{
                    trasFI= this.aceptarFecha(this.retras.__dateIni,1).toString();
                    fechainicial = retras.__dateIni.getCalendar().getTime();
                    map.put("fechainicial",trasFI);
                }
                if(aceptarFecha(this.retras.__datefin,1)==null){
                    trasFF="";
                }else{
                    trasFF = this.aceptarFecha(this.retras.__datefin,1).toString();
                    map.put("fechafinal",trasFF);
                    fechafinal= retras.__datefin.getCalendar().getTime();
                }
                if(fechafinal!=null&&fechainicial!=null&&fechafinal.before(fechainicial)){
                    mensaje(3,"La Fecha Inicial No Puede se Mayor a Fecha Final");
                    return;
                }
                if(ClaveOrigen.isEmpty()){
                    if(ClaveDestino.isEmpty()){
                        if(trasFI.equals("")){
                            if(trasFF.equals("")){
                                //no busca nada
                                mensaje(2,"No hay Parametros de Busqueda, No se Creara el Reporte"); 
                            }else{
                                //busca solo por fecha final
                                System.out.println("fecha final");
                                mensaje(1,"Busqueda por: Fecha Final "+trasFF);
                                this.mimodelo.abrirReporte("TraspasoFFinal.jrxml",map);                                
                            }
                        }else{
                            if(trasFF.equals("")){
                               //busca solo por fecha inicial
                                System.out.println("fecha inicail");
                                mensaje(1,"Busqueda por: Fecha Inicial "+trasFI);
                                this.mimodelo.abrirReporte("TraspasoFInicial.jrxml",map);
//                                System.out.println("Segun es esto busca solo por fecha inicial");
                            }else{
                                //busca solo por fecha Inicial y Final
                                System.out.println("fecha inicial final");
                                mensaje(1,"Busqueda por: Fecha Inicial "+trasFI+", Fecha Final "+trasFF);
                                this.mimodelo.abrirReporte("TraspasoFinicialFFinal.jrxml",map);
//                                System.out.println("Segun es esto busca solo por fecha Inicial y Final");
                            }
                        }
                    }else{
                        if(trasFI.equals("")){
                            if(trasFF.equals("")){
                                 //busca solo por clave destino
                                System.out.println("calve destino");
                                mensaje(1,"Busqueda por: Clave Destino "+ClaveDestino);
                                this.mimodelo.abrirReporte("TraspasoDestino.jrxml",map);
//                                System.out.println("Segun es esto busca solo por clave destino");
                            }else{
                                //destino y  fecha final   
                                System.out.println("destino fecha final");
                                mensaje(1,"Busqueda por: Clave Destino "+ClaveDestino+", Fecha Final "+trasFF);
                                this.mimodelo.abrirReporte("TraspasoDestinoFFinal.jrxml",map);
//                                System.out.println("Segun es esto destino y  fecha final ");
                            }
                        }else{
                            if(trasFF.equals("")){
                              //destino y fecha inicial
                                System.out.println("destino fecha inicial");
                                mensaje(1,"Busqueda por: Clave Destino "+ClaveDestino+", Fecha Inicial "+trasFI);
                                this.mimodelo.abrirReporte("TraspasoDestinoFInicial.jrxml",map);
//                                System.out.println("Segun es esto destino y fecha inicial");
                            }else{
                                //busca solo por fecha inicial y final
                                System.out.println("destino fecha inicial final");
                                mensaje(1,"Busqueda por: Clave Destino "+ClaveDestino+", Fecha Inicial "+trasFI+", Fecha Final "+trasFF);
                                this.mimodelo.abrirReporte("TraspasoDestinoFInicial.jrxml",map);
                            }
                        }                       
                    }
                }else{
                    if(ClaveDestino.isEmpty()){
                        if(trasFI.equals("")){
                            if(trasFF.equals("")){
                                //busca solo por clave origen
                                mensaje(1,"Busqueda por: Folio Inicial "+ClaveOrigen);
                                this.mimodelo.abrirReporte("TraspasoOrigen.jrxml",map);
                                System.out.println("Segun es esto busca solo por clave origen");
                            }else{
                                //busca solo por clave origen y fecha final
                                mensaje(1,"Busqueda por: Folio Inicial "+ClaveOrigen+", Fecha Final "+trasFF);
                                this.mimodelo.abrirReporte("TraspasoOrigenFFinal.jrxml",map);
                                System.out.println("Segun es esto busca solo por clave origen y fecha final");
                            }
                        }else{
                            if(trasFF.equals("")){
                              //origen y fecha inicial
                                mensaje(1,"Busqueda por: Folio Inicial "+ClaveOrigen+", Fecha Inicial "+trasFI);
                                this.mimodelo.abrirReporte("TraspasoOrigenFInicial.jrxml",map);
                                System.out.println("Segun es esto busca origen y fecha inicial");
                            }else{
                                //busca solo por  origen inicial y fecha final
                                mensaje(1,"Busqueda por: Folio Inicial "+ClaveOrigen+", Fecha Inicial "+trasFI+", Fecha Final "+trasFF);
                                this.mimodelo.abrirReporte("TraspasoOrigenFInicialFFinal.jrxml",map);
                                System.out.println("Segun es esto busca solo por  origen inicial y fecha final");
                            }
                        }
                    }else{
                        if(trasFI.equals("")){
                            if(trasFF.equals("")){
                                //busca solo por clave origen y dstino
                                mensaje(1,"Busqueda por: Folio Inicial "+ClaveOrigen+", Folio Final "+ClaveDestino);
                                this.mimodelo.abrirReporte("TraspasoOrigenDestino.jrxml",map);
                                System.out.println("Segun es esto busca solo por clave origen y dstino");
                            }else{
                                //busca solo por clave origen destino y fecha final
                                mensaje(1,"Busqueda por: Folio Inicial "+ClaveOrigen+", Folio Final "+ClaveDestino+", Fecha Final "+trasFF);
                                this.mimodelo.abrirReporte("TraspasoOrigenDestinoFFinal.jrxml",map);
                                System.out.println("Segun es esto busca solo por clave origen destino y fecha final");
                            }
                        }else{
                            if(trasFF.equals("")){
                                //busca solo por origen destino inicial y final
                                mensaje(1,"Busqueda por: Folio Inicial "+ClaveOrigen+", Folio Final "+ClaveDestino+", Fecha Inicial "+trasFI+", Fecha Final "+trasFF);
                                this.mimodelo.abrirReporte("TraspasoOrigenDestinoFInicialFFinal.jrxml",map);
                                System.out.println("Segun es esto busca solo por origen destino inicial ");
                            }else{
                                //origen destino y fecha inicial
                                mensaje(1,"Busqueda por: Folio Final "+ClaveDestino+", Fecha Inicial "+trasFI);
                                this.mimodelo.abrirReporte("TraspasoDestinoFInicial.jrxml",map);
                                System.out.println("Segun es esto busca origen destino y fecha inicial");
                            }
                        }
                    }
                }
                
                break;
            case __CANCELAR_TRASPASORE:
                retras.__foliotras.setText("");
                retras.__foliotrashasta.setText("");
                this.retras.__dateIni.setDate(null);
                this.retras.__datefin.setDate(null);                
                reportes.setEnabled(true);
                reportes.setVisible(true);
                retras.setVisible(false);
                break;
            case __CONSUMOOP:
                this.Consumo.setVisible(true);
                this.reportes.setEnabled(false);
                break;
            case __ACEPTAR_CONSUMO:
                String opini = this.Consumo.OPDesde.getText();
                String opfin = this.Consumo.OPHasta.getText();
                Date fechain=null,fechafi=null;
                String CTI,CTF;
                if(aceptarFecha(this.Consumo.__dateIni,1)==null){
                    CTI="";
                }else{
                    CTI= this.aceptarFecha(this.Consumo.__dateIni,1).toString();
                    fechain = Consumo.__dateIni.getCalendar().getTime();
                }
                if(aceptarFecha(this.Consumo.__datefin,1)==null){
                    CTF="";
                }else{
                    CTF = this.aceptarFecha(this.Consumo.__datefin,1).toString();
                    fechafi=  Consumo.__datefin.getCalendar().getTime();
                }
                if(fechafi!=null&&fechain!=null&&fechafi.before(fechain)){
                    mensaje(3,"La Fecha Inicial No Puede se Mayor a Fecha Final");
                    return;
                }
                mimodelo.ops();
                if(!opini.equals("") && !opfin.equals("")){
                    ResultSet ops = mimodelo.ops(opini, opfin);
                    try {
                        while (ops.next()){
                            String oop = ops.getString("id_op");
                            mimodelo.oop(oop);
                        }
                    } catch (SQLException ex) {
                        mensaje(3,ex.getMessage());
                    }
                    mensaje(1,"Busqueda por: OP Inicial "+opini+ " y OP Final "+opfin);
                    this.mimodelo.abrirReporte("ConsumoTotalOP.jrxml",new HashMap());
                    return;
                }
                if(!opini.equals("") && opfin.equals("")){
                    mimodelo.oop(opini);
                    mensaje(1,"Busqueda por: OP Inicial "+opini);
                    this.mimodelo.abrirReporte("ConsumoTotalOP.jrxml",new HashMap());
                    return;
                }
                if(opini.equals("") && !opfin.equals("")){
                    mimodelo.oop(opfin);
                    mensaje(1,"Busqueda por: OP Final "+opfin);
                    this.mimodelo.abrirReporte("ConsumoTotalOP.jrxml",new HashMap());
                    return;
                }
                ResultSet ops = mimodelo.buscatodoop();
                try {
                    while (ops.next()){
                        String oop = ops.getString("id_op");
                        mimodelo.oop(oop);
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
                if(!CTI.equals("")&&!CTF.equals("")){
                    map.put("final",CTF);
                    map.put("inicial",CTI);
                    mensaje(1,"Busqueda por: Fecha Inicial "+CTI+ " y Fecha Final "+CTF);
                    this.mimodelo.abrirReporte("ConsumoTotalOPEntre.jrxml",map);
                    return;
                }
                if(!CTI.equals("")){
                    map.put("inicial",CTI);
                    mensaje(1,"Busqueda por: Fecha Inicial "+CTI);
                    this.mimodelo.abrirReporte("ConsumoTotalOPInicial.jrxml",map);
                    return;
                }
                if(!CTF.equals("")){
                    map.put("final",CTF);
                    mensaje(1,"Busqueda por: Final "+CTF);
                    this.mimodelo.abrirReporte("ConsumoTotalOPFinal.jrxml",map);
                    return;
                }
                break;
            case __CANCELAR_CONSUMO:
                Consumo.OPDesde.setText("");
                Consumo.OPHasta.setText("");
                this.Finanzas.__dateIni.setDate(null);
                this.Finanzas.__datefin.setDate(null);                
                reportes.setEnabled(true);
                Consumo.setVisible(false);
                break;
            case __ACEPTAR_UBICACION:
                String __clave=this.ubi.__clave.getText().toString();
                String __ubic = this.ubi.__Ubica.getText().toString();
                boolean ubica =mimodelo.ubicacion(__clave, __ubic);
                if(ubica){
                    mensaje(1,"Ubicacion cambiada con exito");
                    ubi.__clave.setText("");
                    ubi.__Ubica.setText("");
                }
                break;
            case __CANCELAR_UBICACION:
                ubi.__clave.setText("");
                ubi.__Ubica.setText("");
                movimientos.setEnabled(true);
                ubi.setVisible(false);
                break;
        }   
        
    }  
    public File archivo(int file){
        File archivo;
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filtro = new FileNameExtensionFilter("Archivos Excel (.xls)", "xls");
        fileChooser.setFileFilter(filtro);
        fileChooser.setAcceptAllFileFilterUsed(false);
        File folder = new File("C:\\IEXSA\\");
        if (!folder.exists()) { 
            folder.mkdir();
        }
        fileChooser.setCurrentDirectory(folder);
        //int resultado = fileChooser.showSaveDialog(null);
        //String anio=Cal.get(Cal.YEAR)<10 ? "0"+Cal.get(Cal.YEAR) : ""+Cal.get(Cal.YEAR);
        int resultado = file ==1 ? fileChooser.showSaveDialog(null) : fileChooser.showOpenDialog(null);
        if(resultado==JFileChooser.CANCEL_OPTION){
            return null;
        }
        return archivo = fileChooser.getSelectedFile();
    }
    public boolean imprimirExcel(File archivo,JTable table){
        try {
            HSSFWorkbook libro = new HSSFWorkbook();
            HSSFSheet hoja = libro.createSheet();
            for (int i = 0; i <= table.getRowCount(); i++) {
                HSSFRow fila = hoja.createRow(i);          
                if(i==0){
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        HSSFCell celda = fila.createCell(j);
                        celda.setCellValue(new HSSFRichTextString(table.getColumnModel().getColumn(j).getHeaderValue().toString()));
                    }
                }
                if(i!=0){
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        HSSFCell celda = fila.createCell(j); 
                        if(table.getValueAt(i-1, j)!=null)
                            celda.setCellValue(new HSSFRichTextString(table.getValueAt(i-1, j).toString()));
                    }
                }
            }   
            FileOutputStream elFichero = new FileOutputStream(archivo.getAbsolutePath()+".xls");
            libro.write(elFichero);
            elFichero.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean enviaarchivo(String urlarchivo, String destinatario,String msg){
        try{
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "dispaper.iexsa@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props, null);
            // session.setDebug(true);

            // Se compone la parte del texto
            BodyPart texto = new MimeBodyPart();
            texto.setText(msg);

            
               
                   //Se compone el adjunto con la imagen
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(urlarchivo)));
           adjunto.setFileName(urlarchivo);       
            
            // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);

            // Se compone el correo, dando to, from, subject y el
            // contenido.
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("prueba@prueba.com"));
            message.addRecipient(
            Message.RecipientType.TO,
            new InternetAddress(destinatario));
            message.setSubject("Correo de Dis-Paper "+ fec);
            message.setContent(multiParte);

            // Se envia el correo.
            Transport t = session.getTransport("smtp");
            String micorreo="dispaper.iexsa@gmail.com";
            String mipswd="dispaperiexsa2014";
            t.connect(micorreo, mipswd);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            return true;
        }
        catch (Exception e){
            mensaje(3,e.getMessage());
            return false;
        }
        
    }
    public void borrarFormularioConsultas(){
        consultas.__PapelNingun.setSelected(true);
        consultas.__optNinguno.setSelected(true);
        consultas.__chkClavePapel.setEnabled(false);
        consultas.__chkClavePapel.setSelected(true);
        consultas.__clave.setEnabled(false);
        consultas.__clave.setText("");
        consultas.__chkNombrePapel.setEnabled(false);
        consultas.__cmbNombrePapel.setSelectedItem("");
        consultas.__chkAncho.setEnabled(false);
        consultas.__chkAncho.setSelected(true);
        consultas.__ancho.setEnabled(false);
        consultas.__ancho.setText("");
        consultas.__chkAlto.setEnabled(false);
        consultas.__chkAlto.setSelected(true);
        consultas.__alto.setEnabled(false);
        consultas.__alto.setText("");
        consultas.__chkGrsPts.setEnabled(false);
        consultas.__chkGrsPts.setSelected(true);
        consultas.__grspts.setEnabled(false);
        consultas.__grspts.setText("");
        consultas.__chkColor.setEnabled(false);
        consultas.__chkColor.setSelected(true);
        consultas.__cmbColor.setEnabled(false);
        consultas.__cmbColor.setSelectedItem("");
        consultas.__chkMarca.setEnabled(false);
        consultas.__chkMarca.setSelected(true);
        consultas.__cmbMarca.setEnabled(false);
        consultas.__cmbMarca.setSelectedItem("");
        consultas.__chkFechaIni.setEnabled(false);
        consultas.__chkFechaIni.setSelected(true);
        consultas.__dateIni.setDate(null);
        consultas.__dateIni.setEnabled(false);
        consultas.__chkFechaFin.setEnabled(false);
        consultas.__chkFechaFin.setSelected(true);
        consultas.__datefin.setDate(null);
        consultas.__datefin.setEnabled(false);
        consultas.__chkPropietario.setEnabled(false);
        consultas.__chkPropietario.setSelected(true);
        consultas.__cmbPropi.setEnabled(false);
        consultas.__cmbPropi.setSelectedItem("");
        consultas.__chkKiloHoja.setEnabled(false);
        consultas.__chkKiloHoja.setSelected(true);
        consultas.__cmbKiloHoja.setEnabled(false);
        consultas.__cmbKiloHoja.setSelectedItem(">");
        consultas.__kilohojas.setEnabled(false);
        consultas.__kilohojas.setText("");
        consultas.__chkBobinaPaq.setEnabled(false);
        consultas.__chkBobinaPaq.setSelected(true);
        consultas.__cmbBobinaPaq.setEnabled(false);
        consultas.__cmbBobinaPaq.setSelectedItem(">");
        consultas.__bobinapaq.setEnabled(false);
        consultas.__bobinapaq.setText("");
        consultas.__chkFolio.setEnabled(false);
        consultas.__chkFolio.setSelected(true);
        consultas.__folio.setEnabled(false);
        consultas.__folio.setText("");
        consultas.__foliohasta.setEnabled(false);
        consultas.__foliohasta.setText("");
        consultas.__chkDocumento.setEnabled(false);
        consultas.__chkDocumento.setSelected(true);
        consultas.__documento.setEnabled(false);
        consultas.__documento.setText("");
        consultas.__chkOrdenP.setEnabled(false);
        consultas.__chkOrdenP.setSelected(true);
        consultas.__OrdenP.setEnabled(false);
        consultas.__OrdenP.setText("");
        consultas.__chkOrdenC.setEnabled(false);
        consultas.__chkOrdenC.setSelected(true);
        consultas.__OrdenC.setEnabled(false);
        consultas.__OrdenC.setText("");
        consultas.__chkProveedor.setEnabled(false);
        consultas.__chkProveedor.setSelected(true);
        consultas.__cmbProveedor.setEnabled(false);
        consultas.__cmbProveedor.setSelectedItem("");
        consultas.__chkTipoEntrada.setEnabled(false);
        consultas.__chkTipoEntrada.setSelected(true);
        consultas.__cmbTipoEntrada.setEnabled(false);
        consultas.__cmbTipoEntrada.setSelectedItem("");
        consultas.__chkTipoSalida.setEnabled(false);
        consultas.__chkTipoSalida.setSelected(true);
        consultas.__cmbTipoSalida.setEnabled(false);
        consultas.__cmbTipoSalida.setSelectedItem("");
        consultas.__chkTurno1.setEnabled(false);
        consultas.__chkTurno1.setSelected(false);
        consultas.__chkTurno2.setEnabled(false);
        consultas.__chkTurno2.setSelected(false);
        consultas.__chkTurno3.setEnabled(false);
        consultas.__chkTurno3.setSelected(false);              
    }
    public void borrarFormularioEmergente(){
        emergente.__TotalSurtidoKG.setText("");
        emergente.__TotalSurtidoPliegos.setText("");
        emergente.__DesperdicioKG.setText("");
        emergente.__DesperdicioPliegos.setText("");
        emergente.__EmpaqueKG.setText("");
        emergente.__EmpaquePliegos.setText("");
        emergente.__CapaKG.setText("");
        emergente.__CapaPliegos.setText("");
        emergente.__ConoKG.setText("");
        emergente.__ConoPliegos.setText("");
        emergente.__TotalesKG.setText("");
        emergente.__TotalesPliegos.setText("");
    }
    public javax.swing.JProgressBar getjProgressBar1() {
        return this.splash.progreso;
    }
    public void iniciarSplash() {
        this.getjProgressBar1().setBorderPainted(true);
        //this.getjProgressBar1().setForeground(new Color(50, 50, 153, 100));
        this.getjProgressBar1().setForeground(Color.RED);
        this.getjProgressBar1().setStringPainted(true);
    }
    public String encriptaContraseña(String contraseña) {
        char[] CONSTS_HEX = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };
        try{
           MessageDigest msgd = MessageDigest.getInstance("MD5");
           byte[] bytes = msgd.digest(contraseña.getBytes());
           StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length);
           for (int i = 0; i < bytes.length; i++){
               int bajo = (int)(bytes[i] & 0x0f);
               int alto = (int)((bytes[i] & 0xf0) >> 4);
               strbCadenaMD5.append(CONSTS_HEX[alto]);
               strbCadenaMD5.append(CONSTS_HEX[bajo]);
           }
           return strbCadenaMD5.toString();
        }catch (NoSuchAlgorithmException e) {
           return null;
        }
    }
    public void cambiopswd(){
        String pswdactual=encriptaContraseña(this.cambiocontraseña.__Pswd.getText());
                if(pswdactual.isEmpty()){
                    mensaje(3,"No dejes campos vacios");
                    this.cambiocontraseña.__Pswd.requestFocus();
                    return;
                }
                String newpswd=this.cambiocontraseña.__NewPswd.getText();
                if(newpswd.isEmpty()){
                    mensaje(3,"No dejes campos vacios");
                    this.cambiocontraseña.__NewPswd.requestFocus();
                    return;
                }
                String newpswd1=this.cambiocontraseña.__NewPswd1.getText();
                if(newpswd1.isEmpty()){
                    mensaje(3,"No dejes campos vacios");
                    this.cambiocontraseña.__NewPswd1.requestFocus();
                    return;
                }
                if(pswdactual.equals(contra)){
                    if(newpswd.equals(newpswd1)){
                        confir=this.mensajeConfirmacion("Estas Seguro de Cambiar tu Contraseña","CONTRASEÑA");
                        if (confir==JOptionPane.OK_OPTION){
                            String newpswd2=newpswd1;
                            boolean cambiocontrasena=mimodelo.cambiocontrasena(encriptaContraseña(newpswd1),newpswd2,id_responsable);
                            if(cambiocontrasena==true){
                                mensaje(1,"Cambio de contraseña correcto");
                                cambiocontraseña.setVisible(false);
                                contra=encriptaContraseña(newpswd1);
                                cambiocontraseña.__Pswd.setText("");
                                cambiocontraseña.__NewPswd1.setText("");
                                cambiocontraseña.__NewPswd.setText("");                                
                                ap.setEnabled(true);
                                movimientos.setEnabled(true);
                                reportes.setEnabled(true);
                                consultas.setEnabled(true);
                                return;
                            }
                        }
                    }else{
                        mensaje(3,"Las contraseñas no coinciden");
                        cambiocontraseña.__NewPswd.requestFocus();
                        return;
                    }                    
                }else{
                    mensaje(3,"La Contraseña Actual No Es La Correcta");
                    cambiocontraseña.__Pswd.selectAll();
                    cambiocontraseña.__Pswd.requestFocus();
                }
    }
    public void iniciasesion() {
        user=this.login.__Usuario.getText();
        contra=this.login.__Pswd.getText();
        contra = this.encriptaContraseña(contra);
        if(user.isEmpty()){
            mensaje(2,"Escribe el Nombre de Usuario");
            this.login.__Usuario.requestFocus();
        }else 
            if(contra.isEmpty()){
                mensaje(2,"Escribe la Contraseña");
                this.login.__Pswd.requestFocus();
            }else{
                try {
                      ResultSet buscarUser=this.mimodelo.buscarUser1(user);
                      if(!buscarUser.next()){
                          mensaje(3,"Error,  Usuario No Dado de Alta");
                          login.__Pswd.setText("");
                          login.__Usuario.setText("");
                          login.__Usuario.requestFocus();
                          return;
                      }
                      buscarUser.beforeFirst();
                      while(buscarUser.next()){
                           pswd = buscarUser.getString(1);
                           id_responsable = buscarUser.getInt(2);
                           cargo = buscarUser.getInt(3);     
                           se=buscarUser.getInt(7); 
                           act=buscarUser.getInt(6); 
                      }
                       if(contra.equals(pswd)){
                            if(act==1){
                                if(se==0){
                                    login.__Usuario.setText("");
                                    login.__Pswd.setText("");
                                    if(!user.equals("ROOT")){
                                         mimodelo.abrirsesion(user);
                                    }
                                    fecha.__etqUser.setText(user);
                                    Calendar Cal= Calendar.getInstance();
                                    String anio=Cal.get(Cal.YEAR)<10 ? "0"+Cal.get(Cal.YEAR) : ""+Cal.get(Cal.YEAR);
                                    String mess=(Cal.get(Cal.MONTH)+1)<10 ? "0"+(Cal.get(Cal.MONTH)+1) : ""+(Cal.get(Cal.MONTH)+1);
                                    String day=Cal.get(Cal.DATE)<10 ? "0"+Cal.get(Cal.DATE) : ""+Cal.get(Cal.DATE);
                                    fech=anio+"-"+mess+"-"+day;
                                    String hora=Cal.get(Cal.HOUR_OF_DAY)<10 ? "0"+Cal.get(Cal.HOUR_OF_DAY) : ""+Cal.get(Cal.HOUR_OF_DAY);
                                    String minute=Cal.get(Cal.MINUTE)<10 ? "0"+Cal.get(Cal.MINUTE) : ""+Cal.get(Cal.MINUTE);
                                    horaentrada = hora+":"+minute;
                                    boolean registraentrada = mimodelo.registraracceso(user,fech,horaentrada);
                                    login.setEnabled(false);
                                    fecha.setLocationRelativeTo(null);
                                    fecha.setVisible(true);
                                }else{
                                    mensaje(3,"Error, La Sesion esta Activa Cierre Su Sesion");
                                    login.__Pswd.setText("");
                                    login.__Usuario.setText("");
                                    login.__Usuario.requestFocus();
                                }
                            }else{
                                mensaje(3,"Error, El Usuario esta Bloqueado Contacte al Administrador");
                                login.__Pswd.setText("");
                                login.__Usuario.setText("");
                                login.__Usuario.requestFocus();
                            }
                       }else{
                           mensaje(3,"Error,  Contraseña Erronea");
                           login.__Pswd.selectAll();
                           login.__Pswd.requestFocus();
                       }
                }catch (SQLException ex) {
                       mensaje(3,ex.getMessage());
                }
            }
    }
    public void altamodificacion(){
        String nombreus=this.newusuario.__nombreUser.getText();
                if(nombreus.isEmpty()){
                    mensaje(3,"Debe Especificar el Nombre del Personal");
                    this.newusuario.__nombreUser.requestFocus();
                    return;
                }
                String usuario=this.newusuario.__User.getText();
                if(usuario.isEmpty()){
                    mensaje(3,"Debe Colocar un Nombre de Usuario");
                    this.newusuario.__User.requestFocus();
                    return;                  
                }
                String pwsd1=this.newusuario.__NewPswd.getText();
                if(pwsd1.isEmpty()){                    
                    mensaje(3,"Debe Colocar una Contraseña para Continuar");
                    this.newusuario.__NewPswd.requestFocus();
                    return;
                }
                String pwsd2=this.newusuario.__NewPswd2.getText();
                if(pwsd2.isEmpty()){
                    mensaje(3,"Debe Repetir la Contraseña");
                    this.newusuario.__NewPswd2.requestFocus();
                    return;
                }
                String contraseña="";
                if(pwsd1.equals(pwsd2)){
                    contraseña=pwsd1;
                }else{
                    mensaje(3,"Las Contraseñas no Coinciden");
                    this.newusuario.__NewPswd.requestFocus();
                    return;
                }
                int bloqueo;
                if(this.newusuario.__optActivo.isSelected()){
                    bloqueo=1;
                }else if(this.newusuario.__optInactivo.isSelected()){
                    bloqueo=0;
                }else{
                    mensaje(3,"Debe Colocar el Usuario en Activo O Inactivo");
                    return;
                }
                int nivel=0;
                if(this.newusuario.__optMaster.isSelected()){
                    nivel = 1;
                }else if(this.newusuario.__optJunior.isSelected()){
                   nivel = 2; 
                }else if(this.newusuario.__optKid.isSelected()){
                    nivel = 3;
                }else{
                    mensaje(3,"Debe Seleccionar un Nivel Para el Usuario");
                    return;
                }                
                switch(tipoalta){//diferenciacion de que es una alta nueva o es una modificacion caso 0 nueva alta caso 1 modificacion de usuario 
                    case 0:
                        confir= mensajeConfirmacion("Realmente Desea dar de Alta el Usuario "+usuario,"Alta");
                            if(confir==JOptionPane.OK_OPTION){
                                boolean altausurio=mimodelo.newaltausuario(nombreus,usuario,contraseña,nivel,bloqueo);                
                                    if(altausurio==true){
                                        mensaje(1,"Alta Correcta de Nuevo Usuario "+usuario+"");                                        
                                    }else{
                                        mensaje(3,"Ocurrio un Error al Dar de Alta el Nuevo Usuario");                          
                                    } 
                        confir= mensajeConfirmacion("¿Desea Dar una Nueva Alta?","Alta");
                                if(confir==JOptionPane.OK_OPTION){
                                        this.borrarFormularioNewUser();
                                       newusuario.setVisible(true);
                                }else{
                                    borrarFormularioNewUser();                                    
                                    movimientos.setEnabled(true);
                                    ap.setEnabled(true);                   
                                    reportes.setEnabled(true);                    
                                    consultas.setEnabled(true);
                                    newusuario.setVisible(false);
                                } 
                                break;
                            }   
                        break;
                    case 1:
                        confir= mensajeConfirmacion("Realmente Desea Modificar el Usuario "+usuario,"Modificación");
                            if(confir==JOptionPane.OK_OPTION){
                                boolean modificarusuario=mimodelo.modificaruser(nombreus,usuario,contraseña,nivel,bloqueo,modificaruser);
                                    if(modificarusuario==true){
                                        mensaje(1,"Modificacion del Usuario "+nombreus+" Realizada con Exito");
                                        tipoalta=0;
                                    }else{
                                        mensaje(3,"Ocurrio un Error al Modificar el Usuario");
                                    }
                                    borrarFormularioNewUser();                                    
                                    movimientos.setEnabled(true);
                                    ap.setEnabled(true);                   
                                    reportes.setEnabled(true);                    
                                    consultas.setEnabled(true);
                                    newusuario.setVisible(false);
                                }
                        break;
                    
                } 
    }
    public void cancelarnewusuario(){
        if(newusuario.__nombreUser.getText().isEmpty() && newusuario.__User.getText().isEmpty() && newusuario.__NewPswd.getText().isEmpty() && newusuario.__NewPswd2.getText().isEmpty() && newusuario.__grpNivelUser.getSelection() == null && newusuario.__grpActivoInactivo.getSelection()== null){
                    borrarFormularioNewUser();
                    movimientos.setEnabled(true);
                    ap.setEnabled(true);                   
                    reportes.setEnabled(true);                    
                    consultas.setEnabled(true);
                     newusuario.setVisible(false); 
        }
        switch(tipoalta){
            case 0:
               confir = mensajeConfirmacion("¿Desea Cancelar la Nueva Alta?","Salida");
                    if (confir==JOptionPane.OK_OPTION){
                        borrarFormularioNewUser();
                        ap.setEnabled(true);
                        movimientos.setEnabled(true);
                        reportes.setEnabled(true);                    
                        consultas.setEnabled(true);
                        newusuario.setVisible(false);
                    }
                break;
            case 1:
                 confir = mensajeConfirmacion("¿Desea Cancelar la Modificación?","Salida");
                    if (confir==JOptionPane.OK_OPTION){
                        borrarFormularioNewUser();
                        ap.setEnabled(true);
                        movimientos.setEnabled(true);
                        reportes.setEnabled(true);                    
                        consultas.setEnabled(true);
                        newusuario.setVisible(false);
                    }
                break;
        }
        
    }    
    public void borrarFormularioNewUser(){
        this.newusuario.__NewPswd.setText("");
        this.newusuario.__NewPswd2.setText("");
        this.newusuario.__User.setText("");
        this.newusuario.__nombreUser.setText("");
        this.newusuario.__grpActivoInactivo.clearSelection();
        this.newusuario.__grpNivelUser.clearSelection();
        this.newusuario.__nombreUser.requestFocus();
        tipoalta=0;
    }
    public void borrarFormularioAltaPapel() {
        this.ap.__grpClasePapel.clearSelection();
        this.ap.__NombrePapel.removeAllItems();
        this.ap.__NombrePapel.addItem("Selecciona...");
        this.ap.__ColorPapel.removeAllItems();
        this.ap.__ColorPapel.addItem("Selecciona...");
        this.ap.__grpCaras.clearSelection();
        this.ap.__Propiedad.removeAllItems();
        this.ap.__Propiedad.addItem("Selecciona...");
        this.ap.__Alto_.setText("");
        this.ap.__Ancho.setText("");
        this.ap.__Gramaje.setText("");
        this.ap.__Marca.removeAllItems();
        this.ap.__Marca.addItem("Selecciona...");
        this.ap.__etqClave.setText("");
        this.ap.__GrPts.setSelectedItem(1);
        this.addItems("ap");
    }
    public void borrarFormularioMovimientosPapel() {
        identradas_="";
        modificarentrada=0;
        modificarsalidah=0;
        modificarsalida=0;
        modificarsalidab=0;
        //PanelEntrada
        movimientos.__TipoEntrada.setText("");
        movimientos.__PropietarioEntr.setText("");
        movimientos.__ProvEntr.setText("");
        movimientos.__ClientEntr.setText("");
        movimientos.__OrdenCompraEntr.setText("");
        movimientos.__OrdenProduccionEntr.setText("");
        movimientos.__OrdenProduccionEntr.setText("");
        movimientos.__OrdenCompraEntr.setText("");
        movimientos.__documentoEntr.setText("");
        movimientos.__chkTurno1Entr.setSelected(false);
        movimientos.__chkTurno2Entr.setSelected(false);
        movimientos.__chkTurno3Entr.setSelected(false);
        limpiarTabla(movimientos.__tablaEntrada);
        maximoentrada();
        
        //PanelSalida
        movimientos.__TipoSalida.setText("");
        movimientos.__PropietarioSalida.setText("");
        movimientos.__ProvSalida.setText("");
        movimientos.__ClientSalida.setText("");
        movimientos.__OrdenCompraSalida.setText("");
        movimientos.__OrdenProduccionSalida.setText("");
        movimientos.__OrdenCompraSalida.setText("");
        movimientos.__documentoSalida.setText("");
        movimientos.__chkTurno1Salida.setSelected(false);
        movimientos.__chkTurno2Salida.setSelected(false);
        movimientos.__chkTurno3Salida.setSelected(false);
        limpiarTabla(movimientos.__tablaSalida);
        maximosalida();
        
        //panelSalidaHoja
        
        movimientos.__TipodeSalidaH.setText("");
        movimientos.__ClienteSalidaH.setText("");
        movimientos.__PropietarioSalidaH.setText("");
        movimientos.__MaquinaSalidaH.setText("");
        movimientos.__TituloSalidaHoja.setText("");
        movimientos.__OPSalidaHoja.setText("");
        movimientos.__EstandarProduccionSalidaHoja.setText("");
        movimientos.__chkTurno1SalHoja.setSelected(false);
        movimientos.__chkTurno2SalHoja.setSelected(false);
        movimientos.__chkTurno3SalHoja.setSelected(false);
        limpiarTabla(movimientos.__tablaSalidaHoja);
        
        maximosalidah();
        
        //PanelSalidaBobina
        
        movimientos.__TipodeSalidaB.setText("");
        movimientos.__ClienteSalidaB.setText("");
        movimientos.__PropietarioSalidaB.setText("");
        movimientos.__MaquinaSalidaB.setText("");
        movimientos.__TituloSalidaBobina.setText("");
        movimientos.__OPSalidaBobina.setText("");
        movimientos.__FactorSalidaBobina.setValue(null);
        movimientos.__PliegoSalidaBobina.setText("");
        movimientos.__TotalKGSalidaBobina1.setText("");
        movimientos.__TiroXPliegoSalidaBobina1.setText("");
        movimientos.__KGPliegoSalidaBobina1.setText("");
        movimientos.__PliegodeAjusteSalidaBobina1.setText("");
        movimientos.__KGAjusteSalidaBobina1.setText("");
        movimientos.__chkTurno1SalBobina.setSelected(false);
        movimientos.__chkTurno2SalBobina.setSelected(false);
        movimientos.__chkTurno3SalBobina.setSelected(false);
        this.limpiarTabla(movimientos.__tablaSalidaBobinaInventario);
        
        maximosalidab();
        
        
        maximosalida();
        
        t1="";
        t2="";
        t3="";
        
    }
    public void limpiarTabla(JTable tabla){
        try {
            String datos []={};
            DefaultTableModel modelo=(DefaultTableModel) tabla.getModel();
            filas=tabla.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
            for (int i = 0;i<10; i++) {
                modelo.addRow(datos);
            }
        } catch (Exception e) {}        
    }
    public void mensaje(int tipo,String mensaje){
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        label.setText(mensaje);
        label.setVisible(true);
        panel.add(label);
        switch (tipo){
            case 1:
                panel.setBackground(Color.green);
                JOptionPane.showMessageDialog(null,panel,"OK", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2:
                panel.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(null,panel,"INFORMA", JOptionPane.QUESTION_MESSAGE);
                break;
            case 3:
                panel.setBackground(Color.RED);
                JOptionPane.showMessageDialog(null,panel,"ERROR", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
    private int mensajeConfirmacion(String mensaje, String titulo) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        label.setText(mensaje);
        label.setVisible(true);
        panel.add(label);
        panel.setBackground(Color.yellow);
        return JOptionPane.showConfirmDialog(null,panel,titulo,JOptionPane.OK_CANCEL_OPTION);
    }
    public void addItems(String formulario){
        try {
            ResultSet buscarMarca = mimodelo.buscarMarca();
            //ResultSet buscarMaquinah = mimodelo.buscarMaquinaHojas();
            //ResultSet buscarMaquinab = mimodelo.buscarMaquinaBobina();
            ResultSet buscarColor =  mimodelo.buscarColorPapel();
            ResultSet buscarNombrePapel = mimodelo.buscarNombrePapel();
            ResultSet buscarPropiedad = mimodelo.buscarPropiedad();
            ResultSet buscarTipoEntrada = mimodelo.buscaTipoEntrada();
            ResultSet buscarTipoSalida = mimodelo.buscaTipoSalida();
            ResultSet buscarProveedores = mimodelo.buscaProveedor();
            
            
            ResultSet buscarColorPapel = mimodelo.buscarColorPapel();
            
            
            
            
            
            
            if(formulario.equals("ap")){
                ap.__Propiedad.removeAllItems();
                ap.__Propiedad.addItem("Selecciona...");
                while(buscarPropiedad.next()){
                    ap.__Propiedad.addItem(buscarPropiedad.getString(2));
                }
                ap.__Marca.removeAllItems();
                ap.__Marca.addItem("Selecciona...");
                while(buscarMarca.next()){
                    ap.__Marca.addItem(buscarMarca.getString(2));
                }
                ap.__ColorPapel.removeAllItems();
                ap.__ColorPapel.addItem("Selecciona...");
                while(buscarColorPapel.next()){
                        ap.__ColorPapel.addItem(buscarColorPapel.getString(1));
                }
                ap.__NombrePapel.removeAllItems();
                ap.__NombrePapel.addItem("Selecciona...");
                while(buscarNombrePapel.next()){
                        ap.__NombrePapel.addItem(buscarNombrePapel.getString(1));
                }
                
            }
            
            
            if(formulario.equals("consultas")){
                consultas.__cmbColor.removeAllItems();
                consultas.__cmbColor.addItem("");
                consultas.__cmbMarca.removeAllItems();
                consultas.__cmbMarca.addItem("");
                consultas.__cmbPropi.removeAllItems();
                consultas.__cmbPropi.addItem("");
                consultas.__cmbProveedor.removeAllItems();
                consultas.__cmbProveedor.addItem("");          
                consultas.__cmbNombrePapel.removeAllItems();
                consultas.__cmbNombrePapel.addItem("");
                consultas.__cmbTipoEntrada.removeAllItems();
                consultas.__cmbTipoEntrada.addItem("");
                consultas.__cmbTipoSalida.removeAllItems();
                consultas.__cmbTipoSalida.addItem("");
                while(buscarPropiedad.next()){
                    consultas.__cmbPropi.addItem(buscarPropiedad.getString(2));
                }
                while(buscarTipoEntrada.next()){
                    consultas.__cmbTipoEntrada.addItem(buscarTipoEntrada.getString(2));
                }   
                while(buscarTipoSalida.next()){
                    consultas.__cmbTipoSalida.addItem(buscarTipoSalida.getString(2));
                }
                while(buscarProveedores.next()){
                    consultas.__cmbProveedor.addItem(buscarProveedores.getString(2));
                }
                while(buscarMarca.next()){
                    consultas.__cmbMarca.addItem(buscarMarca.getString(2));
                }
                while(buscarColor.next()){
                    consultas.__cmbColor.addItem(buscarColor.getString(1));
                }
                while(buscarNombrePapel.next()){
                    consultas.__cmbNombrePapel.addItem(buscarNombrePapel.getString(1));
                }
            }
            
        } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
        }
    }
    private void KeyTipedLetras(KeyEvent evt) {
        mayusculas();
        char caracter = evt.getKeyChar();
        if(((caracter < 'A') || (caracter > 'Z')) && caracter != 'Ñ' && caracter !=' '){
            evt.consume();
        }
    }
    private void KeyTipedLetrasNum(KeyEvent evt) {
        mayusculas();
        char caracter = evt.getKeyChar();
        if(((caracter < 'A') || (caracter > 'Z'))  && ((caracter < '0') || (caracter > '9')) && caracter !=' ' && caracter != 'Ñ' && caracter != '.' && caracter != '-' ){
            evt.consume();
        }
    }
    private void KeyTipedLetrasNumGuion(KeyEvent evt) {
        mayusculas();
        char caracter = evt.getKeyChar();
        if(((caracter < 'A') || (caracter > 'Z'))  && ((caracter < '0') || (caracter > '9')) && caracter != '-'&& caracter != '.'){
            evt.consume();
        }
    }
    private void KeyTipedLetrasNumCar(KeyEvent evt) {
        mayusculas();
        char caracter = evt.getKeyChar();
        if(((caracter < 'A') || (caracter > 'Z'))  && ((caracter < '0') || (caracter > '9')) && caracter != '-' && caracter != ',' && caracter != '/'  && caracter != ' ' && caracter != '.' && caracter != 'Ñ' ){
            evt.consume();
        }
    }
    private void KeyTipedNum(KeyEvent evt) {
        char caracter = evt.getKeyChar();
        if(((caracter < '0') || (caracter > '9') )&& caracter != '.' ){
            evt.consume();
        }
    }
    public void mayusculas(){
        Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
    }
    public void itemspapel(){
         try {
            ResultSet buscarNombrePapel = mimodelo.buscarNombrePapel();
            ap.__NombrePapel.removeAllItems();
            ap.__NombrePapel.addItem("Selecciona...");
            while(buscarNombrePapel.next()){
                    ap.__NombrePapel.addItem(buscarNombrePapel.getString(1));
            }
            ResultSet buscarColorPapel = mimodelo.buscarColorPapel();
            ap.__ColorPapel.removeAllItems();
            ap.__ColorPapel.addItem("Selecciona...");
            while(buscarColorPapel.next()){
                    ap.__ColorPapel.addItem(buscarColorPapel.getString(1));
            }
                
            } catch (SQLException ex) {
                mensaje(3,ex.getMessage());
            }
    } 
    public String aceptarFecha(JDateChooser fech,int a){
        try{
            Date fechaa = fech.getCalendar().getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            return formatoDeFecha.format(fechaa); 
        }catch(Exception ex){
             if(ex.getMessage()==null){
                 if(a ==1){
                     
                 }else{
                     mensaje="Ingresa la fecha a capturar";
                     fech.requestFocus();
                     this.mensaje(2,mensaje);
                 }
             }
        }
        return null;
    }
    public void totalkgpliego() {
        if(movimientos.__TiroXPliegoSalidaBobina1.getText().isEmpty()||movimientos.__TiroXPliegoSalidaBobina1.getText().equals(".")){
            movimientos.__KGPliegoSalidaBobina1.setText("");
            return;
        }
        float tiroxpliego = Float.parseFloat(movimientos.__TiroXPliegoSalidaBobina1.getText());
        try{
            float factor = Float.parseFloat(movimientos.__FactorSalidaBobina.getText());
            float total=factor*tiroxpliego;
            BigDecimal big = new BigDecimal(total+"");
            big = big.setScale(2, RoundingMode.HALF_UP);
            movimientos.__KGPliegoSalidaBobina1.setText(big+"");
            movimientos.__KGPliegoSalidaBobina1.setEditable(false);
        }catch(Exception e){
            
        }
                
    }
    public void pliegosajuste() {
        if(movimientos.__FactorSalidaBobina.getText().isEmpty()||movimientos.__FactorSalidaBobina.getText().equals(".")){
            movimientos.__KGAjusteSalidaBobina1.setText("");
            return;
        }
        float factor = Float.parseFloat(movimientos.__FactorSalidaBobina.getText());
        try{
            float pliegosajuste =  Float.parseFloat(movimientos.__PliegodeAjusteSalidaBobina1.getText());
            float total=factor*pliegosajuste;
            BigDecimal big = new BigDecimal(total+"");
            big = big.setScale(2, RoundingMode.HALF_UP);
            movimientos.__KGAjusteSalidaBobina1.setText(big+"");
            movimientos.__KGAjusteSalidaBobina1.setEditable(false);
        }catch(Exception e){
            
        }
                
    }
    public void altaproveedor(){
            String Nombre=this.provedor.__NombreProveedor.getText(),
            Direccion=this.provedor.__DireccionProveedor.getText(),
            telefono=this.provedor.__TelefonoNProveedor.getText(),
            RfC=this.provedor.__RFC.getText();
            if(Nombre.isEmpty()){
                mensaje(3,"Debe de Ingresar el Nombre");
                this.provedor.__NombreProveedor.requestFocus();
                    return;
            }
            switch(clienteprovedor){//en el caso 0 se da de alta un proveedor en el caso de 1 se da de alta un nuevo cliente
                case 1:
                    confir= mensajeConfirmacion("Realmente Desea dar de Alta el Proveedor "+Nombre,"Alta");
                    if(confir==JOptionPane.OK_OPTION){
                        boolean altaprove=mimodelo.newaltaproveedor(Nombre,Direccion,telefono,RfC);                
                            if(altaprove==true){                                       
                                mensaje(1,"Alta Correcta de Nuevo Proveedor: "+Nombre+"");  
                                borrarFormularioProveedor();
                            }else{
                                mensaje(3,"Ocurrio un Error al Dar de Alta el Nuevo Proveedor");                          
                            }       
                     break;
                    }
                    break;
                case 0:
                    confir= mensajeConfirmacion("Realmente Desea dar de Alta el Cliente: "+Nombre,"Alta");
                    if(confir==JOptionPane.OK_OPTION){
                        boolean altacli=mimodelo.newaltacliente(Nombre,Direccion,telefono,RfC);                
                            if(altacli==true){                                       
                                mensaje(1,"Alta Correcta de Nuevo Cliente: "+Nombre+"");   
                                borrarFormularioProveedor();
                            }else{
                                mensaje(3,"Ocurrio un Error al Dar de Alta el Nuevo Cliente");                          
                            }                        

                        break;
                    }
                    break;
            }
            addItems("movimientos");
    }
    public void borrarFormularioProveedor(){
        this.provedor.__NombreProveedor.setText("");
        this.provedor.__DireccionProveedor.setText("");
        this.provedor.__TelefonoNProveedor.setText("");
        this.provedor.__RFC.setText("");
    }    
    public void nuevaTipoEntrada(){
        String tipoEntra = (String)JOptionPane.showInputDialog(null,"Escribe la Nueva Entrada:\n","NUEVO TIPO ENTRADA",JOptionPane.PLAIN_MESSAGE);
        if ((tipoEntra  != null) && (tipoEntra.length() > 0)) {
            int conf=JOptionPane.showConfirmDialog(null,"Se agregara la Entrada de Papel: " + tipoEntra  + ".",tipoEntra,JOptionPane.OK_CANCEL_OPTION);
            if (conf==JOptionPane.OK_OPTION){
                boolean altanuevoentrada=mimodelo.nuevatipoentrada(tipoEntra);
                if(altanuevoentrada==true){
                        JOptionPane.showMessageDialog(null,"Nuevo Tipo Entrada "+tipoEntra+" Agregado Correctamente.","Correcto",JOptionPane.INFORMATION_MESSAGE);
                        
                }
            }
            return; 
        }
        JOptionPane.showMessageDialog(null,"No Agregaste Nuevo Tipo de Entrada.","Error",JOptionPane.ERROR_MESSAGE);            
    }    
    public void nuevoPropietario(){                                    
               String PROPIEDAD = (String)JOptionPane.showInputDialog(null,"Escribe el Propietario a Registrar:\n","PROPIETARIO DE PAPEL",JOptionPane.PLAIN_MESSAGE);
                if ((PROPIEDAD != null) && (PROPIEDAD.length() > 0)) {
                    int conf=JOptionPane.showConfirmDialog(null,"Se agregara el propietario de papel:, " + PROPIEDAD + ".",PROPIEDAD,JOptionPane.OK_CANCEL_OPTION);
                    if (conf==JOptionPane.OK_OPTION){
                        boolean altapropiedadpapel=mimodelo.nuevopropiedadpapel(PROPIEDAD);
                        if(altapropiedadpapel==true){
                            try {
                                JOptionPane.showMessageDialog(null,"Propiedad de Papel "+PROPIEDAD+" agregado correctamente.","Correcto",JOptionPane.INFORMATION_MESSAGE);
                                ResultSet buscarPropiedad = mimodelo.buscarPropiedad();
                                ap.__Propiedad.removeAllItems();
                                while(buscarPropiedad.next()){
                                    
                                    ap.__Propiedad.addItem(buscarPropiedad.getString(2));
                                }
                            } catch (Exception ex) {
                                mensaje(3,ex.getMessage());
                            }
                        }
                    }
                    return; 
                }
                JOptionPane.showMessageDialog(null,"No agregaste propietario de papel.","Error",JOptionPane.ERROR_MESSAGE);
            }
    public void nuevaMarca(){
         String MARCA = (String)JOptionPane.showInputDialog(null,"Escribe la marca a registrar:\n","MARCA DE PAPEL",JOptionPane.PLAIN_MESSAGE);
        if ((MARCA != null) && (MARCA.length() > 0)) {
            int conf=JOptionPane.showConfirmDialog(null,"Se agregara la marca de papel:, " + MARCA + ".",MARCA,JOptionPane.OK_CANCEL_OPTION);
            if (conf==JOptionPane.OK_OPTION){
                boolean altamarcapapel=mimodelo.nuevomarcapapel(MARCA);
                if(altamarcapapel==true){
                    try {
                        JOptionPane.showMessageDialog(null,"Marca de Papel "+MARCA+" agregado correctamente.","Correcto",JOptionPane.INFORMATION_MESSAGE);
                        //MARCA
                        ResultSet buscarMarca = mimodelo.buscarMarca();
                        ap.__Marca.removeAllItems();
                        while(buscarMarca.next()){
                            ap.__Marca.addItem(buscarMarca.getString(2));
                        }   
                    } catch (SQLException ex) {
                       mensaje(3,ex.getMessage());
                    }
                }
            }
            return; 
        }
        JOptionPane.showMessageDialog(null,"No agregaste ninguna marca de papel.","Error",JOptionPane.ERROR_MESSAGE);
    }
    public void nuevoNombre(JComboBox combo){
        String Nombre = (String)JOptionPane.showInputDialog(null,"Escribe el Nuevo Nombre Papel: ","NUEVO NOMBRE",JOptionPane.PLAIN_MESSAGE);
                if ((Nombre != null) && (Nombre.length() > 0)) {
                    try {
                        Nombre=Nombre.toUpperCase();
                        int conf=JOptionPane.showConfirmDialog(null,"Se agregara el Nombre de Papel:, " +Nombre + ".",Nombre,JOptionPane.OK_CANCEL_OPTION);
                        if (conf==JOptionPane.OK_OPTION){
                            boolean altaNombre=mimodelo.newNombre(Nombre);
                            if(altaNombre==true){
                                JOptionPane.showMessageDialog(null,"Nombre de Papel "+Nombre+" Agregado Correctamente.","Correcto",JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        ResultSet buscarNombrePapel = mimodelo.buscarNombrePapel();
                            combo.removeAllItems();
                            combo.addItem("Selecciona...");
                            while(buscarNombrePapel.next()){
                                    combo.addItem(buscarNombrePapel.getString(1));
                            }
                        return;
                    } catch (SQLException ex) {
                        mensaje(3,ex.getMessage());
                    }
                }
                mensaje(3,"No agregaste Nombre de Papel.");
            }
    private void nuevoColor(JComboBox combo) {
                String Color = (String)JOptionPane.showInputDialog(null,"Escribe el Nuevo Color de Papel: ","NUEVO COLOR",JOptionPane.PLAIN_MESSAGE);
                if ((Color != null) && (Color.length() > 0)) {
                    try {
                        Color=Color.toUpperCase();
                        int conf=JOptionPane.showConfirmDialog(null,"Se agregara el color de papel:, " + Color + ".",Color,JOptionPane.OK_CANCEL_OPTION);
                        if (conf==JOptionPane.OK_OPTION){
                            boolean altaColor=mimodelo.newColor(Color);
                            if(altaColor==true){
                                JOptionPane.showMessageDialog(null,"Color de Papel "+Color+" agregado correctamente.","Correcto",JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        ResultSet buscarColorPapel = mimodelo.buscarColorPapel();
                            combo.removeAllItems();
                            combo.addItem("Selecciona...");
                            while(buscarColorPapel.next()){
                                    combo.addItem(buscarColorPapel.getString(1));
                            }
                        return;
                    } catch (SQLException ex) {
                        mensaje(3,ex.getMessage());
                    }
                }
                mensaje(3,"No agregaste color de papel.");
            }
    public void nuevoTipoPapel(JComboBox combo){
        String TipoPapel = (String)JOptionPane.showInputDialog(null,"Escribe el tipo de papel:\n","TIPO DE PAPEL",JOptionPane.PLAIN_MESSAGE);
                if ((TipoPapel != null) && (TipoPapel.length() > 0)) {
                    try {
                        int conf=JOptionPane.showConfirmDialog(null,"Se agregara el tipo de papel:, " + TipoPapel + ".",TipoPapel,JOptionPane.OK_CANCEL_OPTION);
                        if (conf==JOptionPane.OK_OPTION){
                            boolean altaTipoPapel=mimodelo.nuevoTipoPapel(TipoPapel);
                            if(altaTipoPapel==true){
                                JOptionPane.showMessageDialog(null,"Tipo de Papel "+TipoPapel+" agregado correctamente.","Correcto",JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        ResultSet buscarClasePapel = mimodelo.buscarClasePapel();
                            combo.removeAllItems();
                            combo.addItem("Selecciona...");
                            while(buscarClasePapel.next()){
                                    combo.addItem(buscarClasePapel.getString(1));
                            }
                        return;
                    } catch (SQLException ex) {
                        mensaje(3,ex.getMessage());
                    }
                }
                mensaje(3,"No agregaste ningun tipo de papel.");
            }   
    public void nuevaMaquina(){
        String maquina = (String)JOptionPane.showInputDialog(null,"Escribe la Nueva Entrada:\n","NUEVO TIPO ENTRADA",JOptionPane.PLAIN_MESSAGE);
        if ((maquina  != null) && (maquina.length() > 0)) {
            maquina = maquina.toUpperCase();
            int conf=JOptionPane.showConfirmDialog(null,"Se agregara la Maquina: " + maquina  + ".",maquina,JOptionPane.OK_CANCEL_OPTION);
            if (conf==JOptionPane.OK_OPTION){
                boolean altanuevomaquina=mimodelo.nuevamaquinah(maquina);
                if(altanuevomaquina==true){
                    JOptionPane.showMessageDialog(null,"Nueva Maquina: "+maquina+" Agregado Correctamente.","Correcto",JOptionPane.INFORMATION_MESSAGE);  
                }
            }
            return; 
        }
        JOptionPane.showMessageDialog(null,"No Agregaste Nueva Maquina.","Error",JOptionPane.ERROR_MESSAGE);   
    }
    public void nuevaTipoDeSalida(){
        String salida = (String)JOptionPane.showInputDialog(null,"Escribe el Nuevo Tipo De Salida:","NUEVO TIPO DE SALIDA",JOptionPane.PLAIN_MESSAGE);
        if ((salida != null) && (salida.length() > 0)) {
            salida = salida.toUpperCase();
            int conf=JOptionPane.showConfirmDialog(null,"Se agregara el tipo de salida: " +salida  + ".",salida,JOptionPane.OK_CANCEL_OPTION);
            if (conf==JOptionPane.OK_OPTION){
                boolean nuevatiposalida=mimodelo.nuevaTipoSalida(salida);
                if(nuevatiposalida==true){
                    JOptionPane.showMessageDialog(null,"Tipo de Salida: "+salida+" Agregado Correctamente.","Correcto",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            return; 
        }
        JOptionPane.showMessageDialog(null,"No Agregaste Tipo De Salida.","Error",JOptionPane.ERROR_MESSAGE);   
    }
    public boolean ANTIPEPS(String cclave, int rk, int rb, String feca) {
        //clave a sumar 
        //total de kilos a sumar
        //total de bobinas a sumar
        String fecue="";
        int total=0,cantidad=0;
        String ID="";
        try{
            ResultSet UE = mimodelo.buscarUltimaEntrada(cclave, feca);
            while(UE.next()){
                fecue=UE.getString("fecha_entrada");
                total=UE.getInt("total") ;
                cantidad=UE.getInt("cantidad");
                ID=UE.getString("id");
            }
            if(rb==0){
                if(rk==0){
                    //no sumes nada
                }
                if(rk>total){
                    //suma la diferencia de rk y vuelve a buscar!
                    
                }
                if(rk<total){
                }
                if(rk==total){
                }
            }
        }catch(Exception e){
            
        }
        return false;
    }
    String identradas_="";
    Double costo_total_salida;
    public boolean PEPS2(String clave,double total_restar,double cantidad_restar){
        String fecpe="";
        Double total=0.0,cantidad=0.0;
        String ID="";
        try{
            ResultSet PE = mimodelo.buscarPrimeraEntrada2(clave,fec);
            while(PE.next()){
                fecpe=PE.getString("fecha_entrada");
                if(fecpe==null){
                    mensaje(2,"no hay entradas de este papel");
                    return false;
                }else{
                    total=Double.parseDouble(PE.getString("total"));
                    cantidad=Double.parseDouble(PE.getString("cantidad"));
                    ID=PE.getString("id");
                    identradas_+=ID;
                }
            }
        }catch(Exception e){
        }
        identradas_+=",";
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
    public void modificarentrada(){
        folioentrada = this.movimientos.__FolioEntrada.getText();
        tipoentrada = this.busquedaid(movimientos.__TipoEntrada);
        if(tipoentrada==0){
            mensaje(3,"Verifica el tipo de Entrada");
            movimientos.__TipoEntrada.requestFocus();
            return;
        }
        propietario=this.busquedaid(movimientos.__PropietarioEntr);
        if(propietario==0){
            mensaje(3,"Verifica el Propietario");
            movimientos.__PropietarioEntr.requestFocus();
            return;
        }
        proveedor=this.busquedaid(movimientos.__ProvEntr);
        /*if(proveedor==0){
            mensaje(3,"Verifica el Proveedor");
            movimientos.__ProvEntr.requestFocus();
            return;
        }*/
        cliente = this.busquedaid(movimientos.__ClientEntr);
        /*if(cliente== 0){
            mensaje(3,"Verifica el Cliente");
            movimientos.__ClientEntr.requestFocus();
            return;
        }*/
        ordenProduccion = this.movimientos.__OrdenProduccionEntr.getText();
        /*if(ordenProduccion.isEmpty()){
            mensaje(3,"Debe Especificar la Orden de Producción de Papel");
            return;
        }*/
        if(movimientos.__TipoEntrada.getText().equals("PAPEL DEL CLIENTE")){
             this.movimientos.__OrdenCompraEntr.setText("N/A");
        }
        ordenCompra = this.movimientos.__OrdenCompraEntr.getText();
        /*if(ordenCompra.isEmpty()){
            mensaje(3,"Debe Especificar la Orden de Compra");
            return;
        }*/    
        docentrada();
        documentoEntrada = this.movimientos.__documentoEntr.getText();
        if(documentoEntrada.isEmpty()){
            mensaje(3,"Debe Especificar el Documento de Entrada");
            return;
        }
        if(movimientos.__chkTurno1Entr.isSelected()){
           t1="t1";
        }
        if(movimientos.__chkTurno2Entr.isSelected()){
           t2="t2";
        }
        if(movimientos.__chkTurno3Entr.isSelected()){
           t3="t3";
        }
        /*if(!movimientos.__chkTurno1Entr.isSelected()&&!movimientos.__chkTurno2Entr.isSelected()&&!movimientos.__chkTurno3Entr.isSelected()){
            mensaje(3,"Selecciona al menos un turno");
            return;
        }*/
        if(movimientos.__tablaEntrada.getValueAt(0, 0)==null){
                        mensaje(3,"Ingresa valores a la tabla");
                        return;
                    }
        String clavePapel="";
        switch(this.modificarentrada){
            case 0:
                Obs=JOptionPane.showInputDialog(null, "Observaciones de la Entrada");
                confir=mensajeConfirmacion("Estas seguro de registrar la entrada","Aceptar");
                if (confir==JOptionPane.OK_OPTION){
                    ResultSet buscarMaxEntrada=null;
                    boolean detalleentrada = false;
                    int id_entrada = 0;
                    try {
                        buscarMaxEntrada = mimodelo.bucarMaxEntrada();
                        while(buscarMaxEntrada.next()){
                            id_entrada = buscarMaxEntrada.getInt(1);
                        }
                        buscarMaxEntrada.close();
                        id_entrada++;
                        for(int i=0;i<movimientos.__tablaEntrada.getRowCount();i++){
                            try{
                                clavePapel=movimientos.__tablaEntrada.getValueAt(i, 0).toString();
                                int totalentrada =Integer.parseInt(movimientos.__tablaEntrada.getValueAt(i, 1).toString());
                                int cantidadentrada=Integer.parseInt(movimientos.__tablaEntrada.getValueAt(i, 2).toString());
                                if(movimientos.__TipoEntrada.getText().equals("PAPEL DEL CLIENTE")){
                                    movimientos.__tablaEntrada.setValueAt("0",i, 4);
                                    movimientos.__tablaEntrada.setValueAt("0.0",i, 5);
                                }
                                String ubicacion = movimientos.__tablaEntrada.getValueAt(i, 3).toString();
                                String costo=movimientos.__tablaEntrada.getValueAt(i, 4).toString();
                                String totalcosto=movimientos.__tablaEntrada.getValueAt(i, 5).toString();
                                ResultSet existenciaPapel = mimodelo.buscarExistenciaPapel(clavePapel);
                                int totalentr = 0,nuevototentr;
                                int cantentr =0, nuevacantentr;
                                if(existenciaPapel.next()){
                                    /*existenciaPapel.beforeFirst();
                                    while(existenciaPapel.next()){
                                        totalentr=existenciaPapel.getInt(4);
                                        cantentr=existenciaPapel.getInt(5);
                                    }
                                    existenciaPapel.close();
                                    nuevototentr = totalentr+totalentrada;
                                    nuevacantentr = cantentr+cantidadentrada;
                                    mimodelo.nuevaExistencia(nuevototentr+"",clavePapel,nuevacantentr+"");*/
                                    detalleentrada = mimodelo.altaDetalleEntrada(id_entrada,clavePapel,totalentrada+"",cantidadentrada+"",ubicacion,costo,totalcosto);
                                    mimodelo.sumarexistencia(clavePapel);
                                    mimodelo.costopromedio(clavePapel);
                                    mimodelo.ubicacion(clavePapel, ubicacion);
                                }else{
                                    mensaje(3,"EL PAPEL NO EXISTE");
                                    return;
                                }
                            }catch(Exception evt){
                                break;
                            }
                        }
                        String fechaentrada=fec.replaceAll("-", "");
                        //boolean altaEntrada = mimodelo.altaEntrada(folioentrada,t1, t2, t3, ordenProduccion, ordenCompra,documentoEntrada, propietario, proveedor, id_responsable, fechaentrada, tipoentrada,Obs,cliente);
                        boolean altaEntrada = mimodelo.altaEntrada(folioentrada,t1, t2, t3, ordenProduccion, ordenCompra,documentoEntrada, propietario, proveedor, id_responsable, fechaentrada, tipoentrada,Obs,cliente);
                        mimodelo.altaDocEntrada(eval);
                        if(altaEntrada==true && detalleentrada==true){
                            mensaje(1,"Entrada agregada correctamente");
                            this.borrarFormularioMovimientosPapel();
                            mimodelo.costopromedio(clavePapel);
                        }else{
                            mensaje(3,"Ocurrio un error al dar de alta la entrada");
                            break;
                        }
                        } catch (SQLException ex) {
                            mensaje(3,ex.getMessage());
                            break;
                        }
                    break;
                }
            break;
            case 1:
                Obs=JOptionPane.showInputDialog(null, "Observaciones de la Entrada");
                confir=mensajeConfirmacion("Estas seguro de modificar la entrada","Aceptar");
                if (confir==JOptionPane.OK_OPTION){
                    for(int i=0;i<movimientos.__tablaEntrada.getRowCount();i++){
                        try{
                            clavePapel=movimientos.__tablaEntrada.getValueAt(i, 0).toString();
                            int totalentrada =Integer.parseInt(movimientos.__tablaEntrada.getValueAt(i, 1).toString());
                            int cantidadentrada=Integer.parseInt(movimientos.__tablaEntrada.getValueAt(i, 2).toString());
                            if(movimientos.__TipoEntrada.getText().equals("PAPEL DEL CLIENTE")){
                                movimientos.__tablaEntrada.setValueAt("0",i, 4);
                                movimientos.__tablaEntrada.setValueAt("0.0",i, 5);
                            }
                            String ubicacion = movimientos.__tablaEntrada.getValueAt(i, 3).toString();
                            String costo=movimientos.__tablaEntrada.getValueAt(i, 4).toString();
                            String totalcosto=movimientos.__tablaEntrada.getValueAt(i, 5).toString();
                            ResultSet existenciaPapel = mimodelo.buscarExistenciaPapel(clavePapel);
                            int totalentr = 0,nuevototentr;
                            int cantentr =0,nuevacantentr;
                            if(existenciaPapel.next()){
                                /*existenciaPapel.beforeFirst();
                                while(existenciaPapel.next()){
                                    totalentr=existenciaPapel.getInt(4);
                                    cantentr= existenciaPapel.getInt(5);
                                }
                                existenciaPapel.close();
                                nuevototentr = totalentr+totalentrada;
                                nuevacantentr = cantidadentrada + cantentr;
                                mimodelo.nuevaExistencia(nuevototentr+"",clavePapel,nuevacantentr+"");*/
                                mimodelo.modifDetalleEntrada(Integer.parseInt(identradas[i]),clavePapel,totalentrada+"",cantidadentrada+"",ubicacion,costo,totalcosto);
                                mimodelo.costopromedio(clavePapel);
                                mimodelo.sumarexistencia(clavePapel);
                                mimodelo.ubicacion(clavePapel, ubicacion);
                            }else{
                                mensaje(3,"EL PAPEL NO EXISTE");
                                return;
                            }
                        }catch(Exception evt){
                            break;
                        }
                    }
                    String fechaentrada=fec.replaceAll("-", "");
                    boolean modifEntrada = mimodelo.modifEntrada(folioentrada,t1, t2, t3, ordenProduccion, ordenCompra,documentoEntrada, propietario, proveedor, id_responsable, fechaentrada, tipoentrada,Obs,cliente);
                    mimodelo.altaDocEntrada(eval);
                    if(modifEntrada==true){
                        mensaje(1,"Modificacion Correcta");
                        mimodelo.costopromedio(clavePapel);
                        this.borrarFormularioMovimientosPapel();
                        this.movimientos.__MenuMovimiento.setEnabled(true);
                        this.movimientos.__Archivo.setEnabled(true);
                        this.movimientos.__Edicion.setEnabled(true);
                        this.movimientos.__MODIFICACIONENTRADA.setEnabled(true);
                        this.movimientos.JPanel.setEnabledAt(0, true);
                        this.movimientos.JPanel.setEnabledAt(1, true);
                        this.movimientos.JPanel.setEnabledAt(2, true);
                        this.movimientos.JPanel.setEnabledAt(3, true);
                    }
                }
                break;
        }
        
    }
    String documentoSalida;
    public void modificarsalida(){
        foliosalida = this.movimientos.__FolioSalida.getText();
        tiposalida = this.busquedaid(movimientos.__TipoSalida);
        if(tiposalida==0){
            mensaje(3,"Verifica el tipo de Salida");
            movimientos.__TipoSalida.requestFocus();
            return;
        }
        propietario=this.busquedaid(movimientos.__PropietarioSalida);
        if(propietario==0){
            mensaje(3,"Verifica el Propietario");
            movimientos.__PropietarioSalida.requestFocus();
            return;
        }
        proveedor=this.busquedaid(movimientos.__ProvSalida);
        /*if(proveedor==0){
            mensaje(3,"Verifica el Proveedor");
            movimientos.__ProvSalida.requestFocus();
            return;
        }*/
        cliente = this.busquedaid(movimientos.__ClientSalida);
        /*if(cliente== 0){
            mensaje(3,"Verifica el Cliente");
            movimientos.__ClientSalida.requestFocus();
            return;
        }*/
        ordenProduccion = this.movimientos.__OrdenProduccionSalida.getText();
        /*if(ordenProduccion.isEmpty()){
            mensaje(3,"Debe Especificar la Orden de Producción de Papel");
            movimientos.__OrdenProduccionSalida.requestFocus();
            return;
        }*/
        if(movimientos.__TipoSalida.getText().equals("PAPEL DEL CLIENTE")){
             this.movimientos.__OrdenCompraEntr.setText("N/A");
        }
        ordenCompra = this.movimientos.__OrdenCompraSalida.getText();
        /*if(ordenCompra.isEmpty()){
            mensaje(3,"Debe Especificar la Orden de Compra");
            movimientos.__OrdenCompraSalida.requestFocus();
            return;
        } */   
        documentoSalida = this.movimientos.__documentoSalida.getText();
        if(documentoSalida.isEmpty()){
            mensaje(3,"Debe Especificar el Documento de Salida");
            movimientos.__documentoSalida.requestFocus();
            return;
        }
        if(movimientos.__chkTurno1Salida.isSelected()){
           t1="t1";
        }
        if(movimientos.__chkTurno2Salida.isSelected()){
           t2="t2";
        }
        if(movimientos.__chkTurno3Salida.isSelected()){
           t3="t3";
        }
        if(!movimientos.__chkTurno1Salida.isSelected()&&!movimientos.__chkTurno2Salida.isSelected()&&!movimientos.__chkTurno3Salida.isSelected()){
            mensaje(3,"Selecciona al menos un turno");
            return;
        }
        if(movimientos.__tablaSalida.getValueAt(0, 0)==null){
                        mensaje(3,"Ingresa valores a la tabla");
                        return;
                    }
        
        for(int i=0;i<movimientos.__tablaSalida.getRowCount();i++){
            
            try{
                String clavePapel=movimientos.__tablaSalida.getValueAt(i, 0).toString();
                int totalsalida =Integer.parseInt(movimientos.__tablaSalida.getValueAt(i, 1).toString());
                int cantidadsalida=Integer.parseInt(movimientos.__tablaSalida.getValueAt(i, 2).toString());
                String costo=movimientos.__tablaSalida.getValueAt(i, 3).toString();
                String totalcosto=movimientos.__tablaSalida.getValueAt(i, 4).toString();
                ResultSet existenciaPapel = mimodelo.buscarExistenciaPapelfecha(clavePapel,fec);
                int totalentr = 0,nuevototentr;
                int cantentr =0, nuevacantentr;
                if(existenciaPapel.next()){
                    existenciaPapel.beforeFirst();
                    while(existenciaPapel.next()){
                        totalentr=existenciaPapel.getInt(1);
                        cantentr=existenciaPapel.getInt(2);
                    }
                    if(totalsalida>totalentr){
                        mensaje(3,"tu existencia de kg/hojas es de "+totalentr+" en la salida " +(i+1));
                        return;

                    }
                    if(cantidadsalida>cantentr){
                        mensaje(3,"tu cantidad de paq/bob es de "+cantentr+" en la salida " +(i+1));
                        return;
                    }
                }else{
                    mensaje(3,"EL PAPEL NO EXISTE");
                    return;
                }
            }catch(Exception e){
            }
        }
        switch(this.modificarsalida){
            case 0:
                Obs=JOptionPane.showInputDialog(null, "Observaciones de la Salida");
                confir=mensajeConfirmacion("Estas seguro de registrar la Salida","Aceptar");
                if (confir==JOptionPane.OK_OPTION){
                    ResultSet buscarMaxSalida=null;
                    boolean detallesalida = false;
                    int id_salida = 0;
                    
                    try {
                        buscarMaxSalida = mimodelo.bucarMaxSalida();
                        while(buscarMaxSalida.next()){
                            id_salida = buscarMaxSalida.getInt(1);
                        }
                        buscarMaxSalida.close();
                        id_salida++;
                        for(int i=0;i<movimientos.__tablaSalida.getRowCount();i++){
                            try{
                                String clavePapel=movimientos.__tablaSalida.getValueAt(i, 0).toString();
                                int totalsalida =Integer.parseInt(movimientos.__tablaSalida.getValueAt(i, 1).toString());
                                int cantidadsalida=Integer.parseInt(movimientos.__tablaSalida.getValueAt(i, 2).toString());
                                String costo=movimientos.__tablaSalida.getValueAt(i, 3).toString();
                                String totalcosto=movimientos.__tablaSalida.getValueAt(i, 4).toString();
                                ResultSet existenciaPapel = mimodelo.buscarExistenciaPapelfecha(clavePapel,fec);
                                int totalentr = 0,nuevototentr;
                                int cantentr =0, nuevacantentr;
                                if(existenciaPapel.next()){
                                    existenciaPapel.beforeFirst();
                                    while(existenciaPapel.next()){
                                        totalentr=existenciaPapel.getInt(1);
                                         cantentr=existenciaPapel.getInt(2);
                                    }
                                    if(totalsalida>totalentr){
                                        mensaje(3,"tu existencia de kg/hojas es de "+totalentr);
                                        return;
                                        
                                    }
                                    if(cantidadsalida>cantentr){
                                        mensaje(3,"tu cantidad de paq/bob es de "+cantentr);
                                        return;
                                    }
                                    identradas_="";
                                    this.PEPS2(clavePapel, totalsalida, cantidadsalida);
                                    mimodelo.sumarexistencia(clavePapel);
                                    detallesalida = mimodelo.altaDetalleSalida(id_salida,clavePapel,totalsalida+"",cantidadsalida+"",costo,totalcosto,identradas_);
                                    
                                }else{
                                    mensaje(3,"EL PAPEL NO EXISTE");
                                    return;
                                }
                            }catch(Exception evt){
                                break;
                            }
                        }
                        
                        String fechaentrada=fec.replaceAll("-", "");
                        boolean altaSalida = mimodelo.altaSalida(foliosalida,t1, t2, t3, ordenProduccion, ordenCompra,documentoSalida, propietario, proveedor, id_responsable, fechaentrada, tiposalida,Obs,cliente);
                        if(altaSalida==true && detallesalida==true){
                            mensaje(1,"Salida agregada correctamente");
                            this.borrarFormularioMovimientosPapel();
                            
                        }else{
                            mensaje(3,"Ocurrio un error al dar de alta la SALIDA");
                            break;
                        }
                        } catch (SQLException ex) {
                            mensaje(3,ex.getMessage());
                            break;
                        }
                    break;
                }
                break;
            case 1:
                Obs=JOptionPane.showInputDialog(null, "Observaciones de la Salida");
                confir=mensajeConfirmacion("Estas seguro de modificar la Salida","Aceptar");
                
                if (confir==JOptionPane.OK_OPTION){
                    for(int i=0;i<movimientos.__tablaSalida.getRowCount();i++){
                            try{
                                String clavePapel=movimientos.__tablaSalida.getValueAt(i, 0).toString();
                                int totalsalida =Integer.parseInt(movimientos.__tablaSalida.getValueAt(i, 1).toString());
                                int cantidadsalida=Integer.parseInt(movimientos.__tablaSalida.getValueAt(i, 2).toString());
                                String costo=movimientos.__tablaSalida.getValueAt(i, 3).toString();
                                String totalcosto=movimientos.__tablaSalida.getValueAt(i, 4).toString();
                                ResultSet existenciaPapel = mimodelo.buscarExistenciaPapelfecha(clavePapel,fec);
                                int totalentr = 0,nuevototentr;
                                int cantentr =0, nuevacantentr;
                                if(existenciaPapel.next()){
                                    existenciaPapel.beforeFirst();
                                    while(existenciaPapel.next()){
                                        totalentr=existenciaPapel.getInt(1);
                                         cantentr=existenciaPapel.getInt(2);
                                    }
                                    if(totalsalida>totalentr){
                                        mensaje(3,"tu existencia de kg/hojas es de "+totalentr);
                                        return;
                                        
                                    }
                                    if(cantidadsalida>cantentr){
                                        mensaje(3,"tu cantidad de paq/bob es de "+cantentr);
                                        return;
                                    }
                                    identradas_="";
                                    this.PEPS2(clavePapel, totalsalida, cantidadsalida);
                                    mimodelo.sumarexistencia(clavePapel);
                                    mimodelo.modifDetalleSalida(id_salida,clavePapel,totalsalida+"",cantidadsalida+"",costo,totalcosto,identradas_);
                                }else{
                                    mensaje(3,"EL PAPEL NO EXISTE");
                                    return;
                                }
                            }catch(Exception evt){
                                break;
                            }
                        }
                    String fechaentrada=fec.replaceAll("-", "");
                    boolean modifSalida = mimodelo.modifSalida(foliosalida,t1, t2, t3, ordenProduccion, ordenCompra,documentoSalida, propietario, proveedor, id_responsable, fechaentrada, tiposalida,Obs,cliente);
                    if(modifSalida==true){
                        mensaje(1,"Modificacion Correcta");
                        this.borrarFormularioMovimientosPapel();
                        this.movimientos.__MenuMovimiento.setEnabled(true);
                        this.movimientos.__Archivo.setEnabled(true);
                        this.movimientos.__Edicion.setEnabled(true);
                        this.movimientos.__MODIFICACIONSALIDA.setEnabled(true);
                        this.movimientos.JPanel.setEnabledAt(0, true);
                        this.movimientos.JPanel.setEnabledAt(2, true);
                        this.movimientos.JPanel.setEnabledAt(3, true);
                    }
                }
                break;
        }
    }
    public void modificacionsalh(){
        tiposal=busquedaid(movimientos.__TipodeSalidaH);
                if(tiposal==0){
                    mensaje(3,"Verifica el tipo de salida");
                    movimientos.__TipodeSalidaH.requestFocus();;
                    return;
                }
        int clientesalh = this.busquedaid(movimientos.__ClienteSalidaH);
                if(clientesalh==0){
                    mensaje(3,"Verifica el cliente");
                    movimientos.__ClienteSalidaH.requestFocus();;
                    return;
                }
                int propsalh=busquedaid(movimientos.__PropietarioSalidaH);
                if(propsalh==0){
                    mensaje(3,"Verifica el propietario");
                    movimientos.__PropietarioSalidaH.requestFocus();;
                    return;
                }
        String titulosalh = movimientos.__TituloSalidaHoja.getText();
                String folio = movimientos.__FolioSalidaHoja.getText();
                if(titulosalh.isEmpty()){
                    mensaje(3,"escribe el titulo");
                    movimientos.__TituloSalidaHoja.requestFocus();
                    return;
                }
                String opsalh= movimientos.__OPSalidaHoja.getText();
                if(opsalh.isEmpty()){
                    mensaje(3,"escribe la orden de produccion");
                    movimientos.__OPSalidaHoja.requestFocus();
                    return;
                }
                String epsalh = movimientos.__EstandarProduccionSalidaHoja.getText();
                if(epsalh.isEmpty()){
                    mensaje(3,"escribe el estandar de produccion");
                    movimientos.__EstandarProduccionSalidaHoja.requestFocus();
                    return;
                }
                int maqsalh=this.busquedaid(movimientos.__MaquinaSalidaH);
                if(maqsalh==0){
                    mensaje(3,"Verifica la maquina");
                    movimientos.__MaquinaSalidaH.requestFocus();;
                    return;
                }
                if(!movimientos.__chkTurno1SalHoja.isSelected()&&!movimientos.__chkTurno2SalHoja.isSelected()&&!movimientos.__chkTurno3SalHoja.isSelected()){
                        mensaje(3,"Selecciona al menos un turno");
                        return;
                    }
                if(movimientos.__chkTurno1SalHoja.isSelected()){
                       t1="t1";
                    }
                    if(movimientos.__chkTurno2SalHoja.isSelected()){
                       t2="t2";
                    }
                    if(movimientos.__chkTurno3SalHoja.isSelected()){
                       t3="t3";
                    }
                    if(movimientos.__tablaSalidaHoja.getValueAt(0, 0)==null){
                        mensaje(3,"Ingresa valores a la tabla");
                        return;
                    }
                    for(int i=0;i<movimientos.__tablaSalidaHoja.getRowCount();i++){
                        try{
                            String cpsalh = movimientos.__tablaSalidaHoja.getValueAt(i, 0).toString();
                            double totalhojassalh =Double.parseDouble(movimientos.__tablaSalidaHoja.getValueAt(i, 1).toString());
                            int contsalh=0;
                            double cantsalh=Double.parseDouble(movimientos.__tablaSalidaHoja.getValueAt(i, 2).toString());
                            costo = new BigDecimal(movimientos.__tablaSalidaHoja.getValueAt(i, 3).toString());
                            totalcosto =new BigDecimal( movimientos.__tablaSalidaHoja.getValueAt(i, 4).toString());
                            int restosalh=0;
                            ResultSet existenciaPapelh = mimodelo.buscarExistenciaPapelfecha(cpsalh,fec);
                            double totalsalh = 0.0,nuevototsalh, totalcanth=0.0,nuevacantsalh;
                            if(existenciaPapelh.next()){
                                existenciaPapelh.beforeFirst();
                                while(existenciaPapelh.next()){
                                    totalsalh=Double.parseDouble(existenciaPapelh.getString("cantidad"));
                                    totalcanth=Double.parseDouble(existenciaPapelh.getString("presentacion"));
                                }
                            }else{
                                mensaje(3,"EL PAPEL NO EXISTE");
                                return;
                            }
                            if(totalhojassalh>totalsalh){
                                mensaje(3,"No tienes suficiente existencia en la salida #"+(i+1)+". El total es de "+totalsalh);
                                return;
                            }else{
                                if(cantsalh>totalcanth){
                                    mensaje(3,"No tienes suficiente cantidad en la salida #"+(i+1)+". El total es de "+totalcanth);
                                    return;
                                }else{
                                    if(totalhojassalh==totalsalh&&cantsalh<totalcanth){
                                        mensaje(3,"Verifica el numero de paquetes");
                                        return;
                                    }
                                    if(cantsalh==totalcanth&&totalhojassalh<totalsalh){
                                        mensaje(3,"Verifica el total de hojas");
                                        return;
                                    }
                                }
                            }
                        }catch(Exception evt){
                        }
                    }
                switch(this.modificarsalidah){
                       case 0:
                           Obs = JOptionPane.showInputDialog(null,"Observaciones de la salida de Hoja");
                           confir = this.mensajeConfirmacion("Estas Seguro de Realizar la Salida", "Salida");
                            if(confir==JOptionPane.OK_OPTION){
                                ResultSet buscarMaxSalidah=null;
                                boolean detallesalidah=false;
                                int id_salidah=0;
                                try{
                                    buscarMaxSalidah=mimodelo.bucarMaxSalidah();
                                    while(buscarMaxSalidah.next()){
                                        id_salidah=buscarMaxSalidah.getInt(1);
                                    }
                                    id_salidah++;
                                    for(int i=0;i<movimientos.__tablaSalidaHoja.getRowCount();i++){
                                        try{
                                            String cpsalh = movimientos.__tablaSalidaHoja.getValueAt(i, 0).toString();
                                            double totalhojassalh =Double.parseDouble(movimientos.__tablaSalidaHoja.getValueAt(i, 1).toString());
                                            int contsalh=0;
                                            double cantsalh=Double.parseDouble(movimientos.__tablaSalidaHoja.getValueAt(i, 2).toString());
                                            costo = new BigDecimal(movimientos.__tablaSalidaHoja.getValueAt(i, 3).toString());
                                            totalcosto =new BigDecimal( movimientos.__tablaSalidaHoja.getValueAt(i, 4).toString());
                                            int restosalh=0;
                                            ResultSet existenciaPapelh = mimodelo.buscarExistenciaPapelfecha(cpsalh,fec);
                                            double totalsalh = 0.0,nuevototsalh, totalcanth=0.0,nuevacantsalh;
                                            if(existenciaPapelh.next()){
                                                existenciaPapelh.beforeFirst();
                                                while(existenciaPapelh.next()){
                                                totalsalh=Double.parseDouble(existenciaPapelh.getString("cantidad"));
                                                totalcanth=Double.parseDouble(existenciaPapelh.getString("presentacion"));
                                                }
                                            }else{
                                                mensaje(3,"EL PAPEL NO EXISTE");
                                                return;
                                            }
                                            if(totalhojassalh>totalsalh){
                                                mensaje(3,"No tienes suficiente existencia en la salida #"+(i+1)+". El total es de "+totalhojassalh);
                                                detallesalidah=false;
                                                break;
                                            }else{
                                                if(cantsalh>totalcanth){
                                                     mensaje(3,"No tienes suficiente cantidad en la salida #"+(i+1)+". El total es de "+cantsalh);
                                                     detallesalidah=false;
                                                     break;
                                                }else{
                                                    if(totalhojassalh==totalsalh&&cantsalh<totalcanth){
                                                        mensaje(3,"Verifica el numero de paquetes");
                                                        return;
                                                    }
                                                    if(cantsalh==totalcanth&&totalhojassalh<totalsalh){
                                                         mensaje(3,"Verifica el total de hojas");
                                                        return;
                                                    }
                                                    nuevototsalh = totalsalh-totalhojassalh;
                                                    nuevacantsalh=totalcanth-cantsalh;
                                                    identradas_="";
                                                    if(!PEPS2(cpsalh,totalhojassalh,cantsalh)){
                                                        return;
                                                    }
                                                    mimodelo.sumarexistencia(cpsalh);
                                                    detallesalidah=mimodelo.altaDetalleSalidah(id_salidah,cpsalh,totalhojassalh,contsalh,cantsalh,restosalh,costo+"",totalcosto+"",identradas_);
                                                }
                                            }
                                        }catch(Exception evt){
                                            break;
                                        }
                                        
                                    }
                                    String fechasalidah = fec.replaceAll("-", "");
                                        boolean altasalidah=mimodelo.altaSalidah(folio, t1, t2, t3,opsalh, epsalh, clientesalh, propsalh, maqsalh, fechasalidah, titulosalh, id_responsable,Obs,tiposal);
                                        if(altasalidah==true&&detallesalidah==true){
                                              mensaje(1,"Salida de Hoja agregada correctamente");
                                              this.borrarFormularioMovimientosPapel();
                                        }
                                }catch(SQLException ex){
                                    mensaje(3,ex.getMessage());
                                    break;
                                }
                            }
                           break;
                       case 1:
                           Obs = JOptionPane.showInputDialog(null,"Observaciones de la salida de Hoja");
                            confir=mensajeConfirmacion("Estas Seguro de modificar la salida de hoja","Aceptar");
                            if (confir==JOptionPane.OK_OPTION){
                                    for(int i=0;i<movimientos.__tablaSalidaHoja.getRowCount();i++){
                                        
                                        try{
                                            String cpsalh = movimientos.__tablaSalidaHoja.getValueAt(i, 0).toString();
                                            int totalhojassalh =Integer.parseInt(movimientos.__tablaSalidaHoja.getValueAt(i, 1).toString());
                                            int contsalh=0;
                                            int cantsalh=Integer.parseInt(movimientos.__tablaSalidaHoja.getValueAt(i, 2).toString());
                                            int restosalh=0;
                                             costo = new BigDecimal(movimientos.__tablaSalidaHoja.getValueAt(i, 3).toString());
                                            totalcosto =new  BigDecimal(movimientos.__tablaSalidaHoja.getValueAt(i, 4).toString());
                                            ResultSet existenciaPapelh = mimodelo.buscarExistenciaPapelfecha(cpsalh,fec);
                                            int totalsalh = 0,nuevototsalh, totalcanth=0,nuevacantsalh;
                                            if(existenciaPapelh.next()){
                                                existenciaPapelh.beforeFirst();
                                                    while(existenciaPapelh.next()){
                                                    totalsalh=existenciaPapelh.getInt("cantidad");
                                                    totalcanth=existenciaPapelh.getInt("presentacion");
                                                }
                                            }else{
                                                mensaje(3,"EL PAPEL NO EXISTE");
                                                return;
                                            }
                                            if(totalhojassalh>totalsalh){
                                                mensaje(2,"No Tienes Suficiente Existencia en la Salida #"+(i+1)+". El total es de "+totalhojassalh);
                                                return;
                                            }else{
                                                if(cantsalh>totalcanth){
                                                     mensaje(2,"No Tienes Suficiente Cantidad en la Salida #"+(i+1)+". El total es de "+cantsalh);
                                                     return;
                                                }else{
                                                    if(totalhojassalh==totalsalh&&cantsalh<totalcanth){
                                                        mensaje(3,"Verifica el Numero de Paquetes");
                                                        return;
                                                    }
                                                    if(cantsalh==totalcanth&&totalhojassalh<totalsalh){
                                                         mensaje(3,"Verifica el Total de Hojas");
                                                        return;
                                                    }
                                                    nuevototsalh = totalsalh-totalhojassalh;
                                                    nuevacantsalh=totalcanth-cantsalh;
                                                    identradas_="";
                                                    if(!PEPS2(cpsalh,totalhojassalh,cantsalh)){
                                                        return;
                                                    }
                                                    mimodelo.sumarexistencia(cpsalh);
                                                    mimodelo.modifDetalleSalidah(Integer.parseInt(idsalidash[i]),cpsalh,totalhojassalh,contsalh,cantsalh,restosalh,costo+"",totalcosto+"",identradas_);
                                                }
                                            }
                                        }catch(Exception evt){
                                            break;
                                        }
                                    }
                                    String fechaentrada=fec.replaceAll("-", "");
                                     boolean modifEntrada=mimodelo.modifSalidaH(folio, t1, t2, t3, opsalh, epsalh, propsalh, maqsalh, titulosalh, id_responsable, fechaentrada, tiposal, Obs, clientesalh);
                                    if(modifEntrada==true){
                                      mensaje(1,"Modificacion Correcta");
                                      this.borrarFormularioMovimientosPapel();
                                      this.movimientos.__MenuMovimiento.setEnabled(true);
                                    this.movimientos.__Archivo.setEnabled(true);
                                    this.movimientos.__Edicion.setEnabled(true);
                                    this.movimientos.__MODIFICACIONH.setEnabled(true);
                                    this.movimientos.JPanel.setEnabledAt(0, true);
                                    this.movimientos.JPanel.setEnabledAt(1, true); 
                                    this.movimientos.JPanel.setEnabledAt(2, true); 
                                    this.movimientos.JPanel.setEnabledAt(3, true);
                                    }else{
                                    mensaje(3,"Modificacion Incorrecta");
                                }
                            }
                           break;
                }
    }
    int totalkkgssss;
    public void modificacionsalb(){
        foliob = movimientos.__FolioSalidaBobina.getText();
        tiposal=this.busquedaid(movimientos.__TipodeSalidaB);
        if(tiposal==0){
            mensaje(3,"Debes Seleccionar un Tipo de Salida");
            movimientos.__TipodeSalidaB.requestFocus();
            return;
        }
        titulosalb = movimientos.__TituloSalidaBobina.getText();
        if(titulosalb.isEmpty()){
            mensaje(3,"Escribe el Titulo");
            movimientos.__TituloSalidaBobina.requestFocus();
            return;
        }
        opsalB= movimientos.__OPSalidaBobina.getText();
        if(opsalB.equals("    -    ")){
            mensaje(3,"Escribe la Orden de Produccion");
            movimientos.__OPSalidaBobina.requestFocus();
            return;
        }
        factor = movimientos.__FactorSalidaBobina.getText();
        if(factor.isEmpty()){
            mensaje(3,"Escribe el Factor");
            movimientos.__FactorSalidaBobina.requestFocus();
            return;
        }
       pliego =movimientos.__PliegoSalidaBobina.getText();
        if(pliego.isEmpty()){
            mensaje(3,"Escribe el Pliego");
            movimientos.__PliegoSalidaBobina.requestFocus();
            return;
        }
        clientesalb = this.busquedaid(movimientos.__ClienteSalidaB);
        if(clientesalb==0){
            mensaje(3,"Verifica el Cliente");
            movimientos.__ClienteSalidaB.requestFocus();
            return;
        }
        propsalb=this.busquedaid(movimientos.__PropietarioSalidaB);
        if(propsalb==0){
            mensaje(3,"verifica el Propietario");
            movimientos.__PropietarioSalidaB.requestFocus();
            return;
        }
        maqsalb=this.busquedaid(movimientos.__MaquinaSalidaB);
        if(maqsalb==0){
            mensaje(3,"Verifica la Maquina");
            movimientos.__MaquinaSalidaB.requestFocus();
            return;
        }
        t1="";
        t2="";
        t3="";
        if(movimientos.__chkTurno1SalBobina.isSelected()){
            t1="t1";
        }
        if(movimientos.__chkTurno2SalBobina.isSelected()){
            t2="t2";
        }
        if(movimientos.__chkTurno3SalBobina.isSelected()){
           t3="t3";
        }
        if(!movimientos.__chkTurno1SalBobina.isSelected()&&!movimientos.__chkTurno2SalBobina.isSelected()&&!movimientos.__chkTurno3SalBobina.isSelected()){
            mensaje(3,"Selecciona al Menos un Turno");
            return;
        }
        totalkil = movimientos.__TotalKGSalidaBobina1.getText();
        if(totalkil.isEmpty()){
            mensaje(3,"Escribe el Total de kg");
            movimientos.__TotalKGSalidaBobina1.requestFocus();
            return;
        } 
        tiroxpliego = movimientos.__TiroXPliegoSalidaBobina1.getText();
        if(tiroxpliego.isEmpty()){
            mensaje(3,"Escribe el Tiro por Pliego");
            movimientos.__TiroXPliegoSalidaBobina1.requestFocus();
            return;
        } 
        pliegosdeajuste = movimientos.__PliegodeAjusteSalidaBobina1.getText();
        if(pliegosdeajuste.isEmpty()){
            mensaje(3,"Escribe los Pliegos de Ajuste");
            movimientos.__PliegodeAjusteSalidaBobina1.requestFocus();
            return;
        }
        pliegokgs = movimientos.__KGPliegoSalidaBobina1.getText();
        if(pliegokgs.isEmpty()){
            mensaje(3,"Escribe los Pliegos de Ajuste");
            movimientos.__KGPliegoSalidaBobina1.requestFocus();
            return;
        }
        ajustekgs= movimientos.__KGAjusteSalidaBobina1.getText();
        if(ajustekgs.isEmpty()){
            mensaje(3,"Escribe los Pliegos de Ajuste");
            movimientos.__PliegoSalidaBobina.requestFocus();
            return;
        }
        tmpkg=0.0;
        totalkkgssss=0;
        switch(modificarsalidab){
            case 0:
                for(int i=0;i<movimientos.__tablaSalidaBobinaInventario.getRowCount();i++){
                    try{
                        tmpclv= movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 0).toString();
                        invinikgs = Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 1).toString());
                        invinibob = Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 2).toString());
                        surkgs=Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 3).toString());
                        surbob=Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 4).toString());
                        devkgs=Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 5).toString());
                        devbob=Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 6).toString());
                        costo= new BigDecimal(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 7)+"");
                        totalcosto=new BigDecimal (movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 8)+"");
                        tmpkg = invinikgs + surkgs - devkgs+0.0;
                        totalkkgssss+= tmpkg;
                        tmpbob =  invinibob + surbob -devbob+0.0;
                        ResultSet buscarExistenciaPapelB = mimodelo.buscarExistenciaPapelfecha(tmpclv,fec);
                        while(buscarExistenciaPapelB.next()){
                            exiskgs=buscarExistenciaPapelB.getInt("cantidad");
                            exisbob=buscarExistenciaPapelB.getInt("presentacion");
                        }
                        if(exiskgs<tmpkg){
                            mensaje(3, "No Tienes Suficientes Kilos para la Salida #"+(i+1)+". El Total es de "+exiskgs);
                            return;
                        }else if(exisbob<tmpbob){
                                mensaje(3, "No Tienes Suficientes Bobinas para la Salida #"+(i+1)+". El Total es de "+exisbob);
                                return;
                        } else{
                            int tmpkgi = ( int) Math.floor ( tmpkg );
                            newkgs =exiskgs-tmpkgi;
                            int tmpbobi = ( int) Math.floor ( tmpbob );
                            newbob = exisbob-tmpbobi;
                            newkgs=( int) Math.floor ( tmpkg );
                            newbob=( int) Math.floor ( tmpbob );
                        }
                    }catch(Exception evt){
                        break;
                    }
                }
                tmppliegos = Double.parseDouble(totalkkgssss+"")/Double.parseDouble(factor);
                emergente.__TotalSurtidoKG.setText(totalkkgssss+"");
                emergente.__TotalSurtidoPliegos.setText(tmppliegos+"");
                emergente.setVisible(true);
                emergente.setLocationRelativeTo(null);
                emergente.__DesperdicioKG.requestFocus();
                break;
            case 1:
                for(int i=0;i<movimientos.__tablaSalidaBobinaInventario.getRowCount();i++){
                    try{
                        tmpclv= movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 0).toString();
                        invinikgs = Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 1).toString());
                        invinibob = Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 2).toString());
                        surkgs=Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 3).toString());
                        surbob=Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 4).toString());
                        devkgs=Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 5).toString());
                        devbob=Integer.parseInt(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 6).toString());
                        costo= new BigDecimal(movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 7)+"");
                        totalcosto=new BigDecimal (movimientos.__tablaSalidaBobinaInventario.getValueAt(i, 8)+"");
                        tmpkg = invinikgs + surkgs - devkgs+0.0;
                        totalkkgssss+= tmpkg;
                        tmpbob =  invinibob + surbob -devbob+0.0;
                        ResultSet buscarExistenciaPapelB = mimodelo.buscarExistenciaPapelfecha(tmpclv,fec);
                        while(buscarExistenciaPapelB.next()){
                            exiskgs=buscarExistenciaPapelB.getInt("cantidad");
                            exisbob=buscarExistenciaPapelB.getInt("presentacion");
                        }
                        if(exiskgs<tmpkg){
                            mensaje(3, "No Tienes Suficientes Kilos para la Salida #"+(i+1)+". El Total es de "+exiskgs);
                            return;
                        }else if(exisbob<tmpbob){
                                mensaje(3, "No Tienes Suficientes Bobinas para la Salida #"+(i+1)+". El Total es de "+exisbob);
                                return;
                        } else{
                            int tmpkgi = ( int) Math.floor ( tmpkg );
                            newkgs =exiskgs-tmpkgi;
                            int tmpbobi = ( int) Math.floor ( tmpbob );
                            newbob = exisbob-tmpbobi;
                            newkgs=( int) Math.floor ( tmpkg );
                            newbob=( int) Math.floor ( tmpbob );
                        }
                    }catch(Exception evt){
                        break;
                    }
                }
                tmppliegos = Double.parseDouble(totalkkgssss+"")/Double.parseDouble(factor);
                emergente.__TotalSurtidoKG.setText(totalkkgssss+"");
                emergente.__TotalSurtidoPliegos.setText(tmppliegos+"");
                emergente.setVisible(true);
                emergente.setLocationRelativeTo(null);
                emergente.__DesperdicioKG.requestFocus();
                break;
        }
    }
    public int busquedaid(JTextField campo){
        String parametro = campo.getText();
        if(parametro.isEmpty()){
            return 0;
        }else{
            ResultSet p=null;
            switch (campo.getName()){
                case "__TipoEntrada":
                    p = mimodelo.buscaTipoEntrada(parametro,false);
                    break;
                case "__PropietarioEntr":
                    p = mimodelo.buscarPropiedad(parametro,false);
                    break; 
                case "__ProvEntr":
                    p = mimodelo.buscaProveedor(parametro,false);
                    break;
                case "__ClientEntr":
                    p=mimodelo.buscarCliente(parametro,false);
                    break;
                case "__TipodeSalidaH":
                    p=mimodelo.buscaTipoSalida(parametro, false);
                    break;
                case  "__ClienteSalidaH":
                    p=mimodelo.buscarCliente(parametro,false);
                    break;
                case "__PropietarioSalidaH":
                    p = mimodelo.buscarPropiedad(parametro,false);
                    break;
                case "__MaquinaSalidaH":
                    p=mimodelo.buscarMaquinas(parametro, false);
                    break;
                case "__MaquinaSalidaB":
                    p=mimodelo.buscarMaquinas(parametro, false);
                    break; 
                case  "__ClienteSalidaB":
                    p=mimodelo.buscarCliente(parametro,false);
                    break;
                case "__PropietarioSalidaB":
                    p = mimodelo.buscarPropiedad(parametro,false);
                    break;
                case "__TipodeSalidaB":
                    p=mimodelo.buscaTipoSalida(parametro, false);
                    break;
                case "__TipoSalida":
                    p = mimodelo.buscaTipoSalida(parametro,false);
                    break;
                case "__PropietarioSalida":
                    p = mimodelo.buscarPropiedad(parametro,false);
                    break; 
                case "__ProvSalida":
                    p = mimodelo.buscaProveedor(parametro,false);
                    break;
                case "__ClientSalida":
                    p=mimodelo.buscarCliente(parametro,false);
                    break;
                case "__Origen":
                    p=mimodelo.buscarPropiedad(parametro,false);
                    break;
                case "__Destino":
                    p=mimodelo.buscarPropiedad(parametro,false);
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
    public String busquedaNombre(String tabla, int id){
        ResultSet p=null;
        switch(tabla){
            case "tipo_entrada":
                p= mimodelo.buscaTipoEntrada(id);
                break;
            case "id_propietario":
                p=mimodelo.buscarPropiedad(id);
                break;
            case "id_proveedores":
                p=mimodelo.buscaProveedor(id);
                break;
            case "cliente":
                p=mimodelo.buscarCliente(id);
                break;
            case "tipo_salida":
                p= mimodelo.buscaTipoSalida(id);
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
    public void regresar(){
        switch(cargo){
                        case 1:
                            confir = mensajeConfirmacion("¿Realmente Deseas Regresar al \n Menú Principal?","Salida");
                                 if (confir == JOptionPane.OK_OPTION){
                                    menumaster.setVisible(true);
                                    menumaster.setLocationRelativeTo(null);
                                    consultas.dispose();
                                    movimientos.dispose();
                                    this.ap.dispose();
                                    reportes.dispose();
                                    reportes.dispose();
                                    borrarFormularioNewUser();
                                    borrarFormularioAltaPapel();
                                    borrarFormularioMovimientosPapel();
                                    borrarFormularioProveedor();
                                    borrarFormularioConsultas();
                                    borrarFormularioEmergente();
                                }
                            break;
                        case 2:
                            confir = mensajeConfirmacion("¿Realmente Deseas Regresar al \n Menú Principal?","Salida");
                                if (confir == JOptionPane.OK_OPTION){
                                menumaster.setVisible(true);
                                menumaster.setLocationRelativeTo(null);
                                consultas.dispose();
                                movimientos.dispose();
                                this.ap.dispose();
                                reportes.dispose();
                                borrarFormularioNewUser();
                                borrarFormularioAltaPapel();
                                borrarFormularioMovimientosPapel();
                                borrarFormularioProveedor();
                                borrarFormularioConsultas();
                                borrarFormularioEmergente();
                                }
                            break;
                        case 3:                                
                                confir = mensajeConfirmacion("¿Realmente Deseas Regresar al \n Menú Principal?","Salida");
                                if (confir == JOptionPane.OK_OPTION){
                                menumaster.setVisible(true);
                                menumaster.setLocationRelativeTo(null);
                                consultas.dispose();
                                movimientos.dispose();
                                this.ap.dispose();
                                reportes.dispose();
                                borrarFormularioNewUser();
                                borrarFormularioAltaPapel();
                                borrarFormularioMovimientosPapel();
                                borrarFormularioProveedor();
                                borrarFormularioConsultas();
                                borrarFormularioEmergente();
                                }
                            break;                    
                    }
                    
    }
    public Component prepareRenderer(JTable tabla,TableCellRenderer renderer,int row,int column){
         Component c=movimientos.__tablaSalidaBobinaInventario.prepareRenderer(renderer,row,column);
         //renderer.
//        if (paintEmptyRows) {
//          Component c=super.prepareRenderer(renderer,row,column);
//          if (!isCellSelected(row,column)) {
//            c.setBackground(colorForRow(row));
//            c.setForeground(getForeground());
//          }
//       else {
//            c.setBackground(UIManager.getColor("Table.selectionBackground"));
//            c.setForeground(getForeground());
//          }
//          return c;
//        }
//       else {
          return c;
//        }
}
    public void docentrada(){
        String doc = movimientos.__documentoEntr.getText();
                if(doc.isEmpty()){
                    return;
                }else{
                    if(doc.length()>3&&doc.contains("-")){
                        String[] partido = doc.split("-");
                        for(int i=0;i<partido.length;i++){
                            partido[i]=partido[i].replace(" ", "");
                        }
                        eval="";
                        for(int i=1;i<partido.length;i++){
                            eval+=partido[i]+"-";
                        }
                        try{
                            eval=eval.substring(0,eval.length()-1);
                        }catch(StringIndexOutOfBoundsException e){
                            mensaje(2,"Para Capturar el Documento de Entrada es Necesario Hacerlo de la Siguiente Manera: TIPO DE DOCUMENTO - ######");
                            movimientos.__documentoEntr.requestFocus();
                            return;
                        }
                        if(eval.length()==0){
                            mensaje(2,"Para Capturar el Documento de Entrada es Necesario Hacerlo de la Siguiente Manera: TIPO DE DOCUMENTO - ######");
                            movimientos.__documentoEntr.requestFocus();
                            return;
                        }
                        try{
                            ResultSet documento = mimodelo.buscaDocumentoEntrada(eval);
                            if(documento.next()){
                                mensaje(3,"Esta documento ya fue capturado");
                                movimientos.__documentoEntr.requestFocus();
                            }
                        }catch(Exception e){}
                        
                    }else{
                        mensaje(2,"para capturar el documento de entrada es necesario hacerlo de la siguiente manera: TIPO DE DOCUMENTO - ######");
                        movimientos.__documentoEntr.requestFocus();
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
                    texto = this.ap.__ColorPapel.getSelectedItem().toString();
                    id=mimodelo.buscaridColor(texto);
                    break;
                case "nombre":
                    texto = this.ap.__NombrePapel.getSelectedItem().toString();
                    id=mimodelo.buscaridNombre(texto);
                    break;
                case "propiedad":
                    texto = this.ap.__Propiedad.getSelectedItem().toString();
                    id=mimodelo.buscaridPropietario(texto);
                    break;
                case "marca":
                    texto = this.ap.__Marca.getSelectedItem().toString();
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
    public int obtenerpropietario(String clave){
        String[] split = clave.split("-");
        ResultSet id = mimodelo.buscaridPropietario(split[5]);
        try{
            while(id.next()){
                return id.getInt("id");
            }
        }catch(Exception a){
            return 0;
        }
        return 0;
    }
}
