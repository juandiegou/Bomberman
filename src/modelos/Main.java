import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.awt.Desktop;

public class Main {

    public static void main(String[] args) {

        
        System.out.println("running");
        int [] pos00 = {0,0};
        int [] pos01 = {0,1};
        int [] pos02 = {0,2};
        int [] pos03 = {0,3};
        int [] pos04 = {0,4};
        int [] pos05 = {0,5};
        int [] pos06 = {0,6};
        
        int [] pos10 = {1,0};
        int [] pos11 = {1,1};
        int [] pos12 = {1,2};
        int [] pos13 = {1,3};
        int [] pos14 = {1,4};
        int [] pos15 = {1,5};
        int [] pos16 = {1,6};
        
        int [] pos20 = {2,0};
        int [] pos21 = {2,1};
        int [] pos22 = {2,2};
        int [] pos23 = {2,3};
        int [] pos24 = {2,4};
        int [] pos25 = {2,5};
        int [] pos26 = {2,6};
         
         
        int [] pos30 = {3,0};
        int [] pos31 = {3,1};
        int [] pos32 = {3,2};
        int [] pos33 = {3,3};
        int [] pos34 = {3,4};
        int [] pos35 = {3,5};
        int [] pos36 = {3,6};
        

        
        
        Node node00= new Node(pos00,'C');
        Node node01 = new Node(pos01,'C');
        Node node02 = new Node(pos02,'C');
        Node node03 = new Node(pos03,'C');
        Node node04 = new Node(pos04,'C');
        Node node05 = new Node(pos05,'R');
        Node node06 = new Node(pos06,'C');
         

        Node node10 = new Node(pos10,'C');
        Node node11 = new Node(pos11,'M');
        Node node12 = new Node(pos12,'C');
        Node node13 = new Node(pos13,'M');
        Node node14 = new Node(pos14,'C');
        Node node15 = new Node(pos15,'M');
        Node node16 = new Node(pos16,'C');

        Node node20 = new Node(pos20,'R');
        Node node21 = new Node(pos21,'C');
        Node node22 = new Node(pos22,'C');
        Node node23 = new Node(pos23,'C');
        Node node24 = new Node(pos24,'C');
        Node node25 = new Node(pos25,'C');
        Node node26 = new Node(pos26,'C');
         
        
        Node node30 = new Node(pos30,'C');
        Node node31 = new Node(pos31,'M');
        Node node32 = new Node(pos32,'C');
        Node node33 = new Node(pos33,'M');
        Node node34 = new Node(pos34,'C');
        Node node35 = new Node(pos35,'M');
        Node node36 = new Node(pos36,'C');
        
        node00.childs.add(node01);
        node00.childs.add(node10);
        node01.childs.add(node01);
        node01.childs.add(node11);
        node01.childs.add(node00);
        node02.childs.add(node01);
        node02.childs.add(node03);
        node02.childs.add(node12);
        node03.childs.add(node02);
        node03.childs.add(node04);
        node03.childs.add(node13);
        node04.childs.add(node03);
        node04.childs.add(node05);
        node04.childs.add(node14);
        node05.childs.add(node04);
        node05.childs.add(node06);
        node05.childs.add(node15);
        node06.childs.add(node05);
        node06.childs.add(node16);
        node10.childs.add(node00);
        node10.childs.add(node12);
        node10.childs.add(node11);
        node11.childs.add(node01);
        node11.childs.add(node12);
        node11.childs.add(node21);
        node11.childs.add(node10);
        node12.childs.add(node02);
        node12.childs.add(node13);
        node12.childs.add(node22);
        node12.childs.add(node11);
        node13.childs.add(node03);
        node13.childs.add(node14);
        node13.childs.add(node23);
        node13.childs.add(node12);
        node14.childs.add(node04);
        node14.childs.add(node15);
        node14.childs.add(node24);
        node14.childs.add(node13);
        node15.childs.add(node05);
        node15.childs.add(node16);
        node15.childs.add(node25);
        node15.childs.add(node14);
        

        Graph graph = new Graph();
        //LinkedList<Node> path = graph.profundidad(node00,node16,new LinkedList<Node>());
        //LinkedList<Node> path = graph.anchura(node00,node16);
        LinkedList<Node> path = graph.ufc(node00,node11, new LinkedList<Node>()); 

        path.forEach((n) ->{
            if(n!= null){
                System.out.println("pos:"+ n.positionX+ " "+ n.positionY);
            }
        });







        /**
         * 
         * Desktop desktop = Desktop.getDesktop();
         * 
         * try {
         * File file = new File("C:\\Users\\JUANDIEGO\\Desktop\\path.txt");
         * 
         * if(file.exists()) {
         * int r=0;
         * BufferedReader br=new BufferedReader(new FileReader(file));
         * while((r=br.read())!=-1)
         * {
         * System.out.print((char)r);
         * }
         * }
         * 
         * 
         * } catch (Exception e) {
         * // TODO: handle exception
         * }
         * 
         */

    }

}
