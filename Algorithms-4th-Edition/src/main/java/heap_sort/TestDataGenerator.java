package heap_sort;

import java.util.Random;

public class TestDataGenerator {

    // Generate a large random array
    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(size);
        }
        return array;
    }

    // Generate a reverse sorted array
    public static int[] generateReverseSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        return array;
    }

    // Generate a partially sorted array
    public static int[] generatePartiallySortedArray(int size, int sortedFraction) {
        int[] array = generateRandomArray(size);
        java.util.Arrays.sort(array, 0, size / sortedFraction);
        return array;
    }

    // Generate a nearly sorted array
    public static int[] generateNearlySortedArray(int size, int perturbation) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        Random rand = new Random();
        for (int i = 0; i < perturbation; i++) {
            int index1 = rand.nextInt(size);
            int index2 = rand.nextInt(size);
            int temp = array[index1];
            array[index1] = array[index2];
            array[index2] = temp;
        }
        return array;
    }

    // Generate an array with few unique elements
    public static int[] generateFewUniqueElementsArray(int size, int uniqueElements) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(uniqueElements);
        }
        return array;
    }
}
