package modelos;
import java.io.Serializable;
import javax.swing.ImageIcon;



public class ImageNode implements Serializable
{
    private ImageIcon contenedorImagen;
    
    private ImageNode siguienteNodo;
    
    /** Constructor de Objetos NodoImagen
      * @param contenedorImagen de NodoImagen
      */
    
    public ImageNode(ImageIcon contenedorImagen){
    
        this.contenedorImagen=contenedorImagen;
        this.siguienteNodo=null;
    
    }
    
    /** Retorna la imagen de NodoImagen
      * @return la imagen de NodoImagen
      */

    public ImageIcon getContenedorImagen(){
        
        return contenedorImagen;
        
    }
    
    /** Asigna a NodoImagen una imagen
      * @param contenedorImagen de NodoImagen
      */

    public void setContenedorImagen(ImageIcon contenedorImagen){
        
        this.contenedorImagen=contenedorImagen;
        
    }
    
    /** Retorna el siguiente nodo de NodoImagen
      * @return el siguiente nodo de NodoImagen
      */

    public ImageNode getSiguienteNodo(){
        
        return siguienteNodo;
        
    }
    
    /** Asigna a NodoImagen un nuevo nodo
      * @param siguienteNodo de NodoImagen
      */

    public void setSiguienteNodo(ImageNode siguienteNodo){
        
        this.siguienteNodo=siguienteNodo;
        
    }
    
}
