package views;

import controllers.EventController;
import jade.core.Agent;
import models.Graph;
import views.behaviours.MainAGUI;
import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;

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
        if (graph != null) {
            this.matrix = graph.matrix;
            window = new VentanaJuego(this.matrix);
            controller = new EventController(window, graph);
            addBehaviour(new MainAGUI(this));
            if(controller.start != null & controller.goal != null){
                try {
                    
                    Runtime runtime = Runtime.instance();
                    Profile profile = new ProfileImpl("localhost",1099,"MAIN",true);  
                    AgentController agentBomberman = runtime.createMainContainer(profile)
                    .createNewAgent("bomberman", "models.agents.Bomberman",
                    new Object[]{controller.start, controller.goal, this.graph, this.window});
                    agentBomberman.start();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if(ghost == false){
                try {
                    Runtime runtime = Runtime.instance();
                    Profile profile = new ProfileImpl("localhost",1099,"MAIN",true);  
                    AgentController agentGhost = runtime.createMainContainer(profile)
                    .createNewAgent("gosht", "models.agents.Ghost", 
                    new Object[]{this.graph, this.window.tablero } );
                    agentGhost.start();
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
