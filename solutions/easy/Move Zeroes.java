// Title: Move Zeroes
            // Difficulty: Easy
            // Language: Java
            // Link: https://leetcode.com/problems/move-zeroes/

            }
                break;
        }
        if (j == -1) return;

        for (int i = j+1; i<n; i++) {
            if (nums[i] != 0) {
                swap(nums, i, j);
                j++;
            }
