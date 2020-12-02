package listlearning.RBTree2;

/**
 * @Author: Dyk
 * @Date: Created in 21:43 2020/12/1
 */

/**
 * 1.创建RBTree,定义颜色
 * 2.创建RBNode
 * 3.辅助昂发定义：parentOf(node)、isRed(node)、setBlack(node)、inOrderPrint()
 * 4.左旋方法定义：leftRotate(node)
 * 5.右旋方法定义：rightRotate(node)
 * 6.公开插入接口方法定义：insert（K key,V value）
 * 7.内部插入接口方法方法定义：insert(RBNode node)
 * 8.修正插入后导致红黑树失衡的方法定义：insertFIxUP(RBNode node)
 * 9.测试红黑树正确性
 * @param <K>
 * @param <V>
 */
public class RBTree<K extends Comparable<K>,V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private RBNode root;

    /**
     * 获取当前节点的父节点
     * @param node
     * @return
     */
    private RBNode<K,V> parentOf(RBNode<K,V> node){
        if (node != null){
            return node.parent;
        }
        return null;
    }

    /**
     * 是否为红色
     * @param node
     * @return
     */
    private boolean isRed(RBNode node){
        if (node != null){
            return node.color == RED;
        }
        return false;
    }

    /**
     * 是否为黑色
     * @param node
     * @return
     *
     * return node != null && node.color == RED
     */
    private boolean isBlack(RBNode node){
        if (node != null){
            return node.color == BLACK;
        }
        return false;
    }

    /**
     * 设置节点为红色
     * @param node
     */
    private void  setRed(RBNode node){
        if (node != null){
            node.color = RED;
        }
    }

    /**
     * 设置节点为黑色
     * @param node
     */
    private void  setBlack(RBNode node){
        if (node != null){
            node.color = BLACK;
        }
    }

    /**
     * 中序打印二叉树
     */
    public void inOrderPrint(){
        inOrderPrint(this.root);
    }
    private void inOrderPrint(RBNode node){
        if (node != null){
            inOrderPrint(node.left);
            System.out.println("key:" + node.key + "value:" + node.value);
        }
    }

    /**
     * 左旋操作:
     *      1.建立x-ly的关系，将y的左子节点的父节点更新为x，并将x的右子节点指向y的左子节点（ly）
     *      2.建立p-y的关系，并指定子树。当x的父节点（不为空时），更新y的父节点更新为x的父节点，并将x的父节点指定子树（当前x的位置），指定为y。
     *      3.将x的父节点更新为y，将y的左子节点更新为x
     * @param x
     */
    private void leftRotate(RBNode x){
        RBNode y = x.right;
        //建立x-ly的关系
        x.right = y.left;
        if (y.left != null){
            y.left.parent = x;
        }
         //建立p-y的关系
        if (x.parent != null){
            y.parent = x.parent;
            //指定子树
            if (x == x.parent.left){
                x.parent.left = y;
            }else {
                x.parent.right = y;
            }
        }else {//为null时为=根节点的情况
            this.root = y;
            y.parent = null;
        }
        //3.更新父节点
        x.parent = y;
        y.left = x;

    }

    /**
     *右旋操作
     * @param y
     */
    private void rightRotate(RBNode y){
        RBNode x = y.right;
        //建立y-ly的联系
        y.left = x.right;
        if (x.right != null){
            x.right.parent = y;
        }
        //建立 p-x 并指定相应子树位置
        if (y.parent != null){
            x.parent = y.parent;
            if (y == y.parent.left){
                y.parent.left = x;
            }else {
                y.parent.right = x;
            }
        }else {
            this.root = x;
            x.parent = null;
        }
        //3更新父节点
        y.parent = x;
        x.right = y;
    }

    /**
     * 公开的插入方法
     * @param key
     * @param value
     */
    public void insert(K key,V value){
        RBNode node = new RBNode();
        node.setKey(key);
        node.setValue(value);
        //新节点默认为红色
        node.setColor(RED);
    }

    /**
     * 私有的插入方法，重载上面的
     * @param node
     */
    private void insert(RBNode node){
        //第一步：查找当前node的父节点
        RBNode parent = null;
        RBNode x = this.root;
        while ( x != null){
            parent = x;
            //cmp > 0说明node.key大于 x.key 需要到x的右子树查找
            // cmp == 0 说明 x.key等于 node.key 说明要进行替换操作
            // cmp < 0 说明node.key 小于 x.key 需要到x的左子树查找
            int cmp = node.key.compareTo(x.key);
            if (cmp > 0){
                x = x.right;
            }else if (cmp == 0){
                x.setValue(node.getValue());
                return;
            }else {
                x = x.left;
            }
        }

        node.parent = parent;

        //判断node 和 parent
        if (parent != null){
            int cmp = node.key.compareTo(parent.key);
            if (cmp > 0) {//当前node的key比parent的key大，需要把node放入parent的右子节点
                parent.right = node;
            }else {//当前node的key比parent的key大，需要把node放入parent的左子节点
                parent.left = node;
            }
        }else {
            this.root = node;
        }

        //需要调用修复红黑树的方法
//        insertFixUP();
    }




    static class RBNode<K extends Comparable<K>,V>{
        private RBNode parent;
        private RBNode left;
        private RBNode right;
        private boolean color;
        private K key;
        private V value;

        public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, K key, V value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.key = key;
            this.value = value;
        }

        public RBNode() {
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}

