package models;
import java.io.Serializable;
import java.util.LinkedList;

public class Node implements Serializable{

    public String data;
    private int [] position;
    public int positionX;
    public int positionY;
    private Float priority;
    public LinkedList<Node> childs;
    public Node previous;
    public Node parent;
    public boolean obstacule = false;

    public Node(int [] position, String data){
        this.position = position;
        this.positionX= position[0];
        this.positionY = position[1];
        this.data = data;
        this.previous= null;
        this.parent = null;
        this.childs = new LinkedList<Node> ();
    }

    public Node(String data,int [] position,int positionX,int positionY,
    Float priority,LinkedList<Node> childs,Node previous,Node parent){
        this.data= data;
        this.position= position;
        this.positionX= positionX;
        this.positionY = positionY;
        this.priority = priority;
        this.childs = childs;
        this.previous = previous;
        this.parent= parent;
    }


    public LinkedList<Node> getChilds(){
        return this.childs;
    }


    public void setPriority(Float priority){
        this.priority = priority;
    }

    public void setPriority(int  priority){
        this.priority = (float) priority;
    }

    public Float getPriority(){
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
    

    public void setObtacule(boolean o){
        this.obstacule= o;
    }

}