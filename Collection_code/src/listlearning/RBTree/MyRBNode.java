package collection.RBTree;

/**
 * 红黑树—JAVA实现
 */
public class MyRBNode<T extends Comparable<T>,D> {
    private RBNode<T,D> root;//根节点
    /**
     * 节点颜色
     */
    private static final Boolean RED = false;
    private static final Boolean BLACK = true;
    /**
     * 获取父节点
     */
    public RBNode<T,D> parentOf(RBNode<T,D> node){
        if (node != null){
            return node.parent;
        }
        return null;
    }
    /**
     *获取颜色
     */
    public Boolean colorOf(RBNode<T,D> node){
        if (node != null){
            return node.color;
        }
        return BLACK;
    }

    /**
     *设置父节点
     */

    public void setParent(RBNode<T,D> node ,RBNode<T,D> parent){
        if (node != null){
            node.parent = node;
        }
    }

    /**
     * 设置节点颜色
     */
    public void setColor(RBNode<T,D> node ,Boolean color){
        if (node != null){
            node.color = color;
        }
    }

    /**
     * 节点是否为Red节点
     */
    public Boolean isRed(RBNode<T,D> node){
        return node!=null && node.color == RED;
    }

    /**
     * 节点是否为Black节点
     */
    public Boolean isBlack(RBNode<T,D> node){
        return !isRed(node);
    }

    /**
     * 将节点设置为红色
     * @param node
     */
    public void setRed(RBNode<T,D> node){
        if (node != null){
            node.color = RED;
        }
    }

    /**
     * 将节点设置为黑色
     * @param node
     */
    public void setBlack(RBNode<T,D> node){
        if (node != null){
            node.color = BLACK;
        }
    }

    /**
     * 根据键找到值
     * @param key
     * @return
     */
    public D get(T key){
        RBNode<T, D> node = search(key,root);
        return node == null? null : node.data;
    }

    /**
     * 根据键找到对应的节点
     * @param key
     * @param node
     * @return
     */
    public RBNode<T,D> search(T key ,RBNode<T,D> node){
        if (node != null){
            //查找的过程就是一直递归比较到子叶为止。
            //com>0 说明要查找的key大于node的key要去node的右子树去查找
            //com<0 说明要查找的key小于node的key要去node的左子树去查找
            //com=0 说明要查找的key等于node的key.
            int com = key.compareTo(node.key);
            if (com < 0){
                return search(key, node.leftChild);
            }else if (com > 0){
                return search(key,node.rightChild);
            }else {
                return node;
            }
        }
        return null;
    }

    /**
     * 寻找后续节点，即大于该节点的最小节点。
     * @param node
     * @return
     */
    public RBNode<T,D> min(RBNode<T,D> node){
        //最左端的就是最小值
        if (node.leftChild == null){
            return node;
        }
        while (node.leftChild != null){
            node = node.leftChild;
        }
        return node;
    }

    /**
     * 寻找待删除节点的后续节点
     * （因为这个节点即将要删除了，所以要选个后续节点补到这个位置来
     * 选择节点的规则：
     * 这个节点和普通二叉树是一样的，要么就是找左子树的最大值，要么就是右子树的最小值）
     * @param node
     * @return
     */
    public RBNode<T,D> successor(RBNode<T,D> node){
        if (node.rightChild != null){
            return min(node.rightChild);
        }
        RBNode<T,D> y = node.parent;
        while ((y != null) && (y.rightChild == node)){
            node = y;
            y = y.parent;
        }
        return y;
    }

    /**
     * 对某个节点进行左旋
     * 当前节点就是父节点，整体过程：父下沉，右子上升，然后右孩子的左节点就成为了原父亲的右节点
     *             p                             p
     *            /                             /
     *           x            对x左旋           y
     *          / \         ------->         /  \
     *         lx  y                        x   ry
     *            /\                       / \
     *          ly ry                     lx ly
     *
     *         1.将y的左子节点的父节点更新为x,并将新的柚子节点指向y的坐姿节点（ly）.
     * @param x
     */
    public void  leftRotate(RBNode<T,D> x) {
        RBNode<T,D> y = x.rightChild;
        //1.将x的右子节点指向y的左子节点（ly）,将y的左子节点的父节点更新为x，建立x-ly的联系
        x.leftChild = y.leftChild;
        if (y.leftChild != null){
            y.leftChild.parent = x;
        }

        //2.建立p-y的关系，并指定子树，当x的父节点（不为空时，为空时为root节点）
        //更新y的父节点为x的父节点，并为x的父节点指定子树，指定为y
        if (x.parent != null){
            y.parent = x.parent;
            //指定子树
            if (x == x.parent.leftChild){//如果x是p的左子树
                x.parent.leftChild = y;
            }else {
                x.parent.rightChild = y;
            }
        }else { //x为根节点，需要更新y为根节点
            this.root = y;
            this.root.parent = null;

        }

        //3.将x的父节点更新为y，将y的左子节点更新为x。
        x.parent = y;
        y.leftChild = x;
    }

    /**
     * 对某个节点进行右旋
     * @param x
     */
    public void rightRotate(RBNode<T,D> x){
        //1.建立x-ry关系。将x的左子节点指向y的右子节点，将y的右子节点的父节点更新为x。
        RBNode<T,D> y = x.leftChild;
        x.leftChild = y.rightChild;
        if(y.rightChild != null){
            y.rightChild.rightChild = x;
        }

        //2.当前x的父节点不为空时，将y的父节点更新为x的父节点。
        //并将x的父节点指定子树，指定为y
        if (x.parent != null){

            y.parent = x.parent;
            if (x == x.parent.leftChild){
                y = x.parent.leftChild;
            }else {
                y = x.parent.rightChild;
            }
        }else {
            this.root = y;
            this.root.parent = null;
        }

        //3,将x的父节点更新为y
        //将y的右子节点更新为x
        x.parent = y;
        y.rightChild = x;
    }

    /**
     * 插入节点后红黑树自平衡方法
     * 
     * @param node
     */
    public void balanceInsertion(RBNode<T,D> node){

    }
}
