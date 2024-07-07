public class ArrayDeque<Item> {
    private int size;
    private Item[] array;
    private  double usage_ratio;
    private int nextfirst;
    private int nextlast;

    public ArrayDeque() {
        array = (Item[]) new Object[8];
        size = 0;
        nextfirst = 0;
        nextlast = 1;
    }

    private int plus1(int i) {
        if (i == array.length - 1) {
            return 0;
        }
        return i + 1;
    }

    private int minus1(int i) {
        if (i == 0) {
            return array.length - 1;
        }
        return i - 1;
    }


    public int size() {
        return size;
    }

    public T get(int i) {
        if (i < 0 || i >= size) {
            return null;
        }
        int p = plus1(nextfirst);
        for (int j = 0; j < i; j++) {
            p = plus1(p);
        }
        return array[p];
    }

    private void resize(int newSize) {
        T[] newarray = (T[]) new Object[newSize];
        int p = plus1(nextfirst);
        for (int i = 0; i < size; i++) {
            newarray[i] = array[p];
            p = plus1(p);
        }
        nextfirst = newSize - 1;
        nextlast = size;
        array = newarray;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        int p = plus1(nextfirst);
        for (int i = 0; i < size; i++) {
            System.out.print(array[p] + " ");
            p = plus1(p);
        }
    }

    public void addFirst(T item) {
        if (size == array.length) {
            resize(size * 2);
        }
        array[nextfirst] = item;
        nextfirst = minus1(nextfirst);
        size++;
        usage_ratio = (double) size / (double) array.length;
    }

    public void addLast(T item) {
        if (size == array.length) {
            resize(size * 2);
        }
        array[nextlast] = item;
        nextlast = plus1(nextlast);
        size++;
        usage_ratio = (double) size / (double) array.length;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T ret =  array[plus1(nextfirst)];
        array[plus1(nextfirst)] = null;
        nextfirst = plus1(nextfirst);
        size--;
        usage_ratio = (double) size / (double) array.length;
        if (usage_ratio <= 0.25 && array.length >= 16) {
            resize(array.length / 2);
        }
        return ret;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T ret = array[minus1(nextlast)];
        array[minus1(nextlast)] = null;
        nextlast = minus1(nextlast);
        size--;
        usage_ratio = (double) size / (double) array.length;
        if (usage_ratio <= 0.25 && array.length >= 16) {
            resize(array.length / 2);
        }
        return ret;
    }
}
