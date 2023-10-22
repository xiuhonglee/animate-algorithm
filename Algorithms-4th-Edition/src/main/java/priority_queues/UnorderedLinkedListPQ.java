package priority_queues;


public class UnorderedLinkedListPQ<T extends Comparable<T>> implements PriorityQueue<T> {

    // 定义链表节点
    private class Node {
        T item;
        Node next;

        Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    private Node first;  // 链表的第一个节点
    private int size;  // 优先队列的大小

    public UnorderedLinkedListPQ() {
        first = null;
        size = 0;
    }

    @Override
    public void insert(T item) {
        Node newNode = new Node(item, first);
        first = newNode;
        size++;
    }

    @Override
    public T max() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        Node maxNode = first;
        Node current = first.next;
        while (current != null) {
            if (current.item.compareTo(maxNode.item) > 0) {
                maxNode = current;
            }
            current = current.next;
        }
        return maxNode.item;
    }

    @Override
    public T min() {
        return null;
    }

    @Override
    public T extractMax() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }

        Node maxPrev = null;
        Node maxNode = first;

        // 找到最大元素的节点和它的前一个节点
        Node prev = first;
        Node current = first.next;
        while (current != null) {
            if (current.item.compareTo(maxNode.item) > 0) {
                maxNode = current;
                maxPrev = prev;
            }
            prev = current;
            current = current.next;
        }

        // 如果最大节点是第一个节点
        if (maxNode == first) {
            first = first.next;
        } else {
            maxPrev.next = maxNode.next;
        }

        size--;
        return maxNode.item;
    }

    @Override
    public boolean isEmpty() { return size == 0; }

    // 下面接口未实现
    @Override
    public T extractMin() { return null; }

    @Override
    public int size() { return 0; }


    @Override
    public void merge(PriorityQueue<T> other) {}

    @Override
    public void clear() {}

    public static void main(String[] args) {
        UnorderedLinkedListPQ<Integer> pq = new UnorderedLinkedListPQ<>();
        pq.insert(10);
        pq.insert(20);
        pq.insert(5);
        System.out.println(pq.extractMax());  // 输出: 20
        System.out.println(pq.max());  // 输出: 10
        System.out.println(pq.extractMax());  // 输出: 10
    }
}
