// https://leetcode.com/problems/minimum-operations-to-make-the-array-increasing/
class Solution {
    public int minOperations(int[] nums) {
        int operations = 0;
        for(int i = 1; i < nums.length; i ++){
            if(nums[i] <= nums[i - 1]){
                int diff = nums[i - 1] - nums[i] + 1;
                operations += diff;
                nums[i] += diff;
            }
        }
        return operations;
    }
}
