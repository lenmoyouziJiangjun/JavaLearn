package algorithms;

/**
 * Version 1.0
 * Created by lll on 2019-12-09.
 * Description
 * copyright generalray4239@gmail.com
 */
public class LinkInterview {

  /**
   * 定义一个单链表节点
   */
  public class Node {
    int data;
    public Node next;
  }

  public static Node reverseNode(Node header) {
    Node next = null;
    Node pre = null; //用一个节点来表示当前节点的前驱
    while (header != null) {
      next = header.next;
      //当前节点的后驱指向前驱
      header.next = pre;
      pre = header;
      //处理下一个节点
      header = next;
    }
    return pre;
  }

  //用递归的方法反转链表
  public static Node reverseList2(Node head) {
    if (head == null || head.next == null) {
      return head;
    }
    //递归反转子lian链表
    Node newList = reverseList2(head.next);
    //第三张图
    head.next.next = head;
    head.next = null;
    return newList;
  }


}
