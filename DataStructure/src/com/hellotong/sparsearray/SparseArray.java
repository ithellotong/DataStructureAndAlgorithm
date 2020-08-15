package com.hellotong.sparsearray;

/**
 * 稀疏数组
 * 二维数组 转 稀疏数组的思路
 * 1. 遍历原始的二维数组，得到有效数据的个数 sum
 * 2. 根据 sum 就可以创建 稀疏数组 sparseArr int[sum + 1] [3]
 * 3. 将二维数组的有效数据数据存入到稀疏数组
 *
 * 稀疏数组 转 原始的二维数组的思路
 * 1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的  chessArr2 = int[11][11]
 * 2. 在读取稀疏数组后几行的数据，并赋给原始的二维数组即可.
 *
 * @author hellotong
 * @date 2020-08-11 11:45
 */
public class SparseArray {

    public static void main(String[] args) {
        // 创建一个 11 * 11 的二维数组来表示棋盘
        // 0 表示没有当前位置没有棋子，1 表示黑子，2 表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        // 遍历二维数组
        System.out.println("原始的二维数组为：");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }
        
        /*
         二维数组 转 稀疏数组
          */
        // 获取二位数组中有效元素（即非 0 元素）的个数
        int count = 0;
        for (int[] row : chessArr1) {
            for (int data : row) {
                if (data != 0) {
                    count++;
                }
            }
        }

        // 创建稀疏数组
        int[][] sparseArr = new int[count + 1][3];
        // 用于记录第几个非 0 数据，为稀疏数组初始化数据时使用
        int row = 0;
        // 初始化稀疏数组第一行数据
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = count;

        // 初始化稀疏数组其他行数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    row++;
                    sparseArr[row][0] = i;
                    sparseArr[row][1] = j;
                    sparseArr[row][2] = chessArr1[i][j];
                }
            }
        }

        // 遍历稀疏数组
        System.out.println("稀疏数组为：");
        for (int i = 0; i < sparseArr.length; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(sparseArr[i][j] + "\t");
            }
            System.out.println();
        }
        
        /*
        将稀疏数组恢复为原始的二维数组
         */
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

        // count + 1 <-> sparseArr.length
        for (int i = 1; i < count + 1; i++) { 
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println("恢复出来的原始二维数组为：");
        for (int i = 0; i < sparseArr[0][0]; i++) {
            for (int j = 0; j < sparseArr[0][1]; j++) {
                System.out.print(chessArr2[i][j] + "\t");
            }
            System.out.println();
        }

    }

}
