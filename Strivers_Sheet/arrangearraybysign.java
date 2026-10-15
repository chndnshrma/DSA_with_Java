package Strivers_Sheet;
import java.util.ArrayList;
import java.util.List;

public class arrangearraybysign {
    public static void main(String[] args) {
        int[] arr = {3, 1, -2, -5, 2, -4,2,3,1,2};
        System.out.println(rearrangeBySign(arr));
    }

    public static List<Integer> rearrangeBySign(int[] nums) {
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();

        for (int num : nums) {
            if (num > 0) {
                pos.add(num);
            } else {
                neg.add(num);
            }
        }

        int max = Math.max(pos.size(), neg.size());
        for (int i = 0; i < max; i++) {
            if (i < pos.size()) {
                ans.add(pos.get(i));
            }
            if (i < neg.size()) {
                ans.add(neg.get(i));
            }
        }

        return ans;
    }
}
