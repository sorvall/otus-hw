package ru.sorokin;

public class MyCounter {
    int i = 0;
    boolean goDecr = false;

    public void run() throws InterruptedException {
        synchronized (this) {
            while (i < 10 && !goDecr) {
                i++;
                System.out.println(Thread.currentThread().getName() + " " + i);
                notifyAll();
                wait();
            }
            goDecr = true;
            while (i > 1 && goDecr) {
                i--;
                System.out.println(Thread.currentThread().getName() + " " + i);
                notifyAll();
                wait();
            }
            notifyAll();
        }
    }
}
