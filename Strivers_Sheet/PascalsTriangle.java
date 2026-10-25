import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
	public static void main(String[] args) {
		int n = 5;
        System.out.println(generate(n));
        
    }
    
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int row = 0; row < numRows; row++) {
            List<Integer> ansRow = new ArrayList<>();
            long value = 1;
            for (int col = 0; col <= row; col++) {
                ansRow.add((int) value);
                value = value * (row - col) / (col + 1);
            }
            ans.add(ansRow);

        }
        
        return ans;
    }
}
