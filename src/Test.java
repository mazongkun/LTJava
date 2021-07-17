import java.util.*;

public class Test {
    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedList<>();
        Deque<Integer> deque2 = new LinkedList<>();

        Random random = new Random();
        for (int i=0; i<10; i++) {
            int num = Math.abs(random.nextInt() % 30);
            deque.offer(num);
            deque2.offerLast(num);
            deque.offerFirst()
        }

        System.out.println(deque);
        System.out.println(deque2);
    }
}











