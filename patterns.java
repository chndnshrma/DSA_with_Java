class patterns {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        for (int e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
        rotateArray(arr);

        for (int e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();

    }
    public static int[] rotateArray(int[] arr) {
        int temp = arr[1];
        int n = arr.length;

        for (int i = 1; i<n; i++) {
            arr[i-1] = arr[i];
        }
        arr[n-1] = temp;
        return arr;
    }
}