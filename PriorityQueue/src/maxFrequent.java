import java.util.PriorityQueue;

public class maxFrequent {
    public static void main(String[] args){
        int[] arr = {10,5,20,5,10,10,30};
        kmaxFrequent(arr, );
    }
    public static void kmaxFrequent(int[] arr, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0;i < k;i++){
            pq.add(arr[i]);
        }
    }
}
