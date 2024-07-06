public class ArrayDeque<T> {
    private int size;
    private T[] array;
    private  double usage_ratio;
    private int nextfirst;
    private int nextlast;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        nextfirst = 0;
        nextlast = 1;
    }

    private int plus1(int i) {
        return (i + 1) % array.length;
    }

    private int minus1(int i) {
        return (i - 1) % array.length;
    }


    public int size() {
        return size;
    }

    public T get(int i) {
        if (i < 0 || i >= size) {
            return null;
        }
        return array[(plus1(nextfirst) + i) % array.length];
    }

    private void resize(int newSize) {
        T[] newarray = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newarray[i] = array[(plus1(nextfirst) + i) % array.length];
        }
        nextfirst = 0;
        nextlast = size - 1;
        array = newarray;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            int p = plus1(nextfirst);
            System.out.print(array[p] + " ");
            plus1(p);
        }
    }

    public void addFirst(T item) {
        if (size == array.length) {
            resize(size * 2);
        }
        array[plus1(nextfirst)] = item;
        nextfirst = minus1(nextfirst);
        size++;
        usage_ratio = (double) size / (double) array.length;
    }

    public void addLast(T item) {
        if (size == array.length) {
            resize(size * 2);
        }
        array[minus1(nextlast)] = item;
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
        if (usage_ratio < 0.25 && array.length >= 16) {
            resize(size / 2);
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
        if (usage_ratio < 0.25 && array.length >= 16) {
            resize(size / 2);
        }
        return ret;
    }
}
