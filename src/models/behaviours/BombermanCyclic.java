package models.behaviours;

import java.util.LinkedList;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import com.google.gson.Gson;
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
        System.out.println("Bomberman");
        this.bomberman.path = calculateRoute();
        //this.bomberman.window.paintPath(this.bomberman.path);
        
        if (this.bomberman.path != null) {
            this.bomberman.path.forEach((Node node) -> {
                node.data = "P";
            });
            isDrawing=true;
            drawer = new Thread(new Runnable() {                    
                @Override
                public void run() {
                    while (isDrawing && !Thread.currentThread().isInterrupted()) {
                        if (bomberman.path != null && !bomberman.path.isEmpty()) {
                            bomberman.window.paintPath(bomberman.path);
                        }  {
                            bomberman.window.reset();
                        }
                    }
                }
            });
            try {
                drawer.join();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            drawer.start();
        }
        
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        AID receptor = new AID("ghost", AID.ISLOCALNAME);
        message.addReceiver(receptor);
        message.setContent(gson.toJson(this.bomberman.path, this.bomberman.path.getClass()));
        this.bomberman.send(message);
    }

    public LinkedList<Node> calculateRoute() {
        System.out.println("claculando...");
        LinkedList<Node> path = new LinkedList<Node>();
        switch (this.bomberman.controller.type) {
            case "profundidad":
                path.add(this.bomberman.graph
                        .profundidad(this.bomberman.origin, this.bomberman.goal, new LinkedList<Node>())
                        .get(1));
            case "anchura":
                path.add(this.bomberman.graph
                        .anchura(this.bomberman.origin, this.bomberman.goal).get(1));
            case "ufc":
                path.add(this.bomberman.graph
                        .ufc(this.bomberman.origin, this.bomberman.goal, new LinkedList<Node>())
                        .get(1));
            case "beamSearch":
                path.add(this.bomberman.graph
                        .beamsearch(this.bomberman.origin, this.bomberman.goal, false)
                        .get(1));
            case "hillClimbing":
                path.add(this.bomberman.graph
                        .hillClimbing(this.bomberman.origin, this.bomberman.goal, false)
                        .get(1));
            case "AStar":
                path.add(this.bomberman.graph
                        .AStar(this.bomberman.origin, this.bomberman.goal, false)
                        .get(1));
                System.out.println("*");
        }
        return path;
    }

}
