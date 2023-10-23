package priority_queues;

import java.lang.reflect.Constructor;
import java.util.Random;

public class PerformanceTest {

    // 定义一个随机数生成器
    private static Random random = new Random();

    public static void main(String[] args) {
        int testCount = 10; // 测试次数
        int[] sizes = {1000, 5000, 10000, 50000}; // 不同的队列大小。你也可以按照倍率调整样本大小


        /**  注意：下面测试时间比较长（大约 7 分钟左右），请耐心等待  **/
        /**  注意：下面测试时间比较长（大约 7 分钟左右），请耐心等待  **/
        /**  注意：下面测试时间比较长（大约 7 分钟左右），请耐心等待  **/

        /**  注意：你也可以注释掉一部分代码，每次少测试几个  **/

        System.out.println("测试无序数组实现的优先队列 : ");
        testPerformance(UnorderedArrayPQImpl.class, testCount, sizes);
        /**
         * 本机 RESULT:
          对于大小 1000, 平均用时: 5,041,958 纳秒 = 5.041 毫秒
          对于大小 5000, 平均用时: 48,380,162 纳秒 = 48.38 毫秒
          对于大小 10000, 平均用时: 180,173,970 纳秒 = 180.173 毫秒
          对于大小 50000, 平均用时: 5,053,212,808 纳秒 = 5053 毫秒= 5.053 秒
         */

        System.out.println("测试有序数组实现的优先队列 : ");
        testPerformance(OrderedArrayPQ.class, testCount, sizes);
        /**
         * 本机 RESULT:
         对于大小 1000, 平均用时: 2,518,683 纳秒 = 2.518 毫秒
         对于大小 5000, 平均用时: 9,573,195 纳秒 = 9.573 毫秒
         对于大小 10000, 平均用时: 24,481,258 纳秒 = 24.481 毫秒
         对于大小 50000, 平均用时: 726,777,400 纳秒 = 726 毫秒 = 0.726 秒
         */

        System.out.println("测试无序链表实现的优先队列:");
        testPerformance(UnorderedLinkedListPQ.class, testCount, sizes);
        /**
         * 本机 RESULT:
         对于大小 1000, 平均用时: 5,465,879 纳秒 = 5.466 毫秒
         对于大小 5000, 平均用时: 109,859,212 纳秒 = 109 毫秒
         对于大小 10000, 平均用时: 433,003,654 纳秒 = 433 毫秒
         对于大小 50000, 平均用时: 15,696,266,650 纳秒 = 15696 毫秒 = 15.697 秒
         */

        System.out.println("测试有序链表实现的优先队列:");
        testPerformance(UnorderedLinkedListPQ.class, testCount, sizes);
        /**
         * 本机 RESULT:
         对于大小 1000, 平均用时: 6,424,237 纳秒 = 6.424 毫秒
         对于大小 5000, 平均用时: 112,271,083 纳秒 = 112.271 毫秒
         对于大小 10000, 平均用时: 439,082,329 纳秒 = 439.082329 毫秒
         对于大小 50000, 平均用时: 16426,444,583 纳秒 = 16426 毫秒 = 16.426 秒
         */

        System.out.println("二叉堆实现的优先队列:");
        testPerformance(BinaryHeapPQ.class, testCount, sizes);
        /**
         * 本机 RESULT
         * 对于大小 1000, 平均用时: 2971729 纳秒 < 1毫秒
         * 对于大小 5000, 平均用时: 611,3587 纳秒 < 1毫秒
         * 对于大小 10000, 平均用时: 4,764,079 纳秒 = 4毫秒
         * 对于大小 50000, 平均用时: 26,054,495 纳秒 = 26毫秒
         */
    }

    public static void testPerformance(Class<?> clazz, int testCount, int[] sizes) {
        for (int size : sizes) {
            long totalTime = 0;
            for (int i = 0; i < testCount; i++) {
                totalTime += testPriorityQueue(clazz, size);
            }
            long averageTime = totalTime / testCount;
            System.out.println("对于大小 " + size + ", 平均用时: " + averageTime + " 纳秒");
        }
    }

    public static long testPriorityQueue(Class<?> clazz, int size) {
        Object priorityQueue = null;
        try {
            // 尝试使用接受 int 参数的构造函数
            Constructor<?> constructor = clazz.getConstructor(int.class);
            priorityQueue = constructor.newInstance(size);
        } catch (NoSuchMethodException e) {
            try {
                // 如果没有找到接受 int 参数的构造函数，尝试使用无参数构造函数
                Constructor<?> constructor = clazz.getConstructor();
                priorityQueue = constructor.newInstance();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 插入元素，填满队列
        for (int i = 0; i < size; i++) {
            try {
                clazz.getMethod("insert", Comparable.class).invoke(priorityQueue, random.nextInt(Integer.MAX_VALUE));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        long startTime = System.nanoTime();

        // 删除一半的元素
        for (int i = 0; i < size / 2; i++) {
            try {
                clazz.getMethod("extractMax").invoke(priorityQueue);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // 重新插入元素，填满队列
        for (int i = 0; i < size / 2; i++) {
            try {
                clazz.getMethod("insert", Comparable.class).invoke(priorityQueue, random.nextInt(Integer.MAX_VALUE));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // 删除所有元素
        try {
            while (!(boolean) clazz.getMethod("isEmpty").invoke(priorityQueue)) {
                clazz.getMethod("extractMax").invoke(priorityQueue);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long endTime = System.nanoTime();

        return endTime - startTime;
    }
}
