package Design;

import java.util.*;

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
class BSTIterator {

    private Stack<TreeNode> stack;

    private List<Integer> list = new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        return list.get(k-1);
    }
    private void dfs(TreeNode root){
        if(root == null){
            return;
        }
        dfs(root.left);
        list.add(root.val);
        dfs(root.right);

    }
    public BSTIterator(TreeNode root) {
        this.stack = new Stack<>();
        stack.isEmpty();
        pushAllLeft(root);
    }


    public int next() {

        TreeNode current = stack.pop();


        if (current.right != null) {
            pushAllLeft(current.right);
        }

        return current.val;
    }


    public boolean hasNext() {
        return !stack.isEmpty();
    }


    private void pushAllLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */