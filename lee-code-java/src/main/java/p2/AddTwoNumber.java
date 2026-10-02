package p2;

import common.ListNode;
import common.LinkedUtils;
public class AddTwoNumber {

  private static class SumContext {
    // Carry the tens digit into the next node's sum.
    private int mod = 0;

    private int compute(int sum) {
      this.mod = sum / 10;
      return sum % 10;
    }

    public int mergeTwo(int first, int second) {
      return compute(first + second + mod);
    }

    public int mergeOne(int first) {
      return compute(first + mod);
    }
  }

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    // Keep a dummy head so every result digit uses the same append path.
    ListNode head = new ListNode();
    ListNode cur = head;

    SumContext sum = new SumContext();

    for (; l1 != null && l2 != null; l1 = l1.next, l2 = l2.next) {
      cur = cur.next = new ListNode(sum.mergeTwo(l1.val, l2.val), null);
    }

    for (; l1 != null; l1 = l1.next) {
      cur = cur.next = new ListNode(sum.mergeOne(l1.val), null);
    }

    for (; l2 != null; l2 = l2.next) {
      cur = cur.next = new ListNode(sum.mergeOne(l2.val), null);
    }

    if (sum.mod > 0) cur.next = new ListNode(sum.mod, null);

    return head;
  }

  public static void main(String[] args) {
    ListNode l1 = LinkedUtils.createLinkedList(new int[] { 2, 4, 3 });
    ListNode l2 = LinkedUtils.createLinkedList(new int[] { 5, 6, 4 });

    LinkedUtils.printLinkList(l1);
    LinkedUtils.printLinkList(l2);

    ListNode sumList = addTwoNumbers(l1.next, l2.next);

    LinkedUtils.printLinkList(sumList);

  }
}
