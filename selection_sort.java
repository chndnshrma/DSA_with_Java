class selection_sort {
    public static void main(String[] args) {
       int[] arr = {15, 9, 20, 3, 56, 42, 12};
        int n = arr.length;

        for (Integer e : arr) {
        System.out.print(e + " ");
       }
       System.out.println();

       for (int i = 0; i <= n-2; i++) {
        int min = i;
        for ( int j = i; j <= n-1; j++) {
            if (arr[j] < arr[min]) {
                min = j;
            }
        }
        int temp = arr[min];
        arr[min] = arr[i];
        arr[i] = temp; 
       }
       for (Integer e : arr) {
        System.out.print(e + " ");
       }
       System.out.println();
    }
}