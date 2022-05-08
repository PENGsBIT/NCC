package P2;/**
 * @program: NCC
 * @author: zpc
 * @create: 2022-05-08 19:49
 **/

import java.util.Stack;

/**
 * @Author: zpc
 * @Description:
 * @Create: 2022-05-08 19:49
 **/


public class MyQueue {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(3);
        myQueue.push(4);
        myQueue.push(5);
        System.out.println(myQueue.toString());
        myQueue.peek();
        System.out.println(myQueue.toString());
        myQueue.pop();
        System.out.println(myQueue.toString());
    }

    private Stack<Integer> s1;
    private Stack<Integer> s2;

    public MyQueue() {
        this.s1 = new Stack<>();
        this.s2 = new Stack<>();
    }

    public void push(int x) {
        s1.push(x);
    }


    public int pop() {
        if (empty()) {
            return -1;
        }
        if (s2.empty()) {
            while (!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }


    public int peek() {
        if (empty()) {
            return -1;
        }
        if (s2.empty()) {
            while (!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    @Override
    public String toString() {
        return "MyQueue{" +
                "s1=" + s1 +
                ", s2=" + s2 +
                '}';
    }
}
