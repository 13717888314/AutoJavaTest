package com.test;

import java.util.Scanner;

//计算字符串最后一个单词的长度，单词以空格隔开。
public class niukeTest {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String[] str = s1.split(" ");
        System.out.print(str[str.length-1].length());

    }
}
