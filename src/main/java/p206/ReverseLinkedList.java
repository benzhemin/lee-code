package p206;

import java.util.List;
import java.util.function.Consumer;

public class ReverseLinkedList<T> {

  public ListNode<T> createList(List<T> list) {
    ListNode<T> head = null;
    ListNode<T> cur = head;

    for (T t : list) {
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

  public ListNode<T> reverseList(ListNode<T> head) {
    ListNode<T> newHead = null;

    ListNode<T> cur = head;
    while (cur != null) {
      ListNode<T> node = cur;
      cur = cur.next;

      node.next = newHead;
      newHead = node;
    }

    return newHead;
  }

  public static void main(String[] args) {
    ReverseLinkedList<Integer> reverseLinkedList = new ReverseLinkedList<>();

    ListNode<Integer> head = reverseLinkedList.createList(List.of(1, 2, 3, 4, 5));
    reverseLinkedList.traverse(head, (Integer t) -> System.out.println(t));

    ListNode<Integer> reversedHead = reverseLinkedList.reverseList(head);

    reverseLinkedList.traverse(reversedHead, (Integer t) -> System.out.println(t));
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
