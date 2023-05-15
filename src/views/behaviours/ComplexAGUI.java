package views.behaviours;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import views.AGUI;

public class ComplexAGUI extends ParallelBehaviour{

    AGUI master;
    public ComplexAGUI(AGUI master){
        this.master = master;
    }

    public void addSubBehaviour(Behaviour behaviour){
        super.addSubBehaviour(behaviour);
    }
}
