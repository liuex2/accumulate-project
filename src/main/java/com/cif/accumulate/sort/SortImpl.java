package com.cif.accumulate.sort;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;


/**
 * @Author: liuxincai
 * @Description: 排序实现类
 * @Date: 2019/9/29 11:40
 */
public class SortImpl {

    public static void main(String[] args) {
        int[] array = new int[]{20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22};
        long start = System.currentTimeMillis();
        //bubbleSort(array);
        //cocktailSort(array);
        selectionSort(array);
        long end = System.currentTimeMillis();
        long time = end-start;
        System.out.println("array="+ JSON.toJSONString(array));
        System.out.println("耗时="+time+"ms");

        ArrayList<Integer> integers = new ArrayList<Integer>() {{
            add(3);
            add(300);
            add(3002222);
        }};
        System.out.println("new ArrayList"+integers);

    }

    /**
     * 冒泡排序
     * @param array
     */
    public static void bubbleSort(int[] array) {
        if (array.length == 0) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            //设置标识
            boolean isSwap = false;

            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int mix = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = mix;
                    isSwap = true;
                }
            }

            //排序完成，跳出循环
            if (!isSwap) {
                break;
            }

        }
    }

    /**
     * 鸡尾酒排序
     *在把最大的数往后面冒泡的同时，把最小的数也往前面冒泡，同时收缩无序区的左右边界，有序区在序列左右逐渐累积。
     * @param array
     */
    public static void cocktailSort(int[] array) {
        int left = 0, right = array.length-1;
        while(left<right){
            for(int i=left;i<right;i++){
                if(array[i]>array[i+1]){
                    swap(array,i,i+1);
                }
            }
            right--;
            for(int j=right;j>left;j--){
                if(array[j-1]>array[j]){
                    swap(array,j,j-1);
                }
            }
            left++;
        }
    }
    private static void swap(int[] x, int a, int b) {
        int t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    /**
     * 选择排序
     * 原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
     * @param array
     */
    public static void selectionSort(int[] array){
        if(array.length==0){return;}
        for(int i = 0;i<array.length;i++){
            int midIndex = i;
            for (int j = i;j<array.length;j++){
                //找到最小的数
                if(array[j]<array[midIndex]){
                    //替换索引
                    midIndex = j;
                }
            }
            //保存最小的数，并替换
            int temp = array[midIndex];
            array[midIndex] = array[i];
            array[i] = temp;
        }

    }

    /**
     * 直接插入排序
     * 工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
     * @param array
     */
    public static void insertionSort(int[] array){
        if(array.length==0){return;}
        int current = 0;
        for(int i = 0;i<array.length;i++){
            current = array[i];
            int j = array.length-1;
            while (j>=0&&current<array[j]){
                array[j+1]=array[j];
                j--;
            }

        }
    }

    /**
     * 希尔排序
     * 先将整个待排元素序列分割成 gap 个增量为 gap 的子序列（每个子序列由位置相差为 gap 的元素组成，整个序列正好分割成 gap 个子序列，
     * 每个序列中有 n / gap 个元素）分别进行直接插入排序，然后缩减增量为之前的一半再进行排序，待 gap == 1时，希尔排序就变成了直接插入排序。
     * 因为此时序列已经基本有序，直接插入排序在元素基本有序的情况下（接近最好情况），效率是很高的。gap初始值一般取 len / 2。
     * @param array
     */
    public static void shellSort(int[] array){
        int len = array.length;
        if(len==0){return;}
        int current,gap=len/2;
        while (gap>0){
            for(int i = gap;i<len;i++){
                current = array[i];
                int preIndex = i - gap;
                while (preIndex>=0&&array[preIndex]>current){
                    array[preIndex+gap] = array[preIndex];
                    preIndex-=gap;
                }
                array[preIndex+gap] = current;
            }
            gap/=2;
        }
    }

}
