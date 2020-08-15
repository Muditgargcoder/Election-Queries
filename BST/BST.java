package col106.assignment3.BST;

import java.util.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class BST<T, E extends Comparable> implements BSTInterface<T, E> {
	/*
	 * Do not touch the code inside the upcoming block If anything tempered your
	 * marks will be directly cut to zero
	 */
	public static void main() {

		BSTDriverCode BDC = new BSTDriverCode();
		System.setOut(BDC.fileout());
	}
	/*
	 * end code start writing your code from here
	 */

	// write your code here

	public node<T, E> root = null;
	private HashMap<T, E> map = new HashMap<>();

	public BST() {
		for (int i = 0; i < ar.length; i++) {
			for (int j = 0; j < ar[0].length; j++) {
				ar[i][j] = "";
			}
		}
	}

	public void insert(T key, E value) {
		node<T, E> n = new node<T, E>(key, value);
		if (root == null) {
			root = n;
		} else {
			node<T, E> k = root;
			node<T, E> t = root;
			while (t != null) {
				k = t;
				if (value.compareTo(t.value) >= 0) {
					t = t.right;
				} else {
					t = t.left;
				}

			}
			if (value.compareTo(k.value) >= 0) {
				k.right = n;
			} else {
				k.left = n;
			}

		}

		map.put(key, value);

		// write your code here
	}

	public void update(T key, E value) {
		delete(key);
		insert(key, value);
		// write your code here
	}

	public void delete(T key) {
		E value = map.get(key);
		if (value != null) {
			boolean done = false;

			node<T, E> n = root;
			node<T, E> k = root;
			while (!done) {

				if (value.compareTo(n.value) == 0) {
					done = true;
					map.remove(key);
					if (n.left == null && n.right == null) { // no child
						if (n == root) {
							root = null;
						} else if (k.left == n)
							k.left = null;
						else
							k.right = null;
					} else if (n.left == null || n.right == null) { // one child
						if (n.left == null) {
							if (n == root) {
								root = root.right;
							} else if (k.left == n) {
								k.left = n.right;
							} else
								k.right = n.right;
						} else {
							if (n == root) {
								root = root.left;
							} else if (k.left == n) {
								k.left = n.left;
							} else
								k.right = n.left;
						}
					} else { // two child
						node<T, E> temp = n.right;
						node<T, E> temp1 = n.right;

						while (temp.left != null) {
							temp1 = temp;
							temp = temp.left;
						}
						if (n == root) {
							root = temp;
						} else if (k.left == n) {
							k.left = temp;
						} else {
							k.right = temp;
						}
						temp.left = n.left; // currentNode.left becomes nextInorder.left
						if (temp != temp1) {
							if (temp.right != null) {
								temp1.left = temp.right;
							} else {
								temp1.left = null;
							}
							temp.right = n.right;

						}

					}

				} else if (value.compareTo(n.value) > 0) {

					k = n;
					n = n.right;
				} else {

					k = n;
					n = n.left;
				}
			}
		}

		// write your code here
	}

	public void printBST() {
		Queue<node<T, E>> q = new LinkedList<node<T, E>>();
		q.add(root);
		String s = "";
		node<T, E> n = null;
		while (!q.isEmpty()) {
			n = q.remove();
			if (n.left != null) {
				q.add(n.left);
			}
			if (n.right != null) {
				q.add(n.right);
			}
			System.out.println(n.key.toString() + ", " + n.value.toString());
		}

		// write your code here
	}

	public ArrayList<T> getLevelOrder() {
		ArrayList<T> levelOrder = new ArrayList<>();
		Queue<node<T, E>> q = new LinkedList<node<T, E>>();
		q.add(root);
		String s = "";
		node<T, E> n = null;
		while (!q.isEmpty()) {
			n = q.remove();
			if (n.left != null) {
				q.add(n.left);
			}
			if (n.right != null) {
				q.add(n.right);
			}
			levelOrder.add(n.key);
		}
		return levelOrder;
	}

	private String[][] ar = new String[18][50];

	private void Tree(node<Integer, Integer> n, int i, int j) {
		if (n == null) {
			return;
		} else {
			ar[i][j] = Integer.toString(n.value);
			int ls = height(n.left);
			int rs = height(n.right);
			if (ls != -1) {
				for (int k = 1; k <= ls; k++) {
					ar[i + k][j - k] = "/";
				}
			}
			if (rs != -1) {
				for (int k = 1; k <= rs; k++) {
					ar[i + k][j + k] = "\\";
				}
			}

			if (n.left != null) {
				Tree(n.left, i + ls + 1, j - ls - 1);
			}
			if (n.right != null) {
				Tree(n.right, i + rs + 1, j + rs + 1);
			}

		}
	}

	private int count(node<Integer, Integer> n) { // count nodes connected to node n excluding n
		if (n == null) {
			return 0;
		} else if (n.left != null || n.right != null) {
			int a = count(n.left);
			int b = count(n.right);
			if (n.left != null && n.right != null) {
				return a + b + 2;
			} else
				return a + b + 1;
		} else {
			return 0;
		}
	}

	private int height(node<Integer, Integer> n) {
		if (n == null) {
			return -1;
		} else {
			int a = height(n.left);
			int b = height(n.right);
			if (a > b) {
				return a + 1;
			} else
				return b + 1;
		}
	}

	private void printTree() {
		for (int i = 0; i < ar.length; i++) {
			String s = "";
			for (int j = 0; j < ar[0].length; j++) {
				s += ar[i][j] + " ";
			}
			System.out.println(s);
		}
	}

	private int klar = 0;
	private ArrayList<T> klarge = new ArrayList<>();
	private int temp = 0;

	public ArrayList<T> klarge(int k) {
		klar = k;
		temp = 0;
		getk(root);
		return klarge;
	}

	private void getk(node<T, E> n) {
		if (temp < klar) {
			if (n == null) {
				return;
			} else {
				getk(n.right);

				if (temp < klar) {
					klarge.add(n.key);
					temp++;
				}

				if (temp < klar)
					getk(n.left);
			}
		} else
			return;

	}

	private E max = null;
	private ArrayList<T> l = new ArrayList<>();

	public ArrayList<T> getLargest() {
		node<T, E> n = root;
		while (n.right != null) {
			n = n.right;
		}
		max = n.value;
		rec(root);
		ArrayList<T> done = (ArrayList<T>) l.clone();
		l.clear();
		return done;
	}

	private void rec(node<T, E> n) {
		if (n.right != null) {
			rec(n.right);
		}
		if (max.compareTo(n.value) == 0) {
			l.add(n.key);
		} else
			return;
	}

	public boolean hasKey(T key) {
		return map.containsKey(key);
	}

	public E getValue(T key) {
		return map.get(key);
	}

	public int count = 20;

	public void print2DUtil(node root, int space) {
		// Base case
		if (root == null)
			return;

		// Increase distance between levels
		space += count;

		// Process right child first
		print2DUtil(root.right, space);

		// Print current node after space
		// count
		System.out.print("\n");
		for (int i = count; i < space; i++)
			System.out.print(" ");
		System.out.print(root.value + "\n");

		// Process left child
		print2DUtil(root.left, space);
	}

	// Wrapper over print2DUtil()
	public void print2D(node root) {
		// Pass initial space count as 0
		print2DUtil(root, 0);
	}

	public node<T, E> min(node<T, E> n) {
		if (n == null || n.left == null) {
			return n;
		} else {
			return min(n.left);
		}

	}

	public node<T, E> recdel(node<T, E> n, T key, E val) {

		if (val.compareTo(n.value) > 0) {
			n.right = recdel(n.right, key, val);

		} else if (val.compareTo(n.value) < 0) {
			n.left = recdel(n.left, key, val);

		} else {
			if (n.left == null) {
				return n.right;
			} else if (n.right == null) {
				return n.left;
			} else {
				node<T, E> n1 = min(n.right);
				n.key = n1.key;
				n.value = n1.value;
				n.right = recdel(n.right, n1.key, n1.value);

			}
		}
		return n;

	}

	public void del(T key) {
		E val = map.get(key);
		root = recdel(root, key, val);
		map.remove(key);

	}

	public E common(E n1, E n2) {

		ArrayList<E> a = new ArrayList<>();
		ArrayList<E> b = new ArrayList<>();

		node<T, E> t = root;

		while (t.value != n1) {
			a.add(t.value);
			if (n1.compareTo(t.value) > 0) {
				t = t.right;
			} else
				t = t.left;
		}

		a.add(n1);
		t = root;
		while (t.value != n2) {
			b.add(t.value);
			if (n2.compareTo(t.value) > 0) {
				t = t.right;
			} else
				t = t.left;
		}

		b.add(n2);
		boolean d = false;
		E done = null;
		for (int i = 0; i < a.size(); i++) {
			E k = a.get(a.size() - i - 1);
			for (int j = 0; j < b.size(); j++) {
				if (k == b.get(j)) {
					done = k;
					d = true;
					break;
				}
			}
			if (d == true) {
				break;
			}
		}
		return done;
	}

	public int rankOfN = 0;

	public void rank(node n, E val) {
		if (n == null) {
			return;
		}

		rank(n.left, val);
		rankOfN++;

		if (n.value.compareTo(val) == 0) {
			System.out.println(rankOfN);
			rankOfN = 0;
			return;
		}
		rank(n.right, val);

	}

	public ArrayList<E> path(E val) {
		ArrayList<E> t = new ArrayList<>();
		findpathtoN(root, val, t);
		
		return t;
	}

	public boolean findpathtoN(node<T,E> n, E a, ArrayList<E> l){
		if(n == null){
			return false;
		}
		if(n.value.compareTo(a) == 0){
			l.add(a);
			return true;
		}
		if(findpathtoN(n.left, a, l) || findpathtoN(n.right, a, l)){
			l.add(n.value);
			return true;
		}
		
		return false;
	}

	public static void main(String[] args) {
		BST<Integer, Integer> b = new BST<>();
		// b.insert(1, 8);
		// b.insert(2, 7);
		// b.insert(3, 5);

		// b.print2D(b.root);
		// System.out.println();
		// System.out.println("Changing");
		// System.out.println(b.common(16, 22).toString());

		b.insert(1, 6);
		b.insert(2, 10);
		b.insert(3, 2);
		b.insert(4, 1);
		b.insert(5, 5);
		b.insert(6, 3);
		b.insert(7, 4);
		b.insert(8, 11);
		b.insert(9, 12);
		b.insert(10, 8);
		b.print2D(b.root);

		System.out.println(b.path(4));
	}

}

class node<T, E extends Comparable> {

	T key;
	E value;
	node<T, E> left;
	node<T, E> right;

	public node(T key, E value) {
		this.key = key;
		this.value = value;
		left = null;
		right = null;
	}

}