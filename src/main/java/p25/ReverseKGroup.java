package p25;

import java.util.List;
import java.util.function.Consumer;

public class ReverseKGroup<T> {

  public ListNode<T> createList(List<T> list) {
    ListNode<T> head = null;
    ListNode<T> cur = head;

    for (T t: list) {
      ListNode<T> node = new ListNode<>(t);
      if (head == null) {
        head = node;
        cur = head;
      } else {
        cur.next = node;
        cur = node;
      }
    }

    return head;
  }

  public void traverse(ListNode<T> head, Consumer<T> consumer) {
    while (head != null) {
      consumer.accept(head.val);
      head = head.next;
    }
  }

  public ListNode<T> reverseKGroup(ListNode<T> head, int k) {
    if (k == 1) return head;

    ListNode<T> cur = head, kHead = head;
    int advance = 1;

    while (cur != null) {
  
      while (advance < k && cur != null) {
        cur = cur.next;
        advance += 1;
      }

      // Discard the last < K part.
      if (cur == null) break;

      // Reverse k group, from kHead to tail (cur).
      ListNode<T> c = kHead == head ? head: kHead.next;
      ListNode<T> newHead = null, newTail = c;
      ListNode<T> tail = cur.next;
      while (c!= null && c != tail) {
        ListNode<T> n = c.next;
        c.next = newHead;
        newHead = c;
        c = n;
      }

      newTail.next = tail;

      // join head
      if (kHead == head) {
        head = newHead;
      } else {
        kHead.next = newHead;
      }

      // Reset advance.
      advance = 1;
      // cur is every k group tail, which is the head of next kGroup.
      kHead = newTail;
      cur = tail;
    }

    return head;
  }


  public static void main(String[] args) {
    ReverseKGroup<Integer> reverseKGroup = new ReverseKGroup<>();

    ListNode<Integer> head = reverseKGroup.createList(List.of(1, 2, 3, 4, 5));
    reverseKGroup.traverse(head, (Integer t) -> System.out.println(t));


    ListNode<Integer> reversedHead = reverseKGroup.reverseKGroup(head, 2);

    reverseKGroup.traverse(reversedHead, (Integer t) -> System.out.println(t));
  }  
}

class ListNode<T> {
  T val;
  ListNode<T> next;

  public ListNode(T v) {
    this.val = v;
    this.next = null;
  }

  public ListNode(T v, ListNode<T> next) {
    this.val = v;
    this.next = next;
  }
}
