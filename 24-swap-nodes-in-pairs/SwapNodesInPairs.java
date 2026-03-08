import java.util.List;
import java.util.function.Consumer;

public class SwapNodesInPairs<T> {

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

  public ListNode<T> swapInPairs(ListNode<T> head) {
    ListNode<T> swapHead = head, first = head, second = head;

    while (first != null && first.next != null) {
      second = first.next;

      // Swap
      first.next = second.next;
      second.next = first;

      // Head case
      if (first == head) {
        head = second;
      } else {
        swapHead.next = second;
      }

      swapHead = first;
      first = first.next;
    }

    return head;
  }
  
  public static void main(String[] args) {
    SwapNodesInPairs<Integer> snip = new SwapNodesInPairs<>();

    ListNode<Integer> head = snip.createList(List.of(1, 2, 3, 4));
    snip.traverse(head, (Integer t) -> System.out.println(t));

    ListNode<Integer> swapedHead = snip.swapInPairs(head);
    snip.traverse(swapedHead, (Integer t) -> System.out.println(t));
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
