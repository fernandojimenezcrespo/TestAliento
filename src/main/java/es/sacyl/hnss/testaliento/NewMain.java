package es.sacyl.hnss.testaliento;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import es.sacyl.hnss.pantallas.PantallaPrincipal;
import es.sacyl.hnss.sftp.SftpCanal;
import es.sacyl.hnss.sftp.SftpCtes;
import es.sacyl.hnss.utils.ComprobarDirectorios;
import es.sacyl.hnss.utils.ControlFicheros;
import es.sacyl.hnss.utils.Utilidades;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JTextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NewMain extends javax.swing.JPanel {

    public static String ftpDirectorioLocalPendiente = null;
    public static String ftpDirectorioLocalProcesados = null;
    public static String ftpDirectorioLocalErrores = null;
    private static ControlFicheros controlFichero = new ControlFicheros();
    private static ComprobarDirectorios comprobarDirectorios = new ComprobarDirectorios();
    private static Utilidades utilidades = new Utilidades();
    final static Logger log = LogManager.getLogger(NewMain.class);
    private static PantallaPrincipal pantalla = new PantallaPrincipal();

    public static void main(String[] args) {
        log.info("Empezando el programa.");
        //PantallaPrincipal pantalla = new PantallaPrincipal();

        pantalla.lblFicherosDescargados.setText("");
        pantalla.lblFicherosDescargados.setBackground(Color.red);

        pantalla.setVisible(true);
        if (chequeo()) {
            pantalla.lblInformativo.setText("Chequeo Ficheros OK");
            utilidades.relentiza(100);
            log.info("chequeo de los ficheros OK");
        } else {
            pantalla.lblInformativo.setText("KO el Chequeo de Ficheros");
            log.fatal("Ha habido un error en el Chequeo de los ficheros.");
        }

        pantalla.btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pinta();
            }

        });

    }

    private static boolean chequeo() {
        if (comprobarDirectorios.chequeaDirectorios()) {
            log.info("COMPROBACION DE DIRECTORIOS OK");
            return true;
        } else {
            log.fatal(" :-(  ALGUN DIRECTORIO NO EXISTE ");
            Utilidades utilidades = new Utilidades();
            utilidades.salirPrograma(":-(");
            return false;
        }
    }

    private static boolean conecta() {
        SftpCanal sftpCanal = new SftpCanal();
        ChannelSftp CanalTest = new ChannelSftp();
        HashMap<String, String> xMap = new HashMap();
        xMap = rellenaHas(xMap);

        try {

            CanalTest = sftpCanal.getChannel(xMap, 0);

            ListaFicherosMesActual(CanalTest);
            while (CanalTest != null) {
                log.info("Desconectando Session SFTP");
                CanalTest.disconnect();
                CanalTest=null;
                log.info("Fin del programa.");
                // System.exit(0);
            }
        } catch (JSchException ex) {
            log.fatal("Error fatal " + ex);
            return false;

        }
        return true;
    }

    private static HashMap<String, String> rellenaHas(HashMap<String, String> xMap) {
        SftpCtes sftpCtes = new SftpCtes();
        xMap.put(sftpCtes.getSftp_HOST(), sftpCtes.getSftp_HOST());
        xMap.put(sftpCtes.getSftp_PORT(), sftpCtes.getSftp_PORT());
        xMap.put(sftpCtes.getSftp_USERNAME(), sftpCtes.getSftp_USERNAME());
        xMap.put(sftpCtes.getSftp_PASSWORD(), sftpCtes.getSftp_PASSWORD());
        xMap.put(sftpCtes.getSftp_LOC(), sftpCtes.getSftp_LOC());
        return xMap;

    }

    private static void ListaFicherosMesActual(ChannelSftp CanalTest) {

        Calendar hoy = Calendar.getInstance();
        int year = hoy.get(Calendar.YEAR);
        int month = hoy.get(Calendar.MONTH) + 1;
        String mes = "" + month;
        JTextField jtext = new JTextField();
        jtext.setBounds(100, 30, 100, 20);

        if (mes.length() < 2) {
            mes = "0" + mes;
        }
        try {
            jtext.setText(mes + "/" + year);
            CanalTest.cd("" + year + "/" + mes);
            CanalTest.lcd(ftpDirectorioLocalPendiente);
            String CarpetaActualRemota = CanalTest.pwd();

            String CarpetaActualLocal = CanalTest.lpwd();
            Vector filelist = CanalTest.ls(CarpetaActualRemota);
            int totalFicherosDescargados = 0;
            for (int i = 0; i < filelist.size(); i++) {
                ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) filelist.get(i);
                String fichero = entry.getFilename();
                pantalla.lblFicherosDescargados.setText(fichero);
                utilidades.relentiza(300);
                pantalla.setVisible(true);
                String esDirectorio = entry.getLongname().substring(0, 1);
                OutputStream output = null;
                if (!esDirectorio.equals("d") && !esDirectorio.equals(".")
                        && !controlFichero.ExisteFichero(ftpDirectorioLocalProcesados + "\\" + fichero)
                        && !controlFichero.ExisteFichero(ftpDirectorioLocalPendiente + "\\" + fichero)) {
                    try {
                        totalFicherosDescargados++;
                        output = new FileOutputStream(CarpetaActualLocal + "\\" + fichero);
                        log.info("DESCARGANDO EL FICHERO " + fichero);
                    } catch (FileNotFoundException ex) {
                        log.fatal("Error ->" + ex);
                    }
                    CanalTest.get(fichero, output);
                } else {
                    log.info("El " + fichero + " ya lo he descargado ");
                }
            }
            pantalla.lblFicherosDescargados.setText("TOTAL FICHEROS DESCARGADOS:" + totalFicherosDescargados);
        } catch (SftpException ex) {
            log.info("Error " + ex);
        }

    }

    private static ActionListener pinta() {
        if (pantalla.btnExit.getText() == "Iniciar") {
            pantalla.btnExit.setText("Exit");
        } else {
            pantalla.btnExit.setText("ADIOS");
            log.info("Saliendo del programa");
            System.exit(0);
        }
        if (conecta()) {
            //if (1 == 2) {
            pantalla.lblInformativo.setText("Conecta Ok");
        } else {
            pantalla.lblFicherosDescargados.setText("NINGUN FICHERO DESCARGADO");
            pantalla.lblInformativo.setText("Fallo en la conexion");
        }
        
        for (int i = 40; i < 20; i++) {
            pantalla.lblInformativo.setText("Boton Informativo " + i);
            pantalla.lblFicherosDescargados.setText((i - 1) + "Boton ficheros");
            utilidades.relentiza(200);
        }
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;

    }
}
