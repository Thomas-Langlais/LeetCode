import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BTLevelOrderTrav {
    
    public static void main(String[] args) {
        System.out.println(new Solution().levelOrder(
            new TreeNode(
                3, 
                null,
                new TreeNode(
                    20,
                    null,
                    new TreeNode(
                        7,
                        new TreeNode(
                            99,
                            new TreeNode(
                                11
                            ),
                            null
                        ),
                        new TreeNode(
                            9,
                            null,
                            new TreeNode(
                                15,
                                null,
                                new TreeNode(
                                    1
                                )
                            )
                        )
                    )
                ))
            )
        );
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        
        List<List<Integer>> result = new LinkedList<>();
        double level = 0;

        if (root == null) {
            return result;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            // set the items
            List<Integer> levelitems = new LinkedList<Integer>();
            List<TreeNode> poppedNodes = new LinkedList<>();
            // loop the current level elements
            for (double i = 0; i < Math.pow(2, level); i++) {
                if (!nodeQueue.isEmpty()) {
                    levelitems.add(nodeQueue.peek().val);
                    poppedNodes.add(nodeQueue.remove());
                } else {
                    break;
                }
            }
            // append the popped elements children into the queue
            for (int i = 0; i < poppedNodes.size(); i++) {
                if (poppedNodes.get(i).left != null) {
                    nodeQueue.add(poppedNodes.get(i).left);
                }
                if (poppedNodes.get(i).right != null) {
                    nodeQueue.add(poppedNodes.get(i).right);
                }
            }
            result.add(levelitems);
            // increment to the level to adjust the loop for the dequer
            level++;
        }
        return result;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
