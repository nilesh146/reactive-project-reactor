package com.ace.demo;

import reactor.core.publisher.Mono;

public class MonoConditionallyHandlingData {

    public static void main(String[] args) {

        //filter operation
        Mono.just("Product Record").filter(s -> s.length() > 8).subscribe(s -> {
            System.out.println("filter operation");
            System.out.println(s);
            System.out.println("--------------------------------------");
        });

        //default if mono is empty
        Mono<String> defaultMono = Mono.<String>empty().defaultIfEmpty("Default Value");
        defaultMono.subscribe(s -> {
            System.out.println("defaultMono operation");
            System.out.println(s);
            System.out.println("--------------------------------------");
        });
    }
}
