import java.util.Stack;

public class Practice {
    public static void main(String[] args) {
    String str = "toramaikechodoaaaiiyeee";
    System.out.println(removeConsecutiveDuplicates(str));
    }
    public static String removeConsecutiveDuplicates(String s) {
        // Your code here
        if (s == null || s.isEmpty()) {
            return "";
        }
        Deq
        int n = s.length();

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            char currentChar = s.charAt(i);

            if (st.isEmpty() || st.peek() != currentChar) {
                st.push(currentChar);
            }
        }

        StringBuilder ans = new StringBuilder();
        while (!st.isEmpty()) {
            ans.append(st.pop());
        }
        return ans.reverse().toString();
    }
}
