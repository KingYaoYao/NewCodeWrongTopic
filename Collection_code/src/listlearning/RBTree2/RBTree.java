package listlearning.RBTree2;

/**
 * @Author: Dyk
 * @Date: Created in 21:43 2020/12/1
 */

/**
 * 1.����RBTree,������ɫ
 * 2.����RBNode
 * 3.�����������壺parentOf(node)��isRed(node)��setBlack(node)��inOrderPrint()
 * 4.�����������壺leftRotate(node)
 * 5.�����������壺rightRotate(node)
 * 6.��������ӿڷ������壺insert��K key,V value��
 * 7.�ڲ�����ӿڷ����������壺insert(RBNode node)
 * 8.����������º����ʧ��ķ������壺insertFIxUP(RBNode node)
 * 9.���Ժ������ȷ��
 * @param <K>
 * @param <V>
 */
public class RBTree<K extends Comparable<K>,V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private RBNode root;

    /**
     * ��ȡ��ǰ�ڵ�ĸ��ڵ�
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
     * �Ƿ�Ϊ��ɫ
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
     * �Ƿ�Ϊ��ɫ
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
     * ���ýڵ�Ϊ��ɫ
     * @param node
     */
    private void  setRed(RBNode node){
        if (node != null){
            node.color = RED;
        }
    }

    /**
     * ���ýڵ�Ϊ��ɫ
     * @param node
     */
    private void  setBlack(RBNode node){
        if (node != null){
            node.color = BLACK;
        }
    }

    /**
     * �����ӡ������
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
     * ��������:
     *      1.����x-ly�Ĺ�ϵ����y�����ӽڵ�ĸ��ڵ����Ϊx������x�����ӽڵ�ָ��y�����ӽڵ㣨ly��
     *      2.����p-y�Ĺ�ϵ����ָ����������x�ĸ��ڵ㣨��Ϊ��ʱ��������y�ĸ��ڵ����Ϊx�ĸ��ڵ㣬����x�ĸ��ڵ�ָ����������ǰx��λ�ã���ָ��Ϊy��
     *      3.��x�ĸ��ڵ����Ϊy����y�����ӽڵ����Ϊx
     * @param x
     */
    private void leftRotate(RBNode x){
        RBNode y = x.right;
        //����x-ly�Ĺ�ϵ
        x.right = y.left;
        if (y.left != null){
            y.left.parent = x;
        }
         //����p-y�Ĺ�ϵ
        if (x.parent != null){
            y.parent = x.parent;
            //ָ������
            if (x == x.parent.left){
                x.parent.left = y;
            }else {
                x.parent.right = y;
            }
        }else {//ΪnullʱΪ=���ڵ�����
            this.root = y;
            y.parent = null;
        }
        //3.���¸��ڵ�
        x.parent = y;
        y.left = x;

    }

    /**
     *��������
     * @param y
     */
    private void rightRotate(RBNode y){
        RBNode x = y.right;
        //����y-ly����ϵ
        y.left = x.right;
        if (x.right != null){
            x.right.parent = y;
        }
        //���� p-x ��ָ����Ӧ����λ��
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
        //3���¸��ڵ�
        y.parent = x;
        x.right = y;
    }

    /**
     * �����Ĳ��뷽��
     * @param key
     * @param value
     */
    public void insert(K key,V value){
        RBNode node = new RBNode();
        node.setKey(key);
        node.setValue(value);
        //�½ڵ�Ĭ��Ϊ��ɫ
        node.setColor(RED);
    }

    /**
     * ˽�еĲ��뷽�������������
     * @param node
     */
    private void insert(RBNode node){
        //��һ�������ҵ�ǰnode�ĸ��ڵ�
        RBNode parent = null;
        RBNode x = this.root;
        while ( x != null){
            parent = x;
            //cmp > 0˵��node.key���� x.key ��Ҫ��x������������
            // cmp == 0 ˵�� x.key���� node.key ˵��Ҫ�����滻����
            // cmp < 0 ˵��node.key С�� x.key ��Ҫ��x������������
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

        //�ж�node �� parent
        if (parent != null){
            int cmp = node.key.compareTo(parent.key);
            if (cmp > 0) {//��ǰnode��key��parent��key����Ҫ��node����parent�����ӽڵ�
                parent.right = node;
            }else {//��ǰnode��key��parent��key����Ҫ��node����parent�����ӽڵ�
                parent.left = node;
            }
        }else {
            this.root = node;
        }

        //��Ҫ�����޸�������ķ���
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

