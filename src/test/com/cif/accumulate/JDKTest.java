package com.cif.accumulate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: liuxincai
 * @Description: JDK源码相关测试
 * @Date: 2019/10/11 17:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JDKTest {

    @Test
    public void test(){
        List<Integer> linkedList = new LinkedList<>();
        List<Integer> newArrayList = new ArrayList<>(18);

        List<Integer> list = new ArrayList<Integer>(){{add(300);add(399);add(30);add(39);add(3);add(9);}};
        for(int index = 0;index<list.size();index++){
            System.out.println(list.get(index));
        }

        long count = list.stream().filter(l -> l == 100).count();

        System.out.println(list.subList(0,2));
    }
}
