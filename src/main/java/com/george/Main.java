package com.george;


import com.george.linkedlist.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<Integer> i = new LinkedList<>();

        i.insertFirst(3);
        i.insertFirst(2);
        i.insertFirst(2);
        i.insertFirst(3);

        System.out.println(i);
        i.remove(3);
        System.out.println(i);
        for(Integer a : i ){
            System.out.println(a);
        }
    }
}
