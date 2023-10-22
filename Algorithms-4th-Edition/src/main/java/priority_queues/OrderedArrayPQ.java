package priority_queues;

public class OrderedArrayPQ<T extends Comparable<T>> implements PriorityQueue<T> {
    private T[] queue;  // 用于存储队列元素的数组
    private int size;  // 记录当前队列的大小

    @SuppressWarnings("unchecked")
    public OrderedArrayPQ(int capacity) {
        queue = (T[]) new Comparable[capacity];  // 初始化数组
        size = 0;  // 初始大小为0
    }

    // 插入操作
    public void insert(T item) {
        if (size == queue.length) {
            throw new RuntimeException("Queue is full");  // 当队列满时抛出异常
        }
        int i = size - 1;
        // 找到新元素应该插入的位置，同时将较大的元素向后移动
        while (i >= 0 && queue[i].compareTo(item) < 0) {
            queue[i + 1] = queue[i];
            i--;
        }
        queue[i + 1] = item;  // 插入新元素
        size++;  // 更新队列大小
    }


    // 删除并返回最大元素
    public T extractMax() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");  // 当队列为空时抛出异常
        }
        T maxItem = queue[--size];  // 获取最大元素
        queue[size] = null;  // 避免对象游离，帮助垃圾收集器回收内存
        return maxItem;  // 返回最大元素
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 获取队列的大小
    public int size() {
        return size;
    }

    // 以下接口未实现...
    @Override
    public T max() { return null; }

    @Override
    public T min() { return null; }

    @Override
    public T extractMin() { return null; }

    @Override
    public void merge(PriorityQueue<T> other) { }

    @Override
    public void clear() { }


    // 测试代码
    public static void main(String[] args) {
        OrderedArrayPQ<Integer> pq = new OrderedArrayPQ<>(10);
        pq.insert(10);  // 插入元素
        pq.insert(20);  // 插入元素
        pq.insert(5);   // 插入元素
        System.out.println(pq.extractMax());  // 输出并删除最大元素：20
        System.out.println(pq.extractMax());  // 输出并删除最大元素：10
        System.out.println(pq.extractMax());  // 输出并删除最大元素：5
    }
}
