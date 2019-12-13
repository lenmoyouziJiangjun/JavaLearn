package datasturctures.link.linkQueue;

//链表节点
class Link {
  public long dData;                // data item
  public Link next;                 // next LinkInterview in list

  // -------------------------------------------------------------
  public Link(long d)               // constructor
  {
    dData = d;
  }

  // -------------------------------------------------------------
  public void displayLink()         // display this LinkInterview
  {
    System.out.print(dData + " ");
  }
// -------------------------------------------------------------
}


class FirstLastList {
  private Link first;               // ref to first item
  private Link last;                // ref to last item

  // -------------------------------------------------------------
  public FirstLastList()            // constructor
  {
    first = null;                  // no items on list yet
    last = null;
  }

  // -------------------------------------------------------------
  public boolean isEmpty()          // true if no links
  {
    return first == null;
  }

  // -------------------------------------------------------------
  public void insertLast(long dd) // insert at end of list
  {
    Link newLink = new Link(dd);   // make new LinkInterview
    if (isEmpty())                // if empty list,
      first = newLink;            // first --> newLink
    else
      last.next = newLink;        // old last --> newLink
    last = newLink;                // newLink <-- last
  }

  // -------------------------------------------------------------
  public long deleteFirst()         // delete first LinkInterview
  {                              // (assumes non-empty list)
    long temp = first.dData;
    if (first.next == null)         // if only one item
      last = null;                // null <-- last
    first = first.next;            // first --> old next
    return temp;
  }

  // -------------------------------------------------------------
  public void displayList() {
    Link current = first;          // start at beginning
    while (current != null)         // until end of list,
    {
      current.displayLink();      // print data
      current = current.next;     // move to next LinkInterview
    }
    System.out.println("");
  }
// -------------------------------------------------------------
}


/**
 * Version 1.0
 * Created by lll on 12/6/17.
 * Description
 * 链表实现队列： 先进先出
 * 从链表末尾添加数据，从链表头删除数据
 * copyright generalray4239@gmail.com
 */
class LinkQueue {
  private FirstLastList theList;

  //--------------------------------------------------------------
  public LinkQueue()                // constructor
  {
    theList = new FirstLastList();
  }  // make a 2-ended list

  //--------------------------------------------------------------
  public boolean isEmpty()          // true if queue is empty
  {
    return theList.isEmpty();
  }

  //--------------------------------------------------------------
  public void insert(long j)        // insert, rear of queue
  {
    theList.insertLast(j);
  }

  //--------------------------------------------------------------
  public long remove()              // remove, front of queue
  {
    return theList.deleteFirst();
  }

  //--------------------------------------------------------------
  public void displayQueue() {
    System.out.print("Queue (front-->rear): ");
    theList.displayList();
  }
//--------------------------------------------------------------
}  // end class LinkQueue

////////////////////////////////////////////////////////////////
class LinkQueueApp {
  public static void main(String[] args) {
    LinkQueue theQueue = new LinkQueue();
    theQueue.insert(20);                 // insert items
    theQueue.insert(40);

    theQueue.displayQueue();             // display queue

    theQueue.insert(60);                 // insert items
    theQueue.insert(80);

    theQueue.displayQueue();             // display queue

    theQueue.remove();                   // remove items
    theQueue.remove();

    theQueue.displayQueue();             // display queue
  }  // end main()
}  // end class LinkQueueApp
////////////////////////////////////////////////////////////////
