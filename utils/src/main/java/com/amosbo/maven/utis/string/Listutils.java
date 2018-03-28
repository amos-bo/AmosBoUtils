package com.amosbo.maven.utis.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Amos_bo
 * @package: com.amosbo.maven.utis.String
 * @Created Time: 2018/3/28 13:54
 * @Changed Time: 2018/3/28 13:54
 * @email: 284285624@qq.com
 * @Org: SZKT
 * @version: V1.0
 * @describe: //TODO 添加描述
 */

public class Listutils {

    /**
     * list分段
     *
     * @param list  List
     * @param split int
     * @return List<List>
     */
    public static List<List> getSubGoodList(List list, int split) {
        List<List> lists = new ArrayList<>();
        int size = list.size();
        if (size <= split) {
            lists.add(list);
            return lists;
        }
        int count = list.size() / split;
        int yu = list.size() % split;
        for (int i = 0; i < split; i++) {
            List subList = new ArrayList();
            for (int j = 0; j < count; j++) {
                Object obj = list.get(i + j * split);
                subList.add(obj);
            }
            if (i == split - 1 && yu > 0) {

                List yuList = list.subList(count * (i + 1), count * (i + 1) + yu);

                subList.addAll(yuList);
            }
            lists.add(subList);
        }
        return lists;
    }
}
