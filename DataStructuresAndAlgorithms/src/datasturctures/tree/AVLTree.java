package datasturctures.tree;

import java.util.PriorityQueue;

/**
 * Version 1.0
 * Created by lll on 2020-06-23.
 * Description
 * <pre>
 *     平衡二叉查找树
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class AVLTree {

    private static final int LH = 1; //左子树长高了
    private static final int EH = 0; //等高
    private static final int RH = -1; //右子树长高了

    private AVLNode header;

    /**
     * @param node
     * @param data
     * @param taller 树是否长高，true，长高， false 没有长高
     */
    boolean insertNode(AVLNode node, int data, boolean taller) {
        if (node == null) {
            node = new AVLNode(data);
            node.bf = 0;
            taller = true; //插入了一个节点，长高
        } else {
            if (node.data == data) {//已经有相同数据，插入失败
                taller = false;
                return false;
            } else if (data < node.data) {//插入数据小于节点
                if (insertNode(node.left, data, taller)) { //左边节点插入成功
                    if (taller) {
                        switch (node.bf) {
                            case LH://原本左子树比右子树高，又插入了节点，左子树更高，左平衡处理
                                leftBalance(node);
                                taller = false;//平衡了过后，等高
                                break;
                            case EH: // 原本左右子树等高，现在因为左子树增高而使树增高
                                node.bf = LH;
                                taller = true;
                                break;
                            case RH: //原本右子树高，现在左子树插入节点，导致左右等高
                                node.bf = EH;
                                taller = false;
                                break;

                        }
                    }
                } else {
                    return false;
                }
            } else {//插入数据大于当前节点，右插入
                if (insertNode(node.right, data, taller)) {
                    if (taller) {//树有增高
                        switch (node.bf) {
                            case LH: //原本左子树高，插入右节点后，平衡了
                                node.bf = EH;
                                taller = false;
                                break;
                            case EH: //原本相等，插入右节点，右子树增高
                                node.bf = RH;
                                taller = true;
                                break;
                            case RH: //原本右子树就高，又插入右节点，右子树就比左子树高了2个，不平衡了，旋转
                                rightBalance(node);
                                taller = false;
                                break;
                        }
                    }
                } else {//插入失败
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 删除节点
     *
     * @param data
     */
    void deleteNode(int data) {

    }

    /**
     * 左旋节点:
     * 1、就是当前根节点的右子节点作为根，
     * 2、当跟前节点作为右子节点的左子节点，
     * 3、当前跟节点的右子节点的左子节点作为当前节点的右子节点
     *
     * <pre>
     *       x                           y
     *    a     y      ===左旋==》     x      r
     *        b   r                 a   b
     *
     * </pre>
     *
     * @param node
     */
    void rotateLeft(AVLNode node) {

        AVLNode tmp = node.right;//记录当前节点的右子节点

        node.right = tmp.left;//当前节点的右子节点，更改为当前节点右子节点的左子节点

        tmp.left = node; // 右子节点的左子节点为node

        node = tmp; // node 更改为右子节点
    }

    /**
     * 右旋节点
     * 1、当前根节点的左子节点作为根，
     * 2、当前左子节点的右子节点作为当前节点的左子节点
     * 3、当前节点作为新跟节点的右子节点
     *
     * @param node
     */
    void rotateRight(AVLNode node) {
        AVLNode tmp = node.left;
        node.left = tmp.right;
        tmp.right = node;
        node = tmp;
    }

    /**
     * 当前节点的左子树增高，平衡左子树
     *
     * @param node
     */
    void leftBalance(AVLNode node) {
        AVLNode leftTmp = node.left;
        switch (leftTmp.bf) {
            case LH: //插入节点为当前节点的左子节点的左子节点， 右旋
                rotateRight(node);
                node.bf = leftTmp.bf = EH; //右旋过后，等高
                break;
            case RH: //插入节点为当前节点的左子节点的右子节点， 需要两次旋转了
                AVLNode rightTmp = leftTmp.right; //记录左子节点的右子节点
                switch (rightTmp.bf) { //判断右子节点的平衡位置,来修改Node 和左孩子的平衡因子
                    case LH:
                        node.bf = RH;
                        leftTmp.bf = EH;
                        break;
                    case RH:
                        node.bf = EH;
                        leftTmp.bf = LH;
                        break;
                    case EH:
                        node.bf = leftTmp.bf = EH;
                        break;
                }
                rightTmp.bf = EH;
                rotateLeft(node.left); //左旋左子节点
                rotateRight(node); //右旋当前节点
                break;
        }

    }

    /**
     * 右子树增高，平衡右子树
     *
     * @param node
     */
    void rightBalance(AVLNode node) {

    }

    /**
     * @param args
     */
    public static void main(String[] args) {

    }

}

/**
 * 对于avl节点的实现，方案
 */
class AVLNode {
    int data;
    AVLNode left; //左子树
    AVLNode right; //右子树
    AVLNode parent; //parent
    int bf; //记录左右子树哪边高的flag。左子树高 1； 右子树高为-1， 等高为0；

    int leftHeight;//左子树高度
    int rightHeight;//右子树高度

    public AVLNode(int data) {
        this.data = data;
    }
}

