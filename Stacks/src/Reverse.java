import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Reverse {
    public static void main(String[] args) {
        List<Integer> lst = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        myRev(lst);
    }
    public static void myRev(List<Integer> lst){
        Stack<Integer> s = new Stack<>();
        for(Integer x: lst){
            s.push(x);
        }
        for(int i = 0; i<lst.size();i++){
            lst.set(i, s.pop());
        }
        System.out.println(lst.toString());
    }
}