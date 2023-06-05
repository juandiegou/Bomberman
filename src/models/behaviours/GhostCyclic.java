package models.behaviours;

import java.util.LinkedList;
import java.util.Random;
import com.google.gson.Gson;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import models.Node;
import models.agents.Ghost;

public class GhostCyclic extends CyclicBehaviour {

    public Ghost ghost;
    private Gson gson;
    public Node goal;
    private Node start;
    
    public GhostCyclic(Agent agent){
        super(agent);
        this.ghost = (Ghost) agent;
        this.gson = new Gson();
        this.start = new Node(getPosition(), "G");
    }

    /**
     * @return
     */
    private int[] getPosition() {
        return new int[]{new Random().nextInt(),new Random().nextInt()};
    }

    @Override
    public void action() {
        System.out.println("ghost");
        ACLMessage message;
        message = this.ghost.blockingReceive();
        goal = (Node) this.gson.fromJson(message.getContent(), goal.getClass());
        System.out.println(goal.data);
        ACLMessage reply = new ACLMessage(ACLMessage.INFORM);
        AID receiver = new AID("bomberman",AID.ISLOCALNAME);
        reply.addReceiver(receiver);
        this.ghost.game.getBoard().actualizarTablero(this.ghost.game.panelJuego,this.getMove());
        reply.setContent(gson.toJson(this.start,start.getClass()));
        this.ghost.send(reply);

    }

    public LinkedList<Node> getMove(){
        return this.ghost.graph.profundidad( start, goal, new LinkedList<Node>());
    } 
}
