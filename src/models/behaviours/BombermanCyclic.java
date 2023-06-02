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
        this.bomberman.board.getSquare(0, 0,"C").getImagenCelda();    
        
        /**
         * 

         this.bomberman.path = calculateRoute();
         ACLMessage message = new ACLMessage(ACLMessage.INFORM);
         AID receptor = new AID("gosht",AID.ISLOCALNAME);
         receptor.addAddresses("http://192.168.100.51:7778/acc");
         message.addReceiver(receptor);
         message.setContent("x");
         this.bomberman.send(message);
         */
    }

    public LinkedList<Node> calculateRoute(){
        
        return new LinkedList<Node>();
    }
    
}
