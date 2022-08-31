package com.hyf.cloud.test;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

@SpringBootTest
public class tea {

    @Test
    public void tea() throws ExecutionException, InterruptedException {
        FutureTask<String> ft2 = new FutureTask<>(new T2Task());
        // 创建任务T1的FutureTask
        FutureTask<String> ft1 = new FutureTask<>(new T1Task(ft2));

        // 线程T1执行任务ft2
        Thread T1 = new Thread(ft2);
        T1.start();
        // 线程T2执行任务ft1
        Thread T2 = new Thread(ft1);
        T2.start();
        // 等待线程T1执行结果
        System.out.println(ft1.get());
    }

    class T1Task implements Callable<String> {
        FutureTask<String> ft2;

        // T1任务需要T2任务的FutureTask
        T1Task(FutureTask<String> ft2) {
            this.ft2 = ft2;
        }

        @Override
        public String call() throws Exception {
            System.out.println("T1:洗水壶...");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T1:烧开水...");
            TimeUnit.SECONDS.sleep(15);
            // 获取T2线程的茶叶
            String tf = ft2.get();
            System.out.println("T1:拿到茶叶:" + tf);

            System.out.println("T1:泡茶...");
            return "上茶:" + tf;
        }
    }

    // T2Task需要执行的任务:
// 洗茶壶、洗茶杯、拿茶叶
    class T2Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("T2:洗茶壶...");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T2:洗茶杯...");
            TimeUnit.SECONDS.sleep(2);

            System.out.println("T2:拿茶叶...");
            TimeUnit.SECONDS.sleep(1);
            return "龙井";
        }
    }


    @Test
    public void test() {


        //任务1：洗水壶->烧开水
        CompletableFuture<Void> f1 = CompletableFuture
                .runAsync(() -> {
                    System.out.println("T1:洗水壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T1:烧开水...");
                    sleep(15, TimeUnit.SECONDS);
                });
        //任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("T2:洗茶壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T2:洗茶杯...");
                    sleep(2, TimeUnit.SECONDS);

                    System.out.println("T2:拿茶叶...");
                    sleep(1, TimeUnit.SECONDS);
                    return "龙井";
                });
        //任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tf) -> {
            System.out.println("T1:拿到茶叶:" + tf);
            System.out.println("T1:泡茶...");
            return "上茶:" + tf;
        });
        //等待任务3执行结果
        System.out.println(f3.join());
    }

    static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
        }

    }
}
