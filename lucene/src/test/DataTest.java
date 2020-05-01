package test;

import org.junit.Test;

/**
 * @author Chen Kaihong
 * 2019-11-25 22:18
 */
public class DataTest {

    @Test
    public void test () {

        LinkedQueue queue = new LinkedQueue();
        queue.add(new Node(1));
        queue.add(new Node(2));
        queue.add(new Node(3));
        System.out.println(queue.out().content);
        queue.traversal();
        queue.add(new Node(4));
        System.out.println(queue.out().content);
        System.out.println(queue.out().content);
        System.out.println(queue.out().content);
        System.out.println(queue.out().content);



    }
}

class Queue {
    private int maxSize;

    /**
     * 指向队列的第一个元素
     */
    private int front;

    /**
     * 指向最后一个元素的后一个位置
     */
    private int rear;
    private int[] arr;

    public  Queue () {
        this(4);
    }

    public Queue(int maxSize) {
        this.front = 0;
        this.rear = 0;
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
    }

    public boolean isFull () {
        return (rear+1) % maxSize == front;
    }

    public boolean isEmpty () {
        return front == rear;
    }

    public void add (int i) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        } else {
            arr[rear] = i;
            rear = (rear + 1 ) % maxSize;
        }
    }

    public int num () {
        return (rear + maxSize - front) % maxSize;
    }

    public int out () {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        } else {
            int tmp = arr[front];
            front = (front + 1 ) % maxSize;
            return tmp;
        }


    }



}

class LinkedQueue {

    private Node head;

    private Node lastNode;


    public LinkedQueue () {
        this.head = new Node(null);
        this.lastNode = this.head;
    }

    public boolean isEmpty () {
        return this.head.nextNode == null;
    }

    public void add (Node node) {
        if (head.nextNode == null) {
            head.nextNode = node;
            lastNode = node;
        } else {
            lastNode.nextNode = node;
            this.lastNode = node;
        }

    }

    public Node out() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        Node first = head.nextNode;
        head.nextNode = head.nextNode.nextNode;
        return first;
    }

    public void traversal () {
        Node curr = head;
        while (curr.nextNode != null) {
            System.out.println(curr.nextNode.content);
            curr = curr.nextNode;
        }
    }



}


class Node {
    public int content;
    public Node nextNode;

    public Node(){}

    public Node (int content) {
        this.content = content;
    }

    public Node (Node nextNode) {
        this.nextNode = nextNode;
    }

    public Node (int content, Node nextNode) {
        this.content = content;
        this.nextNode = nextNode;
    }
}