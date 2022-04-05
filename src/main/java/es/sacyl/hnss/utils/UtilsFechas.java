 
package es.sacyl.hnss.utils;

 
public class UtilsFechas {
    
 public String nombreMes(int mes){
         String nombreMmes="";
         switch (mes){
             
             case 1:nombreMmes="ENERO";break;
             case 2:nombreMmes="FEBRERO";break;
             case 3:nombreMmes="MARZO";break;
             case 4:nombreMmes="ABRIL";break;
             case 5:nombreMmes="MAYO";break;
             case 6:nombreMmes="JUNIO";break;
             case 7:nombreMmes="JULIO";break;
             case 8:nombreMmes="AGOSTO";break;
             case 9:nombreMmes="SEPTIEMBRE";break;
             case 10:nombreMmes="OCTUBRE";break;
             case 11:nombreMmes="NOVIEMBRE";break;
             case 12:nombreMmes="DICIEMBRE";break;
         }
         return nombreMmes;
 }
}
