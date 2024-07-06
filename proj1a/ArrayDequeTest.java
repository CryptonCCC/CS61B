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
        AD1.addFirst(1);
        AD1.addFirst(2);
        AD1.addFirst(3);
        AD1.addFirst(4);
        AD1.addFirst(5);
        AD1.addFirst(6);
        AD1.addFirst(7);
        AD1.addFirst(8);
        AD1.addFirst(9);
        AD1.addFirst(10);
        AD1.removeFirst();
        AD1.removeFirst();
        AD1.removeFirst();
        AD1.removeFirst();
        AD1.removeFirst();
        AD1.removeFirst();
        AD1.removeFirst();
        AD1.removeFirst();
        AD1.removeFirst();
        AD1.removeFirst();
        AD1.addFirst(1);
        AD1.addFirst(2);
        AD1.addFirst(3);
        AD1.addFirst(4);
        AD1.addFirst(5);
        AD1.addFirst(6);
        AD1.addFirst(7);
        AD1.addFirst(8);
        AD1.addFirst(9);
        AD1.addFirst(10);
        System.out.println(AD1.get(0));
        AD1.printDeque();
    }




}