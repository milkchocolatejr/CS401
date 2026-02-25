// https://leetcode.com/problems/can-place-flowers
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int planted = 0;

        for(int i = 0; i < flowerbed.length; i++){
            //Trading memory for readability
            boolean nextCondition = (i == flowerbed.length - 1) || (flowerbed[i + 1] == 0);
            boolean currentCondition = flowerbed[i] == 0;
            boolean lastCondition = (i == 0) || (flowerbed[i - 1] == 0);

            //If all conditions are met, plant the flower and set signals.
            if(nextCondition && currentCondition && lastCondition){
                flowerbed[i] = 1; 
                planted++;

                //If we planted a flower, we can't put one in the next slot.
                // This line's impact : Beats 5% --> Beats 98%
                i++; 
            }
        }
        
        return planted >= n;
    }
}
