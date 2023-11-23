package heap_sort;

import java.util.Arrays;

public class HeapBottomUp {
    
    // 自底向上建堆方法
    public static void heapifyBottomUp(int[] arr) {
        int n = arr.length;
        // 重点：从第一个非叶子节点开始，从右向左进行"下沉"
        for (int i = n / 2 - 1; i >= 0; i--) {
            sink(arr, n, i);
        }
    }

    // 下沉操作
    private static void sink(int[] arr, int n, int k) {
        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            if (j < n - 1 && arr[j] < arr[j + 1]) j++;
            if (arr[k] >= arr[j]) break;
            swap(arr, k, j);
            k = j;
        }
    }

    // 交换数组中的两个元素
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arrTopDown = {19, 100, 1, 8, 3, 25, 4, 36, 17};
        int[] arrBottomUp = arrTopDown.clone();

        HeapBottomUp.heapifyBottomUp(arrBottomUp);

        System.out.println("Heap built Bottom-Up: " + Arrays.toString(arrBottomUp));
    }
}
