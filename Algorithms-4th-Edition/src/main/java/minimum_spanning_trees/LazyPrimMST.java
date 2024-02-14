package minimum_spanning_trees;

import edu.princeton.cs.algs4.*;

public class LazyPrimMST {
    private static final double FLOATING_POINT_EPSILON = 1.0E-12; // 浮点数的精度范围

    private double weight;       // MST的总权重
    private Queue<Edge> mst;     // MST中的边
    private boolean[] marked;    // 如果v在树上，则marked[v] = true
    private MinPQ<Edge> pq;      // 具有一个端点在树中的边

    /**
     * 计算一个加权边图的最小生成树（或森林）。
     * @param G 加权边图
     */
    public LazyPrimMST(EdgeWeightedGraph G) {
        mst = new Queue<Edge>();
        pq = new MinPQ<Edge>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)     // 从所有顶点运行Prim算法
            if (!marked[v]) prim(G, v);     // 获取一个最小生成森林

        // 检查最优条件
        assert check(G);
    }

    // 运行Prim算法
    private void prim(EdgeWeightedGraph G, int s) {
        scan(G, s);
        while (!pq.isEmpty()) {                        // 当mst有V-1条边时停止更好
            Edge e = pq.delMin();                      // pq上的最小边
            int v = e.either(), w = e.other(v);        // 两个端点
            assert marked[v] || marked[w];
            if (marked[v] && marked[w]) continue;      // 懒惰，v和w已经被扫描
            mst.enqueue(e);                            // 将e加入到MST
            weight += e.weight();
            if (!marked[v]) scan(G, v);               // v成为树的一部分
            if (!marked[w]) scan(G, w);               // w成为树的一部分
        }
    }

    // 如果另一个端点还没有被扫描，则将所有与v相邻的边e加入到pq中

    private void scan(EdgeWeightedGraph G, int v) {
        assert !marked[v];
        marked[v] = true;
        for (Edge e : G.adj(v))
            if (!marked[e.other(v)]) pq.insert(e); // 如果边e的另一端点未被扫描，则插入pq
    }

    /**
     * 返回最小生成树（或森林）中的边。
     * @return 作为边的可迭代集合的最小生成树（或森林）中的边
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

        // 检查权重
        double totalWeight = 0.0;
        for (Edge e : edges()) {
            totalWeight += e.weight();
        }
        if (Math.abs(totalWeight - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("边的权重之和不等于weight(): %f vs. %f\n", totalWeight, weight());
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
     * 对{@code LazyPrimMST}数据类型进行单元测试。
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        LazyPrimMST mst = new LazyPrimMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
