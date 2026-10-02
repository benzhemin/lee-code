export class Node {
  constructor(val, next) {
    this.val = val;
    this.next = next;
  }
}

export const createList = (arr) => {
  // The returned head is a sentinel; values start at head.next.
  const head = new Node(null, null);
  let cur = head;
  arr.forEach((i) => {
    cur = cur.next = new Node(i, null);
  });
  return head;
};


export const printList = (head) => {
  let p = head;
  let str = "";
  while ((p = p.next) != null) {
    if (head.next != p) str += ", "
    str += `${p.val}`;
  }
  console.log(str);
};
