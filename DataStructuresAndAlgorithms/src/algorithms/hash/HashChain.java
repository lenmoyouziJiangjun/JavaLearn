package algorithms.hash;

/**
 * Version 1.0
 * Created by lll on 12/03/2018.
 * Description
 * <pre>
 *     链地址法解决hash冲突：
 *      hash冲突的数组，存储到链表里面。也就是说每一个数组元素，都可能是一个链表
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class HashChain {

}


//节点数据，为一个链表结构
class Link {
    private int iData;                  // data item
    public Link next;                   // next link in list

    public Link(int it)                 // constructor
    {
        iData = it;
    }

    public int getKey() {
        return iData;
    }

    public void displayLink()           // display this link
    {
        System.out.print(iData + " ");
    }
}

class SortedList {
    private Link first;//链表头节点

    public SortedList() {
        first = null;
    }

    public void insert(Link theLink) {
        int key = theLink.getKey();
        Link previous = null;          // start at first
        Link current = first;
        while (current != null && key > current.getKey()) {//按照key进行排序
            previous = current;
            current = current.next;
        }
        if (previous == null) {
            first = theLink;
        } else {
            previous.next = theLink;
        }
        theLink.next = current;
    }

    public void delete(int key) {                              // (assumes non-empty list)
        Link previous = null;          // start at first
        Link current = first;
        // until end of list,
        while (current != null && key != current.getKey()) {                           // or key == current,
            previous = current;
            current = current.next;     // go to next link
        }
        // disconnect link
        if (previous == null) {             //   if beginning of list
            first = first.next;
        } else {                          //   not at beginning
            previous.next = current.next;
        }
    }

    // -------------------------------------------------------------
    public Link find(int key)         // find link
    {
        Link current = first;          // start at first
        // until end of list,
        while (current != null && current.getKey() <= key) {                           // or key too small,
            if (current.getKey() == key)    // is this the link?
                return current;          // found it, return link
            current = current.next;     // go to next item
        }
        return null;                   // didn't find it
    }
}


class HashChainTable {
    //实际开发中，可以省去，SortedList这一层
    private SortedList[] hashArray;   // array of lists
    private int arraySize;

    public HashChainTable(int size) {
        arraySize = size;
        hashArray = new SortedList[arraySize];  // create array
        for (int j = 0; j < arraySize; j++) {         // fill array
            hashArray[j] = new SortedList();     // with lists
        }
    }

    //hash算法
    public int hashFunc(int key) {
        return key % arraySize;
    }

    public void inseart(Link theLink) {
        int key = theLink.getKey();
        int hashVal = hashFunc(key);
        //如果重复了，就插入到链表里面，如果没有重复,first 就是
        hashArray[hashVal].insert(theLink);
    }

    public void delete(int key) {
        int hashVal = hashFunc(key);   // hash the key
        hashArray[hashVal].delete(key); // delete link
    }

    public Link find(int key)         // find link
    {
        int hashVal = hashFunc(key);   // hash the key
        Link theLink = hashArray[hashVal].find(key);  // get link
        return theLink;                // return link
    }
}