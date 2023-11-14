package heap_sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class HeapSortStandardCount {
    // 用于计数的静态变量
    static int comparisonCount = 0;
    static int swapCount = 0;

    // 泛型堆排序函数
    public static <T extends Comparable<T>> void heapSort(T[] arr) {
        int n = arr.length;
        comparisonCount = 0;
        swapCount = 0;

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
            comparisonCount++;
        }

        // 如果右子节点比最大值还大
        if (right < n && arr[right].compareTo(arr[largest]) > 0) {
            largest = right;
            comparisonCount++;
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
        swapCount++;
    }

    // 生成随机不重复数组的方法
    private static Integer[] generateUniqueRandomArray(int size, int range) {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < size) {
            set.add(random.nextInt(range));
        }
        return set.toArray(new Integer[0]);
    }

    // 测试排序并打印结果的方法
    private static void testSort(Integer[] array, String sizeLabel) {
        heapSort(array);
        // 为简洁起见，不打印整个数组
        System.out.println("Sorted array size " + sizeLabel + ": [First 10 elements]: " + Arrays.toString(Arrays.copyOf(array, 10)));
        System.out.println("Comparisons: " + comparisonCount + ", Swaps: " + swapCount);
    }

    public static void main(String[] args) {

        /**
         * 本机运行结果：
         * 规模为 10^3: 比较次数 11828, 交换次数 9119
         * 规模为 10^4: 比较次数 167383, 交换次数 124517
         * 规模为 10^5: 比较次数 2177017, 交换次数 1581495
         * 规模为 10^6: 比较次数 26680099, 交换次数 19078774
         */
        // 测试N=10^3大小的数组
        Integer[] array1K = generateUniqueRandomArray(1_000, 10_000);
        testSort(array1K, "10^3");

        // 测试N=10^4大小的数组
        Integer[] array10K = generateUniqueRandomArray(10_000, 100_000);
        testSort(array10K, "10^4");

        // 测试N=10^5大小的数组
        Integer[] array100K = generateUniqueRandomArray(100_000, 1_000_000);
        testSort(array100K, "10^5");

        // 测试N=10^6大小的数组
        Integer[] array1M = generateUniqueRandomArray(1_000_000, 10_000_000);
        testSort(array1M, "10^6");

        /**
         * 注意：下面是10^9大小的随机数组，你需要提前给程序分配足够多的堆内存，运行时间会比较漫长 !!!
         * 注意：下面是10^9大小的随机数组，你需要提前给程序分配足够多的堆内存，运行时间会比较漫长 !!!
         * 注意：下面是10^9大小的随机数组，你需要提前给程序分配足够多的堆内存，运行时间会比较漫长 !!!
         */
//        final int SIZE = 1_000_000_000; // 10^9
//        // 生成大数组
//        Integer[] largeArray = new Integer[SIZE];
//        Random random = new Random();
//        for (int i = 0; i < SIZE; i++) {
//            largeArray[i] = random.nextInt(Integer.MAX_VALUE);
//        }
//        heapSort(largeArray);
//        // 打印一些排序后的数据进行验证（例如前10个元素）
//        System.out.println(Arrays.toString(Arrays.copyOf(largeArray, 10)));
//        System.out.println("Comparisons: " + comparisonCount + ", Swaps: " + swapCount);
    }
}
