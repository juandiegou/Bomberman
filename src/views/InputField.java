package views;

import jade.core.Runtime;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.EventController;
import models.Graph;
import models.Node;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputField extends JPanel implements ActionListener{
    private JTextField textField1;
    private JTextField textField2;
    private JButton button;
    private int [] data;
    private Graph graph;
    private EventController controller;
    public Runtime runtime = Runtime.instance();
    public Profile profile = new ProfileImpl("localhost",1099,"MAIN",true); 
    public AgentContainer container =  runtime.createMainContainer(profile);
    public boolean initialization = true;

    /**
     * 
     */
    public InputField(Graph graph, EventController controller) { 
        this.graph = graph;
        this.controller = controller;
        JLabel label = new JLabel("Ingrese coordenadas X, Y");
        textField1 = new JTextField("",3);
        textField2 = new JTextField("",3);
        button = new JButton("Enviar");
        button.addActionListener(this);
        add(label);
        add(textField1);
        add(textField2);
        add(button);
    }


    public int[] getData() {
        return this.data;
    }

    public void setData(int[] data) {
        this.data = data;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource().equals(button)) {
            int valueX;
            int valueY;
            try{
                valueX = Integer.parseInt( textField1.getText());
                valueY = Integer.parseInt(textField2.getText());
                this.setData(new int []{valueX,valueY});
                if(data!=null){
                    Node node =this.graph.nodeFromCoords(data[0], data[1], this.graph.structure);
                    if(this.controller.start==null){
                        this.controller.setStart(node);
                        this.controller.frame.dispose();
                        JOptionPane.showMessageDialog(null, "Nodo Inicio ingresado","Información",JOptionPane.INFORMATION_MESSAGE);
                        Thread.sleep(500);
                        this.controller.getNode();
                       
                    }else if(this.controller.start!=null && this.controller.goal==null){
                        this.controller.setGoal(node);
                        this.controller.frame.dispose();
                        JOptionPane.showMessageDialog(null, "Nodo Final ingresado","Información",JOptionPane.INFORMATION_MESSAGE);
                        while(initialization){
                            if (this.graph != null) {
                                if(this.controller.start != null & this.controller.start != null){
                                    try {
                                        ((ContainerController) container).createNewAgent("bomberman", "models.agents.Bomberman",
                                        new Object[]{this.controller.start, this.controller.goal, this.graph, this.controller.ventana,this.controller});
                                        container.getAgent("bomberman").start();       
                                        ((ContainerController) container).createNewAgent("ghost", "models.agents.Ghost", 
                                        new Object[]{this.graph, this.controller.ventana } );
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
            }catch(NumberFormatException er){
                JOptionPane.showMessageDialog(null, "Ingrese Valores Enteros","Error ingreso datos",JOptionPane.ERROR_MESSAGE);
                er.printStackTrace();
            } catch (Throwable e1) {
                e1.printStackTrace();
            }

        
        }
    }


}
