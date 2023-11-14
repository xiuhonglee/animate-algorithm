package heap_sort;

import java.util.Random;

public class HeapSortStringPerformanceTest {

    public static void main(String[] args) {
        // 定义不同的测试规模
        int[] sizes = {200000, 400000, 600000};  // 对于长字符串，规模应适当减小

        // 对每个规模进行测试
        for (int size : sizes) {
            long totalTimeBeforeOptimization = 0;
            long totalTimeAfterOptimization = 0;

            // 运行5次实验并取平均值
            for (int i = 0; i < 5; i++) {
                String[] originalArray = generateRandomStringArray(size, 500); // 生成长度为 N 的随机字符串

                // 测试优化后的堆排序
                String[] arrayAfterOptimization = originalArray.clone();
                long startTime = System.currentTimeMillis();
                HeapSortFloyd.heapSort(arrayAfterOptimization);
                long endTime = System.currentTimeMillis();
                totalTimeAfterOptimization += (endTime - startTime);

                // 测试优化前的堆排序
                String[] arrayBeforeOptimization = originalArray.clone();

                startTime = System.currentTimeMillis();
                HeapSortStandard.heapSort(arrayBeforeOptimization);
                endTime = System.currentTimeMillis();
                totalTimeBeforeOptimization += (endTime - startTime);
            }

            long averageTimeBeforeOptimization = totalTimeBeforeOptimization / 5;
            long averageTimeAfterOptimization = totalTimeAfterOptimization / 5;

            System.out.println("规模: " + size);
            System.out.println("优化前平均时间: " + averageTimeBeforeOptimization + "ms");
            System.out.println("优化后平均时间: " + averageTimeAfterOptimization + "ms");
            System.out.println();
        }
    }

    // 生成随机字符串数组
    private static String[] generateRandomStringArray(int size, int stringLength) {
        String[] array = new String[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = generateRandomString(stringLength, random);
        }
        return array;
    }

    // 生成指定长度的随机字符串
    private static String generateRandomString(int length, Random random) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
}
