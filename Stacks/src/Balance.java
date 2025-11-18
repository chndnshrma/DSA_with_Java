//This program check is the string has balanced parenthesis
import java.util.ArrayDeque;
import java.util.Deque;

public class Balance {
    public static void main(String[] args) {
    String str = "({}[((]))";
        System.out.println(isBalanced(str));
    }
    public static boolean isBalanced(String str){
        Deque<Character> stack = new ArrayDeque<Character>();

        for(int i = 0; i<str.length(); i++){
            char x = str.charAt(i);
            if(x == '(' || x == '{' || x == '['){
                stack.push(x);
            }else {
                if (stack.isEmpty()) {
                    return false;
                }else if(!isMatching(stack.peek(), x)) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        return (stack.isEmpty() == true);
    }
   public static boolean isMatching(char a, char b){
        return (
                a == '(' && b == ')' || a=='[' && b == ']'  || a=='{' && b == '}'
                );
    }
}
