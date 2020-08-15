package col106.assignment3.Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Heap<T, E extends Comparable> implements HeapInterface<T, E> {
	/*
	 * Do not touch the code inside the upcoming block If anything tempered your
	 * marks will be directly cut to zero
	 */
	public static void main() {
		HeapDriverCode HDC = new HeapDriverCode();
		System.setOut(HDC.fileout());
	}
	/*
	 * end code
	 */

	// write your code here
	ArrayList<E> arr = new ArrayList<>();
	int last = 0;
	HashMap<E, T> m1 = new HashMap<>();
	HashMap<T, E> m2 = new HashMap<>();

	public Heap() {
		arr.add(null);
	}

	public void insert(T key, E value) {
		// write your code here
		arr.add(value);
		last++;
		recinsert(last);
		m1.put(value, key);
		m2.put(key, value);

	}

	private void recinsert(int ind) {
		int parent = ind / 2;
		if (parent != 0) {
			if (arr.get(ind).compareTo(arr.get(parent)) > 0) {
				E t = arr.get(ind);
				arr.set(ind, arr.get(parent));
				arr.set(parent, t);
				recinsert(parent);
			}
		}
	}

	public E extractMax() {
		// write your code here
		E t = arr.get(1);

		arr.set(1, arr.get(last));
		arr.remove(last);

		m2.remove(m1.get(t));
		m1.remove(t);

		last--;
		recHeap(1);

		return t;
	}

	private void recHeap(int ind) {
		int c1 = 2 * ind;
		int c2 = 2 * ind + 1;
		if ((c1 <= last && arr.get(ind).compareTo(arr.get(c1)) < 0)
				|| (c2 <= last && arr.get(ind).compareTo(arr.get(c2)) < 0)) {
			if (arr.get(c2).compareTo(arr.get(c1)) > 0) {
				E swap = arr.get(c2);
				arr.set(c2, arr.get(ind));
				arr.set(ind, swap);
				recHeap(c2);
			} else {
				E swap = arr.get(c1);
				arr.set(c1, arr.get(ind));
				arr.set(ind, swap);
				recHeap(c1);
			}
		}

	}

	private int getIndex(E val) {
		int i = 0;
		for (i = 1; i < arr.size(); i++) {
			if (arr.get(i).compareTo(val) == 0)
				break;

		}
		return i;
	}

	public void delete(T key) {
		// write your code here
		E val = m2.get(key);
		int i = getIndex(val);
		m2.remove(key);
		m1.remove(val);
		arr.set(i, arr.get(last));
		arr.remove(last);
		last--;
		recHeap(i);

	}

	public void increaseKey(T key, E value) {
		// write your code here

		int i = getIndex(m2.get(key));
		arr.set(i, value);
		m1.put(value, key);
		m2.put(key, value);
		recinsert(i);
		recHeap(i);
	}

	public void printHeap() {
		// write your code here
		for (int i = 1; i < arr.size(); i++) {
			System.out.println(m1.get(arr.get(i)).toString() + ", " + arr.get(i).toString());
		}

	}
	public int count = 20;

	public void print2DUtil(int root, int space) {
		// Base case
		if (root >= arr.size())
			return;

		// Increase distance between levels
		space += count;

		// Process right child first
		print2DUtil(2*root + 1, space);

		// Print current node after space
		// count
		System.out.print("\n");
		for (int i = count; i < space; i++)
			System.out.print(" ");
		System.out.print(arr.get(root) + "\n");

		// Process left child
		print2DUtil(2*root, space);
	}

	// Wrapper over print2DUtil()
	public void print2D(int root) {
		// Pass initial space count as 0
		print2DUtil(1, 0);
	}

	public static void main(String[] args) {
		Heap<Integer, Integer> h = new Heap<>();
		h.insert(1, 100);
		h.insert(2, 50);
		h.insert(3, 30);
		h.insert(4, 1);
		h.insert(5, 3);
		h.print2D(1);
		System.out.println("Hello");
		h.insert(6, 40);
		h.insert(7, 120);
		h.insert(8, 60);
		h.delete(7);
		h.print2D(1);
		System.out.println("Hello");
		System.out.println("Hello");
		
		h.increaseKey(2, 110);
		h.increaseKey(2, 120);
		h.increaseKey(2, 30);
		h.print2D(1);

		

	}
}
