package models;

import java.awt.Component;
import java.awt.Font;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import images.Images;

public class Board extends Component {
    public Square[][] celdasTablero;

    public JPanel panelJuego;
    public ImageList contenedorImagenesGemas;

    private String[][] matrix;
    private Font fuenteTexto;

    /**
     * Constructor de objetos TableroJuego
     * 
     * @param nombreJugador  de jugador
     * @param nombreOponente de oponente
     * @param panelJuego     para añadir los componentes de TableroJuego
     */

    public Board(String[][] matrix, JPanel panelJuego) {
        this.matrix = matrix;
        this.panelJuego = panelJuego;
        celdasTablero = new Square[matrix.length][matrix[0].length];
        this.fuenteTexto = new Font("Arial", Font.BOLD, 17);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                celdasTablero[i][j] = getSquare(i, j, matrix[i][j]);
            }
        }

    }

    /**
     * Actualiza las casillas y datos de TableroJuego
     * 
     * @param panelJuego de TableroJuego
     */

    public void actualizarTablero(JPanel panelJuego, LinkedList<Node> path) {

        this.panelJuego = panelJuego;
        for (Node node : path) {
            this.actualizarImagenCelda(node.positionX, node.positionY,
                getSquare(node.positionX, node.positionY, node.data).getImagenCelda());

            try {
                TimeUnit.MILLISECONDS.sleep(125);

                this.repaint();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.repaint();
    }

    /**
     * Actualiza la imagen de una celda del tablero
     * 
     * @param posicionCeldaX de celdasTablero
     * @param posicionCeldaY de celdasTablero
     * @param imagen         que actualizará a la anterior
     */

    public void actualizarImagenCelda(int posicionCeldaX, int posicionCeldaY, ImageIcon imagen) {

        celdasTablero[posicionCeldaX][posicionCeldaY].setImagenCelda(imagen);

    }

    /***
     * Este método actua como una fabrica de Square[estos representan los cuadros de
     * los nodos]
     * 
     * @param posX posición del nodos en X
     * @param posY posicion del node en y
     * @param data el dato del nodo
     * @return Square repretación grafica del Nodo
     */
    public Square getSquare(int posX, int posY, String data) {
        if (data.toUpperCase().equals("C")) {
            return new Square(posX, posY, Images.imagenPiso, this.panelJuego);
        }
        if (data.toUpperCase().equals("M")) {
            return new Square(posX, posY, Images.imagenMuro, this.panelJuego);
        }
        if (data.toUpperCase().equals("R")) {
            return new Square(posX, posY, Images.imagenPared, this.panelJuego);
        }
        if (data.toUpperCase().equals("P")) {
            return new Square(posX, posY, Images.imagenPiedraRubi, this.panelJuego);
        }
        if (data.toUpperCase().equals("G")) {
            return new Square(posX, posY, Images.imagenGhost , this.panelJuego);
        }
        return null;

    }

    public void resetBoard() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                this.actualizarImagenCelda(i, j, getSquare(i, j, matrix[i][j]).getImagenCelda());
            }
        }
    }
}
