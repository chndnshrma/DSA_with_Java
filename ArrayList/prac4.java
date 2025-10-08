import java.util.*;

class prac4 {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int a[] = new int[n];
        
        for (int i = 0; i < n; i++) {
            a[i] = s.nextInt();
        }
        
        getEvenOdd(a);
        
        s.close();
    }

    public static void getEvenOdd(int a[]) {
        ArrayList<Integer> even = new ArrayList<>();
        ArrayList<Integer> odd = new ArrayList<>();

        
        for (int num : a) {
            if (num % 2 == 0) {
                even.add(num);
            } else {
                odd.add(num);
            }
        }

        for (int i = 0; i < odd.size(); i++) {
            System.out.print(odd.get(i));
            if (i < odd.size() - 1) System.out.print(" ");
        }
        System.out.println(); 

        for (int i = 0; i < even.size(); i++) {
            System.out.print(even.get(i));
            if (i < even.size() - 1) System.out.print(" ");
        }
        System.out.println(); 
    }
}