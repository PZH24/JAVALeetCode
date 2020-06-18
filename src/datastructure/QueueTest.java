package datastructure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author pzh
 */
public class QueueTest {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
//        //新增、尽量避免是用add remove 方法：因为会报异常
//        queue.offer("a");
//        queue.offer("b");
//        queue.offer("c");
//        queue.offer("d");
//
//        //测试 offer 取数据同时删除数据，element/peek取数据但不删除原有的
//        System.out.println("原数据:"+String.join(",",queue));
//        String a= queue.poll();
//        System.out.println("移除的数据:"+a);
//        System.out.println("pool后的数据:"+String.join(",",queue));
//        String b = queue.element();
//        System.out.println("取得的数据:"+b);
//        System.out.println("element后的数据:"+String.join(",",queue));
//        String c = queue.peek();
//        System.out.println("取得的数据:"+c);
//        System.out.println("peek后的数据:"+String.join(",",queue));

        QueueTest queueTest = new QueueTest();
        queueTest.put("a");
//        System.out.println(queueTest.get());
        queueTest.put("b");
//        System.out.println(queueTest.get());
        queueTest.put("c");
//        System.out.println(queueTest.get());
        queueTest.put("d");
        System.out.println(queueTest.get());
        System.out.println(queueTest.get());
    }
    /**
     * 自定义先进先出数据结构。
     * */
    private class Node{
        Object item;
        Node next;
        Node(Object item){
            this.item = item;
            this.next =null;
        }
    }
    private Node head;
    private Node tail;
    QueueTest ( Object max ) {
        head = new Node(max);
        tail = null;
    }
    QueueTest(){
        head = null;
        tail = null;
    }
    /**判断是否为空*/
    boolean isEmpty(){
        return head ==null;
    }
    /**
     * 获取树数据
     * */
    Object get(){
        //求head的值。
        Object v = head.item;
        //将头部 的 item值成下一个节点。类似于删除的逻辑。
        Node t = head.next;
        head = t;
        return v;
    }
    /**存储树结构*/
    void put(Object item){
        Node t = tail;
        tail = new Node(item);
        if(isEmpty()){
           head = tail;
        }
        else {
            t.next = tail;
        }
    }

}
