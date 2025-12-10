import java.util.*;

public class Main {
    public static void main(String[] args) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(10);
        q.offer(20);
        q.offer(30);
        q.offer(40);
        q.offer(50);

        System.out.println(removeElements(q,3));
    }
    public static Queue<Integer> removeElements(Queue<Integer> q, int k) {

        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < k; i++) {
            stack.push(q.poll());
        }

        while(!stack.isEmpty()) {
            q.offer(stack.pop());
        }
        for(int i = 0; i < q.size() - k; i++){
            q.offer(q.poll());
        }
        return q;
    }
}