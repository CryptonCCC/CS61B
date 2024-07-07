public class LinkedListDeque<> {
    private int size;
    private node sentinel;

    public class node {
        private node pre;
        private T item;
        private node next;

        public node(node ppre, T iitem, node nnext) {
            pre = ppre;
            item = iitem;
            next = nnext;
        }

    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new node(null, null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        node first = new node(sentinel, item, sentinel.next);
        sentinel.next.pre = first;
        sentinel.next = first;
        size++;
    }

    public void addLast(T item) {
        node last = new node(sentinel.pre, item, sentinel);
        sentinel.pre.next = last;
        sentinel.pre = last;
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first_item = sentinel.next.item;
        sentinel.next.next.pre = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return first_item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last_item = sentinel.pre.item;
        sentinel.pre.pre.next = sentinel;
        sentinel.pre = sentinel.pre.pre;
        size--;
        return last_item;
    }

    public void printDeque() {
        node p = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        node p = sentinel;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.item;
    }

    private T getRecursivehelper(int index, node front) {
       if (index == 0) {
            return front.item;
       }
       return getRecursivehelper(index - 1, front.next);
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursivehelper(index, sentinel.next);
    }
}
