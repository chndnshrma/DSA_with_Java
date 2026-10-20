import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
	public static void main(String[] args) {
		int r = 0;
        int c = 2;
        System.out.println(NcR(c-1, r-1));
	}
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        return result;
    }
    public static long NcR(int n, int r) {
        long res = 1;
        for (int i = 0; i< r; i++) {
            res = res * (n-i) ;
            res = res/(i+1);
        }
        
        return res;
    }
    public static int[] generateRow(int row) {
        if (row <= 0) {
            return new int[0];
        }

        long ans = 1;
        int[] ansRow = new int[row];
        ansRow[0] = 1;

        for (int col = 1; col < row; col++) {
            ans = ans * (row - col);
            ans = ans / (col);
            ansRow[col] = Math.toIntExact(ans);
        }
        return ansRow;
    }

}
