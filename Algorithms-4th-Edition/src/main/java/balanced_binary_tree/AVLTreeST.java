package balanced_binary_tree;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AVLTreeST<Key extends Comparable<Key>, Value> {

    /**
     * 根节点。
     */
    private Node root;

    /**
     * 这个类表示AVL树的内部节点。
     */
    private class Node {
        private final Key key;   // 键
        private Value val;       // 关联的值
        private int height;      // 子树的高度
        private int size;        // 子树中的节点数
        private Node left;       // 左子树
        private Node right;      // 右子树

        public Node(Key key, Value val, int height, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
            this.height = height;
        }
    }

    /**
     * 初始化一个空的符号表。
     */
    public AVLTreeST() {
    }

    /**
     * 检查符号表是否为空。
     *
     * @return 如果符号表为空，则返回{@code true}。
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 返回符号表中键值对的数量。
     *
     * @return 符号表中键值对的数量
     */
    public int size() {
        return size(root);
    }

    /**
     * 返回子树中的节点数。
     *
     * @param x 子树
     * @return 子树中的节点数
     */
    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    /**
     * 返回内部AVL树的高度。假设空树的高度为-1，只有一个节点的树高度为0。
     *
     * @return 内部AVL树的高度
     */
    public int height() {
        return height(root);
    }

    /**
     * 返回子树的高度。
     *
     * @param x 子树
     * @return 子树的高度。
     */
    private int height(Node x) {
        if (x == null) return -1;
        return x.height;
    }

    /**
     * 返回给定键关联的值。
     *
     * @param key 键
     * @return 如果键存在于符号表中，返回给定键关联的值；如果键不在符号表中，返回{@code null}
     * @throws IllegalArgumentException 如果{@code key}是{@code null}
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        Node x = get(root, key);
        if (x == null) return null;
        return x.val;
    }

    /**
     * 在子树中返回给定键关联的值或{@code null}（如果没有这样的键）。
     *
     * @param x   子树
     * @param key 键
     * @return 子树中给定键关联的值或{@code null}（如果没有这样的键）
     */
    private Node get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x;
    }

    /**
     * 检查符号表是否包含给定的键。
     *
     * @param key 键
     * @return 如果符号表包含{@code key}，返回{@code true}；否则返回{@code false}
     * @throws IllegalArgumentException 如果{@code key}是{@code null}
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     * 将指定的键值对插入到符号表中，如果符号表已经包含指定的键，则用新值覆盖旧值。
     * 如果指定的值是{@code null}，则从符号表中删除指定的键（及其关联的值）。
     *
     * @param key 键
     * @param val 值
     * @throws IllegalArgumentException 如果{@code key}是{@code null}
     */
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
        assert check();
    }

    /**
     * 在子树中插入键值对。如果符号表已经包含指定的键，则用新值覆盖旧值；
     * 如果指定的值是{@code null}，则从符号表中删除指定的键（及其关联的值）。
     *
     * @param x   子树
     * @param key 键
     * @param val 值
     * @return 子树
     */
    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 0, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
            return x;
        }
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    /**
     * 恢复子树的AVL树属性。
     *
     * @param x 子树
     * @return 恢复AVL属性后的子树
     */
    private Node balance(Node x) {
        if (balanceFactor(x) < -1) {
            if (balanceFactor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            x = rotateLeft(x);
        } else if (balanceFactor(x) > 1) {
            if (balanceFactor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            x = rotateRight(x);
        }
        return x;
    }

    /**
     * 返回子树的平衡因子。平衡因子定义为左子树和右子树的高度差。
     * 因此，平衡因子为-1, 0或1的子树具有AVL属性，因为两个子树的高度差最多为一。
     *
     * @param x 子树
     * @return 子树的平衡因子
     */
    private int balanceFactor(Node x) {
        return height(x.left) - height(x.right);
    }

    /**
     * 向右旋转给定的子树。
     *
     * @param x 子树
     * @return 向右旋转后的子树
     */
    private Node rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    /**
     * 向左旋转给定的子树。
     *
     * @param x 子树
     * @return 向左旋转后的子树
     */
    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    /**
     * 从符号表中移除指定的键及其关联的值（如果键存在于符号表中）。
     *
     * @param key 键
     * @throws IllegalArgumentException 如果{@code key}是{@code null}
     */
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;
        root = delete(root, key);
        assert check();
    }

    /**
     * 从给定子树中移除指定的键及其关联的值。
     *
     * @param x   子树
     * @param key 键
     * @return 更新后的子树
     */
    private Node delete(Node x, Key key) {
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.left == null) {
                return x.right;
            } else if (x.right == null) {
                return x.left;
            } else {
                Node y = x;
                x = min(y.right);
                x.right = deleteMin(y.right);
                x.left = y.left;
            }
        }
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    /**
     * 从符号表中移除最小的键及其关联的值。
     *
     * @throws NoSuchElementException 如果符号表为空
     */
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("called deleteMin() with empty symbol table");
        root = deleteMin(root);
        assert check();
    }

    /**
     * 从给定子树中移除最小的键及其关联的值。
     *
     * @param x 子树
     * @return 更新后的子树
     */
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    /**
     * 从符号表中移除最大的键及其关联的值。
     *
     * @throws NoSuchElementException 如果符号表为空
     */
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("called deleteMax() with empty symbol table");
        root = deleteMax(root);
        assert check();
    }

    /**
     * 从给定子树中移除最大的键及其关联的值。
     *
     * @param x 子树
     * @return 更新后的子树
     */
    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    /**
     * 返回符号表中的最小键。
     *
     * @return 符号表中的最小键
     * @throws NoSuchElementException 如果符号表为空
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return min(root).key;
    }

    /**
     * 返回子树中的最小键所在的节点。
     *
     * @param x 子树
     * @return 子树中的最小键所在的节点
     */
    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    /**
     * 返回符号表中的最大键。
     *
     * @return 符号表中的最大键
     * @throws NoSuchElementException 如果符号表为空
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return max(root).key;
    }

    /**
     * 返回子树中的最大键所在的节点。
     *
     * @param x 子树
     * @return 子树中的最大键所在的节点
     */
    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    /**
     * 返回符号表中小于或等于{@code key}的最大键。
     *
     * @param key 键
     * @return 符号表中小于或等于{@code key}的最大键
     * @throws NoSuchElementException   如果符号表为空
     * @throws IllegalArgumentException 如果{@code key}是{@code null}
     */
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }

    /**
     * 返回子树中小于或等于给定键的最大键所在的节点。
     *
     * @param x   子树
     * @param key 键
     * @return 子树中小于或等于给定键的最大键所在的节点
     */
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node y = floor(x.right, key);
        if (y != null) return y;
        else return x;
    }

    /**
     * 返回符号表中大于或等于{@code key}的最小键。
     *
     * @param key 键
     * @return 符号表中大于或等于{@code key}的最小键
     * @throws NoSuchElementException   如果符号表为空
     * @throws IllegalArgumentException 如果{@code key}是{@code null}
     */
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    /**
     * 返回子树中大于或等于给定键的最小键所在的节点。
     *
     * @param x   子树
     * @param key 键
     * @return 子树中大于或等于给定键的最小键所在的节点
     */
    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node y = ceiling(x.left, key);
        if (y != null) return y;
        else return x;
    }

    /**
     * 返回符号表中第k小的键。
     *
     * @param k 序数统计
     * @return 符号表中第k小的键
     * @throws IllegalArgumentException 除非{@code k}在0和{@code size() -1}之间
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) throw new IllegalArgumentException("k is not in range 0-" + (size() - 1));
        Node x = select(root, k);
        return x.key;
    }

    /**
     * 返回子树中第k小的键所在的节点。
     *
     * @param x 子树
     * @param k 子树中第k小的键
     * @return 子树中第k小的键所在的节点
     */
    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    /**
     * 返回符号表中小于{@code key}的键的数量。
     *
     * @param key 键
     * @return 符号表中小于{@code key}的键的数量
     * @throws IllegalArgumentException 如果{@code key}是{@code null}
     */
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    }

    /**
     * 返回子树中小于键的键的数量。
     *
     * @param key 键
     * @param x   子树
     * @return 子树中小于键的键的数量
     */
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    /**
     * 返回符号表中的所有键。
     *
     * @return 符号表中的所有键
     */
    public Iterable<Key> keys() {
        return keysInOrder();
    }

    /**
     * 按照中序遍历返回符号表中的所有键。
     *
     * @return 按照中序遍历返回符号表中的所有键
     */
    public Iterable<Key> keysInOrder() {
        Queue<Key> queue = new Queue<Key>();
        keysInOrder(root, queue);
        return queue;
    }

    /**
     * 按照中序遍历将子树中的键添加到队列中。
     *
     * @param x     子树
     * @param queue 队列
     */
    private void keysInOrder(Node x, Queue<Key> queue) {
        if (x == null) return;
        keysInOrder(x.left, queue);
        queue.enqueue(x.key);
        keysInOrder(x.right, queue);
    }

    /**
     * 按照层序遍历返回符号表中的所有键。
     *
     * @return 按照层序遍历返回符号表中的所有键。
     */
    public Iterable<Key> keysLevelOrder() {
        Queue<Key> queue = new Queue<Key>();
        if (!isEmpty()) {
            Queue<Node> queue2 = new Queue<Node>();
            queue2.enqueue(root);
            while (!queue2.isEmpty()) {
                Node x = queue2.dequeue();
                queue.enqueue(x.key);
                if (x.left != null) {
                    queue2.enqueue(x.left);
                }
                if (x.right != null) {
                    queue2.enqueue(x.right);
                }
            }
        }
        return queue;
    }

    /**
     * 返回给定范围内的所有键。
     *
     * @param lo 最低键
     * @param hi 最高键
     * @return 在{@code lo}（包含）和{@code hi}（不包含）之间的符号表中的所有键
     * @throws IllegalArgumentException 如果{@code lo}或{@code hi}是{@code null}
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    /**
     * 将{@code lo}和{@code hi}之间的键添加到子树中的{@code queue}。
     *
     * @param x     子树
     * @param queue 队列
     * @param lo    最低键
     * @param hi    最高键
     */
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    /**
     * 返回给定范围内符号表中键的数量。
     *
     * @param lo 最小端点
     * @param hi 最大端点
     * @return 在{@code lo}（包含）和{@code hi}（不包含）之间的符号表中键的数量
     * @throws IllegalArgumentException 如果{@code lo}或{@code hi}是{@code null}
     */
    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    /**
     * 检查AVL树的不变性是否保持一致。
     *
     * @return 如果AVL树的不变性保持一致，则返回{@code true}
     */
    private boolean check() {
        if (!isBST()) StdOut.println("对称顺序不一致");
        if (!isAVL()) StdOut.println("AVL属性不一致");
        if (!isSizeConsistent()) StdOut.println("子树计数不一致");
        if (!isRankConsistent()) StdOut.println("排名不一致");
        return isBST() && isAVL() && isSizeConsistent() && isRankConsistent();
    }

    /**
     * 检查AVL属性是否一致。
     *
     * @return 如果AVL属性一致，则返回{@code true}。
     */
    private boolean isAVL() {
        return isAVL(root);
    }

    /**
     * 检查子树的AVL属性是否一致。
     *
     * @param x 子树
     * @return 如果子树的AVL属性一致，则返回{@code true}
     */
    private boolean isAVL(Node x) {
        if (x == null) return true;
        int bf = balanceFactor(x);
        if (bf > 1 || bf < -1) return false;
        return isAVL(x.left) && isAVL(x.right);
    }

    /**
     * 检查对称顺序是否一致。
     *
     * @return 如果对称顺序一致，则返回{@code true}
     */
    private boolean isBST() {
        return isBST(root, null, null);
    }

    /**
     * 检查以x为根的树是否是BST，所有键都严格在min和max之间（如果min或max为null，视为空约束）。
     * 该解决方案归功于Bob Dondero的优雅解决方案。
     *
     * @param x   子树
     * @param min 子树中的最小键
     * @param max 子树中的最大键
     * @return 如果对称顺序一致，则返回{@code true}
     */
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    /**
     * 检查大小是否一致。
     *
     * @return 如果大小一致，则返回{@code true}
     */
    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    /**
     * 检查子树的大小是否一致。
     *
     * @param x 子树
     * @return 如果子树的大小一致，则返回{@code true}
     */
    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.size != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    /**
     * 检查排名是否一致。
     *
     * @return 如果排名一致，则返回{@code true}
     */
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }

    /**
     * 测试{@code AVLTreeST}数据类型。
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        /**
         * input: s e a r c h e x a m p l e
         * output:
         * a 8
         * c 4
         * e 6
         * h 5
         * l 11
         * m 9
         * p 10
         * r 3
         * s 0
         * x 7
         */
        // 从命令行读取参数
//        AVLTreeST<String, Integer> st = new AVLTreeST<String, Integer>();
//        for (int i = 0; !StdIn.isEmpty(); i++) {
//            String key = StdIn.readString();
//            st.put(key, i);
//        }
//        for (String s : st.keys())
//            StdOut.println(s + " " + st.get(s));
//        StdOut.println();

        // 读取文件参数
        AVLTreeST<String, Integer> st = new AVLTreeST<String, Integer>();

        if (args.length == 0) {
            System.err.println("Please provide a file name as a command-line argument");
            return;
        }

        try {
            File file = new File(args[0]);
            Scanner scanner = new Scanner(file);

            for (int i = 0; scanner.hasNext(); i++) {
                String key = scanner.next();
                st.put(key, i);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + args[0]);
            return;
        }

        for (String s : st.keys()) {
            System.out.println(s + " " + st.get(s));
        }
    }
}
