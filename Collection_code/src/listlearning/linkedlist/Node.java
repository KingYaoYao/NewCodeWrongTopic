package listlearning.linkedlist;

/**
 * @Author: Dyk
 * @Date: Created in 21:36 2020/11/25
 * ����һ������Ϊ�ڵ�
 */
public class Node {
    //����������
    public String data ;
    //ָ����ָ����һ���ڵ�
    public Node next;

    int size = 0;

    public  int getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        return "data=" + data +
                " next ->\t" + next ;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node() {
    }

    public Node(String data, Node next) {
        this.data = data;
        this.next = next;
    }

    /**
     * ��ʼ��һ�������ͷ�ڵ�
     * @param
     */
    public void initNode(){
        this.setNext(null);
        this.setData("#");
        this.size++;
    }

    /**
     * ����һ������
     * @param
     * @param data
     */
    public void  insertNode(String data){
        Node temp = this;
        Node newNode = new Node();
        for (int i = 0;;){
            if (temp.next == null){
                newNode.setData(data);
                newNode.setNext(null);
                temp.setNext(newNode);
                System.out.println("Insert\t" + data + "\tsuccess");
                this.size++;
                break;
            }else {
                temp = temp.next;
            }

        }
    }

    /**
     * ��������������������
     * @param
     * @return
     */
    public  int traverse(){
        Node temp = this;
        int count = 1;
        System.out.print(temp);
        while (temp.next != null){
            temp = temp.next;
            count++;
        }
        return count;
    }

    public  boolean deleteNode(int index){
        int count = 1;
        Node temp = this;
        if (index <= size) {
            for (; ; ) {
                if (count == index) {
                    temp.setNext(temp.next.next);
                    this.size--;
                    return true;
                } else {
                    count++;
                }
            }
        }
        return false;

    }
}
