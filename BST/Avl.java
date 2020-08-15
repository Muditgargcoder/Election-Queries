package col106.assignment3.BST;

import java.util.HashMap;

public class Avl<T, E extends Comparable> {

    public node<T, E> root = null;
    // private HashMap<T, E> map = new HashMap<>();

    public void insert(T key, E val) {
        if (root == null) {
            root = new node<T, E>(key, val);
        } else {
            root = recinsert(root, key, val);
        }
    }

    public node<T, E> recinsert(node<T, E> n, T key, E val) {
        if (n == null) {
            n = new node<T, E>(key, val);
        } else if (val.compareTo(n.value) > 0) {
            n.right = recinsert(n.right, key, val);
        } else if (val.compareTo(n.value) < 0) {
            n.left = recinsert(n.left, key, val);
        }

        int t = getbalance(n);

        if (t > 1 && getbalance(n.left) >= 0) {
            n = rotateright(n);
        }

        if (t < -1 && getbalance(n.right) <= 0) {
            n = rotateleft(n);
        }
        if (t > 1 && getbalance(n.left) < 0) {
            n.left = rotateleft(n.left);
            n = rotateright(n);
        }
        if (t < -1 && getbalance(n.right) > 0) {
            n.right = rotateright(n.right);
            n = rotateleft(n);
        }

        return n;
    }

    public void delete(E val) {
        root = recdel(root, val);
    }

    public node<T,E> min(node<T,E> n){
        if(n == null || n.left == null){
            return n;
        }else return min(n.left);
    }

    public node<T, E> recdel(node<T, E> n, E val) {
        if (val.compareTo(n.value) > 0) {
            n.right = recdel(n.right, val);
        } else if (val.compareTo(n.value) < 0) {
            n.left = recdel(n.left, val);
        } else {
            if (n.left == null) {
                n = n.right;
            } else if (n.right == null) {
                n = n.left;
            } else {
                node<T,E> n1 = min(n.right);
                n.key = n1.key;
                n.value = n1.value;
                n.right = recdel(n.right, n1.value);
            }
        }

        int t = getbalance(n);

        if(t > 1 && getbalance(n.left) >= 0 ){
            n = rotateright(n);
        }
        if( t > 1 && getbalance(n.left) < 0 ){
            n.left = rotateleft(n.left);
            n = rotateright(n);
        }
        if(t < -1 && getbalance(n.right) <= 0){
            n = rotateleft(n);
        }
        if(t < -1 && getbalance(n.right) > 0){
            n.right = rotateright(n.right);
            n = rotateleft(n);
        }
        return n;

    }

    public node<T, E> rotateright(node<T, E> n) {
        node<T, E> n1 = n.left;
        node<T, E> n2 = n1.right;

        n1.right = n;
        n.left = n2;

        return n1;
    }

    public node<T, E> rotateleft(node<T, E> n) {
        node<T, E> n1 = n.right;
        node<T, E> n2 = n1.left;

        n1.left = n;
        n.right = n2;

        return n1;
    }

    public int getbalance(node<T, E> n) {
        if(n == null){
            return 0;
        }
        return height(n.left) - height(n.right);
    }

    public int height(node<T, E> n) {
        if (n == null) {
            return -1;
        } else {
            int a = height(n.left);
            int b = height(n.right);
            if (a < b) {
                return b + 1;
            } else
                return a + 1;
        }
    }

    public int count = 10;

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

    public static void main(String[] args) {

        Avl<Integer, Integer> a = new Avl<>();
        a.insert(1, 2);
        a.insert(2, 1);
        a.insert(3, 7);
        a.insert(4, 8);
        a.insert(5, 5);
        a.insert(6, 10);
        a.insert(7, 3);
        a.insert(7, 4);
        a.delete(10);
        a.print2D(a.root);
    }
}