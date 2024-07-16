package synthesizer;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    private int first;
    private int last;
    private T[] rb;

    public ArrayRingBuffer(int capacity) {
        this.rb = (T[]) new Object[capacity];
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
    }

    public void enqueue(T x) {
        if (this.isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        } else {
            this.rb[this.last] = x;
            this.last = (this.last + 1) % this.capacity;
            ++this.fillCount;
        }
    }

    public T dequeue() {
        if (this.isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            T ret = this.rb[this.first];
            this.rb[this.first] = null;
            this.first = (this.first + 1) % this.capacity;
            --this.fillCount;
            return ret;
        }
    }

    public T peek() {
        if (this.isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            return this.rb[this.first];
        }
    }
}
