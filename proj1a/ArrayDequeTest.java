import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayDequeTest {
    @Test
    public void Addtest() {
        ArrayDeque<Integer> AD1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 50; i++) {
            AD1.addFirst(i);
        }
        for (int i = 0; i < 25; i++) {
            AD1.removeLast();
        }
        AD1.printDeque();
    }

    @Test
    public void Removetest() {
        ArrayDeque<Integer> AD1 = new ArrayDeque<Integer>();
        AD1.addLast(1);
        AD1.addLast(2);
        AD1.addLast(3);
        AD1.removeFirst();
        AD1.removeLast();
        AD1.printDeque();
    }


}