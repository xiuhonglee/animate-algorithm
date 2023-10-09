package analysis_of_algorithms;

public class FibonacciRecursive {
    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static void main(String[] args) {
        // 测试斐波那契数列的前10项
        for (int i = 0; i < 10; i++) {
            System.out.println("F(" + i + ") = " + fibonacciRecursive(i));
        }
    }
}
