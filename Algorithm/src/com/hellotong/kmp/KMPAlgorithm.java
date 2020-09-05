package com.hellotong.kmp;

import java.util.Arrays;

/**
 * 字符串匹配之 KMP 算法
 * 
 * @author hellotong
 * @date 2020-09-05 10:03
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        
        int[] next = kmpNext(str2);
        System.out.println("next = " + Arrays.toString(next));
        int index = kmpSearch(str1, str2, next);
        System.out.println("index = " + index);
    }

    /**
     * KMP 搜索算法
     * @param str1 主串
     * @param str2 子串 / 模式串
     * @param next 部分匹配表
     * @return 如果存在返回下标，否则返回 -1
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            
            // 找到了
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        
        // 没找到
        return -1;
    }

    /**
     * 获取到一个子串（模式串）的部分匹配表，即 next 数组
     * @param dest 模式串
     * @return next 数组
     */
    public static int[] kmpNext(String dest) {
        // 创建一个 next 数组存储部分匹配值
        // 部分匹配值即字符串前缀和后缀的最大公共子串的长度
        int[] next = new int[dest.length()];
        // 如果字符串长度为 1，部分匹配值就是 0
        next[0] = 0;

        // 因为 next[0] 已经有值，所以下标直接从 1 开始
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // 当 dest.charAt(i) != dest.charAt(j) 时，需要从 next[j-1] 获取新的 j
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        
        return next;
    }
}
