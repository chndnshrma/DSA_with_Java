import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String str = "Meeeeeooooooowww";
        System.out.println(reverseString(str));

    }
    public static String reverseString(String str) {
        char[] arr = str.toCharArray();
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[temp];
            arr[right] = temp;

            left++;
            right--;
        }
        return Arrays.toString(arr);

//        String result = "";
//        for (int i = str.length() - 1; i >= 0; i--) {
//            result = result + str.charAt(i);
//        }
//        return  result;
    }
}
