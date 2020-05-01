package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Chen Kaihong
 * 2019-09-22 22:24
 */
public class MyThread extends Thread{

    private Ticket ticket;

    public MyThread(Ticket ticket) {
        this.ticket = ticket;
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = Executors.newCachedThreadPool();
        //ExecutorService pool2 = Executors
        for (int i = 0; i < 5; i ++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 5; i ++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }

                }
            });

        }
    }
    @Override
    public void run() {
        System.out.println(Ticket.sum);
        while (Ticket.sum > 0) {


            ticket.sale2();
        }
    }
}

class MyThread2 extends Thread {

    private Ticket ticket;
    public MyThread2(Ticket ticket) {
        this.ticket = ticket;
    }
    @Override
    public void run() {
        System.out.println(Ticket.sum);
        while (Ticket.sum > 0) {
            ticket.sale();
        }
    }
}


class ThreadVolatile extends Thread {
    private volatile boolean flag = true;

    @Override
    public void run() {
        System.out.println("开始...");
        while (flag) {
        }
        System.out.println("结束...");
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

class VolatileNoAtomic extends Thread {

    public static void main(String[] args) {
        /*VolatileNoAtomic vnas[] = new VolatileNoAtomic[10];
        for(int i = 0; i < 10; i++) {
            vnas[i] = new VolatileNoAtomic();
        }
        for(int i = 0; i < 10; i++) {
            vnas[i].start();
        }*/
       /* System.out.println("start");
        new VolatileNoAtomic().start();
        User.setFlag(false);
        System.out.println("end");*/
        new VolatileNoAtomic().start();
        new VolatileNoAtomic().start();
        new VolatileNoAtomic().start();
    }

    @Override
    public void run() {


        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User.run();*/

    }
}

class User {

}
