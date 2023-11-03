package priority_queues;

import java.util.Random;

/**
 * 单独为 java 标准库实现的PQ 提供的性能测试代码
 * 结果证明：标准库实现的PQ比我们简单实现的二叉堆更快一些
 */
public class PerformanceTest2 {

    private static Random random = new Random();

    public static void main(String[] args) {
        int testCount = 10;
        int[] sizes = {1000, 2000, 4000, 8000, 16000, 32000, 64000}; // 倍率实验


        System.out.println("Java标准库PriorityQueue性能测试:");
        testPerformance(new JavaPriorityQueueWrapper(), testCount, sizes);
        /**
         * 本机 RESULT
         对于大小 1000, 平均用时: 391,216 纳秒 < 1毫秒
         对于大小 2000, 平均用时: 346,091 纳秒 < 1毫秒
         对于大小 4000, 平均用时: 574,508 纳秒 < 1毫秒
         对于大小 8000, 平均用时: 1,252,154 纳秒 = 1.25 毫秒
         对于大小 16000, 平均用时: 2,980,183 纳秒 = 2.98 毫秒
         对于大小 32000, 平均用时: 6,679,366 纳秒 = 6.68 毫秒
         对于大小 64000, 平均用时: 15,562,404 纳秒 = 15 毫秒
         */
    }

    public static void testPerformance(MaxPQ<Integer> pq, int testCount, int[] sizes) {
        for (int size : sizes) {
            long totalTime = 0;
            for (int i = 0; i < testCount; i++) {
                totalTime += testPriorityQueue(pq, size);
            }
            long averageTime = totalTime / testCount;
            System.out.println("对于大小 " + size + ", 平均用时: " + averageTime + " 纳秒");
        }
    }

    public static long testPriorityQueue(MaxPQ<Integer> pq, int size) {
        // 插入元素
        for (int i = 0; i < size; i++) {
            pq.insert(random.nextInt(Integer.MAX_VALUE));
        }

        long startTime = System.nanoTime();

        // 删除一半的元素
        for (int i = 0; i < size / 2; i++) {
            pq.extractMax();
        }

        // 重新插入元素
        for (int i = 0; i < size / 2; i++) {
            pq.insert(random.nextInt(Integer.MAX_VALUE));
        }

        // 删除所有元素
        while (!pq.isEmpty()) {
            pq.extractMax();
        }

        long endTime = System.nanoTime();

        return endTime - startTime;
    }
}
