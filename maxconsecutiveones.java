import java.util.HashSet;
import java.util.Set;

public class maxconsecutiveones {
    public static void main(String[] args) {
        int[] arr1 = {6,3,5};
        int[] arr2 = {2,5,7};
        Set<Integer> merged = uniteArray(arr1, arr2);
        System.out.println(merged);
    }
    public static Set<Integer> uniteArray(int[] arr1, int[] arr2) {
        Set<Integer> result = new HashSet<>();

        for (Integer e : arr1) {
            result.add(e);
        }
        for (Integer e : arr2) {
            result.add(e);
        }

        return result;
    }
}