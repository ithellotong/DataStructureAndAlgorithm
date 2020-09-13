package com.hellotong.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 马踏棋盘算法
 * 
 * @author hellotong
 * @date 2020-09-13 17:33
 */
public class HorseChessboard {
    /**
     * 棋盘的列数
     */
    private static int X;

    /**
     * 棋盘的行数
     */
    private static int Y;

    /**
     * 创建一个数组，标记棋盘的各个位置是否被访问过
     */
    private static boolean visited[];

    /**
     * 标记是否棋盘中的所有位置都被访问了，如果 true 表示成功
     */
    private static boolean finished;
    
    public static void main(String[] args) {
        X = 8;
        Y = 8;
        // 初始化棋盘
        int[][] chessboard = new int[X][Y];
        // 初始化 visited
        visited = new boolean[X * Y];
        // 设置马儿初始的位置，假设从 (0, 0) 点开始走
        int row = 0;
        int column = 0;
        // 计算开始时间
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row, column, 1);
        long end = System.currentTimeMillis();
        System.out.println("运行所需时间为：" + (end - start));
        
        // 打印棋盘
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 骑士周游问题算法
     * @param chessboard 棋盘
     * @param row 马儿所在当前位置的行坐标，从 0 开始
     * @param column 马儿所在当前位置的纵坐标，从 0 开始
     * @param step 马儿走的是第几步，初始值从 1 开始
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        // 棋盘位置上存放的是第几步
        chessboard[row][column] = step;
        // 标记该位置已被访问，row * X + column 即为马儿当前位置
        visited[row * X + column] = true;
        // 获取当前位置可以走的下一个位置的集合
        ArrayList<Point> points = next(new Point(column, row));
        
        // 使用贪心算法对马儿走的策略进行优化，优化过后运行时间会变得很短
        // 优化的规则就是：选择当前位置的下一个位置的下一个位置的选择尽可能少，来减少程序回溯的次数，从而提高运行效率
        sort(points);
        
        // 遍历 points
        // points 不为空一直遍历
        while (!points.isEmpty()) {
            // 从集合中取出第一个可以走的位置
            Point p = points.remove(0);

            // 如果 p 没有被访问过
            // p.y 表示行，p.x 表示列，找到 p 在 visited 数组中的位置
            if (!visited[p.y * X + p.x]) {
                // 递归
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }
        
        // 表示马儿没有完成任务，即没有踏完棋盘中的所有位置
        // 说明：step < X * Y 成立，有两种情况
        // 1. 棋盘到目前位置，仍然没有走完
        // 2. 棋盘走完了，处于回溯过程中
        if (step < X * Y && !finished) {
            // 说明当时位置 row column 走不通
            // 回溯
            visited[row * X + column] = false;
            // 将棋盘当前位置设置为 0
            chessboard[row][column] = 0;
        } else {
            // 完成任务了，所有位置都走完了
            finished = true;
        }

    }

    /**
     * 根据当前位置 curPoint，计算马儿可以走哪些位置，并将可以走的位置加入到一个 ArrayList 集合中
     * 最多可以走 8 个位置
     * 
     * @param curPoint 马儿当前所在的位置
     * @return 存放当前位置可以走的所有位置的集合
     */
    public static ArrayList<Point> next(Point curPoint) {
        // 创建一个集合
        ArrayList<Point> points = new ArrayList<>();
        
        // 创建一个坐标对象
        Point p = new Point();
        
        // 判断马儿是否可以走 5 这个位置
        if ((p.x = curPoint.x - 2) >= 0 && (p.y = curPoint.y - 1) >= 0) {
            points.add(new Point(p));
        }

        // 判断马儿是否可以走 6 这个位置
        if ((p.x = curPoint.x - 1) >= 0 && (p.y = curPoint.y - 2) >= 0) {
            points.add(new Point(p));
        }

        // 判断马儿是否可以走 7 这个位置
        if ((p.x = curPoint.x + 1) < X && (p.y = curPoint.y - 2) >= 0) {
            points.add(new Point(p));
        }

        // 判断马儿是否可以走 0 这个位置
        if ((p.x = curPoint.x + 2) < X && (p.y = curPoint.y - 1) >= 0) {
            points.add(new Point(p));
        }

        // 判断马儿是否可以走 1 这个位置
        if ((p.x = curPoint.x + 2) < X && (p.y = curPoint.y + 1) < Y) {
            points.add(new Point(p));
        }

        // 判断马儿是否可以走 2 这个位置
        if ((p.x = curPoint.x + 1) < X && (p.y = curPoint.y + 2) < Y) {
            points.add(new Point(p));
        }

        // 判断马儿是否可以走 3 这个位置
        if ((p.x = curPoint.x - 1) >= 0 && (p.y = curPoint.y + 2) < Y) {
            points.add(new Point(p));
        }

        // 判断马儿是否可以走 4 这个位置
        if ((p.x = curPoint.x - 2) >= 0 && (p.y = curPoint.y + 1) < Y) {
            points.add(new Point(p));
        }
        
        return points;
    }

    /**
     * 使用贪心算法进行优化
     * 对马儿所在当前位置可以走的下一个位置构成的集合中每一个位置可以走的下一个位置构成的集合的数目
     * 进行非递减排序（允许出现相同的）
     * 
     * @param points 马儿所在当前位置可以走的下一个位置构成的集合
     */
    public static void sort(ArrayList<Point> points) {
        // 定制排序
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                return count1 - count2;
            }
        });
    }
}
