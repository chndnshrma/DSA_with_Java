import java.util.Arrays;
import java.util.HashSet;

class patterns {
    public static void main(String[] args) {
        int[] arr = {1,1,2,2,3,3,4,4,5,5,5};
        for (int e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();

        System.out.println(removeDuplicate(arr));

    }
    public static int removeDuplicate(int[] arr) {
        int i = 0;
        for ( int j = 1; j < arr.length; j++){
            if (arr[j] != arr[i]) {
                i++;
                arr[i] = arr[j];
            }
        }
        return i + 1;
    }
}