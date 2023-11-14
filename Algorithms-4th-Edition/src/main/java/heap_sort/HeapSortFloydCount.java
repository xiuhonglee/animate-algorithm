package heap_sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
         * 规模为 10^3: 比较次数 8866, 交换次数 978
         * 规模为 10^4: 比较次数 121134, 交换次数 9339
         * 规模为 10^5: 比较次数 1551587, 交换次数 98880
         * 规模为 10^6: 比较次数 18737124, 交换次数 971560
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
