package priority_queues;

import java.lang.reflect.Constructor;
import java.util.Random;

public class PerformanceTest {

    // 定义一个随机数生成器
    private static Random random = new Random();

    public static void main(String[] args) {
        int testCount = 10; // 测试次数
        int[] sizes = {1000, 2000, 4000, 8000, 16000, 32000, 64000}; // 倍率实验


        /**  注意：下面测试时间比较长（大约 7 分钟左右），请耐心等待  **/
        /**  注意：下面测试时间比较长（大约 7 分钟左右），请耐心等待  **/
        /**  注意：下面测试时间比较长（大约 7 分钟左右），请耐心等待  **/

        /**  注意：你也可以注释掉一部分代码，每次少测试几个  **/

//        System.out.println("测试无序数组实现的优先队列 : ");
//        testPerformance(UnorderedArrayPQImpl.class, testCount, sizes);
        /**
         * 本机 RESULT:
             对于大小 1000, 平均用时: 4922395 纳秒
             对于大小 2000, 平均用时: 9355299 纳秒
             对于大小 4000, 平均用时: 30486750 纳秒
             对于大小 8000, 平均用时: 116351200 纳秒
             对于大小 16000, 平均用时: 457130008 纳秒
             对于大小 32000, 平均用时: 1872413958 纳秒
             对于大小 64000, 平均用时: 9903,996,691 纳秒 = 9903毫秒 = 9.903秒
         */

//        System.out.println("测试有序数组实现的优先队列 : ");
//        testPerformance(OrderedArrayPQ.class, testCount, sizes);
        /**
         * 本机 RESULT:
             对于大小 1000, 平均用时: 2582254 纳秒
             对于大小 2000, 平均用时: 3122579 纳秒
             对于大小 4000, 平均用时: 6364745 纳秒
             对于大小 8000, 平均用时: 19739558 纳秒
             对于大小 16000, 平均用时: 74516633 纳秒
             对于大小 32000, 平均用时: 283364246 纳秒
             对于大小 64000, 平均用时: 1195,712,475 纳秒 = 1195毫秒 = 1.195秒
         */

//        System.out.println("测试无序链表实现的优先队列:");
//        testPerformance(UnorderedLinkedListPQ.class, testCount, sizes);
        /**
         * 本机 RESULT:
         对于大小 1000, 平均用时: 6349962 纳秒
         对于大小 2000, 平均用时: 19187150 纳秒
         对于大小 4000, 平均用时: 68820037 纳秒
         对于大小 8000, 平均用时: 279916141 纳秒
         对于大小 16000, 平均用时: 1119193737 纳秒
         对于大小 32000, 平均用时: 4550743020 纳秒
         对于大小 64000, 平均用时: 33227,107,983 纳秒 = 33227 毫秒 = 33秒
         */

        System.out.println("测试有序链表实现的优先队列:");
        testPerformance(OrderedLinkedListPQ.class, testCount, sizes);
        /**
         * 本机 RESULT:
         对于大小 1000, 平均用时: 2905429 纳秒
         对于大小 2000, 平均用时: 4440229 纳秒
         对于大小 4000, 平均用时: 13339858 纳秒
         对于大小 8000, 平均用时: 57600179 纳秒
         对于大小 16000, 平均用时: 244011012 纳秒
         对于大小 32000, 平均用时: 1125402287 纳秒
         对于大小 64000, 平均用时: 7359,276,858 纳秒 = 7359 毫秒 = 7.36秒
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
