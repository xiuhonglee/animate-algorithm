package analysis_of_algorithms;

import java.util.Arrays;
import java.util.Random;

public class SortComparison {

    // 冒泡排序
    public static void bubbleSort(int[] arr) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i - 1] > arr[i]) {
                    int temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }

    // 快速排序
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low, j = high;
        while (true) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;

            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            } else {
                return j;
            }
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int size = 10; // 较小的数组大小

        int[] arr1 = new int[size];
        for (int i = 0; i < size; i++) {
            arr1[i] = random.nextInt(1000); // 生成随机数填充数组
        }

        int[] arr2 = Arrays.copyOf(arr1, size);

        long start = System.nanoTime();
        bubbleSort(arr1);
        long end = System.nanoTime();
        System.out.println("Bubble Sort Time: " + (end - start) + " nanoseconds");

        start = System.nanoTime();
        quickSort(arr2, 0, arr2.length - 1);
        end = System.nanoTime();
        System.out.println("Quick Sort Time: " + (end - start) + " nanoseconds");
    }
}
