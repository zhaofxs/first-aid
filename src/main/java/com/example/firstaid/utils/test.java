package com.example.firstaid.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class test {
    public static void main(String[]args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0;i<10;i++){

            Callable c = new Callable() {

                @Override
                public String call() {
                    System.out.println("1111");
                    return "111";
                }
            };
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("222");
                }
            };
            executorService.submit(c);
            executorService.execute(runnable);

        }

/*
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("222");
            }
        };
        Thread t = new Thread(runnable);
        t.start();*/

    }
}
