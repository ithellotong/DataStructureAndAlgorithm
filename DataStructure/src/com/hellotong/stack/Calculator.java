package com.hellotong.stack;

/**
 * 使用栈完成表达式的计算
 * 思路：
 * 1. 通过一个index值(索引) ,来遍历我们的表达式
 * 2. 如果我们发现是一个数字,就直接入数栈
 * 3. 如果发现扫描到是一个符号,就分如下情况
 *  3.1 如果发现当前的符号栈为空,就直接入栈
 *  3.2 如果符号栈有操作符,就进行比较,如果当前的操作符的优先级小于或者等于栈顶
        的操作符,就需要从数栈中pop出两个数,在从符号栈中pop出一个符号,进行运算,
        将得到结果,入数栈,然后将当前的操作符入符号栈,如果当前的操作符的优先级大
        于栈顶的操作符,就直接入符号栈.
 * 4. 当表达式扫描完毕,就顺序的从数栈和符号栈中pop出相应的数和符号,并运行.
 * 5. 最后在数栈只有一个数字,就是表达式的结果
 * 验证: 3+2*6-2=13
 * 
 * 存在的问题：只能计算个位数，不能计算多位数
 * 解决方法：只要在入栈的时候加以处理即可
 * 1. 当处理多位数时，不能发现是一个数字就入栈，因为它可能是多位数
 * 2. 在处理数字时，需要在 index 的基础上向后再看一位，如果是符号才入栈
 * 3. 如果是数字则进行字符拼接，再转换为数字再入栈
 * 
 * @author hellotong
 * @date 2020-08-18 17:34
 */
public class Calculator {
    public static void main(String[] args) {
        // 要计算的表达式 = 13
        String expression = "30+2*6-2"; 
        // String expression = "9-7*1*1+2"; // TODO:有 BUG
        // 定义两个栈，一个数栈和一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        // 定义用于遍历表达式的索引
        int index = 0;
        
        /*
         定义相关变量
          */
        // 用于存放两个操作数、操作符和中间结果
        int num1;
        int num2;
        int oper;
        int res;
        // 通过遍历存放表达式的每个字符
        char ch;
        
        // 循环遍历
        while (index < expression.length()) {
            // 获取表达式的每一个字符
            ch = expression.charAt(index);
            
            // 判断 ch 是数字还是操作符
            // 使用 numStack 或 operStack 调用方法都行，只是执行方法，没有实际意义
            if (operStack.isOperate(ch)) {
                // 如果是操作符
                // 如果发现当前的符号栈为空,就直接入栈
                if (operStack.isEmpty()) {
                    operStack.push(ch);
                } else {
                // 如果符号栈有操作符,就进行比较
                    /*
                    如果当前的操作符的优先级小于或者等于栈顶的操作符,就需要从数栈中pop出两个数,
                    在从符号栈中pop出一个符号,进行运算,将得到结果,入数栈,然后将当前的操作符入符号栈
                     */
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = operStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                    // 如果当前的操作符的优先级大于栈顶的操作符,就直接入符号栈.
                        operStack.push(ch);
                    }
                }
                
            } else {
                /*
                 * 个位数处理 
                 */
                // 否则就是数字,如果我们发现是一个数字,就直接入数栈
                // 因为 ch 为字符，所以要存入数字需要减去字符 '0' 的 ASCII 码值
                // numStack.push(ch - 48);
                
                /*
                多位数处理
                 */
                // 定义一个字符串用于多个数字字符拼接
                String joinNum = "";
                // 如果 index 后一为是操作符，则 index 位置上的数字直接入栈
                // index + 1 需要做范围检查
                // 如果 index 指向最后一个数字，则直接入栈
                if ((index + 1) == expression.length() ) {
                    numStack.push(ch - 48);
                } else {
                    // index 指向的不是最后一个数字
                    // 如果向后看一位是操作符
                    if (operStack.isOperate(expression.charAt(index + 1))) {
                        numStack.push(ch - 48);
                    } else {
                        // 如果 index 后一位是数字，则进行字符拼接再转换为数字再入栈
                        // 数字字符拼接
                        joinNum += ch;
                        joinNum += expression.charAt(index + 1);
                        // 转换为数字后入栈
                        numStack.push(Integer.parseInt(joinNum));
                        // 重要!!! 索引后移一位，因为 index+1 位置上的字符已经与 index 位置上的字符拼接入栈了
                        index++;
                    }
                }
            }
                
            // 每个字符处理完之后，索引后移
            index++;
        }
        
        // 上一个 while 循环结束时，表达式解析就完成了，下面开始计算表达式结果
        // 循环结束条件是：符号栈为空，代表所有运算完成，最后在数栈只有一个数字,就是表达式的结果
        while (!operStack.isEmpty()) {
            // 顺序的从数栈和符号栈中pop出相应的数和符号,并运行.
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = operStack.cal(num1, num2, oper);
            numStack.push(res);
        }

        // 上一个 while 循环结束时，最后在数栈只有一个数字,就是表达式的结果
        System.out.println(numStack.pop());
    }
}

/**
 * 定义一个 ArrayStack，表示栈
 */
class ArrayStack2 {
    /**
     * 栈的容量
     */
    public int maxSize;

    /**
     * 用于存放栈的数据
     */
    public int[] stack;

    /**
     * 栈顶，初始值为 -1，代表栈中没有数据
     */
    public int top = -1;

    /**
     * 构造器，初始化栈的属性
     * @param maxSize 栈的容量
     */
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 判断栈是否已满
     * @return true or false
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈是否为空
     * @return true or false
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     * @param value 入栈元素
     */
    public void push(int value) {
        // 先判断栈是否已满
        if (isFull()) {
            System.out.println("栈满，无法入栈");
            return;
        }

        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     * @return 栈顶元素
     */
    public int pop() {
        // 先判断栈是否为空
        if (isEmpty()) {
            // 抛出异常，结束程序执行，无需返回值，无需 return
            throw new RuntimeException("栈空，无法出栈");
        }

        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 返回栈顶元素，非出栈
     * @return 栈顶元素
     */
    public int peek() {
        return stack[top];
    }

    /**
     * 遍历栈
     */
    public void list() {
        // 先判断栈是否为空
        if (isEmpty()) {
            System.out.println("栈空，无法遍历");
            return;
        }

        // 从栈顶开始往下遍历
        for (int i = top; i >= 0; i--) {
            System.out.println("stack[" + i + "] = " + stack[i]);
        }
    }
    
    /*
    新增几个方法操作
     */

    /**
     * 判断传入的是否为操作符
     * @param ch 字符
     * @return true or false
     */
    public boolean isOperate(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    /**
     * 获得操作符的优先级，优先级使用整数来表示
     * @param ch 传入的字符
     * @return 字符的优先级
     */
    public int priority(int ch) {
        if (ch == '*' || ch == '/') {
            // 使用 1 来表示 * 和 / 的优先级
            return 1;
        } else if (ch == '+' || ch == '-') {
            // 使用 0 来表示 + 和 - 的优先级
            return 0;
        } else {
            // 不是操作符，优先级统一使用 -1
            return -1;
        }
    }

    /**
     * 计算方法，两个数和一个操作符
     * @param num1 数 1
     * @param num2 数 2
     * @param oper 操作符，传入的是操作符的 ASCII 码，整型值
     * @return 计算结果
     */
    public int cal(int num1, int num2, int oper) {
        // 存放计算结果
        int res = 0;
        switch (oper) {
            // 操作符对应的也是 ASCII 码，整型值
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                // TODO: 除法这里如果不能整除就有问题，先不考虑
                res = num2 / num1; 
                break;
            default:
                break;  
        }
        
        return res;
    }
    
}
