 
package es.sacyl.hnss.utils;

import es.sacyl.hnss.sftp.SftpCtes;
import es.sacyl.hnss.testaliento.NewMain;
import java.util.Calendar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ComprobarDirectorios {

    final static Logger log= LogManager.getLogger(NewMain.class);
    public boolean chequeaDirectorios() {
 
        SftpCtes sftpCtes = new SftpCtes();
        Utilidades utils = new Utilidades();
        ControlFicheros controlFichero = new ControlFicheros();
        es.sacyl.hnss.testaliento.NewMain.ftpDirectorioLocalPendiente=(sftpCtes.getSftp_DIRECTORIO_LOCAL_PENDIENTE());
        es.sacyl.hnss.testaliento.NewMain.ftpDirectorioLocalPendiente = (sftpCtes.getSftp_DIRECTORIO_LOCAL_PENDIENTE());
        if (!utils.ControlVariables(es.sacyl.hnss.testaliento.NewMain.ftpDirectorioLocalPendiente)) {
            log.fatal("No existe la variable ftpDirectorioLocalPendiente");
            return false;
        }
        if (!controlFichero.ExisteDirectorio(es.sacyl.hnss.testaliento.NewMain.ftpDirectorioLocalPendiente)) {
            log.fatal("No existe el Directorio " + es.sacyl.hnss.testaliento.NewMain.ftpDirectorioLocalPendiente);
            return false;
        }
       es.sacyl.hnss.testaliento.NewMain.ftpDirectorioLocalProcesados = (sftpCtes.getSftp_DIRECTORIO_LOCAL_PROCESADOS());
        if (!utils.ControlVariables(es.sacyl.hnss.testaliento.NewMain.ftpDirectorioLocalProcesados)) {
            log.fatal( "No existe la variable ftpDirectorioLocalProcesados");
            return false;
        }
        if (!controlFichero.ExisteDirectorio(es.sacyl.hnss.testaliento.NewMain.ftpDirectorioLocalProcesados)) {
            log.fatal( "No existe el Directorio " + es.sacyl.hnss.testaliento.NewMain.ftpDirectorioLocalProcesados);
            return false;
        }
        if (!compruebaDirectoriosAAAAMM())
        {
            log.fatal("No he podido crear el directorio AAAAMM");
            return false;
        }
        es.sacyl.hnss.testaliento.NewMain.ftpDirectorioLocalErrores = (sftpCtes.getSftp_DIRECTORIO_LOCAL_ERRORES());
        if (!utils.ControlVariables(es.sacyl.hnss.testaliento.NewMain.ftpDirectorioLocalErrores)) {
            log.fatal( "No existe la variable ftpDirectorioLocalErrores");
            return false;
        }
        if (!controlFichero.ExisteDirectorio(es.sacyl.hnss.testaliento.NewMain.ftpDirectorioLocalErrores)) {
            log.fatal("No existe el Directorio " + es.sacyl.hnss.testaliento.NewMain.ftpDirectorioLocalErrores);
            return false;
        }
        return true;
    }

    public boolean compruebaDirectoriosAAAAMM() {
        Calendar fecha = Calendar.getInstance();
        int year = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH)+1;
        String xmes= ""+mes;
        if (xmes.length() < 2) {
            xmes = "0" + mes;
        }
        String directorio = ""+es.sacyl.hnss.testaliento.NewMain.ftpDirectorioLocalProcesados +"\\"+ year + xmes;
        es.sacyl.hnss.testaliento.NewMain.ftpDirectorioLocalProcesados = ""+es.sacyl.hnss.testaliento.NewMain.ftpDirectorioLocalProcesados +"\\"+ year + xmes;;
        ControlFicheros controlFicheros = new ControlFicheros();
        if (controlFicheros.ExisteDirectorio(directorio)) {
            return true;
        } else {
            if (controlFicheros.CrearDirectorios(directorio)) {
                return true;
            } else {
                return false;
            }
        }

    }
}
