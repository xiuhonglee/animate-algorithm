package analysis_of_algorithms;

import java.math.BigInteger;

/**
 * 空间复杂度分析，分以下两步骤:
 *  1. 矩阵存储：我们需要常量空间来存储两个2x2的矩阵，所以其空间复杂度为O(1)。
 *  2. 递归的深度：使用快速幂算法时，递归的最大深度与N的二进制长度成正比，即O(log N)。
 *     由于每一级递归中我们并没有保存额外的矩阵或其他大量数据（我们只是对矩阵进行乘法），所以这里的空间复杂度主要是由于递归调用栈的深度，也是O(log N)。
 *  综上: 使用矩阵乘法和快速幂方法求第N个斐波那契数的空间复杂度是O(log N)。
 */


public class MatrixExponentiation {

    public static void main(String[] args) {

        /**
         * 测试代码
         */

        System.out.println(fibonacci(9));  // 输出34，第9个斐波那契数
        System.out.println(fibonacci(10)); // 输出55
        System.out.println(fibonacci(0));  // 输出0
        System.out.println(fibonacci(50));  // 输出 12586269025
        System.out.println(fibonacci(100));  // 输出 354224848179261915075
        System.out.println(fibonacci(10000)); // 输出第10000个斐波那契数
    }

    // 2x2矩阵乘法
    public static BigInteger[][] multiply(BigInteger[][] A, BigInteger[][] B) {
        BigInteger[][] result = new BigInteger[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                result[i][j] = (A[i][0].multiply(B[0][j])).add(A[i][1].multiply(B[1][j]));
            }
        }

        return result;
    }

    // 快速幂计算矩阵的n次方
    public static BigInteger[][] power(BigInteger[][] matrix, int n) {
        if (n == 1) {
            return matrix;
        }

        if (n % 2 == 0) {
            BigInteger[][] halfPower = power(matrix, n / 2);
            return multiply(halfPower, halfPower);
        } else {
            return multiply(matrix, power(matrix, n - 1));
        }
    }

    // 使用矩阵指数法计算第n个斐波那契数
    public static BigInteger fibonacci(int n) {
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }

        // 基本矩阵
        BigInteger[][] matrix = {
                {BigInteger.ONE, BigInteger.ONE},
                {BigInteger.ONE, BigInteger.ZERO}
        };

        // 计算矩阵的n-1次方
        matrix = power(matrix, n - 1);

        // 结果即为矩阵的[0][0]元素
        return matrix[0][0];
    }
}
