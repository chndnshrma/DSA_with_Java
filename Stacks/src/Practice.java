import java.util.Stack;

public class Practice {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        Stack<Integer> s = new Stack<>();
        for (int j : arr) {
            s.push(j);
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(s.pop() + " ");
        }
    }
}
