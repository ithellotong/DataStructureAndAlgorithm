package com.hellotong.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 图
 *
 * @author hellotong
 * @date 2020-09-02 10:25
 */
public class Graph {
    /**
     * 存储图的顶点集合
     */
    private ArrayList<String> vertexList;

    /**
     * 用于存储顶点之间权值的邻接矩阵
     */
    private int[][] edges;

    /**
     * 图中边的个数
     */
    private int numOfEdges;

    /**所有顶点是否被访问
     */
    private static boolean[] isVisited;

    public static void main(String[] args) {
        // 测试图
        // 顶点的个数
        int n = 5;
        // 创建顶点数组
        String[] vertexs = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        
        // 循环的添加顶点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        
        // 添加边
        // A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        
        // 显示图的邻接矩阵
        graph.showGraph();
        
        // 图的深度优先遍历
        System.out.println("深度优先遍历");
        // graph.dfs();
        System.out.println("广度优先遍历");
        graph.bfs(isVisited, 0);
    }

    /**
     * 构造器
     * @param n 图中顶点的个数
     */
    public Graph(int n) {
        // 初始化图的属性
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    /**
     * 对 dfs 进行一个重载，保证能够 dfs 遍历所有的节点
     * 注意：这里考虑到非联通图的情况，对于此问题不需要此步也可
     */
    public void dfs() {
        for (int i = 0; i < getNumOfVertexs(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 以顶点 v 为起始顶点开始深度优先遍历
     * @param isVisited 存放所有顶点是否被访问
     * @param v 起始顶点
     */
    private void dfs(boolean[] isVisited, int v) {
        // 访问初始结点 v，并标记结点 v 为已访问。
        System.out.print(getVertexValueByIndex(v) + "->");
        isVisited[v] = true;
        // 查找结点 v 的第一个邻接结点 w
        int w = getFirstNeighbor(v);
        // 如果 w 存在
        while (w != -1) {
            // 如果顶点 w 没有被访问过，以 w 为起始顶点继续 dfs
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            // 否则，寻找 v 的下一个邻接顶点，继续 while 循环判断
            w = getNextNeighbor(v, w);
        }
    }

    /**
     * 对 bfs 进行一个重载，保证能够 dfs 遍历所有的节点
     * 注意：这里考虑到非联通图的情况，对于此问题不需要此步也可
     */
    public void bfs() {
        for (int i = 0; i < getNumOfVertexs(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    /**
     * 以顶点 v 为起始顶点开始广度优先遍历，也可以看成是层级遍历
     * @param isVisited 存放所有顶点是否被访问
     * @param v 起始顶点
     */
    public void bfs(boolean[] isVisited, int v) {
        // 记录队列头部顶点的下标
        int u;
        // 存放顶点 v 的邻接顶点的下标
        int w;
        // 定义一个队列记录顶点访问的顺序
        // 用 LinkedList，使用 removeFirst 和 addLast 来模拟队列
        LinkedList<Integer> queue = new LinkedList<>();
        // 访问初始结点 v 并标记结点 v 为已访问
        System.out.print(getVertexValueByIndex(v) + "->");
        isVisited[v] = true;
        // 结点 v 入队列
        queue.addLast(v);
        
        // 队列不为空
        while (!queue.isEmpty()) {
            // 出队列，取得队头结点 u
            u = queue.removeFirst();
            // 查找结点 u 的第一个邻接结点 w
            w = getFirstNeighbor(u);
            // 说明 u 有邻接顶点
            while (w != -1) {
                // 判断 w 是否被访问过
                // 如果没有被访问过
                if (!isVisited[w]) {
                    // 输出
                    System.out.print(getVertexValueByIndex(w) + "->");
                    // 标记为已访问
                    isVisited[w] = true;
                    // 把 w 入队列
                    queue.addLast(w);
                }
                // 如果 w 被访问过，则继续找以 u 为顶点，以 w 为邻接顶点的下一个邻接顶点
                w = getNextNeighbor(u, w);
            }
        }
        
    }

    /**
     * 获取下标为 index 的顶点的第一个邻接顶点的下标 w
     * @param index 传入的顶点下标
     * @return 如果第一个邻接顶点存在则返回下标，否则返回 -1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < getNumOfVertexs(); j++) {
            // 表示 index <-> j 邻接
            if (edges[index][j] == 1) {
                return j;
            }
        }
        
        return -1;
    }

    /**
     * 根据顶点 index 的前一个邻接顶点的下标来获取顶点 index 的下一个邻接顶点的下标
     * @param w 前一个邻接顶点的下标
     * @return 如果存在，下一个邻接顶点的下标，否则返回 -1
     */
    public int getNextNeighbor(int index, int w) {
        for (int j = w + 1; j < getNumOfVertexs(); j++) {
            if (edges[index][j] == 1) {
                return j;
            }
        }
        
        return -1;
    }

    /**
     * 向图中添加顶点
     * @param vertex 顶点值
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 向图中添加边
     * @param vertex1Index 顶点 1 的索引下标，如：A:0 B:1
     * @param vertex2Index 顶点 2 的索引下标
     * @param weight 边的权重，顶点之间直接相连为 1，否则为 0
     */
    public void insertEdge(int vertex1Index, int vertex2Index, int weight) {
        // 因为是无向图，所以需要建立双向连接
        edges[vertex1Index][vertex2Index] = weight;
        edges[vertex2Index][vertex1Index] = weight;
        // 插入一条边之后，边的数目要加 1
        numOfEdges++;
    }

    /*
    图的一些常用方法
     */

    /**
     * 获取图中边的个数
     * @return 边的个数
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 获取图中顶点的个数
     * @return 顶点的个数
     */
    public int getNumOfVertexs() {
        return vertexList.size();
    }

    /**
     * 获取顶点索引下标对应的顶点值，如：0:A 1:B
     * @param index 顶点索引下标
     * @return 顶点值
     */
    public String getVertexValueByIndex(int index) {
        return vertexList.get(index);
    }

    /**
     * 获取顶点 1 和顶点 2 对应边的权值
     * @param vertex1Index 顶点 1 的下标
     * @param vertex2Index 顶点 2 的下标
     * @return 权值
     */
    public int getWeightOfEdge(int vertex1Index, int vertex2Index) {
        return edges[vertex1Index][vertex2Index];
    }

    /**
     * 显示图对应的邻接矩阵
     */
    public void showGraph() {
        for (int[] row : edges) {
            System.out.println(Arrays.toString(row));
        }
    }
}
