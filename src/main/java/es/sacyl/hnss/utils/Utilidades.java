 
package es.sacyl.hnss.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Utilidades {
     
    public void relentiza(int milisegundos)
    {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salirPrograma(String error)
    {
      
      System.exit(0);
    }
        public boolean ControlVariables(String variable) {
        if (variable.isEmpty())
            return false;
        else if (variable==null)
            return false;
        else
            return true;
    }
}
