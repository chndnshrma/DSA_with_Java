import java.util.PriorityQueue;
import java.util.Queue;

public class maxFrequent {
    public static void main(String[] args) {
        int[] arr = {13,20,32,54,67,43,90};
        kLargestElement(arr, 3);
    }
    public static void kLargestElement(int[] arr, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0;i<k; i++){
            pq.add(i);
        }
        for(int i = k; i<arr.length; i++){
            if(arr[i] > pq.peek()){
                pq.poll();
                pq.add(arr[i]);
            }
        }
        for(int i = 0; i<k; i++){
            System.out.print(pq.poll()+ " ");
        }

    }
}

