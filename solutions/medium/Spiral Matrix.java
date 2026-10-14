// Title: Spiral Matrix
            // Difficulty: Medium
            // Language: Java
            // Link: https://leetcode.com/problems/spiral-matrix/

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
            }
                bottom--;
                }
                    result.add(matrix[bottom][i]);
