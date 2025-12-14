import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        int arr[] = {20, 40, 30, 10, 60};
        int k = 3;
        printMax(arr, k);
    }
    static void printMax(int arr[], int k){
        Deque<Integer> dq = new LinkedList<Integer>();

        // Process first K elements
        for (int i = 0; i < k; i++){
            while (!dq.isEmpty() && arr[i] >= arr[dq.peekLast()])
                dq.removeLast();

            dq.addLast(i);
        }

        // Process remaining elements
        for (int i = k; i < arr.length; i++) {
            System.out.print(arr[dq.peek()] + " ");

            // Remove elements that are out of this window
            while (!dq.isEmpty() && dq.peek() <= i - k)
                dq.removeFirst();

            // Remove smaller elements from the back
            while (!dq.isEmpty() && arr[i] >= arr[dq.peekLast()])
                dq.removeLast();

            dq.addLast(i);
        }

        // Print max of last window
        System.out.print(arr[dq.peek()] + " ");
    }
}