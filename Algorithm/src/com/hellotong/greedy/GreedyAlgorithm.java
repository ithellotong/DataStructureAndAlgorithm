package com.hellotong.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 贪心算法
 * 解决电台广播覆盖问题（集合覆盖问题）
 * 
 * @author hellotong
 * @date 2020-09-10 11:12
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        // 创建广播电台和对应覆盖的区域集合 Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        // 创建电台 K1 对应覆盖的区域
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        // 创建电台 K2 对应覆盖的区域
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        // 创建电台 K3 对应覆盖的区域
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        // 创建电台 K4 对应覆盖的区域
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        // 创建电台 K1 对应覆盖的区域
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        
        // 添加到 Map 中
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);
        
        // 创建一个由所有地区构成的集合
        HashSet<String> allAreas = new HashSet<>();
        allAreas.addAll(hashSet1);
        allAreas.addAll(hashSet2);
        allAreas.addAll(hashSet3);
        allAreas.addAll(hashSet4);
        allAreas.addAll(hashSet5);

        // 测试 allAreas
        /*Iterator<String> iterator = allAreas.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }*/
        
        // 创建一个 List 集合，用于存储电台的 Key，即最终结果
        ArrayList<String> selects = new ArrayList<>();
        // 定义 maxKey，用于记录可以覆盖最多地区的电台
        String maxKey = null;
        // 定义 tempSet，用于临时存储当前电台可以覆盖到的区域和需要覆盖到的所有区域的交集
        HashSet<String> tempSet = new HashSet<>();
        // 定义 tempMaxKeySet，用于临时存储 maxKey 对应电台可以覆盖到的区域和需要覆盖到的所有区域的交集
        HashSet<String> tempMaxKeySet = new HashSet<>();

        // broadcasts.size() != 0 表示如果还有区域没有覆盖到，则继续进入循环处理
        while (allAreas.size() != 0) {
            // 遍历 broadcasts
            for (String key : broadcasts.keySet()) {
                tempSet = broadcasts.get(key);
                // 当前电台可以覆盖到的区域和需要覆盖到的所有区域的交集，结果保存到 tempSet
                tempSet.retainAll(allAreas);
                if (maxKey != null) {
                    tempMaxKeySet = broadcasts.get(maxKey);
                    // 取出 maxKey 电台可以覆盖到的区域和需要覆盖到的所有区域的交集，结果保存到 tempSet
                    tempMaxKeySet.retainAll(allAreas);
                }
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > tempMaxKeySet.size())) {
                    maxKey = key;
                }
            }
            
            if (maxKey != null) {
                // 将 maxKey 添加到 List 中
                selects.add(maxKey);
                // 从 allAreas 中移除 maxKey 中已经覆盖到的区域
                allAreas.removeAll(broadcasts.get(maxKey));
                // maxKey 置空，用于下一次遍历
                maxKey = null;
            }
        }

        // [K1,K2,K3,K5]
        System.out.println("得到的选择结果是" + selects);

    }
}
