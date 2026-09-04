public class bubble_sort {
    public static void main(String[] args) {
        int[] arr = {15, 9, 20, 3, 56, 42, 12};
        int n = arr.length;

        for (Integer e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();

        bubbleSort(arr, n);
        for (Integer e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
    }
    public static void bubbleSort(int[] arr, int n) {
        for (int i = n-1; i >= 0; i--) {
            for (int j = 0; j <= i-1; j++ ) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}

