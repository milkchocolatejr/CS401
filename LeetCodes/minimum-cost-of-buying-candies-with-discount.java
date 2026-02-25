class Solution {
    public int minimumCost(int[] cost) {
        int minCost = 0;
        int disCounter = 1;

        //Sort the collection
        Arrays.sort(cost);

        //Buy two most expensive, get the best value for the coupon
        for(int i = cost.length - 1; i > -1; i--){
            if (disCounter == 4){ // Enforce 1,2,3 cycle
                disCounter = 1;
            }

            if(disCounter == 3){ // 3rd item is couponed
                disCounter++;
                continue;
            }

            minCost += cost[i]; //Add the cost of the purchased items
            disCounter++;
        }

        return minCost;
    }
}
