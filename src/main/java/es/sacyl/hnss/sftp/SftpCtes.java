
package es.sacyl.hnss.sftp;

import es.sacyl.hnss.utils.LeerXML;

public class SftpCtes {

    LeerXML leerXML = new LeerXML();
    /**
     * **
     * public static final String SFTP_REQ_HOST =
     * leerXML.devuelveValor("SFTP_REQ_HOST") ;//"85.62.11.130"; public static
     * final String SFTP_REQ_PORT = leerXML.devuelveValor("SFTP_REQ_HOST")
     * ;//"22"; public static final String SFTP_REQ_USERNAME =
     * leerXML.devuelveValor("SFTP_REQ_HOST") ;//"sondige"; public static final
     * String SFTP_REQ_PASSWORD = leerXML.devuelveValor("SFTP_REQ_HOST")
     * ;//"SonDigE%5*374"; public static final int SFTP_DEFAULT_PORT =
     * leerXML.devuelveValor("SFTP_REQ_HOST") ;//22; public static final String
     * SFTP_REQ_LOC = leerXML.devuelveValor("SFTP_REQ_HOST") ;//"location";*****
     */

    private String sftp_HOST = null;            //leerXML.devuelveValor("sftp_HOST") ;//"85.62.11.130";
    private String sftp_PORT = null;            //leerXML.devuelveValor("sftp_PORT") ;//"22";
    private String sftp_USERNAME = null;        //leerXML.devuelveValor("sftp_USERNAME") ;//"sondige";
    private String sftp_PASSWORD = null;        //leerXML.devuelveValor("sftp_PASSWORD") ;//"SonDigE%5*374";
    private String sftp_LOC = null;             //"location"
    private String sftp_DIRECTORIO_LOCAL_PENDIENTE  =   null;
    private String sftp_DIRECTORIO_LOCAL_PROCESADOS =   null;
    private String sftp_DIRECTORIO_LOCAL_ERRORES    =   null;

    public String getSftp_DIRECTORIO_LOCAL_PENDIENTE() {
        return leerXML.devuelveValor("sftp_DIRECTORIO_LOCAL_PENDIENTE");
    }

    public String getSftp_DIRECTORIO_LOCAL_PROCESADOS() {
        return leerXML.devuelveValor("sftp_DIRECTORIO_LOCAL_PROCESADOS");
    }

    public String getSftp_DIRECTORIO_LOCAL_ERRORES() {
        return leerXML.devuelveValor("sftp_DIRECTORIO_LOCAL_ERRORES");
    }
    public String getSftp_HOST() {
        return leerXML.devuelveValor("sftp_HOST");
    }

    public String getSftp_PORT() {
        return leerXML.devuelveValor("sftp_PORT");
    }

    public String getSftp_USERNAME() {
        return leerXML.devuelveValor("sftp_USERNAME");
    }

    public String getSftp_PASSWORD() {
        return leerXML.devuelveValor("sftp_PASSWORD");
    }

    public String getSftp_LOC() {
        return leerXML.devuelveValor("sftp_LOC");
    }

}
