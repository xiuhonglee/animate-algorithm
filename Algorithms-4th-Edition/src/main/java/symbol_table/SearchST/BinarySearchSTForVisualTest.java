package symbol_table.SearchST;

import edu.princeton.cs.algs4.Queue;
import symbol_table.tools.VisualAccumulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BinarySearchSTForVisualTest<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int n = 0;

    private VisualAccumulator va;

    /**
     * 初始化一个空的符号表。
     */
    public BinarySearchSTForVisualTest() {
        this(INIT_CAPACITY);
        va = new VisualAccumulator(14350, 5740);
    }


    /**
     * 使用指定的初始容量初始化一个空的符号表。
     * @param capacity 最大容量
     */
    public BinarySearchSTForVisualTest(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
        va = new VisualAccumulator(14350, 5740);
    }

    // 重设底层数组的大小
    private void resize(int capacity) {
        assert capacity >= n;
        Key[]   tempk = (Key[])   new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
    }

    /**
     * 返回这个符号表中键值对的数量。
     *
     * @return 符号表中键值对的数量
     */
    public int size() {
        return n;
    }

    /**
     * 如果这个符号表为空，则返回true。
     *
     * @return 如果符号表为空返回{@code true}；否则返回{@code false}
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 检查这个符号表是否包含给定的键。
     *
     * @param  key 键
     * @return 如果符号表包含{@code key}返回{@code true}，否则返回{@code false}
     * @throws IllegalArgumentException 如果{@code key}是{@code null}
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     * 返回与这个符号表中给定键关联的值。
     *
     * @param  key 键
     * @return 如果键存在于符号表中，则返回与给定键关联的值；否则返回{@code null}
     * @throws IllegalArgumentException 如果{@code key}是{@code null}
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        if (isEmpty()) return null;
        int i = rank(key)[0];
        if (i < n && keys[i].compareTo(key) == 0) return vals[i];
        return null;
    }

    /**
     * 返回小于{@code key}的键的数量。
     *
     * @param  key 键
     * @return 符号表中小于{@code key}的键的数量
     * @throws IllegalArgumentException 如果{@code key}是{@code null}
     */
    public int[] rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");

        int lo = 0, hi = n - 1;
        int compares = 0;
        int accesses = 0;

        while (lo <= hi) {
            compares++;
            int mid = lo + (hi - lo) / 2;
            accesses++; // 访问 keys[mid]
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return new int[]{mid, compares, accesses};
        }
        return new int[]{lo, compares, accesses};
    }

    /**
     * 将指定的键值对插入到符号表中，如果符号表已包含指定的键，则用新值覆盖旧值。
     * 如果指定的值是{@code null}，则从此符号表中删除指定的键（及其关联的值）。
     *
     * @param  key 键
     * @param  val 值
     * @throws IllegalArgumentException 如果{@code key}是{@code null}
     */
    public void put(Key key, Value val)  {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        if (val == null) {
            delete(key);
            return;
        }

        int[] rankResult = rank(key);
        int i = rankResult[0];
        int compares = rankResult[1];
        int accesses = rankResult[2];

        // 如果键已经存在于表中
        if (i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            accesses++; // 更新 vals[i]
            va.addDataValue(compares + accesses);
            return;
        }

        // 插入新的键值对
        if (n == keys.length) resize(2 * keys.length);

        for (int j = n; j > i; j--)  {
            accesses += 2; // 访问 keys[j-1] 和 vals[j-1]
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        accesses += 2; // 访问 keys[i] 和 vals[i]
        keys[i] = key;
        vals[i] = val;
        n++;

        va.addDataValue(compares + accesses);
        assert check();
    }



    /**
     * 从这个符号表中移除指定的键及其关联的值（如果键在符号表中）。
     *
     * @param  key 键
     * @throws IllegalArgumentException 如果{@code key}是{@code null}
     */
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (isEmpty()) return;

        // 计算排名
        int i = rank(key)[0];

        // 如果键不在表中
        if (i == n || keys[i].compareTo(key) != 0) {
            return;
        }

        for (int j = i; j < n-1; j++)  {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }

        n--;
        keys[n] = null;  // 避免游离
        vals[n] = null;
        // 如果使用率为四分之一，则调整大小
        if (n > 0 && n == keys.length / 4) resize(keys.length / 2);

        assert check();
    }

    /**
     * 从这个符号表中移除最小的键及其关联的值。
     *
     * @throws NoSuchElementException 如果符号表为空
     */
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        delete(min());
    }

    /**
     * 从这个符号表中移除最大的键及其关联的值。
     *
     * @throws NoSuchElementException 如果符号表为空
     */
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        delete(max());
    }

    /***************************************************************************
     *  有序符号表方法。
     ***************************************************************************/

    /**
     * 返回这个符号表中最小的键。
     *
     * @return 符号表中最小的键
     * @throws NoSuchElementException 如果这个符号表为空
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return keys[0];
    }

    /**
     * 返回这个符号表中最大的键。
     *
     * @return 符号表中最大的键
     * @throws NoSuchElementException 如果这个符号表为空
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return keys[n - 1];
    }

    /**
     * 返回这个符号表中第k小的键。
     *
     * @param  k 排序统计
     * @return 符号表中第{@code k}小的键
     * @throws IllegalArgumentException 除非{@code k}在0和<n>–1之间
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        return keys[k];
    }

    /**
     * 返回这个符号表中小于或等于{@code key}的最大键。
     *
     * @param  key 键
     * @return 符号表中小于或等于{@code key}的最大键
     * @throws NoSuchElementException 如果没有这样的键
     * @throws IllegalArgumentException 如果{@code key}是{@code null}
     */
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        int i = rank(key)[0];
        if (i < n && key.compareTo(keys[i]) == 0) return keys[i];
        if (i == 0) throw new NoSuchElementException("argument to floor() is too small");
        else return keys[i - 1];
    }

    /**
     * 返回这个符号表中大于或等于{@code key}的最小键。
     *
     * @param  key 键
     * @return 符号表中大于或等于{@code key}的最小键
     * @throws NoSuchElementException 如果没有这样的键
     * @throws IllegalArgumentException 如果{@code key}是{@code null}
     */
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        int i = rank(key)[0];
        if (i == n) throw new NoSuchElementException("argument to ceiling() is too large");
        else return keys[i];
    }

    /**
     * 返回在指定范围内的键的数量。
     *
     * @param lo 最小端点
     * @param hi 最大端点
     * @return 符号表中{@code lo}（包含）和{@code hi}（包含）之间的键的数量
     * @throws IllegalArgumentException 如果{@code lo}或{@code hi}是{@code null}
     */
    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi)[0] - rank(lo)[0] + 1;
        else              return rank(hi)[0] - rank(lo)[0];
    }

    /**
     * 返回这个符号表中所有键的{@code Iterable}。
     * 要遍历名为{@code st}的符号表中的所有键，请使用foreach表示法：{@code for (Key key : st.keys())}。
     *
     * @return 这个符号表中的所有键
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * 返回给定范围内这个符号表中所有键的{@code Iterable}。
     *
     * @param lo 最小端点
     * @param hi 最大端点
     * @return 在{@code lo}（包含）和{@code hi}（包含）之间的这个符号表中所有键
     * @throws IllegalArgumentException 如果{@code lo}或{@code hi}是{@code null}
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo)[0]; i < rank(hi)[0]; i++)
            queue.enqueue(keys[i]);
        if (contains(hi)) queue.enqueue(keys[rank(hi)[0]]);
        return queue;
    }

    /***************************************************************************
     *  检查内部不变量。
     ***************************************************************************/

    private boolean check() {
        return isSorted() && rankCheck();
    }

    // 数组中的项是否按升序排列？
    private boolean isSorted() {
        for (int i = 1; i < size(); i++)
            if (keys[i].compareTo(keys[i-1]) < 0) return false;
        return true;
    }

    // 检查rank(select(i))是否等于i
    private boolean rankCheck() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))[0]) return false;
        for (int i = 0; i < size(); i++)
            if (keys[i].compareTo(select(rank(keys[i])[0])) != 0) return false;
        return true;
    }
    // 新增方法来获取 VisualAccumulator 实例
    public VisualAccumulator getVisualAccumulator() {
        return this.va;
    }
}
