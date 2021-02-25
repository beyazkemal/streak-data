package com.kemalbeyaz.main;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author kemal.beyaz
 */
public class Streaker {

    private static final List<Long> data = new LinkedList<>();
    private static final List<Long> streak = new LinkedList<>();

    public static void main(String[] args) {
        initializeListWithData();

        calculateStreakValues();

        printDataAndStreakValues();
    }

    private static void printDataAndStreakValues() {
        System.out.println("---------------------------------------------------------");
        System.out.println("Calculated Values: ");
        System.out.println("---------------------------------------------------------");

        AtomicInteger index = new AtomicInteger(0);
        data.forEach(targetData -> {
            Long streakData = streak.get(index.getAndIncrement());

            String message = String.format("   %s   |   %s   ", targetData, streakData);
            System.out.println(message);
        });

        System.out.println("---------------------------------------------------------");
    }

    private static void calculateStreakValues() {
        AtomicInteger index = new AtomicInteger(0);
        AtomicLong lastData = new AtomicLong(0);

        data.forEach(targetData -> {
            System.out.println("Target data: " + targetData);

            long compared = 0;
            if (index.getAndIncrement() != 0) {
                compared = compareTwoNumber(lastData.get(), targetData);
            }

            streak.add(compared);
            lastData.set(targetData);
            System.out.println("Value: " + compared);
        });
    }

    private static long compareTwoNumber(long last, long target) {
        if (last == target) {
            return 0;
        }

        return target - last;
    }

    private static void initializeListWithData() {
        data.add(10L);
        data.add(10L);
        data.add(11L);
        data.add(13L);
        data.add(17L);
        data.add(14L);
        data.add(12L);
        data.add(8L);
        data.add(8L);
        data.add(8L);
        data.add(12L);
        data.add(22L);
        data.add(22L);
        data.add(32L);
    }

}
