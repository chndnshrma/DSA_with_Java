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
    //optimal solution
    public static int[] moveZeroToEnd(int[] arr) {
        int n = arr.length;
        int j = -1;
        for (int i = 0; i<n; i++) {
            if (arr[i] == 0) {
                j = i;
                break;
            }
        }
        if (j == -1) return arr;

        for (int i = j+1; i < n ;i++) {
            if (arr[i] != 0) {
                swap(arr, i, j);
                j++;
            }
        }
        return arr;
    }
    public static void swap(int[] arr, int start, int end) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }
    //brutefor solution
    // public static int[] moveZeroToEnd(int[] arr) {

    //     int n = arr.length;
    //     int nz = 0;

    //     for (int e : arr) {
    //         if (e != 0) {
    //             nz++;
    //         }
    //     }
        
    //     List<Integer> temp = new ArrayList<>();
    //     for (int i = 0; i<n; i++) {
    //         if (arr[i] != 0) {
    //             temp.add(arr[i]);
    //         }
    //     }

    //     for (int i = 0; i < temp.size(); i++) {
    //         arr[i] = temp.get(i);
    //     }
    //     for (int i = nz; i<n; i++) {
    //         arr[i] = 0;
    //     }
    //     return arr;
    // }
}