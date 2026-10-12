import java.util.Arrays;

public class arrangearraybysign {
    public static void main(String[] args) {
        int[] arr = {3,1,-2,-5,2,-4};
        System.out.println(Arrays.toString(rearrangeBySign(arr)));

    }
    public static int[] rearrangeBySign(int[] nums) {
        int n = nums.length;
        int posIndex = 0;
        int negIndex = 1;
        int[] ans = new int[n];

        for (int i = 0; i< n; i++) {
            if (nums[i] < 0) {
                ans[negIndex] = nums[i];
                negIndex += 2;
            } else {
                ans[posIndex] = nums[i];
                posIndex += 2;
            }
        }
        return ans;
    }
}
