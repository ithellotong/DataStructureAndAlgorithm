package com.hellotong.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器
 * 传入一个逆波兰表达式（后缀表达式）计算结果
 * 
 * @author hellotong
 * @date 2020-08-18 20:10
 */
public class PolandNotation {
    public static void main(String[] args) {
        // 给出一个逆波兰表达式，用空格隔开，方便使用 ArrayList 处理
        // String suffixExpression = "3 4 + 5 * 6 -";
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        // 思路分析
        // 1. 创建一个 ArrayList，将 "3 4 + 5 * 6 -" 传入其中
        // 2. 遍历 ArrayList 获得每个字符（字符串），配合栈完成计算
        List<String> list = getListString(suffixExpression);
        System.out.println("list = " + list);
        
        // 调用计算方法获得计算结果
        System.out.println(calculate(list));
    }

    /**
     * 通过遍历传入的 list，计算逆波兰表达式的结果
     * 从左至右扫描表达式,遇到数字时,将数字压入堆栈,遇到运算符时,弹出栈顶的两个
     * 数,用运算符对它们做相应的计算(次顶元素和栈顶元素) ,并将结果入栈;重复上
     * 述过程直到表达式最右端,最后运算得出的值即为表达式的结果
     * @param list 存放的是逆波兰表达式的每个字符（字符串）
     * @return 运算结果
     */
    public static int calculate(List<String> list) {
        // 创建一个栈
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        // 遍历 list
        for (String s : list) {
            // 如果是数字,将数字压入堆栈
            if (s.matches("\\d+")) {
                stack.push(Integer.parseInt(s));
            } else {
            // 遇到运算符时,弹出栈顶的两个数,用运算符对它们做相应的计算(次顶元素和栈顶元素) ,并将结果入栈
                int num1 = stack.pop();
                int num2 = stack.pop();
                if (s.equals("+")) {
                    res = num1 + num2;
                } else if (s.equals("-")) {
                    res = num2 - num1;
                } else if (s.equals("*")) {
                    res = num1 * num2;
                } else if (s.equals("/")) {
                    res = num2 / num1;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                
                stack.push(res);
            }
        }
        
        return res;
    }

    /**
     * 将一个逆波兰表达式，依次将数据和运算符放入到 ArrayList 中
     * @param suffixExpression 逆波兰表达式
     * @return 由逆波兰表达式的每个字符（字符串）构成的 List
     */
    public static List<String> getListString(String suffixExpression) {
        String[] strings = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String string : strings) {
            list.add(string);
        }
        return list;
    }
}