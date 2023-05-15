package views.behaviours;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import views.AGUI;

public class SenderAGUI extends CyclicBehaviour{

    AGUI master;
    public SenderAGUI(AGUI master){
        this.master = master;
    }


    @Override
    public void action() {
       // Enviar mensaje
       ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
       msg.setContent("Este es un mensaje enviado desde mi agente");
       msg.addReceiver(new AID("agenteDestino", AID.ISLOCALNAME));
       System.out.println("Mensaje");
    }

}