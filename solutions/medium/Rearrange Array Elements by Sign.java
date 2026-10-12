// Title: Rearrange Array Elements by Sign
            // Difficulty: Medium
            // Language: Java
            // Link: https://leetcode.com/problems/rearrange-array-elements-by-sign/

            if (nums[i] < 0) {
                ans[negIndex] = nums[i];
                negIndex += 2;
            } else {
        for (int i = 0; i<n; i++) {
                ans[posIndex] = nums[i];
                posIndex += 2;
            }
        }
        return ans;
