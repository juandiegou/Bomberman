package models.agents;

import jade.core.Agent;
import java.util.LinkedList;

import controllers.EventController;
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
    public EventController controller;
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
        this.controller = (EventController) getArguments()[4];
        addBehaviour(new BombermanCyclic(this));
    }
}