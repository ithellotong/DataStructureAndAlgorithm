package com.hellotong.floyd;

/**
 * 弗洛伊德算法求以各个顶点为出发顶点到各个顶点之间的最短路径
 * 
 * @author hellotong
 * @date 2020-09-13 12:45
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };

        Graph graph = new Graph(vertex, matrix);
        graph.floyd();
        graph.showGraph();
    }
}

class Graph {
    /**
     * 顶点数组
     */
    char[] vertex;

    /**
     * 存放顶点到顶点之间的最短距离
     */
    int[][] dis;

    /**
     * 存放各个顶点的前驱顶点下标
     */
    int[][] pre;

    /**
     * 构造器
     * @param vertex 顶点数组
     * @param matrix 邻接矩阵
     */
    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[vertex.length][vertex.length];
        // 初始化 pre
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                pre[i][j] = i;
            }
        }
    }
    
    public void showGraph() {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // 显示前驱顶点二维数组
        System.out.println("各顶点的前驱顶点为：");
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.print(vertex[pre[i][j]] + " ");
            }
            System.out.println();
        }
        System.out.println();
        
        // 显示距离数组
        System.out.println("各个顶点到各个顶点的最短距离为：");
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%c->%c :%5d  ", vertex[i], vertex[j], dis[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 弗洛伊德算法，容易理解，容易实现
     */
    public void floyd() {
        int len = 0;
        // 遍历中间顶点 k，k 依次取 [A, B, C, D, E, F, G]
        for (int k = 0; k < vertex.length; k++) {
            // 遍历出发顶点 i，i 依次取 [A, B, C, D, E, F, G]
            for (int i = 0; i < vertex.length; i++) {
                // 遍历从顶点 i 到达顶点 j，j 依次取 [A, B, C, D, E, F, G]
                for (int j = 0; j < vertex.length; j++) {
                    // 计算通过顶点 k，顶点 i 到顶点 j 的距离
                    len = dis[i][k] + dis[k][j];
                    // 如果顶点 i 通过顶点 k 到达顶点 j 的距离小于直连的距离
                    if (len < dis[i][j]) {
                        // 更新距离和前驱关系
                        dis[i][j] = len;
                        // 把顶点 j 的前驱 i 设置为 k
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}
