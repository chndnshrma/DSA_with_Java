public class insertion_sort {
    public static void main(String[] args) {
        int[] arr = {15, 9, 20, 3, 56, 42, 12};
        int n = arr.length;

        for (Integer e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();

        insertionSort(arr, n);
        for (Integer e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
    }
    public static void insertionSort(int[] arr, int n) {
        for (int i = 0; i <= n-1; i++) {
            int j = i;
            while (j > 0 && arr[j-1] > arr[j]) {
                int temp = arr[j-1];
                arr[j-1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
    }
}
