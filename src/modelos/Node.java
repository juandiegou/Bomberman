import java.util.*;

public class Node{

    char data;
    private int [] position;
    int positionX;
    int positionY;
    private double priority;
    LinkedList<Node> childs;

    public Node(int [] position, char data){
        this.position = position;
        this.positionX= position[0];
        this.positionY = position[1];
        this.data = data;
        this.childs = new LinkedList<Node> ();
    }


    public LinkedList<Node> getChilds(){
        return this.childs;
    }


    public void setPriority(double priority ){
        this.priority = priority;
    }



    public void setPrioity(double priority){
        this.priority = priority;
    }


    public double getPriority(){
        return this.priority;
    }


    public int[] getPosition(){
        return this.position;
    }

    
}