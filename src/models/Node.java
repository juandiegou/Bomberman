package models;
import java.util.*;

public class Node{

    public String data;
    private int [] position;
    public int positionX;
    public int positionY;
    private double priority;
    LinkedList<Node> childs;
    Node previous;
    public Node parent;


    public Node(int [] position, String data){
        this.position = position;
        this.positionX= position[0];
        this.positionY = position[1];
        this.data = data;
        this.previous= null;
        this.parent = null;
        this.childs = new LinkedList<Node> ();
    }


    public LinkedList<Node> getChilds(){
        return this.childs;
    }


    public void setPriority(double priority){
        this.priority = priority;
    }


    public double getPriority(){
        return this.priority;
    }


    public int[] getPosition(){
        return this.position;
    }


    public void setPrevious(Node current) {
        this.previous = current;
    }


    public Node getPrevious() {
        return this.previous;
    }


    public Node getParent(){
        return this.parent;
    }

    public void setParent(Node parent){
        this.parent= parent;
    }
    
}