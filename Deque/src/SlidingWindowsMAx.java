import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowsMAx {
    public static void main(String[] args){
        int[] arr ={20,25,12,4,15,41,12};
        int k = 3;
        System.out.println(maxOfSubarrays(arr,k));
    }
    public static ArrayList<Integer> maxOfSubarrays(int[] arr, int k) {
        // code here
        ArrayList<Integer> ans = new ArrayList<>();
        Deque<Integer> dq = new ArrayDeque<>();
        int n = arr.length;

        for(int i = 0; i < n; i++){
            while(!dq.isEmpty() && arr[dq.peekLast()] <= arr[i]){
                dq.removeLast();
            }
            dq.addLast(i);

            if(dq.peekFirst() == i-k){
                dq.removeFirst();
            }
            if(i >= k-1){
                ans.add(arr[dq.peekFirst()]);
            }
        }
        return ans;
    }
}
