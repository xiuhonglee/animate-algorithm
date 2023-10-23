package priority_queues;

import java.util.PriorityQueue;

public class PriorityQueueExample {

    public static void main(String[] args) {
        // 创建一个优先队列
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // 向队列中添加元素
        priorityQueue.offer(10);
        priorityQueue.offer(4);
        priorityQueue.offer(15);
        priorityQueue.offer(7);
        priorityQueue.offer(3);

        // 输出队列中的最小元素，但不移除
        System.out.println("队列中的最小元素（不移除）: " + priorityQueue.peek());   // 3

        // 移除并输出队列中的最小元素
        System.out.println("移除并返回队列中的最小元素: " + priorityQueue.poll());   // 3

        // 输出移除后队列中的最小元素
        System.out.println("再次查看队列中的最小元素: " + priorityQueue.peek());   // 4

        // 为了展示优先队列的特性，我们继续向队列中添加元素并查看最小值
        priorityQueue.offer(2);
        System.out.println("添加新元素后队列中的最小元素: " + priorityQueue.peek());   // 2
    }
}
