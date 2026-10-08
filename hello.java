import java.util.ArrayList;
import java.util.List;

public class hello {
    public static void main(String[] args) {
        int[] arr = {1,2,5,2,3,4};
        System.out.println(leaderElement(arr));
    }
    public static List<Integer> leaderElement(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i<nums.length; i++) {
            boolean leader = true;
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    leader = false;
                    break;
                }
            }
            if (leader == true) {
                ans.add(nums[i]);
            }
        }
        return ans;
    }
}
