import java.util.ArrayDeque;
import java.util.Deque;

public class Span {
    public static void main(String[] args){
        int[] arr = {60,10,20,40,35,30,5,70,55};
       Deque<Integer> stack = new ArrayDeque<Integer>();
       stack.push(0);
       int span = 1;
       System.out.print(span + " ");

       for(int i = 0; i<arr.length;i++){
           while(stack.isEmpty() == false && arr[stack.peek()] <= arr[i]){
                stack.pop();
           }
           span = (stack.isEmpty() ? (i+i) : (i-stack.peek()));
           System.out.print(span + " ");
           stack.push(i);
       }
    }
}
