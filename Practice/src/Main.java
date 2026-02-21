import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {3,7,2,9,4};
        String str = "Chandan";

        System.out.println(printMax(arr));
        System.out.println(printMin(arr));
        System.out.println(Arrays.toString(revArray(arr)));

        revStr(str);

    }
    //1.to find the max element of array
    public static int printMax(int[] arr){
        int max = arr[0];
        for(int i = 1; i<arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }
    //2.to find the min element of array
    public static int printMin(int[] arr){
        int min = arr[0];
        for(int i = 1; i<arr.length; i++){
            if(arr[i] < min){
                min = arr[i];
            }
        }
        return min;
    }
    //3.to reverse without extra space element of array
    public static int[] revArray(int[] arr){
        int start = 0;
        int end = arr.length-1;
        while(start<end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        return arr;
    }
    //4. Reverse a string manually
    public static void revStr(String str){
        char[] chr = str.toCharArray();
        int start = 0;
        int end = chr.length -1;
        while(start<end){
            char temp = chr[start];
            chr[start] = chr[end];
            chr[end] = temp;

            start++;
            end--;
        }
        String reversed = new String(chr);
        System.out.println(reversed);
    }
}