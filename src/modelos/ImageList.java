package modelos;
import java.io.Serializable;
import javax.swing.ImageIcon;


public class ImageList implements Serializable
{
    private ImageNode imagenRaiz;
    
    private int cantidadImagenes;
    
    /** Constructor de Objetos ListaImagen
      */
    
    public ImageList(){
    
        this.imagenRaiz=null;
        this.cantidadImagenes=0;
    
    }
    
    /** Adiciona al final una imagen a ListaImagen
      * @param imagen de ListaImagen
      */
    
    public void adicionarImagenAlFinal(ImageIcon imagen){
    
        ImageNode newNode=new ImageNode(imagen);
        
        if(imagenRaiz==null){
        
            imagenRaiz=newNode;
        
        }
        else{
        
            ImageNode nodoRecorredor=imagenRaiz;
            
            while(nodoRecorredor.getSiguienteNodo()!=null){
            
                nodoRecorredor=nodoRecorredor.getSiguienteNodo();
            
            }
            
            nodoRecorredor.setSiguienteNodo(newNode);
        
        }
        
        cantidadImagenes++;
    
    }
    
    /** Elimina al final una imagen de ListaImagen
      */
    
    public void eliminarImagenAlFinal(){
           
        if(imagenRaiz!=null){
                    
            if(getCantidadImagenes()==1){
            
                imagenRaiz=null;
            
            }
            else{                
                
                ImageNode nodoRecorredor=imagenRaiz;
            
                while(nodoRecorredor.getSiguienteNodo()!=null){
                
                    nodoRecorredor=nodoRecorredor.getSiguienteNodo();
                
                }
                
                nodoRecorredor=null;
            
            }
        
        }
        
        cantidadImagenes--;
    
    }
    
    /** Retona la cantidad de imagenes en ListaImagen
      * @return la cantidad de imagenes en ListaImagen
      */

    public int getCantidadImagenes(){
        
        return cantidadImagenes;
        
    }
    
}
