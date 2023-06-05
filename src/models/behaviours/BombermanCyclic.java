package models.behaviours;

import java.util.LinkedList;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import models.agents.Bomberman;
import models.Node;

public class BombermanCyclic extends CyclicBehaviour{

    private Bomberman bomberman; 
    public BombermanCyclic(Agent agent){
        super(agent);
        bomberman = (Bomberman) agent;
    }

    @Override
    public void action() {       
         this.bomberman.path = calculateRoute();
         ACLMessage message = new ACLMessage(ACLMessage.INFORM);
         AID receptor = new AID("gosht",AID.ISLOCALNAME);
         message.addReceiver(receptor);
         message.setContent(this.bomberman.path.toString());
         this.bomberman.send(message);
    }

    public LinkedList<Node> calculateRoute(){
        LinkedList <Node>path = new LinkedList<Node>();
        path.add(this.bomberman.graph.AStar(this.bomberman.origin, this.bomberman.goal, false).get(1));
        return path;
    }
    
}
