package col106.assignment3.Heap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * a
 */
public class a<T extends Comparable<T>> {

    int[] t = new int[3];

    T[] arr = (T[]) new Comparable[2];

    ArrayList<T> l = new ArrayList<>();

    void change() {
        t = new int[6];
    }

    void test(T a, T b) {
        if (a.compareTo(b) > 0 && a != null ) {
            System.out.println("Hello");
        }else System.out.println("world");
    }

    public static void main(String[] args) {
        a<Integer> n = new a<Integer>();
        // LinkedList<Integer> l = new LinkedList<>();
        // l.add(null);
        // l.add(null);

        // l.add(2, 3);
        // l.add(3, 4);
        // l.set(2,5);
        // for (int i = 0; i < l.size(); i++) {
        // System.out.println(l.get(i));
        // }
        String[] d = new String[]{"1","2","3"};
        Arrays.sort(d);
        System.out.println(Arrays.toString(d));
    }
}