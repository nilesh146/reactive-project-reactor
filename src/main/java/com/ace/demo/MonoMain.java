package com.ace.demo;

import reactor.core.publisher.Mono;

public class MonoMain {

    public static void main(String[] args) {

        // mono is publisher that is designed for emiting one at a time when subscriber request;
        Mono mono=Mono.just("hello");

        mono.subscribe(o -> System.out.println(o));
        mono.subscribe(o -> System.out.println(o));
        mono.subscribe(o -> System.out.println(o));


        //mono that emits empty
        Mono emptyMono=Mono.empty();

        //hasElement provide boolean mono element
        Mono<Boolean> hasElement=mono.hasElement();
        hasElement.flatMap(hasElementt -> {
                   if (hasElementt) {
                        return emptyMono;
                    } else {
                        return Mono.just("Default Value");
                    }
                })
                .subscribe(
                        value -> System.out.println("Received: " + value),
                        error -> System.err.println("Error: " + error),
                        () -> System.out.println("Completed")
                );


    }
}
