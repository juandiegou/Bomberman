package modelos;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class GestorPartidas
{
    private File partida;
    
    private FileOutputStream contenedorArchivoSalida;
    private ObjectOutputStream escritorPartida;
    
    private FileInputStream contenedorArchivoEntrada;
    private ObjectInputStream lectorPartida;
    
    /** Constructor de objetos GestorPartidas
      */
    
    public GestorPartidas(){
    
        this.partida=null;
        this.contenedorArchivoSalida=null;
        this.escritorPartida=null;
        this.contenedorArchivoEntrada=null;
        this.lectorPartida=null;
    
    }
    
    /** Guarda la partida actual en un archivo .gsf
      * @param tablero a guardar
      */
    
    public void guardarPartida(Board tablero){
    
        String nombrePartida=JOptionPane.showInputDialog(null, "Guardar partida en la direccion 'src/partidasGuardadas' como:", "Guardar partida", JOptionPane.PLAIN_MESSAGE);
        
        if(nombrePartida!=null){            
        
            partida=new File("src/partidasGuardadas", nombrePartida+".gsf");
        
            try{
        
                contenedorArchivoSalida=new FileOutputStream(partida);
                escritorPartida=new ObjectOutputStream(contenedorArchivoSalida);
                escritorPartida.writeObject(tablero);
        
            }
            catch(IOException e){
        
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        
            }
            finally{
                    
                try{
            
                    if(escritorPartida!=null){
            
                        escritorPartida.close();
        
                    }
                    
                }
                catch(IOException e){
                
                    JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
                
                }
    
            }
            
            JOptionPane.showMessageDialog(null, "Partida guardada con éxito", "Guardar partida", JOptionPane.INFORMATION_MESSAGE);
            
        }
        else{
        
            JOptionPane.showMessageDialog(null, "No guardaste una partida", "Guardado cancelado", JOptionPane.OK_OPTION);
        
        }
    
    }
    
    /** Carga una partida guardada en un archivo .gsf
      * @return el tablero guardado
      */
    
    public Board cargarPartida(){
        
        Board tableroACargar=null;
    
        JFileChooser selectorPartida=new JFileChooser("src/partidasGuardadas");
        selectorPartida.setDialogTitle("Cargar partida");
        
        FileNameExtensionFilter filtroArchivosGSF=new FileNameExtensionFilter("Game Save File (.gsf)","gsf");
        selectorPartida.setFileFilter(filtroArchivosGSF);
        
        int botonPresionado=selectorPartida.showOpenDialog(null);
        
        if(botonPresionado==JFileChooser.APPROVE_OPTION){
        
            try{
        
                contenedorArchivoEntrada=new FileInputStream(selectorPartida.getSelectedFile());
                lectorPartida=new ObjectInputStream(contenedorArchivoEntrada);
                tableroACargar=(Board)lectorPartida.readObject();
        
            }
            catch(IOException | ClassNotFoundException | NullPointerException e){
        
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        
            }
            finally{
                    
                try{
            
                    if(lectorPartida!=null){
            
                        lectorPartida.close();
        
                    }
                    
                }
                catch(IOException e){
                
                    JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
                
                }
    
            }
                   
        }
        else{
        
            JOptionPane.showMessageDialog(null, "No cargaste una partida", "Carga cancelada", JOptionPane.OK_OPTION);
        
        }
        
        return tableroACargar;
    
    }
    
    /** Borra una partida guardada en un archivo .gsf
      */
    
    public void borrarPartida(){
    
        JFileChooser selectorPartida=new JFileChooser("src/partidasGuardadas");
        selectorPartida.setDialogTitle("Borrar partida");
        
        FileNameExtensionFilter filtroArchivosGSF=new FileNameExtensionFilter("Game Save File (.gsf)","gsf");
        selectorPartida.setFileFilter(filtroArchivosGSF);
        
        int botonPresionado=selectorPartida.showOpenDialog(null);
        
        if(botonPresionado==JFileChooser.APPROVE_OPTION){
        
            selectorPartida.getSelectedFile().delete();
            
            JOptionPane.showMessageDialog(null, "Partida borrada con éxito", "Borrar partida", JOptionPane.INFORMATION_MESSAGE);
        
        }
        else{
        
            JOptionPane.showMessageDialog(null, "No borraste una partida", "Borrado cancelado", JOptionPane.OK_OPTION);
        
        }
    
    }
    
}
