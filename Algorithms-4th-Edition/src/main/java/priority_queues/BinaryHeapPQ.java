package priority_queues;

import java.util.Arrays;

public class BinaryHeapPQ<T extends Comparable<T>> implements PriorityQueue<T> {

    private T[] heap;
    private int size;

    @SuppressWarnings("unchecked")
    public BinaryHeapPQ(int capacity) {
        heap = (T[]) new Comparable[capacity + 1];
        size = 0;
    }

    @Override
    public void insert(T item) {
        if (size == heap.length - 1) {
            throw new RuntimeException("Queue is full");
        }
        heap[++size] = item;
        swim(size);
    }

    @Override
    public T max() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return heap[1];
    }

    @Override
    public T extractMax() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        T maxItem = heap[1];
        swap(1, size);
        heap[size--] = null;
        sink(1);
        return maxItem;
    }

    // Other implemented methods

    @Override
    public T min() {
        throw new UnsupportedOperationException("BinaryHeapPQ is a max-priority queue");
    }

    @Override
    public T extractMin() {
        throw new UnsupportedOperationException("BinaryHeapPQ is a max-priority queue");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void merge(PriorityQueue<T> other) {
        if (other instanceof BinaryHeapPQ) {
            BinaryHeapPQ<T> otherHeap = (BinaryHeapPQ<T>) other;
            for (int i = 1; i <= otherHeap.size; i++) {
                insert(otherHeap.heap[i]);
            }
        } else {
            throw new IllegalArgumentException("Unsupported type: " + other.getClass());
        }
    }

    @Override
    public void clear() {
        Arrays.fill(heap, null);
        size = 0;
    }

    // Helper methods

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            swap(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= size) {
            int j = 2*k;
            if (j < size && less(j, j+1)) j++;
            if (!less(k, j)) break;
            swap(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }

    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Test cases
    public static void main(String[] args) {
        BinaryHeapPQ<Integer> pq = new BinaryHeapPQ<>(10);
        pq.insert(10);
        pq.insert(20);
        pq.insert(5);
        System.out.println(pq.extractMax());  // Output: 20
        System.out.println(pq.size());       // Output: 2

        BinaryHeapPQ<Integer> pq2 = new BinaryHeapPQ<>(10);
        pq2.insert(15);
        pq.merge(pq2);
        System.out.println(pq.size());       // Output: 3

        pq.clear();
        System.out.println(pq.isEmpty());   // Output: true
    }
}
