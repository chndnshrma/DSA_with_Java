// Title: Pascal's Triangle
            // Difficulty: Easy
            // Language: Java
            // Link: https://leetcode.com/problems/pascal's-triangle/

                value = value * (row - col) / (col + 1);
                ansRow.add((int) value);
            for (int col = 0; col <= row; col++) {
            long value = 1;
            }
            ans.add(ansRow);
        }
        return ans;
    }
}
