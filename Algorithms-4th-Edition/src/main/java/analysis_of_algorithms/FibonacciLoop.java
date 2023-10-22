package analysis_of_algorithms;

public class FibonacciLoop {

    /**
     * 使用直接迭代的方法计算斐波那契数
     * 
     * 空间复杂度：O(1) - 因为我们只需要存储前两个斐波那契数
     * 
     * @param n 要计算的斐波那契数的位置
     * @return 第n个斐波那契数
     */
    public static int fibonacci(int n) {
        if (n <= 1) return n; // 当n为0或1时，直接返回n

        int prev2 = 0; // 存储n-2位置的斐波那契数
        int prev1 = 1; // 存储n-1位置的斐波那契数
        int result = 0; // 存储计算结果

        for (int i = 2; i <= n; i++) {
            result = prev1 + prev2; // 当前斐波那契数是前两个数的和
            prev2 = prev1; // 更新n-2位置的值
            prev1 = result; // 更新n-1位置的值
        }

        return result;
    }

    public static void main(String[] args) {
        // 测试用例
        System.out.println(fibonacci(0));  // 输出: 0
        System.out.println(fibonacci(1));  // 输出: 1
        System.out.println(fibonacci(5));  // 输出: 5
        System.out.println(fibonacci(10)); // 输出: 55
        System.out.println(fibonacci(50)); // 输出: 12586269025
    }
}
