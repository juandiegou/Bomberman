package models;

import java.util.Queue;
import java.util.Map.Entry;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Graph {
    public HashMap<int[], Node> structure;
    private Queue<Node> queue;
    private int level;
    public String[][] matrix;

    public Graph(HashMap<int[], Node> structure, String[][] matrix) {

        this.level = 0;
        this.structure = structure;
        this.matrix = matrix;
        this.getChild(matrix);
        // this.getFullChild(matrix);
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
                if (!path.contains(nodes)) {
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
        start.setPriority(0);
        queue.add(start);
        //path.addLast(goal);
        while (!queue.isEmpty()) {
            temp = queue.poll();
            for (Node node : temp.getChilds()) {
                queue.add(node);
            }
            path.add(temp);
            tempNode = temp;
            while (!tempNode.getChilds().isEmpty()) {
                if (tempNode.getChilds().size() >=0 ) {  
                    tempNode = tempNode.getChilds().removeLast();
                    if (!path.contains(tempNode)) {
                        path.add(tempNode);
                        if (path.contains(goal)) {
                            queue.clear();
                            break;
                        }
                    }
                }
            }
            if (path.size() >this.matrix.length*this.matrix[0].length) {
                queue.clear();
            }
        }
        return path;

       
    }
    

    /**
     * @param matrix
     */
    private void getFullChild(String[][] matrix) {

        Node aux;
        Node temp;

        for (int filas = 0; filas < matrix.length; filas++) {
            for (int columnas = 0; columnas < matrix[filas].length; columnas++) {
                aux = this.nodeFromCoords(filas, columnas, structure);

                // x-1 y
                if (filas - 1 > 0) {
                    temp = this.nodeFromCoords(filas - 1, columnas, this.structure);
                    if (temp != null) {
                        aux.childs.add(temp);
                    }
                }

                // x-1 y-1
                if (filas - 1 > 0 && columnas - 1 > 0) {
                    temp = this.nodeFromCoords(filas + 1, columnas - 1, this.structure);
                    if (temp != null) {
                        aux.childs.add(temp);
                    }
                }
                // x y-1
                if (columnas - 1 > 0) {
                    temp = this.nodeFromCoords(filas, columnas - 1, this.structure);
                    if (temp != null) {
                        aux.childs.add(temp);
                    }
                }
                // x+1 y-1
                if (filas + 1 < matrix.length && columnas - 1 > 0) {
                    temp = this.nodeFromCoords(filas + 1, columnas - 1, this.structure);
                    if (temp != null) {
                        aux.childs.add(temp);
                    }
                }
                // x+1 y
                if (filas + 1 < matrix.length) {
                    temp = this.nodeFromCoords(filas + 1, columnas, this.structure);
                    if (temp != null) {
                        aux.childs.add(temp);
                    }
                }

                // x+1 y+1
                if (filas + 1 < matrix.length && columnas + 1 < matrix[filas].length) {
                    temp = this.nodeFromCoords(filas + 1, columnas + 1, this.structure);
                    if (temp != null) {
                        aux.childs.add(temp);
                    }
                }
                // x y+1
                if (columnas + 1 < matrix[filas].length) {
                    temp = this.nodeFromCoords(filas, columnas + 1, this.structure);
                    if (temp != null) {
                        aux.childs.add(temp);
                    }
                }

                // x-1 y+1
                if (filas - 1 > 0 && columnas + 1 < matrix[filas].length) {
                    temp = this.nodeFromCoords(filas - 1, columnas + 1, this.structure);
                    if (temp != null) {
                        aux.childs.add(temp);
                    }
                }

            }
        }

    }

    private void getChild(String[][] matrix) {

        Node aux;
        Node temp;

        for (int filas = 0; filas < matrix.length; filas++) {
            for (int columnas = 0; columnas < matrix[filas].length; columnas++) {
                aux = this.nodeFromCoords(filas, columnas, structure);

                // x-1 y
                if (filas - 1 > 0) {
                    temp = this.nodeFromCoords(filas - 1, columnas, this.structure);
                    if (temp != null && !temp.data.equalsIgnoreCase("M")) {
                        aux.childs.add(temp);
                    }
                }
                // x y-1
                if (columnas - 1 > 0) {
                    temp = this.nodeFromCoords(filas, columnas - 1, this.structure);
                    if (temp != null && !temp.data.equalsIgnoreCase("M")) {
                        aux.childs.add(temp);
                    }
                }
                // x+1 y
                if (filas + 1 < matrix.length) {
                    temp = this.nodeFromCoords(filas + 1, columnas, this.structure);
                    if (temp != null && !temp.data.equalsIgnoreCase("M")) {
                        aux.childs.add(temp);

                    }

                }

                // x y+1
                if (columnas + 1 < matrix[filas].length) {
                    temp = this.nodeFromCoords(filas, columnas + 1, this.structure);
                    if (temp != null && !temp.data.equalsIgnoreCase("M")) {
                        aux.childs.add(temp);
                        this.level++;
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
    public Node nodeFromCoords(int x, int y, HashMap<int[], Node> structure) {
        for (Entry<int[], Node> data : structure.entrySet()) {
            if (data.getKey()[0] == x && data.getKey()[1] == y) {
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
    public LinkedList<Node> beamsearch(Node start, Node goal, boolean type) {
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return Double.compare(node1.getPriority(), node2.getPriority());
            }
        });
        HashMap<Node, Double> heuristic;
        if (type) {
            heuristic = this.euclidian(goal);
        } else {
            heuristic = this.manhattan(goal);
        }
        int beta = this.getBeta();
        queue.add(start);
        this.initialization();
        HashMap<Node, Node> parentMap = new HashMap<>();
        LinkedList<Node> visitedNodes = new LinkedList<Node>();
        int currentPriority = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (!visitedNodes.contains(current)) {
                visitedNodes.add(current);
            }

            if (current.equals(goal)) {
                LinkedList<Node> path = new LinkedList<>();
                Node node = current;
                while (node != null) {
                    path.addFirst(node);
                    node = parentMap.get(node);
                }
                return path;
            }
            for (Node child : current.getChilds()) {
                double cost = currentPriority + heuristic.get(child);
                if (child.getPriority() > cost) {
                    queue.remove(child);
                    if (!visitedNodes.contains(current)) {
                        visitedNodes.add(current);
                    }
                    child.setPriority(cost);
                    queue.add(child);
                    parentMap.put(child, current);
                }
            }
            if (queue.size() > beta) {
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
    public HashMap<Node, Double> euclidian(Node goal) {
        HashMap<Node, Double> euclidean = new HashMap<Node, Double>();
        for (Node origin : structure.values()) {
            euclidean.put(origin, (double) euclidean(origin, goal));
        }
        return euclidean;

    }

    public HashMap<Node, Double> manhattan(Node goal) {
        HashMap<Node, Double> manhhatan = new HashMap<Node, Double>();
        for (Node origin : structure.values()) {
            manhhatan.put(origin, (double) manhattan(origin, goal));
        }
        return manhhatan;
    }

    /**
     * @param origin
     * @param target
     * @return metrics between two Nodes
     */
    private double euclidean(Node origin, Node target) {
        return Math.sqrt(
                Math.pow(origin.positionX - target.positionX, 2) + Math.pow(origin.positionY - target.positionY, 2));
    }

    /**
     * @param origin
     * @param target
     * @return metrics manhattan between two Nodes
     */
    private double manhattan(Node origin, Node target) {
        return Math.abs(origin.positionX - target.positionX) + Math.abs(origin.positionY - target.positionY);
    }

    private int getBeta() {
        int beta = 0;
        for (Node node : structure.values()) {
            if (node.getChilds().size() > beta) {
                beta = node.getChilds().size();
            }
        }
        return beta;
    }

    private void initialization() {
        for (Node temp : this.structure.values()) {
            temp.setPriority(Double.POSITIVE_INFINITY);
        }
    }

    public LinkedList<Node> hillClimbing(Node start, Node goal, boolean euclidean) {
        LinkedList<Node> path = new LinkedList<Node>();
        Node current = start;
        double currentCost;
        HashMap<Node, Double> heuristic;
        if (euclidean) {
            currentCost = euclidean(current, goal);
            heuristic= euclidian(goal);

        } else {
            currentCost = manhattan(current, goal);
            heuristic=manhattan(goal);
         }
        path.add(current);
        while (current != goal) {
            double lowestCost = Double.POSITIVE_INFINITY;
            Node lowestNode = null;
            for (Node neighbor : current.getChilds()) {
                double neighborCost;
                if (euclidean) {
                    neighborCost = manhattan(neighbor, goal);
                } else {

                    neighborCost = euclidean(neighbor, goal);
                }
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

    /**
     * Find shortest path between two nodes using A* algorithm
     * 
     * @param origin starting node
     * @param target end node
     * @return LinkedList of nodes representing the shortest path
     */
    public LinkedList<Node> AStar(Node origin, Node target, boolean type) {

        HashMap<Node, Double> heuristic;
        if (type) {
            heuristic = this.euclidian(target);
        } else {
            heuristic = this.manhattan(target);
        }
        initialization();
        // initialize priority queue to store nodes to visit
        LinkedList<Node> path = new LinkedList<Node>();
        PriorityQueue<Node> openList = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return Double.compare(node1.getPriority(), node2.getPriority());
            }
        });

        // initialize set to mark nodes already visited
        HashSet<Node> closedList = new HashSet<Node>();

        // set priority of origin node to 0 and add to queue
        // origin.setPriority(0);
        openList.add(origin);

        while (!openList.isEmpty()) {

            // get node with lowest priority value
            Node current = openList.poll();

            // check if we've reached the target node
            if (current == target) {
                // backtrack to create path from origin to target and return it
                System.out.println("encontrado!!!");
                return reconstructPath(origin, target);
            }

            // add current node to the closed list
            closedList.add(current);

            // look at all neighbors of current node
            for (Node neighbor : current.getChilds()) {

                // if neighbor node has already been visited, skip it
                if (closedList.contains(neighbor)) {
                    continue;
                }

                // calculate the distance from the origin node to the neighbor node
                // double tentativeDist = current.getPriority() + neighbor.getPriority();
                double tentativeDist = heuristic.get(current) + heuristic.get(neighbor);
                if (tentativeDist < neighbor.getPriority()) {

                    neighbor.setPrevious(current);
                    neighbor.setPriority(tentativeDist);

                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                    }
                }
            }
            path.add(current);
        }

        return path;
    }

    /**
     * @param origin starting node
     * @param target end node
     * @return LinkedList of nodes representing the path
     */
    private LinkedList<Node> reconstructPath(Node origin, Node target) {
        LinkedList<Node> path = new LinkedList<Node>();
        Node current = target;
        while (current != origin) {
            path.addFirst(current);
            current = current.getPrevious();
        }
        path.addFirst(origin);
        return path;
    }

    public int getlevel() {
        return this.level;
    }
}