import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        //Main Heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(1);
        pq.add(2);
        pq.add(3);

        System.out.println(pq.peek());
        System.out.println(pq.poll());
        System.out.println(pq.peek() + "\n");

        //Hash Heap
        PriorityQueue<Integer> pqu = new PriorityQueue<>(Collections.reverseOrder());
        pqu.add(1);
        pqu.add(2);
        pqu.add(3);

        System.out.println(pqu.peek());
        System.out.println(pqu.poll());
        System.out.println(pqu.peek());


    }
}