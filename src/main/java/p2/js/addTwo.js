import { Node, printList, createList } from "./list.js"

const nonNull = (item) => !(item == undefined || item == null);

const modCXT = {
  // Store the carry for the next digit while returning the current digit.
  mod: 0,
  update(sum) {
    this.mod = Math.floor(sum / 10);
    return sum % 10;
  }
}

const mergeSumList = (la, lb) => {
  const head = new Node(null, null);
  let cur = head;

  for (; nonNull(la) && nonNull(lb); la = la.next, lb = lb.next)
    cur = cur.next = new Node(modCXT.update(la.val + lb.val + modCXT.mod), null);

  for (; la != null; la = la.next)
    cur = cur.next = new Node(modCXT.update(la.val + modCXT.mod), null);

  for (; lb != null; lb = lb.next)
    cur = cur.next = new Node(modCXT.update(lb.val + modCXT.mod), null);

  if (modCXT.mod > 0) cur.next = new Node(modCXT.mod, null);

  return head;
};

const l1 = createList([2, 4, 3]);
const l2 = createList([5, 6, 4]);

const nl = mergeSumList(l1.next, l2.next);
printList(nl);
