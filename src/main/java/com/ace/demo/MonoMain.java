package com.ace.demo;

import reactor.core.publisher.Mono;

public class MonoMain {

    public static void main(String[] args) throws InterruptedException {
        // Create Mono publisher
        // mono is publisher that is designed for emiting one at a time when subscriber request;
        Mono<String> mono = Mono.just(getMonoResultFromDBorMicroservices());

        //method to subscribe the mono
        mono.subscribe(o -> System.out.println(o));

        //mono that emits empty
        //empty Mono that completes without emitting anything.
        Mono emptyMono = Mono.empty();

        //hasElement provide boolean mono element we can check weather the mono is empty or not
        Mono<Boolean> hasElement = mono.hasElement();
        hasElement.flatMap(hasElementt -> {
                    if (hasElementt) {
                        return mono;
                    } else {
                        return Mono.just("Default Value");
                    }
                })
                .subscribe(
                        value -> System.out.println("Received: " + value),
                        error -> System.err.println("Error: " + error),
                        () -> System.out.println("Completed")
                );

        //create mono that terminate with an error
       // Mono<String> errorMono = Mono.error(new RuntimeException("Something went wrong"));
        //errorMono.subscribe();

        Mono<String> callableMono = Mono.fromCallable(() -> "Callable Result");
        callableMono.subscribe(s -> System.out.println(s));

        //Thread.sleep(10000);


    }

    static String getMonoResultFromDBorMicroservices() throws InterruptedException {
        return "Result for Product";
    }
}
