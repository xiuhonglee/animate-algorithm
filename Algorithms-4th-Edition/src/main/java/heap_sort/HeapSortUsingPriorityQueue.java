package heap_sort;

/**
 * 使用 java 提供的优先队列实现堆排序
 *
 * 注意：
 * 1. 并非原地排序，需要额外的空间
 * 2. 性能可能不如直接使用数组实现的堆排序高效
 */

import java.util.PriorityQueue;

public class HeapSortUsingPriorityQueue {
    public static void sort(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 将所有元素加入到优先队列
        for (int value : arr) {
            pq.offer(value);
        }

        // 依次取出元素，实现排序
        for (int i = 0; i < arr.length; i++) {
            arr[i] = pq.poll();
        }
    }
    
    public static void main(String[] args) {
        int[] array = {12, 11, 13, 5, 6, 7};
        sort(array);
        for (int value : array) {
            System.out.print(value + " ");
        }
    }
}
