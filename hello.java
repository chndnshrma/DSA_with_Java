public class hello {
    public static void main(String[] args) {
        int[] arr = {1,1,0,1,1,1};
        System.out.println(majorityElement(arr));
    }
    public static int majorityElement(int[] nums) {
        int threshold = nums.length/2;

        for (int candidate : nums) {
            int frequency = 0;
            for (int value : nums) {
                if (value == candidate) {
                    frequency++;
                }
            }if (frequency > threshold) {
                return candidate;
            }
        }
        return  -1;
    }
}
