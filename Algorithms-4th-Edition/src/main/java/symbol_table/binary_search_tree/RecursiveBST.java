package symbol_table.binary_search_tree;

import symbol_table.OrderedSymbolTable;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class RecursiveBST<Key extends Comparable<Key>, Value> implements OrderedSymbolTable<Key, Value> {

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
     * 外部公开接口-调用内部方法
     *
     * @param key
     * @param val
     */
    @Override
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
    }

    /**
     * 插入操作具体实现-递归
     *
     * @param node
     * @param key
     * @param val
     * @return
     */
    private Node put(Node node, Key key, Value val) {
        // 如果当前节点为空，说明找到了插入位置，创建新节点并返回
        if (node == null) return new Node(key, val, 1);

        // 比较插入键与当前节点键的大小
        int cmp = key.compareTo(node.key);

        // 如果插入键小于当前节点键，则在左子树中继续插入
        if (cmp < 0) node.left = put(node.left, key, val);
            // 如果插入键大于当前节点键，则在右子树中继续插入
        else if (cmp > 0) node.right = put(node.right, key, val);
            // 如果插入键等于当前节点键，则替换该节点的值
        else node.val = val;

        // 更新当前节点的子树大小
        node.size = 1 + size(node.left) + size(node.right);

        // 返回当前节点，以便将结构变化反映到整个树中
        return node;
    }

    @Override
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        return get(root, key);
    }

    /**
     * 查找-递归实现
     *
     * @param node
     * @param key
     * @return
     */
    private Value get(Node node, Key key) {
        // 如果节点为空，说明键不存在于树中，返回null
        if (node == null) return null;

        // 比较查找键与当前节点键的大小
        int cmp = key.compareTo(node.key);

        // 如果查找键小于当前节点键，继续在左子树中查找
        if (cmp < 0) return get(node.left, key);

            // 如果查找键大于当前节点键，在右子树中继续查找
        else if (cmp > 0) return get(node.right, key);

        // 如果查找键等于当前节点键，返回该节点的值
        else return node.val;
    }

    @Override
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        // 如果节点为空，表示键不存在于树中，返回null
        if (node == null) return null;

        // 比较删除键与当前节点键的大小
        int cmp = key.compareTo(node.key);
        // 如果删除键小于当前节点键，在左子树中继续删除
        if (cmp < 0) node.left = delete(node.left, key);
            // 如果删除键大于当前节点键，在右子树中继续删除
        else if (cmp > 0) node.right = delete(node.right, key);
            // 找到了要删除的节点
        else {
            // 如果该节点只有右子树或无子树，直接返回右子节点
            if (node.left == null) return node.right;
            // 如果该节点只有左子树，直接返回左子节点
            if (node.right == null) return node.left;

            // 如果该节点既有左子树又有右子树
            Node t = node;

            /**
             * 下面一般有三种处理方法(我们用的是 第1种 方法)：
             * 方法 1：找到右子树中的最小节点，用它来替换当前节点
             * 方法 2：你也可以找到左子树最大的结点来替换被删除的节点
             * 方法 3：随机从左子树选最大或右子树选最小的节点（推荐!!!）
             */
            node = min(t.right);
            // 删除右子树中的最小节点
            node.right = deleteMin(t.right);
            // 保持左子树不变
            node.left = t.left;
        }
        // 更新节点的大小
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

    private Node deleteMax(Node node) {
        // 如果节点为空，表示已经到达叶子节点，返回null
        if (node == null) return null;

        // 如果节点的右子树为空，表示当前节点就是最大节点，直接返回null
        if (node.right == null) return node.left;

        // 否则，继续向右子树查找最大节点
        node.right = deleteMax(node.right);
        // 更新当前节点的大小
        node.size = size(node.left) + size(node.right) + 1;
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
        RecursiveBST<Integer, String> st = new RecursiveBST<>();

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
