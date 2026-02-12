import java.util.PriorityQueue;
import java.util.Queue;

public class maxFrequent {
    public static void main(String[] args){
        Queue<Integer> q = new PriorityQueue<>();
        q.add(1);
        q.add(3);
        q.add(3);
        q.add(5);
        System.out.println(q);
    }
}

