package symbol_table;

public interface OrderedSymbolTable<Key extends Comparable<Key>, Value> {

    void put(Key key, Value val); // 插入键值对，如果值为空则删除键

    Value get(Key key); // 获取键对应的值

    void delete(Key key); // 删除键（及其对应的值）

    boolean contains(Key key); // 检查表中是否含有该键

    boolean isEmpty(); // 检查表是否为空

    int size(); // 获取表中键值对的数量

    Key min(); // 获取表中最小的键

    Key max(); // 获取表中最大的键

    Key floor(Key key); // 获取小于等于指定键的最大键

    Key ceiling(Key key); // 获取大于等于指定键的最小键

    int rank(Key key); // 获取小于指定键的键的数量

    Key select(int k); // 获取排名为k的键

    Iterable<Key> keys(Key lo, Key hi); // 获取指定范围内的所有键，已排序

    // 默认方法实现
    default void deleteMin() {
        delete(min());
    }

    default void deleteMax() {
        delete(max());
    }

    default int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0) return 0;
        else if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    default Iterable<Key> keys() {
        return keys(min(), max());
    }
}
