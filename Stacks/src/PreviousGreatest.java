import java.util.*;

public class PreviousGreatest {
    public static void main(String[] args){
        int[] stack = {15,10,18,12,4,6,2,8};
        prevGreater(stack);
    }
    public static void prevGreater(int[] arr) {
        for(int i = 0; i<arr.length; i++){
            int pg = -1;
            for(int j = i-1; j >= 0;j--) {
                if (arr[j] > arr[i]){
                    pg = arr[j];
                    break;
                }
            }
            System.out.print(pg + " ");
        }
    }
}
