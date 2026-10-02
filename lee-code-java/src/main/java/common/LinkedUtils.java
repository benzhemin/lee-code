package common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LinkedUtils {

  // Return a dummy head so callers can read the first value from head.next.
  public static ListNode createLinkedList(int[] arr) {
    ListNode head = new ListNode();
    ListNode cur = head;

    for (int i = 0; i < arr.length; i++) {
      ListNode node = new ListNode(arr[i], null);
      cur.next = node;
      cur = node;
    }

    return head;
  }

  public static void printLinkList(ListNode head) {
    ListNode cur = head;
    StringBuffer sb = new StringBuffer();

    while ((cur = cur.next) != null) {
      sb.append(String.format("%s, ", cur.val));
    }

    log.info(sb.toString());
  }
}
