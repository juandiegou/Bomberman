package views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.EventController;
import jade.util.Event;
import models.Graph;
import models.Node;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputField extends JPanel implements ActionListener{
    private JTextField textField1;
    private JTextField textField2;
    private JButton button;
    private int [] data;
    private ActionListener listener;
    private Graph graph;
    private EventController controller;

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

                    }
                }
            }catch(NumberFormatException er){
                JOptionPane.showMessageDialog(null, "Ingrese Valores Enteros","Error ingreso datos",JOptionPane.ERROR_MESSAGE);
                er.printStackTrace();
            } catch (Throwable e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        
        }
    }


}
