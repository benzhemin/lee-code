import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class LinkedListCycle<T> {

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

  public boolean hasCycle(ListNode<T> head) {
    Map<ListNode<T>, Integer> map = new IdentityHashMap<>();
    ListNode<T> cur = head;
    int index = 0;

    while(cur != null) {
      if (map.containsKey(cur)) return true;

      map.put(cur, index);

      index++;
      cur = cur.next;
    }

    return false;
  }

  public boolean hasCycleO1(ListNode<T> head) {
    ListNode<T> slow = head, fast = head;

    while(fast != null && fast.next != null) {
      slow = slow.next;

      // Fast advance twice.
      fast = fast.next.next;

      if (fast == slow) return true;
    }

    return false;
  }

  // The Math      
  // head ──── a steps ──── [cycle start] ──── b steps ──── [meeting point] ──────────────────────────────────── [Point to start]
  //                                                                        └──────────── c steps ────────────┘
  //                                                                             (rest of cycle back to start)
  // When slow and fast meet:
  // - slow traveled: a + b
  // - fast traveled: a + b + (b + c) = a + 2b + c

  // Since fast = 2 × slow:
  // 2(a + b) = a + 2b + c
  // 2a + 2b  = a + 2b + c
  // a        = c          ← KEY INSIGHT

  // a (head → cycle start) equals c (meeting point → cycle start)
  // So: reset one pointer to head, keep the other at the meeting point, advance both one step at a time — they meet exactly at the cycle start.
  public boolean hasCyclePosO1(ListNode<T> head) {
    ListNode<T> slow = head, fast = head;
    boolean hasCycle = false;
    
    ListNode<T> meetingPoint = null;

    while(fast != null && fast.next != null) {
      slow = slow.next;

      // Fast advance twice.
      fast = fast.next.next;

      if (fast == slow) {
        // Because a === c
        // Advance from head head-curosr and meetingpoints meeting-cursor together, if head-curosr == meeting-cursor, step is the relative pos.
        meetingPoint = slow;
        hasCycle = true;
        break;
      }
    }

    // Compute pos
    if (hasCycle) {
      int pos = 0;
      ListNode<T> start = head;
      ListNode<T> cur = meetingPoint;

      while (start != cur) {
        start = start.next;
        cur = cur.next;
        pos += 1; 
      }

      System.err.println(pos);
    }

    return hasCycle;
  }
  
  public static void main(String[] args) {
    LinkedListCycle<Integer> cycleList = new LinkedListCycle<>();

    ListNode<Integer> head = cycleList.createList(List.of(1, 2, 3, 4, 5));
    cycleList.traverse(head, (Integer t) -> System.out.println(t));


    boolean hasCycle = cycleList.hasCycle(head);
    System.out.println(hasCycle);
    
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