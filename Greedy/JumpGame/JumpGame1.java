public class JumpGame1 {

    interface Solution {
        public boolean canJump(int[] nums);
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
        */
        public boolean canJump(int[] nums) {
            int spanningSize = 0;

            /* we loop through the position vectors, updating the spanningSize
            if the distance is greater. */
            for (int i = 0;
                i < nums.length - 1 && // we want the subset, so the bare minimum is not including the last position
                spanningSize >= i && // if the spanning size cannot grow anymore, we exit
                spanningSize < nums.length - 1; // if the spanning size passes the last position, we exit
                i++) {
                if (i + nums[i] > spanningSize) {
                    // set the new spanning size if 
                    spanningSize = i + nums[i];
                }
            }
            
            // once the loop terminates, we can conclude whether we can jump or not
            return spanningSize >= nums.length - 1;
        }
    }
    
    public static void main(String[] args) {
        Solution sol = new JumpGame1.SolutionV1();
        
        System.out.print("finds a solution ");
        System.out.println(
            sol.canJump(new int[] {2,3,1,1,4})
        );
        System.out.print("does not find a solution ");
        System.out.println(
            sol.canJump(new int[] {3,2,1,0,4})
        );
        System.out.print("finds a solution, but greedy is not optimal ");
        System.out.println(
            sol.canJump(new int[] {3,2,2,0,1})
        );
    }
}