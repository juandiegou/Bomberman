package modelos;
import imagenes.Images;
import java.awt.Component;
import java.awt.Font;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends Component{
    public Square[][] celdasTablero;
    
    public JPanel panelJuego;
    public ImageList contenedorImagenesGemas;
    
    
    private Font fuenteTexto;
    
    /** Constructor de objetos TableroJuego
      * @param nombreJugador de jugador
      * @param nombreOponente de oponente
      * @param panelJuego para añadir los componentes de TableroJuego
      */
    
    public Board(String [] [] matrix,JPanel panelJuego){
        
        this.panelJuego=panelJuego;
        celdasTablero=new Square[matrix.length][matrix[0].length];
        contenedorImagenesGemas=new ImageList();
        this.fuenteTexto=new Font("Arial", Font.BOLD, 17);

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                celdasTablero[i][j] = getSquare(i, j,matrix[i][j]);
            }
            
        }
        
        
    }
    
    /** Actualiza las casillas y datos de TableroJuego
      * @param panelJuego de TableroJuego
      */
    
    public void actualizarTablero(JPanel panelJuego, LinkedList<Node> path){
    
        this.panelJuego=panelJuego;
        for(Node node :  path){
            this.actualizarImagenCelda(node.positionX, node.positionY, getSquare(node.positionX, node.positionY, node.data).getImagenCelda());
            this.repaint();
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        this.repaint();        
    }
    
    /** Actualiza la imagen de una celda del tablero
      * @param posicionCeldaX de celdasTablero
      * @param posicionCeldaY de celdasTablero
      * @param imagen que actualizará a la anterior
      */
    
    public void actualizarImagenCelda(int posicionCeldaX, int posicionCeldaY, ImageIcon imagen){
    
        celdasTablero[posicionCeldaX][posicionCeldaY].setImagenCelda(imagen);
    
    }
    
    /***
     * Este método actua como una fabrica de Square[estos representan los cuadros de  los nodos]
     * @param posX posición del nodos en X
     * @param posY posicion del node en y
     * @param data el dato del nodo
     * @return Square repretación grafica del Nodo 
     */
    public Square getSquare(int posX,int posY ,String data){
        if(data.toUpperCase().equals("C") ){
            return new Square(posX, posY, Images.imagenPiso, this.panelJuego);
        }
        if(data.toUpperCase().equals("M") ){
            return new Square(posX, posY, Images.imagenMuro, this.panelJuego);
        }
        if(data.toUpperCase().equals("R")){
            return new Square(posX, posY, Images.imagenPared, this.panelJuego);
        }
        if(data.toUpperCase().equals("P")){
            return new Square(posX, posY, Images.imagenPiedraRubi, this.panelJuego);
        }
        return null;
        
    }
        
    
}
