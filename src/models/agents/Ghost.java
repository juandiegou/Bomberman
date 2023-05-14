package models.agents;

import models.Node;
import jade.core.Agent;
import java.util.HashMap;

public class Ghost extends Agent{

    public int [] position;
    public HashMap<int[], Node> structure;

    public int[] getPosition() {
        return position;
    }

    public Ghost (HashMap<int[],Node> structure){
        this.structure = structure;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    protected void setup(){
        //addBehaviour(new ComplexGhost(this));
    }

    


}