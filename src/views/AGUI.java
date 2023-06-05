package views;

import controllers.EventController;
import jade.core.Agent;
import models.Graph;
import views.behaviours.MainAGUI;
import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class AGUI extends Agent {

    public String[][] matrix;
    public Graph graph;
    public VentanaJuego window;
    public EventController controller;
    private boolean bomberman = false;
    private boolean ghost = false;

    public AGUI() {}

    protected void setup() {
        this.graph = (Graph) getArguments()[0];              
        Runtime runtime = Runtime.instance();
        Profile profile = new ProfileImpl("localhost",1099,"MAIN",true); 
        ContainerController container = (ContainerController) runtime.createMainContainer(profile);
        if (graph != null) {
            this.matrix = graph.matrix;
            window = new VentanaJuego(this.matrix);
            controller = new EventController(window, graph);
            addBehaviour(new MainAGUI(this));
            if(controller.start != null & controller.goal != null){
                try {
                    container.createNewAgent("bomberman", "models.agents.Bomberman",
                    new Object[]{controller.start, controller.goal, this.graph, this.window,this.controller});
                    container.getAgent("bomberman").start();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if(ghost == false){
                try {
                    ((ContainerController) container).createNewAgent("ghost", "models.agents.Ghost", 
                    new Object[]{this.graph, this.window } );
                    container.getAgent("ghost").start();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public boolean getGosht(){
        return this.ghost;
    }

    public boolean getBomberman(){
        return this.bomberman;
    }

    public void setGosht(boolean flag){
        this.ghost = flag;
    }

    public void setBomberman(boolean flag){
        this.bomberman = flag;
    }
}
