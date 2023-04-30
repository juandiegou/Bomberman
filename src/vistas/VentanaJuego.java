package vistas;
import java.awt.Color;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modelos.*;


public class VentanaJuego extends JFrame
{    
    public JPanel panelJuego;
    public Board tablero;
    

    
    public JButton guardarPartida;
    public JButton cargarPartida;
    public JButton reiniciarPartida;
    public JButton borrarPartida;
    public JButton creditos;
    public JButton salirJuego;
    

    
    /** Constructor de objetos VentanaJuego
      *@param nombreJugador jugador el nombre de quien va a jugar
      * @param nombreOponente el nombre del otro jugdor 
      * 
      */
    
    public VentanaJuego(String [][] matrix){
          
        inicializarPanelJuego();        
        inicializarBotones();
        inicializarTableroJuego(matrix);  
        inicializarVentana();        
        
    }
    
    /** Inicializa los componentes de PanelJuego
      */
    
    public void inicializarPanelJuego(){
        
        panelJuego=(JPanel)this.getContentPane();
        panelJuego.setBackground(Color.black);
        panelJuego.setLayout(null);
    
    }
    
    /** Inicializa los botones de la ventana y los agrega a PanelJuego
      */
    
    public void inicializarBotones(){
    
        guardarPartida=new JButton("Guardar Partida");
        guardarPartida.setBounds(864, 112, 150, 30);
        guardarPartida.setFocusable(false);
        panelJuego.add(guardarPartida);
        
        cargarPartida=new JButton("Cargar Partida");
        cargarPartida.setBounds(864, 168, 150, 30);
        cargarPartida.setFocusable(false);
        panelJuego.add(cargarPartida);
        
        reiniciarPartida=new JButton("Reiniciar Partida");
        reiniciarPartida.setBounds(864, 224, 150, 30);
        reiniciarPartida.setFocusable(false);
        panelJuego.add(reiniciarPartida);
        
        borrarPartida=new JButton("Borrar Partida");
        borrarPartida.setBounds(864, 280, 150, 30);
        borrarPartida.setFocusable(false);
        panelJuego.add(borrarPartida);
        
        creditos=new JButton("Créditos");
        creditos.setBounds(864, 336, 150, 30);
        creditos.setFocusable(false);
        panelJuego.add(creditos);
        
        salirJuego=new JButton("Salir");
        salirJuego.setBounds(864, 392, 150, 30);
        salirJuego.setFocusable(false);
        panelJuego.add(salirJuego);
    
    }
    
    /** Inicializa el tablero asociandolo a PanelJuego
     * @param nombreJugador de jugador 
     * @param nombreOponente de oponente
      */
    
    public void inicializarTableroJuego(String [][] matrix){
    
        tablero=new Board( matrix,panelJuego);        
        
    }
    
    
    /** Inicializa y configura los componentes de la ventana de Jewel Run
      */
    
    public void inicializarVentana(){
    
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bomberman");
        setSize(1200, 800);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/Icono.jpg")).getImage());
        setLocationRelativeTo(null);
         
    }
 
    
    /**
     * @param path
     */
    public void paintPath(LinkedList<Node> path){
        this.tablero.actualizarTablero(panelJuego, path);
    }
}
