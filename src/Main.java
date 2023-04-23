
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import tools.Conversor;
import tools.Reader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Usuario
 */
public class Main {

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {

        //Espacios para probar si recibe, el archivo .txt y mapea.
        
        String path = "C:\\Users\\JUANDIEGO\\Desktop\\path.txt";
        char[][] matrix; 

        Conversor convertidor = new Conversor();
        Reader lector = new Reader();

        //Impresion del text leido
        System.out.println(lector.readFile(path));
        
        
        //Matrix lista para trabajarle.
        matrix = convertidor.TransformFile(lector.readFile(path));
        HashMap<int[],Node> structure = new HashMap<>();
      
        for (int x = 0; x < matrix.length; x++) {
            
            for (int y = 0; y < matrix[x].length; y++) {
                //System.out.print(matrix[x][y] + " ");
                structure.put(new int[] {x, y}, new Node(new int[] {x, y}, matrix[x][y]));
                
            }
            System.out.println("");
        }
        
        Graph graph = new Graph(structure, matrix);
        Node start = graph.nodeFromCoords(0, 1, structure);
        Node goal = graph.nodeFromCoords(2,4, structure);
        //LinkedList<Node> nodePath = graph.beamsearch(start, goal, true);
        //LinkedList<Node> nodePath = graph.profundidad(start, goal, new LinkedList<Node>());
        //LinkedList<Node> nodePath = graph.anchura(start, goal);
        LinkedList<Node> nodePath = graph.ufc(start, goal,new LinkedList<Node>());
        System.out.println(nodePath.size());
        nodePath.forEach((Node node)->{
            System.out.println(node.positionX+" "+node.positionY+" :"+node.data);
        });
        
    }
    
}
