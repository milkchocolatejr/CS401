// https://leetcode.com/problems/shortest-path-in-binary-matrix/description/
class Solution {
    public int NUM_ROWS;
    public int NUM_COLS;
    public int MAX = Integer.MAX_VALUE;
    public boolean pathFound = false;

    public int[][] dirs = {{-1,-1}, {-1,0}, {-1, 1}, {0, -1},
                            {0, 1}, {1, -1}, {1, 0}, {1, 1} };

    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0] == 1){
            return -1;
        }

        NUM_ROWS = grid.length;
        NUM_COLS = grid[0].length;

        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        HashMap<Integer, Integer> distanceVals = new HashMap<>();

        for(int r = 0; r < NUM_ROWS; r++){
            for(int c = 0; c < NUM_COLS; c++){
                distanceVals.put(getKey(r,c), MAX);
            }
        }

        // Add first node to queue and visited set
        q.add(0);
        visited.add(0);
        distanceVals.put(getKey(0,0), 1);

        // Kickoff bfs
        while(!q.isEmpty()){
            //Process the node
            int key = q.poll();
            int[] loc = getLoc(key);

            //See if path has been found
            if(loc[0] == NUM_ROWS - 1 && loc[1] == NUM_COLS - 1){
                pathFound = true;
                break;
            }

            int currDist = distanceVals.get(getKey(loc[0], loc[1]));

            //Add valid neighbors
            for(int[] d : dirs){
                int moveCandidateKey = getKey(loc[0] + d[0], loc[1] + d[1]);

                if(isValidMove(new int[]{loc[0] + d[0], loc[1] + d[1]}, visited, grid)){
                    //Update Min distance
                    distanceVals.put(moveCandidateKey, Math.min(currDist + 1, distanceVals.get(moveCandidateKey)));
                
                    //Add to the visited set
                    visited.add(moveCandidateKey);

                    //Queue the neighbor
                    q.add(moveCandidateKey);
                }
            }
        }

        if(!pathFound){
            return -1;
        }

        return distanceVals.get(getKey(NUM_ROWS - 1, NUM_COLS - 1));
    }

    public boolean isValidMove(int[] loc, Set<Integer> visited, int[][] g){
        int r = loc[0];
        int c = loc[1];
        int key = getKey(loc[0], loc[1]);

        //Out of bounds check
        if(r < 0 || c < 0 || r >= NUM_ROWS || c >= NUM_COLS){
            return false;
        }
        //Not a 1 check
        boolean isZero = g[r][c] == 0;
        //Visited check
        boolean seen = visited.contains(key);       
        
        return isZero && !seen;
    }

    public int getKey(int r, int c){
        return (r * NUM_COLS) + c;
    }

    public int[] getLoc(int key){
        int r = key / NUM_COLS;
        int c = key % NUM_COLS;

        return new int[]{r,c};
    }
}
