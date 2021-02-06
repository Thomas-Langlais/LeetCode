import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        
    }
}

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
    
    /*
    Similar solution to the binary tree,
    instead of doing an if statement, we iterate over
    the children nodes
    */
    public List<List<Integer>> levelOrder(Node root) {
        
        List<List<Integer>> result = new LinkedList<>();
        // we now don't need the level, since we use the queue size state
        // to determine our exit point
        // double level = 0;

        // exit if there is no root node to traverse
        if (root == null) {
            return result;
        }

        // setup the queue to pop nodes out with the root to start
        Queue<Node> nodeQueue = new LinkedList<Node>();
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
                Node node = nodeQueue.poll();
                // add the node value to the level
                levelitems.add(node.val);
                
                // break the loop if there are no children
                if (node.children == null) break;
                
                // append the node's child elements into the queue
                // by iterating over the childrens nodes
                for (int i = 0; i < node.children.size(); i++) {
                    nodeQueue.add(node.children.get(i));
                }
            }
            
            result.add(levelitems);
        }
        return result;
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}