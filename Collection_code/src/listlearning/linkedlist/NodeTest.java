package listlearning.linkedlist;

/**
 * @Author: Dyk
 * @Date: Created in 21:43 2020/11/25
 */
public class NodeTest {
    public static void main(String[] args) {
        //初始化链表，并得到一个头节点，数据域为1，指针域为null
       Node linknode = new Node();
       linknode.initNode();
        System.out.println(linknode);
        linknode.insertNode("我爱");
        linknode.insertNode("你");
        linknode.insertNode("世界");
        final int i = linknode.traverse();
        System.out.println(i);
        System.out.println("=========================");
        final int size = linknode.getSize();
        System.out.println(size);
        final boolean b = linknode.deleteNode(2);
        System.out.println(b);
        System.out.println(linknode);
        System.out.println(linknode.getSize());

    }

}
