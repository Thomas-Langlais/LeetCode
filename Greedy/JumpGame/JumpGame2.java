public class JumpGame2 {

    interface Solution {
        public int jump(int[] nums);
    }

    static class SolutionV1 implements Solution {

        /*
            each index in nums is a position, and contains stateful information
            that tells us how many positions we can move from there.

            A way we can solve this by determining that there is a
            span of vectors smaller than the total set
                aka: we can make it to the last index without using it

            Constraints:
                1 <= nums.length <= 3 * 104
                0 <= nums[i] <= 105
                There is always a path to reach the end
        */
        public int jump(int[] nums) {
            int spanningSize = 0;
            int minHops = 0;

            /* we loop through the position vectors, updating the spanningSize
            if the distance is greater. */
            for (int i = 0;
                i < nums.length - 1 && // we want the subset, so the bare minimum is not including the last position
                spanningSize >= i && // if the spanning size cannot grow anymore, we exit
                spanningSize < nums.length - 1; // if the spanning size passes the last position, we exit
                i++) {
                // if we find a spanning size that is large than then
                // update the size and do some calcs on picking a new hop
                // locations
                if (i + nums[i] > spanningSize) {
                    // set the new spanning size if 
                    spanningSize = i + nums[i];
                    // update the min hops
                    // calculate the jump location to maximize
                    // the the next spanning distance 
                    i = maxHop(i + 1, i + nums[i], nums) - 1;
                    minHops += 1;
                }
            }
            
            // once the loop terminates, return the min number of jumps
            return minHops;
        }

        int maxHop(int position, int end, int[] state) {
            int hop = -1;
            int span = -1;
            
            // iterate over the positions and choose the hop location that will max
            // the next spanning distance
            for (; position <= end && position < state.length; position++) {
                // choose the hop that will cover the most ground
                if (span < position + state[position]) {
                    span = position + state[position];
                    hop = position;
                }
            }

            return hop;
        }
    }
    
    public static void main(String[] args) {
        Solution sol = new JumpGame2.SolutionV1();
        
        System.out.print("finds a min jumps of ");
        System.out.println(
            sol.jump(new int[] {2,3,1,1,4})
        );
        System.out.print("finds a min jumps of ");
        System.out.println(
            sol.jump(new int[] {3,2,2,0,1})
        );
        System.out.print("finds a min jumps of when the span changes every iteration ");
        System.out.println(
            sol.jump(new int[] {3,1,3,1,1,2})
        );
    }
}