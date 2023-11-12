package heap_sort;

import java.util.Random;

public class HeapSortPerformanceTest {

    public static void main(String[] args) {
        // 定义不同的测试规模
        int[] sizes = {100000, 200000, 400000, 800000, 1600000}; // 修改了规模，以避免内存溢出

        // 对每个规模进行测试
        for (int size : sizes) {
            long totalTimeBeforeOptimization = 0;
            long totalTimeAfterOptimization = 0;

            // 运行5次实验并取平均值
            for (int i = 0; i < 5; i++) {
                Integer[] originalArray = generateRandomIntegerArray(size);

                // 测试优化前的堆排序
                Integer[] arrayBeforeOptimization = originalArray.clone();
                long startTime = System.currentTimeMillis();
                HeapSortOrigin.heapSort(arrayBeforeOptimization); // 确保这里使用的是泛型版本
                long endTime = System.currentTimeMillis();
                totalTimeBeforeOptimization += (endTime - startTime);

                // 测试优化后的堆排序
                Integer[] arrayAfterOptimization = originalArray.clone();
                startTime = System.currentTimeMillis();
                HeapSortFloyd.heapSort(arrayAfterOptimization); // 使用泛型版本
                endTime = System.currentTimeMillis();
                totalTimeAfterOptimization += (endTime - startTime);
            }

            long averageTimeBeforeOptimization = totalTimeBeforeOptimization / 5;
            long averageTimeAfterOptimization = totalTimeAfterOptimization / 5;

            System.out.println("规模: " + size);
            System.out.println("优化前平均时间: " + averageTimeBeforeOptimization + "ms");
            System.out.println("优化后平均时间: " + averageTimeAfterOptimization + "ms");
            System.out.println();
        }
    }

    // 生成随机整数数组（使用Integer而不是int）
    private static Integer[] generateRandomIntegerArray(int size) {
        Integer[] array = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
        return array;
    }
}
