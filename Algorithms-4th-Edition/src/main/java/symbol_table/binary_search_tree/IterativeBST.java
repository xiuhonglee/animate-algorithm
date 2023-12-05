package symbol_table.binary_search_tree;

import symbol_table.OrderedSymbolTable;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class IterativeBST<Key extends Comparable<Key>, Value> implements OrderedSymbolTable<Key, Value> {

    private Node root; // 二叉查找树的根节点

    private class Node {
        private Key key;          // 键
        private Value val;        // 值
        private Node left, right; // 左右子树
        private int size;         // 以该节点为根的子树中的节点总数

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    /**
     * 插入元素-非递归
     * @param key
     * @param val
     */
    @Override
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("键不能为空");
        if (val == null) {
            delete(key);
            return;
        }

        // 如果根节点为空，直接在根节点处插入
        if (root == null) {
            root = new Node(key, val, 1);
            return;
        }

        Node current = root;
        Node parent = null;
        while (current != null) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                current.val = val; // 如果键已存在，更新其值
                return;
            }
        }

        // 创建新节点并插入到合适的位置
        Node newNode = new Node(key, val, 1);
        int cmp = key.compareTo(parent.key);
        if (cmp < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        // 更新经过路径上每个节点的大小
        current = root;
        while (current != null) {
            current.size = 1 + size(current.left) + size(current.right);
            cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                break;
            }
        }
    }

    /**
     * 查找元素-非递归
     * @param key
     * @return
     */
    @Override
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("键不能为空");

        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current.val; // 找到键，返回对应的值
            }
        }
        return null; // 未找到键，返回 null
    }

    @Override
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = delete(node.left, key);
        else if (cmp > 0) node.right = delete(node.right, key);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.size;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        else return min(node.left);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table is empty");
        return min(root).key;
    }

    @Override
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table is empty");
        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null) return node;
        else return max(node.right);
    }

    @Override
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        if (isEmpty()) throw new NoSuchElementException("Symbol table is empty");
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp < 0) return floor(node.left, key);
        Node t = floor(node.right, key);
        if (t != null) return t;
        else return node;
    }

    @Override
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        if (isEmpty()) throw new NoSuchElementException("Symbol table is empty");
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp > 0) return ceiling(node.right, key);
        Node t = ceiling(node.left, key);
        if (t != null) return t;
        else return node;
    }

    @Override
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        return rank(key, root);
    }

    private int rank(Key key, Node node) {
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return rank(key, node.left);
        else if (cmp > 0) return 1 + size(node.left) + rank(key, node.right);
        else return size(node.left);
    }


    @Override
    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + k);
        }
        return select(root, k).key;
    }

    private Node select(Node node, int k) {
        if (node == null) return null;
        int t = size(node.left);
        if (t > k) return select(node.left, k);
        else if (t < k) return select(node.right, k - t - 1);
        else return node;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null || hi == null) throw new IllegalArgumentException("keys() arguments cannot be null");
        ArrayList<Key> queue = new ArrayList<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node node, ArrayList<Key> queue, Key lo, Key hi) {
        if (node == null) return;
        int cmplo = lo.compareTo(node.key);
        int cmphi = hi.compareTo(node.key);
        if (cmplo < 0) keys(node.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.add(node.key);
        if (cmphi > 0) keys(node.right, queue, lo, hi);
    }

    // 测试用例
    public static void main(String[] args) {
        IterativeBST<Integer, String> st = new IterativeBST<>();

        st.put(1, "苹果");
        st.put(2, "香蕉");
        st.put(3, "橙子");

        System.out.println("获取键为2的值: " + st.get(2)); // 应输出 "香蕉"
        System.out.println("大小: " + st.size());         // 应输出 3

        st.delete(2);
        System.out.println("删除键为2后的大小: " + st.size()); // 应输出 2
        System.out.println("检查键为2是否存在: " + st.contains(2)); // 应输出 false
    }
}
