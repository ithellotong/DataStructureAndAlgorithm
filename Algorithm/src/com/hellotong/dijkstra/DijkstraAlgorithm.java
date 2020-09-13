package com.hellotong.dijkstra;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法求最短路径
 *
 * @author hellotong
 * @date 2020-09-12 18:35
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertexs.length][vertexs.length];
        // 表示两个顶点之间不可以连接
        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        
        Graph graph = new Graph(vertexs, matrix);
        graph.showGraph();
        
        graph.dijkstra(6);
        // 显示出发顶点到各顶点的最短距离
        graph.showDijkstra();
    }
}

class Graph {
    private char[] vertexs;
    private int[][] matrix;
    private VisitedVertex vv;

    public Graph(char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 迪杰斯特拉算法
     * @param index 出发顶点的下标
     */
    public void dijkstra(int index) {
        vv = new VisitedVertex(vertexs.length, 6);
        // 更新通过 index 顶点到周围顶点之间的距离
        update(index);

        // 因为出发顶点已访问，所以下标从 1 开始，保证遍历访问所有顶点
        for (int i = 1; i < vertexs.length; i++) {
            index = vv.updateArr();
            update(index);
        }
    }

    /**
     * 更新通过 index 顶点到周围顶点之间的距离
     * @param index 经过的顶点下标 index
     */
    public void update(int index) {
        int len = 0;
        for (int i = 0; i < matrix[index].length; i++) {
            // len = 出发顶点到 index 顶点的距离 + index 顶点到 i 顶点的距离
            len = vv.getDis(index) + matrix[index][i];
            // 如果 index 顶点不在已访问顶点中，且经过 index 顶点到顶点 i 的距离比原来的距离小
            if (!vv.in(i) && len < vv.getDis(i)) {
                // 更新顶点 i 的前驱顶点为 index
                vv.updatePre(i, index);
                // 更新出发顶点到顶点 i 的距离为 len
                vv.updateDis(i, len);
            }
        }
    }

    /**
     * 显示
     */
    public void showDijkstra() {
        vv.show();
    }
}

/**
 * 已访问的顶点集合
 */
class VisitedVertex {
    /**
     * 记录顶点是否被访问过，1 表示已访问，0 表示未访问
     */
    int[] already_arr;

    /**
     * 记录各个顶点的前驱顶点的下标
     */
    int[] pre_visited;

    /**
     * 记录出发顶点到各个顶点的距离
     */
    int[] dis;

    /**
     * 构造器
     * @param length 顶点的个数
     * @param index 出发顶点下标
     */
    public VisitedVertex(int length, int index) {
        already_arr = new int[length];
        pre_visited = new int[length];
        dis = new int[length];
        
        // 初始化 dis
        Arrays.fill(dis, 65535);
        // 设置 index 顶点已访问
        already_arr[index] = 1;
        // 出发顶点到自己的距离为 0
        dis[index] = 0;
    }

    /**
     * 判断顶点下标为 index 的顶点是否在已访问数组 already_arr 中 
     * @param index 顶点的下标
     * @return 如果在返回 true，否则 false
     */
    public boolean in(int index) {
        // 1 表示已访问，0 表示未访问
        return already_arr[index] == 1;
    }

    /**
     * 更新出发顶点到顶点下标为 index 的顶点的距离
     * @param index 顶点下标
     * @param len 距离
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新顶点下标为 pre 的顶点的前驱顶点下标为 index
     * @param pre 要更改的顶点
     * @param index 要修改成的顶点下标
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到 index 顶点的距离
     * @param index 目标顶点下标
     * @return 距离
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问顶点
     * @return 新的访问顶点的下标
     */
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            // 如果顶点 i 未访问过，求得出发顶点到所有顶点中的最短距离的顶点下标，即为新的访问顶点
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                // 将新的访问顶点设置为 i
                index = i;
            }
        }
        
        // 设置新的访问顶点为已访问
        already_arr[index] = 1;
        return index;
    }

    /**
     * 显示已访问顶点中的三个数组的情况
     */
    public void show() {
        // 输出 already_arr
        System.out.println(Arrays.toString(already_arr));
        // 输出 pre_visited
        System.out.println(Arrays.toString(pre_visited));
        // 输出 dis
        System.out.println(Arrays.toString(dis));
    }
}