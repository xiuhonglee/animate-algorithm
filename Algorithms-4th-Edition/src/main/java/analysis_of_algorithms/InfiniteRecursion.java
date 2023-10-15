package analysis_of_algorithms;

/**
 * 无限递归示例
 * Error: java.lang.StackOverflowError
 */
public class InfiniteRecursion {

    public static void main(String[] args) {
        try {
            recursiveFunction(0);
        } catch (StackOverflowError e) {
            // java.lang.StackOverflowError
            System.err.println(e);
        }
    }

    public static void recursiveFunction(int depth) {

        // 递归深度（我本机最大深度: 22164，你运行的结果取决于你的运行环境）
        System.out.println(depth);
        recursiveFunction(depth + 1);
    }
}
