package models.agents;

import jade.core.Agent;
import java.util.LinkedList;

import models.Board;
import models.Graph;
import models.Node;
import models.behaviours.BombermanCyclic;
import views.VentanaJuego;

public class Bomberman extends Agent{

    public Node origin;
    public Node goal;
    public Graph graph;
    public VentanaJuego window;
    public LinkedList<Node> path;

    /**
     * 
     */
    public Bomberman(){}

    protected void setup(){
        this.origin = (Node) getArguments()[0];
        this.goal = (Node) getArguments()[1];
        this.graph = (Graph) getArguments()[2];
        this.window =(VentanaJuego) getArguments()[3];
        addBehaviour(new BombermanCyclic(this));
    }
}