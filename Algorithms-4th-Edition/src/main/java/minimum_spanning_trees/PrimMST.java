package minimum_spanning_trees;

import edu.princeton.cs.algs4.*;

public class PrimMST {
    private static final double FLOATING_POINT_EPSILON = 1.0E-12; // 浮点数精度误差阈值

    private Edge[] edgeTo;        // edgeTo[v] = 从树顶点到非树顶点的最短边
    private double[] distTo;      // distTo[v] = 此类边的权重
    private boolean[] marked;     // marked[v] = 如果v在树上则为true，否则为false
    private IndexMinPQ<Double> pq; // 最小索引优先队列

    /**
     * 计算一个加权图的最小生成树（或森林）。
     * @param G 加权图
     */
    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<Double>(G.V());
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;

        for (int v = 0; v < G.V(); v++)      // 从每个顶点开始运行，以找到
            if (!marked[v]) prim(G, v);      // 最小生成森林

        // 检查最优条件
        assert check(G);
    }

    // 在图G中，从顶点s开始运行Prim算法
    private void prim(EdgeWeightedGraph G, int s) {
        distTo[s] = 0.0;
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            scan(G, v);
        }
    }

    // 扫描顶点v
    private void scan(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;         // v-w是过时的边
            if (e.weight() < distTo[w]) {
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
                else                pq.insert(w, distTo[w]);
            }
        }
    }

    /**
     * 返回最小生成树（或森林）中的边。
     * @return 作为边的可迭代对象的最小生成树（或森林）中的边
     */
    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<Edge>();
        for (int v = 0; v < edgeTo.length; v++) {
            Edge e = edgeTo[v];
            if (e != null) {
                mst.enqueue(e);
            }
        }
        return mst;
    }

    /**
     * 返回最小生成树（或森林）中边的权重总和。
     * @return 最小生成树（或森林）中边的权重总和
     */
    public double weight() {
        double weight = 0.0;
        for (Edge e : edges())
            weight += e.weight();
        return weight;
    }

    // 检查最优条件（时间与E V lg* V成正比）
    private boolean check(EdgeWeightedGraph G) {

        // 检查权重
        double totalWeight = 0.0;
        for (Edge e : edges()) {
            totalWeight += e.weight();
        }
        if (Math.abs(totalWeight - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("边的权重总和与weight()不相等：%f vs. %f\n", totalWeight, weight());
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

        // 检查它是最小覆盖森林（切割优化条件）
        for (Edge e : edges()) {

            // MST中除了e的所有边
            uf = new UF(G.V());
            for (Edge f : edges()) {
                int x = f.either(), y = f.other(x);
                if (f != e) uf.union(x, y);
            }

            // 检查e是横跨割中最小权重的边
            for (Edge f : G.edges()) {
                int x = f.either(), y = f.other(x);
                if (uf.find(x) != uf.find(y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("边 " + f + " 违反了切割优化条件");
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * 对{@code PrimMST}数据类型进行单元测试。
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        PrimMST mst = new PrimMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
