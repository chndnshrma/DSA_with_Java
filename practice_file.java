package ArrayList;
import java.util.*;
public class practice_file{
    public static void main(String[] args){
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(10);   
        arr.add(12);  
        arr.add(13);  
        arr.add(11);  
        arr.add(18);  
        arr.add(16);  

        System.out.println(findEvenNumbers(arr));  
        System.out.println(findOddNumbers(arr));

    }
    public static LinkedList<Integer> findEvenNumbers(ArrayList<Integer> arr){
        LinkedList<Integer> even = new LinkedList<>();
        for(Integer x: arr){
            if(x%2==0){
                even.add(x);
            }
        }
        return even;
    }
    public static LinkedList<Integer> findOddNumbers(ArrayList<Integer> arr){
        LinkedList<Integer> odd = new LinkedList<>();
        for(Integer x: arr){
            if(x%2 !=0 ){
                odd.add(x);
            }
        }
        return odd;
    }
} 