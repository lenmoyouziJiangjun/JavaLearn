package algorithms.hash;

import java.util.Hashtable;

/**
 * Version 1.0
 * Created by lll on 12/03/2018.
 * Description
 * <pre>
 *    hash算法：
 *    线性探测实现hash冲突算法
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class HashDemo {

}


class HashListTable {
    private DataItem[] hashArray;    // 数组
    private int arraySize;
    private DataItem nonItem;        //

    // -------------------------------------------------------------
    public HashListTable(int size)       // constructor
    {
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem(-1);   // deleted item key is -1
    }

    // -------------------------------------------------------------
    public void displayTable() {
        System.out.print("Table: ");
        for (int j = 0; j < arraySize; j++) {
            if (hashArray[j] != null)
                System.out.print(hashArray[j].getKey() + " ");
            else
                System.out.print("** ");
        }
        System.out.println("");
    }

    //hash算法
    public int hashFunc(int key) {
        return key % arraySize;       // hash function
    }


    public void insert(DataItem item) {
        int key = item.getKey();      // extract key
        int hashVal = hashFunc(key);  // hash the key
        // until empty cell or -1,
        //线性查找的逻辑，对应hashVal索引上的已经有数据了，
        while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
            ++hashVal;                 // go to next cell
            hashVal %= arraySize;      // wraparound if necessary
        }
        hashArray[hashVal] = item;    // insert item
    }

    public DataItem delete(int key)  // delete a DataItem
    {
        int hashVal = hashFunc(key);  // hash the key

        while (hashArray[hashVal] != null)  // until empty cell,
        {                               // found the key?
            if (hashArray[hashVal].getKey() == key) {
                DataItem temp = hashArray[hashVal]; // save item
                hashArray[hashVal] = nonItem;       // delete item
                return temp;                        // return item
            }
            ++hashVal;                 // go to next cell
            hashVal %= arraySize;      // wraparound if necessary
        }
        return null;                  // can't find item
    }

    /**
     *  1、第一步：先根据hash去取值，取出来的值不为null,
     *  2、判断key是否相等，相等就返回，
     *  3、不相等，就是hash冲突了。索引加1继续查找
     * @param key
     * @return
     */
    public DataItem find(int key) {   // find item with key

        int hashVal = hashFunc(key);  // hash the key

        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                return hashArray[hashVal];
            }
            ++hashVal;
            hashVal %= arraySize;
        }
        return null;
    }
}


/**
 * 模拟一个数据结构
 */
class DataItem {
    private int iData;               // data item (key)

    public DataItem(int ii)          // constructor
    {
        iData = ii;
    }

    public int getKey() {
        return iData;
    }
}
