package models.agents;

import jade.core.Agent;
import java.util.LinkedList;
import models.Graph;
import models.Node;

public class Bomberman extends Agent{

    public Node origin;
    public Node goal;
    public Graph graph;
    public String algorithm;
    public LinkedList<Node> path;

    public Bomberman(Node origin, Node goal,Graph graph,String algorithm){
        this.origin = origin;
        this.goal = goal;
        this.graph = graph;
        this.algorithm = algorithm;
    }

    protected void setup(){

    }
}