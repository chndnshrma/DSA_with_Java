// Title: Rotate Array
            // Difficulty: Medium
            // Language: Java
            // Link: https://leetcode.com/problems/rotate-array/

            return;
        }
        k = k % n;
        int[] temp = new int[k];

class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
