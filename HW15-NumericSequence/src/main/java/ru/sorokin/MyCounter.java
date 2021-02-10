package ru.sorokin;

public class MyCounter {
    private int i = 0;
    private boolean goDecr = false;
    private String verificationNumberThread = "Second";

    public void run(String numberThread) throws InterruptedException {
        synchronized (this) {
            while ((i > 1 && goDecr) || (i <= 10 && !goDecr)) {
                while (numberThread.equals(verificationNumberThread)) {
                    System.out.println("Поток " + Thread.currentThread().getName() + " вне очереди! Блок");
                    this.wait();
                }
                if (i < 10 && !goDecr) {
                    i++;
                }
                else {
                    goDecr = true;
                    i--;
                }
                System.out.println(Thread.currentThread().getName() + " " + i);
                verificationNumberThread = numberThread;
                notifyAll();
                wait();
            }
            notifyAll();
        }
    }
}

