import java.util.Arrays;

public class maxconsecutiveones {
    public static void main(String[] args) {
        int[] nums = {3, 0, 1};
        System.out.println(missingNumber(nums));
    }
    public static int missingNumber(int[] arr) {
        for (int i = 0; i<arr.length; i++) {
            boolean found = false;
            for (int value : arr) {
                if (value == i) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                return i;
            }
        }
        return -1;


        // Arrays.sort(arr);
        // for (int i = 0; i<arr.length; i++) {
        //     if (arr[i] != i) {
        //         return i;
        //     }
        // }
        // return arr.length;
    }
}