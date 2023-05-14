package views;

import controllers.EventController;
import jade.core.Agent;
import models.Graph;
import views.behaviours.CyclicAGUI;

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
            addBehaviour(new CyclicAGUI(this));

        }
    }

    public void action() {

    }
}
