package views;
import java.awt.Color;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.*;


public class VentanaJuego extends JFrame
{    
    public JPanel panelJuego;
    public Board tablero;
    

    public JButton define;
    public JButton profundidad;
    public JButton anchura;
    public JButton UFC;
    public JButton hillClimbing;
    public JButton beamSearch;
    public JButton aStar;
    public JButton reset;
    

    
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

        JLabel label = new JLabel("Recorridos");
        label.setBounds(994, 20, 190, 30);
        label.setForeground(Color.white);
        panelJuego.add(label);

        
        define=new JButton("Definir Nodos");
        define.setBounds(994, 56, 190, 30);
        define.setFocusable(false);
        panelJuego.add(define);

        profundidad=new JButton("Recorrido en Profundidad");
        profundidad.setBounds(994, 112, 190, 30);
        profundidad.setFocusable(false);
        panelJuego.add(profundidad);
        
        anchura=new JButton("Recorrido en Anchura");
        anchura.setBounds(994, 168, 190, 30);
        anchura.setFocusable(false);
        panelJuego.add(anchura);
        
        UFC=new JButton("Recorrido Costo Uniforme");
        UFC.setBounds(994, 224, 190, 30);
        UFC.setFocusable(false);
        panelJuego.add(UFC);

        hillClimbing=new JButton("Recorrido Hill Climbing");
        hillClimbing.setBounds(994, 280, 190, 30);
        hillClimbing.setFocusable(false);
        panelJuego.add(hillClimbing);
        
        beamSearch=new JButton("Recorrido de Beam Search");
        beamSearch.setBounds(994, 336, 190, 30);
        beamSearch.setFocusable(false);
        panelJuego.add(beamSearch);
        
        aStar=new JButton("Recorrido A *");
        aStar.setBounds(994, 392, 190, 30);
        aStar.setFocusable(false);
        panelJuego.add(aStar);
    
        reset=new JButton("Restablecer");
        reset.setBounds(994, 444, 190, 30);
        reset.setFocusable(false);
        panelJuego.add(reset);
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
        setIconImage(new ImageIcon(getClass().getResource("/images/Icono.jpg")).getImage());
        setLocationRelativeTo(null);
         
    }
 
    
    /**
     * @param path
     */
    public void paintPath(LinkedList<Node> path){
        this.tablero.actualizarTablero(panelJuego, path);
    }

    public void reset(){
        this.tablero.resetBoard(); 
    }

    /**
     *             int[] coorInit;
            int[] coorEnd;
            JFrame frame = new JFrame();
            JPanel panel = new JPanel(new GridLayout(3, 3));
            panel.add(new InputField());

            frame.getContentPane().add(panel);
            frame.pack();
            frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);
            frame.setVisible(true);
     */
}
