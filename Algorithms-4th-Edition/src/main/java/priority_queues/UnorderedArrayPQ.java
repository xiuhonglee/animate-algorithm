package priority_queues;

/**
 * Desc: 无序数组实现 PQ 插入、提取最大值接口
 * 分析:
 *  1. 提取最大值操作的时间复杂度（O(n)）
 *  2. 插入操作的时间复杂度（O(1)）
 *  3. 这个简单的实现无法插入新元素，在实际应用中，可以通过动态扩展数组来解决这个问题
 */

public class UnorderedArrayPQ {

    private int[] arr;    // 用于存储数据的数组
    private int n;        // 数组中的实际元素个数

    // 初始化一个给定大小的优先队列
    public UnorderedArrayPQ(int capacity) {
        arr = new int[capacity];
        n = 0;
    }

    // 插入操作
    public void insert(int value) {
        if (n == arr.length) {
            throw new RuntimeException("队列已满");  // 如果数组已满，抛出异常
        }
        arr[n++] = value;  // 在数组的末尾插入元素
    }

    // 提取最大值操作
    public int extractMax() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");  // 如果数组为空，抛出异常
        }

        int maxIndex = 0;
        // 遍历数组，找到最大值的索引
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }

        int maxValue = arr[maxIndex];

        // 将最大值后面的元素左移，覆盖最大值
        for (int i = maxIndex; i < n - 1; i++) {
            arr[i] = arr[i + 1];
        }

        n--;  // 减少数组中的实际元素个数
        return maxValue;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return n == 0;
    }

    public static void main(String[] args) {
        // 测试代码
        UnorderedArrayPQ pq = new UnorderedArrayPQ(10);
        pq.insert(5);
        pq.insert(3);
        pq.insert(7);
        pq.insert(2);

        System.out.println(pq.extractMax());  // 输出7
        System.out.println(pq.extractMax());  // 输出5
    }
}
