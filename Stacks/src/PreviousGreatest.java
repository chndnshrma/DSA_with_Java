import java.util.*;

public class PreviousGreatest {
    public static void main(String[] args){
        int[] stack = {20,30,10,5,15};
        prevGreater(stack);
    }
    public static void prevGreater(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(arr[0]);
        int pg = -1;
        System.out.println(pg + " ");
        for(int i = 1; i < arr.length; i++){
            while(stack.isEmpty() == false && stack.peek() <= arr[i]){
                stack.pop();
            }
            pg = stack.isEmpty() ? -1 : stack.peek();
            System.out.println(pg + " ");
            stack.push(arr[i]);
        }
    }
}
