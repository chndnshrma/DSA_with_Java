import java.util.*;

public class largestElements {
    public static void main(String[] args){
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        larEle(arr, 3);
    }
    public static void larEle(int[] arr, int k){
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
            System.out.print(pq.poll() + " ");
        }
    }
}

