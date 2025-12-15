import java.util.*;

public class PurchasingMaxSum {
    public static void main(String[] args) {
        List<Integer> al = Arrays.asList(1, 12, 5, 111, 200);
        System.out.println(purchaseMax(al, 10));
    }

    public static int purchaseMax(List<Integer> al, int sum) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(al);
        int res = 0;
        while(sum >= 0 && !pq.isEmpty() && pq.peek() <= sum){
            sum = sum - pq.poll();
            res++;
        }
        return res;
    }
}
