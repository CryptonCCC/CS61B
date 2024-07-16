package synthesizer;

public class GuitarString {
    private static final int SR = 44100;
    private static final double DECAY = 0.996;
    private BoundedQueue<Double> buffer;

    public GuitarString(double frequency) {
        int capacity = (int)Math.round(44100.0 / frequency);
        this.buffer = new ArrayRingBuffer(capacity);

        while(!this.buffer.isFull()) {
            this.buffer.enqueue(0.0);
        }

    }

    public void pluck() {
        while(!this.buffer.isEmpty()) {
            this.buffer.dequeue();
        }

        while(!this.buffer.isFull()) {
            double r = Math.random() - 0.5;
            this.buffer.enqueue(r);
        }

    }

    public void tic() {
        double s = (Double)this.buffer.dequeue();
        double ns = ((Double)this.buffer.peek() + s) * 0.996 * 0.5;
        this.buffer.enqueue(ns);
    }

    public double sample() {
        return (Double)this.buffer.peek();
    }
}
