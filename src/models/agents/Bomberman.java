package models.agents;

import jade.core.Agent;
import java.util.LinkedList;

import models.Board;
import models.Graph;
import models.Node;
import models.behaviours.BombermanCyclic;

public class Bomberman extends Agent{

    public Node origin;
    public Node goal;
    public Graph graph;
    public Board board;
    public LinkedList<Node> path;

    /**
     * 
     */
    public Bomberman(){}

    protected void setup(){
        this.origin = (Node) getArguments()[0];
        this.goal = (Node) getArguments()[1];
        this.graph = (Graph) getArguments()[2];
        this.board =(Board) getArguments()[3];
        addBehaviour(new BombermanCyclic(this));
    }
}