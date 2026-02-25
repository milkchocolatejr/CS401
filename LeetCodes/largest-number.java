// https://leetcode.com/problems/largest-number/
class Solution {
    public String largestNumber(int[] nums) {
        int[] soln = new int[nums.length];

        for (int solIndex = 0; solIndex < nums.length; solIndex++) {

            int bestValueIndex = -1;
            String bestValueStr = null;

            for (int numIndex = 0; numIndex < nums.length; numIndex++) {

                if (nums[numIndex] == -1) continue;

                String currentStr = String.valueOf(nums[numIndex]);

                if (bestValueStr == null || 
                    isBetter(currentStr, bestValueStr)) {

                    bestValueIndex = numIndex;
                    bestValueStr = currentStr;
                }
            }

            soln[solIndex] = nums[bestValueIndex];
            nums[bestValueIndex] = -1; // mark as used
        }

        // Handle edge case: all zeros
        if (soln[0] == 0) return "0";

        StringBuilder sb = new StringBuilder();
        for (int num : soln) {
            sb.append(num);
        }

        return sb.toString();
    }

    private boolean isBetter(String newVal, String oldVal) {
        String option1 = newVal + oldVal;
        String option2 = oldVal + newVal;
        return option1.compareTo(option2) > 0;
    }
}
