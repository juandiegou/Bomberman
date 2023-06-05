package views.behaviours;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import views.AGUI;

public class MainAGUI extends SimpleBehaviour{

    private AGUI theAgent;
    public MainAGUI(Agent agent){
        super(agent);
        this.theAgent = (AGUI) agent;
    }

    @Override
    public void action() {
        //System.out.println("Hola");
        theAgent.getBomberman();
        theAgent.getGosht();

    }

    @Override
    public boolean done() {
        System.out.println("terminé");
        return true;
    }


}
