package com.hyf.cloud.test;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@SpringBootTest
public class CreateServiceDao {

    DataSourceConfig.Builder dataSourceBuilder;


    @BeforeEach
    public void before() {
        dataSourceBuilder = new DataSourceConfig.Builder("jdbc:mysql://124.222.193.26:3306/oauth?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false", "root", "123456")
                .dbQuery(new MySqlQuery())
                .typeConvert(new MySqlTypeConvert());
    }

    private static final String pathPrefix = "D:\\cloud-study\\redis-mysql\\src\\main\\";
    private static final String domainPacageName = "domain";
    private static final String mapperPacageName = "mapper";
    private static final String xmlPacageName = "mapper";
    private static final String packageParent = "com.hyf.cloud";
    private static String[] tablePrefix = {"COMM_", "REG_CO_", "REG_IN_", "E_", "AN_"}; //"COMM_CODE_BZHS"

    @Test
    public void generator() {
        DataSourceConfig.Builder dataSourceBuilder = new DataSourceConfig.Builder("jdbc:mysql://124.222.193.26:3306/oauth?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false", "root", "123456")
                .dbQuery(new MySqlQuery())
                .typeConvert(new MySqlTypeConvert());

        FastAutoGenerator.create(dataSourceBuilder)
                .globalConfig(builder -> {
                    builder.author("heyf") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir(pathPrefix + "java"); // 指定输出目录
                })
                .packageConfig((builder) -> builder.parent(packageParent)
                        .entity(domainPacageName)
                        .mapper(mapperPacageName)
                        .xml(xmlPacageName)
                        .pathInfo(Collections.singletonMap(OutputFile.xml, pathPrefix + "resources\\" + xmlPacageName)))
                .strategyConfig(builder -> {
                    builder.addInclude("product") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })

                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }

    @Test
    public void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int result = 100;
            System.out.println("第一次运算：" + result);
            return result;
        }).thenApply(number -> {
            int result = number * 3;
            System.out.println("第二次运算：" + result);
            return result;
        });
        Integer integer = future.get();
        System.out.println(integer);
    }

    @Test
    public void thenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture
                .supplyAsync(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        int number = new Random().nextInt(30);
                        System.out.println("第一次运算：" + number);
                        return number;
                    }
                })
                .thenCompose(param -> CompletableFuture.supplyAsync(() -> {
                    int number = param * 2;
                    System.out.println("第二次运算：" + number);
                    return number;
                }))
                .thenCompose(param -> CompletableFuture.supplyAsync(() -> {
                    int number = param * 2;
                    System.out.println("第二次运算：" + number);
                    return number;
                }));
        Integer integer = future.get();
        System.out.println(integer);

    }

    @Test
    public void thenAcceptBoth() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(3) + 1;
            try {
                TimeUnit.SECONDS.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1结果：" + number);
            return number;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(3) + 1;
            try {
                TimeUnit.SECONDS.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务2结果：" + number);
            return number;
        });
        Integer integer1 = future1.get();
        Integer integer2 = future2.get();
        System.out.println(integer1 + ":" + integer2);
        future1.thenAcceptBoth(future2, (x, y) -> System.out.println("最终结果：" + (x + y)));

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(10);
            System.out.println("第一阶段：" + number);
            return number;
        }).thenRun(() ->
                System.out.println("thenRun 执行"));

    }

    @Test
    public void acceptEither() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(10) + 1;
            try {
                TimeUnit.SECONDS.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一阶段：" + number);
            return number;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(10) + 1;
            try {
                TimeUnit.SECONDS.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第二阶段：" + number);
            return number;
        });

        future1.acceptEither(future2, (number) -> System.out.println("最快结果：" + number));

        Integer integer1 = future1.get();
        Integer integer2 = future2.get();
        System.out.println(integer1 + ":" + integer2);
    }
}



