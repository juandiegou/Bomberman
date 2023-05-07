package models;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



public class Square extends Component
{
    private JLabel etiquetaCelda;
    
    /** Constructor de objetos CeldaTablero con imagen
      * @param tamañoEnX de CeldaTablero
      * @param tamañoEnY de CeldaTablero
      * @param imagenCelda de CeldaTablero
      * @param panelJuego para añadir la celda
      */
    
    public Square(int tamañoEnX, int tamañoEnY, ImageIcon imagenCelda, JPanel panelJuego){
    
        etiquetaCelda=new JLabel();
        etiquetaCelda.setBounds(tamañoEnX*64, tamañoEnY*64, 60, 60);
        etiquetaCelda.setIcon(imagenCelda);
        panelJuego.add(etiquetaCelda);
    
    }
    
    /** Constructor de objetos CeldaTablero con texto
      * @param tamañoEnX de CeldaTablero
      * @param tamañoEnY de CeldaTablero
      * @param fuenteTexto de CeldaTablero
      * @param texto de CeldaTablero
      * @param colorTexto de CeldaTablero
      * @param panelJuego para añadir la celda
      */
    
    public Square(int tamañoEnX, int tamañoEnY, Font fuenteTexto, String texto, Color colorTexto, JPanel panelJuego){
    
        etiquetaCelda=new JLabel();
        etiquetaCelda.setBounds(tamañoEnX*64, tamañoEnY*64, 64, 64);        
        etiquetaCelda.setFont(fuenteTexto);
        etiquetaCelda.setText(texto);
        etiquetaCelda.setForeground(colorTexto);
        etiquetaCelda.setHorizontalAlignment(SwingConstants.CENTER);
        panelJuego.add(etiquetaCelda);
    
    }
    
    /** Añade a CeldaTablero al panel
      * @param panelJuego para añadir la celda 
      */
    
    public void añadirCeldaAlPanel(JPanel panelJuego){
    
        panelJuego.add(etiquetaCelda);
    
    }
    
    /** Retorna la imagen actual de CeldaTablero
      * @return la imagen actual de CeldaTablero
      */
    
    public ImageIcon getImagenCelda(){
    
        return (ImageIcon)etiquetaCelda.getIcon();
    
    }
    
    /** Asigna a CeldaTablero una imagen
      * @param imagenCelda de CeldaTablero
      */
    
    public void setImagenCelda(ImageIcon imagenCelda){
    
        etiquetaCelda.setIcon(imagenCelda);
        
    }
    
}
