class patterns {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,0};
        for (int e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
        rotateArray(arr, 3);

        for (int e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();

    }
    public static int[] rotateArray(int[] arr, int d) {
        int n = arr.length;
        d = d % n;
        int[] temp = new int[d];
        
        for (int i = 0; i < d; i++) {
            temp[i] = arr[i];
        }

        for (int i = d; i < n ; i++) {
            arr[i-d] = arr[i];
        }

        for (int i = n-d; i < n; i++) {
            arr[i] = temp[i - (n-d)];
        }

        return arr;
    }
}