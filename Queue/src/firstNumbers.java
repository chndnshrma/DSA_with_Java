import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class firstNumbers {
    public static void main(String[] args) {
        int n = 8;
        printFirstN(n);
    }
    public static void printFirstN(int n) {
        Queue<String> q = new LinkedList<>();
        q.add("5");
        q.add("6");
        for (int i = 0; i < n; i++) {
            String curr = q.poll();
            System.out.print(curr + " ");
            q.add(curr + "5");
            q.add(curr + "6");
        }
    }
}
