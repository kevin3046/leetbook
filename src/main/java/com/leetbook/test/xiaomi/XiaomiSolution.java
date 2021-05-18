package com.leetbook.test.xiaomi;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/5/7 17:53
 * @Description:三个线程交替打印ABC
 */
public class XiaomiSolution {

    private static ReentrantLock lock = new ReentrantLock();
    private static int times = 0;


    public static void main(String[] args) {

        try {
            (new ThreadA()).start();
            Thread.sleep(100);
            (new ThreadB()).start();
            (new ThreadC()).start();
        } catch (Exception e) {

        }
    }

    public static class ThreadA extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 5; ) {
                lock.lock();
                try {
                    while (times % 3 == 0) {
                        System.out.println("A");
                        times++;
                        i++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }
    }

    public static class ThreadB extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 5; ) {
                lock.lock();
                try {
                    while (times % 3 == 1) {
                        System.out.println("B");
                        times++;
                        i++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static class ThreadC extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 5; ) {
                lock.lock();
                try {
                    while (times % 3 == 2) {
                        System.out.println("C");
                        times++;
                        i++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}


