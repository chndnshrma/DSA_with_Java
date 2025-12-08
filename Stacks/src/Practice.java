import java.util.Stack;

public class Practice {
    public static void main(String[] args) {
    String str = "toramaikechodoaaaiiyeee";
    removeConsecutiveDuplicates(str);
    }

    public static String removeConsecutiveDuplicates(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(s.toCharArray());


    }
}
