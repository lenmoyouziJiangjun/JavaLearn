package datasturctures.tree;

/**
 * Version 1.0
 * Created by lll on 2019-12-24.
 * Description
 * <pre>
 *   堆
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class Heap {
  /**
   *
   * 如果从1开始
   *
   * 如果节点的下标是 i，那左子节点的下标就是 2∗i，右子节点的下标就是 2∗i+1，父节点的下标就是 i/2​。
   *
   *
   * 如果从0开始
   * 如果节点的下标是 i，那左子节点的下标就是 2∗i+1，右子节点的下标就是 2∗i+2，父节点的下标就是 (i−1)/2​。
   *
   *
   */
  public int[] heapDataArray;//数组实现堆，节点n，左子节点2N，右子节点2N+1;  如果N是0的话，左子节点2n+1；

  private int n; //堆可以存储的最大数据个数

  private int count;//堆中已经存储的数据个数

  public Heap(int capacity) {
    heapDataArray = new int[capacity + 1];
    n = capacity;
    count = 0;
  }

  public void insert(int data) {
    if (count >= n) {
      return;
    }
    ++count; //为什么++；因为我们从1开始，这样2n不为0
    heapDataArray[count] = data;
    int i = count;
    while (i / 2 > 0 && heapDataArray[i] > heapDataArray[i / 2]) {
      //我们建立的大顶堆，如果插入元素比父节点大，交换位置
      swap(heapDataArray, i / 2, i);
      i = i >> 1; //继续下一个节点的判断。 从下往上堆化
    }
  }

  /**
   * 删除堆顶元素
   *
   * @return
   */
  public int delete() {
    if (count == 0) {
      return -1;
    }
    int temp = heapDataArray[1];
    heapDataArray[1] = heapDataArray[count];// 把最后一个元素赋值给堆顶，
    //然后从上往下堆化
    --count;
    heapify(heapDataArray, count, 1);
    return temp;
  }

  /**
   * 从上往下堆化
   *
   * @param array 数组
   * @param n 数组长度
   * @param i 对顶元素索引
   */
  private void heapify(int[] array, int n, int i) {
    while (true) {
      int maxPox = i;
      if (i * 2 <= n && array[i] < array[i * 2]) { //如果堆根小于左子节点
        maxPox = i * 2;
      }
      if ((i * 2 + 1) <= n && array[i] < array[i * 2 + 1]) { //根节点小于右子节点，将右子节点置为根位置
        maxPox = i * 2 + 1;
      }
      if (maxPox == i) { //如果最大节点位置就是根位置，停止循环
        break;
      }
      swap(array, i, maxPox); //交换根节点和大于根节点的子节点
      i = maxPox;
    }
  }

  /**
   * @param data
   * @param formIndex
   * @param toIndex
   */
  public void swap(int[] data, int formIndex, int toIndex) {
    int temp = data[formIndex];
    data[formIndex] = data[toIndex];
    data[toIndex] = temp;
  }

}
