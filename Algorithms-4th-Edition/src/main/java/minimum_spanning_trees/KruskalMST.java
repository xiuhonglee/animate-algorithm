package minimum_spanning_trees;

import edu.princeton.cs.algs4.*;

import java.util.Arrays;

public class KruskalMST {
    private static final double FLOATING_POINT_EPSILON = 1.0E-12; // 浮点数精度误差阈值

    private double weight;                        // MST的权重
    private Queue<Edge> mst = new Queue<Edge>();  // MST中的边

    /**
     * 计算一个加权图的最小生成树（或森林）。
     * @param G 加权图
     */
    public KruskalMST(EdgeWeightedGraph G) {

        // 创建边的数组，并按权重排序
        Edge[] edges = new Edge[G.E()];
        int t = 0;
        for (Edge e: G.edges()) {
            edges[t++] = e;
        }
        Arrays.sort(edges);

        // 执行贪心算法
        UF uf = new UF(G.V());
        for (int i = 0; i < G.E() && mst.size() < G.V() - 1; i++) {
            Edge e = edges[i];
            int v = e.either();
            int w = e.other(v);

            // v-w不会创建环
            if (uf.find(v) != uf.find(w)) {
                uf.union(v, w);     // 合并v和w的分量
                mst.enqueue(e);     // 将边e添加到mst
                weight += e.weight();
            }
        }

        // 检查最优条件
        assert check(G);
    }

    /**
     * 返回最小生成树（或森林）中的边。
     * @return 作为边的可迭代对象的最小生成树（或森林）中的边
     */
    public Iterable<Edge> edges() {
        return mst;
    }

    /**
     * 返回最小生成树（或森林）中边的权重总和。
     * @return 最小生成树（或森林）中边的权重总和
     */
    public double weight() {
        return weight;
    }

    // 检查最优条件（时间与E V lg* V成正比）
    private boolean check(EdgeWeightedGraph G) {
        // 检查边的总权重
        double total = 0.0;
        for (Edge e : edges()) {
            total += e.weight();
        }
        if (Math.abs(total - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("边的权重总和与weight()不相等：%f vs. %f\n", total, weight());
            return false;
        }

        // 检查它是无环的
        UF uf = new UF(G.V());
        for (Edge e : edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.find(v) == uf.find(w)) {
                System.err.println("不是森林");
                return false;
            }
            uf.union(v, w);
        }

        // 检查它是一个覆盖森林
        for (Edge e : G.edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.find(v) != uf.find(w)) {
                System.err.println("不是覆盖森林");
                return false;
            }
        }

        // 检查它是最小覆盖森林（割优化条件）
        for (Edge e : edges()) {

            // MST中除了e的所有边
            uf = new UF(G.V());
            for (Edge f : mst) {
                int x = f.either(), y = f.other(x);
                if (f != e) uf.union(x, y);
            }

            // 检查e是横跨割中最小权重的边
            for (Edge f : G.edges()) {
                int x = f.either(), y = f.other(x);
                if (uf.find(x) != uf.find(y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("边 " + f + " 违反了割优化条件");
                        return false;
                    }
                }
            }

        }

        return true;
    }

    /**
     * 对{@code KruskalMST}数据类型进行单元测试。
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        KruskalMST mst = new KruskalMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
