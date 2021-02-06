import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BTLevelOrderTrav {
    
    public static void main(String[] args) {
        System.out.println(new SolutionV2().levelOrder(
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
class SolutionV1 {
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        
        List<List<Integer>> result = new LinkedList<>();
        double level = 0;

        if (root == null) {
            return result;
        }

        // setup the queue to pop nodes out with the root to start
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.add(root);
        
        // loop over the queue until we have traversed the tree
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

class SolutionV2 {
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        
        List<List<Integer>> result = new LinkedList<>();
        // we now don't need the level, since we use the queue size state
        // to determine our exit point
        // double level = 0;

        // exit if there is no root node to traverse
        if (root == null) {
            return result;
        }

        // setup the queue to pop nodes out with the root to start
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.add(root);
        
        // loop over the queue until we have traversed the tree
        while (!nodeQueue.isEmpty()) {

            // set the items
            List<Integer> levelitems = new LinkedList<Integer>();
            // check the current size of the queue, we will use this as our
            // exit condition. we want to know the state of how many nodes are
            // in the queue, so we can immediately insert the children in the
            // queue so we can reduce the memory used by removing V1's poppedNOdes list
            int currentQueueState = nodeQueue.size();
            
            // loop the current level elements
            for (; currentQueueState > 0; currentQueueState--) {
                TreeNode node = nodeQueue.poll();
                // add the node value to the level
                levelitems.add(node.val);
                // append the node's child elements into the queue
                // if they exist
                if (node.left != null) {
                    nodeQueue.add(node.left);
                }
                if (node.right != null) {
                    nodeQueue.add(node.right);
                }
            }
            
            result.add(levelitems);
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
