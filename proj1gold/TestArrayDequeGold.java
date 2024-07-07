import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayDeque;

public class TestArrayDequeGold {
    @Test
    public void test() {
        ArrayDequeSolution<Integer> std = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> test = new StudentArrayDeque<>();
        String operations = "";

        for (int i = 0; i < 1000; i++) {
            if (std.size() == 0) {
                int r = StdRandom.uniform(1000);
                if (StdRandom.bernoulli()) {
                    operations += "addFirst(" + r + ")\n";
                    test.addFirst(r);
                    std.addFirst(r);
                } else {
                    operations += "addLast(" + r + ")\n";
                    std.addLast(r);
                    test.addLast(r);
                }
            } else {
                int x = StdRandom.uniform(1, 5);
                int addnum = StdRandom.uniform(1000);
                switch (x) {
                    case 1:
                        operations += "addFirst(" + addnum + ")\n";
                        std.addFirst(addnum);
                        test.addFirst(addnum);
                        break;
                    case 2:
                        operations += "addLast(" + addnum + ")\n";
                        std.addLast(addnum);
                        test.addLast(addnum);
                        break;
                    case 3:
                        operations += "removeFirst()\n";
                        assertEquals(operations, std.removeFirst(), test.removeFirst());
                        break;
                    case 4:
                        operations += "removeLast()\n";
                        assertEquals(operations, std.removeLast(), test.removeLast());
                    default:
                }
            }
        }
    }
}

