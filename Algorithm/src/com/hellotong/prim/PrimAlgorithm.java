package com.hellotong.prim;

import java.util.Arrays;

/**
 * 普里姆算法求最小生成树
 * 解决村庄修路问题
 *
 * @author hellotong
 * @date 2020-09-10 12:44
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        // 顶点数据
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // 顶点个数
        int vertex = data.length;
        MGraph graph = new MGraph(vertex);
        MinTree minTree = new MinTree();
        // 创建图的邻接矩阵，10000 表示两点不可达
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };
        minTree.createGraph(graph, vertex, weight, data);
        minTree.showGraph(graph);
        
        minTree.prim(graph, 0);
    }
    
    
}

/**
 * 图的最小生成树
 */
class MinTree {
    /**
     * 创建图的邻接矩阵
     *
     * @param graph  图
     * @param vertex 图的顶点个数
     * @param weight 邻接矩阵
     * @param data   顶点数据
     */
    public void createGraph(MGraph graph, int vertex, int[][] weight, char[] data) {
        for (int i = 0; i < vertex; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < vertex; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    /**
     * 普里姆算法
     * @param graph 图
     * @param v 顶点的下标，表示从哪个顶点开始处理
     */
    public void prim(MGraph graph, int v) {
        // visited 存放 0、1，0 表示该顶点还没有被访问过，1 表示已被访问
        // 默认为 0
        int[] visited = new int[graph.vertex];
        
        // 标记当前顶点被访问
        visited[v] = 1;
        
        // 表示最小权值，初始化为一个大数，在遍历的过程中会被替换
        int minWeight = 10000;
        
        // 表示两个顶点的下标，初始化为 -1，不指向任何顶点
        int h1 = -1;
        int h2 = -1;
        
        // 因为最小生成树的边为 图的顶点个数 - 1，所以只需循环 graph.vertex - 1 次即可得到最小生成树
        // 这层循环没有任何具体含义，只是进行进行 graph.vertex - 1 次循环而已，循环结束最小生成树即生成
        for (int k = 0; k < graph.vertex - 1; k++) {
            // 双层循环的作用是：以已被访问过的顶点开始去遍历与未被访问过的顶点之间直接相连的边
            // 生成一棵生成树，找到当前循环的最小边
            for (int i = 0; i < graph.vertex; i++) {
                for (int j = 0; j < graph.vertex; j++) {
                    // 表示下标为 i 的顶点是被访问过的，下标为 j 的顶点是未被访问过的
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        // 替换 minWeight
                        minWeight = graph.weight[i][j];
                        // 记录 i 和 j 的下标
                        h1 = i;
                        h2 = j;
                    }
                }
            }

            System.out.println("边 <" + graph.data[h1] + "," + graph.data[h2] + ">" + " 的权值：" + graph.weight[h1][h2]);
            
            // 把未被访问过的顶点 h2 即 j 设置为已访问 
            visited[h2] = 1;
            // 设置 minWeight 为最大值，进行下一次的双层循环比较
            minWeight = 10000;
        }
    }

    /**
     * 显示图的邻接矩阵
     */
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }
}

class MGraph {
    /**
     * 顶点个数
     */
    int vertex;

    /**
     * 存放顶点的数据
     */
    char[] data;

    /**
     * 图的邻接矩阵
     */
    int[][] weight;

    public MGraph(int vertex) {
        this.vertex = vertex;
        this.data = new char[vertex];
        this.weight = new int[vertex][vertex];
    }
}
