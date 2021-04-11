package datasturctures.tree;

import datasturctures.heap.Node;
import sun.security.krb5.internal.PAData;

import javax.swing.tree.TreeNode;
import java.util.Stack;

/**
 * Version 1.0
 * Created by lll on 2019-12-04.
 * Description
 * <pre>
 *   二叉查找树
 *
 *   1、左子节点 < 根节点 < 右子节点
 *   2、方便动态数据的快速插入、删除、查找
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class SearchTree {

  enum TreeDumpType {
    PRE,  //前序遍历
    MIDDLE,  //中序遍历
    POST, //后序遍历
    FLOOR // 层级遍历
  }

  public static class TreeNode<T> { // 树节点

    public TreeNode leftChild;

    public TreeNode rightChild;

    public T data; //数据

    public int sortKey; //排序关键字

    @Override
    public String toString() {
      return "TreeNode{" +
              "leftChild=" + leftChild +
              ", rightChild=" + rightChild +
              ", data=" + data +
              ", sortKey=" + sortKey +
              '}';
    }
  }


  /**
   *
   */
  public class Tree<T> {

    public TreeNode<T> rootNode; //根节点

    public Tree() {

    }

    /**
     * 节点查找
     * @param key
     * @return
     */
    public TreeNode find(int key) {
      TreeNode current = rootNode;               // start at root
      while (current.sortKey != key) { // while no match,
        if (key < current.sortKey)         // go left?
          current = current.leftChild;
        else                            // or go right?
          current = current.rightChild;
        if (current == null)             // if no child,
          return null;                 // didn't find it
      }
      return current;
    }

    /**
     * 插入节点
     * @param node
     */
    public void insert(TreeNode node) {
      if (node == null) {
        rootNode = node;
      } else {
        TreeNode current = rootNode;       // start at root
        TreeNode parent;
        while (true) {                // (exits internally)
          parent = current;
          if (node.sortKey < current.sortKey) {  // go left?
            current = current.leftChild;
            if (current == null) {  // if end of the line,
              parent.leftChild = node; // insert on left
              return;
            }
          } else {  // end if go left, or go right?
            current = current.rightChild;
            if (current == null) { // if end of the line
              // insert on right
              parent.rightChild = node;
              return;
            }
          }  // end else go right
        }  // end while
      }
    }


    /**
     * 删除节点
     *
     *
     *
     * @param node
     */
    public boolean delete(TreeNode node) {
      TreeNode current = rootNode;
      TreeNode parent = rootNode;
      boolean isLeftChild = true;
      while (current.sortKey != node.sortKey) { // search for node, find the node location
        parent = current;
        if (node.sortKey < current.sortKey) {
          isLeftChild = true;
          current = current.leftChild;
        } else {
          isLeftChild = false;
          current = current.rightChild;
        }
        if (current == null) {
          return false; // not find return false;
        }
      }
      //find the current node;
      if (current.leftChild == null && current.rightChild == null) { //delete the leaf node；
        // set the current parent child is null
        if (current == rootNode) {
          rootNode = null;
        } else if (isLeftChild) { // set left pointer is null
          parent.leftChild = null;
        } else {
          parent.rightChild = null; // set right pointer is null
        }
      } else if (current.rightChild == null) { // right child is null, parent child = current.leftChild;
        if (current == rootNode) {
          rootNode = current.leftChild;
        } else if (isLeftChild) {
          parent.leftChild = current.leftChild;
        } else {
          parent.rightChild = current.leftChild;
        }
      } else if (current.leftChild == null) {
         if(current == rootNode) {
           rootNode  = current.rightChild;
         } else if(isLeftChild) {
           parent.leftChild = current.rightChild;
         } else {
           parent.rightChild = current.rightChild;
         }
      } else { // delete node has left and right child
         //find new current node ，and swap sub right child tree
         TreeNode successor = getSuccessor(current);
         if(current == rootNode) {
           rootNode = successor;
         } else if (isLeftChild) {
           parent.leftChild = successor;
         } else {
           parent.rightChild = successor;
         }
         successor.leftChild = current.leftChild;
      }
      current = null; // recycle node
      return true; // delete success
    }

    /**
     * Find the youngest child in the right subtree
     * @param delNode
     * @return
     */
    private TreeNode getSuccessor(TreeNode delNode) {
      TreeNode successorParent = delNode;
      TreeNode successor = delNode;
      TreeNode current = delNode.rightChild;
      while (current != null) { //find the youngest child and its parent
        successorParent = successor;
        successor = current;
        current = current.leftChild;
      }
      if (successor != delNode.rightChild) {//if not first child , we need to swap right subtree
         successorParent.leftChild = successor.rightChild; // successor has right child，
         successor.rightChild = delNode.rightChild;
      }
      return successor;
    }

    /**
     * 二叉树遍历
     * @param type
     */
    public void dumpTree(TreeDumpType type) {
      switch (type) {
        case PRE:
          preDumpTree(rootNode);
          break;
        case MIDDLE:
          midDumpTree(rootNode);
          break;
        case POST:
          postDumpTree(rootNode);
          break;
        case FLOOR:
          floorDumpTree(rootNode);
          break;
      }
    }


    /**
     * 前序遍历树
     *  parent-> left -> right
     */
    public void preDumpTree(TreeNode currentRootNode) {
      if (currentRootNode != null) {
        System.out.println(currentRootNode); // print root node
        preDumpTree(currentRootNode.leftChild); // print left leaf node
        preDumpTree(currentRootNode.rightChild); // print right left node
      }
    }

    /**
     * 中序遍历
     *   left -> parent -> right
     * @param currentRootNode
     */
    public void midDumpTree(TreeNode currentRootNode) {
      if (currentRootNode != null) {
        midDumpTree(currentRootNode.leftChild); // print left leaf node
        System.out.println(currentRootNode); // print root node
        midDumpTree(currentRootNode.rightChild); // print right left node
      }
    }

    /**
     * 后续， left -> right -> parent
     * @param currentRootNode
     */
    public void postDumpTree(TreeNode currentRootNode) {
      if (currentRootNode != null) {
        postDumpTree(currentRootNode.leftChild); // print left leaf node
        postDumpTree(currentRootNode.rightChild); // print right left node
        System.out.println(currentRootNode); // print root node
      }
    }

    public void floorDumpTree(TreeNode currentRootNode) {
      Stack globalStack = new Stack();
      globalStack.push(rootNode);
      int nBlanks = 32; // print blank nums
      boolean isRowEmpty = false;
      System.out.println("......................................................");
      while (isRowEmpty == false) {
        Stack localStack = new Stack();
        isRowEmpty = true;
        for (int j = 0; j < nBlanks; j++) {
          System.out.print(' ');
        }
        while (globalStack.isEmpty() == false) {
          TreeNode temp = (TreeNode) globalStack.pop();
          if (temp != null) {
            System.out.print(temp); //print row child
            localStack.push(temp.leftChild); //add child to local stack
            localStack.push(temp.rightChild);
            if (temp.leftChild != null || temp.rightChild != null) {
              //continue
              isRowEmpty = false;
            }
          } else {
            System.out.print("--");
            localStack.push(null);
            localStack.push(null);
          }
          for (int j = 0; j < nBlanks * 2 - 2; j++) { //
            System.out.print(' ');
          }
        }

        System.out.println();
        nBlanks /= 2;
        while (localStack.isEmpty() == false) { // add row child to global stack
          globalStack.push(localStack.pop());
        }
      }
    }
  }

}
