package tools;

public class Conversor {

    public Conversor(){}

    public String[][] TransformFile(String path){
        
   
     

        String[] rows = path.split("\n");

        int numRows = rows.length;
        int numCols = rows[0].split(",").length;

        String[][] matrix = new String[numCols][numRows];

        for (int i = 0; i < numRows; i++) {

            String[] cols = rows[i].split(",");

            for (int j = 0; j < numCols; j++) {
                matrix[j][i] = cols[j];
            }
        }
        
        return matrix;
    }
}
    

