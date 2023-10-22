package priority_queues;

/**
 * Desc: 功能同 UnorderedArrayPQ，这里展示实际对接口编程的具体方法
 */

public class UnorderedArrayPQImpl<T extends Comparable<T>> implements PriorityQueue<T> {

    private T[] arr;  // 用于存储数据的数组
    private int n;    // 数组中的实际元素个数

    // 初始化一个给定大小的优先队列
    @SuppressWarnings("unchecked")
    public UnorderedArrayPQImpl(int capacity) {
        arr = (T[]) new Comparable[capacity];
        n = 0;
    }

    @Override
    public void insert(T item) {
        if (n == arr.length) {
            throw new RuntimeException("队列已满");  // 如果数组已满，抛出异常
        }
        arr[n++] = item;  // 在数组的末尾插入元素
    }

    @Override
    public T extractMax() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");  // 如果数组为空，抛出异常
        }

        int maxIndex = 0;
        // 遍历数组，找到最大值的索引
        for (int i = 1; i < n; i++) {
            if (arr[i].compareTo(arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }

        T maxValue = arr[maxIndex];

        // 将最大值后面的元素左移，覆盖最大值
        for (int i = maxIndex; i < n - 1; i++) {
            arr[i] = arr[i + 1];
        }

        n--;  // 减少数组中的实际元素个数
        return maxValue;
    }

    @Override
    public T max() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");  // 如果数组为空，抛出异常
        }

        int maxIndex = 0;
        // 遍历数组，找到最大值的索引
        for (int i = 1; i < n; i++) {
            if (arr[i].compareTo(arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }

        return arr[maxIndex];
    }

    @Override
    public int size() { return n; }

    @Override
    public boolean isEmpty() { return n == 0; }

    // 下面的方法都未实现，可以根据需要进行实现
    @Override
    public T extractMin() { return null; }

    @Override
    public T min() { return null; }

    @Override
    public void merge(PriorityQueue<T> other) {}

    @Override
    public void clear() {}

    public static void main(String[] args) {
        UnorderedArrayPQImpl<Integer> pq = new UnorderedArrayPQImpl<>(10);
        pq.insert(5);
        pq.insert(3);
        pq.insert(7);
        pq.insert(2);

        System.out.println(pq.extractMax());  // 输出7
        System.out.println(pq.extractMax());  // 输出5
    }
}
