package analysis_of_algorithms;

import java.math.BigInteger;

public class MatrixPower {

    private static class Matrix2x2 {
        BigInteger a, b, c, d;

        Matrix2x2(BigInteger a, BigInteger b, BigInteger c, BigInteger d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        Matrix2x2 multiply(Matrix2x2 other) {
            return new Matrix2x2(
                this.a.multiply(other.a).add(this.b.multiply(other.c)),
                this.a.multiply(other.b).add(this.b.multiply(other.d)),
                this.c.multiply(other.a).add(this.d.multiply(other.c)),
                this.c.multiply(other.b).add(this.d.multiply(other.d))
            );
        }
    }

    public static Matrix2x2 matrixPower(Matrix2x2 base, int exp) {
        if (exp == 1) {
            return base;
        }

        Matrix2x2 halfPower = matrixPower(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPower.multiply(halfPower);
        } else {
            return base.multiply(halfPower.multiply(halfPower));
        }
    }

    public static void main(String[] args) {
        // 测试用例
        Matrix2x2 Q = new Matrix2x2(BigInteger.ONE, BigInteger.ONE, BigInteger.ONE, BigInteger.ZERO);
        
        int testN = 5;
        Matrix2x2 result = matrixPower(Q, testN);
        System.out.println("Q^" + testN + ":");
        System.out.println(result.a + " " + result.b);
        System.out.println(result.c + " " + result.d);

        testN = 99;
        result = matrixPower(Q, testN);
        System.out.println("\nQ^" + testN + ":");
        System.out.println(result.a + " " + result.b);
        System.out.println(result.c + " " + result.d);
    }
}
