package com.hellotong.recursion;

/**
 * 递归
 * 1. 递归的调用规则
 * （1）当程序执行一个方法时，就会在栈中开辟一个独立的空间。
 * （2）每个空间的数据属于局部变量，是独立的。
 * （3）如果方法中使用的是引用类型的遍历（如数组），就会共享该引用类型的数据
 * （4）递归必须向退出递归的条件逼近，否则就是无限递归，出现 StackOverflowError
 * （5）当一个方法执行完毕，或者遇到 return，就会返回，遵守谁调用，就将结果返回给谁，同时
 *     当方法执行完毕或者返回时，该方法也就执行完毕。
 * 2. 举例理解递归机制
 * （1）打印问题
 * （2）阶乘问题
 * 
 * @author hellotong
 * @date 2020-08-20 9:38
 */
public class RecursionTest {
    public static void main(String[] args) {
        // 打印
        print(4);
        
        // 阶乘
        System.out.println(factorial(4));
    }

    /**
     * 打印问题
     * @param n num
     */
    public static void print(int n) {
        if (n > 2) {
            print(n - 1);
        }
        System.out.println("n = " + n);
    }
    
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }
}

