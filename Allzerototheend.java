import java.util.ArrayList;
import java.util.List;

class Allzerototheend {
    public static void main(String[] args) {
        int[] arr = {1,0,3,0,5,0,7};
        for (int e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
        moveZeroToEnd(arr);

        for (int e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
    }
    public static int[] moveZeroToEnd(int[] arr) {
        // if (arr == null || arr.length == 0) return;

        int n = arr.length;
        int nz = 0;

        for (int e : arr) {
            if (e != 0) {
                nz++;
            }
        }
        
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i<n; i++) {
            if (arr[i] != 0) {
                temp.add(arr[i]);
            }
        }

        for (int i = 0; i < temp.size(); i++) {
            arr[i] = temp.get(i);
        }
        for (int i = nz; i<n; i++) {
            arr[i] = 0;
        }
        return arr;
    }
}