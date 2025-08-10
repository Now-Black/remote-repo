package test;

import java.util.PriorityQueue;
import java.util.Scanner;

public class ListNode_acm_tra {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }
    private static ListNode readList(Scanner sc) {
        int n = sc.nextInt(); // 链表长度
        ListNode dummy = new ListNode(0); // 哑节点
        ListNode current = dummy;

        for (int i = 0; i < n; i++) {
            current.next = new ListNode(sc.nextInt());
            current = current.next;
        }

        return dummy.next;
    }
    public static void main(String[] args) {
        // 读取输入并构建链表
        Scanner sc = new Scanner(System.in);
        ListNode l1 = readList(sc);
        ListNode l2 = readList(sc);
        ListNode result = addTwoNumbers(l1, l2);
        printList(result);
    }
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
    private static ListNode addTwoNumbers(ListNode l1,ListNode l2){
        return null;
    }

}
