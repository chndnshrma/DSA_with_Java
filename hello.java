import java.util.ArrayList;
import java.util.List;

public class hello {
    public static void main(String[] args) {
        int[] arr = {10,22,12,3,0,6};
        System.out.println(leaderElement(arr));
    }
    public static List<Integer> leaderElement(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int max = Integer.MIN_VALUE;

        for (int i = nums.length-1; i > 0 ; i--) {
            if (nums[i] > max) {
                max = nums[i];
                ans.add(max);
            }
        }
        return ans;
    }
}
