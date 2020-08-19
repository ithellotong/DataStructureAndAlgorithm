package com.hellotong.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中缀表达式转后缀表达式
 * 主要思路：
 * 1) 初始化两个栈：运算符栈 s1 和储存中间结果的栈 s2；
 * 2) 从左至右扫描中缀表达式；
 * 3) 遇到操作数时，将其压 s2；
 * 4) 遇到运算符时，比较其与 s1 栈顶运算符的优先级：
 *  1.如果 s1 为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
 *  2.否则，若优先级比栈顶运算符的高，也将运算符压入 s1；
 *  3.否则，将 s1 栈顶的运算符弹出并压入到 s2 中，再次转到(4-1)与 s1 中新的栈顶运算符相比较；
 * 5) 遇到括号时：
 *  1. 如果是左括号“(”，则直接压入 s1
 *  2. 如果是右括号“)”，则依次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，此时将这一对括号丢弃
 * 6) 重复步骤 2 至 5，直到表达式的最右边
 * 7) 将 s1 中剩余的运算符依次弹出并压入 s2
 * 8) 依次弹出 s2 中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
 *
 * @author hellotong
 * @date 2020-08-19 13:08
 */
public class InfixToSuffixExpressionDemo {
    public static void main(String[] args) {
        /*
        完成中缀表达式转成后缀表达式的功能
        思路：
        1. 因为直接操作 String 不方便，所以先将中缀表达式的数据拆分后装入到一个 ArrayList 中
        2. 调用一个方法返回一个 ArrayList，然后遍历 List 进行操作
         */
        // 定义一个中缀表达式
        String infixExpression = "1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(infixExpression);
        System.out.println("list = " + list);
        System.out.println(infixExpression + " 的后缀表达式为：" + infixToSuffixExpression(list));
    }

    /**
     * 中缀表达式转后缀表达式
     *
     * @param list 由中缀表达式各元素构成的 List
     * @return 后缀表达式
     */
    public static String infixToSuffixExpression(List<String> list) {
        // 初始化两个栈：运算符栈 s1 和储存中间结果的栈 s2
        // s1
        Stack<String> operStack = new Stack<>();
        // s2
        Stack<String> tempResultStack = new Stack<>();

        // 定义一个 StringBuffer 用于存储最终的后缀表达式结果
        StringBuilder stringBuilder = new StringBuilder();

        // 从左至右扫描中缀表达式
        for (String s : list) {
            // 遇到运算符时，比较其与 s1 栈顶运算符的优先级
            if (isOperate(s)) {
                while (true) {
                    // 1.如果 s1 为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
                    if (operStack.isEmpty() || "(".equals(operStack.peek())) {
                        operStack.push(s);
                        break;
                    } else if (priority(s) > priority(operStack.peek())) {
                        // 2.否则，若优先级比栈顶运算符的高，也将运算符压入 s1；
                        operStack.push(s);
                        break;
                    } else {
                        // 3.否则，将 s1 栈顶的运算符弹出并压入到 s2 中，再次转到(4-1)与 s1 中新的栈顶运算符相比较；
                        tempResultStack.push(operStack.pop());
                    }
                }
            } else if (isBracket(s)) {
                // 遇到括号时：
                // 1. 如果是左括号“(”，则直接压入 s1
                if ("(".equals(s)) {
                    operStack.push(s);
                }
                // 2. 如果是右括号“)”，则依次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，此时将这一对括号丢弃
                if (")".equals(s)) {
                    while (!"(".equals(operStack.peek())) {
                        tempResultStack.push(operStack.pop());
                    }
                    // 丢弃左括号
                    operStack.pop();
                    // 右括号不做处理即丢弃
                }

            } else {
                // 遇到操作数时，将其压 s2
                tempResultStack.push(s);
            }
        }

        // 遍历结束后，将 s1 中剩余的运算符依次弹出并压入 s2
        while (!operStack.isEmpty()) {
            tempResultStack.push(operStack.pop());
        }

        // 依次弹出 s2 中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
        while (!tempResultStack.isEmpty()) {
            stringBuilder.append(tempResultStack.pop());
        }

        return stringBuilder.reverse().toString();
    }

    /**
     * 获取一个字符串（符号：数字、操作符、括号）的优先级
     *
     * @param s 传入的字符串
     * @return 优先级
     */
    public static int priority(String s) {
        if (s == "*" || s == "/") {
            return 2;
        } else if (s == "+" || s == "-") {
            return 1;
        } else if (s == "(" || s == ")") {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 是否为括号
     *
     * @param s 传入一个字符串
     * @return true or false
     */
    public static boolean isBracket(String s) {
        char ch = s.charAt(0);
        return ch == '(' || ch == ')';
    }

    /**
     * 是否为操作符
     *
     * @param s 传入一个字符串
     * @return true or false
     */
    public static boolean isOperate(String s) {
        char ch = s.charAt(0);
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    /**
     * 传入一个中缀表达式，拆分中缀表达式后将数据装入到 List 中，并返回
     *
     * @param infixExpression 传入的中缀表达式
     * @return List
     */
    public static List<String> toInfixExpressionList(String infixExpression) {
        // 创建一个 ArrayList
        ArrayList<String> list = new ArrayList<>();
        // 定义一个索引用于扫描中缀表达式字符串
        int index = 0;
        // 存储字符串的每个字符
        char ch;
        String joinStr = "";

        // index 没有扫猫到最后方可进入循环
        while (index < infixExpression.length()) {
            ch = infixExpression.charAt(index);
            // 如果是运算符，直接添加到 List 中
            // 数字范围是 '0'[48]-'9'[57]，不是数字就是运算符，
            if (ch < 48 || ch > 57) {
                list.add(ch + "");
            } else {
                // 如果是是数字，还需要考虑多位数的情况
                // 每次进来先把 joinStr 置空
                joinStr = "";

                joinStr += ch;
                if ((index + 1) < infixExpression.length() && (ch = infixExpression.charAt(index + 1)) >= 48 &&
                        (ch = infixExpression.charAt(index + 1)) <= 57) {
                    joinStr += ch;
                    index++;
                }
                list.add(joinStr);
            }
            index++;
        }

        return list;
    }
    
}
