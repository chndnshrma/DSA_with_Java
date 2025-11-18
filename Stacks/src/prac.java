import java.util.*;

public class prac {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        int max = Math.max(a, Math.max(b, c));
        int min = Math.min(a, Math.min(b, c));

        if(Math.abs(max-min) >= 10){
            System.out.println("check again");
        }else{
            int median = findMedian(a, b, c);
            System.out.println("final " + median);
        }
        sc.close();
    }
    public static int findMedian(int a, int b, int c) {
        if((a >= b && a <= c) || (a >= c && a <= b)){
            return a;
        }else if((b >= a && b <= c) || (b >= c && b <= a)){
            return b;
        }else{
            return c;
        }
    }
}
