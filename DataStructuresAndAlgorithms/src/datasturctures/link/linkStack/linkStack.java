package datasturctures.link.linkStack;


class Link {
  public long dData;             // data item
  public Link next;              // next LinkInterview in list

  // -------------------------------------------------------------
  public Link(long dd)           // constructor
  {
    dData = dd;
  }

  // -------------------------------------------------------------
  public void displayLink()      // display ourself
  {
    System.out.print(dData + " ");
  }
}


class LinkList {
  private Link first;            // ref to first item on list

  // -------------------------------------------------------------
  public LinkList()              // constructor
  {
    first = null;
  }           // no items on list yet

  // -------------------------------------------------------------
  public boolean isEmpty()       // true if list is empty
  {
    return (first == null);
  }

  // -------------------------------------------------------------
  public void insertFirst(long dd) // insert at start of list
  {                           // make new LinkInterview
    Link newLink = new Link(dd);
    newLink.next = first;       // newLink --> old first
    first = newLink;            // first --> newLink
  }

  // -------------------------------------------------------------
  public long deleteFirst()      // delete first item
  {                           // (assumes list not empty)
    Link temp = first;          // save reference to LinkInterview
    first = first.next;         // delete it: first-->old next
    return temp.dData;          // return deleted LinkInterview
  }

  // -------------------------------------------------------------
  public void displayList() {
    Link current = first;       // start at beginning of list
    while (current != null)      // until end of list,
    {
      current.displayLink();   // print data
      current = current.next;  // move to next LinkInterview
    }
    System.out.println("");
  }
// -------------------------------------------------------------
}

/**
 * Version 1.0
 * Created by lll on 12/6/17.
 * Description
 * 链表 实现栈
 * 链表头部插入数据，头部删除数据
 * copyright generalray4239@gmail.com
 */

class LinkStack {
  private LinkList theList;

  //--------------------------------------------------------------
  public LinkStack()             // constructor
  {
    theList = new LinkList();
  }

  //--------------------------------------------------------------
  public void push(long j)     // put item on top of stack
  {
    theList.insertFirst(j);
  }

  //--------------------------------------------------------------
  public long pop()            // take item from top of stack
  {
    return theList.deleteFirst();
  }

  //--------------------------------------------------------------
  public boolean isEmpty()       // true if stack is empty
  {
    return (theList.isEmpty());
  }

  //--------------------------------------------------------------
  public void displayStack() {
    System.out.print("Stack (top-->bottom): ");
    theList.displayList();
  }
//--------------------------------------------------------------
}  // end class LinkStack

////////////////////////////////////////////////////////////////
class LinkStackApp {
  public static void main(String[] args) {
    LinkStack theStack = new LinkStack(); // make stack

    theStack.push(20);                    // push items
    theStack.push(40);

    theStack.displayStack();              // display stack

    theStack.push(60);                    // push items
    theStack.push(80);

    theStack.displayStack();              // display stack

    theStack.pop();                       // pop items
    theStack.pop();

    theStack.displayStack();              // display stack
  }  // end main()
}  // end class LinkStackApp
////////////////////////////////////////////////////////////////
