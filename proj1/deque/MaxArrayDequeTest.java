package deque;
import org.junit.Test;

import java.util.Comparator;

public class MaxArrayDequeTest {



    public static void main(String[] args) {
        Comparator<Integer> cmp1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        MaxArrayDeque<Integer> list =  new MaxArrayDeque<>(cmp1);
        list.addFirst(1);
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(4);
        System.out.println(list.max());



    }
}
