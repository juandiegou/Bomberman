package models.behaviours;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import com.google.gson.Gson;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import javafx.scene.shape.Path;
import models.Node;
import models.agents.Ghost;

public class GhostCyclic extends CyclicBehaviour {

    public Ghost ghost;
    private Gson gson;
    public Node goal;
    private Node start;
    LinkedList<Node> path;
    
    public GhostCyclic(Agent agent){
        super(agent);
        this.ghost = (Ghost) agent;
        this.gson = new Gson();
        int [] coords = this.getPosition();
        this.start = this.ghost.graph.nodeFromCoords(coords[0], coords[1], this.ghost.structure);
        path = new LinkedList<Node> ();
    }

    /**
     * @return
     */
    private int[] getPosition() {
        return new int[]{
            new Random().nextInt(this.ghost.graph.matrix.length-1),
            new Random().nextInt(this.ghost.graph.matrix[0].length-1)};
    }

    @Override
    public void action() {

        ACLMessage message;
        message = this.ghost.blockingReceive();
        try {
            goal = (Node) message.getContentObject();
        } catch (UnreadableException e) {
            e.printStackTrace();
        }
        ACLMessage reply = this.ghost.blockingReceive();
        reply.addReceiver(message.getSender());
        this.start=this.getMove().get(1);
        this.path.add(this.start);
        if(path!=null){
            this.path.forEach((Node node) -> {
                node.data = "G";
            });
            this.ghost.game.getBoard().actualizarTablero(this.ghost.game.panelJuego,path);
        }
        try {
            reply.setContentObject(this.start);

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.ghost.send(reply);

    }

    public LinkedList<Node> getMove(){
        return this.ghost.graph.AStar( start, goal, false);
    } 
}
