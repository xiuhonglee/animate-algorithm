package priority_queues;

import java.util.PriorityQueue;
import java.util.Comparator;

interface MaxPQ<T extends Comparable<T>> {
    void insert(T value);
    T extractMax();
    boolean isEmpty();
}

class JavaPriorityQueueWrapper implements MaxPQ<Integer> {
    private PriorityQueue<Integer> pq;

    public JavaPriorityQueueWrapper() {
        this.pq = new PriorityQueue<>(Comparator.reverseOrder()); // 反转自然顺序以得到最大堆
    }

    @Override
    public void insert(Integer value) {
        pq.offer(value);
    }

    @Override
    public Integer extractMax() {
        return pq.poll();
    }

    @Override
    public boolean isEmpty() {
        return pq.isEmpty();
    }
}
