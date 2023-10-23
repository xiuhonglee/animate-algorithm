package priority_queues;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class IndexedBinaryHeap<T extends Comparable<T>> implements PriorityQueue<T> {
    private int capacity;
    private int size;
    private Object[] items;  // 存储元素
    private int[] indices;  // 索引
    private int[] reverseIndices;  // 反向索引

    public IndexedBinaryHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        items = new Object[capacity];
        indices = new int[capacity];
        reverseIndices = new int[capacity];
        Arrays.fill(indices, -1);
        Arrays.fill(reverseIndices, -1);
    }

    // 辅助函数
    private int getParentIndex(int index) { return (index - 1) / 2; }  // 获取父节点的索引
    private int getLeftChildIndex(int index) { return 2 * index + 1; }  // 获取左子节点的索引
    private int getRightChildIndex(int index) { return 2 * index + 2; }  // 获取右子节点的索引
    private boolean hasParent(int index) { return getParentIndex(index) >= 0; }  // 判断是否有父节点
    private boolean hasLeftChild(int index) { return getLeftChildIndex(index) < size; }  // 判断是否有左子节点
    private boolean hasRightChild(int index) { return getRightChildIndex(index) < size; }  // 判断是否有右子节点

    // 交换两个索引位置的元素
    private void swap(int indexOne, int indexTwo) {
        Object temp = items[indexOne];
        items[indexOne] = items[indexTwo];
        items[indexTwo] = temp;

        indices[reverseIndices[indexOne]] = indexTwo;
        indices[reverseIndices[indexTwo]] = indexOne;

        int tempIndex = reverseIndices[indexOne];
        reverseIndices[indexOne] = reverseIndices[indexTwo];
        reverseIndices[indexTwo] = tempIndex;
    }

    // 上浮操作
    private void siftUp() {
        int index = size - 1;
        while (hasParent(index) && ((T) items[getParentIndex(index)]).compareTo((T) items[index]) > 0) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    // 下沉操作
    private void siftDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && ((T) items[getRightChildIndex(index)]).compareTo((T) items[smallerChildIndex]) < 0) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (((T) items[index]).compareTo((T) items[smallerChildIndex]) < 0) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }

            index = smallerChildIndex;
        }
    }

    @Override
    public void insert(T item) {
        if (size == capacity) {
            items = Arrays.copyOf(items, capacity * 2);
            indices = Arrays.copyOf(indices, capacity * 2);
            reverseIndices = Arrays.copyOf(reverseIndices, capacity * 2);
            capacity *= 2;
        }
        items[size] = item;
        reverseIndices[size] = size;
        indices[size] = size;
        size++;
        siftUp();
    }

    @Override
    public T max() {
        if (isEmpty()) throw new NoSuchElementException("The queue is empty");
        T max = (T) items[0];
        for (int i = 1; i < size; i++) {
            if (((T) items[i]).compareTo(max) > 0) {
                max = (T) items[i];
            }
        }
        return max;
    }

    @Override
    public T min() {
        if (isEmpty()) throw new NoSuchElementException("The queue is empty");
        return (T) items[0];
    }

    @Override
    public T extractMax() {
        if (isEmpty()) throw new NoSuchElementException("The queue is empty");
        T max = max();
        for (int i = 0; i < size; i++) {
            if (((T) items[i]).compareTo(max) == 0) {
                swap(i, size - 1);
                size--;
                siftDown();
                break;
            }
        }
        return max;
    }

    @Override
    public T extractMin() {
        if (isEmpty()) throw new NoSuchElementException("The queue is empty");
        T item = (T) items[0];
        items[0] = items[size - 1];
        size--;
        siftDown();
        return item;
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
        while (!other.isEmpty()) {
            insert(other.extractMin());
        }
    }

    @Override
    public void clear() {
        size = 0;
    }

    public static void main(String[] args) {
        IndexedBinaryHeap<Integer> heap = new IndexedBinaryHeap<>(10);
        heap.insert(10);
        heap.insert(4);
        heap.insert(15);
        heap.insert(7);
        heap.insert(3);

        System.out.println(heap.min());   // 3
        System.out.println(heap.extractMin());   // 3
        System.out.println(heap.min());   // 4
    }
}
