package datasturctures.link;

import java.util.Collections;
import java.util.Queue;
import java.util.Stack;

/**
 * Version 1.0
 * Created by lll on 2019-12-14.
 * Description
 * <pre>
 *   链表面试题搜集
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class LinkInterview {

    /**
     * 定义一个单链表节点
     */
    static class Node {
        public int value;
        public Node nextNode; //后继节点

        public Node(int data) {
            this.value = data;
        }

        @Override
        public String toString() {
            return value + "  ";
        }
    }

    /**
     * 双链表结构
     */
    static class DoubleNode {


        public int value;
        public DoubleNode nextNode; //后继节点
        public DoubleNode preNode; //前驱节点

        public DoubleNode(int data) {
            this.value = data;
        }

        @Override
        public String toString() {
            return value + "  ";
        }
    }

    public void printLink(Node head) {
        System.out.println("-----------start print link-----");
        while (head != null) {
            System.out.print(head.toString());
            head = head.nextNode;
        }
        System.out.println();
        System.out.println("-----------end print link-----");
    }

    public Node displayLink() {
        Node head = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        head.nextNode = node1;
        node1.nextNode = node2;
        node2.nextNode = node3;
        node3.nextNode = node4;
        node4.nextNode = node5;
        node5.nextNode = node6;
        return head;
    }

    public Node displayRingLink() {
        Node head = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        head.nextNode = node1;
        node1.nextNode = node2;
        node2.nextNode = node3;
        node3.nextNode = node4;
        node4.nextNode = node5;
        node5.nextNode = node6;
        node6.nextNode = head;
        return head;
    }


    /**
     * 反转一个单链表
     * 方法1、增加一个pre节点： pre, 1，2，3，
     *
     * @param head
     * @return
     */
    public Node revertNode(Node head) {
        Node next = null;//当前节点的后驱
        Node pre = null;//增加一个前驱节点
        while (head != null) {
            next = head.nextNode;
            //当前节点的后驱指向前驱
            head.nextNode = pre;
            pre = head;
            //继续循环
            head = next;
        }
        return pre;
    }

    /**
     * 采用递归反转链表
     *
     * @param head
     * @return
     */
    public Node revertNode2(Node head) {
        if (head == null || head.nextNode == null) {
            return head;
        }

        Node newList = revertNode2(head.nextNode);
        head.nextNode.nextNode = head;
        head.nextNode = null;
        return newList;
    }

    /**
     * 给定一个单向链表的头结点head,以及两个整数from和to ,在单项链表上把第from个节点和第to个节点这一部分进行反转
     * 1、如果链表长度为N，时间复杂度要求为O（N),额外空间复杂度要求为O（1）
     * <p>
     * 2、如果不满足1<=from<=to<=N,则不调整
     *
     * @param head
     * @param from
     * @param to
     */
    public Node revertDepartNode(Node head, int from, int to) {
        if (from >= to) {
            return head;
        }
        //空间复杂度要求O(1);就只有原地
        int i = 0;
        Node revertNodeHead = null;
        Node revertNodeHeadPre = null;

        Node revertNodeTileNode = null;
        Node revertNodeTileNodeNext = null;
        while (head != null) {
            if (i == from) {
                revertNodeHead = head;
            } else if (i == (from - 1)) {
                revertNodeHeadPre = head;
            } else if (i == to) {
                revertNodeTileNode = head;
                revertNodeTileNodeNext = head.nextNode;
                break;
            }
            i++;
            head = head.nextNode;
        }
        System.out.println("revertNodeHead==" + revertNodeHead);
        System.out.println("revertNodeHeadPre==" + revertNodeHeadPre);
        System.out.println("revertNodeTileNode==" + revertNodeTileNode);
        System.out.println("revertNodeTileNodeNext==" + revertNodeTileNodeNext);
        if (revertNodeTileNode == null) {
            System.out.println("超过链表长度");
            return head;
        }
        Node node = revertNode3(revertNodeHead, revertNodeTileNodeNext);
        System.out.println("the node ==" + node);
        if (revertNodeHeadPre != null) {
            revertNodeHeadPre.nextNode = node;
            return head;
        } else {
            return node;
        }
    }

    public Node revertNode3(Node startNode, Node endNode) {
        Node pre = endNode;
        Node next = null;
        while (startNode != endNode) {
            next = startNode.nextNode;
            startNode.nextNode = pre;
            pre = startNode;
            startNode = next;
        }
        return pre;
    }

    /**
     * 删除链表节点
     *
     * @param node
     * @param deleteIndex
     * @return
     */
    public Node deleteNode(Node node, int deleteIndex) {
        int index = 0;
        int preIndex = deleteIndex - 1;
        Node nextNode = null;
        Node preNode = null;
        while (node != null) {
            if (index == preIndex) {
                preNode = node;
            } else if (index == deleteIndex) {
                nextNode = node.nextNode;
                node = null;
                break;
            }
            index++;
            node = node.nextNode;
        }
        preNode.nextNode = nextNode;
        return node;
    }


    /**
     * 41个人排成圆圈，由第一个人开始报数，报数到3的人就自杀，然后再有下一个人重新报1，报到3的自杀，
     * 依次下去，直到剩下的一个人，环形单链表描述
     *
     * @param head
     * @param num
     * @return
     */
    public Node josephusNode(Node head, int num) {
        if (head == null || num < 1) { //算法的第一条：先判断输入是否合法
            return head;
        }
        //因为是一个环，所以第一步要找到尾节点
        Node last = head;
        while (head.nextNode != last) {
            head = head.nextNode;
        }
        int count = 0;
        while (head.nextNode != head) { //head这个节点必须跟着变，
            System.out.println("the head==" + head + "==tile" + head);
            if (++count == num) { //从1开始
                head.nextNode = head.nextNode.nextNode;
                count = 0;
            } else {
                head = head.nextNode;
            }
        }
        return head;
    }


    /**
     * 判断是否是回文链表
     * 1->2->1，返回 true.
     * <p>
     * 1->2->2->1, 返回 true。
     * <p>
     * 1->2->3，返回 false。
     * <p>
     * 要求：如果链表的长度为 N, 时间复杂度达到 O(N)。
     * <p>
     * 双链表版本
     *
     * @param node
     * @return
     */
    public void isPlalindromeLint(DoubleNode node) {
        int n = 0;
        while (node != null) {
            n++;
            node = node.nextNode;
        }
        System.out.println("the node length==" + n);
        int k = 0;
        boolean isDoubleLength = (n % 2 == 0);
        int num = isDoubleLength ? (n >> 1) : ((n + 1) >> 1);
        DoubleNode node1 = null;
        DoubleNode node2 = null;
        while (node != null) {
            if (k == num) {
                if (isDoubleLength) {
                    node1 = node;
                    node2 = node.nextNode;
                } else {
                    node1 = node;
                    node2 = node;
                }
            }
            k++;
        }

        boolean value = isEquals(node1, node2);
    }

    private boolean isEquals(DoubleNode node1, DoubleNode node2) {
        boolean value = true;
        if (node1.value != node2.value) {
            value = false;
        }
        while (node1 != null && node2 != null) {
            if (node1.preNode.value != node2.nextNode.value) {
                value = false;
                break;
            }
        }
        return value;
    }


    /**
     * 判断是否是回文链表
     * * 1->2->1，返回 true.
     * * <p>
     * * 1->2->2->1, 返回 true。
     * * <p>
     * * 1->2->3，返回 false。
     * * <p>
     * <p>
     * 实现方案：
     * 1、用栈装链表后半部分，然后pop出栈比较
     * 2、反转后半部分数组，再比较
     *
     * @param node
     */
    public void isPlalindromeLint(Node node) {

    }


    /**
     * 对链表进行排序
     * 给定一个单向链表的头结点head, 节点的值类型是整型，再给定一个整数privot。实现一个调整链表的函数，将链表调整为左部分都是值小于privot的节点，中间部分都是值等于privot的节点，右部分都是大于privot的节点。且对某部分内部节点的顺序不做要求
     *
     * @param node
     * @param value
     */
    public void sortLink(Node node, int value) {
        Node sB = null; //小的指针头，即small begin
        Node sE = null; //小的指针尾，即small end
        Node eB = null;
        Node eE = null;
        Node bB = null;
        Node bE = null;

    }

    /**
     * 给定一个单链表的头节点head, 实现一个调整单链表的函数，使得每K个节点之间逆序，如果最后不够K个节点一组，则不调整最后几个节点。
     * <p>
     * 链表:1->2->3->4->5->6->7->8->null, K = 3。
     * <p>
     * 调整后：3->2->1->6->5->4->7->8->null。其中 7，8不调整，因为不够一组。
     * <p>
     * 要求：如果链表的长度为 N, 时间复杂度达到 O(N)。
     * <p>
     * 1、空间负责度为O(1)
     * 2、可以通过栈辅助，空间复杂度为O(N)
     *
     * @param head
     * @param k
     */
    public Node revertPartLinkByLength(Node head, int k) {
        if (head == null || head.nextNode == null) {
            return head;
        }
        Node cur = head;
        for (int i = 1; cur != null && i < k; i++) { //分离前面K个链表
            cur = cur.nextNode;
        }
        if (cur == null) {
            return head;
        }
        Node temp = cur.nextNode;
        cur.nextNode = null;
        //把前面k个节点进行反转
        Node newHead = revertNode(head);

        //把K后面的链表进行每K个节点逆转转
        Node newTemp = revertPartLinkByLength(temp, k);
        return newHead;
    }

    /**
     * 递归反转链表
     *
     * @param node
     * @return
     */
    public Node revertNodeByRecursive(Node node) {
        if (node == null || node.nextNode == null) {
            return node;
        }
        Node newHead = revertNodeByRecursive(node.nextNode);
        node.nextNode.nextNode = node;
        node.nextNode = null;
        return newHead;
    }

    /**
     * 给定两个非空链表来表示两个非负整数。
     * 位数按照逆序方式存储，
     * 它们的每个节点只存储单个数字。
     * 将两数相加返回一个新的链表。
     * <p>
     * 你可以假设除了数字 0 之外，
     * 这两个数字都不会以零开头。
     * 示例：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * <pre>
     *    思路
     *    先把链表的转成对应的整数，然后求值，再生成对应的链表
     * </pre>
     *
     * @param node
     * @param node2
     */
    public static void getSum(Node node, Node node2) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        while (node != null) {
            sb1.append(node.value);
            node = node.nextNode;
        }
        while (node2 != null) {
            sb2.append(node2.value);
            node2 = node2.nextNode;
        }
        int num1 = Integer.parseInt(sb1.toString());
        int num2 = Integer.parseInt(sb2.toString());
        int total = num1 + num2;
        System.out.println("the total==" + total + " /10==" + (total / 10));
        Node nodeResult = null;
        while (total / 10 != 0) {
            System.out.print(" " + total % 10 + "->");
            if (nodeResult != null) {
                Node nodeN = new Node(total % 10);
                nodeResult.nextNode = nodeN;
                nodeResult = nodeN;
            } else {
                nodeResult = new Node(total % 10);
            }
            total = total / 10;
        }
        if (total / 10 == 0) {
            System.out.print(total % 10);
            Node nodeN = new Node(total % 10);
            nodeResult.nextNode = nodeN;
        }
    }

    public static void getNum2(Node node1, Node node2) {
        Node head = new Node(0); //定义哨兵
        Node temp = head;
        int count = 0;
        while (node1 != null || node2 != null || count != 0) {
            if (node1 != null) {
                count += node1.value;
                node1 = node1.nextNode;
            }

            if (node2 != null) {
                count += node2.value;
                node2 = node2.nextNode;
            }
            temp.nextNode = new Node(count % 10);
            temp = temp.nextNode;
            count = count / 10;
        }
        while (head.nextNode != null) {
            System.out.print("" + head.nextNode.value + " ->");
            head.nextNode = head.nextNode.nextNode;
        }
    }


    public static void testGetNum() {
        Node node1 = new Node(2);
        Node node11 = new Node(4);
        Node node12 = new Node(3);
        node1.nextNode = node11;
        node11.nextNode = node12;

        Node node2 = new Node(5);
        Node node21 = new Node(6);
        Node node22 = new Node(4);
        node2.nextNode = node21;
        node21.nextNode = node22;

//        getSum(node1, node2);
        getNum2(node1, node2);
    }


    /**
     * 删除倒数第index 节点
     *
     * <pre>
     *     分析：
     *     方法一：
     *     第一步计算出链表长度length， 第二步 便利找到length - index （要删除节点的父节点）
     *
     *     方法二：双指针法：
     *     第一个指针从n+1开始遍历，第二个真正从头开始遍历，
     * </pre>
     *
     * @param node
     * @param index
     */
    public static void deleteNum(Node node, int index) {
        //第一步找到n+1节点
        Node first = node;
        Node second = node;
        for (int i = 0; i < index + 1; i++) {
            first = first.nextNode;
        }
        while (first != null) {
            first = first.nextNode;
            second = second.nextNode;
        }
        second.nextNode = second.nextNode.nextNode;
        printNode(node);
    }

    public static void printNode(Node node){
        System.out.println("print node ============");
        while(node != null){
            System.out.print(node.value +" ");
            node = node.nextNode;
        }
        System.out.println();
        System.out.println("print end node ============");
    }

    public static void testDeleteNum() {
        Node node1 = new Node(1);
        Node node11 = new Node(2);
        Node node12 = new Node(3);
        Node node13 = new Node(4);
        Node node14 = new Node(5);
        node1.nextNode = node11;
        node11.nextNode = node12;
        node12.nextNode = node13;
        node13.nextNode = node14;

        printNode(node1);
        deleteNum(node1,2);
    }

    public static void main(String[] args) {
        LinkInterview interview = new LinkInterview();

        Node node = interview.displayLink();
//    interview.printLink(node);
//
//
//    node = interview.revertNode(node);
//    interview.printLink(node);
//
//    node = interview.revertNode2(node);
//    interview.printLink(node);


//    node = interview.revertDepartNode(node, 0, 1);

//    interview.printLink(node);
//        node = interview.displayRingLink();
//        node = interview.josephusNode(node, 3);
//
//        interview.printLink(node);


//        testGetNum();

        testDeleteNum();
    }
}
