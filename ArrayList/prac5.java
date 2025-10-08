import java.util.*;

public class prac5 {
    public static void main(String args[]) {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        ListIterator<Integer> it = list.listIterator(list.size());

        while (it.hasPrevious()) {
            int x = it.previous();
            it.set(x * 2); // Modify each element
        }

        System.out.println(list);
    }
}