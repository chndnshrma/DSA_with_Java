import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;

public class PreviousGreatest {
    public static void main(String[] args){
        ArrayDeque<Integer> stack = new ArrayDeque<>(Arrays.asList(15,10,18,12,4,6,2,8));

        Iterator<Integer> iterator = stack.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }
}
