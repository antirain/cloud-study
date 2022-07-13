package com.hyf.consumer.controller;

import com.hyf.consumer.service.ProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

@RestController
public class ConsumerController {

    @Autowired
    ProviderClient providerClient;
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/hi-feign")
    public String hiFeign(){
        return providerClient.hi("feign");
    }

    @GetMapping("/hi-hi")
    public String hi_hi(){
        String url = "http://provider/provider/hi";
        return restTemplate.getForObject(url,String.class);
    }

    public static void main(String[] args) {
        List<Integer> numList = Arrays.asList(3, 4, 5, 6);
        Consumer<List<Integer>> squareConsumer = list -> {
            for (int i = 0; i < list.size(); i++) {
                list.set(i, list.get(i) * list.get(i));
            }
        };

        Consumer<List<Integer>> printConsumer = list -> list.forEach(n -> System.out.println(n));

        squareConsumer.andThen(printConsumer).accept(numList);
    }
}

