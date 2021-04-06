package ru.geekbrains.race;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch CDLFinish = new CountDownLatch(CARS_COUNT);
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60, false), new Tunnel(), new Road(40, true));
        Car[] cars = new Car[CARS_COUNT];
        CyclicBarrier cb = new CyclicBarrier(CARS_COUNT, () -> {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        });
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cb, CDLFinish);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        CDLFinish.await();

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
