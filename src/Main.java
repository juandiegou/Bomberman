
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controllers.EventController;
import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import models.Graph;
import models.Node;
import tools.Conversor;
import tools.Reader;
import views.VentanaJuego;

public class Main {

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        VentanaJuego window;
        EventController controller;
        String path;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter( new FileNameExtensionFilter(".*bomberman's map", "txt")); 
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            path = fileChooser.getSelectedFile().getAbsolutePath();
        }else{
            path = "C:\\Users\\JUANDIEGO\\Desktop\\path2.txt";
        }       
        String[][] matrix; 
        matrix = new Conversor().TransformFile(new Reader().readFile(path));
        HashMap<int[],Node> structure = new HashMap<>();
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                structure.put(new int[] {x, y}, new Node(new int[] {x, y}, matrix[x][y]));   
            }
        }
        Graph graph = new Graph(structure, matrix);
        window = new VentanaJuego(matrix);
        controller = new EventController(window, graph);
        //Runtime runtime = Runtime.instance();
        //Profile profile = new ProfileImpl("localhost",1099,"MAIN",true);    
        /*
         * 

         try {
             AgentController agentController = runtime.createMainContainer(profile).createNewAgent("vista", "views.AGUI",new Object[]{graph} );
             agentController.start();
         } catch (Exception ex) {
             ex.printStackTrace();
         }
         */

    }

    
}
