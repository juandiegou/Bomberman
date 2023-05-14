package views.behaviours;

import jade.core.behaviours.CyclicBehaviour;
import views.AGUI;

public class CyclicAGUI extends CyclicBehaviour{

    AGUI master;
    public CyclicAGUI(AGUI master){
        this.master = master;
    }


    @Override
    public void action() {
        if(master.controller.start!=null){
            
        }
    }


    protected void validarCoordenadas() {
    }

}