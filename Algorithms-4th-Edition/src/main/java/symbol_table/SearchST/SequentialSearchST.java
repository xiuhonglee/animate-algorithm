package symbol_table.SearchST;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SequentialSearchST<Key, Value> {
    private int n;           // 键值对的数量
    private Node first;      // 键值对的链表

    // 辅助的链表数据类型
    private class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 初始化一个空的符号表。
     */
    public SequentialSearchST() {
    }

    /**
     * 返回符号表中的键值对数量。
     *
     * @return 符号表中的键值对数量
     */
    public int size() {
        return n;
    }

    /**
     * 判断符号表是否为空。
     *
     * @return 如果符号表为空返回true，否则返回false
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 判断符号表是否包含指定的键。
     *
     * @param key 键
     * @return 如果符号表包含key则返回true，否则返回false
     * @throws IllegalArgumentException 如果key为null
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("键为null");
        return get(key) != null;
    }

    /**
     * 根据键获取对应的值。
     *
     * @param key 键
     * @return 如果键存在，返回相应的值；如果不存在，返回null
     * @throws IllegalArgumentException 如果key为null
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("键为null");
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;
        }
        return null;
    }

    /**
     * 向符号表中插入或更新键值对。
     * 如果值为null，则删除键。
     *
     * @param key 键
     * @param val 值
     * @throws IllegalArgumentException 如果key为null
     */
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("键为null");
        if (val == null) {
            delete(key);
            return;
        }

        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        n++;
    }

    /**
     * 从符号表中删除指定的键及其关联的值。
     *
     * @param key 键
     * @throws IllegalArgumentException 如果key为null
     */
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("键为null");
        first = delete(first, key);
    }

    // 从以x为首的链表中删除键
    // 注意：如果表很大，函数调用栈可能过大
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    /**
     * 返回符号表中所有键的Iterable集合。
     * 使用for-each遍历符号表的所有键。
     *
     * @return 符号表中的所有键
     */
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }

    /**
     * 对SequentialSearchST数据类型进行单元测试。
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
// 确保命令行参数中包含文件路径
        if (args.length < 1) {
            System.out.println("Usage: java SequentialSearchST <filename>");
            return;
        }

        try {
            // 使用Scanner从文件中读取
            File file = new File(args[0]);
            Scanner scanner = new Scanner(file);
            int i = 0;
            while (scanner.hasNext()) {
                String key = scanner.next();
                st.put(key, i++);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + args[0]);
            return;
        }

        // 打印所有键及其对应的值
        for (String s : st.keys())
            System.out.println(s + " " + st.get(s));
    }
}

