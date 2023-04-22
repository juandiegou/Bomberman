package tools;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Reader {

    public Reader(){}


    public String readFile(String path ){
        String data="";
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (Exception ex) {
            System.out.println("Error al leer el archivo: " + ex.getMessage());
        }
        return data;
    }
    
}

