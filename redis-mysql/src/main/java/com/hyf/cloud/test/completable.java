package com.hyf.cloud.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Demo class
 *
 * @author heyifan
 * @date 2022/9/2
 */
public class completable {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Void> exceptionally = CompletableFuture.supplyAsync(completable::getDouble).thenAccept(System.out::println).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
        Thread.sleep(200);
        exceptionally.get();
    }

    static Double getDouble() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("Error when get price");
        }
        return Math.random() * 20;
    }
}
