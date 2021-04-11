package datasturctures.tree;

import java.util.HashMap;

/**
 * Version 1.0
 * Created by lll on 2019-12-04.
 * Description
 * <pre>
 *   红黑树
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class RedBlackTree {

    HashMap map = new HashMap();

}

/**
 * 红黑树节点的设计
 */
class RedBlackNode {
    int data;
    boolean isblack;
    RedBlackNode parent;
    RedBlackNode left;
    RedBlackNode right;
}
