/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.pantallas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.LabelView;

/**
 *
 * @author ferna
 */
public class PantallaPrincipal extends JFrame {
 public JLabel lblFicherosDescargados;
 public JLabel lblInformativo;
 public JButton btnExit;
 public JButton btnConexion;
    public PantallaPrincipal() {
        setLayout(null);
        setTitle("fjc.hnss.sacyl.es");
        setLocationRelativeTo(null);
        setSize(600, 400);
        lblFicherosDescargados = new JLabel();
        lblFicherosDescargados.setText("TOTAL FICHEROS DESCARGADOS HASTA AHORA FICHERO:");
        lblFicherosDescargados.setBounds(150, 75, 500, 20);
        lblFicherosDescargados.setFont(new Font("Arial", Font.PLAIN, 18));
        
        btnConexion = new JButton();
        btnConexion.setText("CONEXION CERRADA");
        btnConexion.setForeground(Color.red);
        btnConexion.setBounds(150,20,250,25);
        
        lblInformativo=new JLabel();
        lblInformativo.setText("PANEL INFORMATIVO");
        lblInformativo.setBounds(150,100,500,80);
        lblInformativo.setFont(new Font("Serif", Font.PLAIN, 18));
        btnExit=new JButton();
        btnExit.setText("Iniciar");
        btnExit.setBounds(400,300, 100, 30);
        
        
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        this.add(lblFicherosDescargados);
        this.add(btnConexion);
        this.add(lblInformativo);
        this.add(btnExit);

        
    }

}
