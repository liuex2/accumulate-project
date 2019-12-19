package com.cif.accumulate;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author liuxincai
 * <p>
 * 算法测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculateTest {

    public List<Integer> list() {
        int element = 0;
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }
        System.out.println("list1=" + JSON.toJSONString(list));
        return list;
    }

    /**
     * 时间复杂度 O(n)
     * 空间复杂度O(1)
     */
    @Test
    public void test1() {
        List<Integer> list = list();

        for (int i = 0; i<list.size()/2;i++){
            Integer temp = list.get(i);
            list.set(i,list.get(list.size()-i-1));
            list.set(list.size()-i-1,temp);
        }

        System.out.println("list2="+JSON.toJSONString(list));
    }
}
