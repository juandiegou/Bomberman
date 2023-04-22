package tools;

public class Conversor {

    public Conversor(){}

    public char[][] TransformFile(String path){
        
   
     

        String[] rows = path.split("\n");

        int numRows = rows.length;
        int numCols = rows[0].split(",").length;

        char[][] matrix = new char[numRows][numCols];

        for (int i = 0; i < numRows; i++) {

            String[] cols = rows[i].split(",");

            for (int j = 0; j < numCols; j++) {
                matrix[i][j] = cols[j].charAt(0);
            }
        }
        
        return matrix;
    }
}
    

