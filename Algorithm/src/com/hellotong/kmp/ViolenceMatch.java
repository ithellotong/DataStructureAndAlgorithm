package com.hellotong.kmp;

/**
 * 字符串匹配之暴力匹配算法
 * 
 * @author hellotong
 * @date 2020-09-04 18:23
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        int index = voilenceMath(str1, str2);
        System.out.println("index = " + index);
    }

    /**
     * 暴力匹配算法
     * @param str1 主串
     * @param str2 子串
     */
    public static int voilenceMath(String str1, String str2) {
        // 先将字符串转为字符数组
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        
        // 初始化两个索引，i 指向主串 s1，j 指向子串 s2
        int i = 0;
        int j = 0;
        
        // 拿到字符串的长度
        int s1Len = s1.length;
        int s2Len = s2.length;
        
        // 保证下标不越界
        while (i < s1Len && j < s2Len) {
            // 如果一个字符匹配成功，下标后移继续匹配
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                // 否则，索引 i 和 j 回溯继续从第一个字符匹配成功后的下一个位置继续匹配
                // 也因此体现出了它效率较低
                i = i - (j - 1);
                j = 0;
            }
        }
        
        // 通过索引 j 来判断 str2 是否在 str1 中，如果存在返回第一个字符匹配成功时的下标，否则返回 -1
        // 如果匹配成功，索引 j 会越界退出 while 循环
        if (j == s2.length) {
            return i - j;
        } else {
            return -1;
        }
    }
}
