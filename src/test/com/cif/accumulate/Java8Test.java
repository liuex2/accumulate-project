package com.cif.accumulate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: liuxincai
 * @Description: java8新特性
 * @Date: 2019/6/24 11:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Java8Test {

    @Test
    public void test(){
        System.out.println("test");
    }

    @Test
    public void lamadaTest(){
        String[] args = {"23434","4343","948320"};

        //转换成集合
        List<String> list = Arrays.asList(args);

        //lamada表达式
        list.forEach(arg->{
            System.out.println(arg);
        });

        //::应用
        list.forEach(
                System.out::println
        );

    }

    @Test
    public void streamTest(){
        String[] args = {"23434","23434","4343","4343","948320"};

        //转换集合
        List<String> list = Arrays.asList(args);

        //利用stream去重
        List<String> collect = list.stream().distinct().collect(Collectors.toList());

        collect.forEach(
                System.out::println
        );

    }

}
