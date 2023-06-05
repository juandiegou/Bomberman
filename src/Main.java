
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controllers.EventController;
import jade.core.Runtime;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import models.Graph;
import models.Node;
import models.agents.Bomberman;
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
        fileChooser.setFileFilter(new FileNameExtensionFilter(".*bomberman's map", "txt"));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            path = fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            path = "C:\\Users\\JUANDIEGO\\Desktop\\path2.txt";
        }
        String[][] matrix;
        matrix = new Conversor().TransformFile(new Reader().readFile(path));
        HashMap<int[], Node> structure = new HashMap<>();
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                structure.put(new int[] { x, y }, new Node(new int[] { x, y }, matrix[x][y]));
            }
        }
        Graph graph = new Graph(structure, matrix);
        window = new VentanaJuego(matrix);
        controller = new EventController(window, graph);
        
        /**
         * 
         * 
         Runtime runtime = Runtime.instance();
         Profile profile = new ProfileImpl("localhost", 1099, "MAIN", true);
 
         try {
             AgentController agentController = runtime.createMainContainer(profile).createNewAgent("vista", "views.AGUI",
                     new Object[] { graph });
             agentController.start();
         } catch (Exception ex) {
             ex.printStackTrace();
         }
         */


        Runtime runtime = Runtime.instance();
        Profile profile = new ProfileImpl("localhost",1099,"MAIN",true); 
        AgentContainer container =  runtime.createMainContainer(profile);
        boolean initialization = true;
        while(initialization){
            if (graph != null) {
                System.out.println("grpah");
                if(controller.start != null & controller.goal != null){
                    System.out.println("nodes");
                    try {
                        ((ContainerController) container).createNewAgent("bomberman", "models.agents.Bomberman",
                        new Object[]{controller.start, controller.goal, graph, window,controller});
                        container.getAgent("bomberman").start();                  
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    try {
                        ((ContainerController) container).createNewAgent("ghost", "models.agents.Ghost", 
                        new Object[]{graph, window } );
                        container.getAgent("ghost").start();
                        initialization=false;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                    
            }

        }

    }

}
