package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import modelos.GestorPartidas;
import modelos.Board;
import vistas.VentanaJuego;


public class EventController implements ActionListener, KeyListener
{
    private VentanaJuego ventana;
    
    private GestorPartidas gestorPartidas;
    
    /** Constructor de objetos ControladorEventos
      * @param ventana de Jewel Run
      */
    
    public EventController(VentanaJuego ventana){
    
        this.ventana=ventana;
        this.gestorPartidas=new GestorPartidas();        
        escucharAcciones(this);
        escucharTeclado(this);
    
    }
    
    /** Añade a los botones de la ventana la capacidad de saber cuando son presionados
      * @param listener de acciones
      */
    
    private void escucharAcciones(ActionListener listener){
    
        ventana.guardarPartida.addActionListener(listener);
        ventana.cargarPartida.addActionListener(listener);
        ventana.reiniciarPartida.addActionListener(listener);
        ventana.borrarPartida.addActionListener(listener);
        ventana.creditos.addActionListener(listener);
        ventana.salirJuego.addActionListener(listener);
        
    }
    
    /** Añade a la ventana la capacidad de saber cuando son presionadas las teclas del teclado
      * @param listener de teclas
      */
    
    private void escucharTeclado(KeyListener listener){
    
        ventana.addKeyListener(listener);
        
    }
    
    /** Define las acciones a realizar de cada boton de la ventana
      * @param e es el evento de accion sobre un boton
      */

    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource().equals(ventana.guardarPartida)){
                
            gestorPartidas.guardarPartida(ventana.tablero);
           
        }
        
        if(e.getSource().equals(ventana.cargarPartida)){
            
            Board tableroCargado=gestorPartidas.cargarPartida();
            
            if(tableroCargado!=null){
            
                ventana.panelJuego.removeAll();
                ventana.inicializarBotones();
                escucharAcciones(this);
                ventana.repaint();
                
                JOptionPane.showMessageDialog(null, "Partida cargada con éxito", "Cargar partida", JOptionPane.INFORMATION_MESSAGE);
            
            }
            
        }
        
        if(e.getSource().equals(ventana.reiniciarPartida)){
            
            ventana.panelJuego.removeAll();
            //ventana.paintPath();
            ventana.inicializarBotones();
            escucharAcciones(this);
            ventana.repaint();
            
        }
        

    }

    /** Define las teclas que hacen mover a los jugadores y que son escuchadas por la ventana
      * @param e es el evento de accion sobre una tecla
      */
    
    @Override
    public void keyPressed(KeyEvent e){}

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyReleased(KeyEvent e){}
    
}
