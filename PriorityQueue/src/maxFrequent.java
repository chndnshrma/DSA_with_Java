import java.util.PriorityQueue;
import java.util.Queue;

public class maxFrequent {
    public static void main(String[] args){
        int[] arr = {10,20,13,41,28,45};
        printK(arr, 2);
    }
    public static void printK(int[] arr, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < k; i++){
            pq.add(arr[i]);
        }
        for(int i = k; i < arr.length; i++){
            if(arr[i] > pq.peek()){
                pq.poll();
                pq.add(arr[i]);
            }
        }
        for(int i = 0; i < k; i++){
            System.out.println(pq.poll());
        }
    }
}

