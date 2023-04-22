import java.util.Queue;
import java.util.HashMap;
import java.util.LinkedList;

class Graph {

    private Node start;
    private Node goal;
    private HashMap<int[], Node> structure;
    private Queue<Node> queue;

    public Graph(Node start, Node goal) {
        this.start = start;
        this.goal = goal;
    }

    public Graph(HashMap<int[], Node> structure ) {
        
        this.structure = structure;
    }
    
    public Graph(){
        
    }

    public HashMap<int[], Node> strucutreGraph() {

        return null;
    }

    public LinkedList<Node> anchura(Node start, Node goal) {

        Node temp;
        LinkedList<Node> path = new LinkedList<Node>();
        queue = new LinkedList<Node>();
        queue.add(start);

        while (!queue.isEmpty()) {
            temp = queue.remove();
            if (path.contains(goal)) {
                queue.clear();
                return path;
            }
            for (Node nodes : temp.getChilds()) {
                queue.add(nodes);
            }

            if (!path.contains(temp)) {
                path.add(temp);
            }
        }
        return path;
    }

    /**
     * @param start
     * @param goal
     * @param path
     * @return
     */
    public LinkedList<Node> profundidad(Node start, Node goal, LinkedList<Node> path) {

        LinkedList <Node> templist = new LinkedList<Node>();
            if (start.equals(goal)) {
                path.add(start);
                return path;
            }

            if (!path.contains(start)) {
                path.add(start);
                for (Node node : start.getChilds()) {

                    templist= profundidad(node, goal, path);
                    templist.forEach((nodes)->{
                        if(!path.contains(nodes)){
                            path.add(nodes);
                        }
                    });

                }
            }
        

        return path;
    }

    /**
     * @param start
     * @param goal
     * @return
     */
    public LinkedList<Node> ufc(Node start, Node goal, LinkedList<Node> path) {

        Node temp;
        queue = new LinkedList<Node>();
        Node tempNode;
        queue.add(start);
        while (!queue.isEmpty()) {
            temp = queue.peek();
            for (Node node : temp.getChilds()) {
                queue.add(node);
            }

            path.add(temp);

            tempNode = temp;

            while(!tempNode.getChilds().isEmpty()){
                tempNode=tempNode.getChilds().removeFirst();
                if(tempNode!=null){
                    if(!path.contains(tempNode)){
                        path.add(tempNode);
                        if(path.contains(goal)){
                            queue.clear();
                            break;
                        }
                    }
                    
                }
                
            }

        }
        return path;

    }
    
    private void getChild(char[][] matriz){
       
        Node temp;
        
        for(int x=0; x < matriz.length;x++){
            for (int y=0; y< matriz[x].length; y++){
                
                if(y-1>0){
                    temp = this.obtenerCoordenadas(x, y-1, this.structure);
                }
                if(x+1<matriz[x].length && y-1>0){
                    temp = this.obtenerCoordenadas(x+1, y-1, this.structure);
                }
                if(x+1<matriz[x].length){
                    temp = this.obtenerCoordenadas(x+1, y, this.structure);
                }
                if(x+1<matriz[x].length && y+1<matriz.length){
                    temp = this.obtenerCoordenadas(x+1, y+1, this.structure);
                }
                if(y+1<matriz.length){
                    temp = this.obtenerCoordenadas(x, y+1, this.structure);
                }
                if(x-1>0 && y+1<matriz.length){
                    temp = this.obtenerCoordenadas(x-1, y+1, this.structure);
                }
                if(x-1>0){
                    temp = this.obtenerCoordenadas(x-1, y, this.structure);
                }
                if(x-1>0 && y-1>0){
                    temp = this.obtenerCoordenadas(x-1, y-1, this.structure);
                }
            }
        }
            
         
        }
        
    }
    
    private Node obtenerCoordenadas(int x, int y, HashMap<int[], Node> structure){
        
        for (int[] key : structure.keySet()) {
        
        return
    }

}