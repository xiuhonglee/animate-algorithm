package heap_sort;

import java.util.Arrays;

public class HeapTopDown {
    
    // 自顶向下建堆方法
    public static void heapifyTopDown(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            swim(arr, i);
        }
    }

    // 上浮操作
    private static void swim(int[] arr, int k) {
        while (k > 0 && arr[k] > arr[(k - 1) / 2]) {
            swap(arr, k, (k - 1) / 2);
            k = (k - 1) / 2;
        }
    }

    // 交换数组中的两个元素
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 测试用例
    public static void main(String[] args) {
        int[] arrTopDown = {19, 100, 1, 8, 3, 25, 4, 36, 17};
        HeapTopDown.heapifyTopDown(arrTopDown);

        System.out.println("Heap built Top-Down: " + Arrays.toString(arrTopDown));
    }
}
