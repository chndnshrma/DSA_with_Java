import java.util.ArrayList;
import java.util.List;

public class spiralMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, 
                         {4, 5, 6}, 
                         {7, 8, 9}};
        System.out.println(spiralOrder(matrix));

    }
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int row = matrix.length;
        int col = matrix[0].length;
        
        for (int[] row : matrix) {
            result.add(row);
        }

        
        return result;
    }
}
