package models.behaviours;

import java.io.IOException;
import java.util.LinkedList;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.agents.Bomberman;
import models.Node;

public class BombermanCyclic extends CyclicBehaviour {

    private Bomberman bomberman;
    private Thread drawer;
    private boolean isDrawing=true;
    private Gson gson;

    public BombermanCyclic(Agent agent) {
        super(agent);
        bomberman = (Bomberman) agent;
        gson = new Gson();
    }

    @Override
    public void action() {
        if(!this.bomberman.controller.type.equals("")){

            this.bomberman.path = calculateRoute();
        }        
        if (this.bomberman.path != null) {
            this.bomberman.path.forEach((Node node) -> {
                node.data = "P";
            });
            bomberman.window.paintPath(bomberman.path);
        }
        
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        AID receptor = new AID("ghost", AID.ISLOCALNAME);
        message.addReceiver(receptor);
        try {
            if(!this.bomberman.path.equals(null) && this.bomberman.path.size()>1){
                Node temp = this.bomberman.path.get(1);
                message.setContentObject(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.bomberman.send(message);

        ACLMessage reply = this.bomberman.blockingReceive();
        try {
            Node temp = (Node) reply.getContentObject();
            temp.setObtacule(true);
            reply.setSender(reply.getSender());
        } catch (UnreadableException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Node> calculateRoute() {
        LinkedList<Node> path = new LinkedList<Node>();
        switch (this.bomberman.controller.type) {
            case "profundidad":
                path.addAll(this.bomberman.graph
                        .profundidad(this.bomberman.origin, this.bomberman.goal, new LinkedList<Node>()));
            case "anchura":
                path.addAll(this.bomberman.graph
                        .anchura(this.bomberman.origin, this.bomberman.goal));
            case "ufc":
                path.addAll(this.bomberman.graph
                        .ufc(this.bomberman.origin, this.bomberman.goal, new LinkedList<Node>()));
            case "beamSearch":
                path.addAll(this.bomberman.graph
                        .beamsearch(this.bomberman.origin, this.bomberman.goal, false));
            case "hillClimbing":
                path.addAll(this.bomberman.graph
                        .hillClimbing(this.bomberman.origin, this.bomberman.goal, false));
            case "AStar":
                path.addAll(this.bomberman.graph
                        .AStar(this.bomberman.origin, this.bomberman.goal, false));
        }
        return path;
    }

}
