/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.sftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.ProxySOCKS5;
import com.jcraft.jsch.Session;
import es.sacyl.hnss.pantallas.PantallaPrincipal;
import es.sacyl.hnss.testaliento.NewMain;
import es.sacyl.hnss.utils.ControlFicheros;
import es.sacyl.hnss.utils.Desencriptar;
import es.sacyl.hnss.utils.Utilidades;
import java.util.Map;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SftpCanal {

    Session session = null;
    Channel channel = null;
    SftpCtes sftpCtes = new SftpCtes();
    Utilidades utilidades = new Utilidades();
    ControlFicheros controlFichero = new ControlFicheros();

    final static Logger log = LogManager.getLogger(NewMain.class);

    public ChannelSftp getChannel(Map<String, String> sftpDetails, int timeout) throws JSchException {
        PantallaPrincipal pantalla = new PantallaPrincipal();
        String ftpHost = sftpDetails.get(sftpCtes.getSftp_HOST());
        if (!utilidades.ControlVariables(ftpHost)) {
            log.error("Error al configurar ftpHost. No exist la variable ftpHost");
            pantalla.btnConexion.setText("NO EXISTE FTPHOST");
            utilidades.salirPrograma("No existe la variable ftpHost");
        }

        String port = sftpDetails.get(sftpCtes.getSftp_PORT());
        if (!utilidades.ControlVariables(port)) {
            log.error("Error al configurar port");
            pantalla.btnConexion.setText("NO EXISTE FTPPORT");
            utilidades.salirPrograma("No existe la variable port");
        }
        String ftpUserName = sftpDetails.get(sftpCtes.getSftp_USERNAME());
        if (!utilidades.ControlVariables(ftpUserName)) {
            log.error("Error al configurar ftpUserName");
            pantalla.btnConexion.setText("NO EXISTE FTPUSER");
            utilidades.salirPrograma("No existe la variable ftpUserName");
        }
        String ftpPassword = sftpDetails.get(sftpCtes.getSftp_PASSWORD());
        if (!utilidades.ControlVariables(ftpPassword)) {
            log.error("Error al configurar ftpPassword");
            utilidades.salirPrograma("No existe la variable ftpPassword");
        }
        Desencriptar desencriptar = new Desencriptar();
        ftpUserName = desencriptar.descifraClave(ftpUserName);
        ftpPassword = desencriptar.descifraClave(ftpPassword);

        int ftpPort = Integer.parseInt(port);
        if (port != null && !port.equals("")) {
            ftpPort = Integer.valueOf(port);
        }

        JSch jsch = new JSch(); // Crear objeto JSch
        session = jsch.getSession(ftpUserName, ftpHost, ftpPort); // Obtenga un objeto Session de acuerdo con el nombre de usuario, la IP del host y el puerto
        System.out.println("Session created.");
        log.info("Session created");
        if (ftpPassword != null) {
            session.setPassword(ftpPassword); // establecer contraseña
        }
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        SftpProxy sftpProxy = new SftpProxy();
        ProxySOCKS5 proxy = sftpProxy.setProxy();
        /*----------
         ProxySOCKS5    proxy = new ProxySOCKS5( "proxy.sacyl.es",1080 );
            ( (ProxySOCKS5) proxy ).setUserPasswd( "06384936K","12345678" );
 
         --------------------*/
        session.setConfig(config); // Establecer propiedades para el objeto Session
        session.setTimeout(timeout); // establecer el tiempo de espera
        if (proxy == null) {
            //System.out.println("NO EXISTE PROXY");
            log.info("trabajando sin PROXY");
            pantalla.btnConexion.setText("trabajando sin PROXY");
        } else {
            log.info("trabajando con PROXY");
            pantalla.btnConexion.setText("trabajando con PROXY");
            session.setProxy(proxy);
            
        }

        session.connect(); // Establecer un enlace a través de Session
        pantalla.btnConexion.setText("CONEXION EXITOSA");
        log.info("Session connected");

        channel = session.openChannel("sftp"); // Abre el canal SFTP
        channel.connect(); // Establecer una conexión al canal SFTP

        log.info("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = " + ftpUserName + ", returning: " + channel);

        return (ChannelSftp) channel;
    }

    public void closeChannel() throws Exception {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }
}
