package datasturctures.tree;

/**
 * Version 1.0
 * Created by lll on 2019-12-16.
 * Description
 * <pre>
 *   二叉树的算法题汇总
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class TreeInterview {


  public static class TreeNode {
    public int data;
    public TreeNode parent;
    public TreeNode leftChild;
    public TreeNode rightChild;

    public TreeNode(int data) {
      this.data = data;
    }

    @Override
    public String toString() {
      return "" + data;
    }
  }

  /**
   * 构建一个二叉查找树
   */
  public static class Tree {
    public TreeNode root;

    public Tree(TreeNode root) {
      this.root = root;
    }

    public void insertNode(int data) {
      TreeNode node = new TreeNode(data);
      if (root == null) {
        root = node;
      } else {
        TreeNode currentNode = root;
        TreeNode parentNode = null; //插入节点的父亲
        while (true) {
          parentNode = currentNode;
          if (currentNode.data < data) { //大于跟节点，往右边insert
            currentNode = currentNode.rightChild;
            if (currentNode == null) { //找到了左子节点为空
              parentNode.rightChild = node;
              return;
            }
          } else { // 小于跟节点，往左边插入
            currentNode = currentNode.leftChild;
            if (currentNode == null) {
              parentNode.leftChild = node;
              return;
            }
          }
        }
      }
    }
  }


  public void midDumpTree(TreeNode treeNode) {
    if (treeNode != null) {
      midDumpTree(treeNode.leftChild);
      System.out.print("  " + treeNode);
      midDumpTree(treeNode.rightChild);
    }
  }


  public Tree createTestTress() {
    Tree tree = new Tree(null);
    tree.insertNode(7);
    tree.insertNode(10);
    tree.insertNode(11);
    tree.insertNode(5);
    tree.insertNode(4);
    tree.insertNode(12);
    tree.insertNode(6);
    tree.insertNode(9);
    return tree;
  }


  public static void main(String[] args) {

    TreeInterview interview = new TreeInterview();
    Tree tree = interview.createTestTress();
    interview.midDumpTree(tree.root);
  }


}
