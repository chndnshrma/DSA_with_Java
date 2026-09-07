class patterns {
    public static void main(String[] args) {
        int[] arr = {15, 9, 20, 3, 56, 42, 12};
        int n = arr.length;

        for (Integer e : arr) {
        System.out.print(e + " ");
        }
       System.out.println();

       System.out.println("Second Largest Element: " + secondLargest(arr));
    }

    static int secondLargest(int[] arr) {
        if ( arr == null || arr.length < 2) {
            return -1;
        }
        int largestElement = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int value : arr) {
            if (value > largestElement) {
                secondLargest = largestElement;
                largestElement = value;
            } else if (value > secondLargest && value != largestElement) {
                secondLargest = value;
            }
        }
        return secondLargest;
    }
}