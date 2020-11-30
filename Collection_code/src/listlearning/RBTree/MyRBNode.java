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
     *             p                            p
     *            /                            /
     *           x            对x左旋          y
     *          / \         ------->         /\
     *         lx  y                        x  y
     *            /\                       / \  \
     *          ly ry                     lx ly  ry
     * @param x
     */
    public void  leftRotate(RBNode<T,D> x){
        RBNode<T,D> y = x.rightChild; //得到右子节点
        if (y.leftChild != null){
            y.leftChild.parent = x;
        }
        x.rightChild = y.leftChild;
        y.leftChild = x;
        y.parent = x.parent;

        if (x.parent != null){
            if (x.parent.leftChild == x){
                x.parent.leftChild = y;
            }else {
                x.parent.rightChild = y;
            }
        }else {
            this.root = y;
        }
        x.parent = y;
    }
}
