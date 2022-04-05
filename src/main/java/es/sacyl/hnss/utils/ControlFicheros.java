/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.utils;

import es.sacyl.hnss.testaliento.NewMain;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ControlFicheros {
Utilidades utils=new Utilidades();
  final static Logger log= LogManager.getLogger(NewMain.class);  
public boolean ExisteDirectorio(String directorio)
{
        File directorioFichero = new File(directorio);
        if (!directorioFichero.exists()) {
          return false;
        }
        if (!directorioFichero.isDirectory())
        {
            return false;
        }
        return true;
    }
public boolean ExisteFichero(String fichero)
{
        File directorioFichero = new File(fichero);
        if (!directorioFichero.exists()) {
          return false;
        }
        if (!directorioFichero.isFile())
        {
            return false;
        }
        return true;
    }

public boolean CrearDirectorios (String carpeta){
         File directorios = new File(carpeta);
        if (!directorios.exists()) {
            if (directorios.mkdirs()) {
                log.info("He creado el directorio:"+carpeta);
                return true;
            } else {
                log.fatal("No he podido crear el directorio:"+carpeta);
                utils.salirPrograma("Error. No he podido crear el directorio "+carpeta);
                return false;
                 
            }
        }
        return false;
    }
}
