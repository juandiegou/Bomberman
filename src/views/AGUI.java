package views;

import controllers.EventController;
import jade.core.Agent;
import jade.domain.introspection.ACLMessage;
import models.Graph;
import views.behaviours.ComplexAGUI;
import views.behaviours.SenderAGUI;
import views.behaviours.ReceiverAGUI;

public class AGUI extends Agent {

    public String[][] matrix;
    public Graph graph;
    public VentanaJuego window;
    public EventController controller;

    public AGUI() {}

    protected void setup() {
        this.graph = (Graph) getArguments()[0];
        if (graph != null) {
            this.matrix = graph.matrix;
            window = new VentanaJuego(this.matrix);
            controller = new EventController(window, graph);
            ComplexAGUI behaviour = new ComplexAGUI(this);
            behaviour.addSubBehaviour(new SenderAGUI(this));
            behaviour.addSubBehaviour(new ReceiverAGUI(this));
        }
    }

    
}
