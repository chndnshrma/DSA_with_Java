import java.util.*;
import java.util.function.Predicate;

public class practice1 {
    public static void main(String[] args){
        List<Integer> al = new ArrayList<>(Arrays.asList(10,29,30,47,50));
        GFG.printCond(al, x -> x %2 == 0);
    }
}
class GFG {
    public static void printCond(Collection<Integer> C, Predicate<Integer> p){
        for(Integer x: C){
            if(p.test(x)){
                System.out.println(x + " ");
            }
        }
    }
}