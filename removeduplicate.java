public class removeduplicate {
    public static void main(String[] args) {
        int[] arr = {1,1,2,3,4,4,4,5,5,2,1,9};
        for (int e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
        System.out.println(removeDuplicateElements(arr));

        for (int e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
    }
    public static int removeDuplicateElements(int[] arr) {
        int i = 0;
        for (int j = 1; j<arr.length; j++) {
            if (arr[j] != arr[i]) {
                arr[i+1] = arr[j];
                i++;
            }
        }
        return i+1;
    }
}
