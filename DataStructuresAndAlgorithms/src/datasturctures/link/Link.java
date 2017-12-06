package datasturctures.link;

/**
 * Version 1.0
 * Created by lll on 12/6/17.
 * Description
 *  链表
 * copyright generalray4239@gmail.com
 */
public class Link {

    public Node tail;// 头结点



    public void insertData(int data){
        Node newNode = new Node(data);
        if(tail == null){
            tail = newNode;
        }else{
            newNode .next = tail;
            tail = newNode;
        }
    }

    public void deleteData(int data){

    }

}


class Node{

    public int data;
    public Node next;

    public Node(int data){
        this.data = data;
    }

}
