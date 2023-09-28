package analysis_of_algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class SearchComparison {

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int size = 10; // 小数组，线性搜索更快
        HashSet<Integer> set = new HashSet<>();
        Random rand = new Random();

        // 生成独一无二的随机数
        while (set.size() < size) {
            set.add(rand.nextInt());
        }

        // 转换为数组并排序
        ArrayList<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        int[] arr = list.stream().mapToInt(i -> i).toArray();

        // 选择一个随机的目标值
        int target = arr[rand.nextInt(arr.length)];

        // 计算线性搜索的平均运行时间
        long totalTime = 0;
        int repeatTimes = 100;
        for (int i = 0; i < repeatTimes; i++) {
            long startTime = System.nanoTime();
            linearSearch(arr, target);
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        System.out.println("Average Linear Search Time: " + (totalTime / repeatTimes) + " nanoseconds(纳秒)");

        // 计算二分搜索的平均运行时间
        totalTime = 0;
        for (int i = 0; i < repeatTimes; i++) {
            long startTime = System.nanoTime();
            binarySearch(arr, target);
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        System.out.println("Average Binary Search Time: " + (totalTime / repeatTimes) + " nanoseconds(纳秒)");
    }
}
