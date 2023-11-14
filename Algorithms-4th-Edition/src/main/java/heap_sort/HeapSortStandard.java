package heap_sort;

import java.util.Arrays;

public class HeapSortStandard {

    // 泛型堆排序函数
    public static <T extends Comparable<T>> void heapSort(T[] arr) {
        int n = arr.length;

        // 构建大顶堆（未优化版本，从上到下）
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 从堆顶取出最大的元素，放到数组末尾，并重新调整堆
        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    // 递归: 使得以root为根的子树成为大顶堆
    private static <T extends Comparable<T>> void heapify(T[] arr, int n, int root) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        // 如果左子节点比根大
        if (left < n && arr[left].compareTo(arr[largest]) > 0) {
            largest = left;
        }

        // 如果右子节点比最大值还大
        if (right < n && arr[right].compareTo(arr[largest]) > 0) {
            largest = right;
        }

        // 如果最大值不是根
        if (largest != root) {
            swap(arr, root, largest);
            heapify(arr, n, largest);
        }
    }

    // 交换数组中的两个元素
    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        // 整数数组示例
        Integer[] intArr = {12, 11, 13, 5, 6, 7};
        heapSort(intArr);
        System.out.println("Sorted integer array:");
        System.out.println(Arrays.toString(intArr));

        // 字符串数组示例
        String[] stringArr = {"banana", "apple", "cherry", "date"};
        heapSort(stringArr);
        System.out.println("Sorted string array:");
        System.out.println(Arrays.toString(stringArr));
    }
}
