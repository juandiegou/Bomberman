package controllers;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.util.LinkedList;
import models.*;
import views.InputField;
import views.VentanaJuego;

public class EventController implements ActionListener, KeyListener {
    private VentanaJuego ventana;
    public JFrame frame;
    private Graph graph;
    public Node start;
    public Node goal;
    private LinkedList<Node> path;
    private Thread drawer;
    private GestorPartidas gestorPartidas;

    /**
     * Constructor de objetos ControladorEventos
     * 
     * @param ventana de Jewel Run
     */

    public EventController(VentanaJuego ventana, Graph graph) {
        this.ventana = ventana;
        this.graph = graph;
        this.gestorPartidas = new GestorPartidas();
        escucharAcciones(this);
        escucharTeclado(this);

    }

    /**
     * Añade a los botones de la ventana la capacidad de saber cuando son
     * presionados
     * 
     * @param listener de acciones
     */

    private void escucharAcciones(ActionListener listener) {

        ventana.define.addActionListener(listener);
        ventana.anchura.addActionListener(listener);
        ventana.profundidad.addActionListener(listener);
        ventana.UFC.addActionListener(listener);
        ventana.beamSearch.addActionListener(listener);
        ventana.hillClimbing.addActionListener(listener);
        ventana.aStar.addActionListener(listener);
        ventana.reset.addActionListener(listener);
        path = new LinkedList<Node>();

    }

    /**
     * Añade a la ventana la capacidad de saber cuando son presionadas las teclas
     * del teclado
     * 
     * @param listener de teclas
     */

    private void escucharTeclado(KeyListener listener) {

        ventana.addKeyListener(listener);

    }

    /**
     * Define las acciones a realizar de cada boton de la ventana
     * 
     * @param e es el evento de accion sobre un boton
     */

    @Override
    public void actionPerformed(ActionEvent e) {

        if (start != null & goal != null) {
            if (e.getSource().equals(ventana.profundidad)) {
                path = graph.profundidad(start, goal, new LinkedList<Node>());
            }

            if (e.getSource().equals(ventana.anchura)) {
                path = graph.anchura(start, goal);
            }

            if (e.getSource().equals(ventana.UFC)) {
                path = graph.ufc(start, goal, new LinkedList<Node>());
            }

            if (e.getSource().equals(ventana.hillClimbing)) {
                path = graph.hillClimbing(start, goal);
            }

            if (e.getSource().equals(ventana.beamSearch)) {
                path = graph.beamsearch(start, goal, true);
            }

            if (e.getSource().equals(ventana.aStar)) {
                path = graph.AStar(start, goal, true);
            }

            if (path != null) {
                path.forEach((Node node) -> {
                    node.data = "P";
                });
                drawer = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (!Thread.currentThread().isInterrupted()) {
                            if (path != null) {
                                ventana.paintPath(path);
                            } else {
                                ventana.reset();
                            }

                        }

                    }
                });
                try {
                    drawer.join();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                drawer.start();
            }

        }

        if (e.getSource().equals(ventana.reset)) {

            if (!drawer.isInterrupted()) {
                drawer.interrupt();
                path = null;
                ventana.reset();
            }

        }

        if (e.getSource().equals(ventana.define)) {
            try {
                getNode();
                
            } catch (Exception w) {
                w.getStackTrace();
            }
        }
    }

    /**
     * Define las teclas que hacen mover a los jugadores y que son escuchadas por la
     * ventana
     * 
     * @param e es el evento de accion sobre una tecla
     */

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void setGoal(Node goal) {
        this.goal = goal;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public void getNode() {
        frame = new JFrame();
        JPanel panel = new JPanel(new GridLayout(5, 3));
        panel.add(new InputField(this.graph,this));
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setVisible(true);        
    }
}
