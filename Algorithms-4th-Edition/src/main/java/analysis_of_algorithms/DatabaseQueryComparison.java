package analysis_of_algorithms;

public class DatabaseQueryComparison {

    // 模拟大常数延迟的一次性查询
    public static void batchQuery(int dataSize) throws InterruptedException {
        int bigConstantDelay = 2000; // 假设2秒的网络延迟
        Thread.sleep(bigConstantDelay); // 模拟网络延迟
        for (int i = 0; i < dataSize; i++) {
            // 处理数据
        }
    }

    // 模拟小常数延迟的多次查询
    public static void multipleSmallQueries(int querySize, int queryTimes) throws InterruptedException {
        int smallConstantDelay = 100; // 假设0.1秒的网络延迟
        for (int i = 0; i < queryTimes; i++) {
            Thread.sleep(smallConstantDelay); // 模拟网络延迟
            for (int j = 0; j < querySize; j++) {
                // 处理数据
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int dataSize = 10000;
        int querySize = 1000;
        int queryTimes = 10; // 确保 querySize * queryTimes = dataSize
        
        long start = System.currentTimeMillis();
        batchQuery(dataSize);
        long end = System.currentTimeMillis();
        System.out.println("Batch Query Time: " + (end - start) + " milliseconds");
        
        start = System.currentTimeMillis();
        multipleSmallQueries(querySize, queryTimes);
        end = System.currentTimeMillis();
        System.out.println("Multiple Small Queries Time: " + (end - start) + " milliseconds");
    }
}
