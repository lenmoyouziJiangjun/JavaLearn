package algorithms.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Version 1.0
 * Created by lll on 17/5/11.
 * Description 堆排序
 * 1、实现原理：
 * 使用普通的insert方法插入全部无序数据，然后反复调用remove.就可以按顺序移除所有数据了
 * 2、堆排序的时间复杂度：
 * N*logN
 * 3、堆排序的好处：
 * 节省时间和节省内存
 * copyright generalray4239@gmail.com
 */
public class HeapSort {
    public static void main(String[] args) throws IOException {
        int size, j;

        System.out.print("Enter number of items: ");
        size = getInt();
        Heap theHeap = new Heap(size);

        for (j = 0; j < size; j++)       // fill array with
        {                        //    random nodes
            int random = (int) (java.lang.Math.random() * 100);
            Node newNode = new Node(random);
            theHeap.insertAt(j, newNode);
            theHeap.incrementSize();
        }

        System.out.print("Random: ");
        theHeap.displayArray();  // display random array

        for (j = size / 2 - 1; j >= 0; j--) { // make random array into heap
            theHeap.trickleDown(j);
        }

        System.out.print("Heap:   ");
        theHeap.displayArray();     // dislay heap array
        theHeap.displayHeap();      // display heap

        for (j = size - 1; j >= 0; j--) {
            Node biggestNode = theHeap.remove();// remove from heap and
            System.out.println("remove node------ "+biggestNode.getKey());
            theHeap.insertAt(j, biggestNode);// 倒序插入，最大的放最后面   store at array end
        }
        System.out.print("Sorted: ");
        theHeap.displayArray();     // display sorted array
    }


    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}


////////////////////////////////////////////////////////////////
class Node {
    private int iData;             // data item (key)

    // -------------------------------------------------------------
    public Node(int key)           // constructor
    {
        iData = key;
    }

    // -------------------------------------------------------------
    public int getKey() {
        return iData;
    }
// -------------------------------------------------------------
}  // end class Node

////////////////////////////////////////////////////////////////
class Heap {
    private Node[] heapArray;
    private int maxSize;           // size of array
    private int currentSize;       // number of items in array

    // -------------------------------------------------------------
    public Heap(int mx)            // constructor
    {
        maxSize = mx;
        currentSize = 0;
        heapArray = new Node[maxSize];
    }

    // -------------------------------------------------------------
    public Node remove()           // delete item with max key
    {                           // (assumes non-empty list)
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }  // end remove()

    // -------------------------------------------------------------
    public void trickleDown(int index) {
        int largerChild;
        Node top = heapArray[index];        // save root
        while (index < currentSize / 2)        // not on bottom row
        {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            // find larger child
            if (rightChild < currentSize &&   // right ch exists?
                    heapArray[leftChild].getKey() <
                            heapArray[rightChild].getKey())
                largerChild = rightChild;
            else
                largerChild = leftChild;
            // top >= largerChild?
            if (top.getKey() >= heapArray[largerChild].getKey())
                break;
            // shift child up
            heapArray[index] = heapArray[largerChild];
            index = largerChild;             // go down
        }  // end while
        heapArray[index] = top;             // root to index
    }  // end trickleDown()

    // -------------------------------------------------------------
    public void displayHeap() {
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0;                          // current item
        String dots = "...............................";
        System.out.println(dots + dots);      // dotted top line

        while (currentSize > 0)              // for each heap item
        {
            if (column == 0)                  // first item in row?
                for (int k = 0; k < nBlanks; k++)  // preceding blanks
                    System.out.print(' ');
            // display item
            System.out.print(heapArray[j].getKey());

            if (++j == currentSize)           // done?
                break;

            if (++column == itemsPerRow)        // end of row?
            {
                nBlanks /= 2;                 // half the blanks
                itemsPerRow *= 2;             // twice the items
                column = 0;                   // start over on
                System.out.println();         //    new row
            } else                             // next item on row
                for (int k = 0; k < nBlanks * 2 - 2; k++)
                    System.out.print(' ');     // interim blanks
        }  // end for
        System.out.println("\n" + dots + dots); // dotted bottom line
    }  // end displayHeap()

    // -------------------------------------------------------------
    public void displayArray() {
        for (int j = 0; j < maxSize; j++)
            System.out.print(heapArray[j].getKey() + " ");
        System.out.println("");
    }

    // -------------------------------------------------------------
    public void insertAt(int index, Node newNode) {
        heapArray[index] = newNode;
    }

    // -------------------------------------------------------------
    public void incrementSize() {
        currentSize++;
    }
// -------------------------------------------------------------
}  // end class Heap