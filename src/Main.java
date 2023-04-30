
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import tools.Conversor;
import tools.Reader;
import modelos.*;
import vistas.VentanaJuego;


/**
 *
 * @author Usuario
 */
public class Main {

    /**
     * @param args
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {

        //Espacios para probar si recibe, el archivo .txt y mapea.
        
        String path = "C:\\Users\\JUANDIEGO\\Desktop\\path2.txt";
        String[][] matrix; 

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
        Node start = graph.nodeFromCoords(0, 0, structure);
        Node goal = graph.nodeFromCoords(6,2, structure);
        LinkedList<Node> nodePath = graph.beamsearch(start, goal, true);
        //LinkedList<Node> nodePath = graph.profundidad(start, goal, new LinkedList<Node>());
        //LinkedList<Node> nodePath = graph.anchura(start, goal);
        //LinkedList<Node> nodePath = graph.ufc(start, goal,new LinkedList<Node>());
        
        //LinkedList<Node> nodePath = graph.hillClimbing(start, goal);
        //System.out.println(start);
        //System.out.println(goal);
        //LinkedList<Node> nodePath = graph.AStar(start, goal,true);
        //System.out.println(nodePath.size());
        

        


        //System.out.println("\nAltura del arbol:"+ graph.getNivel());
        VentanaJuego bomberman=new VentanaJuego(matrix);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            nodePath.forEach((Node node)->{
                //System.out.printf(node.data+",");
                System.out.println(node.positionX+"  "+node.positionY+ " : "+node.data);
                node.data= "P";
            });
            bomberman.paintPath(nodePath);
        
        }

        
    }

    
}
