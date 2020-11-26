package collection.hashmap;

public class ZsHashMap<K,V> {
    //存储头结点的数组
    private Node<K,V>[] nodes;
    //元素个数
    private int size;
    //默认容量
    private static int defaultCapacity = 16;
    //扩展因子
    private static float defaultLoadFactor = 0.75f;
    public ZsHashMap(){}
    //指定大小和扩展因子
    public ZsHashMap(int capacity,int loadFactor){
        defaultCapacity = capacity;
        defaultLoadFactor = loadFactor;
    }
    //添加元素
    public V put(K key,V value){
        //初始化数组
        if (nodes == null){
            nodes = new Node[defaultCapacity];
        }
        int index = hash(key); //计算存储的索引
        //获取到数组索引元素，可视为头结点
        Node<K,V> node = nodes[index];
        //遍历链表中节点对象
        while (node != null){
            //存在重复key将value替换
            if (node.key.equals(key)){
                node.value = value;
                return value;
            }else {
                node = node.next;
            }
        }
        //判断是否需要扩展defaultCapacity为数组长度
        //defaultLoadFactor为扩展因子默认0.75
        if(size >= defaultCapacity * defaultLoadFactor){
            resize();
        }
        //将新添加的数据最为头结点添加到数组中
        nodes[index] = new Node<>(key,value,nodes[index]);
        size++;
        return value;
    }

    public void resize() {
        //扩容后要对元素重新Put(重新三列)，所以讲size置为0
        size = 0;
        //记录之前的数组
        Node<K,V>[] oldNodes = nodes;
        defaultCapacity = defaultCapacity *2;
        nodes = new Node[defaultCapacity];
        //遍历散列表中每个元素
        for (int i = 0; i <oldNodes.length ; i++) {
            //扩容后hash值会改变，所以要重新散列
            Node<K,V> node = oldNodes[i];
            while (node != null){
                Node<K,V> oldNode = node;
                put(node.key,node.value);//散列
                node = node.next;//角标往后移
                oldNode.next = null;//将当前散列的节点next置为null
            }
        }
    }
    //获取一个元素
    public  V get(K key){
        //获取角标位置
        int index = hash(key);
        //获取头节点
        Node<K,V> node = nodes[index];
        if (node != null){
            while (node != null&& !node.key.equals(key)){
                node = node.next;
            }
            if (node == null){
                return null;
            }else {
                return node.value;
            }
        }
        return null;
    }
    public int hash(K key){
        //获得key的hashcode值
        int code = key.hashCode();
        //defaultCapacity为数组长度

        return  code%(defaultCapacity -1);  //计算的是数组的索引
    }
}
