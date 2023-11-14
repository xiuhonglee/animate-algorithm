package heap_sort;

import java.util.Arrays;

public class HeapSortFloydCount {

    private static int comparisonCount = 0;
    private static int swapCount = 0;

    // 泛化的堆排序函数
    public static <T extends Comparable<T>> void heapSort(T[] arr) {
        comparisonCount = 0;
        swapCount = 0;

        int n = arr.length;

        // 构建最大堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 一个个从堆顶取出元素
        for (int i = n - 1; i > 0; i--) {
            // 将堆尾元素保存到临时变量
            T temp = arr[i];

            // 将堆顶元素放到堆尾
            arr[i] = arr[0];

            // 从堆顶开始调整堆
            siftDown(arr, i, 0, temp);
        }
    }

    // 泛化的调整堆的函数
    private static <T extends Comparable<T>> void siftDown(T[] arr, int n, int i, T temp) {
        int parent = i; // 起始位置为当前“空”堆顶
        int child;

        while (true) {
            int leftChild = 2 * parent + 1;
            int rightChild = 2 * parent + 2;

            if (leftChild >= n) {
                break; // 没有子节点了
            }

            if (rightChild >= n) {
                child = leftChild; // 只有左子节点
            } else {
                comparisonCount++; // 对左右子节点进行比较
                child = arr[leftChild].compareTo(arr[rightChild]) > 0 ? leftChild : rightChild;
            }

            arr[parent] = arr[child];
            parent = child;
        }

        // 将原先的堆尾元素放到调整后的位置
        arr[parent] = temp;

        // 可能的上浮操作
        while (parent > 0 && arr[parent].compareTo(arr[(parent - 1) / 2]) > 0) {
            comparisonCount++; // 对子节点和父节点进行比较
            T swap = arr[parent];
            arr[parent] = arr[(parent - 1) / 2];
            arr[(parent - 1) / 2] = swap;
            swapCount++;
            parent = (parent - 1) / 2;
        }
    }

    // 泛化的构建堆的函数
    private static <T extends Comparable<T>> void heapify(T[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l].compareTo(arr[largest]) > 0) {
            comparisonCount++; // 对子节点和父节点进行比较
            largest = l;
        }

        if (r < n && arr[r].compareTo(arr[largest]) > 0) {
            comparisonCount++; // 对子节点和父节点进行比较
            largest = r;
        }

        if (largest != i) {
            T swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            swapCount++;

            heapify(arr, n, largest);
        }
    }
    // 测试代码（主函数）
    public static void main(String[] args) {
        // 整数数组示例
        Integer[] intArr = {12, 11, 13, 5, 6, 7};
        heapSort(intArr);
        System.out.println("Sorted integer array (Floyd):");
        System.out.println(Arrays.toString(intArr));
        System.out.println("Comparisons: " + comparisonCount + ", Swaps: " + swapCount);

        // 字符串数组示例
        String[] stringArr = {"banana", "apple", "cherry", "date"};
        heapSort(stringArr);
        System.out.println("Sorted string array (Floyd):");
        System.out.println(Arrays.toString(stringArr));
        System.out.println("Comparisons: " + comparisonCount + ", Swaps: " + swapCount);

        // 创建一个包含20个随机整数的数组
        Integer[] testArray = new Integer[40];
        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = (int) (Math.random() * 100); // 生成0到99之间的随机整数
        }
        heapSort(testArray);
        System.out.println("Sorted integer array (Floyd):");
        System.out.println(Arrays.toString(testArray));
        System.out.println("Comparisons: " + comparisonCount + ", Swaps: " + swapCount);

    }
}
