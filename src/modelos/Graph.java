
import java.util.Queue;
import java.util.Map.Entry;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

class Graph {

    private Node start;
    private Node goal;
    private HashMap<int[], Node> structure;
    private Queue<Node> queue;

    public Graph(Node start, Node goal) {
        this.start = start;
        this.goal = goal;
    }

    public Graph(HashMap<int[], Node> structure,char[][] matrix) {

        this.structure = structure;
        this.getChild(matrix);
        //this.getFullChild(matrix);
    }

    public Graph() {

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
                if(!path.contains(nodes)){
                    queue.add(nodes);
                }
                
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

        LinkedList<Node> templist = new LinkedList<Node>();
        if (path.contains(goal)) {
            return path;
        }

        if (!path.contains(start)) {
            path.add(start);
            for (Node node : start.getChilds()) {

                templist = profundidad(node, goal, path);
                templist.forEach((nodes) -> {
                    if (!path.contains(nodes)) {
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

            while (!tempNode.getChilds().isEmpty()) {
                tempNode = tempNode.getChilds().removeFirst();
                if (tempNode != null) {
                    if (!path.contains(tempNode)) {
                        path.add(tempNode);
                        if (path.contains(goal)) {
                            queue.clear();
                            break;
                        }
                    }

                }

            }

        }
        return path;

    }

    /**
     * @param matrix
     */
    private void getFullChild(char[][] matrix) {

        Node aux; 
        Node temp;

        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                aux= this.nodeFromCoords(x, y, structure);
                if (x + 1 < matrix[x].length && y - 1 > 0) {
                    temp = this.nodeFromCoords(x + 1, y - 1, this.structure);
                    if(temp!=null){
                        aux.childs.add(temp);
                    }
                }
                if (x + 1 < matrix[x].length) {
                    temp = this.nodeFromCoords(x + 1, y, this.structure);
                    if(temp!=null){
                        aux.childs.add(temp);
                    }
                }
                if (x + 1 < matrix[x].length && y + 1 < matrix.length) {
                    temp = this.nodeFromCoords(x + 1, y + 1, this.structure);
                    if(temp!=null){
                        aux.childs.add(temp);
                    }
                }
                if (y + 1 < matrix.length) {
                    temp = this.nodeFromCoords(x, y + 1, this.structure);
                    if(temp!=null){
                        aux.childs.add(temp);
                    }
                }
                if (x - 1 > 0 && y + 1 < matrix.length) {
                    temp = this.nodeFromCoords(x - 1, y + 1, this.structure);
                    if(temp!=null){
                        aux.childs.add(temp);
                    }
                }
                if (x - 1 > 0 && y - 1 > 0) {
                    temp = this.nodeFromCoords(x - 1, y - 1, this.structure);
                    if(temp!=null){
                        aux.childs.add(temp);
                    }
                }
                if (y - 1 > 0) {
                    temp = this.nodeFromCoords(x, y - 1, this.structure);
                    if(temp!=null){
                        aux.childs.add(temp);
                    }
                   
                    
                }





                if (x - 1 > 0) {
                    temp = this.nodeFromCoords(x - 1, y, this.structure);
                    if(temp!=null){
                        aux.childs.add(temp);
                    }
                }

            }
        }

    

    }

    private void getChild(char[][] matrix) {

        Node aux; 
        Node temp;

        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                aux= this.nodeFromCoords(x, y, structure);
                if (x + 1 < matrix[x].length) {
                    temp = this.nodeFromCoords(x + 1, y, this.structure);
                    if(temp!=null){
                        aux.childs.add(temp);
                    }
                }
                if (y + 1 < matrix.length) {
                    temp = this.nodeFromCoords(x, y + 1, this.structure);
                    if(temp!=null){
                        aux.childs.add(temp);
                    }
                }
                if (y - 1 > 0) {
                    temp = this.nodeFromCoords(x, y - 1, this.structure);
                    if(temp!=null){
                        aux.childs.add(temp);
                    }
                   
                    
                }

                if (x - 1 > 0) {
                    temp = this.nodeFromCoords(x - 1, y, this.structure);
                    if(temp!=null){
                        aux.childs.add(temp);
                    }
                }
            }
        }

    

    }


    /**
     * @param x
     * @param y
     * @param structure
     * @return
     */
    public Node nodeFromCoords(int x, int y, HashMap<int[], Node> structure){
        
        for (Entry<int[], Node> data : structure.entrySet()) {
            if(data.getKey()[0]== x && data.getKey()[1]==y){
                return data.getValue();
            }
        }
        return null;
    }


    /**
     * @param start
     * @param goal
     * @param type
     * @return
     */
    public LinkedList<Node> beamsearch(Node start, Node goal, boolean type){
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return Double.compare(node1.getPriority(), node2.getPriority());
            }
        });
        HashMap<Node, Double> heuristic;
        if(type){
            heuristic=this.euclidian(goal);
        }else{
            heuristic = this.manhattan(goal);
        }
        int beta = this.getBeta();
        queue.add(start);
        this.initializtion();
        HashMap<Node, Node> parentMap = new HashMap<>();
        LinkedList<Node> visitedNodes = new LinkedList<Node>();
        int currentPriority = 0;

        while(!queue.isEmpty()){
            Node current = queue.poll();
            if(!visitedNodes.contains(current)){
                visitedNodes.add(current);
            }
            
            if(current.equals(goal)){ 
                LinkedList<Node> path = new LinkedList<>();
                Node node = current;
                while(node != null){ 
                    path.addFirst(node);
                    node = parentMap.get(node);
                }
                return path;
            }
            for(Node child : current.getChilds()){ 
                double cost = currentPriority + heuristic.get(child); 
                if(child.getPriority() > cost){ 
                    queue.remove(child); 
                    if(!visitedNodes.contains(current)){
                        visitedNodes.add(current);
                    }
                    child.setPriority(cost);
                    queue.add(child); 
                    parentMap.put(child, current); 
                }
            }
            if(queue.size() > beta){ 
                queue.poll();
            }
            currentPriority++;
        }
        return visitedNodes;
    }



    /**
     * @param goal
     * @return
     */
    public HashMap<Node, Double> euclidian(Node goal){
        HashMap<Node, Double> euclidean = new HashMap<Node, Double>();
        for(Node origin: structure.values()){
            euclidean.put(origin, (double) euclidean(origin, goal) );
        }
        return euclidean;
        
    }

    public HashMap<Node, Double> manhattan(Node goal){
        HashMap<Node, Double> manhhatan = new HashMap<Node, Double>();
        for(Node origin: structure.values()){
            manhhatan.put(origin, (double) manhattan(origin, goal) );
        }
        return manhhatan;
    }


    /**
     * @param origin
     * @param target
     * @return metrics between two Nodes
     */
    private double euclidean (Node origin, Node target){
        return Math.sqrt(Math.pow(origin.positionX - target.positionX, 2) + Math.pow(origin.positionY - target.positionY, 2));
    }

    /**
     * @param origin
     * @param target
     * @return metrics manhattan between two Nodes
     */
    private double manhattan (Node origin, Node target){
        return Math.abs(origin.positionX - target.positionX) + Math.abs(origin.positionY - target.positionY);
    }


    private int getBeta(){
        int beta=0;
        for(Node node: structure.values()){
            if(node.getChilds().size()>beta){
                beta=node.getChilds().size();
            }
        }
        return beta;
    }

    private void  initializtion(){
        for(Node temp : this.structure.values()){
            temp.setPrioity(Double.POSITIVE_INFINITY);
        }
    }

    public LinkedList<Node> hillClimbing(Node start, Node goal) {
        LinkedList<Node> path = new LinkedList<Node>();
        Node current = start;
        double currentCost = manhattan(current, goal);
        path.add(current);
        while (current != goal) {
            double lowestCost = Double.POSITIVE_INFINITY;
            Node lowestNode = null;
            for (Node neighbor : current.getChilds()) {
                double neighborCost = manhattan(neighbor, goal);
                if (neighborCost < lowestCost) {
                    lowestCost = neighborCost;
                    lowestNode = neighbor;
                }
            }
            if (lowestCost >= currentCost) {
                return path;
            }
            current = lowestNode;
            currentCost = lowestCost;
            path.add(current);
        }
        return path;
    }
}