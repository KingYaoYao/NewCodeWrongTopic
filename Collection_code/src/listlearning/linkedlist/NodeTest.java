package listlearning.linkedlist;

/**
 * @Author: Dyk
 * @Date: Created in 21:43 2020/11/25
 */
public class NodeTest {
    public static void main(String[] args) {
        //��ʼ���������õ�һ��ͷ�ڵ㣬������Ϊ1��ָ����Ϊnull
       Node linknode = new Node();
       linknode.initNode();
        System.out.println(linknode);
        linknode.insertNode("�Ұ�");
        linknode.insertNode("��");
        linknode.insertNode("����");
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
