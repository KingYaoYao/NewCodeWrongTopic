package collection.RBTree;

/**
 * 提升了权限，成员变量没有设置成类权限，升级成了包权限。
 * @param <T>
 * @param <D>
 */
 class RBNode<T extends Comparable<T>, D> {
     Boolean color;  //节点颜色
     T key;//键值
     D data;//数据域
     RBNode<T,D> parent; //父节点
     RBNode<T,D> leftChild;//左子节点
     RBNode<T,D> rightChild;//右子节点

    public RBNode(Boolean color, T key, D data, RBNode<T, D> parent, RBNode<T, D> leftChild, RBNode<T, D> rightChild) {
        this.color = color;
        this.key = key;
        this.data = data;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

}
