// Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
// Используйте wait/notify/notifyAll.
package com.geekbrains;

public class FiveThread {
    private final Object object = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        FiveThread fiveThread = new FiveThread();
        Thread thread1 = new Thread(() -> {
            fiveThread.printA();
        });
        Thread thread2 = new Thread(() -> {
            fiveThread.printB();
        });
        Thread thread3 = new Thread(() -> {
            fiveThread.printC();
        });
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public void printA() {
        synchronized (object) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A') {
                        object.wait();
                    }
                    System.out.print("A");
                    currentLetter = 'B';
                    object.notifyAll();
                }
            } catch (InterruptedException e) {
                System.out.println(e.getCause());
            }
        }

    }

    public void printB() {
        synchronized (object) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B') {
                        object.wait();
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    object.notifyAll();
                }
            } catch (InterruptedException e) {
                System.out.println(e.getCause());
            }
        }
    }

    public void printC() {
        synchronized (object) {
            try {


                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'C') {
                        object.wait();
                    }
                    System.out.print("C");
                    currentLetter = 'A';
                    object.notifyAll();

                }
            } catch (InterruptedException e) {
                System.out.println(e.getCause());
            }

        }
    }
}