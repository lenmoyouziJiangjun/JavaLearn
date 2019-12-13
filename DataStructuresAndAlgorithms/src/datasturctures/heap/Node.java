package datasturctures.heap;

/**
 * Version 1.0
 * Created by lll on 17/5/11.
 * Description 堆节点
 * copyright generalray4239@gmail.com
 */
public class Node {
  //节点数据
  private int iData;

  public Node(int var1) {
    this.iData = var1;
  }

  public int getKey() {
    return this.iData;
  }

  public void setKey(int var1) {
    this.iData = var1;
  }
}
