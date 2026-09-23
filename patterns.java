class patterns {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
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

class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
            return;
        }
        k = k % n;
        int[] temp = new int[k];

        for (int i = 0; i < k; i++) {
            temp[i] = nums[n - k + i];
        }
        for (int i = n - k - 1; i >= 0; i--) {
            nums[i + k] = nums[i];
        }
        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }
    }
}