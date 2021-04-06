package ru.geekbrains.race;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Road extends Stage {
    private boolean isFinish;
    private boolean hasWinner = false;
    private Lock winnerLock = new ReentrantLock();

    public Road(int length, boolean isFinish) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
        this.isFinish = isFinish;
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
            if (isFinish) {
                winnerLock.lock();
                if (!hasWinner) {
                    hasWinner = true;
                    System.out.println(c.getName() + " победитель!");
                } else {
                    System.out.println(c.getName() + " пришел на финиш");
                }
                winnerLock.unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}