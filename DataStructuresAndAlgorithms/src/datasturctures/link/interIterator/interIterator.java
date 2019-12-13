package datasturctures.link.interIterator;// interIterator.java
// demonstrates iterators on a linked listListIterator
// to run this program: C>java InterIterApp

import java.io.*;                 // for I/O

////////////////////////////////////////////////////////////////
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
}  // end class Link

////////////////////////////////////////////////////////////////
class LinkList {
  private Link first;            // ref to first item on list

  // -------------------------------------------------------------
  public LinkList()              // constructor
  {
    first = null;
  }           // no items on list yet

  // -------------------------------------------------------------
  public Link getFirst()         // get value of first
  {
    return first;
  }

  // -------------------------------------------------------------
  public void setFirst(Link f)   // set first to new LinkInterview
  {
    first = f;
  }

  // -------------------------------------------------------------
  public boolean isEmpty()       // true if list is empty
  {
    return first == null;
  }

  // -------------------------------------------------------------
  public ListIterator getIterator()  // return iterator
  {
    return new ListIterator(this);  // initialized with
  }                               //    this list

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
}  // end class LinkList

////////////////////////////////////////////////////////////////
class ListIterator {
  private Link current;          // current LinkInterview
  private Link previous;         // previous LinkInterview
  private LinkList ourList;      // our linked list

  //--------------------------------------------------------------
  public ListIterator(LinkList list) // constructor
  {
    ourList = list;
    reset();
  }

  //--------------------------------------------------------------
  public void reset()            // start at 'first'
  {
    current = ourList.getFirst();
    previous = null;
  }

  //--------------------------------------------------------------
  public boolean atEnd()         // true if last LinkInterview
  {
    return (current.next == null);
  }

  //--------------------------------------------------------------
  public void nextLink()         // go to next LinkInterview
  {
    previous = current;
    current = current.next;
  }

  //--------------------------------------------------------------
  public Link getCurrent()       // get current LinkInterview
  {
    return current;
  }

  //--------------------------------------------------------------
  public void insertAfter(long dd)     // insert after
  {                                 // current LinkInterview
    Link newLink = new Link(dd);

    if (ourList.isEmpty())     // empty list
    {
      ourList.setFirst(newLink);
      current = newLink;
    } else                        // not empty
    {
      newLink.next = current.next;
      current.next = newLink;
      nextLink();              // point to new LinkInterview
    }
  }

  //--------------------------------------------------------------
  public void insertBefore(long dd)    // insert before
  {                                 // current LinkInterview
    Link newLink = new Link(dd);

    if (previous == null)        // beginning of list
    {                        // (or empty list)
      newLink.next = ourList.getFirst();
      ourList.setFirst(newLink);
      reset();
    } else                        // not beginning
    {
      newLink.next = previous.next;
      previous.next = newLink;
      current = newLink;
    }
  }

  //--------------------------------------------------------------
  public long deleteCurrent()    // delete item at current
  {
    long value = current.dData;
    if (previous == null)        // beginning of list
    {
      ourList.setFirst(current.next);
      reset();
    } else                        // not beginning
    {
      previous.next = current.next;
      if (atEnd())
        reset();
      else
        current = current.next;
    }
    return value;
  }
//--------------------------------------------------------------
}  // end class ListIterator

////////////////////////////////////////////////////////////////
class InterIterApp {
  public static void main(String[] args) throws IOException {
    LinkList theList = new LinkList();           // new list
    ListIterator iter1 = theList.getIterator();  // new iter
    long value;

    iter1.insertAfter(20);             // insert items
    iter1.insertAfter(40);
    iter1.insertAfter(80);
    iter1.insertBefore(60);

    while (true) {
      System.out.print("Enter first letter of show, reset, ");
      System.out.print("next, get, before, after, delete: ");
      System.out.flush();
      int choice = getChar();         // get user's option
      switch (choice) {
        case 's':                    // show list
          if (!theList.isEmpty())
            theList.displayList();
          else
            System.out.println("List is empty");
          break;
        case 'r':                    // reset (to first)
          iter1.reset();
          break;
        case 'n':                    // advance to next item
          if (!theList.isEmpty() && !iter1.atEnd())
            iter1.nextLink();
          else
            System.out.println("Can't go to next LinkInterview");
          break;
        case 'g':                    // get current item
          if (!theList.isEmpty()) {
            value = iter1.getCurrent().dData;
            System.out.println("Returned " + value);
          } else
            System.out.println("List is empty");
          break;
        case 'b':                    // insert before current
          System.out.print("Enter value to insert: ");
          System.out.flush();
          value = getInt();
          iter1.insertBefore(value);
          break;
        case 'a':                    // insert after current
          System.out.print("Enter value to insert: ");
          System.out.flush();
          value = getInt();
          iter1.insertAfter(value);
          break;
        case 'd':                    // delete current item
          if (!theList.isEmpty()) {
            value = iter1.deleteCurrent();
            System.out.println("Deleted " + value);
          } else
            System.out.println("Can't delete");
          break;
        default:
          System.out.println("Invalid entry");
      }  // end switch
    }  // end while
  }  // end main()

  //--------------------------------------------------------------
  public static String getString() throws IOException {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    String s = br.readLine();
    return s;
  }

  //-------------------------------------------------------------
  public static char getChar() throws IOException {
    String s = getString();
    return s.charAt(0);
  }

  //-------------------------------------------------------------
  public static int getInt() throws IOException {
    String s = getString();
    return Integer.parseInt(s);
  }
//-------------------------------------------------------------
}  // end class InterIterApp
////////////////////////////////////////////////////////////////
