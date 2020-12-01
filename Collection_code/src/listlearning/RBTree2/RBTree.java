package collection.RBTree2;

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
