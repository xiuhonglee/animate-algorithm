package analysis_of_algorithms;

public class MemoryAnalysis {

    /**
     * 说明：Java中的对象和基本数据类型的内存使用情况受到多种因素的影响，如JVM实现、系统架构（32位 vs 64位）
     * 下面分析基于 JVM 64 位的情况
     */

    public static void main(String[] args) {
        // 基本数据类型 (通常存储在栈内存)
        byte b = 10; // 1字节
        char c = 'A'; // 2字节
        short s = 123; // 2字节
        int i = 12345; // 4字节
        long l = 1234567890L; // 8字节
        float f = 1.23f; // 4字节
        double d = 1.23456; // 8字节
        boolean bool = true; // JVM依赖, 通常为1字节

        // 包装类型 (存储在堆内存)
        Byte bObj = b; // 16字节 (对象开销)
        Character cObj = c; // 16字节 (对象开销)
        Short sObj = s; // 16字节 (对象开销)
        Integer iObj = i; // 16字节 (对象开销)
        Long lObj = l; // 24字节 (对象开销 + long)
        Float fObj = f; // 16字节 (对象开销)
        Double dObj = d; // 24字节 (对象开销 + double)
        Boolean boolObj = bool; // 16字节 (对象开销)

        // 数组 (存储在堆内存)
        int[] arr = new int[10]; // 56字节 (16字节对象开销 + 4*10)

        // 简单数据结构 (例如String, 存储在堆内存)
        String str = "Hello"; // 三部分：对象开销, char数组, 哈希值。具体大小取决于字符串长度。

        // 链表 (存储在堆内存)
        Node node = new Node(5); // 对象大小取决于节点数量

        // 自定义的类 (存储在堆内存)
        CustomClass custom = new CustomClass(); // 大小取决于属性和父类
    }

    // 链表的节点定义
    static class Node {
        int value;
        Node next;
        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    // 自定义类定义
    static class CustomClass {
        int attribute1;
        double attribute2;
        char attribute3;
        // 更多属性...
    }
}
