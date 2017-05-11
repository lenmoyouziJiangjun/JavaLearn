package datasturctures;

/**
 * Version 1.0
 * Created by lll on 17/5/11.
 * Description 堆
 * 一、堆的特点：
 *    1、堆实际上是一棵完全二叉树
 *    2、根节点数据大于所有子节点数据
 * 二、节点
 *     1、插入和删除的时间复杂度为 logN
 *     2、有很多插入操作时使用堆实现的优先级队列
 * copyright generalray4239@gmail.com
 */
public class Heap {
    private Node[] heapArray;//用一个一维数组表示堆，父节点和子节点的关系，
    private int maxSize;
    private int currentSize;

    public Heap(int var1) {
        this.maxSize = var1;
        this.currentSize = 0;
        this.heapArray = new Node[this.maxSize];
    }

    public boolean isEmpty() {
        return this.currentSize == 0;
    }

    /**
     * 插入节点
     * @param var1
     * @return
     */
    public boolean insert(int var1) {
        if (this.currentSize == this.maxSize) {
            return false;
        } else {
            Node var2 = new Node(var1);
            this.heapArray[this.currentSize] = var2;
            this.trickleUp(this.currentSize++);
            return true;
        }
    }

    /**
     * 数据排序
     * @param var1
     */
    public void trickleUp(int var1) {
        int var2 = (var1 - 1) >>> 2;//等价于 var2 = (var1 - 1)/2，但是效率要高些
        Node var3;
        for (var3 = this.heapArray[var1]; var1 > 0 && this.heapArray[var2].getKey() < var3.getKey(); var2 = (var2 - 1) / 2) {
            this.heapArray[var1] = this.heapArray[var2];
            var1 = var2;
        }

        this.heapArray[var1] = var3;
    }

    /**
     * 删除节点，每次从头删除
     * @return
     */
    public Node remove() {
        Node var1 = this.heapArray[0];
        this.heapArray[0] = this.heapArray[--this.currentSize];
        this.trickleDown(0);
        return var1;
    }

    public void trickleDown(int var1) {
        int var2;
        Node var3;
        for (var3 = this.heapArray[var1]; var1 < this.currentSize / 2; var1 = var2) {
            int var4 = 2 * var1 + 1;
            int var5 = var4 + 1;
            if (var5 < this.currentSize && this.heapArray[var4].getKey() < this.heapArray[var5].getKey()) {
                var2 = var5;
            } else {
                var2 = var4;
            }

            if (var3.getKey() >= this.heapArray[var2].getKey()) {
                break;
            }

            this.heapArray[var1] = this.heapArray[var2];
        }

        this.heapArray[var1] = var3;
    }

    public boolean change(int var1, int var2) {
        if (var1 >= 0 && var1 < this.currentSize) {
            int var3 = this.heapArray[var1].getKey();
            this.heapArray[var1].setKey(var2);
            if (var3 < var2) {
                this.trickleUp(var1);
            } else {
                this.trickleDown(var1);
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * 答应堆
     */
    public void displayHeap() {
        System.out.print("heapArray: ");

        for (int var1 = 0; var1 < this.currentSize; ++var1) {
            if (this.heapArray[var1] != null) {
                System.out.print(this.heapArray[var1].getKey() + " ");
            } else {
                System.out.print("-- ");
            }
        }

        System.out.println();
        int var2 = 32;
        int var3 = 1;
        int var4 = 0;
        int var5 = 0;
        String var6 = "...............................";
        System.out.println(var6 + var6);

        while (this.currentSize > 0) {
            if (var4 == 0) {
                for (int var7 = 0; var7 < var2; ++var7) {
                    System.out.print(' ');
                }
            }

            System.out.print(this.heapArray[var5].getKey());
            ++var5;
            if (var5 == this.currentSize) {
                break;
            }

            ++var4;
            if (var4 == var3) {
                var2 /= 2;
                var3 *= 2;
                var4 = 0;
                System.out.println();
            } else {
                for (int var8 = 0; var8 < var2 * 2 - 2; ++var8) {
                    System.out.print(' ');
                }
            }
        }
        System.out.println("\n" + var6 + var6);
    }
}
