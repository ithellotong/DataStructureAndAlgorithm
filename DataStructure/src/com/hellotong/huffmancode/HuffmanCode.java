package com.hellotong.huffmancode;

import java.util.*;

/**
 * 赫夫曼编码、字符串的压缩和解压
 * 
 * @author hellotong
 * @date 2020-08-30 11:55
 */
public class HuffmanCode {
    public static void main(String[] args) {
        // 要压缩的内容
        String content = "i like like like java do you like a java";
        // 字符串转字节数组
        byte[] contentBytes = content.getBytes();

        // 压缩（编码）
        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println(Arrays.toString(huffmanCodeBytes));
        
        // 解压（解码）
        byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
        // i like like like java do you like a java
        System.out.println("原来的字符串：" + new String(sourceBytes));

        /* 分步处理
        // 通过遍历字节数组，获得每个字符以及其出现的次数
        // 并将字符和其出现的次数封装成一个 Node
        // 将所有的 Node 加入到一个 ArrayList 集合中，方便操作
        List<Node> nodesList = getNodesList(contentBytes);
        System.out.println(nodesList);

        // 通过一个 ArrayList 集合，创建出一棵赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodesList);
        // 前序遍历赫夫曼树
        preOrderList(huffmanTreeRoot);

        // 获取赫夫曼编码
        // 方式一：getHuffmanCodes(huffmanTreeRoot, "", stringBuilder);
        // 方式二
        Map<Byte, String> huffmanCodes = getHuffmanCodes(huffmanTreeRoot);
        System.out.println("赫夫曼编码：" + huffmanCodes);

        byte[] zip = zip(contentBytes, huffmanCodes);
        System.out.println(Arrays.toString(zip));
        */
    }

    // 解压（解码）
    // 思路：
    // 1. 将 huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    // 先转成 赫夫曼编码对应的二进制的字符串 "1010100010111..."
    // 2. 赫夫曼编码对应的二进制的字符串 "1010100010111..." =》 对照 赫夫曼编码
    // =》 "i like like like java do you like a java"

    /**
     * 将一个通过赫夫曼编码后的字节数组还原出原来的字符串字节数组
     * @param huffmanCodes 赫夫曼编码表
     * @param huffmanBytes 经过赫夫曼编码后的字节数组
     * @return 原来的字符串字节数组
     */
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        // 1. 将 huffmanBytes 转成对应的二进制字符串
        // 创建一个 StringBuilder 用于拼接二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            // 最后一个字节不需要补高位，flag = true，!flag = false
            boolean flag = (i == huffmanBytes.length - 1);
            String byteStr = byteToBitString(!flag, huffmanBytes[i]);
            // 每处理一个字节拼接一次
            stringBuilder.append(byteStr);
        }
        // 测试二进制字符串是否正确
        // System.out.println(stringBuilder.toString());
        
        // 2. 现在要将赫夫曼编码还原成字节，所以需要将赫夫曼编码表的 key 和 value 互换
        // 创建一个新的 Map，用于存储原赫夫曼编码表 key 和 value 互换后的结果
        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        // 测试赫夫曼编码表反转是否成功
        System.out.println(map);
        
        // 3. 遍历二进制字符串，开始还原原来的字符串，放到字节数组中
        // 创建一个 ArryaList 集合存放还原后的每个字节
        ArrayList<Byte> list = new ArrayList<>();
        
        for (int i = 0; i < stringBuilder.length(); ) {
            // 用于匹配编码的游标
            int count = 1;
            // 标志是否进入循环
            boolean flag = true;
            // 临时变量
            Byte b = null;
            
            // flag == true 进入循环
            while (flag) {
                String substring = stringBuilder.substring(i, i + count);
                b = map.get(substring);
                // substring 在反转赫夫曼编码表中没匹配到
                if (b == null) {
                    count++;
                } else {
                    // substring 在反转赫夫曼编码表中匹配到了
                    // 加入到集合
                    list.add(b);
                    // 退出循环
                    flag = false;
                }
            }
            // 跳转到下一个位置开始匹配
            i += count;
        }
        // System.out.println(list);

        // 当 for 循环结束后，我们 list 中就存放了所有的字符 "i like like like java do you like a java"
        // 把 list 中的数据放入到 byte[] 并返回
        byte[] sourceBytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            sourceBytes[i] = list.get(i);
        }
        
        return sourceBytes;
    }

    /**
     * 将一个字节 byte 转成对应的二进制字符串
     * 
     * @param flag 标志是否需要补高位，如果 true 需要补高位，如果是 false 不需要补，如果是最后一个字节
     *             无需补高位，直接返回
     * @param b 传入的字节
     * @return 该字节对应的二进制字符串
     */
    public static String byteToBitString(boolean flag, byte b) {
        // 先把字节存起来，转成 int
        int temp = b;

        // 如果是正数我们还存在补高位
        if (flag) {
            //  //按位与 256，1 0000 0000 | 0000 0001 => 1 0000 0001
            temp |= 256;
        }

        // 返回的是 temp 对应的二进制的补码
        String str = Integer.toBinaryString(temp);
        
        if (flag) {
            // 取低位的 8 位，因为 int 是 16 位，而 int 是 16 位
            return str.substring(str.length() - 8);
        } else {
            // 如果 flag 不需要补高位直接返回
            return str;
        }
        
    }

    /**
     * 将为了得到通过赫夫曼编码压缩过后的字节数组之前的步骤，封装成一个方法，方便调用
     *
     * @param bytes 原始字符串对应的字节数组
     * @return 通过赫夫曼编码压缩过后的字节数组
     */
    public static byte[] huffmanZip(byte[] bytes) {
        // 通过遍历字节数组，获得每个字符以及其出现的次数
        // 并将字符和其出现的次数封装成一个 Node
        // 将所有的 Node 加入到一个 ArrayList 集合中，方便操作
        List<Node> nodesList = getNodesList(bytes);
        // 通过一个 ArrayList 集合，创建出一棵赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodesList);
        // 获取赫夫曼编码
        // 方式一：getHuffmanCodes(huffmanTreeRoot, "", stringBuilder);
        // 方式二
        Map<Byte, String> huffmanCodes = getHuffmanCodes(huffmanTreeRoot);
        byte[] zipBytes = zip(bytes, huffmanCodes);
        return zipBytes;
    }

    /**
     * 压缩（编码）
     * 将原始字符串对应的字节数组 按照赫夫曼编码 得到原始字符串的赫夫曼编码字符串
     * 再处理赫夫曼编码字符串，将 8 位封装成一个字节，放到新的字节数组中，处理结束后返回
     *
     * @param bytes        原始字符串对应的字节数组
     * @param huffmanCodes 赫夫曼编码表
     * @return 按照赫夫曼编码处理过后的字节数组
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        // 1. 按照赫夫曼编码 得到原始字符串的赫夫曼编码字符串
        // 创建一个 StringBuilder，用于拼接每个字节对应的赫夫曼编码
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        // 2. 求出需要创建的字节数组的长度
        int length = 0;
        // 如果字符串的长度可以整除 8，长度就是 stringBuilder.length() / 8
        // 否则 stringBuilder.length() / 8 + 1
        // 也可以一句话：length = (stringBuilder + 7) / 8 与下面处理等价
        if (stringBuilder.length() % 8 == 0) {
            length = stringBuilder.length() / 8;
        } else {
            length = stringBuilder.length() / 8 + 1;
        }
        // 创建新的字节数组
        byte[] huffmanCodeBytes = new byte[length];
        // 新的字节数组的索引
        int index = 0;
        // 用于临时存储截取的 8 位字符串
        String substring = "";

        // 3. 开始处理赫夫曼编码字符串，将 8 位封装成一个字节，最后不够 8 位的也封装成一个字节，放到新的字节数组中
        // 因为要 8 位封装成一个字节，所以步长为 8
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            // 如果最后一个字节不够 8 位
            if (i + 8 > stringBuilder.length()) {
                // 从 i 开始截取到最后
                substring = stringBuilder.substring(i);
            } else {
                substring = stringBuilder.substring(i, i + 8);
            }
            // 强转成字节
            huffmanCodeBytes[index] = (byte) Integer.parseInt(substring, 2);
            index++;
        }

        // 返回新的字节数组
        return huffmanCodeBytes;
    }

    /**
     * 为了操作方便，重载 getHuffmanCodes 方法，传入一个赫夫曼树的根节点，返回赫夫曼编码
     *
     * @param root 赫夫曼树的根节点
     * @return 赫夫曼树对应的赫夫曼编码
     */
    public static Map<Byte, String> getHuffmanCodes(Node root) {
        if (root != null) {
            // 处理 root 的左子树
            getHuffmanCodes(root.left, "0", stringBuilder);
            // 处理 root 的右子树
            getHuffmanCodes(root.right, "1", stringBuilder);

            return huffmanCodes;
        } else {
            return null;
        }
    }

    /**
     * 用于存储赫夫曼编码表，形式如：32(' ')->10,97('a')->100 等等
     */
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    /**
     * 创建一个 StringBuilder，用于传入 getHuffmanCodes 方法，没有其他作用
     * 用于拼接路径的为 getHuffmanCodes 方法内部的 StringBuilder
     */
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 将传入的 node 节点的所有叶子节点的赫夫曼编码得到，并将其放入到 huffmanCodes 集合中
     *
     * @param node          传入的节点
     * @param code          要拼接的路径：左子节点为 0，右子节点为 1
     * @param stringBuilder 用于拼接节点的路径，每次传入会在前一次的 stringBuilder 路径基础上继续拼接
     */
    public static void getHuffmanCodes(Node node, String code, StringBuilder stringBuilder) {
        // 用于拼接每个节点的赫夫曼编码
        // 每递归一次都会创建一个新的栈空间，不同栈空间互相不影响，可以存储每个节点的赫夫曼编码
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        // 拼接路径
        stringBuilder2.append(code);
        // 如果 node == null 不处理
        if (node != null) {
            // 如果 node 为非叶子节点
            if (node.data == null) {
                // 左递归
                getHuffmanCodes(node.left, "0", stringBuilder2);
                getHuffmanCodes(node.right, "1", stringBuilder2);
            } else {
                // 如果 node 为叶子节点
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    /**
     * 通过一个 ArrayList 集合，创建出一棵赫夫曼树
     *
     * @param nodesList 节点集合
     * @return 赫夫曼树的根节点
     */
    public static Node createHuffmanTree(List<Node> nodesList) {
        while (nodesList.size() > 1) {
            // 1. 排序
            Collections.sort(nodesList);
            // 2. 取出并从集合中移除两个最小的节点（二叉树）
            Node leftNode = nodesList.remove(0);
            Node rightNode = nodesList.remove(0);
            // 3. 创建一棵新的二叉树，父节点 data = null，weight 为取出的两个节点的和
            Node parentNode = new Node(null, leftNode.weight + rightNode.weight);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            // 4. 将父节点添加到集合中
            nodesList.add(parentNode);
        }

        // 此时集合中只有一个节点，即赫夫曼树的根节点
        return nodesList.get(0);
    }


    /**
     * 通过一个字节数组得到一个装有 Node 的 ArrayList 集合
     *
     * @param bytes 字节数组
     * @return 节点集合 nodesList
     */
    public static List<Node> getNodesList(byte[] bytes) {
        // 创建一个 ArrayList 集合，用于存储每个 Node 节点
        ArrayList<Node> nodesList = new ArrayList<>();
        // 创建一个 Map，用于存储每个字符以及其出现的次数
        HashMap<Byte, Integer> map = new HashMap<>();
        // 通过遍历字节数组，获得每个字符以及其出现的次数，使用 Map 存储
        for (byte b : bytes) {
            // 先获取 key：b 在 map 中出现的次数
            Integer count = map.get(b);
            // 如果 count 为空，说明 map 中还没有 b，是第一次存
            if (count == null) {
                map.put(b, 1);
            } else {
                // 如果 count 不为空，说明 map 中已经存在 b，不是第一次存
                map.put(b, count + 1);
            }
        }

        // 将 map 的每个键值对封装成一个 Node，并将其加入到集合 nodesList 中
        // 遍历 map
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodesList.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodesList;
    }


    /**
     * 前序遍历
     *
     * @param root 赫夫曼树的根节点
     */
    public static void preOrderList(Node root) {
        if (root != null) {
            root.preOrderList();
        } else {
            System.out.println("树为空，无法遍历");
        }
    }
}

/**
 * 树节点
 */
class Node implements Comparable<Node> {
    /**
     * 存放数据（字符）
     */
    public Byte data;

    /**
     * 权值：存放字符出现的次数
     */
    public int weight;

    /**
     * 指向左子节点
     */
    public Node left;

    /**
     * 指向右子节点
     */
    public Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    /**
     * 前序遍历
     */
    public void preOrderList() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrderList();
        }

        if (this.right != null) {
            this.right.preOrderList();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
