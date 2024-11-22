package com.ace.demo;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MonoFromCallableExample {

    public static void main(String[] args) throws InterruptedException {
        // Create a Mono from a Callable
        Mono<String> callableMono = Mono.fromCallable(() -> {
            // Simulate a blocking operation
            Thread.sleep(1000);
            System.out.println("Callable executed on: " + Thread.currentThread().getName());
            return "Callable Result";
        });

        // Use a scheduler to perform the operation on a different thread.
        //here calling thread is independent of doing another task and will not wait for callable task to be completed.
        //if we are not providing schedular then the execution will occur on the same thread that initiates the subscription.
        //if we will not use scheduler then this callable task will be executed by same thread.
        // which in term blocked the main thread or calling thread.
        /*
        subscribeOn(Schedulers.boundedElastic()) ensures that the Callable will be executed on a thread provided by the boundedElastic scheduler.
        The boundedElastic scheduler is designed for blocking operations, providing a pool of threads that can dynamically grow as needed.
         When you subscribe to the Mono, the Callable's call method is executed on a thread from the boundedElastic scheduler.*/
        callableMono.subscribeOn(Schedulers.boundedElastic())
                .subscribe(
                        result -> System.out.println("Received: " + result + " on " + Thread.currentThread().getName()),
                        error -> System.err.println("Error: " + error),
                        () -> System.out.println("Completed on " + Thread.currentThread().getName())
                );

        // Keep the main thread alive to see async results
        System.out.println("main thread free to execute on " + Thread.currentThread().getName());
        Thread.sleep(2000); // Adjust sleep time as needed
    }
}
