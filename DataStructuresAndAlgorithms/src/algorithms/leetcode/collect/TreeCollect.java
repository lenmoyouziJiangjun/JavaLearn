package algorithms.leetcode.collect;

import java.util.*;

/**
 * Version 1.0
 * Created by lll on 2020-06-22.
 * Description
 * <pre>
 *     tree 先关算法合集
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class TreeCollect {

    /**
     * 将一个数组表示的二叉树 用一个 一个链表结构表示的二叉树
     *
     * @param index
     * @param nums
     * @return
     */
    private static TreeNode generatorTree(int index, Integer[] nums) {
        if (index < nums.length && nums[index] != null) {
            TreeNode node = new TreeNode(nums[index]);

            node.left = generatorTree(2 * index + 1, nums);
            node.right = generatorTree(2 * index + 2, nums);
            return node;
        } else {
            return null;
        }
    }

    /**
     * 生成二叉搜索树1
     * <p>
     * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
     *
     * @param start 数组开始元素
     * @param end   数组结束元素
     * @return
     */
    private static List<TreeNode> generatorBT(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
            return result;
        }

        for (int i = start; i <= end; ++i) {
            List<TreeNode> left = generatorBT(start, i - 1); //小于当前节点的作为左子树
            List<TreeNode> right = generatorBT(i + 1, end); //大于当前节点的作为右子树
            for (TreeNode leftNode : left) {
                for (TreeNode rightNode : right) {
                    TreeNode node = new TreeNode(i);
                    node.left = leftNode;
                    node.right = rightNode;
                    result.add(node);
                }
            }
        }
        return result;
    }


    /**
     * 中序遍历一个二叉树
     *
     * @param node
     */
    private static void dumpMidTreeNode(TreeNode node) {
        if (node != null) {
            //升序遍历
            dumpMidTreeNode(node.right);
            System.out.print(node.data + "  ");
            dumpMidTreeNode(node.left);
        }
    }

    /**
     * 层级遍历二叉树
     *
     * @param node
     */
    private static void dumpFloorTreeNode(TreeNode node) {
        LinkedList<TreeNode> nodeLinkedList = new LinkedList<>();
        nodeLinkedList.addFirst(node);
        while (nodeLinkedList.size() != 0) {
            int size = nodeLinkedList.size();
            for (int i = 0; i < size; i++) {
                TreeNode deleteNode = nodeLinkedList.removeLast();
                if (deleteNode.left != null) {
                    nodeLinkedList.addFirst(deleteNode.left);
                }
                if (deleteNode.right != null) {
                    nodeLinkedList.addFirst(deleteNode.right);
                }
                System.out.print(deleteNode.data + "   ");
            }
        }
    }

    /**
     * 获取数的高度
     *
     * @param node
     * @return
     */
    private static int getNodeHeight(TreeNode node) {
        if (node == null) {
            return -1; //高度 ，node 到子节点的边数，如果node没有子节点，node 的边数为0，所以这里为-1；
        }
        int leftHeight = getNodeHeight(node.left); //获取左边高度
        int rightHeight = getNodeHeight(node.right); //获取右边高度
        return Math.max(leftHeight, rightHeight) + 1;
    }


    /**
     * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
     */
    public static void leetCode_95() {
        List<TreeNode> nodeList = generatorBT(1, 3);
//        dumpfloorTreeNode(nodeList.get(2));
        for (TreeNode node : nodeList) {
            System.out.println("--------header node===" + node.data);
            dumpMidTreeNode(node);
//            dumpfloorTreeNode(node);
            System.out.println("-----------");
        }
    }

    /**
     * 当前节点的最大路径和 = node.data+node.left.路径和+ node.right.路径和
     *
     * @param node
     * @return
     */
    private static int getTreeNodeNum(TreeNode node) {
        if (node == null) {
            return 0;
        } else {

            int leftValue = Math.max(getTreeNodeNum(node.left), 0); //左边值，小于0，取0；

            int rightValue = Math.max(getTreeNodeNum(node.right), 0);//右边值，

            int value = node.data + leftValue + rightValue;

            if (value > maxValue) {
                maxValue = value;
            }
            return value;
        }

    }

    /**
     * 给定一个非空二叉树，返回其最大路径和
     * <p>
     * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
     */
    static int maxValue = 0;

    public static void leetCode_124() {
        Integer[] nums = new Integer[]{-10, 9, 20, null, null, 15, 7};
//        Integer[] nums = new Integer[]{1,2,3};
        TreeNode node = generatorTree(0, nums);

        //当前节点的路径和 = data+ left.路径和+ right.路径和;
        dumpFloorTreeNode(node);


        getTreeNodeNum(node);
        System.out.println("max value===" + maxValue);
        int height = getNodeHeight(node);
        System.out.println("the height===" + height);
    }


    /**
     * 二叉树 翻转，左子树和右子树翻转
     */
    public static void leetCode_125() {

    }


    Map<String, Integer> trees = new HashMap<>();
    List<TreeNode> nodeList = new ArrayList<>();

    private String lookup(TreeNode node) {
        if (node == null) {
            return "#";
        }
        //计算当前节点的标记
        String serial = node.data + "," + lookup(node.left) + "," + lookup(node.right);
        trees.put(serial, trees.getOrDefault(serial, 0) + 1);
        if (trees.get(serial) == 2) {
            nodeList.add(node);
            System.out.println("the node ===" + node.data);
        }
        return serial;
    }


    /**
     * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
     * <p>
     * 给每一个节点设置一个唯一id。存储到map集合中，如果map已经包含了，就表示有重复节点
     */
    public void leetCode_652() {
        //一颗树里面的重复子树，只能出现在左右子树中

        TreeNode header = new TreeNode(1);//模拟一个树结构
        TreeNode node2 = new TreeNode(2);//模拟一个树结构
        TreeNode node3 = new TreeNode(3);//模拟一个树结构
        TreeNode node4 = new TreeNode(4);//模拟一个树结构

        header.left = node2;
        header.right = node3;
        node2.left = node4;
        node3.left = node2;
        node3.right = node4;

        lookup(header);

        for (TreeNode node : nodeList) {
            System.out.println("---------------");
            dumpFloorTreeNode(node);
        }
    }






    public static void main(String[] args) {
//        leetCode_95();

//        leetCode_124();

        TreeCollect collect = new TreeCollect();
        collect.leetCode_652();
    }
}


class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data) {
        this.data = data;
    }


}