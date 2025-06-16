package test;

import java.util.List;
import java.util.Scanner;

public class ListNode_ACM {
    static class ListNode{
        ListNode next;
        ListNode pre;
        int val;
        public ListNode(int val){
            this.val = val;
            this.next = null;
            this.pre  = null;
        }
    }
    static class DoubleListnode{
        private ListNode head;
        private ListNode tail;

        public DoubleListnode(){
            head = null;
            tail = null;
        }
        public void inserthead(int val){
            ListNode node = new ListNode(val);
            if(head == null){
                head = node;
                tail = node;
            }else {
                node.next = head;
                head.pre = node;
                head = node;
            }
        }
        public void inserttail(int val){
            ListNode node = new ListNode(val);
            if(head == null){
                head = node;
                tail = node;
            }else {
                tail.next = node;
                node.pre = tail;
                tail = node;
            }
        }
        public void reverse_node(){
            ListNode temp = null;
            ListNode now = head;
            while (now != null){
                temp = now.pre;
                now.pre = now.next;
                now.next = temp;
                now = now.pre;
            }
            temp = head;
            head = tail;
            tail = temp;
        }
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        DoubleListnode list = new DoubleListnode();

        while (s.hasNext()){
            String op = s.next();
            switch (op){
                case "reverse":
                    list.reverse_node();
                    break;
                case "in_head":
                    int a = s.nextInt();
                    list.inserthead(a);
                    break;

            }
            s.close();
        }



    }


}
