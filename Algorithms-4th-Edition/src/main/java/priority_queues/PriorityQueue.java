package priority_queues;

public interface PriorityQueue<T> {

    /**
     * 向优先队列中插入一个元素。
     * 
     * @param item 要插入的元素
     */
    void insert(T item);

    /**
     * 返回但不移除优先队列中的最大元素。
     * 
     * @return 优先队列中的最大元素
     */
    T max();

    /**
     * 返回但不移除优先队列中的最小元素。
     * 
     * @return 优先队列中的最小元素
     */
    T min();

    /**
     * 移除并返回优先队列中的最大元素。
     * 
     * @return 优先队列中被移除的最大元素
     */
    T extractMax();

    /**
     * 移除并返回优先队列中的最小元素。
     * 
     * @return 优先队列中被移除的最小元素
     */
    T extractMin();

    /**
     * 返回优先队列中的元素个数。
     * 
     * @return 优先队列的大小
     */
    int size();

    /**
     * 判断优先队列是否为空。
     * 
     * @return 如果优先队列为空，返回true；否则返回false。
     */
    boolean isEmpty();

    /**
     * 将当前的优先队列与另一个优先队列合并。
     * 
     * @param other 另一个要合并的优先队列
     */
    void merge(PriorityQueue<T> other);

    /**
     * 清空优先队列的所有元素。
     */
    void clear();

    // 依据实际需要，还可以继续扩充其他方法。
}
