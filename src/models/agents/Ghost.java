package models.agents;

import models.Board;
import models.Graph;
import models.Node;
import models.behaviours.GhostCyclic;
import views.VentanaJuego;
import jade.core.Agent;
import java.util.HashMap;

public class Ghost extends Agent{

    public int [] position;
    public HashMap<int[], Node> structure;
    public Graph graph;
    public VentanaJuego game;
    public int[] getPosition() {
        return position;
    }

    public Ghost (){}

    public void setPosition(int[] position) {
        this.position = position;
    }

    protected void setup(){
        this.graph = (Graph) getArguments()[0];
        this.structure= this.graph.structure;
        this.game = (VentanaJuego) getArguments()[1];
        addBehaviour(new GhostCyclic(this));
        
    }

    


}