package com.hellotong.kruskal;

import java.util.Arrays;

/**
 * 克鲁斯卡尔算法求最小生成树
 * 解决公交站问题
 *
 * @author hellotong
 * @date 2020-09-12 11:55
 */
public class KruskalAlgorithm {
    /**
     * 图中边的个数
     */
    private int edgeNum;

    /**
     * 顶点数据数组
     */
    private char[] vertexs;

    /**
     * 图的邻接矩阵
     */
    private int[][] matrix;

    /**
     * INF 表示两个顶点之间不连通
     */
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // 0 表示自己与自己也不相连，与 INF 区分
        int[][] matrix = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };
        KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm(vertexs, matrix);
        kruskalAlgorithm.print();
        
        kruskalAlgorithm.kruskal();
    }

    public KruskalAlgorithm(char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;

        // 统计边的个数
        for (int i = 0; i < vertexs.length; i++) {
            // j = i + 1，不把 0 计算在边的数目内
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    /**
     * 对所有边组成的数组按照边的权值使用冒泡排序进行从小到大排序
     * @param edges 所有边组成的数组
     * @return 排序后的边数组
     */
    public EData[] sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - i - 1; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
        
        return edges; 
    }

    /**
     * 根据图的邻接矩阵获取所有边组成的数组，不包括 0 和 INF
     * @return 所有边组成的数组
     */
    public EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            // j = i + 1 跳过 <A, A>, <B, B> 因为权值为 0
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    EData edge = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                    edges[index++] = edge;
                }
            }
        }
        return edges;
    }

    /**
     * 根据顶点字符数据获取下标
     * @param ch 顶点字符数据，如 'A'、'B'
     * @return 如果 ch 存在返回下标，否则返回 -1
     */
    public int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (ch == vertexs[i]) {
                return i;
            }
        }
        // 如果 ch 不存在返回 -1
        return -1;
    }

    /**
     * 获取顶点下标为 i 的顶点的终点下标
     * @param ends 记录的是所有顶点的终点下标
     * @param i 传入的顶点的下标
     * @return 传入顶点的终点下标
     */
    public int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        // 循环结束，ends[i] == 0 说明 i 为终点，即为传入顶点的终点下标
        
        return i;
    }

    public void kruskal() {
        // 结果数组的索引
        int index = 0;
        // 存放图的最小生成树的每一条边，顶点个数 - 1
        EData[] res = new EData[vertexs.length - 1];
        // 存储 "已有最小生成树" 中每个顶点的终点下标，总共的顶点个数为 vertexs.length
        int[] ends = new int[vertexs.length];
        
        // 获取图中所有的边
        EData[] edges = getEdges();
        // 对所有边进行排序
        sortEdges(edges);
        
        // 遍历排序后的数组，依次取出权值最小的边
        for (int i = 0; i < edgeNum; i++) {
            // 获取边 edge[i] 的第一个顶点(起点) 的下标
            int p1 = getPosition(edges[i].start);
            // 获取边 edge[i] 的第二个顶点的下标
            int p2 = getPosition(edges[i].end);
            
            // 获取 p1 在已有最小生成树中的终点
            int m = getEnd(ends, p1);
            // 获取 p2 在已有最小生成树中的终点
            int n = getEnd(ends, p2);
            
            // 判断加入 edge[i] 这条边是否会构成回路
            if (m != n) {
                // 不构成回路，将这条边加入到结果数组中
                res[index++] = edges[i];
                // 设置 m 在已有最小生成树中的终点为 n
                ends[m] = n;
            }
        }
        
        // 打印最小生成树
        System.out.println("最小生成树为：");
        for (int i = 0; i < index; i++) {
            System.out.println(res[i]);
        }
    }
    
    /**
     * 打印图的邻接矩阵
     */
    public void print() {
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();
        }
    }
}

/**
 * 边的信息
 */
class EData {
    /**
     * 边的一个顶点
     */
    char start;

    /**
     * 边的另一个顶点
     */
    char end;

    /**
     * 边的权值
     */
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "<" + start +
                ", " + end +
                ">=" + weight +
                '}';
    }
}
