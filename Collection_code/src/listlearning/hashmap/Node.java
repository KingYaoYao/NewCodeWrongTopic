package collection.hashmap;

/**
 * 设计一个简单的HashMap,用来学习散列表的相关知识
 * 解决哈希冲突采用单向链表
 * 参考：https://www.jianshu.com/p/a89e9487a06c
 * 参考：https://blog.csdn.net/samniwu/article/details/90550196
 *
 * 设置节点对象Node
 */
 class Node<K,V> {
     K key;     //键
     V value;   //值
    Node<K,V> next ;    //下一个节点对象

    public Node(K key, V value, Node<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

}
