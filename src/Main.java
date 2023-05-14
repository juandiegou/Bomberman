
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import controllers.EventController;
import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import models.*;
import tools.Conversor;
import tools.Reader;
import views.VentanaJuego;
import views.*;

/**
 *
 * @author Usuario
 */
public class Main {

    /**
     * @param args
     * @param graph2 TODO
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {

        //Espacios para probar si recibe, el archivo .txt y mapea.
        
        String path = "C:\\Users\\JUANDIEGO\\Desktop\\path2.txt";
        String[][] matrix; 

        Conversor convertidor = new Conversor();
        Reader lector = new Reader();

        //Impresion del text leido
        //System.out.println(lector.readFile(path));
        
        
        //Matrix lista para trabajarle.
        matrix = convertidor.TransformFile(lector.readFile(path));
        HashMap<int[],Node> structure = new HashMap<>();
      
        for (int x = 0; x < matrix.length; x++) {
            
            for (int y = 0; y < matrix[x].length; y++) {
                structure.put(new int[] {x, y}, new Node(new int[] {x, y}, matrix[x][y]));   
            }
        }
        
        Graph graph = new Graph(structure, matrix);
        Node start = graph.nodeFromCoords(0, 0, structure);
        Node goal = graph.nodeFromCoords(6,2, structure);
        //LinkedList<Node> nodePath = graph.beamsearch(start, goal, true);
        //LinkedList<Node> nodePath = graph.profundidad(start, goal, new LinkedList<Node>());
        //LinkedList<Node> nodePath = graph.anchura(start, goal);
        //LinkedList<Node> nodePath = graph.ufc(start, goal,new LinkedList<Node>());
        
        //LinkedList<Node> nodePath = graph.hillClimbing(start, goal);
        //System.out.println(start);
        //System.out.println(goal);
        //LinkedList<Node> nodePath = graph.AStar(start, goal,true);
        //System.out.println(nodePath.size());
        

        


        //System.out.println("\nAltura del arbol:"+ graph.getNivel());
        /**
        VentanaJuego bomberman=new VentanaJuego(matrix);
        EventController controller=new EventController(bomberman,graph);
        controller.setGoal(goal);
        controller.setStart(start);
        */

        Runtime runtime = Runtime.instance();
        Profile profile = new ProfileImpl();
        //profile profile = new ProfileImpl("localhost", 3333,"MainPlataform",true);
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        profile.setParameter(Profile.MAIN_PORT, "1099");
        profile.setParameter(Profile.PLATFORM_ID, "MAIN");
        //profile.setParameter(Profile.LOCAL_PORT, 2000);

       
        
        try {
            // Crear el agente de GUI
            Object[] arg = new Object[]{graph};
            AgentContainer container = runtime.createMainContainer(profile);
            System.out.println(container);
            AgentController agentController = container.createNewAgent("vista", "views.AGUI",arg );
            agentController.start();
            

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    
}
